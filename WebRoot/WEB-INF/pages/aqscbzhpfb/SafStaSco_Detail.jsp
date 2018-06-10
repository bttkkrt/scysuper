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
					<th width="15%">所在区域</th>
					<td width="35%" >${safStaSco.areaName}</td>
					<th width="15%">评分时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${safStaSco.patingDate}" /></td>
				</tr>
				<tr>
					<th width="15%">企业名称</th>
					<td width="35%" >${safStaSco.companyName}</td>
					<th width="15%">总分</th>
					<td width="35%" >${safStaSco.totalScore}</td>
				</tr>
				<tr>
					<th width="15%">评分人</th>
					<td width="35%" >${safStaSco.ratingUserId}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_safStaSco');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
