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
				
				document.myform1.action="behaviorLogParamSave.action";
				document.myform1.submit();
			}
		}
	</script>
	
</head>
<body>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="behaviorLogParam.id" value="${behaviorLogParam.id}">
		<input type="hidden" name="behaviorLogParam.createTime" value="<fmt:formatDate type="both" value="${behaviorLogParam.createTime}" />">
		<input type="hidden" name="behaviorLogParam.updateTime" value="${behaviorLogParam.updateTime}">
		<input type="hidden" name="behaviorLogParam.createUserID" value="${behaviorLogParam.createUserID}">
		<input type="hidden" name="behaviorLogParam.updateUserID" value="${behaviorLogParam.updateUserID}">
		<input type="hidden" name="behaviorLogParam.deptId" value="${behaviorLogParam.deptId}">
		<input type="hidden" name="behaviorLogParam.delFlag" value="${behaviorLogParam.delFlag}">
		
		<div class="submitdata" style="width:100%;height:100%">
			<table width="100%" border="0">
				<tr>
					<th width="15%">参数名称</th>
					<td width="35%"><input name="behaviorLogParam.paramName" value="${behaviorLogParam.paramName}" type="text" maxlength="30"></td>
					<th width="15%">参数值</th>
					<td width="35%"><input name="behaviorLogParam.paramValue" value="${behaviorLogParam.paramValue}" type="text" maxlength="500"></td>
				</tr>
				<tr>
					<th width="15%">日志</th>
					<td width="35%"><input name="behaviorLogParam.logId" value="${behaviorLogParam.logId}" type="text" maxlength="32"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win('paramWindow');">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
