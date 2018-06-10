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
					<th width="15%">备注</th>
					<td width="35%" ><textarea readOnly name="identifyItemAssociate.remark" style="width:100%;height:120px">${identifyItemAssociate.remark}</textarea></td>
					<th width="15%">规格型号</th>
					<td width="35%" >${identifyItemAssociate.specificationModel}</td>
				</tr>
				<tr>
					<th width="15%">数量</th>
					<td width="35%" >${identifyItemAssociate.identifyNum}</td>
					<th width="15%">关联委托书编号</th>
					<td width="35%" >${identifyItemAssociate.attorenyId}</td>
				</tr>
				<tr>
					<th width="15%">物品名称</th>
					<td width="35%" >${identifyItemAssociate.itemName}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_identifyItemAssociate');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
