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
	<form name="myform1" method="post" enctype="multipart/form-data" action="hearingNoteSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="hearingNote.id" value="${hearingNote.id}">
		<input type="hidden" name="hearingNote.createTime" value="<fmt:formatDate type="both" value="${hearingNote.createTime}" />">
		<input type="hidden" name="hearingNote.updateTime" value="${hearingNote.updateTime}">
		<input type="hidden" name="hearingNote.createUserID" value="${hearingNote.createUserID}">
		<input type="hidden" name="hearingNote.updateUserID" value="${hearingNote.updateUserID}">
		<input type="hidden" name="hearingNote.deptId" value="${hearingNote.deptId}">
		<input type="hidden" name="hearingNote.delFlag" value="${hearingNote.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">听证开始时间</th>
					<td width="35%"><input name="hearingNote.startTime" style="width: 60%" value="<fmt:formatDate type='both' value='${hearingNote.startTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
					<th width="15%">听证结束时间</th>
					<td width="35%"><input name="hearingNote.endTime" style="width: 60%" value="<fmt:formatDate type='both' value='${hearingNote.endTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
				</tr>
				<tr>
					<th width="15%">调查人员</th>
					<td width="35%"><input name="hearingNote.investigator" style="width: 60%" value="${hearingNote.investigator}" type="text" maxlength="127"></td>
					<th width="15%">委托代理人1</th>
					<td width="35%"><input name="hearingNote.attorney1" value="${hearingNote.attorney1}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">委托代理人1性别</th>
					<td width="35%"><cus:SelectOneTag property="hearingNote.attorney1Sex" defaultText='请选择' codeName="性别" value="${hearingNote.attorney1Sex}" /></td>
					<th width="15%">委托代理人1年龄</th>
					<td width="35%"><input name="hearingNote.attorney1Age" value="${hearingNote.attorney1Age}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">委托代理人1工作单位</th>
					<td width="35%"><input name="hearingNote.attorney1Company" value="${hearingNote.attorney1Company}" type="text" maxlength="127"></td>
					<th width="15%">委托代理人2</th>
					<td width="35%"><input name="hearingNote.attorney2" value="${hearingNote.attorney2}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">委托代理人2性别</th>
					<td width="35%"><cus:SelectOneTag property="hearingNote.attorney2Sex" defaultText='请选择' codeName="性别" value="${hearingNote.attorney2Sex}" /></td>
					<th width="15%">委托代理人2年龄</th>
					<td width="35%"><input name="hearingNote.attorney2Age" value="${hearingNote.attorney2Age}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">委托代理人2工作单位</th>
					<td width="35%"><input name="hearingNote.attorney2Company" value="${hearingNote.attorney2Company}" type="text" maxlength="127"></td>
					<th width="15%">第三人</th>
					<td width="35%"><input name="hearingNote.thirdPerson" value="${hearingNote.thirdPerson}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">其他参与人员</th>
					<td width="35%"><input name="hearingNote.otherParticipants" value="${hearingNote.otherParticipants}" type="text" maxlength="127"></td>
					<th width="15%">听证记录</th>
					<td width="35%"><input name="hearingNote.hearingRecord" value="${hearingNote.hearingRecord}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="hearingNote.relatedId" value="${hearingNote.relatedId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_hearingNote');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
