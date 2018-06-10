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
					<th width="15%">职业病危害因素名称</th>
					<td width="35%" >${occDisInd.occupationalDiseaseName}</td>
					<th width="15%">现场浓度</th>
					<td width="35%" >${occDisInd.fieldConcentration}</td>
				</tr>
				<tr>
					<th width="15%">接触人数（可重复）</th>
					<td width="35%" >${occDisInd.contactNumber}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_occDisInd');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
