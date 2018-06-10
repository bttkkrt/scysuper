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
	<form name="myform1" method="post" enctype="multipart/form-data" action="showGuidSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="showGuid.id" value="${showGuid.id}">
		<input type="hidden" name="showGuid.createTime" value="<fmt:formatDate type="both" value="${showGuid.createTime}" />">
		<input type="hidden" name="showGuid.updateTime" value="${showGuid.updateTime}">
		<input type="hidden" name="showGuid.createUserID" value="${showGuid.createUserID}">
		<input type="hidden" name="showGuid.updateUserID" value="${showGuid.updateUserID}">
		<input type="hidden" name="showGuid.deptId" value="${showGuid.deptId}">
		<input type="hidden" name="showGuid.delFlag" value="${showGuid.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">通道编号</th>
					<td width="35%"><input name="showGuid.guid" value="${showGuid.guid}" style="width: 60%" type="text" datatype="*1-127" errormsg='通道编号必须是1到127位字符！' nullmsg='通道编号不能为空！' sucmsg='通道编号填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<td colspan="2" height="100px"  style="text-align:center">
					
							<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
							</s:if>
							<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
							</s:else>						
							
						<a href="#" class="btn_01"  onclick="parent.close_win('win_showGuid');">关闭<b></b></a>
					
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
