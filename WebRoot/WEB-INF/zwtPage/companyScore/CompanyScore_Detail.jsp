<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ">
			<font style="color:blue;font-size:28px;">${companyScore.companyName}&nbsp;&nbsp;&nbsp;安全生产标准化评分</font>
			<br/>
			<table width="100%" border="0"  align="center" >
				<tr >
					<th width="5%" style="text-align:center">序号</th>
					<th width="20%" style="text-align:center">企业达标标准</th>
					<th width="5%" style="text-align:center">标准分值</th>
					<th width="15%" style="text-align:center">评分方式</th>
					<th width="5%" style="text-align:center">是否参评</th>
					<th width="10%" style="text-align:center">企业自评分</th>
					<th width="15%" style="text-align:center">扣分说明</th>
					<th width="10%" style="text-align:center">评审分</th>
					<th width="15%" style="text-align:center">扣分说明</th>
				</tr>
				<c:forEach var="checkItem" items="${list}" varStatus="status">
				<tr>
					<td style="text-align:center">${status.index + 1}</td>
					<td style="text-align:left">${checkItem.dbbz}</td>
					<td style="text-align:center">${checkItem.bzfz}</td>
					<td style="text-align:left">${checkItem.pffs}</td>
					<td style="text-align:center"><input type="checkbox" <c:if test="${checkItem.sfcp == '1'}">checked</c:if> disabled="disabled"/></td>
					<td style="text-align:center">${checkItem.qypf}</td>
					<td style="text-align:center">${checkItem.qybz}</td>
					<td style="text-align:center">${checkItem.ajpf}</td>
					<td style="text-align:center">${checkItem.ajbz}</td>
				</tr>
				</c:forEach>
				<tr>
					<td style="text-align:center" colspan="5">得分</td>
					<td style="text-align:center" colspan="2">${companyScore.zpzf}</td>
					<td style="text-align:center" colspan="2">${companyScore.ajzf}</td>
				</tr>
				
			</table>
		</div></div></div>
	</form>
</body>
</html>
