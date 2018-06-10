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
					<th width="15%">审核人id</th>
					<td width="35%" >${checkRecord.checkUserid}</td>
					<th width="15%">审核备注</th>
					<td width="35%" >${checkRecord.checkRemark}</td>
				</tr>
				<tr>
					<th width="15%">审核结果</th>
					<td width="35%" >${checkRecord.checkResult}</td>
					<th width="15%">审核时间</th>
					<td width="35%" >${checkRecord.checkTime}</td>
				</tr>
				<tr>
					<th width="15%">关联id</th>
					<td width="35%" >${checkRecord.infoId}</td>
					<th width="15%">审核人名称</th>
					<td width="35%" >${checkRecord.checkUsername}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_checkRecord');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
