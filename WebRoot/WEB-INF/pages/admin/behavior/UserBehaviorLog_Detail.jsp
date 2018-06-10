<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看</title>
		<%@include file="/common/jsLib.jsp"%>
	</head>
	<body>
		<div class="page_dialog">
			<form name="myform" method="post">
				<div class="inner6px">
					<div class="cell">
						<table>
							<tr>
								<th width="15%">
									用户行为
								</th>
								<td width="85%" colspan="3">
									${userBehaviorLog.behavior.behaviorName}
								</td>
							</tr>
							<tr>
								<th width="15%">
									操作用户
								</th>
								<td width="35%">
									${userBehaviorLog.user.displayName}
								</td>
								<th width="15%">
									日志记录时间
								</th>
								<td width="35%">
									<fmt:formatDate type="both"
										value="${userBehaviorLog.loggedDate}" />
								</td>
							</tr>
							<tr>
								<th width="15%">
									日志内容
								</th>
								<td width="85%" colspan="3" style="height: 95; overflow: auto">
									${userBehaviorLog.logContent}
								</td>
							</tr>
							<tr>
								<th width="15%">
									日志参数
								</th>
								<td width="85%" colspan="3" style="overflow: auto">
									<c:forEach items="${userBehaviorLog.paramList }" var="logParam">
										<span><font style="color: red;">${logParam.paramName}</font>&nbsp;:&nbsp;${logParam.paramValue}&nbsp;</span>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<div class="btn_area_setc">
										<a href="###" class="btn_01"
											onclick="parent.close_win('logWindow');">关闭<b></b> </a>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
