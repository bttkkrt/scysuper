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
					<td width="35%" ><fmt:formatDate type="both" value="${zywsndzb.yearTime}" pattern="yyyy"/></td>
					<th width="15%">区域</th>
					<td width="35%" >${zywsndzb.areaName}</td>
				</tr>
				<tr>
					<th width="15%">网上申报目标数</th>
					<td width="35%" >${zywsndzb.wssbmbs}</td>
					<th width="15%">网上申报完成数</th>
					<td width="35%" >${zywsndzb.wssbwcs}</td>
				</tr>
				<tr>
					<th width="15%">处罚案例目标数</th>
					<td width="35%" >${zywsndzb.cfalmbs}</td>
					<th width="15%">处罚案例完成数</th>
					<td width="35%" >${zywsndzb.cfalwcs}</td>
				</tr>
				<tr>
					<th width="15%">定期检测目标数</th>
					<td width="35%" >${zywsndzb.dqjcmbs}</td>
					<th width="15%">定期检测完成数</th>
					<td width="35%" >${zywsndzb.dqjcwcs}</td>
				</tr>
				<tr>
					<th width="15%">健康监护完成数</th>
					<td width="35%" >${zywsndzb.jkjhwcs}</td>
					<th width="15%">健康监护目标数</th>
					<td width="35%" >${zywsndzb.jkjhmbs}</td>
				</tr>
				<tr>
					<th width="15%">企业培训目标数</th>
					<td width="35%" >${zywsndzb.qypxmbs}</td>
					<th width="15%">企业培训完成数</th>
					<td width="35%" >${zywsndzb.qypxwcs}</td>
				</tr>
				<tr>
					<th width="15%">基础建设示范企业目标数</th>
					<td width="35%" >${zywsndzb.jcjsmbs}</td>
					<th width="15%">基础建设示范企业完成数</th>
					<td width="35%" >${zywsndzb.jcjswcs}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_zywsndzb');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
