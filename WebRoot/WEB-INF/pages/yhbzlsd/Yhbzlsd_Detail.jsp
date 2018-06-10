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
					<td width="35%" ><fmt:formatDate type="both" value="${yhbzlsd.yearTime}" pattern="yyyy"/></td>
					<th width="15%">区域</th>
					<td width="35%" >${yhbzlsd.areaName}</td>
				</tr>
				<tr>
					<th width="15%">现有零售点</th>
					<td width="35%" >${yhbzlsd.xylsd}</td>
					<th width="15%">当年换证零售点计划数</th>
					<td width="35%" >${yhbzlsd.dnhzlsdjhs}</td>
				</tr>
				<tr>
					<th width="15%">当年换证零售点完成数</th>
					<td width="35%" >${yhbzlsd.dnhzlsdwcs}</td>
					<th width="15%">当年参加培训零售点计划数</th>
					<td width="35%" >${yhbzlsd.dncjpxjhs}</td>
				</tr>
				<tr>
					<th width="15%">当年参加培训零售点完成数</th>
					<td width="35%" >${yhbzlsd.dncjpxwcs}</td>
					<th width="15%">持证上岗人数</th>
					<td width="35%" >${yhbzlsd.czsgrs}</td>
				</tr>
				<tr>
					<th width="15%">当年累计督察</th>
					<td width="35%" >${yhbzlsd.dnljdc}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_yhbzlsd');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
