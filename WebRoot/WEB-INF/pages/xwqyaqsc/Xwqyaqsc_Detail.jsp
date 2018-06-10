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
					<th width="15%">年度</th>
					<td width="35%" ><fmt:formatDate type="both" value="${xwqyaqsc.yearTime}" pattern="yyyy"/></td>
					<th width="15%">区域</th>
					<td width="35%" >${xwqyaqsc.areaName}</td>
				</tr>
				<tr>
					<th width="15%">去年累计达标数</th>
					<td width="35%" >${xwqyaqsc.jnljdbs}</td>
					<th width="15%">今年创建目标数</th>
					<td width="35%" >${xwqyaqsc.mncjmbs}</td>
				</tr>
				<tr>
					<th width="15%">今年已申请企业数</th>
					<td width="35%" >${xwqyaqsc.mnysqqys}</td>
					<th width="15%">培训企业数</th>
					<td width="35%" >${xwqyaqsc.pxqys}</td>
				</tr>
				<tr>
					<th width="15%">今年已达标企业数</th>
					<td width="35%" >${xwqyaqsc.mnydbqys}</td>
					<th width="15%">小微企业累计达标数</th>
					<td width="35%" >${xwqyaqsc.xwqyljdbs}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_xwqyaqsc');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
