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
				
				document.myform1.action="dailyinspectionSave.action";
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
		<input type="hidden" name="dailyinspection.id" value="${dailyinspection.id}">
		<input type="hidden" name="dailyinspection.createTime" value="<fmt:formatDate type="both" value="${dailyinspection.createTime}" />">
		<input type="hidden" name="dailyinspection.updateTime" value="${dailyinspection.updateTime}">
		<input type="hidden" name="dailyinspection.createUserID" value="${dailyinspection.createUserID}">
		<input type="hidden" name="dailyinspection.updateUserID" value="${dailyinspection.updateUserID}">
		<input type="hidden" name="dailyinspection.deptId" value="${dailyinspection.deptId}">
		<input type="hidden" name="dailyinspection.delFlag" value="${dailyinspection.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">公司名称</th>
					<td width="35%"><input name="dailyinspection.comname" value="${dailyinspection.comname}" type="text" maxlength="255"></td>
					<th width="15%">所属街道</th>
					<td width="35%"><cus:SelectOneTag property="dailyinspection.szz" defaultText='请选择' codeName="相城地址" value="${dailyinspection.szz}" /></td>
				</tr>
				<tr>
					<th width="15%">公司id</th>
					<td width="35%"><input name="dailyinspection.comid" value="${dailyinspection.comid}" type="text" maxlength="255"></td>
					<th width="15%">备注</th>
					<td width="35%"><textarea name="dailyinspection.remark" style="width:100%;height:120px">${dailyinspection.remark}</textarea></td>
				</tr>
				<tr>
					<th width="15%">关联图片id</th>
					<td width="35%" colspan="3"><input name="dailyinspection.linkid" value="${dailyinspection.linkid}" type="text" maxlength="255"></td>
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
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
