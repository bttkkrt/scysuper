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
				
				document.myform1.action="jdhxpSave.action";
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
		<input type="hidden" name="jdhxp.id" value="${jdhxp.id}">
		<input type="hidden" name="jdhxp.createTime" value="<fmt:formatDate type="both" value="${jdhxp.createTime}" />">
		<input type="hidden" name="jdhxp.updateTime" value="${jdhxp.updateTime}">
		<input type="hidden" name="jdhxp.createUserID" value="${jdhxp.createUserID}">
		<input type="hidden" name="jdhxp.updateUserID" value="${jdhxp.updateUserID}">
		<input type="hidden" name="jdhxp.deptId" value="${jdhxp.deptId}">
		<input type="hidden" name="jdhxp.delFlag" value="${jdhxp.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">化学名</th>
					<td width="35%"><input name="jdhxp.hxname" value="${jdhxp.hxname}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
					<th width="15%">别名</th>
					<td width="35%"><input name="jdhxp.bname" value="${jdhxp.bname}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">CAS号</th>
					<td width="35%"><input name="jdhxp.casname" value="${jdhxp.casname}" type="text" maxlength="255"></td>
					<th width="15%">UN号</th>
					<td width="35%"><input name="jdhxp.unname" value="${jdhxp.unname}" type="text" maxlength="255"></td>
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
