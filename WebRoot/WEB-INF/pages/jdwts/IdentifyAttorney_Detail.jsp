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
					<th width="15%">鉴定要求</th>
					<td width="35%" >${identifyAttorney.inentifyRequire}</td>
					<th width="15%">提交时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${identifyAttorney.submitTime}" /></td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%" >${identifyAttorney.relatedId}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_identifyAttorney');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
