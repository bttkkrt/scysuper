<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        $(function(){
        	$("input[name='companyScore.checkScore']").keyup(function(){  
	            var c=$(this);  
	            if(/[^0-9]*/.test(c.val())){//替换非数字字符  
	              var temp_amount=c.val().replace(/[^0-9]*/g,'');  
	              $(this).val(temp_amount);  
	            }  
         	}) ;
        });
        
        
        
        function setValue(obj,index)
        {
        	if(obj.checked)
        	{
        		document.getElementById('ifcp' + index).value = "1";
        	}
        	else
        	{
        		document.getElementById('ifcp' + index).value = "0";
        	}
        }
        
         function getScore()
        {
        	$.ajax({
					cache: false,
					type: "POST",
					dataType: 'json',
					url:"getScoreBzhpf.action", //把表单数据发送到entBaseInfoSaveRegister.action
					data:$('#myform1').serialize(), //要发送的是ajaxFrm表单中的数据
					async: false,
					error: function(request) {
						alert("计算失败");
					},
					success: function(data) {
 						if(data.result){
        					document.getElementById('score').innerHTML = data.score;
        				}else{
        					alert("计算失败");
        				}
					}
 
				});
        }
        
        function save(state)
	{
		document.getElementById('state').value = state; 
	}	
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: ">
			<font style='color:red'>*若信息还未填写完整，可以点击保存暂存（安监查看不到），若填写完整，则点击提交上报安监审核，提交后信息不可修改删除</font>
	<form id="myform1" name="myform1" method="post" enctype="multipart/form-data" action="companyScoreSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="companyScore.id" value="${companyScore.id}">
		<input type="hidden" name="companyScore.createTime" value="<fmt:formatDate type="both" value="${companyScore.createTime}" />">
		<input type="hidden" name="companyScore.updateTime" value="${companyScore.updateTime}">
		<input type="hidden" name="companyScore.createUserID" value="${companyScore.createUserID}">
		<input type="hidden" name="companyScore.updateUserID" value="${companyScore.updateUserID}">
		<input type="hidden" name="companyScore.deptId" value="${companyScore.deptId}">
		<input type="hidden" name="companyScore.delFlag" value="${companyScore.delFlag}">
		<input type="hidden" name="companyScore.companyId" value="${companyScore.companyId}">
		<input type="hidden" name="companyScore.companyName" value="${companyScore.companyName}">
		<input type="hidden" name="companyScore.areaId" value="${companyScore.areaId}">
		<input type="hidden" name="companyScore.areaName" value="${companyScore.areaName}">
		<input type="hidden" name="companyScore.startTime" value="<fmt:formatDate type="date" value="${companyScore.startTime}" />">
		<input type="hidden" name="companyScore.endTime" value="<fmt:formatDate type="date" value="${companyScore.endTime}" />">
		<input type="hidden" name="companyScore.systemScore" value="${companyScore.systemScore}">
		<input type="hidden" name="companyScore.type" value="${companyScore.type}">
		<input type="hidden" id="state" name="companyScore.state" value="0">
		<font style="color:blue;font-size:28px;">${companyScore.companyName}&nbsp;&nbsp;&nbsp;安全生产标准化评分</font>
			<table width="100%" border="0"  align="center" >
				<tr >
					<th width="5%" style="text-align:center">序号</th>
					<th width="20%" style="text-align:center">企业达标标准</th>
					<th width="5%" style="text-align:center">标准分值</th>
					<th width="20%" style="text-align:center">评分方式</th>
					<th width="5%" style="text-align:center">是否参评</th>
					<th width="10%" style="text-align:center">自评分</th>
					<th width="30%" style="text-align:center">扣分说明</th>
				</tr>
				<c:forEach var="checkItem" items="${list}" varStatus="status">
				<tr>
					<td width="5%" style="text-align:center">${status.index + 1}</td>
					<td width="20%" style="text-align:left">${checkItem.dbbz}</td>
					<td width="5%" style="text-align:center"><input type="hidden" name="companyScore.bzScore" value="${checkItem.bzfz}">${checkItem.bzfz}</td>
					<td width="20%" style="text-align:left">${checkItem.pffs}</td>
					<td width="5%" style="text-align:center"><input type="checkbox" checked onclick="setValue(this,'${status.index}')"/><input id="ifcp${status.index}" type="hidden" name="companyScore.ifcp" value="${checkItem.sfcp}"></td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="companyScore.checkScore" value="${checkItem.qypf}"/></td>
					<td width="30%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="companyScore.qyremark" value="${checkItem.qybz}"/></td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="5" style="text-align:center">得分</td>
					<td colspan="2" style="text-align:center"><span id="score"></span></td>
				</tr>
				<tr>
					<td colspan="7" height="100px" style="text-align:center">
						<a href="#" class="btn_01" onclick="getScore();" >计算得分<b></b></a>&nbsp;
						<a href="#" class="btn_01" onclick="save('0')" type="submit">保存<b></b></a>&nbsp;
						<a href="#" class="btn_01" onclick="save('1')" type="submit">提交<b></b></a>&nbsp;
					
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
