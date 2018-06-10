<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>现场检查记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="supTasXcjcSave.action">
		<s:token />
		<input type="hidden" name="supTas.id" value="${supTas.id}">
		<input type="hidden" name="siteCheckRecord.id" value="${siteCheckRecord.id}">
		<input type="hidden" name="siteCheckRecord.relatedId" value="${siteCheckRecord.relatedId}">
		
			<table width="100%" border="0">
				<tr>
						<th width="15%">检查开始时间</th>
						<td width="35%">
							<input id="starttime4" name="siteCheckRecord.startTime" style="width:60%" value="<fmt:formatDate type='both' value='${siteCheckRecord.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime4\')}'})" datatype="*1-127" errormsg='检查开始时间必须是1到127位字符！' nullmsg='检查开始时间不能为空！' sucmsg='检查开始时间填写正确！'  maxlength="127" style="width:50%"/><font style='color:red'>*</font>
						</td>
						<th width="15%">检查结束时间</th>
						<td width="35%">
							<input id="endtime4" name="siteCheckRecord.endTime" style="width:60%" value="<fmt:formatDate type='both' value='${siteCheckRecord.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime4\')}'})" datatype="*1-127" errormsg='检查结束时间必须是1到127位字符！' nullmsg='检查结束时间不能为空！' sucmsg='检查结束时间填写正确！'  maxlength="127" style="width:50%"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">检查人员</th>
						<td width="35%">
							<input type="hidden" name="siteCheckRecord.checkPersonName" value="${siteCheckRecord.checkPersonName}">
							<input type="hidden" name="siteCheckRecord.checkPerson" value="${siteCheckRecord.checkPerson}">
							${siteCheckRecord.checkPersonName}
						</td>
					</tr>
					<tr>
						<th width="15%">检查场所</th>
						<td width="85%" colspan="3"><input name="siteCheckRecord.checkAddress" value="${siteCheckRecord.checkAddress}" type="text" datatype="*1-127" errormsg='检查场所必须是1到127位字符！' nullmsg='检查场所不能为空！' sucmsg='检查场所填写正确！'  maxlength="127" style="width:80%" class="clsdiv9"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">检查情况</th>
						<td width="85%" colspan="3">
							<textarea name="siteCheckRecord.checkCondition" style="width:78%;height:120px" datatype="*1-2000" errormsg='检查情况必须是1到2000位字符！' nullmsg='检查情况不能为空！' sucmsg='检查情况填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv9">${siteCheckRecord.checkCondition}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >确认<b></b></a>&nbsp;
 					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
