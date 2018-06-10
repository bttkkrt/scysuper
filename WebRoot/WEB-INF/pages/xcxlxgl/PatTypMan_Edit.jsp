<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="patTypManSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="patTypMan.id" value="${patTypMan.id}">
		<input type="hidden" name="patTypMan.createTime" value="<fmt:formatDate type="both" value="${patTypMan.createTime}" />">
		<input type="hidden" name="patTypMan.updateTime" value="${patTypMan.updateTime}">
		<input type="hidden" name="patTypMan.createUserID" value="${patTypMan.createUserID}">
		<input type="hidden" name="patTypMan.updateUserID" value="${patTypMan.updateUserID}">
		<input type="hidden" name="patTypMan.deptId" value="${patTypMan.deptId}">
		<input type="hidden" name="patTypMan.delFlag" value="${patTypMan.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="35%">巡查项类型名称</th>
					<td width="65%"><input name="patTypMan.patrolTypeName" value="${patTypMan.patrolTypeName}" type="text" maxlength="15"
					 datatype="*" errormsg="该项为必填项" nullmsg="请输入巡查项类型名称"  sucmsg="名称输入正确" style="width: 60%" ><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="35%">备注</th>
					<td width="65%"><textarea name="patTypMan.remark" type="text" onKeyDown='if(this.value.length > 50) this.value=this.value.substr(0,50)' style="width:96%;height:60px">${patTypMan.remark}</textarea></td>
				
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_patTypMan');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
