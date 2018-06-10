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
				
				document.myform1.action="wxhxpSave.action";
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
		<input type="hidden" name="wxhxp.id" value="${wxhxp.id}">
		<input type="hidden" name="wxhxp.createTime" value="<fmt:formatDate type="both" value="${wxhxp.createTime}" />">
		<input type="hidden" name="wxhxp.updateTime" value="${wxhxp.updateTime}">
		<input type="hidden" name="wxhxp.createUserID" value="${wxhxp.createUserID}">
		<input type="hidden" name="wxhxp.updateUserID" value="${wxhxp.updateUserID}">
		<input type="hidden" name="wxhxp.deptId" value="${wxhxp.deptId}">
		<input type="hidden" name="wxhxp.delFlag" value="${wxhxp.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">危险货物编号</th>
					<td width="35%"><input name="wxhxp.whpnum" value="${wxhxp.whpnum}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
					<th width="15%">名称</th>
					<td width="35%"><input name="wxhxp.whpname" value="${wxhxp.whpname}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">别名</th>
					<td width="35%"><input name="wxhxp.bname" value="${wxhxp.bname}" type="text" maxlength="255"></td>
					<th width="15%">UN号</th>
					<td width="35%"><input name="wxhxp.unnum" value="${wxhxp.unnum}" type="text" maxlength="255"></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
