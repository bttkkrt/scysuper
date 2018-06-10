<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看部门</title>
		<%@include file="/common/jsLib.jsp"%>
	</head>
	<body>
		<div class="page_dialog">
			<div class="inner6px">
				<div class="cell">
					<table>
						<tr>
							<th>上级部门</th>
							<td>${dept.parentDept.deptName}</td>
						</tr>
						<tr>
							<th>部门编号</th>
							<td>${dept.deptCode}</td>
						</tr>
						<tr>
							<th>部门名称</th>
							<td>${dept.deptName}</td>
						</tr>
						<tr>
						    <th>部门类型</th>
						    <td><cus:hxlabel codeName="部门属性" itemValue="${dept.deptTypeCode}" /></td>
						</tr>
						<c:if test="${not empty dept.linkedDeptTypeCode }">
						<tr>
						    <th>关联部门类型</th>
						    <td><cus:hxlabel codeName="部门属性" itemValue="${dept.linkedDeptTypeCode}" /></td>
						</tr>
						</c:if>
						<tr>
							<th>同级排序</th>
							<td>${dept.sortSQ}</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="btn_area_setc">
									<a href="###" class="btn_01" onclick="parent.close_win('view_dept');">关闭<b></b></a>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>