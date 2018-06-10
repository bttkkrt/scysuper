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
					<th width="15%">检查项名称</th>
					<td width="35%" >${cheIte.checkName}</td>
					<th width="15%">检查项类型</th>
					<td width="35%" ><cus:hxlabel codeName="监督检查项类型" itemValue="${cheIte.checkType}" /></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" >${cheIte.remark}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_cheIte');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
