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
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${posOpeTra.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${posOpeTra.companyName}</td>
				</tr>
				<tr>
				<th width="15%">操作类别</th>
					<td width="35%" ><cus:hxlabel codeName="岗位操作类别" itemValue="${posOpeTra.operateType}" /></td>
					<th width="15%">姓名</th>
					<td width="35%" >${posOpeTra.operateName}</td>
				</tr>
				<tr>
					<th width="15%">性别</th>
					<td width="35%" >${posOpeTra.sex}</td>
					<th width="15%">职位</th>
					<td width="35%" >${posOpeTra.position}</td>
				</tr>
				<tr>
					<th width="15%">文化程度</th>
					<td width="35%" ><cus:hxlabel codeName="学历" itemValue="${posOpeTra.education}" /></td>
					<th width="15%">电话</th>
					<td width="35%" >${posOpeTra.tele}</td>
				</tr>
				<tr>
					<th width="15%">身份证</th>
					<td width="35%" >${posOpeTra.idCard}</td>
					<th width="15%">地址</th>
					<td width="35%" >${posOpeTra.address}</td>
				</tr>
				<tr>
					<th width="15%">职称</th>
					<td width="35%" >${posOpeTra.jobTitle}</td>
					<th width="15%">首次领证时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${posOpeTra.firstLicense}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">合格证号</th>
					<td width="35%" >${posOpeTra.certificateNo}</td>
					<th width="15%">证书有效期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${posOpeTra.certificaateValid}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_posOpeTra');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
