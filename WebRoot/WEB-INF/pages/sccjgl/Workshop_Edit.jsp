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
	<form name="myform1" method="post" enctype="multipart/form-data" action="workshopSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="workshop.id" value="${workshop.id}">
		<input type="hidden" name="workshop.createTime" value="<fmt:formatDate type="both" value="${workshop.createTime}" />">
		<input type="hidden" name="workshop.updateTime" value="${workshop.updateTime}">
		<input type="hidden" name="workshop.createUserID" value="${workshop.createUserID}">
		<input type="hidden" name="workshop.updateUserID" value="${workshop.updateUserID}">
		<input type="hidden" name="workshop.deptId" value="${workshop.deptId}">
		<input type="hidden" name="workshop.delFlag" value="${workshop.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">车间编号</th>
					<td width="35%"><input name="workshop.workshopCode" style="width:60%;" value="${workshop.workshopCode}" type="text" maxlength="127"></td>
					
					<th width="15%">车间名称</th>
					<td width="35%"><input name="workshop.workshopName" style="width:60%;" value="${workshop.workshopName}" errormsg='车间名称必须是1到30位字符！' nullmsg='车间名称不能为空！' sucmsg='车间名称填写正确！'    datatype="*1-127" type="text" maxlength="30"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">车间负责人</th>
					<td width="35%"><input name="workshop.workshopCharge" style="width:60%;" value="${workshop.workshopCharge}" type="text" maxlength="127"></td>
					<th width="15%">员工数</th>
					<td width="35%"><input name="workshop.workshopWorkers" style="width:60%;" value="${workshop.workshopWorkers}" type="text"  maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">操作方式</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="workshop.workshopOperation" defaultText='请选择' codeName="车间操作方式" value="${workshop.workshopOperation}" /></td>
					<th width="15%"></th>
					<td width="35%"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_workshop');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
