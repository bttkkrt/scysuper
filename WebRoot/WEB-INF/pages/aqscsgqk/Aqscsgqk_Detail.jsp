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
					<td width="35%" ><fmt:formatDate type="both" value="${aqscsgqk.monthTime}" pattern="yyyy-MM"/></td>
					<th width="15%">区域</th>
					<td width="35%" >${aqscsgqk.areaName}</td>
					
				</tr>
				<tr>
					<th width="15%">当月事故起数</th>
					<td width="35%" >${aqscsgqk.dysgqs}</td>
					<th width="15%">当月事故生产性</th>
					<td width="35%" >${aqscsgqk.dysgscx}</td>
				</tr>
				<tr>
					<th width="15%">当月死亡事故起数/生产性</th>
					<td width="35%" >${aqscsgqk.dyswsgqs}<span>/</span>${aqscsgqk.dyswsgscx}</td>
				
					<th width="15%">当月死亡事故人数/生产性</th>
					<td width="35%" >${aqscsgqk.dyswsgrs}<span>/</span>${aqscsgqk.dyswsgscxs}</td>
				</tr>
				
				<tr>
					<th width="15%">当月重伤事故起数/生产性</th>
					<td width=5%" >${aqscsgqk.dyzssgqs}<span>/</span>${aqscsgqk.dyzssgscx}</td>
		
					<th width="15%">当月重伤事故人数/生产性</th>
					<td width="35%" >${aqscsgqk.dyzssgrs}<span>/</span>${aqscsgqk.dyzssgscxs}</td>
				
				</tr>
				<tr>
					<th width="15%">累计事故情况起数</th>
					<td width="35%" >${aqscsgqk.ljsgqkqs}</td>
					<th width="15%">累计事故情况生产性</th>
					<td width="35%" >${aqscsgqk.ljsgqkscx}</td>
				</tr>
				
				<tr>
					<th width="15%">累计亡人事故起数</th>
					<td width="35%" >${aqscsgqk.ljwrsgqs}</td>
					<th width="15%">累计亡人事故生产性</th>
					<td width="35%" >${aqscsgqk.ljwrsgscx}</td>
				</tr>
				
				<tr>
					<th width="15%">累计重伤事故起数</th>
					<td width="35%" >${aqscsgqk.ljzssgqs}</td>
					<th width="15%">累计重伤事故生产性</th>
					<td width="35%" >${aqscsgqk.ljzssgscx}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_aqscsgqk');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
