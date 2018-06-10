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
					<td width="35%" ><fmt:formatDate type="both" value="${qyaqscyt.monthTime}" pattern="yyyy-MM"/></td>
					<th width="15%">约谈分类</th>
					<td width="35%" >${qyaqscyt.areaName}</td>
					
				</tr>
				<tr>
					<th width="15%">本年度应约谈数</th>
					<td width="35%" >${qyaqscyt.bndyyt}</td>
					<th width="15%">本月约谈企业数</th>
					<td width="35%" >${qyaqscyt.byytqys}</td>
				</tr>
				<tr>
					<th width="15%">本年度累计约谈企业数</th>
					<td width="35%" >${qyaqscyt.bnyljytqys}</td>
					<th width="15%">备注</th>
					<td width="35%" >${qyaqscyt.remark}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_qyaqscyt');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
