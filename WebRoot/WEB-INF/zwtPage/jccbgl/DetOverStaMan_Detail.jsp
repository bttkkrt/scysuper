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
					<td width="35%" >${detOverStaMan.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${detOverStaMan.companyName}</td>
				</tr>
				<tr>
					<th width="15%">样品编码</th>
					<td width="35%" >${detOverStaMan.sampleEncoding}</td>
					<th width="15%">检测类别</th>
					<td width="35%" ><cus:hxlabel codeName="检测类别" itemValue="${detOverStaMan.detectionCategory}" /></td>
				</tr>
				<tr>
					<th width="15%">检测岗位</th>
					<td width="35%" >${detOverStaMan.testPosition}</td>
					<th width="15%">检测项目</th>
					<td width="35%" >${detOverStaMan.testItems}</td>
				</tr>
				<tr>
					<th width="15%">检测结果</th>
					<td width="35%" >${detOverStaMan.testResult}</td>
					<th width="15%">标准值</th>
					<td width="35%" >${detOverStaMan.standardValue}</td>
				</tr>
				<tr>
					<th width="15%">检测机构</th>
					<td width="35%" >${detOverStaMan.detectionMechanism}</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea readOnly name="detOverStaMan.remark" style="width:96%;height:60px">${detOverStaMan.remark}</textarea></td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
