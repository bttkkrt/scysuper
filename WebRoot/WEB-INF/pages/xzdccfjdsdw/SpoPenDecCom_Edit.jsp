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
	<form name="myform1" method="post" enctype="multipart/form-data" action="spoPenDecComSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="spoPenDecCom.id" value="${spoPenDecCom.id}">
		<input type="hidden" name="spoPenDecCom.createTime" value="<fmt:formatDate type="both" value="${spoPenDecCom.createTime}" />">
		<input type="hidden" name="spoPenDecCom.updateTime" value="${spoPenDecCom.updateTime}">
		<input type="hidden" name="spoPenDecCom.createUserID" value="${spoPenDecCom.createUserID}">
		<input type="hidden" name="spoPenDecCom.updateUserID" value="${spoPenDecCom.updateUserID}">
		<input type="hidden" name="spoPenDecCom.deptId" value="${spoPenDecCom.deptId}">
		<input type="hidden" name="spoPenDecCom.delFlag" value="${spoPenDecCom.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">规定</th>
					<td width="35%"><input name="spoPenDecCom.provision" value="${spoPenDecCom.provision}" type="text" maxlength="127"></td>
					<th width="15%">执法依据</th>
					<td width="35%"><input name="spoPenDecCom.lawBasic" value="${spoPenDecCom.lawBasic}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">执法人员</th>
					<td width="35%"><input name="spoPenDecCom.lawOfficer" value="${spoPenDecCom.lawOfficer}" type="text" maxlength="127"></td>
					<th width="15%">罚款方式</th>
					<td width="35%"><cus:hxradio property="spoPenDecCom.fineMethod" codeName="罚款方式" value="${spoPenDecCom.fineMethod}" /></td>
				</tr>
				<tr>
					<th width="15%">银行名称</th>
					<td width="35%"><input name="spoPenDecCom.bankName" value="${spoPenDecCom.bankName}" type="text" maxlength="127"></td>
					<th width="15%">银行账户</th>
					<td width="35%"><input name="spoPenDecCom.bankAccount" value="${spoPenDecCom.bankAccount}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="spoPenDecCom.relatedId" value="${spoPenDecCom.relatedId}" type="text" maxlength="127"></td>
					<th width="15%">行政处罚</th>
					<td width="35%"><input name="spoPenDecCom.adminPenalties" value="${spoPenDecCom.adminPenalties}" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_spoPenDecCom');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
