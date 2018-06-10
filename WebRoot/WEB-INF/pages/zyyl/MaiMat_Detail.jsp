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
					<td width="35%" >${maiMat.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${maiMat.companyName}</td>
				</tr>
				<tr>
					<th width="15%">物料</th>
					<td width="35%" >${maiMat.material}</td>
					<th width="15%">危险性分析</th>
					<td width="35%" >${maiMat.riskAnalysis}</td>
				</tr>
				<tr>	
					<th width="15%">存放方式</th>
					<td width="35%" >${maiMat.storageMode}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_maiMat');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
