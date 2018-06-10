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
					<td width="35%" >${safetyExperts.safetyName}</td>
					<th width="15%">电子邮箱</th>
					<td width="35%" >${safetyExperts.email}</td>
				</tr>
				<tr>
					<th width="15%">性别</th>
					<td width="35%" ><cus:hxlabel codeName="性别" itemValue="${safetyExperts.sex}" /></td>
					<th width="15%">出生年月</th>
					<td width="35%" ><fmt:formatDate type="date" value="${safetyExperts.birth}"  pattern="yyyy-MM"/></td>
				</tr>
				<tr>
					<th width="15%">所学专业</th>
					<td width="35%" >${safetyExperts.professional}</td>
					<th width="15%">学历</th>
					<td width="35%" ><cus:hxlabel codeName="学历" itemValue="${safetyExperts.education}" /></td>
				</tr>
				<tr>
					<th width="15%">毕业时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${safetyExperts.graduationTime}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">职称</th>
					<td width="35%" >${safetyExperts.jobTitle}</td>
				</tr>
				<tr>
					<th width="15%">工作单位</th>
					<td width="35%" >${safetyExperts.employer}</td>
					<th width="15%">专长</th>
					<td width="35%" >${safetyExperts.specialty}</td>
				</tr>
				<tr>
					<th width="15%">住址</th>
					<td width="35%" >${safetyExperts.address}</td>
					<th width="15%">联系电话</th>
					<td width="35%" >${safetyExperts.mobile}</td>
				</tr>
				<tr>
					<th width="15%">工作记录</th>
					<td width="35%" colspan="3"><textarea readOnly name="safetyExperts.workRecord" style="width:96%;height:120px">${safetyExperts.workRecord}</textarea></td>
				</tr>
				<th width="15%">教育情况</th>
				<td width="35%" colspan="3"><textarea readOnly name="safetyExperts.educationSec" style="width:96%;height:120px">${safetyExperts.educationSec}</textarea></td>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_safetyExperts');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
