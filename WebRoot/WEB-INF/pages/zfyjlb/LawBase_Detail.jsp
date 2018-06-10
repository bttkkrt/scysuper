<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<style>
		TD {
			FONT-SIZE: 9pt;
			FONT-FAMILY: "宋体", "宋体";
		}
</style>
</head>
<body style="overflow: auto;">
	<div class="page_dialog" style="overflow-y: auto;overflow-x: auto;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
	 			<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%">
					<tr>
						<td>&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<table border="1" cellpadding="0" style="border-collapse: collapse" width="100%" height="100%" bordercolor="#003366">
							<tr>
								<td height="25" width="20%" align="center">执法依据名称：</td>
								<td height="25" width="80%" colspan="3">${lawBase.lawName}</td>
							</tr>
							<tr>
								<td height="25" width="20%" align="center">索 引 号：</td>
								<td height="25" width="80%" colspan="3">${lawBase.syh}</td>
							</tr>
							<tr>
								<td height="25" width="20%" align="center">发布机构：</td>
								<td height="25" width="80%" colspan="3">${lawBase.fbjg}</td>
							</tr>
							<tr>
								<td height="25" width="20%" align="center">发布日期：</td>
								<td height="25" width="30%"><fmt:formatDate type="date" value="${lawBase.createDay}"/></td>
								<td height="25" width="20%" align="center">实施日期：</td>
								<td height="25" width="30%"><fmt:formatDate type="date" value="${lawBase.pubDay}"/></td>
							</tr>
							<tr>
								<td height="25" width="20%" align="center">内容概述：</td>
								<td height="25" width="80%" colspan="3">${lawBase.gs}</td>
							</tr>
							</table>						
						</td>
					</tr>
					<tr>
						<td valign="top" style="line-height: 150%">
							<div class="pagecontent">
								${lawBase.content}
							</div>
						</td>
					</tr>
				</table>
			</div>
</body>
</html>
