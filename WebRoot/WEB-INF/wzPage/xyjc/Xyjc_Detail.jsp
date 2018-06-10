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
					<th width="15%">姓名</th>
					<td width="35%" >${xyjc.name}</td>
					<th width="15%">性别</th>
					<td width="35%" ><cus:hxlabel codeName="性别" itemValue="${xyjc.sex}" /></td>
				</tr>
				<tr>
					<th width="15%">联系地址</th>
					<td width="35%" >${xyjc.address}</td>
					<th width="15%">联系电话</th>
					<td width="35%" >${xyjc.mobile}</td>
				</tr>
				<tr>
					<th width="15%">电子邮箱</th>
					<td width="35%" >${xyjc.mail}</td>
					<th width="15%">建言标题</th>
					<td width="35%" >${xyjc.infoTitle}</td>
				</tr>
				<tr>
					<th width="15%">建言内容</th>
					<td width="85%" colspan="3"><textarea readOnly name="xyjc.infoContent" style="width:100%;height:90px">${xyjc.infoContent}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_xyjc');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
