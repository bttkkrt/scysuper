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
					<th width="15%">年度</th>
					<td width="35%">
						${zybfzjf.jshxYear}
					</td>
					<th width="15%">用途</th>
					<td width="35%">
						${zybfzjf.yt}
					</td>
				</tr>
				<tr>
					<th width="15%">工作内容</th>
					<td width="35%">${zybfzjf.gznr}</td>
					<th width="15%">经费(万元)</th>
					<td width="35%">${zybfzjf.fee}</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3">${zybfzjf.remark}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center" style="text-align:center;">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
