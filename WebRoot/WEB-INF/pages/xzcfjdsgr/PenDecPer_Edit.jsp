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
	<form name="myform1" method="post" enctype="multipart/form-data" action="penDecPerSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="penDecPer.id" value="${penDecPer.id}">
		<input type="hidden" name="penDecPer.createTime" value="<fmt:formatDate type="both" value="${penDecPer.createTime}" />">
		<input type="hidden" name="penDecPer.updateTime" value="${penDecPer.updateTime}">
		<input type="hidden" name="penDecPer.createUserID" value="${penDecPer.createUserID}">
		<input type="hidden" name="penDecPer.updateUserID" value="${penDecPer.updateUserID}">
		<input type="hidden" name="penDecPer.deptId" value="${penDecPer.deptId}">
		<input type="hidden" name="penDecPer.delFlag" value="${penDecPer.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">被处罚人性别</th>
					<td width="35%"><cus:SelectOneTag property="penDecPer.punishedSex" defaultText='请选择' codeName="性别" value="${penDecPer.punishedSex}" /></td>
					<th width="15%">被处罚人年龄</th>
					<td width="35%"><input name="penDecPer.punishedAge" value="${penDecPer.punishedAge}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">执法人员</th>
					<td width="35%"><input name="penDecPer.adminPenalties" value="${penDecPer.adminPenalties}" type="text" maxlength="127"></td>
					<th width="15%">被处罚人家庭住址</th>
					<td width="35%"><input name="penDecPer.punishedAddress" value="${penDecPer.punishedAddress}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">被处罚人职务</th>
					<td width="35%"><input name="penDecPer.punishedPosition" value="${penDecPer.punishedPosition}" type="text" maxlength="127"></td>
					<th width="15%">银行名称</th>
					<td width="35%"><input name="penDecPer.provision" value="${penDecPer.provision}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">银行账户</th>
					<td width="35%"><input name="penDecPer.lawBasic" value="${penDecPer.lawBasic}" type="text" maxlength="127"></td>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="penDecPer.relatedId" value="${penDecPer.relatedId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">被处罚人身份证号</th>
					<td width="35%"><input name="penDecPer.punishedId" value="${penDecPer.punishedId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_penDecPer');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
