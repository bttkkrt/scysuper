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
	<form name="myform1" method="post" enctype="multipart/form-data" action="aqscsgyhpcSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="aqscsgyhpc.id" value="${aqscsgyhpc.id}">
		<input type="hidden" name="aqscsgyhpc.createTime" value="<fmt:formatDate type="both" value="${aqscsgyhpc.createTime}" />">
		<input type="hidden" name="aqscsgyhpc.updateTime" value="${aqscsgyhpc.updateTime}">
		<input type="hidden" name="aqscsgyhpc.createUserID" value="${aqscsgyhpc.createUserID}">
		<input type="hidden" name="aqscsgyhpc.updateUserID" value="${aqscsgyhpc.updateUserID}">
		<input type="hidden" name="aqscsgyhpc.deptId" value="${aqscsgyhpc.deptId}">
		<input type="hidden" name="aqscsgyhpc.delFlag" value="${aqscsgyhpc.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">年度</th>
					<td width="35%"><input name="aqscsgyhpc.yearTime"  style="width:60%" value="<fmt:formatDate type='both' value='${aqscsgyhpc.yearTime}' pattern="yyyy"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy'})"><font style='color:red'>*</font></td>
					<th width="15%">区域</th>
					
					<td width="35%"><s:select  id="areaName"   cssStyle="width:60%" name="aqscsgyhpc.areaName" list="#{'':'请选择','中新合作区':'中新合作区','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道','其他部门':'其他部门'}" theme="simple" /></td>
				</tr>
				<tr>
					<th width="15%">已覆盖企业数量</th>
					<td width="35%"><input name="aqscsgyhpc.yfgqysl"  style="width:60%" value="${aqscsgyhpc.yfgqysl}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">已覆盖规模以上企业数量</th>
					<td width="35%"><input name="aqscsgyhpc.yfggmysqysl"  style="width:60%" value="${aqscsgyhpc.yfggmysqysl}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">年度累计排查隐患数量</th>
					<td width="35%"><input name="aqscsgyhpc.ndljpcthsl"  style="width:60%" value="${aqscsgyhpc.ndljpcthsl}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">年度累计整改隐患数量</th>
					<td width="35%"><input name="aqscsgyhpc.ndljzgyhsl"  style="width:60%" value="${aqscsgyhpc.ndljzgyhsl}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="aqscsgyhpc.remark" style="width:96%;height:60px">${aqscsgyhpc.remark}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_aqscsgyhpc');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
