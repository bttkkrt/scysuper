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
			return "${medIns.mapkey}";
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
					<th width="15%">机构名称</th>
					<td width="35%" >${medIns.agencyName}</td>
					<th width="15%">联系方式</th>
					<td width="35%" >${medIns.mobile}</td>
				</tr>
				<tr>
					<th width="15%">机构编号</th>
					<td width="35%" >${medIns.agencyNum}</td>
					<th width="15%">机构地址</th>
					<td width="35%" >${medIns.agencyAddress}</td>
				</tr>
				<tr>
					<td width="100%" colspan="4">
						<div style="width:100%;">
				        	<iframe id="map" name="map" src="${ctx}/webResources/Map/index.html"  style="height:500px;width:100%; border:hidden; "scrolling="no" ></iframe>

				    	</div>
					</td>
				</tr>
			 
			</table>
		</div></div></div>
	</form>
</body>
</html>
