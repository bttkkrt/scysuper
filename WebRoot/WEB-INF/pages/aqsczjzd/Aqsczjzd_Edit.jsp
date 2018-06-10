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
	<form name="myform1" method="post" enctype="multipart/form-data" action="aqsczjzdSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="aqsczjzd.id" value="${aqsczjzd.id}">
		<input type="hidden" name="aqsczjzd.createTime" value="<fmt:formatDate type="both" value="${aqsczjzd.createTime}" />">
		<input type="hidden" name="aqsczjzd.updateTime" value="${aqsczjzd.updateTime}">
		<input type="hidden" name="aqsczjzd.createUserID" value="${aqsczjzd.createUserID}">
		<input type="hidden" name="aqsczjzd.updateUserID" value="${aqsczjzd.updateUserID}">
		<input type="hidden" name="aqsczjzd.deptId" value="${aqsczjzd.deptId}">
		<input type="hidden" name="aqsczjzd.delFlag" value="${aqsczjzd.delFlag}">
		
			<table width="100%" border="0">
				<tr>
				    <th width="15%">年度</th>
					<td width="35%"><input name="aqsczjzd.yearTime" value="<fmt:formatDate type='both' value='${aqsczjzd.yearTime}' pattern="yyyy"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy'})" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">区域</th>
					<td width="35%"><s:select  id="areaName"  name="aqsczjzd.areaName" list="#{'':'请选择','一中队':'一中队','二中队':'二中队','三中队':'三中队','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道','功能区':'功能区','规建委':'规建委','国资委':'国资委'}" theme="simple" cssStyle="width:60%"/></td>
				</tr>
				<tr>
					<th width="15%">目标数</th>
					<td width="35%"><input name="aqsczjzd.mbs" value="${aqsczjzd.mbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
				
					<th width="15%">上报数</th>
					<td width="35%"><input name="aqsczjzd.sbs" value="${aqsczjzd.sbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">验收数</th>
					<td width="35%"><input name="aqsczjzd.yss" value="${aqsczjzd.yss}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_aqsczjzd');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
