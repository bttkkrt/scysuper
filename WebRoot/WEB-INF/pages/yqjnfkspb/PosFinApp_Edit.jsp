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
	<form name="myform1" method="post" enctype="multipart/form-data" action="posFinAppSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="posFinApp.id" value="${posFinApp.id}">
		<input type="hidden" name="posFinApp.createTime" value="<fmt:formatDate type="both" value="${posFinApp.createTime}" />">
		<input type="hidden" name="posFinApp.updateTime" value="${posFinApp.updateTime}">
		<input type="hidden" name="posFinApp.createUserID" value="${posFinApp.createUserID}">
		<input type="hidden" name="posFinApp.updateUserID" value="${posFinApp.updateUserID}">
		<input type="hidden" name="posFinApp.deptId" value="${posFinApp.deptId}">
		<input type="hidden" name="posFinApp.delFlag" value="${posFinApp.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">理由</th>
					<td width="35%"><input name="posFinApp.reason" value="${posFinApp.reason}" type="text" maxlength="127"></td>
					<th width="15%">承办人意见</th>
					<td width="35%"><input name="posFinApp.undertakerComment" value="${posFinApp.undertakerComment}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">审批意见</th>
					<td width="35%"><input name="posFinApp.approverComment" value="${posFinApp.approverComment}" type="text" maxlength="127"></td>
					<th width="15%">审核人</th>
					<td width="35%"><input name="posFinApp.checkPerson" value="${posFinApp.checkPerson}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">审核意见</th>
					<td width="35%"><input name="posFinApp.checkComment" value="${posFinApp.checkComment}" type="text" maxlength="127"></td>
					<th width="15%">审批人</th>
					<td width="35%"><input name="posFinApp.approver" value="${posFinApp.approver}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="posFinApp.relatedId" value="${posFinApp.relatedId}" type="text" maxlength="127"></td>
					<th width="15%">承办人</th>
					<td width="35%"><input name="posFinApp.undertaker" value="${posFinApp.undertaker}" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_posFinApp');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
