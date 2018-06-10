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
					<th width="15%">听证会时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${hearingNotice.hearingTime}" /></td>
					<th width="15%">书记员</th>
					<td width="35%" >${hearingNotice.clerk}</td>
				</tr>
				<tr>
					<th width="15%">是否公开</th>
					<td width="35%" ><cus:hxlabel codeName="是或否" itemValue="${hearingNotice.publicCondition}" /></td>
					<th width="15%">听证主持人</th>
					<td width="35%" >${hearingNotice.hearingChairperson}</td>
				</tr>
				<tr>
					<th width="15%">听证员</th>
					<td width="35%" >${hearingNotice.hearingOfficer}</td>
					<th width="15%">关联文书编号</th>
					<td width="35%" >${hearingNotice.relatedId}</td>
				</tr>
				<tr>
					<th width="15%">听证地点</th>
					<td width="35%" >${hearingNotice.hearingAddress}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_hearingNotice');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
