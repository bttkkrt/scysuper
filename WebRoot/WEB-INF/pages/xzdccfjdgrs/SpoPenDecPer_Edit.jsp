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
	<form name="myform1" method="post" enctype="multipart/form-data" action="spoPenDecPerSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="spoPenDecPer.id" value="${spoPenDecPer.id}">
		<input type="hidden" name="spoPenDecPer.createTime" value="<fmt:formatDate type="both" value="${spoPenDecPer.createTime}" />">
		<input type="hidden" name="spoPenDecPer.updateTime" value="${spoPenDecPer.updateTime}">
		<input type="hidden" name="spoPenDecPer.createUserID" value="${spoPenDecPer.createUserID}">
		<input type="hidden" name="spoPenDecPer.updateUserID" value="${spoPenDecPer.updateUserID}">
		<input type="hidden" name="spoPenDecPer.deptId" value="${spoPenDecPer.deptId}">
		<input type="hidden" name="spoPenDecPer.delFlag" value="${spoPenDecPer.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">被处罚人性别</th>
					<td width="35%"><cus:SelectOneTag property="spoPenDecPer.punishedSex" defaultText='请选择' codeName="性别" value="${spoPenDecPer.punishedSex}" /></td>
					<th width="15%">被处罚人年龄</th>
					<td width="35%"><input name="spoPenDecPer.punishedAge" value="${spoPenDecPer.punishedAge}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">被处罚人身份证号</th>
					<td width="35%"><input name="spoPenDecPer.punishedId" value="${spoPenDecPer.punishedId}" type="text" maxlength="127"></td>
					<th width="15%">被处罚人家庭住址</th>
					<td width="35%"><input name="spoPenDecPer.punishedAddress" value="${spoPenDecPer.punishedAddress}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">执法人员</th>
					<td width="35%"><input name="spoPenDecPer.lawOfficer" value="${spoPenDecPer.lawOfficer}" type="text" maxlength="127"></td>
					<th width="15%">规定</th>
					<td width="35%"><input name="spoPenDecPer.provision" value="${spoPenDecPer.provision}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">执法依据</th>
					<td width="35%"><input name="spoPenDecPer.lawBasic" value="${spoPenDecPer.lawBasic}" type="text" maxlength="127"></td>
					<th width="15%">行政处罚</th>
					<td width="35%"><input name="spoPenDecPer.adminPenalties" value="${spoPenDecPer.adminPenalties}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">罚款方式</th>
					<td width="35%"><cus:hxradio property="spoPenDecPer.fineMethod" codeName="罚款方式" value="${spoPenDecPer.fineMethod}" /></td>
					<th width="15%">银行名称</th>
					<td width="35%"><input name="spoPenDecPer.bankName" value="${spoPenDecPer.bankName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">银行账户</th>
					<td width="35%"><input name="spoPenDecPer.bankAccount" value="${spoPenDecPer.bankAccount}" type="text" maxlength="127"></td>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="spoPenDecPer.relatedId" value="${spoPenDecPer.relatedId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">被处罚人职务</th>
					<td width="35%"><input name="spoPenDecPer.punishedPosition" value="${spoPenDecPer.punishedPosition}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_spoPenDecPer');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
