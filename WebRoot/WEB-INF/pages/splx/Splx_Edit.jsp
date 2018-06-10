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
	<form name="myform1" method="post" enctype="multipart/form-data" action="splxSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="splx.id" value="${splx.id}">
		<input type="hidden" name="splx.createTime" value="<fmt:formatDate type="both" value="${splx.createTime}" />">
		<input type="hidden" name="splx.updateTime" value="${splx.updateTime}">
		<input type="hidden" name="splx.createUserID" value="${splx.createUserID}">
		<input type="hidden" name="splx.updateUserID" value="${splx.updateUserID}">
		<input type="hidden" name="splx.deptId" value="${splx.deptId}">
		<input type="hidden" name="splx.delFlag" value="${splx.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">文件名称</th>
					<td width="35%"><input name="splx.fileName" value="${splx.fileName}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">开始时间</th>
					<td width="35%"><input name="splx.beginTime" value="<fmt:formatDate type='both' value='${splx.beginTime}' />" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">结束时间</th>
					<td width="35%"><input name="splx.endTime" value="<fmt:formatDate type='both' value='${splx.endTime}' />" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"><font style='color:red'>*</font></td>
					<th width="15%">文件类型</th>
					<td width="35%"><input name="splx.fileType" value="${splx.fileType}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_splx');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
