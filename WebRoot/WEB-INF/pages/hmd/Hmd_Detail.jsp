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
					<th width="15%">所在区域</th>
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${hmd.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${hmd.companyName}</td>
				</tr>
				<tr>
					<th width="15%">发生时间</th>
					<td width="35%" >${hmd.startTime}</td>
					<th width="15%">违法违规行为</th>
					<td width="35%" >${hmd.illegalActivity}</td>
				</tr>
				<tr>
					<th width="15%">管理期限</th>
					<td width="35%" >${hmd.manageTerm}</td>
					<th width="15%">录入时间</th>
					<td width="35%" ><fmt:formatDate type="both"  pattern="yyyy-MM-dd" value="${hmd.createTime}" /></td>
				</tr>
				<tr>
					<th width="15%">录入人员</th>
					<td width="35%" >${hmd.createUserID}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_hmd');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
