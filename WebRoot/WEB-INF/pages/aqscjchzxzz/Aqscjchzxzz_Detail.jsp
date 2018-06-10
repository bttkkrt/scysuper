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
					<td width="35%" ><fmt:formatDate type="both" value="${aqscjchzxzz.monthTime}" pattern="yyyy-MM"/></td>
					<th width="15%">区域</th>
					<td width="35%" >${aqscjchzxzz.areaName}</td>
				</tr>
				<tr>
					<th width="15%">监管企业数</th>
					<td width="35%" >${aqscjchzxzz.jgqys}</td>
					<th width="15%">检查企业目标</th>
					<td width="35%" >${aqscjchzxzz.jcqymb}</td>
				</tr>
				<tr>
					<th width="15%">检查企业数</th>
					<td width="35%" >${aqscjchzxzz.jcqys}</td>
				<tr>
					<th width="15%">一般隐患排查数</th>
					<td width="35%" >${aqscjchzxzz.ybyhpcs}</td>
				
					<th width="15%">一般隐患已整治数</th>
					<td width="35%" >${aqscjchzxzz.ybyhyzzs}</td>
				</tr>
				<tr>
					<th width="15%">重大隐患排查数</th>
					<td width="35%" >${aqscjchzxzz.zdyhpcs}</td>
				
					<th width="15%">重大隐患已整治数</th>
					<td width="35%" >${aqscjchzxzz.zdyhyzzs}</td>
				</tr>
				<tr>
					<th width="15%">打击非法违法行为</th>
					<td width="35%" >${aqscjchzxzz.djffwfxw}</td>
					<th width="15%">整治违规违法行为</th>
					<td width="35%" >${aqscjchzxzz.zzwgwfxw}</td>
				</tr>
				<tr>
					<th width="15%">处罚总起数（违法）</th>
					<td width="35%" >${aqscjchzxzz.cfzqs}</td>
					<th width="15%">处罚总起数（事故）</th>
					<td width="35%" >${aqscjchzxzz.cfzqssg}</td>
				</tr>
				<tr>
					<th width="15%">已结案事故（违法）</th>
					<td width="35%" >${aqscjchzxzz.yjasg}</td>
					<th width="15%">已结案事故（事故）</th>
					<td width="35%" >${aqscjchzxzz.yjasgsg}</td>
		         </tr>
		         <tr>
					<th width="15%">处罚金额（违法）</th>
					<td width="35%" >${aqscjchzxzz.cfje}</td>
					<th width="15%">处罚金额（事故）</th>
					<td width="35%" >${aqscjchzxzz.cfjesg}</td>
				</tr>
				<tr>
					<th width="15%">追究刑事责任</th>
					<td width="35%" >${aqscjchzxzz.zjxszr}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_aqscjchzxzz');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
