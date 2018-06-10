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
	<form name="myform1" method="post" enctype="multipart/form-data" action="noticeEvidenceSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="noticeEvidence.id" value="${noticeEvidence.id}">
		<input type="hidden" name="noticeEvidence.createTime" value="<fmt:formatDate type="both" value="${noticeEvidence.createTime}" />">
		<input type="hidden" name="noticeEvidence.updateTime" value="${noticeEvidence.updateTime}">
		<input type="hidden" name="noticeEvidence.createUserID" value="${noticeEvidence.createUserID}">
		<input type="hidden" name="noticeEvidence.updateUserID" value="${noticeEvidence.updateUserID}">
		<input type="hidden" name="noticeEvidence.deptId" value="${noticeEvidence.deptId}">
		<input type="hidden" name="noticeEvidence.delFlag" value="${noticeEvidence.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">处理地点</th>
					<td width="35%"><input name="noticeEvidence.dealAddress" value="${noticeEvidence.dealAddress}" type="text" maxlength="127"></td>
					<th width="15%">处理时间</th>
					<td width="35%"><input name="noticeEvidence.dealTime" value="<fmt:formatDate type='both' value='${noticeEvidence.dealTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="noticeEvidence.relatedId" value="${noticeEvidence.relatedId}" type="text" maxlength="127"></td>
					<th width="15%">违法行为</th>
					<td width="35%"><input name="noticeEvidence.violation" value="${noticeEvidence.violation}" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_noticeEvidence');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
