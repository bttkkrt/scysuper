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
				
				document.myform1.action="inspectItemSave.action";
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
		<input type="hidden" name="inspectItem.id" value="${inspectItem.id}">
		<input type="hidden" name="inspectItem.createTime" value="<fmt:formatDate type="both" value="${inspectItem.createTime}" />">
		<input type="hidden" name="inspectItem.updateTime" value="${inspectItem.updateTime}">
		<input type="hidden" name="inspectItem.createUserID" value="${inspectItem.createUserID}">
		<input type="hidden" name="inspectItem.updateUserID" value="${inspectItem.updateUserID}">
		<input type="hidden" name="inspectItem.deptId" value="${inspectItem.deptId}">
		<input type="hidden" name="inspectItem.delFlag" value="${inspectItem.delFlag}">
		
		<input type="hidden" name="inspectItem.companyFlag" value="${inspectItem.companyFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%" >安全检测类型</th>
					<td width="35%" colspan="3" >
						<cus:SelectOneTag property="inspectItem.inspectType" defaultText='请选择' codeName="安全检测类型" value="${inspectItem.inspectType}" dataType="Require" msg="此项为必选" /><font color="red">*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">巡检项</th>
					<td width="35%" colspan="3" ><input name="inspectItem.item" value="${inspectItem.item}" type="text" dataType="Require" msg="此项为必填" maxlength="25"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">巡检要求</th>
					<td width="35%" colspan="3" >
						<textarea rows="3" cols="50" name="inspectItem.requirement" dataType="Require" msg="此项为必填" maxlength="250">${inspectItem.requirement}</textarea><font color="red">*</font>
					</td>
				</tr>
				<tr>
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
