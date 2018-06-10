<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script type="text/javascript">
		function getMark(){
			return '-1';
		}
		function getChlidCode(){
			return "${equAndFac.mapkey}";
		}
	</script>
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${equAndFac.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${equAndFac.companyName}</td>
				</tr>
				<tr>
					<th width="15%">设备名称</th>
					<td width="35%" >${equAndFac.equipmentName}</td>
					<th width="15%">设备地点</th>
					<td width="35%" >${equAndFac.equipmentPlace}</td>
				</tr>
			<!-- 	<tr>
					<th width="15%">设备经度</th>
					<td width="35%" >${equAndFac.equipmentLongitude}</td>
					<th width="15%">设备纬度</th>
					<td width="35%" >${equAndFac.equipmentLatitude}</td>
				</tr> -->
				<tr>
					<th width="15%">设备编号</th>
					<td width="35%" >${equAndFac.equipmentNumber}</td>
				</tr>
				<tr>
					<td width="100%" colspan="4">
						<div style="width:100%;">
				        	<iframe id="map" name="map" src="${ctx}/webResources/Map/index.html"  style="height:300px;width:100%; border:hidden; "scrolling="no" ></iframe>

				    	</div>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
