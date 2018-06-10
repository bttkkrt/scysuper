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
				
				document.myform1.action="reviewLogSave.action";
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
		<input type="hidden" name="reviewLog.id" value="${reviewLog.id}">
		<input type="hidden" name="reviewLog.createTime" value="<fmt:formatDate type="both" value="${reviewLog.createTime}" />">
		<input type="hidden" name="reviewLog.updateTime" value="${reviewLog.updateTime}">
		<input type="hidden" name="reviewLog.createUserID" value="${reviewLog.createUserID}">
		<input type="hidden" name="reviewLog.updateUserID" value="${reviewLog.updateUserID}">
		<input type="hidden" name="reviewLog.deptId" value="${reviewLog.deptId}">
		<input type="hidden" name="reviewLog.delFlag" value="${reviewLog.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">审核项目id</th>
					<td width="35%"><input name="reviewLog.itemId" value="${reviewLog.itemId}" type="text" dataType="Require" msg="此项为必填" maxlength="32"><font color="red">*</font></td>
					<th width="15%">审核项目类型</th>
					<td width="35%"><input name="reviewLog.itemType" value="${reviewLog.itemType}" type="text" dataType="Require" msg="此项为必填" maxlength="32"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">审核状态</th>
					<td width="35%"><input name="reviewLog.state" value="${reviewLog.state}" type="text" dataType="Require" msg="此项为必填" maxlength="3"><font color="red">*</font></td>
					<th width="15%">审核岗位标记</th>
					<td width="35%"><input name="reviewLog.dutyFlag" value="${reviewLog.dutyFlag}" type="text" dataType="Require" msg="此项为必填" maxlength="3"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">审核人Id</th>
					<td width="35%"><input name="reviewLog.userId" value="${reviewLog.userId}" type="text" dataType="Require" msg="此项为必填" maxlength="32"><font color="red">*</font></td>
					<th width="15%">审核人名称</th>
					<td width="35%"><input name="reviewLog.userName" value="${reviewLog.userName}" type="text" maxlength="32"></td>
				</tr>
				<tr>
					<th width="15%">审核人部门编码</th>
					<td width="35%"><input name="reviewLog.userDeptCode" value="${reviewLog.userDeptCode}" type="text" maxlength="255"></td>
					<th width="15%">审核开始时间</th>
					<td width="35%"><input name="reviewLog.startTime" value="<fmt:formatDate type='both' value='${reviewLog.startTime}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
				</tr>
				<tr>
					<th width="15%">审核结束时间</th>
					<td width="35%"><input name="reviewLog.endTime" value="<fmt:formatDate type='both' value='${reviewLog.endTime}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
					<th width="15%">审核记录</th>
					<td width="35%"><input name="reviewLog.record" value="${reviewLog.record}" type="text" maxlength="4000"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%"><input name="reviewLog.remark" value="${reviewLog.remark}" type="text" maxlength="4000"></td>
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
