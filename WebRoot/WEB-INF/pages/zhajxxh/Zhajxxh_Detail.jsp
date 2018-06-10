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
					<td width="35%" ><fmt:formatDate type="both" value="${zhajxxh.monthTime}" pattern="yyyy-MM"/></td>
					<th width="15%">区域</th>
					<td width="35%" >${zhajxxh.areaName}</td>
				</tr>
				<tr>
					<th width="15%">监管企业数</th>
					<td width="35%" >${zhajxxh.jgqys}</td>
					<th width="15%">推广使用企业目标数</th>
					<td width="35%" >${zhajxxh.tgmbs}</td>
				</tr>
				<tr>
					<th width="15%">企业注册数</th>
					<td width="35%" >${zhajxxh.qyzcs}</td>
					<th width="15%">完成基础信息填报企业数</th>
					<td width="35%" >${zhajxxh.wctbqys}</td>
				</tr>
				<tr>
					<th width="15%">正常运行隐患排查企业数</th>
					<td width="35%" >${zhajxxh.yhpcqys}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_zhajxxh');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
