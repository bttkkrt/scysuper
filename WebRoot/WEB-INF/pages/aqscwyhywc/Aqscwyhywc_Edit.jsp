<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body validform="true">
   <div class="box_01 boxBmargin12 submitdata">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="aqscwyhywcSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="aqscwyhywc.id" value="${aqscwyhywc.id}">
		<input type="hidden" name="aqscwyhywc.createTime" value="<fmt:formatDate type="both" value="${aqscwyhywc.createTime}" />">
		<input type="hidden" name="aqscwyhywc.updateTime" value="${aqscwyhywc.updateTime}">
		<input type="hidden" name="aqscwyhywc.createUserID" value="${aqscwyhywc.createUserID}">
		<input type="hidden" name="aqscwyhywc.updateUserID" value="${aqscwyhywc.updateUserID}">
		<input type="hidden" name="aqscwyhywc.deptId" value="${aqscwyhywc.deptId}">
		<input type="hidden" name="aqscwyhywc.delFlag" value="${aqscwyhywc.delFlag}">
		<input type="hidden" name="aqscwyhywc.glId" value="${aqscwyhywc.glId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">月份</th>
					<td width="35%"><input name="aqscwyhywc.monthTime"  style="width:60%" value="<fmt:formatDate type='both' value='${aqscwyhywc.monthTime}' pattern="yyyy-MM"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"><font style='color:red'>*</font></td>
					<th width="15%">召开安委会全体成员会议完成次数</th>
					<td width="35%"><input name="aqscwyhywc.zkwccs"  style="width:60%" value="${aqscwyhywc.zkwccs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">主要领导带队安全检查完成次数</th>
					<td width="35%"><input name="aqscwyhywc.zywccs"  style="width:60%" value="${aqscwyhywc.zywccs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">分管领导带队安全检查完成次数</th>
					<td width="35%"><input name="aqscwyhywc.fgwccs"  style="width:60%" value="${aqscwyhywc.fgwccs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">其他领导带队安全检查完成次数</th>
					<td width="35%"><input name="aqscwyhywc.qtwccs"  style="width:60%" value="${aqscwyhywc.qtwccs}" type="text" maxlength="127"></td>
					<th width="15%">工委会研究安全生产完成次数</th>
					<td width="35%"><input name="aqscwyhywc.gwwccs"  style="width:60%" value="${aqscwyhywc.gwwccs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				   <th width="15%">主要领导批示安全生产工作次数</th>
					<td width="35%"><input name="aqscwyhywc.zyaqsccs"  style="width:60%" value="${aqscwyhywc.zyaqsccs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_aqscwyhywc');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
