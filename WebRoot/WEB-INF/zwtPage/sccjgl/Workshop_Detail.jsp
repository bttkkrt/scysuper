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
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${workshop.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${workshop.companyName}</td>
					
				</tr>
				<tr>
					<th width="15%">车间名称</th>
					<td width="35%" >${workshop.workshopName}</td>
					<th width="15%">员工数</th>
					<td width="35%" >${workshop.workshopWorkers}</td>
				</tr>
				<tr>
					<th width="15%">车间负责人</th>
					<td width="35%" >${workshop.workshopCharge}</td>
					<th width="15%">操作方式</th>
					<td width="35%" ><cus:hxlabel codeName="车间操作方式" itemValue="${workshop.workshopOperation}" /></td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
