<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看</title>
		<%@include file="/common/jsLib.jsp"%>
	</head>
	<body>
		<div class="page_dialog">
			<div class="inner6px">
				<div class="cell">
					<table>
						<tr>
							<th width="15%">类名</th>
							<td width="35%" colspan="3">${exceptionLog.className}</td>
						</tr>
						<tr>
							<th width="15%">方法名</th>
							<td width="35%" colspan="3">${exceptionLog.mothodName}</td>
						</tr>
						<tr>
							<th width="15%">发生时间</th>
							<td width="35%">${exceptionLog.createTime}</td>
							<th width="15%">操作者</th>
							<td width="35%">
							    <c:if test="${not empty exceptionLog.oprator}">
							    	${exceptionLog.oprator.displayName}
							    </c:if>
							</td>
						</tr>
						<tr>
							<th width="15%">异常详情</th>
							<td width="35%" colspan="3">
							    ${exceptionLog.msg}
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div class="btn_area_setc">
									<a href="###" class="btn_01" onclick="parent.close_win('expWindow');">关闭<b></b></a>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
