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
	<form name="myform1" method="post" enctype="multipart/form-data" action="posFinRatSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="posFinRat.id" value="${posFinRat.id}">
		<input type="hidden" name="posFinRat.createTime" value="<fmt:formatDate type="both" value="${posFinRat.createTime}" />">
		<input type="hidden" name="posFinRat.updateTime" value="${posFinRat.updateTime}">
		<input type="hidden" name="posFinRat.createUserID" value="${posFinRat.createUserID}">
		<input type="hidden" name="posFinRat.updateUserID" value="${posFinRat.updateUserID}">
		<input type="hidden" name="posFinRat.deptId" value="${posFinRat.deptId}">
		<input type="hidden" name="posFinRat.delFlag" value="${posFinRat.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="posFinRat.relatedId" value="${posFinRat.relatedId}" type="text" maxlength="127"></td>
					<th width="15%">罚款大写</th>
					<td width="35%"><input name="posFinRat.fineUppercase" value="${posFinRat.fineUppercase}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">延期方式</th>
					<td width="35%"><cus:SelectOneTag property="posFinRat.postponeMethod" defaultText='请选择' codeName="延期方式" value="${posFinRat.postponeMethod}" /></td>
					<th width="15%">延期年</th>
					<td width="35%"><input name="posFinRat.postponeYear" value="${posFinRat.postponeYear}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">延期月</th>
					<td width="35%"><input name="posFinRat.postponeMonth" value="${posFinRat.postponeMonth}" type="text" maxlength="127"></td>
					<th width="15%">延期日</th>
					<td width="35%"><input name="posFinRat.postponeDate" value="${posFinRat.postponeDate}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">分期长度</th>
					<td width="35%"><input name="posFinRat.stageLength" value="${posFinRat.stageLength}" type="text" maxlength="127"></td>
					<th width="15%">还款期限大写</th>
					<td width="35%"><input name="posFinRat.repayPeriod" value="${posFinRat.repayPeriod}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">尚未缴纳罚款大写</th>
					<td width="35%"><input name="posFinRat.noPay" value="${posFinRat.noPay}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_posFinRat');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
