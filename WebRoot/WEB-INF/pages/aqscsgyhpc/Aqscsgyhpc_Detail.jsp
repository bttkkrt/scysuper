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
					<th width="15%">年度</th>
					<td width="35%" ><fmt:formatDate type="both" value="${aqscsgyhpc.yearTime}" pattern="yyyy"/></td>
					<th width="15%">区域</th>
					<td width="35%" >${aqscsgyhpc.areaName}</td>
				</tr>
				<tr>
					<th width="15%">已覆盖企业数量</th>
					<td width="35%" >${aqscsgyhpc.yfgqysl}</td>
					<th width="15%">已覆盖规模以上企业数量</th>
					<td width="35%" >${aqscsgyhpc.yfggmysqysl}</td>
				</tr>
				<tr>
					<th width="15%">年度累计排查隐患数量</th>
					<td width="35%" >${aqscsgyhpc.ndljpcthsl}</td>
					<th width="15%">年度累计整改隐患数量</th>
					<td width="35%" >${aqscsgyhpc.ndljzgyhsl}</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea readOnly name="aqscsgyhpc.remark" style="width:96%;height:60px">${aqscsgyhpc.remark}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_aqscsgyhpc');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
