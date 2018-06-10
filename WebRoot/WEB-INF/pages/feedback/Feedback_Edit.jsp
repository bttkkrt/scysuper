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
	<form name="myform1" method="post" enctype="multipart/form-data" action="feedbackSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="feedback.id" value="${feedback.id}">
		<input type="hidden" name="feedback.createTime" value="<fmt:formatDate type="both" value="${feedback.createTime}" />">
		<input type="hidden" name="feedback.updateTime" value="${feedback.updateTime}">
		<input type="hidden" name="feedback.createUserID" value="${feedback.createUserID}">
		<input type="hidden" name="feedback.updateUserID" value="${feedback.updateUserID}">
		<input type="hidden" name="feedback.deptId" value="${feedback.deptId}">
		<input type="hidden" name="feedback.delFlag" value="${feedback.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">意见反馈</th>
					<td width="35%"><input name="feedback.content" style="width: 60%" value="${feedback.content}" type="text" datatype="*1-2000" errormsg='此项为必填' maxlength="2000"><font style='color:red'>*</font></td>
					<th width="15%">用户姓名</th>
					<td width="35%"><input name="feedback.userName" style="width: 60%" value="${feedback.userName}" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="document.myform1.reset()">重置<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_feedback');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
