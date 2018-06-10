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
	<form name="myform1" method="post" enctype="multipart/form-data" action="samplingEvidenceSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="samplingEvidence.id" value="${samplingEvidence.id}">
		<input type="hidden" name="samplingEvidence.createTime" value="<fmt:formatDate type="both" value="${samplingEvidence.createTime}" />">
		<input type="hidden" name="samplingEvidence.updateTime" value="${samplingEvidence.updateTime}">
		<input type="hidden" name="samplingEvidence.createUserID" value="${samplingEvidence.createUserID}">
		<input type="hidden" name="samplingEvidence.updateUserID" value="${samplingEvidence.updateUserID}">
		<input type="hidden" name="samplingEvidence.deptId" value="${samplingEvidence.deptId}">
		<input type="hidden" name="samplingEvidence.delFlag" value="${samplingEvidence.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">现场负责人</th>
					<td width="35%"><input name="samplingEvidence.chargePerson" value="${samplingEvidence.chargePerson}" type="text" maxlength="127"></td>
					<th width="15%">执法人员</th>
					<td width="35%"><input name="samplingEvidence.lawOfficer" value="${samplingEvidence.lawOfficer}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">抽样取证结束时间</th>
					<td width="35%"><input name="samplingEvidence.endTime" value="${samplingEvidence.endTime}" type="text" maxlength="127"></td>
					<th width="15%">抽样地点</th>
					<td width="35%"><input name="samplingEvidence.forensicAddress" value="${samplingEvidence.forensicAddress}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%"><input name="samplingEvidence.relatedId" value="${samplingEvidence.relatedId}" type="text" maxlength="127"></td>
					<th width="15%">抽样取证开始时间</th>
					<td width="35%"><input name="samplingEvidence.startTime" value="${samplingEvidence.startTime}" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_samplingEvidence');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
