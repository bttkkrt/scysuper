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
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="aqscsgqkSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="aqscsgqk.id" value="${aqscsgqk.id}">
		<input type="hidden" name="aqscsgqk.createTime" value="<fmt:formatDate type="both" value="${aqscsgqk.createTime}" />">
		<input type="hidden" name="aqscsgqk.updateTime" value="${aqscsgqk.updateTime}">
		<input type="hidden" name="aqscsgqk.createUserID" value="${aqscsgqk.createUserID}">
		<input type="hidden" name="aqscsgqk.updateUserID" value="${aqscsgqk.updateUserID}">
		<input type="hidden" name="aqscsgqk.deptId" value="${aqscsgqk.deptId}">
		<input type="hidden" name="aqscsgqk.delFlag" value="${aqscsgqk.delFlag}">
		
			<table width="100%" border="0">
				<tr>
				    <th width="15%">月份</th>
					<td width="35%"><input name="aqscsgqk.monthTime"  style="width:60%" value="<fmt:formatDate type='both' value='${aqscsgqk.monthTime}' pattern="yyyy-MM"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"><font style='color:red'>*</font></td>
					<th width="15%">区域</th>
					
					<td width="35%"><s:select  id="areaName"   cssStyle="width:60%" name="aqscsgqk.areaName" list="#{'':'请选择','一中队':'一中队','二中队':'二中队','三中队':'三中队','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道','胜浦街道':'胜浦街道','科教创新区':'科教创新区','国际科技园':'国际科技园','阳澄湖旅游度假区':'阳澄湖旅游度假区'}" theme="simple" /></td>
				</tr>
				<tr>
					<th width="15%">当月事故起数</th>
					<td width="35%"><input name="aqscsgqk.dysgqs"  style="width:60%" value="${aqscsgqk.dysgqs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">当月事故生产性</th>
					<td width="35%"><input name="aqscsgqk.dysgscx"  style="width:60%" value="${aqscsgqk.dysgscx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				
				<tr>
					<th width="15%">当月死亡事故起数/生产性</th>
					<td width="35%"><input name="aqscsgqk.dyswsgqs"  style="width:30%" value="${aqscsgqk.dyswsgqs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font><input name="aqscsgqk.dyswsgscx"  style="width:30%" value="${aqscsgqk.dyswsgscx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					
					<th width="15%">当月死亡事故人数/生产性</th>
					<td width="35%"><input name="aqscsgqk.dyswsgrs"  style="width:30%" value="${aqscsgqk.dyswsgrs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font><input name="aqscsgqk.dyswsgscxs"  style="width:30%" value="${aqscsgqk.dyswsgscxs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					
				</tr>
				<tr>
					<th width="15%">当月重伤事故起数/生产性</th>
					<td width="35%"><input name="aqscsgqk.dyzssgqs"  style="width:30%" value="${aqscsgqk.dyzssgqs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font><input name="aqscsgqk.dyzssgscx"  style="width:30%" value="${aqscsgqk.dyzssgscx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					
			
					<th width="15%">当月重伤事故人数/生产性</th>
					<td width="35%"><input name="aqscsgqk.dyzssgrs"  style="width:30%" value="${aqscsgqk.dyzssgrs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font><input name="aqscsgqk.dyzssgscxs"  style="width:30%" value="${aqscsgqk.dyzssgscxs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					
				</tr>
				
				<tr>
					<th width="15%">累计事故情况起数</th>
					<td width="35%"><input name="aqscsgqk.ljsgqkqs"  style="width:60%" value="${aqscsgqk.ljsgqkqs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">累计事故情况生产性</th>
					<td width="35%"><input name="aqscsgqk.ljsgqkscx"  style="width:60%" value="${aqscsgqk.ljsgqkscx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				
				<tr>
					<th width="15%">累计亡人事故起数</th>
					<td width="35%"><input name="aqscsgqk.ljwrsgqs"  style="width:60%" value="${aqscsgqk.ljwrsgqs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">累计亡人事故生产性</th>
					<td width="35%"><input name="aqscsgqk.ljwrsgscx"  style="width:60%" value="${aqscsgqk.ljwrsgscx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				
				<tr>
					<th width="15%">累计重伤事故起数</th>
					<td width="35%"><input name="aqscsgqk.ljzssgqs"  style="width:60%" value="${aqscsgqk.ljzssgqs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">累计重伤事故生产性</th>
					<td width="35%"><input name="aqscsgqk.ljzssgscx"  style="width:60%" value="${aqscsgqk.ljzssgscx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_aqscsgqk');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
