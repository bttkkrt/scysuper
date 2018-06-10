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
	<form name="myform1" method="post" enctype="multipart/form-data" action="inquiryRecordSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="inquiryRecord.id" value="${inquiryRecord.id}">
		<input type="hidden" name="inquiryRecord.createTime" value="<fmt:formatDate type="both" value="${inquiryRecord.createTime}" />">
		<input type="hidden" name="inquiryRecord.updateTime" value="${inquiryRecord.updateTime}">
		<input type="hidden" name="inquiryRecord.createUserID" value="${inquiryRecord.createUserID}">
		<input type="hidden" name="inquiryRecord.updateUserID" value="${inquiryRecord.updateUserID}">
		<input type="hidden" name="inquiryRecord.deptId" value="${inquiryRecord.deptId}">
		<input type="hidden" name="inquiryRecord.delFlag" value="${inquiryRecord.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">询问开始时间</th>
					<td width="35%"><input name="inquiryRecord.inquiryPeriod" value="<fmt:formatDate type='both' value='${inquiryRecord.inquiryPeriod}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
					<th width="15%">询问结束时间</th>
					<td width="35%"><input name="inquiryRecord.endTime" value="<fmt:formatDate type='both' value='${inquiryRecord.endTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
				</tr>
				<tr>
					<th width="15%">被询问人姓名</th>
					<td width="35%"><input name="inquiryRecord.askedPerson" value="${inquiryRecord.askedPerson}" type="text" maxlength="127"></td>
					<th width="15%">性别</th>
					<td width="35%"><cus:SelectOneTag property="inquiryRecord.sex" defaultText='请选择' codeName="性别" value="${inquiryRecord.sex}" /></td>
				</tr>
				<tr>
					<th width="15%">年龄</th>
					<td width="35%"><input name="inquiryRecord.peopleAge" value="${inquiryRecord.peopleAge}" type="text" maxlength="127"></td>
					<th width="15%">身份证号</th>
					<td width="35%"><input name="inquiryRecord.cardId" value="${inquiryRecord.cardId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">职位</th>
					<td width="35%"><input name="inquiryRecord.position" value="${inquiryRecord.position}" type="text" maxlength="127"></td>
					<th width="15%">住址</th>
					<td width="35%"><input name="inquiryRecord.address" value="${inquiryRecord.address}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">电话</th>
					<td width="35%"><input name="inquiryRecord.tele" value="${inquiryRecord.tele}" type="text" maxlength="127"></td>
					<th width="15%">在场人</th>
					<td width="35%"><input name="inquiryRecord.presentPeople" value="${inquiryRecord.presentPeople}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">询问记录</th>
					<td width="35%"><input name="inquiryRecord.inquiryRecord" value="${inquiryRecord.inquiryRecord}" type="text" maxlength="127"></td>
					<th width="15%">询问人</th>
					<td width="35%"><input name="inquiryRecord.inquiryPerson" value="${inquiryRecord.inquiryPerson}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">记录人</th>
					<td width="35%"><input name="inquiryRecord.recordPerson" value="${inquiryRecord.recordPerson}" type="text" maxlength="127"></td>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="inquiryRecord.relatedId" value="${inquiryRecord.relatedId}" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_inquiryRecord');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
