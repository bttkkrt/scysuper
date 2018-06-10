<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src='<c:url value="/webResources/js/easyui/jquery.easyui.min.js"/>'></script>
	<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/js/easyui/themes/default/easyui.css"/>'> 
	<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/js/easyui/themes/icon.css"/>'>
	<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>  
	<link rel="stylesheet" type="text/css" href="<c:url value='/webResources/themes/${curr_user.cssId}/css/style.css' />" />
	<script src="<c:url value='/webResources/js/common.js' />"></script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">用户名</th>
					<td width="35%">${terminalLoginInfo.userName}</td>
					<th width="15%">所属部门</th>
					<td width="35%">${terminalLoginInfo.deptname}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center;">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
