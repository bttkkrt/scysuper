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
	<form name="myform1" method="post" enctype="multipart/form-data" action="reviewSubmissionSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="reviewSubmission.id" value="${reviewSubmission.id}">
		<input type="hidden" name="reviewSubmission.createTime" value="<fmt:formatDate type="both" value="${reviewSubmission.createTime}" />">
		<input type="hidden" name="reviewSubmission.updateTime" value="${reviewSubmission.updateTime}">
		<input type="hidden" name="reviewSubmission.createUserID" value="${reviewSubmission.createUserID}">
		<input type="hidden" name="reviewSubmission.updateUserID" value="${reviewSubmission.updateUserID}">
		<input type="hidden" name="reviewSubmission.deptId" value="${reviewSubmission.deptId}">
		<input type="hidden" name="reviewSubmission.delFlag" value="${reviewSubmission.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">意见</th>
					<td width="35%"><input name="reviewSubmission.reviewComment" value="${reviewSubmission.reviewComment}" type="text" maxlength="127"></td>
					<th width="15%">执法人员</th>
					<td width="35%"><input name="reviewSubmission.lawOfficer" value="${reviewSubmission.lawOfficer}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="reviewSubmission.relatedId" value="${reviewSubmission.relatedId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_reviewSubmission');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
