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
					<th width="15%">处理结果</th>
					<td width="35%" >${closeApproval.approvalResult}</td>
					<th width="15%">执法情况</th>
					<td width="35%" >${closeApproval.executeCondition}</td>
				</tr>
				<tr>
					<th width="15%">承办人</th>
					<td width="35%" >${closeApproval.undertaker}</td>
					<th width="15%">审核人</th>
					<td width="35%" >${closeApproval.checkPerson}</td>
				</tr>
				<tr>
					<th width="15%">审核意见</th>
					<td width="35%" >${closeApproval.checkComment}</td>
					<th width="15%">审批人</th>
					<td width="35%" >${closeApproval.approver}</td>
				</tr>
				<tr>
					<th width="15%">审批意见</th>
					<td width="35%" >${closeApproval.approverComment}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_closeApproval');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
