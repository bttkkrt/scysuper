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
					<th width="15%">关联文书编号</th>
					<td width="35%" >${inquiryNotice.relatedId}</td>
					<th width="15%">询问时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${inquiryNotice.inquiryTime}" /></td>
				</tr>
				<tr>
					<th width="15%">询问地点</th>
					<td width="35%" >${inquiryNotice.inquiryAddress}</td>
					<th width="15%">证件材料</th>
					<td width="35%" >${inquiryNotice.docMaterial}</td>
				</tr>
				<tr>
					<th width="15%">联系人</th>
					<td width="35%" >${inquiryNotice.contact}</td>
					<th width="15%">联系电话</th>
					<td width="35%" >${inquiryNotice.tele}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_inquiryNotice');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
