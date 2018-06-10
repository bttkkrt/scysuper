<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="/common/jsLib.jsp"%>
</head>

<body validform="true">
	<div class="page_dialog">
		<form name="myform" method="post" enctype="multipart/form-data" action="leaveInfoSave.action">
			<s:token />
			<input type="hidden" name="flag" value="${flag}">
			<input type="hidden" name="leaveInfo.id" value="${leaveInfo.id}">
			<input type="hidden" name="leaveInfo.createTime" value="<fmt:formatDate type="both" value="${leaveInfo.createTime}" />">
			<input type="hidden" name="leaveInfo.updateTime" value="${leaveInfo.updateTime}">
			<input type="hidden" name="leaveInfo.createUserID" value="${leaveInfo.createUserID}">
			<input type="hidden" name="leaveInfo.updateUserID" value="${leaveInfo.updateUserID}">
			<input type="hidden" name="leaveInfo.deptId" value="${leaveInfo.deptId}">
			<input type="hidden" name="leaveInfo.delFlag" value="${leaveInfo.delFlag}">
			
			<div class="inner6px">
				<div class="cell">
					<table width="100%">
						<tr>
							<th width="">请假开始时间</th>
							<td width=""><input name="leaveInfo.starttime" value="<fmt:formatDate type='both' value='${leaveInfo.starttime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
							<th width="">请假结束时间</th>
							<td width=""><input name="leaveInfo.endtime" value="<fmt:formatDate type='both' value='${leaveInfo.endtime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
						</tr>
						<tr>
							<th width="">用户ID</th>
							<td width=""><input name="leaveInfo.userId" value="${leaveInfo.userId}" type="text" maxlength="32"></td>
							<th width="">请假原因</th>
							<td width=""><input name="leaveInfo.reason" value="${leaveInfo.reason}" type="text" maxlength="32"></td>
						</tr>
						<tr>
							<th width="">申请时间</th>
							<td width=""><input name="leaveInfo.applyTime" value="<fmt:formatDate type='both' value='${leaveInfo.applyTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
							<th width="">流程实例ID</th>
							<td width=""><input name="leaveInfo.processInstanceId" value="${leaveInfo.processInstanceId}" type="text" maxlength="64"></td>
						</tr>
						<tr>
						</tr>
						<tr>
							<td colspan="4">
								<div class="btn_area_setc">
									<s:if test="flag=='add'">
										<a href="#" class="btn_01" type="submit">添加<b></b>
										</a>
									</s:if>
									<s:else>
										<a href="#" class="btn_01" type="submit">更新<b></b>
										</a>
									</s:else>
									</a>
									<a href="#" class="btn_01"
										onclick="parent.close_win('win_leaveInfo');">关闭<b></b>
									</a>
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
