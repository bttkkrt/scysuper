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
					<td width="35%" ><fmt:formatDate type="both" value="${whpqybzh.monthTime}" pattern="yyyy-MM" /></td>
					<th width="15%">区域</th>
					<td width="35%" >${whpqybzh.areaName}</td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>计划核查数</strong></td>
				</tr>
				<tr>
					<th width="15%">计划核查数合计</th>
					<td width="35%" >${whpqybzh.jhhcshj}</td>
					<th width="15%">计划核查数生产</th>
					<td width="35%" >${whpqybzh.jhhcssc}</td>
				</tr>
				<tr>
					<th width="15%">计划核查数使用</th>
					<td width="35%" >${whpqybzh.jhhcssy}</td>
					<th width="15%">计划核查数仓储</th>
					<td width="35%" >${whpqybzh.jhhcscc}</td>
				</tr>
				<tr>
				    <th width="15%">计划核查数经营带储存</th>
					<td width="35%" >${whpqybzh.jhhcsjydsc}</td>
					<th width="15%">计划核查数加油站</th>
					<td width="35%" >${whpqybzh.jhhxsjyz}</td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>已完成核查数</strong></td>
				</tr>
				<tr>
					<th width="15%">已完成核查数合计</th>
					<td width="35%" >${whpqybzh.ywchcshj}</td>
					
					<th width="15%">已完成核查数生产</th>
					<td width="35%" >${whpqybzh.ywchcssc}</td>
				</tr>
				<tr>
					<th width="15%">已完成核查数使用</th>
					<td width="35%" >${whpqybzh.ywchcssy}</td>
					<th width="15%">已完成核查数仓储</th>
					<td width="35%" >${whpqybzh.ywchcscc}</td>
				</tr>
				<tr>
					<th width="15%">已完成核查数经营带储存</th>
					<td width="35%" >${whpqybzh.ywchcsjysc}</td>
					<th width="15%">已完成核查数加油站</th>
					<td width="35%" >${whpqybzh.ywchcsjyz}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_whpqybzh');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
