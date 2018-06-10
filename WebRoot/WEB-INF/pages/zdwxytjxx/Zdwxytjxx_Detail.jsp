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
					<th width="15%">所在镇</th>
					<td width="35%">${zdwxytjxx.szzname}</td>
					<th width="15%">企业名称</th>
					<td width="35%">${zdwxytjxx.qymc}</td>
				</tr>
				<tr>
					<th width="15%">一级危险化学品重大危险源个数</th>
					<td width="35%">${zdwxytjxx.yjgs}</td>
					<th width="15%">二级危险化学品重大危险源个数</th>
					<td width="35%">${zdwxytjxx.ejgs}</td>
				</tr>
				<tr>
					<th width="15%">三级危险化学品重大危险源个数</th>
					<td width="35%">${zdwxytjxx.sjgs}</td>
					<th width="15%">四级危险化学品重大危险源个数</th>
					<td width="35%">${zdwxytjxx.sijigs}</td>
				</tr>
				<tr>
					<th width="15%">填表人</th>
					<td width="35%">${zdwxytjxx.tbr}</td>
					<th width="15%">联系电话</th>
					<td width="35%">${zdwxytjxx.lxdh}</td>
				</tr>
				<tr>
					<th width="15%">填表日期</th>
					<td width="35%"><fmt:formatDate type="date" value="${zdwxytjxx.tbrq}" /></td>
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
