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
	<form name="myform1" method="post" enctype="multipart/form-data" action="xwqyaqscSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="xwqyaqsc.id" value="${xwqyaqsc.id}">
		<input type="hidden" name="xwqyaqsc.createTime" value="<fmt:formatDate type="both" value="${xwqyaqsc.createTime}" />">
		<input type="hidden" name="xwqyaqsc.updateTime" value="${xwqyaqsc.updateTime}">
		<input type="hidden" name="xwqyaqsc.createUserID" value="${xwqyaqsc.createUserID}">
		<input type="hidden" name="xwqyaqsc.updateUserID" value="${xwqyaqsc.updateUserID}">
		<input type="hidden" name="xwqyaqsc.deptId" value="${xwqyaqsc.deptId}">
		<input type="hidden" name="xwqyaqsc.delFlag" value="${xwqyaqsc.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">年度</th>
					<td width="35%"><input name="xwqyaqsc.yearTime" style="width: 60%"  value="<fmt:formatDate type='both' value='${xwqyaqsc.yearTime}' pattern="yyyy" />" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy'})"><font style='color:red'>*</font></td>
					<th width="15%">区域</th>
					
					<td width="35%"><s:select cssStyle="width: 60%"  id="areaName"  name="xwqyaqsc.areaName" list="#{'':'请选择','一中队':'一中队','二中队':'二中队','三中队':'三中队','科教创新区':'科教创新区','国际商务区':'国际商务区','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道'}" theme="simple" /></td>
				</tr>
				<tr>
					<th width="15%">去年累计达标数</th>
					<td width="35%"><input name="xwqyaqsc.jnljdbs" style="width: 60%" value="${xwqyaqsc.jnljdbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">今年创建目标数</th>
					<td width="35%"><input name="xwqyaqsc.mncjmbs" style="width: 60%" value="${xwqyaqsc.mncjmbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">今年已申请企业数</th>
					<td width="35%"><input name="xwqyaqsc.mnysqqys" style="width: 60%" value="${xwqyaqsc.mnysqqys}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">培训企业数</th>
					<td width="35%"><input name="xwqyaqsc.pxqys" style="width: 60%" value="${xwqyaqsc.pxqys}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">今年已达标企业数</th>
					<td width="35%"><input name="xwqyaqsc.mnydbqys" style="width: 60%" value="${xwqyaqsc.mnydbqys}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">小微企业累计达标数</th>
					<td width="35%"><input name="xwqyaqsc.xwqyljdbs" style="width: 60%" value="${xwqyaqsc.xwqyljdbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
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
		
						<a href="#" class="btn_01"  onclick="parent.close_win('win_xwqyaqsc');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
