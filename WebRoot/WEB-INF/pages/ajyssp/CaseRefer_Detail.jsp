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
					<th width="15%">受移送机关</th>
					<td width="35%" >${caseRefer.transferAuthority}</td>
					<th width="15%">移送理由</th>
					<td width="35%" >${caseRefer.feedingGrounds}</td>
				</tr>
				<tr>
					<th width="15%">承办人意见</th>
					<td width="35%" >${caseRefer.undertakerComment}</td>
					<th width="15%">承办人</th>
					<td width="35%" >${caseRefer.undertaker}</td>
				</tr>
				<tr>
					<th width="15%">部门负责人</th>
					<td width="35%" >${caseRefer.departPerson}</td>
					<th width="15%">部门负责人审核意见</th>
					<td width="35%" >${caseRefer.departComment}</td>
				</tr>
				<tr>
					<th width="15%">机关负责人</th>
					<td width="35%" >${caseRefer.officePerson}</td>
					<th width="15%">机关负责人审核意见</th>
					<td width="35%" >${caseRefer.officeComment}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_caseRefer');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
