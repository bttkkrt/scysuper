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
					<th width="15%">提请理由及依据</th>
					<td width="35%" >${inventoryCheck.reasonBasic}</td>
					<th width="15%">承办人意见</th>
					<td width="35%" >${inventoryCheck.undertakerComment}</td>
				</tr>
				<tr>
					<th width="15%">机关负责人意见</th>
					<td width="35%" >${inventoryCheck.officeComment}</td>
					<th width="15%">部门负责人</th>
					<td width="35%" >${inventoryCheck.departPerson}</td>
				</tr>
				<tr>
					<th width="15%">部门负责人意见</th>
					<td width="35%" >${inventoryCheck.departComment}</td>
					<th width="15%">机关负责人</th>
					<td width="35%" >${inventoryCheck.officePerson}</td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%" >${inventoryCheck.relatedId}</td>
					<th width="15%">承办人</th>
					<td width="35%" >${inventoryCheck.undertaker}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_inventoryCheck');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
