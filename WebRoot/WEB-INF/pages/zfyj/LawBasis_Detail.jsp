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
					<th width="15%">法律法规名称</th>
					<td width="35%" >${lawBasis.lawName}</td>
					<th width="15%">法律法规条款项</th>
					<td width="35%" >${lawBasis.lawProvision}</td>
				</tr>
				<tr>
					<th width="15%">法律法规内容</th>
					<td width="85%" colspan="3"><textarea readOnly name="lawBasis.lawContent" style="width:96%;height:120px">${lawBasis.lawContent}</textarea></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3"><textarea readOnly name="lawBasis.remark" style="width:96%;height:120px">${lawBasis.remark}</textarea></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_lawBasis');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
