﻿<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">案件来源</th>
					<td width="35%"><cus:hxlabel codeName="案件来源" itemValue="${insRec.caseSource}" /></td>
					<th width="15%">文书类型</th>
					<td width="35%" ><cus:hxlabel codeName="文书类型" itemValue="${insRec.instrumentType}" /></td>
				</tr>
				<tr>
					<th width="15%">检查记录</th>
					<td width="85%" colspan="3">
						<textarea readOnly name="insRec.inspectionRecord" style="width:96%;height:120px">${insRec.inspectionRecord}</textarea>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_insRec');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
