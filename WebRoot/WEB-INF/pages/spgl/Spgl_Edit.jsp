<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body validform="true">
   <div class="box_01 boxBmargin12 submitdata">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="spglSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="spgl.id" value="${spgl.id}">
		<input type="hidden" name="spgl.createTime" value="<fmt:formatDate type="both" value="${spgl.createTime}" />">
		<input type="hidden" name="spgl.updateTime" value="${spgl.updateTime}">
		<input type="hidden" name="spgl.createUserID" value="${spgl.createUserID}">
		<input type="hidden" name="spgl.updateUserID" value="${spgl.updateUserID}">
		<input type="hidden" name="spgl.deptId" value="${spgl.deptId}">
		<input type="hidden" name="spgl.delFlag" value="${spgl.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">登录人名称</th>
					<td width="35%"><input name="spgl.loginName" value="${spgl.loginName}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">登录时间</th>
					<td width="35%"><input name="spgl.loginTime" value="<fmt:formatDate type='both' style="width: 60%" value='${spgl.loginTime}' />" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="document.myform1.reset()">重置<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_spgl');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
