<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="hmdSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="hmd.id" value="${hmd.id}">
		<input type="hidden" name="hmd.createTime" value="<fmt:formatDate type="both" value="${hmd.createTime}" />">
		<input type="hidden" name="hmd.updateTime" value="${hmd.updateTime}">
		<input type="hidden" name="hmd.createUserID" value="${hmd.createUserID}">
		<input type="hidden" name="hmd.updateUserID" value="${hmd.updateUserID}">
		<input type="hidden" name="hmd.deptId" value="${hmd.deptId}">
		<input type="hidden" name="hmd.delFlag" value="${hmd.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><input name="hmd.areaName" value="${hmd.areaName}" style="width: 60%" type="text" maxlength="127"></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="hmd.companyName" value="${hmd.companyName}" style="width: 60%" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">发生时间</th>
					<td width="35%"><input name="hmd.startTime" value="${hmd.startTime}" style="width: 60%" type="text" maxlength="127"></td>
					<th width="15%">违法违规行为</th>
					<td width="35%"><input name="hmd.illegalActivity" value="${hmd.illegalActivity}" style="width: 60%" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">管理期限</th>
					<td width="35%"><cus:SelectOneTag property="hmd.manageTerm" style="width: 60%" defaultText='请选择' codeName="管理期限" value="${hmd.manageTerm}" /></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
 					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
