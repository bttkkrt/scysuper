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
				document.myform1.action="gridManagerGxfwSave.action";
				document.myform1.submit();
		}
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="user.id" value="${user.id}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="40%">管辖范围</th>
					<td width="60%"><input name="user.gxfw" value="${user.gxfw}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<td colspan="2" height="100px" style="text-align:center">
						<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">保存</a>&nbsp;
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
