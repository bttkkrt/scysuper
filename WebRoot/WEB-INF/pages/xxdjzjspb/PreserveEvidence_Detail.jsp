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
					<th width="15%">提请理由及依据</th>
					<td width="35%" >${preserveEvidence.reasonBasic}</td>
					<th width="15%">保存方式</th>
					<td width="35%" >${preserveEvidence.preserveMethod}</td>
				</tr>
				<tr>
					<th width="15%">机关负责人意见</th>
					<td width="35%" >${preserveEvidence.officeComment}</td>
					<th width="15%">承办人意见</th>
					<td width="35%" >${preserveEvidence.undertakerComment}</td>
				</tr>
				<tr>
					<th width="15%">部门负责人</th>
					<td width="35%" >${preserveEvidence.departPerson}</td>
					<th width="15%">部门负责人意见</th>
					<td width="35%" >${preserveEvidence.departComment}</td>
				</tr>
				<tr>
					<th width="15%">机关负责人</th>
					<td width="35%" >${preserveEvidence.officePerson}</td>
					<th width="15%">关联文书编号</th>
					<td width="35%" >${preserveEvidence.relatedId}</td>
				</tr>
				<tr>
					<th width="15%">承办人</th>
					<td width="35%" >${preserveEvidence.undertaker}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_preserveEvidence');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
