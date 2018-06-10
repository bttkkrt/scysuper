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
	<form name="myform1" method="post" enctype="multipart/form-data" action="hearingNoticeSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="hearingNotice.id" value="${hearingNotice.id}">
		<input type="hidden" name="hearingNotice.createTime" value="<fmt:formatDate type="both" value="${hearingNotice.createTime}" />">
		<input type="hidden" name="hearingNotice.updateTime" value="${hearingNotice.updateTime}">
		<input type="hidden" name="hearingNotice.createUserID" value="${hearingNotice.createUserID}">
		<input type="hidden" name="hearingNotice.updateUserID" value="${hearingNotice.updateUserID}">
		<input type="hidden" name="hearingNotice.deptId" value="${hearingNotice.deptId}">
		<input type="hidden" name="hearingNotice.delFlag" value="${hearingNotice.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">听证会时间</th>
					<td width="35%"><input name="hearingNotice.hearingTime" value="<fmt:formatDate type='both' value='${hearingNotice.hearingTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
					<th width="15%">书记员</th>
					<td width="35%"><input name="hearingNotice.clerk" value="${hearingNotice.clerk}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">是否公开</th>
					<td width="35%"><cus:SelectOneTag property="hearingNotice.publicCondition" defaultText='请选择' codeName="是或否" value="${hearingNotice.publicCondition}" /></td>
					<th width="15%">听证主持人</th>
					<td width="35%"><input name="hearingNotice.hearingChairperson" value="${hearingNotice.hearingChairperson}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">听证员</th>
					<td width="35%"><input name="hearingNotice.hearingOfficer" value="${hearingNotice.hearingOfficer}" type="text" maxlength="127"></td>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="hearingNotice.relatedId" value="${hearingNotice.relatedId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">听证地点</th>
					<td width="35%"><input name="hearingNotice.hearingAddress" value="${hearingNotice.hearingAddress}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_hearingNotice');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
