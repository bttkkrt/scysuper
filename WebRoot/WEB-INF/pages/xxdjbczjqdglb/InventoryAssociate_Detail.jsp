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
					<th width="15%">规格型号</th>
					<td width="35%" >${inventoryAssociate.specificationModel}</td>
					<th width="15%">产地</th>
					<td width="35%" >${inventoryAssociate.originPlace}</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" ><textarea readOnly name="inventoryAssociate.remark" style="width:100%;height:120px">${inventoryAssociate.remark}</textarea></td>
					<th width="15%">单位</th>
					<td width="35%" >${inventoryAssociate.company}</td>
				</tr>
				<tr>
					<th width="15%">价格</th>
					<td width="35%" >${inventoryAssociate.price}</td>
					<th width="15%">数量</th>
					<td width="35%" >${inventoryAssociate.inventoryNum}</td>
				</tr>
				<tr>
					<th width="15%">证据名称</th>
					<td width="35%" >${inventoryAssociate.evidenceName}</td>
					<th width="15%">成色</th>
					<td width="35%" >${inventoryAssociate.condition}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_inventoryAssociate');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
