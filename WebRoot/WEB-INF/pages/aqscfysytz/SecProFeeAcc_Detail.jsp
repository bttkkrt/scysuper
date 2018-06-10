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
					<th width="15%" style="text-align:center">提取费用</th>
					<th width="15%" style="text-align:center">提取时间</th>
					<th width="20%" style="text-align:center">发票号</th>
					<th width="50%" style="text-align:center">用途</th>
				</tr>
				<c:forEach var="secProFeeExt" items="${list}">
					<tr>
						<td width="15%" style="text-align:center">${secProFeeExt.feeExtractFee}</td>
						<td width="15%" style="text-align:center"><fmt:formatDate type="date" value="${secProFeeExt.feeExtractTime}" pattern="yyyy-MM-dd"/></td>
						<td width="20%" style="text-align:center">${secProFeeExt.fpNum}</td>
						<td width="50%" style="text-align:center">${secProFeeExt.feeExtractRemark}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_secProFeeAcc');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
