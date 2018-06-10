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
					<th width="15%">考评类目</th>
					<td width="35%" ><cus:hxlabel codeName="考评类目" itemValue="${scoAssLis.evaluationCategory}" /></td>
					<th width="15%">实际得分</th>
					<td width="35%" >${scoAssLis.actualScore}</td>
				</tr>
				<tr>
					<th width="15%">标准分值</th>
					<td width="35%" >${scoAssLis.score}</td>
					<th width="15%">评分方式</th>
					<td width="35%" ><cus:hxlabel codeName="评分方式" itemValue="${scoAssLis.grading}" /></td>
				</tr>
				<tr>
					<th width="15%">扣分</th>
					<td width="35%" >${scoAssLis.mark}</td>
					<th width="15%">标准条款</th>
					<td width="35%" >${scoAssLis.provision}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_scoAssLis');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
