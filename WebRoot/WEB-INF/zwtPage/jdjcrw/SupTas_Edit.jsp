<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	  function queryQy3()
		{
		var checkUserId=document.getElementById("supTas_checkUserid").value;
		if(checkUserId==""){
		   alert("请先选择承办人");
		}
		else{
			popupCenter("${ctx}/jsp/jdjcjh/selectCompanys2.action?supPla.addType=0&supPla.checkUserId="+checkUserId, "setUser", "800", "600", "no", "no", "no", "no", "no","no");
		}
	}
		  function clearCompany(){
        	document.getElementById("companyName").value="";
        	document.getElementById("companyId").value="";
        }
         function saveSelText(i,text){
			$("#"+i).val(text);
			$("#companyIds").val("");
			$("#companyNames").val("");
		}
		
		function saveSelText1(i,text){
			$("#"+i).val(text);
		}
		
		function check(){
		var vs=$("#checkItemId").val();
         	$.ajax({
		                	url : "check.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
			"supTas.checkItemId":vs
		                    },
		                   success: function(data){
		                     if(data.result){

		                        }else{
		                        	$.messager.alert('提示','该检查项类型没有检查项，请先添加检查项');
		                        	$("#checkItemId").val("");
		                        }
		                      }
		});
         
         
         
		}
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="supTasSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="supTas.id" value="${supTas.id}">
		<input type="hidden" name="supTas.createTime" value="<fmt:formatDate type="both" value="${supTas.createTime}" />">
		<input type="hidden" name="supTas.updateTime" value="${supTas.updateTime}">
		<input type="hidden" name="supTas.createUserID" value="${supTas.createUserID}">
		<input type="hidden" name="supTas.updateUserID" value="${supTas.updateUserID}">
		<input type="hidden" name="supTas.deptId" value="${supTas.deptId}">
		<input type="hidden" name="supTas.delFlag" value="${supTas.delFlag}">
		<input type="hidden" id="checkUsername" name="supTas.checkUsername" value="${supTas.checkUsername}"/>
		<input type="hidden" id="xbUserName" name="supTas.xbUserName" value="${supTas.xbUserName}"/>
			<table width="100%" border="0">
				
				<tr>
					<th width="15%">任务名称</th>
					<td width="85%" colspan="3"><input name="supTas.taskName" style="width: 80%"  value="${supTas.taskName}" type="text" datatype="*1-127" errormsg='任务名称必须是1到20位字符！' nullmsg='任务名称不能为空！' sucmsg='任务名称填写正确！'  maxlength="20"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">检查项类型</th>
					<td width="85%" colspan="3"><cus:hxcheckbox   property="supTas.checkItemId"   codeSql="select t.row_id,t.PATROL_TYPE_NAME from PAT_TYP_MAN t where t.delflag = 0" value="${supTas.checkItemId}"  datatype="*"  nullmsg='检查项类型不能为空！' sucmsg='检查项类型填写正确！'/><font style='color:red'>*</font></td>
				</tr>
				<tr>	
					<th width="15%">承办人</th>
							<td width="35%">
								<s:select theme="simple" cssStyle="width:60%"  list="persons" listKey="id" listValue="name"  name="supTas.checkUserid"
	 								  headerKey="" headerValue="--请选择--" datatype="*"  nullmsg='承办人不能为空！' onchange="saveSelText('checkUsername',jQuery('#supTas_checkUserid  option:selected').text())">
	 						    </s:select>
	 						    <font style='color:red'>*</font>
					</td>
					<th width="15%">协办人</th>
							<td width="35%">
								<s:select theme="simple" list="persons" cssStyle="width:60%" listKey="id" listValue="name"  name="supTas.xbUserId"
	 								  headerKey="" headerValue="--请选择--" datatype="*"  nullmsg='协办人不能为空！' onchange="saveSelText1('xbUserName',jQuery('#supTas_xbUserId  option:selected').text())">
	 						    </s:select>
	 						    <font style='color:red'>*</font>
					</td>
				</tr>
			
				<tr>
					<th width="15%">任务开始时间</th>
					<td width="35%"><input id="stime" name="supTas.stime" style="width:60%" value="<fmt:formatDate type='both' value='${supTas.stime}' pattern="yyyy-MM-dd"/>" type="text" datatype="*"  nullmsg='任务开始时间不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'ftime\')}'})"><font style='color:red'>*</font></td>
					<th width="15%">任务结束时间</th>
					<td width="35%"><input id="ftime" name="supTas.ftime" style="width:60%" value="<fmt:formatDate type='both' value='${supTas.ftime}' pattern="yyyy-MM-dd"/>" type="text" datatype="*"  nullmsg='任务结束时间不能为空！'  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'stime\')}'})"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<tr>
					<th width="15%">检查对象名称</th>
					<td width="85%" colspan="3">
						<input type="hidden" id="companyIds" name="supTas.companyId" value="${supTas.companyId}"/>
						<textarea readonly="readonly" style="width:85%;height:100px;" id="companyNames" name="supTas.companyName" datatype="*"  nullmsg='检查对象不能为空！'>${supTas.companyName}</textarea>
						<a href="###" class="btn_01_mini1" onclick="queryQy3()">选择</a>
					</td>
				</tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
