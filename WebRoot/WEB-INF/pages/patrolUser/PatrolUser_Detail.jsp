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
			<div class="cell" style="width: ">
			<table width="100%">
				<tr>
					<th width="15%">姓名</th>
					<td width="35%" >${patrolUser.userName}</td>
					<th width="15%">手机</th>
					<td width="35%" >${patrolUser.mobile}</td>
				</tr>
				<tr>
					<th width="15%">用户名</th>
					<td width="35%" >${patrolUser.loginId}</td>
					<th width="15%">密码</th>
					<td width="35%" >${patrolUser.passWord}</td>
				</tr>
				<tr>
					<th width="15%">职务</th>
					<td width="35%" >${patrolUser.job}</td>
					<th width="15%"></th>
					<td width="35%" ></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_patrolUser');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
