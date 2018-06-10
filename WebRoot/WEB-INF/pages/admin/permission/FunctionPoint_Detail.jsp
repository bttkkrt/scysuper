<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/jsLib.jsp"%>
	</head>
	<body>
		<div class="page_dialog">
			<div class="inner6px">
				<div class="cell">
					<table>
						<tr>
							<th>功能点名称</th>
							<td>${functionPoint.funcName}</td>
						</tr>
						<tr>
							<th>权限表达式</th>
							<td >${functionPoint.funcPermission}</td>
						</tr>
						<!--  
						<tr>
							<th>是否绑定模块</th>
							<td ><cus:hxlabel codeName="是或否" itemValue="${functionPoint.isBandingModule}" /></td>
						</tr>
						<tr>
							<th>绑定模块</th>
							<td >${functionPoint.moduleName}</td>
						</tr>
						-->
						<tr>
							<td colspan="2">
								<div class="btn_area_setc">
									<a href="###" class="btn_01" onclick="parent.close_win('win_functionPoint');">关闭<b></b></a>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
