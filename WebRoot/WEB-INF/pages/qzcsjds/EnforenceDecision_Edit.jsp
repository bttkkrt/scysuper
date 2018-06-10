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
	<form name="myform1" method="post" enctype="multipart/form-data" action="enforenceDecisionSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="enforenceDecision.id" value="${enforenceDecision.id}">
		<input type="hidden" name="enforenceDecision.createTime" value="<fmt:formatDate type="both" value="${enforenceDecision.createTime}" />">
		<input type="hidden" name="enforenceDecision.updateTime" value="${enforenceDecision.updateTime}">
		<input type="hidden" name="enforenceDecision.createUserID" value="${enforenceDecision.createUserID}">
		<input type="hidden" name="enforenceDecision.updateUserID" value="${enforenceDecision.updateUserID}">
		<input type="hidden" name="enforenceDecision.deptId" value="${enforenceDecision.deptId}">
		<input type="hidden" name="enforenceDecision.delFlag" value="${enforenceDecision.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">申请单位</th>
					<td width="35%"><input name="enforenceDecision.applyCompany" value="${enforenceDecision.applyCompany}" type="text" maxlength="127"></td>
					<th width="15%">执法依据</th>
					<td width="35%"><input name="enforenceDecision.lawBasic" value="${enforenceDecision.lawBasic}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">措施</th>
					<td width="35%"><input name="enforenceDecision.method" value="${enforenceDecision.method}" type="text" maxlength="127"></td>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="enforenceDecision.relatedId" value="${enforenceDecision.relatedId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">问题</th>
					<td width="35%"><input name="enforenceDecision.problem" value="${enforenceDecision.problem}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_enforenceDecision');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
