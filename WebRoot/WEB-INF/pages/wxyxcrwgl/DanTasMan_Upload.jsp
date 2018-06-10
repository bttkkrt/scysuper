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
	<form name="myform1" method="post" enctype="multipart/form-data" action="danTasManUploadSave.action">
		<s:token />
		<input type="hidden" name="danTasMan.id" value="${danTasMan.id}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">巡查单号</th>
					<td width="35%">${danTasMan.chenkNo}</td>
					<th width="15%">任务名称</th>
					<td width="35%" >${danTasMan.taskName}</td>
				</tr>
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" >${danTasMan.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${danTasMan.companyName}</td>
				</tr>
				<tr>
					<th width="15%">巡检点名称</th>
					<td width="35%" >${danTasMan.checkName}</td>
					
					<th width="15%">巡查人员姓名</th>
					<td width="35%" >${danTasMan.checkPeopleName}</td>
				</tr>
				<tr>
					<th width="15%">巡查开始时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${danTasMan.checkTime}" /></td>
					<th width="15%">巡查结束时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${danTasMan.checkTimeEnd}" /></td>
				</tr>
				<tr>
					<th width="15%">巡查反馈备注</th>
					<td width="85%"  colspan="3">
						<textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="danTasMan.remark" style="width:96%;height:60px">${danTasMan.remark}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >上报<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_danTasMan');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
