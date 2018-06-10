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
					<th width="15%">网格名称</th>
					<td width="35%" >${comGriMan.gridName}</td>
					<th width="15%">MAP_KEY</th>
					<td width="35%" >${comGriMan.mapKey}</td>
				</tr>
				<tr>
					<th width="15%">网格管理中队</th>
					<td width="35%" >${comGriMan.gridManageDeptName}</td>
				</tr>
				<tr>
					<th width="15%">管辖范围</th>
					<td width="85%" colspan="3" ><textarea readOnly name="comGriMan.manageScope" style="width:96%;height:50px">${comGriMan.manageScope}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_comGriMan');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
