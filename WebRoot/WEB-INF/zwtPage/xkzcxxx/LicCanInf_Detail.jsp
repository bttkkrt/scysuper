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
					<td width="35%" >${licCanInf.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${licCanInf.companyName}</td>
				</tr>
				<tr>
					<th width="15%">许可证名称</th>
					<td width="35%" >${licCanInf.licenseName}</td>
					<th width="15%">许可证编号</th>
					<td width="35%" >${licCanInf.licenseNumber}</td>
				</tr>
				<tr>
					<th width="15%">注销文号</th>
					<td width="35%" >${licCanInf.cancellationNumber}</td>
					<th width="15%">注销原因</th>
					<td width="35%" >${licCanInf.cancelReason}</td>
				</tr>
				<tr>
					<th width="15%">批准机关名称</th>
					<td width="35%" >${licCanInf.approvalAuthority}</td>
					<th width="15%">批准日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${licCanInf.approvalDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3" ><textarea readOnly name="licCanInf.remark" style="width:96%;height:60px">${licCanInf.remark}</textarea></td>
				</tr>
				<tr>
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
