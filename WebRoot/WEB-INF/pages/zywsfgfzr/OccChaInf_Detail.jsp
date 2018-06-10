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
					<th width="15%">姓名</th>
					<td width="35%" >${occChaInf.jshxName}</td>
					<th width="15%">职务</th>
					<td width="35%" >${occChaInf.duty}</td>
				</tr>
				<tr>
					<th width="15%">办公电话</th>
					<td width="35%" >${occChaInf.telephone}</td>
					<th width="15%">手机</th>
					<td width="35%" >${occChaInf.mobile}</td>
				</tr>
				<tr>
					<th width="15%">文化程度</th>
					<td width="35%" ><cus:hxlabel codeName="学历" itemValue="${occChaInf.degreeEducation}" /></td>
					<th width="15%">专业</th>
					<td width="35%" ><cus:hxlabel codeName="专业" itemValue="${occChaInf.professional}" /></td>
				</tr>
				<tr>
					<th width="15%">培训日期</th>
					<td width="35%" ><fmt:formatDate type="both" value="${occChaInf.trainingDate}" /></td>
					<th width="15%">培训合格证号</th>
					<td width="35%" >${occChaInf.trainingCertificatNumber}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_occChaInf');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
