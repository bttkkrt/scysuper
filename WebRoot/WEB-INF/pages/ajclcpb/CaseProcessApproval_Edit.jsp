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
	<form name="myform1" method="post" enctype="multipart/form-data" action="caseProcessApprovalSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="caseProcessApproval.id" value="${caseProcessApproval.id}">
		<input type="hidden" name="caseProcessApproval.createTime" value="<fmt:formatDate type="both" value="${caseProcessApproval.createTime}" />">
		<input type="hidden" name="caseProcessApproval.updateTime" value="${caseProcessApproval.updateTime}">
		<input type="hidden" name="caseProcessApproval.createUserID" value="${caseProcessApproval.createUserID}">
		<input type="hidden" name="caseProcessApproval.updateUserID" value="${caseProcessApproval.updateUserID}">
		<input type="hidden" name="caseProcessApproval.deptId" value="${caseProcessApproval.deptId}">
		<input type="hidden" name="caseProcessApproval.delFlag" value="${caseProcessApproval.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">承办人意见</th>
					<td width="35%"><input name="caseProcessApproval.undertakerComment" value="${caseProcessApproval.undertakerComment}" type="text" maxlength="127"></td>
					<th width="15%">审批意见</th>
					<td width="35%"><input name="caseProcessApproval.approverComment" value="${caseProcessApproval.approverComment}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">审核人</th>
					<td width="35%"><input name="caseProcessApproval.checkPerson" value="${caseProcessApproval.checkPerson}" type="text" maxlength="127"></td>
					<th width="15%">审核意见</th>
					<td width="35%"><input name="caseProcessApproval.checkComment" value="${caseProcessApproval.checkComment}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">审批人</th>
					<td width="35%"><input name="caseProcessApproval.approverPerson" value="${caseProcessApproval.approverPerson}" type="text" maxlength="127"></td>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="caseProcessApproval.relatedId" value="${caseProcessApproval.relatedId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">承办人</th>
					<td width="35%"><input name="caseProcessApproval.undertaker" value="${caseProcessApproval.undertaker}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_caseProcessApproval');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
