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
	<form name="myform1" method="post" enctype="multipart/form-data" action="inquestRecordSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="inquestRecord.id" value="${inquestRecord.id}">
		<input type="hidden" name="inquestRecord.createTime" value="<fmt:formatDate type="both" value="${inquestRecord.createTime}" />">
		<input type="hidden" name="inquestRecord.updateTime" value="${inquestRecord.updateTime}">
		<input type="hidden" name="inquestRecord.createUserID" value="${inquestRecord.createUserID}">
		<input type="hidden" name="inquestRecord.updateUserID" value="${inquestRecord.updateUserID}">
		<input type="hidden" name="inquestRecord.deptId" value="${inquestRecord.deptId}">
		<input type="hidden" name="inquestRecord.delFlag" value="${inquestRecord.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">勘验开始时间</th>
					<td width="35%"><input name="inquestRecord.startTime" value="<fmt:formatDate type='both' value='${inquestRecord.startTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
					<th width="15%">勘验结束时间</th>
					<td width="35%"><input name="inquestRecord.endTime" value="<fmt:formatDate type='both' value='${inquestRecord.endTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
				</tr>
				<tr>
					<th width="15%">勘验场所</th>
					<td width="35%"><input name="inquestRecord.inquestAddress" value="${inquestRecord.inquestAddress}" type="text" maxlength="127"></td>
					<th width="15%">天气情况</th>
					<td width="35%"><input name="inquestRecord.weatherCondition" value="${inquestRecord.weatherCondition}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">当事人1</th>
					<td width="35%"><input name="inquestRecord.party1" value="${inquestRecord.party1}" type="text" maxlength="127"></td>
					<th width="15%">当事人1单位及职务</th>
					<td width="35%"><input name="inquestRecord.party1Company" value="${inquestRecord.party1Company}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">当事人2</th>
					<td width="35%"><input name="inquestRecord.party2" value="${inquestRecord.party2}" type="text" maxlength="127"></td>
					<th width="15%">当事人2单位及职务</th>
					<td width="35%"><input name="inquestRecord.party2Company" value="${inquestRecord.party2Company}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">被邀请人</th>
					<td width="35%"><input name="inquestRecord.invitee" value="${inquestRecord.invitee}" type="text" maxlength="127"></td>
					<th width="15%">被邀请人单位及职务</th>
					<td width="35%"><input name="inquestRecord.inviteeCompany" value="${inquestRecord.inviteeCompany}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">记录人</th>
					<td width="35%"><input name="inquestRecord.recordPerson" value="${inquestRecord.recordPerson}" type="text" maxlength="127"></td>
					<th width="15%">记录人单位及职务</th>
					<td width="35%"><input name="inquestRecord.recordCompany" value="${inquestRecord.recordCompany}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">勘验情况</th>
					<td width="35%"><input name="inquestRecord.inquestCondition" value="${inquestRecord.inquestCondition}" type="text" maxlength="127"></td>
					<th width="15%">勘验人</th>
					<td width="35%"><input name="inquestRecord.inquestPerson" value="${inquestRecord.inquestPerson}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="inquestRecord.relatedId" value="${inquestRecord.relatedId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_inquestRecord');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
