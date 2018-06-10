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
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${ppeManag.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${ppeManag.companyName}</td>
				</tr>
				<tr>
					<th width="15%">用品编号</th>
					<td width="35%" >${ppeManag.ppeNo}</td>
					<th width="15%">用品名称</th>
					<td width="35%" >${ppeManag.ppeName}</td>
				</tr>
				<tr>
					<th width="15%">用品数量</th>
					<td width="35%" >${ppeManag.ppeNum}</td>
					
					<th width="15%">用品单位</th>
					<td width="35%" >${ppeManag.ppeCompany}</td>
				</tr>
				<tr>
					<th width="15%">用品分类</th>
					<td width="35%" ><cus:hxlabel codeName="劳保用品分类" itemValue="${ppeManag.ppeType}" /></td>
					
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3" ><textarea readOnly name="ppeManag.ppeRemark" style="width:96%;height:60px">${ppeManag.ppeRemark}</textarea></td>
					
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_ppeManag');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
