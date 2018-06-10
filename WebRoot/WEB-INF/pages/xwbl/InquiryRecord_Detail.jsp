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
					<th width="15%">询问开始时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${inquiryRecord.inquiryPeriod}" /></td>
					<th width="15%">询问结束时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${inquiryRecord.endTime}" /></td>
				</tr>
				<tr>
					<th width="15%">被询问人姓名</th>
					<td width="35%" >${inquiryRecord.askedPerson}</td>
					<th width="15%">性别</th>
					<td width="35%" ><cus:hxlabel codeName="性别" itemValue="${inquiryRecord.sex}" /></td>
				</tr>
				<tr>
					<th width="15%">年龄</th>
					<td width="35%" >${inquiryRecord.peopleAge}</td>
					<th width="15%">身份证号</th>
					<td width="35%" >${inquiryRecord.cardId}</td>
				</tr>
				<tr>
					<th width="15%">职位</th>
					<td width="35%" >${inquiryRecord.position}</td>
					<th width="15%">住址</th>
					<td width="35%" >${inquiryRecord.address}</td>
				</tr>
				<tr>
					<th width="15%">电话</th>
					<td width="35%" >${inquiryRecord.tele}</td>
					<th width="15%">在场人</th>
					<td width="35%" >${inquiryRecord.presentPeople}</td>
				</tr>
				<tr>
					<th width="15%">询问记录</th>
					<td width="35%" >${inquiryRecord.inquiryRecord}</td>
					<th width="15%">询问人</th>
					<td width="35%" >${inquiryRecord.inquiryPerson}</td>
				</tr>
				<tr>
					<th width="15%">记录人</th>
					<td width="35%" >${inquiryRecord.recordPerson}</td>
					<th width="15%">关联文书编号</th>
					<td width="35%" >${inquiryRecord.relatedId}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_inquiryRecord');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
