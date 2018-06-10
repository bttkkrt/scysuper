<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="inventoryCheckSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="inventoryCheck.id" value="${inventoryCheck.id}">
		<input type="hidden" name="inventoryCheck.createTime" value="<fmt:formatDate type="both" value="${inventoryCheck.createTime}" />">
		<input type="hidden" name="inventoryCheck.updateTime" value="${inventoryCheck.updateTime}">
		<input type="hidden" name="inventoryCheck.createUserID" value="${inventoryCheck.createUserID}">
		<input type="hidden" name="inventoryCheck.updateUserID" value="${inventoryCheck.updateUserID}">
		<input type="hidden" name="inventoryCheck.deptId" value="${inventoryCheck.deptId}">
		<input type="hidden" name="inventoryCheck.delFlag" value="${inventoryCheck.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">提请理由及依据</th>
					<td width="35%"><input name="inventoryCheck.reasonBasic" value="${inventoryCheck.reasonBasic}" type="text" maxlength="127"></td>
					<th width="15%">承办人意见</th>
					<td width="35%"><input name="inventoryCheck.undertakerComment" value="${inventoryCheck.undertakerComment}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">机关负责人意见</th>
					<td width="35%"><input name="inventoryCheck.officeComment" value="${inventoryCheck.officeComment}" type="text" maxlength="127"></td>
					<th width="15%">部门负责人</th>
					<td width="35%"><input name="inventoryCheck.departPerson" value="${inventoryCheck.departPerson}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">部门负责人意见</th>
					<td width="35%"><input name="inventoryCheck.departComment" value="${inventoryCheck.departComment}" type="text" maxlength="127"></td>
					<th width="15%">机关负责人</th>
					<td width="35%"><input name="inventoryCheck.officePerson" value="${inventoryCheck.officePerson}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="inventoryCheck.relatedId" value="${inventoryCheck.relatedId}" type="text" maxlength="127"></td>
					<th width="15%">承办人</th>
					<td width="35%"><input name="inventoryCheck.undertaker" value="${inventoryCheck.undertaker}" type="text" maxlength="127"></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_inventoryCheck');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
