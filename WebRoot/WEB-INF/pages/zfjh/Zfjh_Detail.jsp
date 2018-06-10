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
					<th width="15%">关联编号</th>
					<td width="35%">${zfjh.proId}</td>
					<th width="15%">年</th>
					<td width="35%">${zfjh.zfjhYear}</td>
				</tr>
				<tr>
					<th width="15%">周次</th>
					<td width="35%">${zfjh.zfjhWeek}</td>
					<th width="15%">直管企业名称</th>
					<td width="35%">${zfjh.zgqyName}</td>
				</tr>
				<tr>
					<th width="15%">直管企业工作日</th>
					<td width="35%">${zfjh.zgqyDay}</td>
					<th width="15%">直管企业检查内容</th>
					<td width="35%">${zfjh.zgqyContent}</td>
				</tr>
				<tr>
					<th width="15%">行业领域名称</th>
					<td width="35%">${zfjh.hylyName}</td>
					<th width="15%">行业领域工作日</th>
					<td width="35%">${zfjh.hylyDay}</td>
				</tr>
				<tr>
					<th width="15%">行业领域检查内容</th>
					<td width="35%">${zfjh.hylyContent}</td>
					<th width="15%">综合监管名称</th>
					<td width="35%">${zfjh.zhjgName}</td>
				</tr>
				<tr>
					<th width="15%">综合监管工作日</th>
					<td width="35%">${zfjh.zhjgDay}</td>
					<th width="15%">综合监管检查内容</th>
					<td width="35%">${zfjh.zhjgContent}</td>
				</tr>
				<tr>
					<th width="15%">县区政府名称</th>
					<td width="35%">${zfjh.xqzfName}</td>
					<th width="15%">县区政府工作日</th>
					<td width="35%">${zfjh.xqzfDay}</td>
				</tr>
				<tr>
					<th width="15%">县区政府检查内容</th>
					<td width="35%">${zfjh.xqzfContent}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px"  style="text-align:center" >
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeLayer();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
