<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body validform="true">
   <div class="box_01 boxBmargin12 submitdata">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="photoPicSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="photoPic.id" value="${photoPic.id}">
		<input type="hidden" name="photoPic.createTime" value="<fmt:formatDate type="both" value="${photoPic.createTime}" />">
		<input type="hidden" name="photoPic.updateTime" value="${photoPic.updateTime}">
		<input type="hidden" name="photoPic.createUserID" value="${photoPic.createUserID}">
		<input type="hidden" name="photoPic.updateUserID" value="${photoPic.updateUserID}">
		<input type="hidden" name="photoPic.deptId" value="${photoPic.deptId}">
		<input type="hidden" name="photoPic.delFlag" value="${photoPic.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">附件关联id</th>
					<td width="35%"><input name="photoPic.linkId" value="${photoPic.linkId}" type="text" maxlength="127"></td>
					<th width="15%">内网url</th>
					<td width="35%"><input name="photoPic.nwUrl" value="${photoPic.nwUrl}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">模块类型</th>
					<td width="35%"><input name="photoPic.mkType" value="${photoPic.mkType}" type="text" maxlength="127"></td>
					<th width="15%">附件真实名称</th>
					<td width="35%"><input name="photoPic.fileName" value="${photoPic.fileName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">服务器url</th>
					<td width="35%"><input name="photoPic.httpUrl" value="${photoPic.httpUrl}" type="text" maxlength="127"></td>
					<th width="15%">附件名称</th>
					<td width="35%"><input name="photoPic.picName" value="${photoPic.picName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">附件类型</th>
					<td width="35%"><input name="photoPic.picType" value="${photoPic.picType}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_photoPic');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
