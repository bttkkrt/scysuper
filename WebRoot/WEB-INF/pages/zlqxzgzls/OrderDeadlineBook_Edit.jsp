<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="orderDeadlineBookSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="orderDeadlineBook.id" value="${orderDeadlineBook.id}">
		<input type="hidden" name="orderDeadlineBook.createTime" value="<fmt:formatDate type="both" value="${orderDeadlineBook.createTime}" />">
		<input type="hidden" name="orderDeadlineBook.updateTime" value="${orderDeadlineBook.updateTime}">
		<input type="hidden" name="orderDeadlineBook.createUserID" value="${orderDeadlineBook.createUserID}">
		<input type="hidden" name="orderDeadlineBook.updateUserID" value="${orderDeadlineBook.updateUserID}">
		<input type="hidden" name="orderDeadlineBook.deptId" value="${orderDeadlineBook.deptId}">
		<input type="hidden" name="orderDeadlineBook.delFlag" value="${orderDeadlineBook.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">问题</th>
					<td width="35%"><input name="orderDeadlineBook.problem" value="${orderDeadlineBook.problem}" type="text" maxlength="127"></td>
					<th width="15%">执法人员</th>
					<td width="35%"><input name="orderDeadlineBook.lawOfficer" value="${orderDeadlineBook.lawOfficer}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">整改开始时间</th>
					<td width="35%"><input name="orderDeadlineBook.startTime" value="<fmt:formatDate type='both' value='${orderDeadlineBook.startTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
					<th width="15%">整改结束时间</th>
					<td width="35%"><input name="orderDeadlineBook.endTime" value="<fmt:formatDate type='both' value='${orderDeadlineBook.endTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="orderDeadlineBook.relatedId" value="${orderDeadlineBook.relatedId}" type="text" maxlength="127"></td>
					<th width="15%">修改项</th>
					<td width="35%"><input name="orderDeadlineBook.changeItem" value="${orderDeadlineBook.changeItem}" type="text" maxlength="127"></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_orderDeadlineBook');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
