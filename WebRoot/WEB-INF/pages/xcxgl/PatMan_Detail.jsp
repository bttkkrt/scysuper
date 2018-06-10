<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
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
					<th width="35%">巡查项类型</th>
					<td width="65%" >
						<s:select theme="simple" list="types"  disabled="true" name="patMan.patrolType"  listKey="id" listValue="name"  headerKey="" headerValue="--请选择--"></s:select>
					</td>
				</tr>
				<tr>
					<th width="35%">巡查项</th>
					<td width="65%" >${patMan.patrolName}</td>
				</tr>
				<tr>
					<th width="35%">备注</th>
					<td width="65%" ><textarea disabled="true" name="patMan.remark" type="text" maxlength="200">${patMan.remark}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_patMan');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
