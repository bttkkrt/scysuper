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
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${occDisPre.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${occDisPre.companyName}</td>
				</tr>
				<tr>
					<th width="15%">经费</th>
					<td width="35%" >${occDisPre.attachment}</td>
					<th width="15%">年度</th>
					<td width="35%" >${occDisPre.jshxYear}</td>
				</tr>
				<tr>
					<th width="15%">用途</th>
					<td width="85%" colspan="3"><textarea readOnly name="occDisPre.jshxUse" style="width:96%;height:100px">${occDisPre.jshxUse}</textarea></td>
				</tr>
				<tr>
				<th width="15%">工作内容</th>
				<td width="85%" colspan="3"><textarea readOnly name="occDisPre.workContent" style="width:96%;height:100px">${occDisPre.workContent}</textarea></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3"><textarea readOnly name="occDisPre.remark" style="width:96%;height:100px">${occDisPre.remark}</textarea></td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
