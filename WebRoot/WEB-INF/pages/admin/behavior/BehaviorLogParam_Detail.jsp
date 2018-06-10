<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
	<form name="myform" method="post">
		<div class="submitdata" style="width:100%; height:100%">
			<table width="500">
				<tr>
					<th width="15%">参数名称</th>
					<td width="35%">${behaviorLogParam.paramName}</td>
					<th width="15%">参数值</th>
					<td width="35%">${behaviorLogParam.paramValue}</td>
				</tr>
				<tr>
					<th width="15%">日志</th>
					<td width="35%">${behaviorLogParam.logId}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win('paramWindow');">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
