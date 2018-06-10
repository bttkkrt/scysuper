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
				
				document.myform1.action="carequipmentSave.action";
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
		<input type="hidden" name="carequipment.id" value="${carequipment.id}">
		<input type="hidden" name="carequipment.createTime" value="<fmt:formatDate type="both" value="${carequipment.createTime}" />">
		<input type="hidden" name="carequipment.updateTime" value="${carequipment.updateTime}">
		<input type="hidden" name="carequipment.createUserID" value="${carequipment.createUserID}">
		<input type="hidden" name="carequipment.updateUserID" value="${carequipment.updateUserID}">
		<input type="hidden" name="carequipment.deptId" value="${carequipment.deptId}">
		<input type="hidden" name="carequipment.delFlag" value="${carequipment.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">企业/机构名称</th>
					<td width="35%"><input name="carequipment.companyname" value="${carequipment.companyname}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
					<th width="15%">视频名称</th>
					<td width="35%"><input name="carequipment.detailname" value="${carequipment.detailname}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">GUID</th>
					<td width="35%"><input name="carequipment.guid" value="${carequipment.guid}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
					<th width="15%">PUID</th>
					<td width="35%"><input name="carequipment.puid" value="${carequipment.puid}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">STREAMID</th>
					<td width="35%"><input name="carequipment.streamid" value="${carequipment.streamid}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px"  style="text-align: center;">
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
