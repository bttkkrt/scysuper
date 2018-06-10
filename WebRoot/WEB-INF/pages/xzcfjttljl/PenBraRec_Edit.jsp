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
	<form name="myform1" method="post" enctype="multipart/form-data" action="penBraRecSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="penBraRec.id" value="${penBraRec.id}">
		<input type="hidden" name="penBraRec.createTime" value="<fmt:formatDate type="both" value="${penBraRec.createTime}" />">
		<input type="hidden" name="penBraRec.updateTime" value="${penBraRec.updateTime}">
		<input type="hidden" name="penBraRec.createUserID" value="${penBraRec.createUserID}">
		<input type="hidden" name="penBraRec.updateUserID" value="${penBraRec.updateUserID}">
		<input type="hidden" name="penBraRec.deptId" value="${penBraRec.deptId}">
		<input type="hidden" name="penBraRec.delFlag" value="${penBraRec.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">讨论开始时间</th>
					<td width="35%"><input name="penBraRec.startTime" value="<fmt:formatDate type='both' value='${penBraRec.startTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
					<th width="15%">讨论结束时间</th>
					<td width="35%"><input name="penBraRec.endTime" value="<fmt:formatDate type='both' value='${penBraRec.endTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
				</tr>
				<tr>
					<th width="15%">讨论地点</th>
					<td width="35%"><input name="penBraRec.discussionAddress" value="${penBraRec.discussionAddress}" type="text" maxlength="127"></td>
					<th width="15%">结论性意见</th>
					<td width="35%"><input name="penBraRec.conclusionComment" value="${penBraRec.conclusionComment}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">汇报人</th>
					<td width="35%"><input name="penBraRec.reportPerson" value="${penBraRec.reportPerson}" type="text" maxlength="127"></td>
					<th width="15%">记录人</th>
					<td width="35%"><input name="penBraRec.recordPerson" value="${penBraRec.recordPerson}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">出席人员姓名及职务</th>
					<td width="35%"><input name="penBraRec.attendNamePosition" value="${penBraRec.attendNamePosition}" type="text" maxlength="127"></td>
					<th width="15%">讨论内容</th>
					<td width="35%"><input name="penBraRec.discussionContent" value="${penBraRec.discussionContent}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">讨论记录</th>
					<td width="35%"><input name="penBraRec.discussionRecord" value="${penBraRec.discussionRecord}" type="text" maxlength="127"></td>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="penBraRec.relatedId" value="${penBraRec.relatedId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">主持人</th>
					<td width="35%"><input name="penBraRec.chairperson" value="${penBraRec.chairperson}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_penBraRec');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
