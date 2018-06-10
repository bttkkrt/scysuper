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
					<th width="15%">标题</th>
					<td width="35%" >${lighthouse.title}</td>
					<th width="15%">发布时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${lighthouse.publicDate}" /></td>
				</tr>
				<tr>
				 <tr>
					
						<td colspan="2" width="35%"><strong>描述</strong></td>
					    <td colspan="2" width="65%" ><strong>相关图片</strong></td>
				</tr>
				<s:iterator value="list" id="p">
					<tr>
						<td colspan="2" width="35%"><s:property value="#p.descriptor"/></td>
						<td colspan="2" width="65%" >
							<s:iterator value="#p.photos">
								<a rel="example_group" href="/upload/photo/<s:property/>">
									<img style="width:50px;height:50px;" alt="" src="/upload/photo/<s:property/>" />
								</a>
							</s:iterator>
						</td>
						
						
					</tr>
				</s:iterator>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_lighthouse');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
