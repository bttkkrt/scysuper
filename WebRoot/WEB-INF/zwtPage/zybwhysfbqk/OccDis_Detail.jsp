<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">关联基本因素id</th>
					<td width="35%" >${occDis.proId}</td>
					<th width="15%">作业场所名称</th>
					<td width="35%" >${occDis.workPlace}</td>
				</tr>
				<tr>
					<th width="15%">接触人数</th>
					<td width="35%" >${occDis.contactNum}</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
