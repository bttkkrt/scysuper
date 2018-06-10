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
	<form name="myform1" method="post" enctype="multipart/form-data" action="preserveEvidenceSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="preserveEvidence.id" value="${preserveEvidence.id}">
		<input type="hidden" name="preserveEvidence.createTime" value="<fmt:formatDate type="both" value="${preserveEvidence.createTime}" />">
		<input type="hidden" name="preserveEvidence.updateTime" value="${preserveEvidence.updateTime}">
		<input type="hidden" name="preserveEvidence.createUserID" value="${preserveEvidence.createUserID}">
		<input type="hidden" name="preserveEvidence.updateUserID" value="${preserveEvidence.updateUserID}">
		<input type="hidden" name="preserveEvidence.deptId" value="${preserveEvidence.deptId}">
		<input type="hidden" name="preserveEvidence.delFlag" value="${preserveEvidence.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">提请理由及依据</th>
					<td width="35%"><input name="preserveEvidence.reasonBasic" value="${preserveEvidence.reasonBasic}" type="text" maxlength="127"></td>
					<th width="15%">保存方式</th>
					<td width="35%"><input name="preserveEvidence.preserveMethod" value="${preserveEvidence.preserveMethod}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">机关负责人意见</th>
					<td width="35%"><input name="preserveEvidence.officeComment" value="${preserveEvidence.officeComment}" type="text" maxlength="127"></td>
					<th width="15%">承办人意见</th>
					<td width="35%"><input name="preserveEvidence.undertakerComment" value="${preserveEvidence.undertakerComment}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">部门负责人</th>
					<td width="35%"><input name="preserveEvidence.departPerson" value="${preserveEvidence.departPerson}" type="text" maxlength="127"></td>
					<th width="15%">部门负责人意见</th>
					<td width="35%"><input name="preserveEvidence.departComment" value="${preserveEvidence.departComment}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">机关负责人</th>
					<td width="35%"><input name="preserveEvidence.officePerson" value="${preserveEvidence.officePerson}" type="text" maxlength="127"></td>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="preserveEvidence.relatedId" value="${preserveEvidence.relatedId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">承办人</th>
					<td width="35%"><input name="preserveEvidence.undertaker" value="${preserveEvidence.undertaker}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_preserveEvidence');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
