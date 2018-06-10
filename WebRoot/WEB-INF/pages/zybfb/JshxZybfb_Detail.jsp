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
					<th width="15%">作业场所名称</th>
					<td width="35%">${jshxZybfb.workName}</td>
					<th width="15%">职业病危害因素名称</th>
					<td width="35%">${jshxZybfb.zybName}</td>
				</tr>
				<tr>
					<th width="15%">接触人数(可重复)</th>
					<td width="35%">${jshxZybfb.repeatCount}</td>
					<th width="15%">接触人数(不可重复)</th>
					<td width="35%">${jshxZybfb.noRepeatCount}</td>
				</tr>
				<tr>
					<th width="15%">女工数</th>
					<td width="35%">${jshxZybfb.womanCount}</td>
					<th width="15%">填报人</th>
					<td width="35%">${jshxZybfb.tbr}</td>
				</tr>
				<tr>
					<th width="15%">联系电话</th>
					<td width="35%">${jshxZybfb.telephone}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px"  style="text-align:center;">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
