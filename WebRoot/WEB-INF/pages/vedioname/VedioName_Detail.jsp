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
					<th width="15%">监控平台视频名称</th>
					<td width="35%">${vedioName.vedioname}</td>
					<th width="15%">显示名称</th>
					<td width="35%">${vedioName.showname}</td>
				</tr>
				<tr>
					<th width="15%">企业名称</th>
					<td width="35%">${vedioName.companyname}</td>
					<th width="15%">企业类型</th>
					<td width="35%">
						<s:if test="vedioName.qylx == 0">危化企业</s:if>
						<s:if test="vedioName.qylx == 1">涉粉企业</s:if>
					</td>
				</tr>
				<tr>
					<th width="15%">排列顺序</th>
					<td width="35%">${vedioName.sort}</td>
					<th width="15%">备注</th>
					<td width="85%" colspan="3">${vedioName.remark}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
