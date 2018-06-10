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
					<td width="35%" ><fmt:formatDate type="both" value="${aqsczjzd.yearTime}" pattern="yyyy"/></td>
					<th width="15%">区域</th>
					<td width="35%" >${aqsczjzd.areaName}</td>
				</tr>
				<tr>
					<th width="15%">目标数</th>
					<td width="35%" >${aqsczjzd.mbs}</td>
			
					<th width="15%">上报数</th>
					<td width="35%" >${aqsczjzd.sbs}</td>
				</tr>
				<tr>
					<th width="15%">验收数</th>
					<td width="35%" >${aqsczjzd.yss}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_aqsczjzd');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
