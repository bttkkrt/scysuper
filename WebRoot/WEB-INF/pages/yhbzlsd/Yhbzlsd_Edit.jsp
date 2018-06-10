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
	<form name="myform1" method="post" enctype="multipart/form-data" action="yhbzlsdSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="yhbzlsd.id" value="${yhbzlsd.id}">
		<input type="hidden" name="yhbzlsd.createTime" value="<fmt:formatDate type="both" value="${yhbzlsd.createTime}" />">
		<input type="hidden" name="yhbzlsd.updateTime" value="${yhbzlsd.updateTime}">
		<input type="hidden" name="yhbzlsd.createUserID" value="${yhbzlsd.createUserID}">
		<input type="hidden" name="yhbzlsd.updateUserID" value="${yhbzlsd.updateUserID}">
		<input type="hidden" name="yhbzlsd.deptId" value="${yhbzlsd.deptId}">
		<input type="hidden" name="yhbzlsd.delFlag" value="${yhbzlsd.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">年度</th>
					<td width="35%"><input name="yhbzlsd.yearTime" style="width: 60%" value="<fmt:formatDate type='both' value='${yhbzlsd.yearTime}' pattern="yyyy"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy'})"><font style='color:red'>*</font></td>
					<th width="15%">区域</th>
					<td width="35%"><s:select cssStyle="width: 60%"  id="areaName"  name="yhbzlsd.areaName" list="#{'':'请选择','一中队':'一中队','二中队':'二中队','三中队':'三中队','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道'}" theme="simple" /></td>
				</tr>
				<tr>
					<th width="15%">现有零售点</th>
					<td width="35%"><input name="yhbzlsd.xylsd" style="width: 60%" value="${yhbzlsd.xylsd}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">当年换证零售点计划数</th>
					<td width="35%"><input name="yhbzlsd.dnhzlsdjhs" style="width: 60%" value="${yhbzlsd.dnhzlsdjhs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">当年换证零售点完成数</th>
					<td width="35%"><input name="yhbzlsd.dnhzlsdwcs" style="width: 60%" value="${yhbzlsd.dnhzlsdwcs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">当年参加培训零售点计划数</th>
					<td width="35%"><input name="yhbzlsd.dncjpxjhs" style="width: 60%" value="${yhbzlsd.dncjpxjhs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">当年参加培训零售点完成数</th>
					<td width="35%"><input name="yhbzlsd.dncjpxwcs" style="width: 60%" value="${yhbzlsd.dncjpxwcs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">持证上岗人数</th>
					<td width="35%"><input name="yhbzlsd.czsgrs" style="width: 60%" value="${yhbzlsd.czsgrs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">当年累计督察</th>
					<td width="35%"><input name="yhbzlsd.dnljdc" style="width: 60%" value="${yhbzlsd.dnljdc}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_yhbzlsd');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
