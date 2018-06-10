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
	<form name="myform1" method="post" enctype="multipart/form-data" action="dotSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="dot.id" value="${dot.id}">
		<input type="hidden" name="dot.createTime" value="<fmt:formatDate type="both" value="${dot.createTime}" />">
		<input type="hidden" name="dot.updateTime" value="${dot.updateTime}">
		<input type="hidden" name="dot.createUserID" value="${dot.createUserID}">
		<input type="hidden" name="dot.updateUserID" value="${dot.updateUserID}">
		<input type="hidden" name="dot.deptId" value="${dot.deptId}">
		<input type="hidden" name="dot.delFlag" value="${dot.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">名称</th>
					<td width="35%"><input name="dot.dotName" value="${dot.dotName}" style="width:60%" type="text" maxlength="127"></td>
					<th width="15%">位置</th>
					<td width="35%"><input name="dot.position" value="${dot.position}" style="width:60%" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_dot');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
