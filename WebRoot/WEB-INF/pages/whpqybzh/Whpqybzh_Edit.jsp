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
	<form name="myform1" method="post" enctype="multipart/form-data" action="whpqybzhSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="whpqybzh.id" value="${whpqybzh.id}">
		<input type="hidden" name="whpqybzh.createTime" value="<fmt:formatDate type="both" value="${whpqybzh.createTime}" />">
		<input type="hidden" name="whpqybzh.updateTime" value="${whpqybzh.updateTime}">
		<input type="hidden" name="whpqybzh.createUserID" value="${whpqybzh.createUserID}">
		<input type="hidden" name="whpqybzh.updateUserID" value="${whpqybzh.updateUserID}">
		<input type="hidden" name="whpqybzh.deptId" value="${whpqybzh.deptId}">
		<input type="hidden" name="whpqybzh.delFlag" value="${whpqybzh.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">月份</th>
					<td width="35%"><input name="whpqybzh.monthTime" style="width: 60%" value="<fmt:formatDate type='both' value='${whpqybzh.monthTime}' pattern="yyyy-MM"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"><font style='color:red'>*</font></td>
					<th width="15%">区域</th>
					<td width="35%"><s:select  id="areaName"  name="whpqybzh.areaName" cssStyle="width: 60%" list="#{'':'请选择','一中队':'一中队','二中队':'二中队','三中队':'三中队','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道'}" theme="simple" /></td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>计划核查数</strong></td>
				</tr>
				<tr>
					<th width="15%">计划核查数合计</th>
					<td width="35%"><input name="whpqybzh.jhhcshj" value="${whpqybzh.jhhcshj}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">计划核查数生产</th>
					<td width="35%"><input name="whpqybzh.jhhcssc" value="${whpqybzh.jhhcssc}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">计划核查数使用</th>
					<td width="35%"><input name="whpqybzh.jhhcssy" value="${whpqybzh.jhhcssy}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">计划核查数仓储</th>
					<td width="35%"><input name="whpqybzh.jhhcscc" value="${whpqybzh.jhhcscc}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				    <th width="15%">计划核查数经营带储存</th>
					<td width="35%"><input name="whpqybzh.jhhcsjydsc" value="${whpqybzh.jhhcsjydsc}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">计划核查数加油站</th>
					<td width="35%"><input name="whpqybzh.jhhxsjyz" value="${whpqybzh.jhhxsjyz}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>已完成核查数</strong></td>
				</tr>
				<tr>
					<th width="15%">已完成核查数合计</th>
					<td width="35%"><input name="whpqybzh.ywchcshj" value="${whpqybzh.ywchcshj}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					
					<th width="15%">已完成核查数生产</th>
					<td width="35%"><input name="whpqybzh.ywchcssc" value="${whpqybzh.ywchcssc}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">已完成核查数使用</th>
					<td width="35%"><input name="whpqybzh.ywchcssy" value="${whpqybzh.ywchcssy}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">已完成核查数仓储</th>
					<td width="35%"><input name="whpqybzh.ywchcscc" value="${whpqybzh.ywchcscc}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">已完成核查数经营带储存</th>
					<td width="35%"><input name="whpqybzh.ywchcsjysc" value="${whpqybzh.ywchcsjysc}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">已完成核查数加油站</th>
					<td width="35%"><input name="whpqybzh.ywchcsjyz" value="${whpqybzh.ywchcsjyz}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
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
					
						<a href="#" class="btn_01"  onclick="parent.close_win('win_whpqybzh');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
