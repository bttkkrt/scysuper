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
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">上级责任人名称</th>
					<td width="35%">${gridManager.upUsername}</td>
					<th width="15%">上级责任人id</th>
					<td width="35%">${gridManager.upUserid}</td>
				</tr>
				<tr>
					<th width="15%">下级责任人名称</th>
					<td width="35%">${gridManager.downUsername}</td>
					<th width="15%">下级责任人id</th>
					<td width="35%">${gridManager.downUserid}</td>
				</tr>
				<tr>
					<th width="15%">类型</th>
					<td width="35%">${gridManager.gridType}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
