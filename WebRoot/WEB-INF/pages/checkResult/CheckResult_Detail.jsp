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
					<th width="15%">检查表id</th>
					<td width="35%" >${checkResult.checkTableId}</td>
					<th width="15%">内容序号</th>
					<td width="35%" >${checkResult.contentOrder}</td>
				</tr>
				<tr>
					<th width="15%">检查内容</th>
					<td width="35%" >${checkResult.checkContent}</td>
					<th width="15%">检查情况</th>
					<td width="35%" >${checkResult.checkResult}</td>
				</tr>
				<tr>
					<th width="15%">是否合格</th>
					<td width="35%" >${checkResult.ifOk}</td>
					<th width="15%">备注</th>
					<td width="35%" >${checkResult.remark}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_checkResult');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
