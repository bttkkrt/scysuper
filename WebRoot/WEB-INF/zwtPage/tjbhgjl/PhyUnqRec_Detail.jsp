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
					<td width="35%" >${phyUnqRec.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${phyUnqRec.companyName}</td>
				</tr>
				<tr>
					<th width="15%">身份证</th>
					<td width="35%" >${phyUnqRec.identification}</td>
					<th width="15%">姓名</th>
					<td width="35%" >${phyUnqRec.jshxName}</td>
				</tr>
				<tr>
					<th width="15%">体检类型</th>
					<td width="35%" ><cus:hxlabel codeName="体检类型" itemValue="${phyUnqRec.physicalExaminatioType}" /></td>
					<th width="15%">体检日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${phyUnqRec.medicalExaminationDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">体检机构</th>
					<td width="35%" >${phyUnqRec.medicalInstitution}</td>
					<th width="15%">体检结果</th>
					<td width="35%" >${phyUnqRec.physicalExaminatioResults}</td>
				</tr>
				<tr>
					<th width="15%">职业禁忌岗位</th>
					<td width="35%" >${phyUnqRec.occupationalTabooPost}</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
