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
					<th width="15%">关联文书编号</th>
					<td width="35%" >${posFinRat.relatedId}</td>
					<th width="15%">罚款大写</th>
					<td width="35%" >${posFinRat.fineUppercase}</td>
				</tr>
				<tr>
					<th width="15%">延期方式</th>
					<td width="35%" ><cus:hxlabel codeName="延期方式" itemValue="${posFinRat.postponeMethod}" /></td>
					<th width="15%">延期年</th>
					<td width="35%" >${posFinRat.postponeYear}</td>
				</tr>
				<tr>
					<th width="15%">延期月</th>
					<td width="35%" >${posFinRat.postponeMonth}</td>
					<th width="15%">延期日</th>
					<td width="35%" >${posFinRat.postponeDate}</td>
				</tr>
				<tr>
					<th width="15%">分期长度</th>
					<td width="35%" >${posFinRat.stageLength}</td>
					<th width="15%">还款期限大写</th>
					<td width="35%" >${posFinRat.repayPeriod}</td>
				</tr>
				<tr>
					<th width="15%">尚未缴纳罚款大写</th>
					<td width="35%" >${posFinRat.noPay}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_posFinRat');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
