<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript">
		function getMark(){
			return '-1';
		}
		function getChlidCode(){
			return "${publicBoard.mapkey}";
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
					<td width="35%" >${publicBoard.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${publicBoard.companyName}</td>
				</tr>
				<tr>
					<th width="15%">公告栏类别</th>
					<td width="35%" >${publicBoard.publicType}</td>
					<th width="15%">公告栏位置</th>
					<td width="35%" >${publicBoard.publicAddress}</td>
				</tr>
				<tr>
					<th width="15%">公告栏内容</th>
					<td width="35%" colspan="3"><textarea readOnly name="publicBoard.publicContent" style="width:96%;height:60px">${publicBoard.publicContent}</textarea></td>
				</tr>
				<tr>
					<td width="100%" colspan="4">
						<div style="width:100%;">
				        	<iframe id="map" name="map" src="${ctx}/webResources/Map/index.html"  style="height:500px;width:100%; border:hidden; "scrolling="no" ></iframe>

				    	</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_publicBoard');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
