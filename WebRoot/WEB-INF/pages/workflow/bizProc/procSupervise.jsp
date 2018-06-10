<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html>
	<head>
		<head>
		<title>流程监控</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="description" content="模块的列表显示页面">
		<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/themes/${curr_user.cssId}/css/style.css" />' />
		<script src='<c:url value="/webResources/js/common.js"/>'></script>
		<script src='<c:url value="/dwr/engine.js" />'></script>
		<script src='<c:url value="/dwr/util.js" />'></script>
		<script>

    </script>

	</head>

	<body><c:set var="curr_path" value="流程监控"></c:set>
		<%@include file="/WEB-INF/template/content_title.jsp"%>
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td>
						<jbpm:processimageToken token="${tokenId}" />
					</td>
			</table>
	</body>
</html>