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
	<form name="myform1" method="post" enctype="multipart/form-data" action="cheIteSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="cheIte.id" value="${cheIte.id}">
		<input type="hidden" name="cheIte.createTime" value="<fmt:formatDate type="both" value="${cheIte.createTime}" />">
		<input type="hidden" name="cheIte.updateTime" value="${cheIte.updateTime}">
		<input type="hidden" name="cheIte.createUserID" value="${cheIte.createUserID}">
		<input type="hidden" name="cheIte.updateUserID" value="${cheIte.updateUserID}">
		<input type="hidden" name="cheIte.deptId" value="${cheIte.deptId}">
		<input type="hidden" name="cheIte.delFlag" value="${cheIte.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">检查项名称</th>
					<td width="35%"><input name="cheIte.checkName" value="${cheIte.checkName}" style="width: 60%" type="text" maxlength="127"></td>
					<th width="15%">检查项类型</th>
					<td width="35%"><cus:SelectOneTag property="cheIte.checkType" style="width: 60%" defaultText='请选择' codeName="监督检查项类型" value="${cheIte.checkType}" /></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%"><input name="cheIte.remark" style="width: 60%" value="${cheIte.remark}" type="text" maxlength="2000"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_cheIte');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
