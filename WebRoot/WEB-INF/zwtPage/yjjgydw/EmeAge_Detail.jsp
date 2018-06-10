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
					<td width="35%" >${emeAge.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${emeAge.companyName}</td>
				</tr>
				<tr>
					<th width="15%">机构名称</th>
					<td width="35%" >${emeAge.agencyName}</td>
					<th width="15%">机构职责</th>
					<td width="35%" >${emeAge.agencyResponsible}</td>
				</tr>
				<tr>
					<th width="15%">成员数量</th>
					<td width="35%" >${emeAge.memberNumber}</td>
					<th width="15%">负责人</th>
					<td width="35%" >${emeAge.personInCharge}</td>
				</tr>
				<tr>
					<th width="15%">负责人联系方式</th>
					<td width="35%" >${emeAge.inChargePhone}</td>
					<th width="15%">负责人邮箱</th>
					<td width="35%" >${emeAge.inChargeEmail}</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea readOnly name="emeAge.remark" style="width:96%;height:60px">${emeAge.remark}</textarea></td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
