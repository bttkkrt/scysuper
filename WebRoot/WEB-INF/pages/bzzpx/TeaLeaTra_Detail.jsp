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
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${teaLeaTra.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${teaLeaTra.companyName}</td>
				</tr>
				<tr>
					<th width="15%">车间名称</th>
					<td width="35%" >${teaLeaTra.trainingWorkshopName}</td>
					<th width="15%">姓名</th>
					<td width="35%" >${teaLeaTra.trainingName}</td>
				</tr>
				<tr>
					<th width="15%">性别</th>
					<td width="35%" ><cus:hxlabel codeName="性别" itemValue="${teaLeaTra.trainingSex}" /></td>
					<th width="15%">职位</th>
					<td width="35%" >${teaLeaTra.trainingPosition}</td>
				</tr>
				<tr>
					<th width="15%">电话</th>
					<td width="35%" >${teaLeaTra.trainingPhone}</td>
					<th width="15%">授课人</th>
					<td width="35%" >${teaLeaTra.trainingTeacher}</td>
				</tr>
				<tr>
					<th width="15%">培训单位</th>
					<td width="35%" >${teaLeaTra.trainingAddress}</td>
					<th width="15%">培训学时</th>
					<td width="35%" >${teaLeaTra.trainingTeacheTime}</td>
				</tr>
				<tr>
					<th width="15%">培训开始时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${teaLeaTra.trainingTime}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">培训结束时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${teaLeaTra.trainingTimeEnd}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">培训内容</th>
					<td width="35%" colspan="3"><textarea readOnly name="teaLeaTra.trainingContent" style="width:96%;height:120px">${teaLeaTra.trainingContent}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_teaLeaTra');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
