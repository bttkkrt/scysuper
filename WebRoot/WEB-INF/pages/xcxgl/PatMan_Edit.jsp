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
	<form name="myform1" method="post" enctype="multipart/form-data" action="patManSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="patMan.id" value="${patMan.id}">
		<input type="hidden" name="patMan.createTime" value="<fmt:formatDate type="both" value="${patMan.createTime}" />">
		<input type="hidden" name="patMan.updateTime" value="${patMan.updateTime}">
		<input type="hidden" name="patMan.createUserID" value="${patMan.createUserID}">
		<input type="hidden" name="patMan.updateUserID" value="${patMan.updateUserID}">
		<input type="hidden" name="patMan.deptId" value="${patMan.deptId}">
		<input type="hidden" name="patMan.delFlag" value="${patMan.delFlag}">
		<input type="hidden" name="patMan.patrolType" value="${patMan.patrolType}">
			<table width="100%" border="0">
				<tr>
					<th width="35%">巡查项</th>
					<td width="65%">
						<textarea name="patMan.patrolName" style="width:78%;height:120px" datatype="*1-2000" errormsg='巡查项必须是1到2000位字符！' nullmsg='巡查项不能为空！' sucmsg='巡查项填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${patMan.patrolName}</textarea><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<th width="35%">备注</th>
					<td width="65%"><textarea name="patMan.remark" style="width:78%;height:120px" type="text" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${patMan.remark}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_patMan');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
