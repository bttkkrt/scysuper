<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="gridManagerSave.action";
				document.myform1.submit();
			}
		}
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="gridManager.id" value="${gridManager.id}">
		<input type="hidden" name="gridManager.createTime" value="<fmt:formatDate type="both" value="${gridManager.createTime}" />">
		<input type="hidden" name="gridManager.updateTime" value="${gridManager.updateTime}">
		<input type="hidden" name="gridManager.createUserID" value="${gridManager.createUserID}">
		<input type="hidden" name="gridManager.updateUserID" value="${gridManager.updateUserID}">
		<input type="hidden" name="gridManager.deptId" value="${gridManager.deptId}">
		<input type="hidden" name="gridManager.delFlag" value="${gridManager.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">上级责任人名称</th>
					<td width="35%"><input name="gridManager.upUsername" value="${gridManager.upUsername}" type="text" maxlength="255"></td>
					<th width="15%">上级责任人id</th>
					<td width="35%"><input name="gridManager.upUserid" value="${gridManager.upUserid}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">下级责任人名称</th>
					<td width="35%"><input name="gridManager.downUsername" value="${gridManager.downUsername}" type="text" maxlength="255"></td>
					<th width="15%">下级责任人id</th>
					<td width="35%"><input name="gridManager.downUserid" value="${gridManager.downUserid}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">类型</th>
					<td width="35%"><input name="gridManager.gridType" value="${gridManager.gridType}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
