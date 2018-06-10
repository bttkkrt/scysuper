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
					<th width="15%">身份证</th>
					<td width="35%">${zycsjcry.sfz}</td>
					<th width="15%">姓名</th>
					<td width="35%">${zycsjcry.xm}</td>
				</tr>
				<tr>
					<th width="15%">体检类型</th>
					<td width="35%">${zycsjcry.tjlx}</td>
					<th width="15%">体检时间</th>
					<td width="35%">${zycsjcry.tjrq}</td>
				</tr>
				<tr>	
					<th width="15%">体检机构</th>
					<td width="35%">${zycsjcry.tjjg}</td>
					<th width="15%">体检结果</th>
					<td width="35%">${zycsjcry.tjjguo}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
