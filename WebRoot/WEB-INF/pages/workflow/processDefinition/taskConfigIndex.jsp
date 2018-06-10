<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>

<c:set var="curr_path" value="用户管理"></c:set>
<html>
	<head>
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<title>用户管理</title>
		<link rel="stylesheet" type="text/css"
              href='<c:url value="/webResources/themes/${curr_user.cssId}/css/style.css" />' />

	</head>
	<body>
		<table border="0" width="100%" height="100%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="20%" height="100%">
					<iframe name="_left" scrolling="NO" noresize width="100%"
						height="100%" frameborder="0" scrolling="auto"
						src="${ctx}/jsp/admin/dept/deptTreeForWorkflow.action"></iframe>
				</td>
				<td width="80%" height="100%" align="center">
					<iframe name="_right"
						src="<c:url value="/workflow/initTaskConfig.action"/>"
						frameborder="0" scrolling="auto" width="100%" height="100%"
						width="100%" height="100%"></iframe>
				</td>
				<td width="14">
					&nbsp;
				</td>
			</tr>
		</table>
	</body>
</html>