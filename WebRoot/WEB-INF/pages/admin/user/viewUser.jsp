<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户资料</title>
		<%@include file="/common/jsLib.jsp"%>
	</head>
	<body>
		<div class="page_dialog">
			<div class="inner6px">
				<div class="cell">
					<table>
						<tr>
							<th>姓名</th>
							<td>${user.displayName}</td>
						</tr>
						<tr>
							<th>用户名</th>
							<td>${user.loginId}</td>
						</tr>
						<tr>
							<th>所属部门</th>
							<td>${user.dept.deptName}</td>
						</tr>
						<tr>
							<th>职务</th>
							<td>${user.duty}</td>
						</tr>
						<tr>
							<th>电话</th>
							<td>${user.tel}</td>
						</tr>
						<tr>
							<th>手机</th>
							<td>${user.mobile}</td>
						</tr>
						<tr>
							<th>电子邮箱</th>
							<td>${user.email}</td>
						</tr>
						<tr>
							<th>执法证号</th>
							<td>${user.zfzh}</td>
						</tr>
						<tr>
							<th>电子签名</th>
							<td>
								<a href="${user.filePath}" rel="example_group">	
									<img src="${user.filePath}"
										border='0' width='100' height='50'/>
								</a>
				    		</td>
						</tr>
						<tr>
							<th>用户角色</th>
							<td>
								<c:forEach var="userRight" items="${user.userRoles}">
									${userRight.role.roleName}, 
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="btn_area_setc">
									<a href="###" class="btn_01" onclick="closeLayer();">关闭<b></b></a>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>