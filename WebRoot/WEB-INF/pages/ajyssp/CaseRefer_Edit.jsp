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
	<form name="myform1" method="post" enctype="multipart/form-data" action="caseReferSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="caseRefer.id" value="${caseRefer.id}">
		<input type="hidden" name="caseRefer.createTime" value="<fmt:formatDate type="both" value="${caseRefer.createTime}" />">
		<input type="hidden" name="caseRefer.updateTime" value="${caseRefer.updateTime}">
		<input type="hidden" name="caseRefer.createUserID" value="${caseRefer.createUserID}">
		<input type="hidden" name="caseRefer.updateUserID" value="${caseRefer.updateUserID}">
		<input type="hidden" name="caseRefer.deptId" value="${caseRefer.deptId}">
		<input type="hidden" name="caseRefer.delFlag" value="${caseRefer.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">受移送机关</th>
					<td width="35%"><input name="caseRefer.transferAuthority" value="${caseRefer.transferAuthority}" type="text" maxlength="127"></td>
					<th width="15%">移送理由</th>
					<td width="35%"><input name="caseRefer.feedingGrounds" value="${caseRefer.feedingGrounds}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">承办人意见</th>
					<td width="35%"><input name="caseRefer.undertakerComment" value="${caseRefer.undertakerComment}" type="text" maxlength="127"></td>
					<th width="15%">承办人</th>
					<td width="35%"><input name="caseRefer.undertaker" value="${caseRefer.undertaker}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">部门负责人</th>
					<td width="35%"><input name="caseRefer.departPerson" value="${caseRefer.departPerson}" type="text" maxlength="127"></td>
					<th width="15%">部门负责人审核意见</th>
					<td width="35%"><input name="caseRefer.departComment" value="${caseRefer.departComment}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">机关负责人</th>
					<td width="35%"><input name="caseRefer.officePerson" value="${caseRefer.officePerson}" type="text" maxlength="127"></td>
					<th width="15%">机关负责人审核意见</th>
					<td width="35%"><input name="caseRefer.officeComment" value="${caseRefer.officeComment}" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_caseRefer');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
