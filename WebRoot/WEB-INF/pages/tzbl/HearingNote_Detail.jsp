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
					<th width="15%">听证开始时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${hearingNote.startTime}" /></td>
					<th width="15%">听证结束时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${hearingNote.endTime}" /></td>
				</tr>
				<tr>
					<th width="15%">调查人员</th>
					<td width="35%" >${hearingNote.investigator}</td>
					<th width="15%">委托代理人1</th>
					<td width="35%" >${hearingNote.attorney1}</td>
				</tr>
				<tr>
					<th width="15%">委托代理人1性别</th>
					<td width="35%" ><cus:hxlabel codeName="性别" itemValue="${hearingNote.attorney1Sex}" /></td>
					<th width="15%">委托代理人1年龄</th>
					<td width="35%" >${hearingNote.attorney1Age}</td>
				</tr>
				<tr>
					<th width="15%">委托代理人1工作单位</th>
					<td width="35%" >${hearingNote.attorney1Company}</td>
					<th width="15%">委托代理人2</th>
					<td width="35%" >${hearingNote.attorney2}</td>
				</tr>
				<tr>
					<th width="15%">委托代理人2性别</th>
					<td width="35%" ><cus:hxlabel codeName="性别" itemValue="${hearingNote.attorney2Sex}" /></td>
					<th width="15%">委托代理人2年龄</th>
					<td width="35%" >${hearingNote.attorney2Age}</td>
				</tr>
				<tr>
					<th width="15%">委托代理人2工作单位</th>
					<td width="35%" >${hearingNote.attorney2Company}</td>
					<th width="15%">第三人</th>
					<td width="35%" >${hearingNote.thirdPerson}</td>
				</tr>
				<tr>
					<th width="15%">其他参与人员</th>
					<td width="35%" >${hearingNote.otherParticipants}</td>
					<th width="15%">听证记录</th>
					<td width="35%" >${hearingNote.hearingRecord}</td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%" >${hearingNote.relatedId}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_hearingNote');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
