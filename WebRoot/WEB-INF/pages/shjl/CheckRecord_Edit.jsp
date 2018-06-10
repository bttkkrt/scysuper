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
	<form name="myform1" method="post" enctype="multipart/form-data" action="checkRecordSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="checkRecord.id" value="${checkRecord.id}">
		<input type="hidden" name="checkRecord.createTime" value="<fmt:formatDate type="both" value="${checkRecord.createTime}" />">
		<input type="hidden" name="checkRecord.updateTime" value="${checkRecord.updateTime}">
		<input type="hidden" name="checkRecord.createUserID" value="${checkRecord.createUserID}">
		<input type="hidden" name="checkRecord.updateUserID" value="${checkRecord.updateUserID}">
		<input type="hidden" name="checkRecord.deptId" value="${checkRecord.deptId}">
		<input type="hidden" name="checkRecord.delFlag" value="${checkRecord.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">审核人id</th>
					<td width="35%"><input name="checkRecord.checkUserid" value="${checkRecord.checkUserid}" type="text" maxlength="127"></td>
					<th width="15%">审核备注</th>
					<td width="35%"><input name="checkRecord.checkRemark" value="${checkRecord.checkRemark}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">审核结果</th>
					<td width="35%"><input name="checkRecord.checkResult" value="${checkRecord.checkResult}" type="text" maxlength="127"></td>
					<th width="15%">审核时间</th>
					<td width="35%"><input name="checkRecord.checkTime" value="${checkRecord.checkTime}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">关联id</th>
					<td width="35%"><input name="checkRecord.infoId" value="${checkRecord.infoId}" type="text" maxlength="127"></td>
					<th width="15%">审核人名称</th>
					<td width="35%"><input name="checkRecord.checkUsername" value="${checkRecord.checkUsername}" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_checkRecord');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
