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
					<td width="35%" ><fmt:formatDate type="both" value="${qygsrdqk.monthTime}" pattern="yyyy-MM"/></td>
					<th width="15%">工伤申报总数</th>
					<td width="35%" >${qygsrdqk.gssbzs}</td>
				</tr>
				<tr>
					<th width="15%">其中个人申报</th>
					<td width="35%" >${qygsrdqk.qzgrsb}</td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>不同认定依据分类</strong></td>
				</tr>
				<tr>
					<th width="15%">14.1</th>
					<td width="35%" >${qygsrdqk.btrd1}</td>
				
					<th width="15%">14.2</th>
					<td width="35%" >${qygsrdqk.btrd2}</td>
				</tr>
				<tr>
					<th width="15%">14.3</th>
					<td width="35%" >${qygsrdqk.btrd3}</td>
				
					<th width="15%">14.4</th>
					<td width="35%" >${qygsrdqk.btrd4}</td>
				</tr>
				<tr>
					<th width="15%">14.5</th>
					<td width="35%" >${qygsrdqk.btrd5}</td>
				
					<th width="15%">14.6</th>
					<td width="35%" >${qygsrdqk.btrd6}</td>
				</tr>
				<tr>
					<th width="15%">15.1</th>
					<td width="35%" >${qygsrdqk.btrd7}</td>
				
					<th width="15%">15.2</th>
					<td width="35%" >${qygsrdqk.btrd8}</td>
				</tr>
				<tr>
					<th width="15%">15.3</th>
					<td width="35%" >${qygsrdqk.btrd9}</td>
				</tr>
				<tr>
					<th width="15%">轻伤</th>
					<td width="35%" >${qygsrdqk.qdss}</td>
					<th width="15%">重伤</th>
					<td width="35%" >${qygsrdqk.zdss}</td>
				</tr>
				<tr>
					<th width="15%">工亡</th>
					<td width="35%" >${qygsrdqk.gzsw}</td>
					<th width="15%">生产</th>
					<td width="35%" >${qygsrdqk.sclb}</td>
				</tr>
				<tr>
					<th width="15%">交通</th>
					<td width="35%" >${qygsrdqk.jtlb}</td>
					<th width="15%">其他</th>
					<td width="35%" >${qygsrdqk.qtlb}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_qygsrdqk');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
