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
	<form name="myform1" method="post" enctype="multipart/form-data" action="occChaInfSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="occChaInf.id" value="${occChaInf.id}">
		<input type="hidden" name="occChaInf.createTime" value="<fmt:formatDate type="both" value="${occChaInf.createTime}" />">
		<input type="hidden" name="occChaInf.updateTime" value="${occChaInf.updateTime}">
		<input type="hidden" name="occChaInf.createUserID" value="${occChaInf.createUserID}">
		<input type="hidden" name="occChaInf.updateUserID" value="${occChaInf.updateUserID}">
		<input type="hidden" name="occChaInf.deptId" value="${occChaInf.deptId}">
		<input type="hidden" name="occChaInf.delFlag" value="${occChaInf.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="occChaInf.jshxName" style="width: 60%" value="${occChaInf.jshxName}" type="text" maxlength="127"></td>
					<th width="15%">职务</th>
					<td width="35%"><input name="occChaInf.duty" style="width: 60%" value="${occChaInf.duty}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">办公电话</th>
					<td width="35%"><input name="occChaInf.telephone" style="width: 60%" value="${occChaInf.telephone}" type="text" maxlength="127"></td>
					<th width="15%">手机</th>
					<td width="35%"><input name="occChaInf.mobile" style="width: 60%" value="${occChaInf.mobile}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">文化程度</th>
					<td width="35%"><cus:SelectOneTag style="width: 60%" property="occChaInf.degreeEducation" defaultText='请选择' codeName="学历" value="${occChaInf.degreeEducation}" /></td>
					<th width="15%">专业</th>
					<td width="35%"><cus:SelectOneTag style="width: 60%" property="occChaInf.professional" defaultText='请选择' codeName="专业" value="${occChaInf.professional}" /></td>
				</tr>
				<tr>
					<th width="15%">培训日期</th>
					<td width="35%"><input name="occChaInf.trainingDate" style="width: 60%" value="<fmt:formatDate type='both' value='${occChaInf.trainingDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
					<th width="15%">培训合格证号</th>
					<td width="35%"><input name="occChaInf.trainingCertificatNumber" style="width: 60%" value="${occChaInf.trainingCertificatNumber}" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_occChaInf');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
