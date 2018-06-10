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
					<th width="15%">年度</th>
					<td width="35%" ><fmt:formatDate type="both" value="${fcsbzzqk.yearTime}" pattern="yyyy"/></td>
					<th width="15%">区域</th>
					<td width="35%" >${fcsbzzqk.areaName}</td>
				</tr>
				<tr>
					<th width="15%">原有企业</th>
					<td width="35%" >${fcsbzzqk.yyqy}</td>
					<th width="15%">现有企业</th>
					<td width="35%" >${fcsbzzqk.xyqy}</td>
				</tr>
				<tr>
					<th width="15%">当年新增</th>
					<td width="35%" >${fcsbzzqk.dnxz}</td>
					<th width="15%">当年关闭</th>
					<td width="35%" >${fcsbzzqk.dngb}</td>
				</tr>
				<tr>
					<th width="15%">当年取缔</th>
					<td width="35%" >${fcsbzzqk.dnqd}</td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>当年累计检查情况</strong></td>
				</tr>
				<tr>
					<th width="15%">省级检查</th>
					<td width="35%" >${fcsbzzqk.sjjc}</td>
				
					<th width="15%">市级检查</th>
					<td width="35%" >${fcsbzzqk.shjjc}</td>
				</tr>
				<tr>
					<th width="15%">区级检查</th>
					<td width="35%" >${fcsbzzqk.qjjc}</td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>视频监控</strong></td>
				</tr>
				<tr>
					<th width="15%">已安装</th>
					<td width="35%" >${fcsbzzqk.yaz}</td>
					<th width="15%">当年计划安装数</th>
					<td width="35%" >${fcsbzzqk.dnjhazs}</td>
				</tr>
				<tr>
				    <th width="15%">正在施工数 </th>
					<td width="35%" >${fcsbzzqk.zzsgs}</td>
					<th width="15%">当年安装数 </th>
					<td width="35%" >${fcsbzzqk.dnazs}</td>
				
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_fcsbzzqk');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
