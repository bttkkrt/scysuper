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
					<th width="15%">所在区域</th>
					<td width="35%" >${tecPro.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${tecPro.companyName}</td>
				</tr>
				<tr>
					<th width="15%">生产工艺</th>
					<td colspan="3"><cus:hxmulselectlabel codeName="生产工艺" itemValue="${tecPro.productionProcess}" /></td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
