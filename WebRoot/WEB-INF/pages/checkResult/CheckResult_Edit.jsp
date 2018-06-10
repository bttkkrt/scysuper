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
	<form name="myform1" method="post" enctype="multipart/form-data" action="checkResultSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="checkResult.id" value="${checkResult.id}">
		<input type="hidden" name="checkResult.createTime" value="<fmt:formatDate type="both" value="${checkResult.createTime}" />">
		<input type="hidden" name="checkResult.updateTime" value="${checkResult.updateTime}">
		<input type="hidden" name="checkResult.createUserID" value="${checkResult.createUserID}">
		<input type="hidden" name="checkResult.updateUserID" value="${checkResult.updateUserID}">
		<input type="hidden" name="checkResult.deptId" value="${checkResult.deptId}">
		<input type="hidden" name="checkResult.delFlag" value="${checkResult.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">检查表id</th>
					<td width="35%"><input name="checkResult.checkTableId" value="${checkResult.checkTableId}" type="text" maxlength="127"></td>
					<th width="15%">内容序号</th>
					<td width="35%"><input name="checkResult.contentOrder" value="${checkResult.contentOrder}" type="text" datatype="n1-3" errormsg='此项请填入整数' ignore="ignore" maxlength="1"></td>
				</tr>
				<tr>
					<th width="15%">检查内容</th>
					<td width="35%"><input name="checkResult.checkContent" value="${checkResult.checkContent}" type="text" maxlength="2000"></td>
					<th width="15%">检查情况</th>
					<td width="35%"><input name="checkResult.checkResult" value="${checkResult.checkResult}" type="text" maxlength="2000"></td>
				</tr>
				<tr>
					<th width="15%">是否合格</th>
					<td width="35%"><input name="checkResult.ifOk" value="${checkResult.ifOk}" type="text" maxlength="127"></td>
					<th width="15%">备注</th>
					<td width="35%"><input name="checkResult.remark" value="${checkResult.remark}" type="text" maxlength="2000"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_checkResult');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
