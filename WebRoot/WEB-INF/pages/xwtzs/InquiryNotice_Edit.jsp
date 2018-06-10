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
	<form name="myform1" method="post" enctype="multipart/form-data" action="inquiryNoticeSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="inquiryNotice.id" value="${inquiryNotice.id}">
		<input type="hidden" name="inquiryNotice.createTime" value="<fmt:formatDate type="both" value="${inquiryNotice.createTime}" />">
		<input type="hidden" name="inquiryNotice.updateTime" value="${inquiryNotice.updateTime}">
		<input type="hidden" name="inquiryNotice.createUserID" value="${inquiryNotice.createUserID}">
		<input type="hidden" name="inquiryNotice.updateUserID" value="${inquiryNotice.updateUserID}">
		<input type="hidden" name="inquiryNotice.deptId" value="${inquiryNotice.deptId}">
		<input type="hidden" name="inquiryNotice.delFlag" value="${inquiryNotice.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="inquiryNotice.relatedId" value="${inquiryNotice.relatedId}" type="text" maxlength="127"></td>
					<th width="15%">询问时间</th>
					<td width="35%"><input name="inquiryNotice.inquiryTime" value="<fmt:formatDate type='both' value='${inquiryNotice.inquiryTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
				</tr>
				<tr>
					<th width="15%">询问地点</th>
					<td width="35%"><input name="inquiryNotice.inquiryAddress" value="${inquiryNotice.inquiryAddress}" type="text" maxlength="127"></td>
					<th width="15%">证件材料</th>
					<td width="35%"><input name="inquiryNotice.docMaterial" value="${inquiryNotice.docMaterial}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">联系人</th>
					<td width="35%"><input name="inquiryNotice.contact" value="${inquiryNotice.contact}" type="text" maxlength="127"></td>
					<th width="15%">联系电话</th>
					<td width="35%"><input name="inquiryNotice.tele" value="${inquiryNotice.tele}" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_inquiryNotice');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
