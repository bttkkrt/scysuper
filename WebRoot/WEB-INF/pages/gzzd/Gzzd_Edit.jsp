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
				
				document.myform1.action="gzzdSave.action";
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
		<input type="hidden" name="gzzd.id" value="${gzzd.id}">
		<input type="hidden" name="gzzd.createTime" value="<fmt:formatDate type="both" value="${gzzd.createTime}" />">
		<input type="hidden" name="gzzd.updateTime" value="${gzzd.updateTime}">
		<input type="hidden" name="gzzd.createUserID" value="${gzzd.createUserID}">
		<input type="hidden" name="gzzd.updateUserID" value="${gzzd.updateUserID}">
		<input type="hidden" name="gzzd.deptId" value="${gzzd.deptId}">
		<input type="hidden" name="gzzd.delFlag" value="${gzzd.delFlag}">
		
		<input type="hidden" name="gzzd.szzname" value="${gzzd.szzname}">
		<input type="hidden" name="gzzd.qymc" value="${gzzd.qymc}">
		<input type="hidden" name="gzzd.szzid" value="${gzzd.szzid}">
		<input type="hidden" name="gzzd.qyid" value="${gzzd.qyid}">
		<input type="hidden" name="gzzd.qylx" value="${gzzd.qylx}">
		<input type="hidden" name="gzzd.hyfl" value="${gzzd.hyfl}">
		<input type="hidden" name="gzzd.qygm" value="${gzzd.qygm}">
		<input type="hidden" name="gzzd.qyzclx" value="${gzzd.qyzclx}">
		<input type="hidden" name="gzzd.szc" value="${gzzd.szc}">
		<input type="hidden" name="gzzd.szcname" value="${gzzd.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="10%">档案</th>
					<td width="90%"><cus:hxcheckbox property="gzzd.dalx" codeName="档案类型" value="${gzzd.dalx}" /></td>
				</tr>
				<tr>
					<th width="10%">制度</th>
					<td width="90%"><cus:hxcheckbox property="gzzd.zdlx" codeName="制度类型" value="${gzzd.zdlx}" /></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="2" height="100px" style="text-align:center;">
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
