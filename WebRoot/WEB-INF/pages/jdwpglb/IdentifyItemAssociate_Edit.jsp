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
	<form name="myform1" method="post" enctype="multipart/form-data" action="identifyItemAssociateSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="identifyItemAssociate.id" value="${identifyItemAssociate.id}">
		<input type="hidden" name="identifyItemAssociate.createTime" value="<fmt:formatDate type="both" value="${identifyItemAssociate.createTime}" />">
		<input type="hidden" name="identifyItemAssociate.updateTime" value="${identifyItemAssociate.updateTime}">
		<input type="hidden" name="identifyItemAssociate.createUserID" value="${identifyItemAssociate.createUserID}">
		<input type="hidden" name="identifyItemAssociate.updateUserID" value="${identifyItemAssociate.updateUserID}">
		<input type="hidden" name="identifyItemAssociate.deptId" value="${identifyItemAssociate.deptId}">
		<input type="hidden" name="identifyItemAssociate.delFlag" value="${identifyItemAssociate.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">备注</th>
					<td width="35%"><textarea name="identifyItemAssociate.remark" style="width:100%;height:120px">${identifyItemAssociate.remark}</textarea></td>
					<th width="15%">规格型号</th>
					<td width="35%"><input name="identifyItemAssociate.specificationModel" value="${identifyItemAssociate.specificationModel}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">数量</th>
					<td width="35%"><input name="identifyItemAssociate.identifyNum" value="${identifyItemAssociate.identifyNum}" type="text" maxlength="127"></td>
					<th width="15%">关联委托书编号</th>
					<td width="35%"><input name="identifyItemAssociate.attorenyId" value="${identifyItemAssociate.attorenyId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">物品名称</th>
					<td width="35%"><input name="identifyItemAssociate.itemName" value="${identifyItemAssociate.itemName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_identifyItemAssociate');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
