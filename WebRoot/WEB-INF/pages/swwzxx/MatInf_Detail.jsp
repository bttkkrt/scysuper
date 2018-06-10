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
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${matInf.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${matInf.companyName}</td>
				</tr>
				<tr>
					<th width="15%">危险源单元名称</th>
					<td width="35%" >${matInf.dangerouSourceName}</td>
					<th width="15%">危险化学品名称</th>
					<td width="35%" >${matInf.dangerousChemicalName}</td>
				</tr>
				<tr>
					<th width="15%">是否剧毒化学品</th>
					<td width="35%" ><cus:hxlabel codeName="是或否" itemValue="${matInf.ifToxicChemicals}" /></td>
					<th width="15%">危规号</th>
					<td width="35%" >${matInf.riskGauge}</td>
				</tr>
				<tr>
					<th width="15%">所处装置</th>
					<td width="35%" >${matInf.device}</td>
					<th width="15%">存在量</th>
					<td width="35%" >${matInf.existenceQuantity}</td>
				</tr>
				<tr>
					<th width="15%">临界值</th>
					<td width="35%" >${matInf.criticalValue}</td>
					<th width="15%">校正系数</th>
					<td width="35%" >${matInf.correctionFactor}</td>
				</tr>
				<tr>
					<th width="15%">涉及危险工艺</th>
					<td width="35%" >${matInf.hazardousProcess}</td>
					<th width="15%">操作人员情况</th>
					<td width="35%" >${matInf.operatingPersonnel}</td>
				</tr>
				<tr>
					<th width="15%">运输注意事项</th>
					<td width="85%" colspan="3"><textarea readOnly style="width:96%;height:60px">${matInf.transportationNote}</textarea></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3"><textarea readOnly style="width:96%;height:60px">${matInf.remark}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_matInf');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
