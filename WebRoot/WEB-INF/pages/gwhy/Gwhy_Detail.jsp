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
					<td width="35%" ><fmt:formatDate type="both" value="${gwhy.monthTime}" pattern="yyyy-MM"/></td>
					<th width="15%">所在区域</th>
					<td width="35%" >${gwhy.areaName}</td>
				</tr>
				<tr>
					<th width="15%">与共保体专门协商</th>
					<td width="35%" >${gwhy.ygbtzmxs}</td>
					<th width="15%">专题动员会</th>
					<td width="35%" >${gwhy.ztdyh}</td>
				</tr>
				<tr>
					<th width="15%">已报试点企业</th>
					<td width="35%" >${gwhy.ybsdqy}</td>
					<th width="15%">试点企业安全现状评估数</th>
					<td width="35%" >${gwhy.sdqyaqpgs}</td>
				</tr>
				<tr>
					<th width="15%">试点企业已投保</th>
					<td width="35%" >${gwhy.sdqyytb}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_gwhy');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
