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
	<form name="myform1" method="post" enctype="multipart/form-data" action="sjzfgpdbSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="sjzfgpdb.id" value="${sjzfgpdb.id}">
		<input type="hidden" name="sjzfgpdb.createTime" value="<fmt:formatDate type="both" value="${sjzfgpdb.createTime}" />">
		<input type="hidden" name="sjzfgpdb.updateTime" value="${sjzfgpdb.updateTime}">
		<input type="hidden" name="sjzfgpdb.createUserID" value="${sjzfgpdb.createUserID}">
		<input type="hidden" name="sjzfgpdb.updateUserID" value="${sjzfgpdb.updateUserID}">
		<input type="hidden" name="sjzfgpdb.deptId" value="${sjzfgpdb.deptId}">
		<input type="hidden" name="sjzfgpdb.delFlag" value="${sjzfgpdb.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">月份</th>
					<td width="35%"><input name="sjzfgpdb.monthTime" style="width: 60%" value="<fmt:formatDate type='both' value='${sjzfgpdb.monthTime}'  pattern="yyyy-MM"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"><font style='color:red'>*</font></td>
					<th width="15%">区域</th>
					<td width="35%"><s:select  id="areaName"  style="width: 60%" name="sjzfgpdb.areaName" list="#{'':'请选择','中新合作区':'中新合作区','科教创新区':'科教创新区','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道'}" theme="simple" /></td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>总体情况</strong></td>
				</tr>
				<tr>
					<th width="15%">总体挂牌数</th>
					<td width="35%"><input name="sjzfgpdb.ztgps" style="width: 60%" value="${sjzfgpdb.ztgps}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">总体已整改数</th>
					<td width="35%"><input name="sjzfgpdb.ztyzgs" style="width: 60%" value="${sjzfgpdb.ztyzgs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">总体投入整改资金</th>
					<td width="35%"><input name="sjzfgpdb.zttrzgzj" style="width: 60%" value="${sjzfgpdb.zttrzgzj}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>苏州市级</strong></td>
				</tr>
				<tr>
					<th width="15%">市级挂牌数</th>
					<td width="35%"><input name="sjzfgpdb.sjgps" style="width: 60%" value="${sjzfgpdb.sjgps}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">市级已整改数</th>
					<td width="35%"><input name="sjzfgpdb.sjyzgs" style="width: 60%" value="${sjzfgpdb.sjyzgs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">投入经费</th>
					<td width="35%"><input name="sjzfgpdb.sjtrjf" style="width: 60%" value="${sjzfgpdb.sjtrjf}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>县（市、区）级</strong></td>
				</tr>
				<tr>
					<th width="15%">县级挂牌数</th>
					<td width="35%"><input name="sjzfgpdb.xjgps" style="width: 60%" value="${sjzfgpdb.xjgps}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"></td>
					<th width="15%">县级已整改数</th>
					<td width="35%"><input name="sjzfgpdb.xjyzgs" style="width: 60%" value="${sjzfgpdb.xjyzgs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">投入经费</th>
					<td width="35%"><input name="sjzfgpdb.xjtrjf" style="width: 60%" value="${sjzfgpdb.xjtrjf}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>乡镇（街道）级</strong></td>
				</tr>
				<tr>
					<th width="15%">镇级挂牌数</th>
					<td width="35%"><input name="sjzfgpdb.zjgps" style="width: 60%" value="${sjzfgpdb.zjgps}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">镇级已整改数</th>
					<td width="35%"><input name="sjzfgpdb.zjyzgs" style="width: 60%" value="${sjzfgpdb.zjyzgs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">投入经费</th>
					<td width="35%"><input name="sjzfgpdb.zjtrjf" style="width: 60%" value="${sjzfgpdb.zjtrjf}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_sjzfgpdb');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
