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
					<th width="15%">月份</th>
					<td width="35%" ><fmt:formatDate type="both" value="${aqscpxqk.monthTime}" pattern="yyyy-MM"/></td>
					<th width="15%">区域</th>
					<td width="35%" >${aqscpxqk.areaName}</td>
				</tr>
				<tr>
					<th width="15%">主要负责人</th>
					<td width="35%" >${aqscpxqk.zyfzr}</td>
					<th width="15%">安全管理人员</th>
					<td width="35%" >${aqscpxqk.aqglry}</td>
				</tr>
				<tr>
					<th width="15%">职业卫生</th>
					<td width="35%" >${aqscpxqk.zyws}</td>
					<th width="15%">班组长</th>
					<td width="35%" >${aqscpxqk.bzz}</td>
				</tr>
				<tr>
					<th width="15%">特种作业人员</th>
					<td width="35%" >${aqscpxqk.tzzyry}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_aqscpxqk');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
