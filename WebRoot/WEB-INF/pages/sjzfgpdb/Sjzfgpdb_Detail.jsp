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
					<th width="15%">月份</th>
					<td width="35%" ><fmt:formatDate type="both" value="${sjzfgpdb.monthTime}"  pattern="yyyy-MM"/></td>
					<th width="15%">区域</th>
					<td width="35%" >${sjzfgpdb.areaName}</td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>总体情况</strong></td>
				</tr>
				<tr>
					<th width="15%">总体挂牌数</th>
					<td width="35%" >${sjzfgpdb.ztgps}</td>
					<th width="15%">总体已整改数</th>
					<td width="35%" >${sjzfgpdb.ztyzgs}</td>
				</tr>
				<tr>
					<th width="15%">总体投入整改资金</th>
					<td width="35%" >${sjzfgpdb.zttrzgzj}</td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>苏州市级</strong></td>
				</tr>
				<tr>
					<th width="15%">市级挂牌数</th>
					<td width="35%" >${sjzfgpdb.sjgps}</td>
					<th width="15%">市级已整改数</th>
					<td width="35%" >${sjzfgpdb.sjyzgs}</td>
				</tr>
				<tr>
					<th width="15%">投入经费</th>
					<td width="35%" >${sjzfgpdb.sjtrjf}</td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>县（市、区）级</strong></td>
				</tr>
				<tr>
					<th width="15%">县级挂牌数</th>
					<td width="35%" >${sjzfgpdb.xjgps}</td>
					<th width="15%">县级已整改数</th>
					<td width="35%" >${sjzfgpdb.xjyzgs}</td>
				</tr>
				<tr>
					<th width="15%">投入经费</th>
					<td width="35%" >${sjzfgpdb.xjtrjf}</td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>乡镇（街道）级</strong></td>
				</tr>
				<tr>
					<th width="15%">镇级挂牌数</th>
					<td width="35%" >${sjzfgpdb.zjgps}</td>
					<th width="15%">镇级已整改数</th>
					<td width="35%" >${sjzfgpdb.zjyzgs}</td>
				</tr>
				<tr>
					<th width="15%">投入经费</th>
					<td width="35%" >${sjzfgpdb.zjtrjf}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_sjzfgpdb');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
