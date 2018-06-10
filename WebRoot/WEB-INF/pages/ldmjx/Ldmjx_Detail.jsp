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
					<td width="35%" ><fmt:formatDate type="both" value="${ldmjx.monthTime}" pattern="yyyy-MM"/></td>
					<th width="15%">区域</th>
					<td width="35%" >${ldmjx.areaName}</td>
				</tr>
				<tr>
					<th width="15%">现有企业数</th>
					<td width="35%" >${ldmjx.xyqys}</td>
					<th width="15%">检查数</th>
					<td width="35%" >${ldmjx.jcs}</td>
				</tr>
				<tr>
					<th width="15%">出动检查数</th>
					<td width="35%" >${ldmjx.cdjcs}</td>
					<th width="15%">发现隐患数</th>
					<td width="35%" >${ldmjx.fxyhs}</td>
				</tr>
				<tr>
					<th width="15%">整改隐患数</th>
					<td width="35%" >${ldmjx.zgyhs}</td>
					<th width="15%">处罚数</th>
					<td width="35%" >${ldmjx.cfs}</td>
				</tr>
				<tr>
					<th width="15%">整治关闭数</th>
					<td width="35%" >${ldmjx.zzgbs}</td>
					<th width="15%">达到安全标准化数</th>
					<td width="35%" >${ldmjx.ddaqbzhs}</td>
				</tr>
				<tr>
					<th width="15%">录入监管信息系统数</th>
					<td width="35%" >${ldmjx.lrjgxxx}</td>
					<th width="15%">建立安全总监数</th>
					<td width="35%" >${ldmjx.jlaqzjs}</td>
				</tr>
				<tr>
					<th width="15%">投入安全责任险数</th>
					<td width="35%" >${ldmjx.traqzr}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_ldmjx');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
