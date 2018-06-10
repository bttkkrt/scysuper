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
					<th width="15%">承办人意见</th>
					<td width="35%" >${caseProcessApproval.undertakerComment}</td>
					<th width="15%">审批意见</th>
					<td width="35%" >${caseProcessApproval.approverComment}</td>
				</tr>
				<tr>
					<th width="15%">审核人</th>
					<td width="35%" >${caseProcessApproval.checkPerson}</td>
					<th width="15%">审核意见</th>
					<td width="35%" >${caseProcessApproval.checkComment}</td>
				</tr>
				<tr>
					<th width="15%">审批人</th>
					<td width="35%" >${caseProcessApproval.approverPerson}</td>
					<th width="15%">关联文书编号</th>
					<td width="35%" >${caseProcessApproval.relatedId}</td>
				</tr>
				<tr>
					<th width="15%">承办人</th>
					<td width="35%" >${caseProcessApproval.undertaker}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_caseProcessApproval');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
