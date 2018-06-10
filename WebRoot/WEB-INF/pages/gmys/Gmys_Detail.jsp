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
					<td width="35%" ><fmt:formatDate type="both" value="${gmys.monthTime}" pattern="yyyy-MM"/></td>
					<th width="15%">区域</th>
					<td width="35%" >${gmys.areaName}</td>
				</tr>
				<tr>
					<th width="15%">达标数量合计</th>
					<td width="35%" >${gmys.dbslhj}</td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>一级</strong></td>
				</tr>
				<tr>
					<th width="15%">一级计划达标数</th>
					<td width="35%" >${gmys.yjjhdbs}</td>
				
					<th width="15%">一级申报企业数</th>
					<td width="35%" >${gmys.yjsbqys}</td>
				</tr>
				<tr>
					<th width="15%">一级达标企业数</th>
					<td width="35%" >${gmys.yjdbqys}</td>
				
					<th width="15%">一级累计达标数</th>
					<td width="35%" >${gmys.yjljdbs}</td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>二级</strong></td>
				</tr>
				
				<tr>
					<th width="15%">二级计划达标数</th>
					<td width="35%" >${gmys.ejjhdbs}</td>
				
					<th width="15%">二级申报企业数</th>
					<td width="35%" >${gmys.ejsbqys}</td>
				</tr>
				<tr>
					<th width="15%">二级达标企业数</th>
					<td width="35%" >${gmys.ejdbqys}</td>
				
					<th width="15%">二级累计达标数</th>
					<td width="35%" >${gmys.ejljdbs}</td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>三级</strong></td>
				</tr>
				<tr>
				
					<th width="15%">三级计划复审数</th>
					<td width="35%" >${gmys.sjjhfss}</td>
				
					<th width="15%">三级复审达标数</th>
					<td width="35%" >${gmys.sjfsdbs}</td>
				</tr>
				<tr>
				    <th width="15%">三级摘牌企业数</th>
					<td width="35%" >${gmys.sjzpqys}</td>
					<th width="15%">三级累计达标数</th>
					<td width="35%" >${gmys.sjljdbs}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_gmys');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
