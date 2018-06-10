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
	<form name="myform1" method="post" enctype="multipart/form-data" action="lighthouseSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="lighthouse.id" value="${lighthouse.id}">
		<input type="hidden" name="lighthouse.createTime" value="<fmt:formatDate type="both" value="${lighthouse.createTime}" />">
		<input type="hidden" name="lighthouse.updateTime" value="${lighthouse.updateTime}">
		<input type="hidden" name="lighthouse.createUserID" value="${lighthouse.createUserID}">
		<input type="hidden" name="lighthouse.updateUserID" value="${lighthouse.updateUserID}">
		<input type="hidden" name="lighthouse.deptId" value="${lighthouse.deptId}">
		<input type="hidden" name="lighthouse.delFlag" value="${lighthouse.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">标题</th>
					<td width="85%" colspan="3"><input name="lighthouse.title" value="${lighthouse.title}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127" style="width:80%"><font style='color:red'>*</font></td>
				</tr>
				
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_lighthouse');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
