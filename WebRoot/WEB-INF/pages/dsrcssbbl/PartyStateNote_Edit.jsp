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
	<form name="myform1" method="post" enctype="multipart/form-data" action="partyStateNoteSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="partyStateNote.id" value="${partyStateNote.id}">
		<input type="hidden" name="partyStateNote.createTime" value="<fmt:formatDate type="both" value="${partyStateNote.createTime}" />">
		<input type="hidden" name="partyStateNote.updateTime" value="${partyStateNote.updateTime}">
		<input type="hidden" name="partyStateNote.createUserID" value="${partyStateNote.createUserID}">
		<input type="hidden" name="partyStateNote.updateUserID" value="${partyStateNote.updateUserID}">
		<input type="hidden" name="partyStateNote.deptId" value="${partyStateNote.deptId}">
		<input type="hidden" name="partyStateNote.delFlag" value="${partyStateNote.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">陈述开始时间</th>
					<td width="35%"><input name="partyStateNote.startTime" value="<fmt:formatDate type='both' value='${partyStateNote.startTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
					<th width="15%">陈述结束时间</th>
					<td width="35%"><input name="partyStateNote.endTime" value="<fmt:formatDate type='both' value='${partyStateNote.endTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
				</tr>
				<tr>
					<th width="15%">陈述地点</th>
					<td width="35%"><input name="partyStateNote.stateAddress" value="${partyStateNote.stateAddress}" type="text" maxlength="127"></td>
					<th width="15%">陈述申辩人</th>
					<td width="35%"><input name="partyStateNote.stateDefense" value="${partyStateNote.stateDefense}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">陈述申辩记录</th>
					<td width="35%"><input name="partyStateNote.stateRecord" value="${partyStateNote.stateRecord}" type="text" maxlength="127"></td>
					<th width="15%">职务</th>
					<td width="35%"><input name="partyStateNote.position" value="${partyStateNote.position}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">电话</th>
					<td width="35%"><input name="partyStateNote.tele" value="${partyStateNote.tele}" type="text" maxlength="127"></td>
					<th width="15%">联系地址</th>
					<td width="35%"><input name="partyStateNote.address" value="${partyStateNote.address}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">邮编</th>
					<td width="35%"><input name="partyStateNote.zipCode" value="${partyStateNote.zipCode}" type="text" maxlength="127"></td>
					<th width="15%">承办人</th>
					<td width="35%"><input name="partyStateNote.undertaker" value="${partyStateNote.undertaker}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">记录人</th>
					<td width="35%"><input name="partyStateNote.recorder" value="${partyStateNote.recorder}" type="text" maxlength="127"></td>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="partyStateNote.relatedId" value="${partyStateNote.relatedId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">性别</th>
					<td width="35%"><cus:SelectOneTag property="partyStateNote.sex" defaultText='请选择' codeName="性别" value="${partyStateNote.sex}" /></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_partyStateNote');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
