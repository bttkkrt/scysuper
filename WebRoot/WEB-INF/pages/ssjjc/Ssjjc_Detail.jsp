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
					<th width="15%">检查时间</th>
					<td width="35%"><fmt:formatDate pattern="yyyy-MM-dd" type='both' value='${ssjjc.jcsj}' /></td>
					<th width="15%">检查企业比例</th>
					<td width="35%">${ssjjc.jcbl}%</td>
				</tr>
				<tr>
					<th width="15%">企业类型</th>
					<td width="85%" colspan="3"><cus:hxmulselectlabel codeName="双随机企业分类" itemValue="${ssjjc.qylx}"/></td>
				</tr>
				<tr>
					<th colspan="4" style="text-align:center">检查表</th>
				</tr>
				<tr>
					<td colspan="4">
						<table>
							<tr>
								<td width="15%">检查时间</td>
								<td width="20%">检查人员</td>
								<td width="65%">检查企业</td>
							</tr>
							<c:forEach var="bean" items="${sjList}">
								<tr>
									<td width="15%">${bean.jcsj}</td>
									<td width="20%">${bean.jcry}</td>
									<td width="65%">${bean.jcqy}</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_ssjjc');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
