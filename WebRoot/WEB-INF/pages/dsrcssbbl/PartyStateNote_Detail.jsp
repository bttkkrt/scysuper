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
					<th width="15%">陈述开始时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${partyStateNote.startTime}" /></td>
					<th width="15%">陈述结束时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${partyStateNote.endTime}" /></td>
				</tr>
				<tr>
					<th width="15%">陈述地点</th>
					<td width="35%" >${partyStateNote.stateAddress}</td>
					<th width="15%">陈述申辩人</th>
					<td width="35%" >${partyStateNote.stateDefense}</td>
				</tr>
				<tr>
					<th width="15%">陈述申辩记录</th>
					<td width="35%" >${partyStateNote.stateRecord}</td>
					<th width="15%">职务</th>
					<td width="35%" >${partyStateNote.position}</td>
				</tr>
				<tr>
					<th width="15%">电话</th>
					<td width="35%" >${partyStateNote.tele}</td>
					<th width="15%">联系地址</th>
					<td width="35%" >${partyStateNote.address}</td>
				</tr>
				<tr>
					<th width="15%">邮编</th>
					<td width="35%" >${partyStateNote.zipCode}</td>
					<th width="15%">承办人</th>
					<td width="35%" >${partyStateNote.undertaker}</td>
				</tr>
				<tr>
					<th width="15%">记录人</th>
					<td width="35%" >${partyStateNote.recorder}</td>
					<th width="15%">关联文书编号</th>
					<td width="35%" >${partyStateNote.relatedId}</td>
				</tr>
				<tr>
					<th width="15%">性别</th>
					<td width="35%" ><cus:hxlabel codeName="性别" itemValue="${partyStateNote.sex}" /></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_partyStateNote');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
