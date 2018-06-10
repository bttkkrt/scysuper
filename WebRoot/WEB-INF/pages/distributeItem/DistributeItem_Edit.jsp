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
				
				document.myform1.action="distributeItemSave.action";
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
		<input type="hidden" name="distributeItem.id" value="${distributeItem.id}">
		<input type="hidden" name="distributeItem.createTime" value="<fmt:formatDate type="both" value="${distributeItem.createTime}" />">
		<input type="hidden" name="distributeItem.updateTime" value="${distributeItem.updateTime}">
		<input type="hidden" name="distributeItem.createUserID" value="${distributeItem.createUserID}">
		<input type="hidden" name="distributeItem.updateUserID" value="${distributeItem.updateUserID}">
		<input type="hidden" name="distributeItem.deptId" value="${distributeItem.deptId}">
		<input type="hidden" name="distributeItem.delFlag" value="${distributeItem.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">分配记录标识</th>
					<td width="35%"><input name="distributeItem.distributeId" value="${distributeItem.distributeId}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
					<th width="15%">巡检项</th>
					<td width="35%"><input name="distributeItem.item" value="${distributeItem.item}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">巡检要求</th>
					<td width="35%"><input name="distributeItem.requirement" value="${distributeItem.requirement}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
					<th width="15%">是否已检查标识</th>
					<td width="35%"><input name="distributeItem.isinspect" value="${distributeItem.isinspect}" type="text" dataType="Require" msg="此项为必填" maxlength="5"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">检测上传图片</th>
					<td width="35%"><input name="distributeItem.image" value="${distributeItem.image}" type="text" dataType="Require" msg="此项为必填" maxlength="2000"><font color="red">*</font></td>
					<th width="15%">不合格数量</th>
					<td width="35%"><input name="distributeItem.count" value="${distributeItem.count}" type="text" dataType="Require" msg="此项为必填" maxlength="2000"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">检测备注</th>
					<td width="35%"><input name="distributeItem.remark" value="${distributeItem.remark}" type="text" dataType="Require" msg="此项为必填" maxlength="500"><font color="red">*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align: center;">
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