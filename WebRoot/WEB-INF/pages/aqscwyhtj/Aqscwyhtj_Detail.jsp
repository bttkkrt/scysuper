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
					<th width="15%">年份</th>
					<td width="35%" ><fmt:formatDate type="both" value="${aqscwyhtj.yearTime}" pattern="yyyy"/></td>
					<th width="15%">召开安委会全体成员会议年度计划次数</th>
					<td width="35%" >${aqscwyhtj.zkndjhs}</td>
				</tr>
				<tr>
					<th width="15%">主要领导带队安全检查年度计划次数</th>
					<td width="35%" >${aqscwyhtj.zyndjhcs}</td>
					<th width="15%">分管领导带队安全检查年度计划次数</th>
					<td width="35%" >${aqscwyhtj.fgndjhcs}</td>
				</tr>
				<tr>
					<th width="15%">其他领导带队安全检查年度计划次数</th>
					<td width="35%" >${aqscwyhtj.qtndjhcs}</td>
			
					<th width="15%">工委会研究安全生产年度计划次数</th>
					<td width="35%" >${aqscwyhtj.gwndjhcs}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_aqscwyhtj');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
