<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/jsLib.jsp"%>
	</head>
	<body validform="true">
		<div class="page_dialog">
				<div class="inner6px">
					<div class="cell">
						<form name="myform" method="post" action="functionPointSave.action">
							<s:token />
							<input type="hidden" name="flag" 					   value="${flag}">
							<input type="hidden" name="functionPoint.id" 		   value="${functionPoint.id}">
							<input type="hidden" name="functionPoint.createTime"   value="<fmt:formatDate type='both' value='${functionPoint.createTime}' />">
							<input type="hidden" name="functionPoint.updateTime"   value="${functionPoint.updateTime}">
							<input type="hidden" name="functionPoint.createUserID" value="${functionPoint.createUserID}">
							<input type="hidden" name="functionPoint.updateUserID" value="${functionPoint.updateUserID}">
							<input type="hidden" name="functionPoint.deptId" 	   value="${functionPoint.deptId}">
							<input type="hidden" name="functionPoint.delFlag" 	   value="${functionPoint.delFlag}">
							<table>
								<tr>
									<th width="25%">功能点名称<font color="red">*</font></th>
									<td width="75%"><input name="functionPoint.funcName" value="${functionPoint.funcName}" datatype="*1-30"></td>
								</tr>
								<tr>
									<th width="15%">权限表达式<font color="red">*</font></th>
									<td width="35%"><input name="functionPoint.funcPermission" value="${functionPoint.funcPermission}" datatype="*1-100"></td>
								</tr>
								<!--  
								<tr>
									<th width="">是否绑定模块</th>
									<td width=""><cus:SelectOneTag property="functionPoint.isBandingModule" defaultText='请选择' codeName="是或否" value="${functionPoint.isBandingModule}" /></td>
								</tr>
								<tr>
									<th width="">绑定模块</th>
									<td width="">
									<span id="moduleName">${functionPoint.moduleName}</span>
									<input type="button" class="btn" value="选择" onclick="chooseModule()">
									<input type="hidden" name="functionPoint.moduleId" id="moduleId" value="${functionPoint.module.moduleCode}" maxlength="40"></td>
								</tr>
								-->
								<tr>
									<td colspan="4">
										<div class="btn_area_setc">
											<a href="###" class="btn_01_mini" type="submit">保存<b></b></a>
											<a href="###" class="btn_01_mini" onclick="clear_form(document.myform);">取消<b></b></a>
											<a href="###" class="btn_01_mini" onclick="parent.close_win('win_functionPoint');">关闭<b></b></a>
										</div>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
		</div>
	</body>
</html>
