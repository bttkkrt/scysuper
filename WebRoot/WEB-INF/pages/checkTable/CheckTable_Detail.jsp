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
				
				<table width="100%" border="0" >
				<tr>
					<th style="text-align:center">检查内容</th>
					<th style="text-align:center">检查情况</th>
					<th style="text-align:center">是否合格</th>
					<th style="text-align:center">备注</th>
				</tr>
				<c:forEach items="${checkContentList }" var="content" varStatus="i">
				
				<tr>
					<td>${content }</td>
					<td>${checkResultList[i.index ].checkResult }</td>
					<td>${checkResultList[i.index ].ifOk }</td>
					<td>${checkResultList[i.index ].remark }</td>
				</tr>
				</c:forEach>
				<tr>
					<th width="15%">检查时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${checkTable.checkTime}" /></td>
				</tr>
				
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="closeLayer();">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
