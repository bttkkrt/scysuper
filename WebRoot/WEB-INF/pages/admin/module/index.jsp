<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>功能模块管理</title>
	</head>
	<body style="overflow: auto">
		<table width="100%" height="100%">
			<tr>
				<td width="20%" height="100%">
					<iframe id="_left" name="_left" scrolling="NO" noresize width="100%"
						height="100%" frameborder="0" scrolling="auto"
						src="moduleTree.action?func=findChildForAdmin"></iframe>
				</td>
				<td width="78%" height="100%" align="left">
					<iframe id="_right" name="module_right"
						src="list.action"
						frameborder="0" scrolling="auto" width="100%" height="100%"></iframe>
				</td>
				<td width="14">
					&nbsp;
				</td>
			</tr>
		</table>
	</body>
</html>