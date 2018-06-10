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
					<td width="35%" >${secProFeeExt.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${secProFeeExt.companyName}</td>
				</tr>
				<tr>
					<th width="15%">提取费用</th>
					<td width="35%" >${secProFeeExt.feeExtractFee}</td>
					<th width="15%">提取时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${secProFeeExt.feeExtractTime}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">发票号</th>
					<td width="35%">${secProFeeExt.fpNum}</td>
				</tr>
				<tr>
					<th width="15%">用途</th>
					<td width="35%" colspan="3"><textarea readOnly name="secProFeeExt.feeExtractRemark" style="width:96%;height:60px">${secProFeeExt.feeExtractRemark}</textarea></td>
				</tr>
				 
			</table>
		</div></div></div>
	</form>
</body>
</html>
