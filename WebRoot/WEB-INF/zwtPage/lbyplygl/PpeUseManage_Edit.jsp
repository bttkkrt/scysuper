<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="ppeUseManageSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="ppeUseManage.id" value="${ppeUseManage.id}">
		<input type="hidden" name="ppeUseManage.createTime" value="<fmt:formatDate type="both" value="${ppeUseManage.createTime}" />">
		<input type="hidden" name="ppeUseManage.updateTime" value="${ppeUseManage.updateTime}">
		<input type="hidden" name="ppeUseManage.createUserID" value="${ppeUseManage.createUserID}">
		<input type="hidden" name="ppeUseManage.updateUserID" value="${ppeUseManage.updateUserID}">
		<input type="hidden" name="ppeUseManage.deptId" value="${ppeUseManage.deptId}">
		<input type="hidden" name="ppeUseManage.delFlag" value="${ppeUseManage.delFlag}">
		<input type="hidden" name="ppeUseManage.areaId" value="${ppeUseManage.areaId}">
		<input type="hidden" name="ppeUseManage.areaName" value="${ppeUseManage.areaName}">
		<input type="hidden" name="ppeUseManage.companyId" value="${ppeUseManage.companyId}">
		<input type="hidden" name="ppeUseManage.companyName" value="${ppeUseManage.companyName}">
		
			<table width="100%" border="0">
				<!-- <tr>
					<th width="15%">所在区域</th>
					<td width="35%"><input name="ppeUseManage.areaName" style="width:60%" value="${ppeUseManage.areaName}" type="text" maxlength="127"></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="ppeUseManage.companyName" style="width:60%" value="${ppeUseManage.companyName}" type="text" maxlength="127"></td>
				</tr>
				 -->
				<tr>
					
					<th width="15%">用品名称</th>
					<td width="35%">
						<cus:SelectOneTag style="width:60%"  dataType="*"  property="ppeUseManage.ppeId" defaultText='请选择' codeSql="select t.ROW_ID,t.PPE_NAME from PPE_MANAG t where t.delflag = 0 and t.COMPANY_ID='${ppeUseManage.companyId}'" value="${ppeUseManage.ppeId}" /><font style='color:red'>*</font>
					</td>
					<th width="15%">领用人</th>
					<td width="35%"><input name="ppeUseManage.ppeUsePeople" style="width:60%" errormsg='领用人必须是1到127位字符！' nullmsg='领用人不能为空！' sucmsg='领用人填写正确！'   datatype="*1-127"  value="${ppeUseManage.ppeUsePeople}" type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">领用数量</th>
					<td width="35%"><input name="ppeUseManage.ppeUseNum" style="width:60%" value="${ppeUseManage.ppeUseNum}"   type="text" maxlength="127"></td>
					<th width="15%">领用时间</th>
					<td width="35%"><input name="ppeUseManage.ppeUseTime" style="width:60%" dataType="*"  style="width:150px;" value="<fmt:formatDate pattern='yyyy-MM-dd' type='both' value='${ppeUseManage.ppeUseTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font style='color:red'>*</font></td>
			
				</tr>
				<tr>
				<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea name="ppeUseManage.ppeUseRemark" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:96%;height:60px">${ppeUseManage.ppeUseRemark}</textarea></td>
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
