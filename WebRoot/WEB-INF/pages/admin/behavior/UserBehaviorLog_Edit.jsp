<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="userBehaviorLogSave.action";
				document.myform1.submit();
			}
		}
	</script>
	
</head>
<body>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="userBehaviorLog.id" value="${userBehaviorLog.id}">
		<input type="hidden" name="userBehaviorLog.createTime" value="<fmt:formatDate type="both" value="${userBehaviorLog.createTime}" />">
		<input type="hidden" name="userBehaviorLog.updateTime" value="${userBehaviorLog.updateTime}">
		<input type="hidden" name="userBehaviorLog.createUserID" value="${userBehaviorLog.createUserID}">
		<input type="hidden" name="userBehaviorLog.updateUserID" value="${userBehaviorLog.updateUserID}">
		<input type="hidden" name="userBehaviorLog.deptId" value="${userBehaviorLog.deptId}">
		<input type="hidden" name="userBehaviorLog.delFlag" value="${userBehaviorLog.delFlag}">
		
		<div class="submitdata" style="width:500">
			<table width="100%" border="0">
				<tr>
					<th width="15%">用户行为</th>
					<td width="35%"><input name="userBehaviorLog.behaviorId" value="${userBehaviorLog.behaviorId}" type="text" maxlength="32"></td>
					<th width="15%">记录时间</th>
					<td width="35%"><input name="userBehaviorLog.loggedDate" value="<fmt:formatDate type='both' value='${userBehaviorLog.loggedDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
				</tr>
				<tr>
					<th width="15%">日志内容</th>
					<td width="35%"><input name="userBehaviorLog.logContent" value="${userBehaviorLog.logContent}" type="text" maxlength="500"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
