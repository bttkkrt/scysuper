<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${ppeUseManage.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${ppeUseManage.companyName}</td>
				</tr>
				<tr>
					<th width="15%">用品名称</th>
					<td width="35%" >${ppeUseManage.ppeName}</td>
					<th width="15%">领用人</th>
					<td width="35%" >${ppeUseManage.ppeUsePeople}</td>
				</tr>
				<tr>
					<th width="15%">领用数量</th>
					<td width="35%" >${ppeUseManage.ppeUseNum}</td>
					<th width="15%">领用时间</th>
					<td width="35%" ><fmt:formatDate pattern='yyyy-MM-dd' type="both" value="${ppeUseManage.ppeUseTime}" /></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea readOnly name="ppeUseManage.ppeUseRemark" style="width:96%;height:60px">${ppeUseManage.ppeUseRemark}</textarea></td>
					
				</tr>
				 
			</table>
		</div></div></div>
	</form>
</body>
</html>
