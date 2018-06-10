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
	<form name="myform1" method="post" enctype="multipart/form-data" action="inventoryAssociateSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="inventoryAssociate.id" value="${inventoryAssociate.id}">
		<input type="hidden" name="inventoryAssociate.createTime" value="<fmt:formatDate type="both" value="${inventoryAssociate.createTime}" />">
		<input type="hidden" name="inventoryAssociate.updateTime" value="${inventoryAssociate.updateTime}">
		<input type="hidden" name="inventoryAssociate.createUserID" value="${inventoryAssociate.createUserID}">
		<input type="hidden" name="inventoryAssociate.updateUserID" value="${inventoryAssociate.updateUserID}">
		<input type="hidden" name="inventoryAssociate.deptId" value="${inventoryAssociate.deptId}">
		<input type="hidden" name="inventoryAssociate.delFlag" value="${inventoryAssociate.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">规格型号</th>
					<td width="35%"><input name="inventoryAssociate.specificationModel" value="${inventoryAssociate.specificationModel}" type="text" maxlength="127"></td>
					<th width="15%">产地</th>
					<td width="35%"><input name="inventoryAssociate.originPlace" value="${inventoryAssociate.originPlace}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%"><textarea name="inventoryAssociate.remark" style="width:100%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${inventoryAssociate.remark}</textarea></td>
					<th width="15%">单位</th>
					<td width="35%"><input name="inventoryAssociate.company" value="${inventoryAssociate.company}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">价格</th>
					<td width="35%"><input name="inventoryAssociate.price" value="${inventoryAssociate.price}" type="text" maxlength="127"></td>
					<th width="15%">数量</th>
					<td width="35%"><input name="inventoryAssociate.inventoryNum" value="${inventoryAssociate.inventoryNum}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">证据名称</th>
					<td width="35%"><input name="inventoryAssociate.evidenceName" value="${inventoryAssociate.evidenceName}" type="text" maxlength="127"></td>
					<th width="15%">成色</th>
					<td width="35%"><input name="inventoryAssociate.condition" value="${inventoryAssociate.condition}" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_inventoryAssociate');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
