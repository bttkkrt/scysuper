<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<c:set var="curr_path" value="选择任务参与人"></c:set>
<html>
<head>
<title>选择任务参与人</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/themes/${curr_user.cssId}/css/style.css" />' />

		<script src='<c:url value="/webResources/js/common.js"/>'></script>
		<script src='<c:url value="/dwr/engine.js" />'></script>
		<script src='<c:url value="/dwr/util.js" />'></script>
		<script type='text/javascript'
			src='<c:url value="/dwr/interface/sysSettingManager.js"/>'> </script>
</head>

<body class="PageBgColor" style="overflow: auto">
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="settingFrm" method="post"> 
<img src='${chartUrl}'/>
</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>