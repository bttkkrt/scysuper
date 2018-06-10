<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>审核记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		var flag = ",${flag},";
		function beforeSubmitCallback()
		{
			if("${caseInfo.caseStatus}" == 8)
			{
				var glhNum = document.getElementById("glhNum").value;
				if(glhNum.length == 1 )
				{
					glhNum = "00" + glhNum;
				}
				else if(glhNum.length == 2)
				{
					glhNum = "0" + glhNum;
				}
				if(flag.indexOf(","+glhNum + ",") != -1)
				{
					alert("该案号已被使用，请更换！");
					hideLoading();
					document.getElementById("glhNum").focus();
					return false;
				}
			}
		}
	</script>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="caseInfoShenheSave.action">
		<s:token />
		<input type="hidden" name="caseInfo.id" value="${caseInfo.id}">
		<input type="hidden" name="caseInfo.caseStatus" value="${caseInfo.caseStatus}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">案件名称</th>
					<td width="35%">${caseInfo.caseName}</td>
					<th width="15%">案件时间</th>
					<td width="35%"><fmt:formatDate type='date' value='${caseInfo.caseTime}' /></td>
				</tr>
				<tr>
					<th width="15%">案发区域</th>
					<td width="35%">${caseInfo.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%">${caseInfo.companyName}</td>
				</tr>
				<tr>
					<th width="15%">立案时间</th>
					<td width="35%"><fmt:formatDate type='date' value='${caseInfo.laTime}'/></td>
					<th width="15%">案件编号</th>
					<td width="35%">
						<s:if test="caseInfo.caseStatus == 8">
							已使用案件编号：${flag}
							<br/>
							<input id="glhNum" name="caseInfo.glhNum" value="${caseInfo.glhNum}" type="text" datatype="*1-127" errormsg='案件编号必须是1到127位字符！' nullmsg='案件编号不能为空！' sucmsg='案件编号填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font>
						</s:if>
						<s:else>
							${caseInfo.caseId}
						</s:else>
					</td>
				</tr>
				<tr>
					<th width="15%">案由</th>
					<td width="85%" colspan="3">
						<textarea name="caseInfo.caseCause" style="width:96%;height:120px" readonly="readonly">${caseInfo.caseCause}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">案件来源</th>
					<td width="35%">
						<cus:hxlabel codeName="案件来源" itemValue="${caseInfo.caseSource}" />
					</td>
					<th width="15%">处罚对象</th>
					<td width="35%">
						<cus:hxlabel codeName="当事人类别" itemValue="${caseInfo.personType}" />
					</td>
				</tr>
				<s:if test="caseInfo.caseSource == 1">
				<tr>
					<th width="15%">轻伤人数</th>
					<td width="35%">${caseInfo.miborNum}</td>
					<th width="15%">重伤人数</th>
					<td width="35%">${caseInfo.injuriesNum}</td>
				</tr>
				<tr>
					<th width="15%">死亡人数</th>
					<td width="35%">${caseInfo.dieNum}</td>
					<th width="15%">事故级别</th>
					<td width="35%">
						<cus:hxlabel codeName="事故级别" itemValue="${caseInfo.accidentLevel}" />
					</td>
				</tr>
				<tr>
					<th width="15%">事故类别</th>
					<td width="85%" colspan="3">
						<cus:hxlabel codeName="事故类别" itemValue="${caseInfo.accidentCategory}" />
					</td>
				</tr>
				</s:if>
				<s:if test="caseInfo.personType == 1">
				<tr>
					<th width="15%">被处罚人</th>
					<td width="35%">${caseInfo.person}</td>
					<th width="15%">身份证号</th>
					<td width="35%">${caseInfo.sfzh}</td>
				</tr>
				<tr>
					<th width="15%">家庭住址</th>
					<td width="85%" colspan="3">${caseInfo.address}</td>
				</tr>
				</s:if>
				<s:if test="caseInfo.personType == 2">
				<tr>
					<th width="15%">法定代表人</th>
					<td width="85%" colspan="3">${caseInfo.fddbr}</td>
				</tr>
				</s:if>
				<tr>
					<th width="15%">年龄</th>
					<td width="35%">${caseInfo.age}</td>
					<th width="15%">性别</th>
					<td width="35%"><cus:hxlabel codeName="性别" itemValue="${caseInfo.sex}" /></td>
				</tr>
				<tr>
					<th width="15%">职务</th>
					<td width="35%">${caseInfo.zw}</td>
					<th width="15%">单位地址</th>
					<td width="35%">${caseInfo.companyAddress}</td>
				</tr>
				<tr>
					<th width="15%">电话</th>
					<td width="35%">${caseInfo.tele}</td>
					<th width="15%">邮编</th>
					<td width="35%">${caseInfo.zipCode}</td>
				</tr>
				<tr>
					<th width="15%">处罚类型</th>
					<td width="35%">
						<s:if test="caseInfo.fineType == 0">
							违法处罚
						</s:if>
						<s:elseif test="caseInfo.fineType == 1">
							事故处罚
						</s:elseif>
					</td>
					<th width="15%">承办人</th>
					<td width="35%">
						${caseInfo.undertakerName}
					</td>
				</tr>
				<tr>
					<th width="15%">当事人基本情况</th>
					<td width="85%" colspan="3">
						<textarea name="caseInfo.personCondition" style="width:96%;height:120px" readonly="readonly">${caseInfo.personCondition}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">案件基本情况</th>
					<td width="85%" colspan="3">
						<textarea name="caseInfo.caseCondition" style="width:96%;height:120px" readonly="readonly">${caseInfo.caseCondition}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="date" value="${cr.checkTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					</td>
				</tr>
					<tr>
						<th width="15%">审核结果</th>
						<td width="35%">
							<s:select name="caseInfo.result" cssStyle="width: 60%"  list="#{'0':'审核通过','1':'审核不通过'}" theme="simple"/>
						</td>
						<th width="15%">审核时间</th>
						<td width="35%"><input name="caseInfo.underTime" type="text" value="<fmt:formatDate type='date' value='${caseInfo.underTime}'  pattern='yyyy-MM-dd'/>" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*1-127"  nullmsg='审核时间不能为空！' sucmsg='审核时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">审核意见</th>
						<td width="85%" colspan="3">
							<textarea name="caseInfo.undertakerComment" style="width:96%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)"></textarea>
						</td>
					</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >确认<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_caseInfo');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
