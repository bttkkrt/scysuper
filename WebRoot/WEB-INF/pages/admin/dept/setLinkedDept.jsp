<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <title>关联部门</title>
		<%@include file="/common/jsLib.jsp"%>
	    <script>
		    function saveLinkedDept(){
		    	document.linkedDeptFrm.submit();
		    }
	    </script>
	</head>

	<body validform="true">
		<div class="page_dialog">
				<div class="inner6px">
					<div class="cell">
						<form name="myform" method="post" action="saveLinkedDept.action">
							<s:token />
							<input type="hidden" name="dept.id" value="${dept.id}">
							<table style="text-align:center;">
							    <tr>
							        <th width="10%" style="text-align:center;"></th>
							        <th width="45%" style="text-align:center;">部门名称</th>
							        <th width="45%" style="text-align:center;">上层部门名称</th>
							    </tr>
								<c:forEach items="${deptList}" var="dept">
							    <tr>
							        <td>
							            <input type="checkbox" name="ids" value="${dept.id}" <c:if test="${not empty deptMap[dept.id] }">checked</c:if>>
							        </td>
							        <td>${dept.deptName}</td>
							        <td>${dept.parentDept.deptName}</td>
							    </tr>
								</c:forEach>
								<tr>
									<td colspan="3">
										<div class="btn_area_setc">
											<a href="###" class="btn_01_mini" type="submit">保存<b></b></a>
											<a href="###" class="btn_01_mini" onclick="parent.close_win('set_dept');">关闭<b></b></a>
										</div>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
		</div>
	</body>
</html>