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
	<form name="myform1" method="post" enctype="multipart/form-data" action="hearingReportSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="hearingReport.id" value="${hearingReport.id}">
		<input type="hidden" name="hearingReport.createTime" value="<fmt:formatDate type="both" value="${hearingReport.createTime}" />">
		<input type="hidden" name="hearingReport.updateTime" value="${hearingReport.updateTime}">
		<input type="hidden" name="hearingReport.createUserID" value="${hearingReport.createUserID}">
		<input type="hidden" name="hearingReport.updateUserID" value="${hearingReport.updateUserID}">
		<input type="hidden" name="hearingReport.deptId" value="${hearingReport.deptId}">
		<input type="hidden" name="hearingReport.delFlag" value="${hearingReport.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">负责人审核意见</th>
					<td width="35%"><input name="hearingReport.picComment" value="${hearingReport.picComment}" type="text" maxlength="127"></td>
					<th width="15%">听证主持人意见</th>
					<td width="35%"><input name="hearingReport.hearChairComment" value="${hearingReport.hearChairComment}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">负责人</th>
					<td width="35%"><input name="hearingReport.pic" value="${hearingReport.pic}" type="text" maxlength="127"></td>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="hearingReport.relatedId" value="${hearingReport.relatedId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">听证会基本情况摘要</th>
					<td width="35%"><input name="hearingReport.hearingSummary" value="${hearingReport.hearingSummary}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_hearingReport');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
