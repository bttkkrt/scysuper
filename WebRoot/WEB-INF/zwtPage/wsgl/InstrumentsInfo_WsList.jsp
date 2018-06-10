<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	function save()
	{
		var obj   =   document.getElementsByName('instrumentsInfo.instrumentType');
		var time = document.getElementById('time').value;   
		var type = "";
 	 	for(var i=0;i<obj.length;i++){   
    		if(obj[i].checked){   
      			type = obj[i].value;
    		}   
    	}
    	if(type == "")
    	{
    		alert("请选择一种文书类型");
    	}
    	else if(time == "")
    	{
    		alert("请选择文书时间");
    	}
    	else
    	{
    		document.myform1.submit();
    	}
	}
	
	function checkWs(obj)
	{
		document.getElementById("next").style.display = "none";
		$.ajax({
	        	url : "${ctx}/jsp/wsgl/queryWsxx.action?instrumentsInfo.caseId=${instrumentsInfo.caseId}&instrumentsInfo.instrumentType="+obj,
	        	type: 'post',
	        	dataType: 'json',
	        	async : false,
	        	error: function(){
	           	 	alert('查询时出错！');
	        	},
	        	success: function(data){
	        		var ifCan = data.ifCan;
	        		var message = data.message;
	        		if(ifCan == '0')
	        		{
	        			document.getElementById("next").style.display = "";
	        		}
	        		else
	        		{
	        			alert(message);
	        		}
	        	}
	    	});
	}
    
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: auto;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form class="checkform" name="myform1" method="post" enctype="multipart/form-data" action="instrumentsInfoInitEdit.action?flag=add">
		<s:token />
		<input type="hidden" id="caseId" name="instrumentsInfo.caseId" value="${instrumentsInfo.caseId}">
		<input type="hidden" name="instrumentsInfo.caseName" value="${instrumentsInfo.caseName}">
		<input type="hidden" id="instrumentType" value="">
		
			<table width="100%" border="0">
				<tr>
					<th colspan="4" style="text-align:center">案件文书</th>
				</tr>
				<c:forEach var="wsInfo" items="${wsList}">
					<tr>
						<th style="text-align:center">${wsInfo.wsname}</th>
						<td style="text-align:center">${wsInfo.wssl}</td>
						<td style="text-align:center">
						<c:choose>
							<c:when test="${wsInfo.sfcz==1}"> 
								<img src="${ctx}/webResources/images/wsinfo/succ.png"  border='0' width='15' height='15'/>
							</c:when> 
							<c:otherwise> 
								<img src="${ctx}/webResources/images/wsinfo/error.png"  border='0' width='15' height='15'/>
							</c:otherwise>
						</c:choose>
						</td>
						<td><input type="radio" name="instrumentsInfo.instrumentType" value="${wsInfo.wsid}" onclick="checkWs(this.value)"></td>
					</tr>
					<c:if test="${wsInfo.jt==1}"> 
						<tr>
							<td colspan="4" style="text-align:center">
								<img src="${ctx}/webResources/images/wsinfo/next.png"  border='0' width='15' height='15'/>
							</td>
						</tr>
					</c:if> 
				</c:forEach>
				<tr>
					<td colspan="4" style="text-align:center">&nbsp;</td>
				</tr>
				<tr>
					<th colspan="4" style="text-align:center">其他文书</th>
				</tr>
				<c:forEach var="wsInfo" items="${qtList}">
					<tr>
						<th style="text-align:center">${wsInfo.wsname}</th>
						<td style="text-align:center">${wsInfo.wssl}</td>
						<td style="text-align:center">
							<c:if test="${wsInfo.sfcz==1}"> 
								<img src="${ctx}/webResources/images/wsinfo/succ.png"  border='0' width='15' height='15'/>
							</c:if> 
							<c:if test="${wsInfo.sfcz!=1}"> 
								<img src="${ctx}/webResources/images/wsinfo/error.png"  border='0' width='15' height='15'/>
							</c:if> 
						</td>
						<td><input type="radio" name="instrumentsInfo.instrumentType" value="${wsInfo.wsid}" onclick="checkWs(this.value)"></td>
					</tr>
				</c:forEach>
				<tr>
					<th width="15%">文书时间</th>
					<td width="85%" colspan="3"><input id="time" name="instrumentsInfo.time" value="<fmt:formatDate type='date' value='${instrumentsInfo.time}' pattern='yyyy-MM-dd'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="127" style="width:50%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a id="next" href="#" class="btn_01" onclick="save()" style="display:none">下一步<b></b></a>&nbsp;
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
