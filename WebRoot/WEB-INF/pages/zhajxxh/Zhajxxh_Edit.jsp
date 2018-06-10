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
	<form name="myform1" method="post" enctype="multipart/form-data" action="zhajxxhSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="zhajxxh.id" value="${zhajxxh.id}">
		<input type="hidden" name="zhajxxh.createTime" value="<fmt:formatDate type="both" value="${zhajxxh.createTime}" />">
		<input type="hidden" name="zhajxxh.updateTime" value="${zhajxxh.updateTime}">
		<input type="hidden" name="zhajxxh.createUserID" value="${zhajxxh.createUserID}">
		<input type="hidden" name="zhajxxh.updateUserID" value="${zhajxxh.updateUserID}">
		<input type="hidden" name="zhajxxh.deptId" value="${zhajxxh.deptId}">
		<input type="hidden" name="zhajxxh.delFlag" value="${zhajxxh.delFlag}">
		
			<table width="100%" border="0">
				<tr>
				   <th width="15%">月份</th>
					<td width="35%"><input name="zhajxxh.monthTime" style="width: 60%" value="<fmt:formatDate type='both' value='${zhajxxh.monthTime}' pattern="yyyy-MM"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"><font style='color:red'>*</font></td>
					<th width="15%">区域</th>
					<td width="35%"><s:select  id="areaName" cssStyle="width: 60%"  name="zhajxxh.areaName" list="#{'':'请选择','一中队':'一中队','二中队':'二中队','三中队':'三中队','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道'}" theme="simple" /></td>
				</tr>
				<tr>
					<th width="15%">监管企业数</th>
					<td width="35%"><input name="zhajxxh.jgqys" style="width: 60%" value="${zhajxxh.jgqys}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">推广使用企业目标数</th>
					<td width="35%"><input name="zhajxxh.tgmbs" style="width: 60%" value="${zhajxxh.tgmbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">企业注册数</th>
					<td width="35%"><input name="zhajxxh.qyzcs" style="width: 60%" value="${zhajxxh.qyzcs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">完成基础信息填报企业数</th>
					<td width="35%"><input name="zhajxxh.wctbqys" style="width: 60%" value="${zhajxxh.wctbqys}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">正常运行隐患排查企业数</th>
					<td width="35%"><input name="zhajxxh.yhpcqys" style="width: 60%" value="${zhajxxh.yhpcqys}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>					
						<a href="#" class="btn_01"  onclick="parent.close_win('win_zhajxxh');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
