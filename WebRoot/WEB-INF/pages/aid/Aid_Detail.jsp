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
					<th width="15%">物资名称</th>
					<td width="35%" >${aid.suppliedName}</td>
					<th width="15%">保管人联系方式</th>
					<td width="35%" >${aid.custodianMoblie}</td>
				</tr>
				<tr>
					<th width="15%">物资级别</th>
					<td width="35%" ><cus:hxlabel codeName="应急物资级别" itemValue="${aid.suppliedLevel}" /></td>
					<th width="15%">物资数量</th>
					<td width="35%" >${aid.suppliedCount}</td>
				</tr>
				<tr>
					<th width="15%">物资型号</th>
					<td width="35%" >${aid.suppliedModel}</td>
					<th width="15%">物资规格</th>
					<td width="35%" >${aid.suppliedSpecificate}</td>
				</tr>
				<tr>
					<th width="15%">购入日期</th>
					<td width="35%" ><fmt:formatDate type="both" value="${aid.purchaseDate}" /></td>
					<th width="15%">生产厂家</th>
					<td width="35%" >${aid.manufacture}</td>
				</tr>
				<tr>
					<th width="15%">出厂日期</th>
					<td width="35%" ><fmt:formatDate type="both" value="${aid.manufactureDate}" /></td>
					<th width="15%">有效期至</th>
					<td width="35%" ><fmt:formatDate type="both" value="${aid.validity}" /></td>
				</tr>
				<tr>
					<th width="15%">存放地点</th>
					<td width="35%" >${aid.storageLocation}</td>
					<th width="15%">负责保管人</th>
					<td width="35%" >${aid.custodian}</td>
				</tr>
				<tr>
					<th width="15%">用途说明</th>
					<td width="35%" colspan="3"><textarea readOnly name="aid.application" style="width:100%;height:90px">${aid.application}</textarea></td>
				</tr>
				<tr>
					<th width="15%">性能说明</th>
					<td width="35%" colspan="3"><textarea readOnly name="aid.performance" style="width:100%;height:90px">${aid.performance}</textarea></td>
				</tr>
				<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea readOnly name="aid.remark" style="width:100%;height:90px">${aid.remark}</textarea></td>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_aid');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
