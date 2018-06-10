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
					<th width="15%">文件名称</th>
					<td width="35%" >${splx.fileName}</td>
					<th width="15%">开始时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${splx.beginTime}" /></td>
				</tr>
				<tr>
					<th width="15%">结束时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${splx.endTime}" /></td>
					<th width="15%">文件类型</th>
					<td width="35%" >${splx.fileType}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_splx');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
