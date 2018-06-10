<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>导入安全检查项配置结果</title>

		<%@include file="/common/jsLib.jsp"%>
	</head>
	<body>
		<%@include file="/WEB-INF/template/content_title.jsp"%>
		<div class="submitdata">
			<span style="color: red;">${logInfo }</span>
		</div>
		<%@include file="/WEB-INF/template/pagefoot.jsp"%>
	</body>
</html>