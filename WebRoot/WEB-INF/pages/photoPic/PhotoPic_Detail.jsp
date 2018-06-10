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
					<th width="15%">附件关联id</th>
					<td width="35%" >${photoPic.linkId}</td>
					<th width="15%">内网url</th>
					<td width="35%" >${photoPic.nwUrl}</td>
				</tr>
				<tr>
					<th width="15%">模块类型</th>
					<td width="35%" >${photoPic.mkType}</td>
					<th width="15%">附件真实名称</th>
					<td width="35%" >${photoPic.fileName}</td>
				</tr>
				<tr>
					<th width="15%">服务器url</th>
					<td width="35%" >${photoPic.httpUrl}</td>
					<th width="15%">附件名称</th>
					<td width="35%" >${photoPic.picName}</td>
				</tr>
				<tr>
					<th width="15%">附件类型</th>
					<td width="35%" >${photoPic.picType}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_photoPic');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
