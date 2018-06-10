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
	<form name="myform1" method="post" enctype="multipart/form-data" action="zywsndzbSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="zywsndzb.id" value="${zywsndzb.id}">
		<input type="hidden" name="zywsndzb.createTime" value="<fmt:formatDate type="both" value="${zywsndzb.createTime}" />">
		<input type="hidden" name="zywsndzb.updateTime" value="${zywsndzb.updateTime}">
		<input type="hidden" name="zywsndzb.createUserID" value="${zywsndzb.createUserID}">
		<input type="hidden" name="zywsndzb.updateUserID" value="${zywsndzb.updateUserID}">
		<input type="hidden" name="zywsndzb.deptId" value="${zywsndzb.deptId}">
		<input type="hidden" name="zywsndzb.delFlag" value="${zywsndzb.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">年度</th>
					<td width="35%"><input name="zywsndzb.yearTime" style="width: 60%" value="<fmt:formatDate type='both' value='${zywsndzb.yearTime}' pattern="yyyy"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy'})"><font style='color:red'>*</font></td>
					<th width="15%">区域</th>
					<td width="35%"><s:select  cssStyle="width: 60%"  id="areaName"  name="zywsndzb.areaName" list="#{'':'请选择','一中队':'一中队','二中队':'二中队','三中队':'三中队','国际商务区':'国际商务区','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道'}" theme="simple" /></td>
				</tr>
				<tr>
					<th width="15%">网上申报目标数</th>
					<td width="35%"><input name="zywsndzb.wssbmbs" style="width: 60%" value="${zywsndzb.wssbmbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">网上申报完成数</th>
					<td width="35%"><input name="zywsndzb.wssbwcs" style="width: 60%" value="${zywsndzb.wssbwcs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">处罚案例目标数</th>
					<td width="35%"><input name="zywsndzb.cfalmbs" style="width: 60%" value="${zywsndzb.cfalmbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">处罚案例完成数</th>
					<td width="35%"><input name="zywsndzb.cfalwcs" style="width: 60%" value="${zywsndzb.cfalwcs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">定期检测目标数</th>
					<td width="35%"><input name="zywsndzb.dqjcmbs" style="width: 60%" value="${zywsndzb.dqjcmbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">定期检测完成数</th>
					<td width="35%"><input name="zywsndzb.dqjcwcs" style="width: 60%" value="${zywsndzb.dqjcwcs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">健康监护完成数</th>
					<td width="35%"><input name="zywsndzb.jkjhwcs" style="width: 60%" value="${zywsndzb.jkjhwcs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">健康监护目标数</th>
					<td width="35%"><input name="zywsndzb.jkjhmbs" style="width: 60%" value="${zywsndzb.jkjhmbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">企业培训目标数</th>
					<td width="35%"><input name="zywsndzb.qypxmbs" style="width: 60%" value="${zywsndzb.qypxmbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">企业培训完成数</th>
					<td width="35%"><input name="zywsndzb.qypxwcs" style="width: 60%" value="${zywsndzb.qypxwcs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">基础建设示范企业目标数</th>
					<td width="35%"><input name="zywsndzb.jcjsmbs" style="width: 60%" value="${zywsndzb.jcjsmbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">基础建设示范企业完成数</th>
					<td width="35%"><input name="zywsndzb.jcjswcs" style="width: 60%" value="${zywsndzb.jcjswcs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
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
						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_zywsndzb');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
