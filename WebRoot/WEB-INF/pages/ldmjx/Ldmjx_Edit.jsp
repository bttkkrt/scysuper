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
	<form name="myform1" method="post" enctype="multipart/form-data" action="ldmjxSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="ldmjx.id" value="${ldmjx.id}">
		<input type="hidden" name="ldmjx.createTime" value="<fmt:formatDate type="both" value="${ldmjx.createTime}" />">
		<input type="hidden" name="ldmjx.updateTime" value="${ldmjx.updateTime}">
		<input type="hidden" name="ldmjx.createUserID" value="${ldmjx.createUserID}">
		<input type="hidden" name="ldmjx.updateUserID" value="${ldmjx.updateUserID}">
		<input type="hidden" name="ldmjx.deptId" value="${ldmjx.deptId}">
		<input type="hidden" name="ldmjx.delFlag" value="${ldmjx.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">月份</th>
					<td width="35%"><input name="ldmjx.monthTime" style="width: 60%" value="<fmt:formatDate type='both' value='${ldmjx.monthTime}' pattern="yyyy-MM"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"><font style='color:red'>*</font></td>
					<th width="15%">区域</th>
				
					<td width="35%"><s:select  id="areaName"  cssStyle="width: 60%" name="ldmjx.areaName" list="#{'':'请选择','一中队':'一中队','二中队':'二中队','三中队':'三中队','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道'}" theme="simple" /></td>
				</tr>
				<tr>
					<th width="15%">现有企业数</th>
					<td width="35%"><input name="ldmjx.xyqys" value="${ldmjx.xyqys}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">检查数</th>
					<td width="35%"><input name="ldmjx.jcs" value="${ldmjx.jcs}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">出动检查数</th>
					<td width="35%"><input name="ldmjx.cdjcs" value="${ldmjx.cdjcs}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">发现隐患数</th>
					<td width="35%"><input name="ldmjx.fxyhs" value="${ldmjx.fxyhs}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">整改隐患数</th>
					<td width="35%"><input name="ldmjx.zgyhs" value="${ldmjx.zgyhs}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">处罚数</th>
					<td width="35%"><input name="ldmjx.cfs" value="${ldmjx.cfs}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">整治关闭数</th>
					<td width="35%"><input name="ldmjx.zzgbs" value="${ldmjx.zzgbs}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">达到安全标准化数</th>
					<td width="35%"><input name="ldmjx.ddaqbzhs" value="${ldmjx.ddaqbzhs}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">录入监管信息系统数</th>
					<td width="35%"><input name="ldmjx.lrjgxxx" value="${ldmjx.lrjgxxx}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">建立安全总监数</th>
					<td width="35%"><input name="ldmjx.jlaqzjs" value="${ldmjx.jlaqzjs}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">投入安全责任险数</th>
					<td width="35%"><input name="ldmjx.traqzr" value="${ldmjx.traqzr}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_ldmjx');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
