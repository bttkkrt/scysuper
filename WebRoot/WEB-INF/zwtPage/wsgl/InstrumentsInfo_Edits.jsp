<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	var divvalue = "${instrumentsInfo.instrumentType}";
	$(function(){
		$('.clsdiv9').each(function(){
     		$(this).data("dataIgnore","dataIgnore");
		});
		$('.clsdiv11').each(function(){
     		$(this).data("dataIgnore","dataIgnore");
		});
		$('.clsdiv12').each(function(){
     		$(this).data("dataIgnore","dataIgnore");
		});
		$('.clsdiv'+divvalue).each(function(){
     		$(this).removeData("dataIgnore");
		});
	});
	
	function changDiv(obj)
	{
	    divvalue = obj;
	    document.getElementById('div9').style.display = "none";
	    document.getElementById('div11').style.display = "none";
	    document.getElementById('div12').style.display = "none";
	    $('.clsdiv9').each(function(){
     		$(this).data("dataIgnore","dataIgnore");
		});
		$('.clsdiv11').each(function(){
     		$(this).data("dataIgnore","dataIgnore");
		});
		$('.clsdiv12').each(function(){
     		$(this).data("dataIgnore","dataIgnore");
		});
		document.getElementById('div'+obj).style.display = "";
		$('.clsdiv'+divvalue).each(function(){
     		$(this).removeData("dataIgnore");
		});
	}
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: auto;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form class="checkform" id="myform1" name="myform1" method="post" enctype="multipart/form-data" action="instrumentsInfoSaves.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="instrumentsInfo.id" value="${instrumentsInfo.id}">
		<input type="hidden" name="instrumentsInfo.createTime" value="<fmt:formatDate type="both" value="${instrumentsInfo.createTime}" />">
		<input type="hidden" name="instrumentsInfo.updateTime" value="${instrumentsInfo.updateTime}">
		<input type="hidden" name="instrumentsInfo.createUserID" value="${instrumentsInfo.createUserID}">
		<input type="hidden" name="instrumentsInfo.updateUserID" value="${instrumentsInfo.updateUserID}">
		<input type="hidden" name="instrumentsInfo.deptId" value="${instrumentsInfo.deptId}">
		<input type="hidden" name="instrumentsInfo.delFlag" value="${instrumentsInfo.delFlag}">
		<input type="hidden" name="instrumentsInfo.linkId" value="${instrumentsInfo.linkId}">
		<input type="hidden" name="instrumentsInfo.fileName" value="${instrumentsInfo.fileName}">
		<input type="hidden" name="instrumentsInfo.wsh" value="${instrumentsInfo.wsh}">
		<input type="hidden" name="instrumentsInfo.ajbz" value="${instrumentsInfo.ajbz}">
		<input type="hidden" name="instrumentsInfo.ajh" value="${instrumentsInfo.ajh}">
		<input type="hidden" name="instrumentsInfo.ajhNum" value="${instrumentsInfo.ajhNum}">
		<input type="hidden" name="instrumentsInfo.ifCheck" value="${instrumentsInfo.ifCheck}">
		<input type="hidden" name="instrumentsInfo.ifPrint" value="${instrumentsInfo.ifPrint}">
		<input type="hidden" name="instrumentsInfo.lastFile" value="${instrumentsInfo.lastFile}"/>
		<input type="hidden" name="siteCheckRecord.id" value="${siteCheckRecord.id}">
		<input type="hidden" name="orderDeadlineBook.id" value="${orderDeadlineBook.id}">
		<input type="hidden" name="reviewSubmission.id" value="${reviewSubmission.id}">
		<input type="hidden" name="type" value="${type}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">文书时间</th>
					<td width="35%"><input id="time" name="instrumentsInfo.time" value="<fmt:formatDate type='date' value='${instrumentsInfo.time}' pattern='yyyy-MM-dd'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="127" style="width:50%"><font style='color:red'>*</font></td>
					<th width="15%">文书类型</th>
					<td width="35%"><s:select name="instrumentsInfo.instrumentType" cssStyle="width: 60%"  list="#{'':'请选择','9':'现场检查记录','11':'责令限期整改指令书','12':'整改复查意见书'}" theme="simple" onchange="changDiv(this.value)"  datatype="*1-127" errormsg='文书类型必须是1到127位字符！' nullmsg='文书类型不能为空！' sucmsg='文书类型填写正确！'  maxlength="127"  style="width:60%"/><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">被检查单位</th>
					<td width="35%"><input name="instrumentsInfo.companyName" value="${instrumentsInfo.companyName}" type="text" datatype="*1-127" errormsg='被检查单位是1到127位字符！' nullmsg='被检查单位不能为空！' sucmsg='被检查单位填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">处罚类型</th>
					<td width="35%">
						<s:select name="instrumentsInfo.personType" list="#{'':'请选择','0':'违法处罚','1':'事故处罚'}" theme="simple" datatype="*1-127" errormsg='处罚类型必须是1到127位字符！' nullmsg='处罚类型不能为空！' sucmsg='处罚类型填写正确！'  maxlength="127"  cssStyle="width:60%"/><font style='color:red'>*</font>
					</td>
				</tr>
				<!-- 现场检查记录  div9 -->
				<tr id="div9" <s:if test="instrumentsInfo.instrumentType!=9">style="display:none"</s:if>>
					<td colspan="4" style="padding:0 0 0 0;">
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<th width="15%">单位地址</th>
								<td width="85%" colspan="3"><input name="siteCheckRecord.companyAddress" value="${siteCheckRecord.companyAddress}" type="text" datatype="*1-127" errormsg='单位地址是1到127位字符！' nullmsg='单位地址不能为空！' sucmsg='单位地址填写正确！'  maxlength="127" style="width:60%" class="clsdiv9"><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">法定代表人（负责人）</th>
								<td width="35%"><input name="siteCheckRecord.chargePerson" value="${siteCheckRecord.chargePerson}" type="text"  datatype="*1-127" errormsg='法定代表人（负责人）必须是1到127位字符！' nullmsg='法定代表人（负责人）不能为空！' sucmsg='法定代表人（负责人）填写正确！'  maxlength="127" style="width:60%" class="clsdiv9"><font style='color:red'>*</font></td>
								<th width="15%">职务</th>
								<td width="35%"><input name="siteCheckRecord.chargePersonZw" value="${siteCheckRecord.chargePersonZw}" type="text" datatype="*1-127" errormsg='职务必须是1到127位字符！' nullmsg='职务不能为空！' sucmsg='职务填写正确！'  maxlength="127" style="width:60%" class="clsdiv9"><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">联系电话</th>
								<td width="35%"><input name="siteCheckRecord.chargePersonTel" value="${siteCheckRecord.chargePersonTel}" type="text"  datatype="*1-127" errormsg='电话必须是1到127位字符！' nullmsg='电话不能为空！' sucmsg='电话填写正确！'  maxlength="127" style="width:60%" class="clsdiv9"><font style='color:red'>*</font></td>
								<th width="15%">检查场所</th>
								<td width="35%"><input name="siteCheckRecord.checkAddress" value="${siteCheckRecord.checkAddress}" type="text" datatype="*1-127" errormsg='检查场所必须是1到127位字符！' nullmsg='检查场所不能为空！' sucmsg='检查场所填写正确！'  maxlength="127" style="width:80%" class="clsdiv9"><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">检查开始时间</th>
								<td width="35%">
									<input id="starttime4" name="siteCheckRecord.startTime" value="<fmt:formatDate type='both' value='${siteCheckRecord.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv9" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime4\')}'})" datatype="*1-127" errormsg='检查开始时间必须是1到127位字符！' nullmsg='检查开始时间不能为空！' sucmsg='检查开始时间填写正确！'  maxlength="127" style="width:60%"/><font style='color:red'>*</font>
								</td>
								<th width="15%">检查结束时间</th>
								<td width="35%">
									<input id="endtime4" name="siteCheckRecord.endTime" value="<fmt:formatDate type='both' value='${siteCheckRecord.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv9" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime4\')}'})" datatype="*1-127" errormsg='检查结束时间必须是1到127位字符！' nullmsg='检查结束时间不能为空！' sucmsg='检查结束时间填写正确！'  maxlength="127" style="width:60%"/><font style='color:red'>*</font>
								</td>
							</tr>
							<tr>
								<th width="15%">检查人员1</th>
								<td width="35%">
									<input type="hidden" name="siteCheckRecord.checkPersonName1" value="${siteCheckRecord.checkPersonName1}">
									${siteCheckRecord.checkPersonName1}
								</td>
								<th width="15%">检查人员2</th>
								<td width="35%">
									<cus:SelectOneTag property="siteCheckRecord.checkPerson" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${siteCheckRecord.checkPerson}" datatype="*1-127" errormsg='检查人员必须是1到127位字符！' nullmsg='检查人员不能为空！' sucmsg='检查人员填写正确！'  maxlength="127" style="width:60%" class="clsdiv9"/><font style='color:red'>*</font>
								</td>
							</tr>
							<tr>
								<th width="15%">检查情况</th>
								<td width="85%" colspan="3">
									<textarea name="siteCheckRecord.checkCondition" style="width:78%;height:120px" datatype="*1-2000" errormsg='检查情况必须是1到2000位字符！' nullmsg='检查情况不能为空！' sucmsg='检查情况填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv9">${siteCheckRecord.checkCondition}</textarea><font style='color:red'>*</font>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<!-- 责令限期整改指令书  div11 -->
				<tr id="div11" <s:if test="instrumentsInfo.instrumentType!=11">style="display:none"</s:if>>
					<td colspan="4" style="padding:0 0 0 0;">
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<th width="15%">执法人员1</th>
								<td width="35%">
									<input type="hidden" name="orderDeadlineBook.lawOfficerName1" value="${orderDeadlineBook.lawOfficerName1}">
									${orderDeadlineBook.lawOfficerName1}
								</td>
								<th width="15%">执法人员2</th>
								<td width="35%">
									<cus:SelectOneTag property="orderDeadlineBook.lawOfficer" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}' " value="${orderDeadlineBook.lawOfficer}" datatype="*1-127" errormsg='执法人员必须是1到127位字符！' nullmsg='执法人员不能为空！' sucmsg='执法人员填写正确！'  maxlength="127" style="width:60%" class="clsdiv11"/><font style='color:red'>*</font>
								</td>
							</tr>
							<tr>
								<th width="15%">问题</th>
								<td width="85%" colspan="3">
									<textarea name="orderDeadlineBook.problem" style="width:78%;height:120px" datatype="*1-2000" errormsg='问题必须是1到2000位字符！' nullmsg='问题不能为空！' sucmsg='问题填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv11">${orderDeadlineBook.problem}</textarea><font style='color:red'>*</font>
								</td>
							</tr>
							<tr>
								<th width="15%">整改项</th>
								<td width="35%"><input name="orderDeadlineBook.changeItem" value="${orderDeadlineBook.changeItem}" type="text" datatype="*1-127" errormsg='整改项必须是1到127位字符！' nullmsg='整改项不能为空！' sucmsg='整改项填写正确！'  maxlength="127" style="width:60%" class="clsdiv11"><font style='color:red'>*</font></td>
								<th width="15%">整改期限</th>
								<td width="35%"><input name="orderDeadlineBook.startTime" value="<fmt:formatDate type='date' value='${orderDeadlineBook.startTime}' />" type="text" class="Wdate clsdiv11" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*1-127" errormsg='整改期限必须是1到127位字符！' nullmsg='整改期限不能为空！' sucmsg='整改期限填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
							</tr>
						</table>
					</td>
				</tr>
				
				<!-- 整改复查意见书  div12 -->
				<tr id="div12" <s:if test="instrumentsInfo.instrumentType!=12">style="display:none"</s:if>>
					<td colspan="4" style="padding:0 0 0 0;">
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<th width="15%">责令整改时间</th>
								<td width="35%"><input name="reviewSubmission.xqzgTime" value="<fmt:formatDate type='date' value='${reviewSubmission.xqzgTime}' pattern='yyyy-MM-dd'/>" type="text" class="Wdate clsdiv12" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="127" style="width:60%" datatype="*1-127" errormsg='责令整改时间必须是1到127位字符！' nullmsg='责令整改时间不能为空！' sucmsg='责令整改时间填写正确！'><font style='color:red'>*</font>
								</td>
								<th width="15%">责令整改决定</th>
								<td width="35%"><input name="reviewSubmission.zgjd" value="${reviewSubmission.zgjd}" type="text" datatype="*1-127" errormsg='责令整改决定必须是1到127位字符！' nullmsg='责令整改决定不能为空！' sucmsg='责令整改决定填写正确！'  maxlength="127" style="width:80%" class="clsdiv12"><font style='color:red'>*</font>
								</td>
							</tr>
							<tr>
								<th width="15%">责令整改文号</th>
								<td width="85%" colspan="3"><input name="reviewSubmission.jdajbz" value="${reviewSubmission.jdajbz}" type="text" datatype="*1-127"  maxlength="127" class="clsdiv12">〔<input name="reviewSubmission.zfh" value="${reviewSubmission.zfh}" type="text" datatype="*1-127"  maxlength="127" class="clsdiv12">〕<input name="reviewSubmission.zfhNum" value="${reviewSubmission.zfhNum}" type="text" datatype="*1-127"  maxlength="127"  class="clsdiv12">号
								</td>
							</tr>
							<tr>
								<th width="15%">执法人员1</th>
								<td width="35%">
									<input type="hidden" name="reviewSubmission.lawOfficerName1" value="${reviewSubmission.lawOfficerName1}">
									${reviewSubmission.lawOfficerName1}
								</td>
								<th width="15%">执法人员2</th>
								<td width="35%">
									<cus:SelectOneTag property="reviewSubmission.lawOfficer" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}' " value="${reviewSubmission.lawOfficer}" datatype="*1-127" errormsg='执法人员必须是1到127位字符！' nullmsg='执法人员不能为空！' sucmsg='执法人员填写正确！'  maxlength="127" style="width:60%" class="clsdiv12"/><font style='color:red'>*</font>
								</td>
							</tr>
							<tr>
								<th width="15%">复查意见</th>
								<td width="85%" colspan="3">
									<textarea name="reviewSubmission.reviewComment" style="width:78%;height:120px" datatype="*1-2000" errormsg='复查意见必须是1到2000位字符！' nullmsg='复查意见不能为空！' sucmsg='复查意见填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv12">${reviewSubmission.reviewComment}</textarea><font style='color:red'>*</font>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit">提交<b></b></a>&nbsp;
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
