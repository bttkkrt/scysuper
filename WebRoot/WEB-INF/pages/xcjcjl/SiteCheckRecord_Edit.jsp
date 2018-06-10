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
	<form name="myform1" method="post" enctype="multipart/form-data" action="siteCheckRecordSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="siteCheckRecord.id" value="${siteCheckRecord.id}">
		<input type="hidden" name="siteCheckRecord.createTime" value="<fmt:formatDate type="both" value="${siteCheckRecord.createTime}" />">
		<input type="hidden" name="siteCheckRecord.updateTime" value="${siteCheckRecord.updateTime}">
		<input type="hidden" name="siteCheckRecord.createUserID" value="${siteCheckRecord.createUserID}">
		<input type="hidden" name="siteCheckRecord.updateUserID" value="${siteCheckRecord.updateUserID}">
		<input type="hidden" name="siteCheckRecord.deptId" value="${siteCheckRecord.deptId}">
		<input type="hidden" name="siteCheckRecord.delFlag" value="${siteCheckRecord.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">现场负责人</th>
					<td width="35%"><input name="siteCheckRecord.chargePerson" value="${siteCheckRecord.chargePerson}" type="text" maxlength="127"></td>
					<th width="15%">检查人员</th>
					<td width="35%"><input name="siteCheckRecord.checkPerson" value="${siteCheckRecord.checkPerson}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">检查开始时间</th>
					<td width="35%"><input name="siteCheckRecord.startTime" value="${siteCheckRecord.startTime}" type="text" maxlength="127"></td>
					<th width="15%">检查结束时间</th>
					<td width="35%"><input name="siteCheckRecord.endTime" value="${siteCheckRecord.endTime}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">检查情况</th>
					<td width="35%"><input name="siteCheckRecord.checkCondition" value="${siteCheckRecord.checkCondition}" type="text" maxlength="127"></td>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="siteCheckRecord.relatedId" value="${siteCheckRecord.relatedId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">检查场所</th>
					<td width="35%"><input name="siteCheckRecord.checkAddress" value="${siteCheckRecord.checkAddress}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_siteCheckRecord');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
