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
	<form name="myform1" method="post" enctype="multipart/form-data" action="samplingAssociateSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="samplingAssociate.id" value="${samplingAssociate.id}">
		<input type="hidden" name="samplingAssociate.createTime" value="<fmt:formatDate type="both" value="${samplingAssociate.createTime}" />">
		<input type="hidden" name="samplingAssociate.updateTime" value="${samplingAssociate.updateTime}">
		<input type="hidden" name="samplingAssociate.createUserID" value="${samplingAssociate.createUserID}">
		<input type="hidden" name="samplingAssociate.updateUserID" value="${samplingAssociate.updateUserID}">
		<input type="hidden" name="samplingAssociate.deptId" value="${samplingAssociate.deptId}">
		<input type="hidden" name="samplingAssociate.delFlag" value="${samplingAssociate.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">数量</th>
					<td width="35%"><input name="samplingAssociate.samplingNum" value="${samplingAssociate.samplingNum}" type="text" maxlength="127"></td>
					<th width="15%">规格及批号</th>
					<td width="35%"><input name="samplingAssociate.specificationLot" value="${samplingAssociate.specificationLot}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">关联取证编号</th>
					<td width="35%"><input name="samplingAssociate.forensicId" value="${samplingAssociate.forensicId}" type="text" maxlength="127"></td>
					<th width="15%">证据物品名称</th>
					<td width="35%"><input name="samplingAssociate.evidenceName" value="${samplingAssociate.evidenceName}" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_samplingAssociate');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
