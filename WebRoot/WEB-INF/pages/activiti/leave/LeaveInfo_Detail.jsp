<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
	<div class="page_dialog">
		<form name="myform" method="post">
			<div class="cell">
				<table width="100%">
					<tr>
						<th width="15%">请假开始时间</th>
						<td width="35%" ><fmt:formatDate type="both" value="${leaveInfo.starttime}" /></td>
						<th width="15%">请假结束时间</th>
						<td width="35%" ><fmt:formatDate type="both" value="${leaveInfo.endtime}" /></td>
					</tr>
					<tr>
						<th width="15%">用户ID</th>
						<td width="35%" >${leaveInfo.userId}</td>
						<th width="15%">请假原因</th>
						<td width="35%" >${leaveInfo.reason}</td>
					</tr>
					<tr>
						<th width="">申请时间</th>
						<td width="" ><fmt:formatDate type="both" value="${leaveInfo.applyTime}" /></td>
						<th width="">流程实例ID</th>
						<td width="" >${leaveInfo.processInstanceId}</td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td colspan="4">
							<div class="btn_area_setc">
								<a href="###" class="btn_01" onclick="parent.close_win('win_leaveInfo');">关闭<b></b></a>
							</div>
						</td>
					</tr>						
				</table>
			</div>
		</form>
	</div>
</body>
</html>
