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
	<form name="myform1" method="post" enctype="multipart/form-data" action="inventoryDecisionSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="inventoryDecision.id" value="${inventoryDecision.id}">
		<input type="hidden" name="inventoryDecision.createTime" value="<fmt:formatDate type="both" value="${inventoryDecision.createTime}" />">
		<input type="hidden" name="inventoryDecision.updateTime" value="${inventoryDecision.updateTime}">
		<input type="hidden" name="inventoryDecision.createUserID" value="${inventoryDecision.createUserID}">
		<input type="hidden" name="inventoryDecision.updateUserID" value="${inventoryDecision.updateUserID}">
		<input type="hidden" name="inventoryDecision.deptId" value="${inventoryDecision.deptId}">
		<input type="hidden" name="inventoryDecision.delFlag" value="${inventoryDecision.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">机关负责人意见</th>
					<td width="35%"><input name="inventoryDecision.officeComment" value="${inventoryDecision.officeComment}" type="text" maxlength="127"></td>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="inventoryDecision.relatedId" value="${inventoryDecision.relatedId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">处理</th>
					<td width="35%"><input name="inventoryDecision.deal" value="${inventoryDecision.deal}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_inventoryDecision');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
