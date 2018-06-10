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
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="safStaScoSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="safStaSco.id" value="${safStaSco.id}">
		<input type="hidden" name="safStaSco.createTime" value="<fmt:formatDate type="both" value="${safStaSco.createTime}" />">
		<input type="hidden" name="safStaSco.updateTime" value="${safStaSco.updateTime}">
		<input type="hidden" name="safStaSco.createUserID" value="${safStaSco.createUserID}">
		<input type="hidden" name="safStaSco.updateUserID" value="${safStaSco.updateUserID}">
		<input type="hidden" name="safStaSco.deptId" value="${safStaSco.deptId}">
		<input type="hidden" name="safStaSco.delFlag" value="${safStaSco.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><input name="safStaSco.areaName" value="${safStaSco.areaName}" type="text" maxlength="127" style="width:60%"></td>
					<th width="15%">评分时间</th>
					<td width="35%"><input name="safStaSco.patingDate"  style="width:60%" value="<fmt:formatDate type='both' value='${safStaSco.patingDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
				</tr>
				<tr>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="safStaSco.companyName" value="${safStaSco.companyName}" type="text" maxlength="127" style="width:60%"></td>
					<th width="15%">总分</th>
					<td width="35%"><input name="safStaSco.totalScore" value="${safStaSco.totalScore}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">评分人</th>
					<td width="35%"><input name="safStaSco.ratingUserId" value="${safStaSco.ratingUserId}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_safStaSco');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
