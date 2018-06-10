<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">讨论开始时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${penBraRec.startTime}" /></td>
					<th width="15%">讨论结束时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${penBraRec.endTime}" /></td>
				</tr>
				<tr>
					<th width="15%">讨论地点</th>
					<td width="35%" >${penBraRec.discussionAddress}</td>
					<th width="15%">结论性意见</th>
					<td width="35%" >${penBraRec.conclusionComment}</td>
				</tr>
				<tr>
					<th width="15%">汇报人</th>
					<td width="35%" >${penBraRec.reportPerson}</td>
					<th width="15%">记录人</th>
					<td width="35%" >${penBraRec.recordPerson}</td>
				</tr>
				<tr>
					<th width="15%">出席人员姓名及职务</th>
					<td width="35%" >${penBraRec.attendNamePosition}</td>
					<th width="15%">讨论内容</th>
					<td width="35%" >${penBraRec.discussionContent}</td>
				</tr>
				<tr>
					<th width="15%">讨论记录</th>
					<td width="35%" >${penBraRec.discussionRecord}</td>
					<th width="15%">关联文书编号</th>
					<td width="35%" >${penBraRec.relatedId}</td>
				</tr>
				<tr>
					<th width="15%">主持人</th>
					<td width="35%" >${penBraRec.chairperson}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_penBraRec');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
