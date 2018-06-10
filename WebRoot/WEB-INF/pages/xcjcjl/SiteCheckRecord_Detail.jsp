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
					<th width="15%">现场负责人</th>
					<td width="35%" >${siteCheckRecord.chargePerson}</td>
					<th width="15%">检查人员</th>
					<td width="35%" >${siteCheckRecord.checkPerson}</td>
				</tr>
				<tr>
					<th width="15%">检查开始时间</th>
					<td width="35%" >${siteCheckRecord.startTime}</td>
					<th width="15%">检查结束时间</th>
					<td width="35%" >${siteCheckRecord.endTime}</td>
				</tr>
				<tr>
					<th width="15%">检查情况</th>
					<td width="35%" >${siteCheckRecord.checkCondition}</td>
					<th width="15%">关联文书编号</th>
					<td width="35%" >${siteCheckRecord.relatedId}</td>
				</tr>
				<tr>
					<th width="15%">检查场所</th>
					<td width="35%" >${siteCheckRecord.checkAddress}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_siteCheckRecord');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
