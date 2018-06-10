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
	<form name="myform1" method="post" enctype="multipart/form-data" action="aqscwyhtjSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="aqscwyhtj.id" value="${aqscwyhtj.id}">
		<input type="hidden" name="aqscwyhtj.createTime" value="<fmt:formatDate type="both" value="${aqscwyhtj.createTime}" />">
		<input type="hidden" name="aqscwyhtj.updateTime" value="${aqscwyhtj.updateTime}">
		<input type="hidden" name="aqscwyhtj.createUserID" value="${aqscwyhtj.createUserID}">
		<input type="hidden" name="aqscwyhtj.updateUserID" value="${aqscwyhtj.updateUserID}">
		<input type="hidden" name="aqscwyhtj.deptId" value="${aqscwyhtj.deptId}">
		<input type="hidden" name="aqscwyhtj.delFlag" value="${aqscwyhtj.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">年份</th>
					<td width="35%"><input name="aqscwyhtj.yearTime"  style="width:60%" value="<fmt:formatDate type='both' value='${aqscwyhtj.yearTime}' pattern="yyyy"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy'})"><font style='color:red'>*</font></td>
					<th width="15%">召开安委会全体成员会议年度计划次数</th>
					<td width="35%"><input name="aqscwyhtj.zkndjhs"  style="width:60%" value="${aqscwyhtj.zkndjhs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">主要领导带队安全检查年度计划次数</th>
					<td width="35%"><input name="aqscwyhtj.zyndjhcs"  style="width:60%" value="${aqscwyhtj.zyndjhcs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">分管领导带队安全检查年度计划次数</th>
					<td width="35%"><input name="aqscwyhtj.fgndjhcs"  style="width:60%" value="${aqscwyhtj.fgndjhcs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">其他领导带队安全检查年度计划次数</th>
					<td width="35%"><input name="aqscwyhtj.qtndjhcs"  style="width:60%" value="${aqscwyhtj.qtndjhcs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					
			
					<th width="15%">工委会研究安全生产年度计划次数</th>
					<td width="35%"><input name="aqscwyhtj.gwndjhcs"  style="width:60%" value="${aqscwyhtj.gwndjhcs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_aqscwyhtj');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
