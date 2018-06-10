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
	<form name="myform1" method="post" enctype="multipart/form-data" action="aqscpxqkSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="aqscpxqk.id" value="${aqscpxqk.id}">
		<input type="hidden" name="aqscpxqk.createTime" value="<fmt:formatDate type="both" value="${aqscpxqk.createTime}" />">
		<input type="hidden" name="aqscpxqk.updateTime" value="${aqscpxqk.updateTime}">
		<input type="hidden" name="aqscpxqk.createUserID" value="${aqscpxqk.createUserID}">
		<input type="hidden" name="aqscpxqk.updateUserID" value="${aqscpxqk.updateUserID}">
		<input type="hidden" name="aqscpxqk.deptId" value="${aqscpxqk.deptId}">
		<input type="hidden" name="aqscpxqk.delFlag" value="${aqscpxqk.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">月份</th>
					<td width="35%"><input name="aqscpxqk.monthTime"  style="width:60%" value="<fmt:formatDate type='both' value='${aqscpxqk.monthTime}' pattern="yyyy-MM"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"><font style='color:red'>*</font></td>
					<th width="15%">区域</th>
					<td width="35%"><s:select  id="areaName"   cssStyle="width:60%" name="aqscpxqk.areaName" list="#{'':'请选择','一中队':'一中队','二中队':'二中队','三中队':'三中队','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道'}" theme="simple" /></td>
				</tr>
				<tr>
					<th width="15%">主要负责人</th>
					<td width="35%"><input name="aqscpxqk.zyfzr"  style="width:60%" value="${aqscpxqk.zyfzr}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">安全管理人员</th>
					<td width="35%"><input name="aqscpxqk.aqglry"  style="width:60%" value="${aqscpxqk.aqglry}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">职业卫生</th>
					<td width="35%"><input name="aqscpxqk.zyws"  style="width:60%" value="${aqscpxqk.zyws}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">班组长</th>
					<td width="35%"><input name="aqscpxqk.bzz"  style="width:60%" value="${aqscpxqk.bzz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">特种作业人员</th>
					<td width="35%"><input name="aqscpxqk.tzzyry"  style="width:60%" value="${aqscpxqk.tzzyry}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
		
						<a href="#" class="btn_01"  onclick="parent.close_win('win_aqscpxqk');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
