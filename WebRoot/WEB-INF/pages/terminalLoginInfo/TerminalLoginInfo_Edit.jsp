<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	
	<script src="<c:url value='/webResources/My97DatePicker/WdatePicker.js' />"></script>
	<script src="<c:url value='/webResources/js/docorder.js' />"></script>
	<script src="<c:url value='/webResources/js/validator.js' />"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value='/webResources/themes/${curr_user.cssId}/css/style.css' />" />
	<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src='<c:url value="/webResources/js/easyui/jquery.easyui.min.js"/>'></script>
	<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/js/easyui/themes/default/easyui.css"/>'> 
	<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/js/easyui/themes/icon.css"/>'>
	<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>  
	<script src="<c:url value='/webResources/js/common.js' />"></script>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="terminalLoginInfoSave.action";
				document.myform1.submit();
			}
		}
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="terminalLoginInfo.id" value="${terminalLoginInfo.id}">
		<input type="hidden" name="terminalLoginInfo.createTime" value="<fmt:formatDate type="both" value="${terminalLoginInfo.createTime}" />">
		<input type="hidden" name="terminalLoginInfo.updateTime" value="${terminalLoginInfo.updateTime}">
		<input type="hidden" name="terminalLoginInfo.createUserID" value="${terminalLoginInfo.createUserID}">
		<input type="hidden" name="terminalLoginInfo.updateUserID" value="${terminalLoginInfo.updateUserID}">
		<input type="hidden" name="terminalLoginInfo.deptId" value="${terminalLoginInfo.deptId}">
		<input type="hidden" name="terminalLoginInfo.delFlag" value="${terminalLoginInfo.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">用户名</th>
					<td width="35%"><input name="terminalLoginInfo.userName" value="${terminalLoginInfo.userName}" type="text" maxlength="20"></td>
					<th width="15%">所属部门</th>
					<td width="35%"><input name="terminalLoginInfo.deptname" value="${terminalLoginInfo.deptname}" type="text" maxlength="20"></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center;">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
							<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
