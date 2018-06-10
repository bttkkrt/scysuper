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
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${ppeWareManag.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${ppeWareManag.companyName}</td>
				</tr>
				<tr>
					<th width="15%">用品名称</th>
					<td width="35%" >${ppeWareManag.ppeName}</td>
					<th width="15%">类型</th>
					<td width="35%" ><cus:hxlabel codeName="进库出库" itemValue="${ppeWareManag.ppeWareType}" /></td>
				</tr>
				<tr>
					
					<th width="15%">数量</th>
					<td width="35%" >${ppeWareManag.ppeWareNum}</td>
					<th width="15%">盘点人</th>
					<td width="35%" >${ppeWareManag.ppeWarePeople}</td>
				</tr>
				<tr>
					
					<th width="15%">盘点时间</th>
					<td width="35%" ><fmt:formatDate pattern='yyyy-MM-dd' type="both" value="${ppeWareManag.ppeWareTime}" /></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3" ><textarea readOnly name="ppeWareManag.ppeWareRemark" style="width:96%;height:60px">${ppeWareManag.ppeWareRemark}</textarea></td>
					
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_ppeWareManag');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
