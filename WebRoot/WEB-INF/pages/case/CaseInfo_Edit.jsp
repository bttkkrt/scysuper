<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		$(function()
		{
			var aa = "${caseInfo.personType}";
			if(aa != '1')
			{
				$('.clsdiv').each(function(){
     				$(this).data("dataIgnore","dataIgnore")
				});
				$('.clsdiv2').each(function(){
     				$(this).removeData("dataIgnore");
				});
			}
			else
			{
				$('.clsdiv2').each(function(){
     				$(this).data("dataIgnore","dataIgnore")
				});
				$('.clsdiv').each(function(){
     				$(this).removeData("dataIgnore");
				});
			}
			var bb = "${caseInfo.caseSource}";
			if(bb != '1')
			{
				$('.clsdiv1').each(function(){
     				$(this).data("dataIgnore","dataIgnore")
				});
			}
		});
		
		
		function queryQy()
		{
			popupCenter("${ctx}/jsp/qyjbxx/queryCompanyList.action?flag=idmc", "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
		}
		
        
        function changeDsr(obj)
        {
        	if(obj == 1)
        	{
        		document.getElementById('div1').style.display = "";
        		document.getElementById('div2').style.display = "";
        		document.getElementById('div3').style.display = "none";
        		$('.clsdiv').each(function(){
     				$(this).removeData("dataIgnore");
				});
				$('.clsdiv2').each(function(){
     				$(this).data("dataIgnore","dataIgnore")
				});
        	}
        	else
        	{
        		document.getElementById('div3').style.display = "";
        		document.getElementById('div1').style.display = "none";
        		document.getElementById('div2').style.display = "none";
        		$('.clsdiv2').each(function(){
     				$(this).removeData("dataIgnore");
				});
        		$('.clsdiv').each(function(){
     				$(this).data("dataIgnore","dataIgnore")
				});
        	}
        }
        
        function showSgInfo(obj)
        {
        	if(obj == 1)
        	{
        		document.getElementById('sgdiv1').style.display = "";
        		document.getElementById('sgdiv2').style.display = "";
        		document.getElementById('sgdiv3').style.display = "";
        		$('.clsdiv1').each(function(){
     				$(this).removeData("dataIgnore");
				});
        	}
        	else
        	{
        		document.getElementById('sgdiv1').style.display = "none";
        		document.getElementById('sgdiv2').style.display = "none";
        		document.getElementById('sgdiv3').style.display = "none";
        		$('.clsdiv1').each(function(){
     				$(this).data("dataIgnore","dataIgnore")
				});
        	}
        }
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form class="checkform" name="myform1" method="post" enctype="multipart/form-data" action="caseInfoSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="type" value="${type}">
		<input type="hidden" name="caseInfo.id" value="${caseInfo.id}">
		<input type="hidden" name="caseInfo.createTime" value="<fmt:formatDate type="both" value="${caseInfo.createTime}" />">
		<input type="hidden" name="caseInfo.updateTime" value="${caseInfo.updateTime}">
		<input type="hidden" name="caseInfo.createUserID" value="${caseInfo.createUserID}">
		<input type="hidden" name="caseInfo.updateUserID" value="${caseInfo.updateUserID}">
		<input type="hidden" name="caseInfo.deptId" value="${caseInfo.deptId}">
		<input type="hidden" name="caseInfo.delFlag" value="${caseInfo.delFlag}">
		<input type="hidden" name="caseInfo.caseStatus" value="${caseInfo.caseStatus}">
		<input type="hidden" name="caseInfo.caseId" value="${caseInfo.caseId}">
		
		<s:if test="type == 1">
		<input type="hidden" name="caseInfo.underTime" value="${caseInfo.underTime}">
		<input type="hidden" name="caseInfo.checkComment" value="${caseInfo.checkComment}">
		<input type="hidden" name="caseInfo.checkPersonId" value="${caseInfo.checkPersonId}">
		<input type="hidden" name="caseInfo.checkPersonName" value="${caseInfo.checkPersonName}">
		<input type="hidden" name="caseInfo.checkTime" value="${caseInfo.checkTime}">
		<input type="hidden" name="caseInfo.approvalComment" value="${caseInfo.approvalComment}">
		<input type="hidden" name="caseInfo.approvalId" value="${caseInfo.approvalId}">
		<input type="hidden" name="caseInfo.approvalName" value="${caseInfo.approvalName}">
		<input type="hidden" name="caseInfo.approvalTime" value="${caseInfo.approvalTime}">
		<input type="hidden" name="caseInfo.gajbz" value="${caseInfo.gajbz}">
		<input type="hidden" name="caseInfo.glh" value="${caseInfo.glh}">
		<input type="hidden" name="caseInfo.glhNum" value="${caseInfo.glhNum}">
		<input type="hidden" name="caseInfo.jaTime" value="${caseInfo.jaTime}">
		<input type="hidden" name="caseInfo.laTime" value="${caseInfo.laTime}">
		<input type="hidden" name="caseInfo.gdTime" value="${caseInfo.gdTime}">
		<input type="hidden" name="caseInfo.gdNum" value="${caseInfo.gdNum}">
		<input type="hidden" name="caseInfo.bcqx" value="${caseInfo.bcqx}">
		<input type="hidden" name="caseInfo.approvalResult" value="${caseInfo.approvalResult}">
		<input type="hidden" name="caseInfo.gdhttpurl" value="${caseInfo.gdhttpurl}">
		<input type="hidden" name="caseInfo.gdnwurl" value="${caseInfo.gdnwurl}">
		<input type="hidden" name="caseInfo.sySize" value="${caseInfo.sySize}">
		<input type="hidden" name="caseInfo.jnmuSize" value="${caseInfo.jnmuSize}">
		<input type="hidden" name="caseInfo.fwcheck" value="${caseInfo.fwcheck}">
		<input type="hidden" name="caseInfo.dzqmcheck" value="${caseInfo.dzqmcheck}">
		<input type="hidden" name="caseInfo.dzcheck" value="${caseInfo.dzcheck}">
		<input type="hidden" name="caseInfo.jzcheck" value="${caseInfo.jzcheck}">
		</s:if>
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">案件名称</th>
					<td width="35%"><input name="caseInfo.caseName" value="${caseInfo.caseName}" type="text" datatype="*1-127" errormsg='案件名称必须是1到127位字符！' nullmsg='案件名称不能为空！' sucmsg='案件名称填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">案件时间</th>
					<td width="35%"><input name="caseInfo.caseTime" value="<fmt:formatDate type='date' value='${caseInfo.caseTime}' pattern='yyyy-MM-dd'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*1-127"  nullmsg='案件时间不能为空！' sucmsg='案件时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">案发区域</th>
					<td width="35%"><cus:SelectOneTag property="caseInfo.areaId" defaultText='请选择' codeName="企业属地" value="${caseInfo.areaId}" datatype="*1-127" errormsg='所在区域必须是1到127位字符！' nullmsg='所在区域不能为空！' sucmsg='所在区域填写正确！'  maxlength="127" style="width:60%"/><font style='color:red'>*</font></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input id="companyName" name="caseInfo.companyName" value="${caseInfo.companyName}" type="text" datatype="*1-127" errormsg='企业名称必须是1到127位字符！' nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'  maxlength="127" style="width:60%" onclick="queryQy()"/><font style='color:red'>*</font>
						<input type="hidden" id="companyId" name="caseInfo.companyId" value="${caseInfo.companyId}"/>
					</td>
				</tr>
				<tr>
					<th width="15%">立案时间</th>
					<td width="35%"><input name="caseInfo.laTime" value="<fmt:formatDate type='date' value='${caseInfo.laTime}' pattern='yyyy-MM-dd'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*1-127"  nullmsg='立案时间不能为空！' sucmsg='立案时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">处罚类型</th>
					<td width="35%">
						<s:select name="caseInfo.fineType" list="#{'':'请选择','0':'违法处罚','1':'事故处罚'}" theme="simple" datatype="*1-127" errormsg='处罚类型必须是1到127位字符！' nullmsg='处罚类型不能为空！' sucmsg='处罚类型填写正确！'  maxlength="127"  cssStyle="width:60%"/><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">案件来源</th>
					<td width="35%">
						<cus:SelectOneTag property="caseInfo.caseSource" defaultText='请选择' codeName="案件来源" value="${caseInfo.caseSource}" onchange="showSgInfo(this.value)" datatype="*1-127" errormsg='案件来源必须是1到127位字符！' nullmsg='案件来源不能为空！' sucmsg='案件来源填写正确！'  maxlength="127" style="width:60%"/><font style='color:red'>*</font>
					</td>
					<th width="15%">处罚对象</th>
					<td width="35%"><cus:SelectOneTag property="caseInfo.personType" defaultText='请选择' codeName="当事人类别" value="${caseInfo.personType}" onchange="changeDsr(this.value)" datatype="*1-127" errormsg='处罚对象必须是1到127位字符！' nullmsg='处罚对象不能为空！' sucmsg='处罚对象填写正确！'  maxlength="127" style="width:60%"/><font style='color:red'>*</font></td>
				</tr>
				<tr id="sgdiv1" <s:if test="flag == 'add' || caseInfo.caseSource != 1">style="display:none"</s:if>>
					<th width="15%">轻伤人数</th>
					<td width="35%"><input name="caseInfo.miborNum" value="${caseInfo.miborNum}" type="text" datatype="n1-127" errormsg='轻伤人数必须是1到127位数字！' nullmsg='轻伤人数不能为空！' sucmsg='轻伤人数填写正确！'  maxlength="127" style="width:60%" class="clsdiv1"><font style='color:red'>*</font></td>
					<th width="15%">重伤人数</th>
					<td width="35%"><input name="caseInfo.injuriesNum" value="${caseInfo.injuriesNum}" type="text" datatype="n1-127" errormsg='重伤人数必须是1到127位数字！' nullmsg='重伤人数不能为空！' sucmsg='重伤人数填写正确！'  maxlength="127" style="width:60%" class="clsdiv1"><font style='color:red'>*</font></td>
				</tr>
				<tr id="sgdiv2" <s:if test="flag == 'add' || caseInfo.caseSource != 1">style="display:none"</s:if>>
					<th width="15%">死亡人数</th>
					<td width="35%"><input name="caseInfo.dieNum" value="${caseInfo.dieNum}" type="text" datatype="n1-127" errormsg='死亡人数必须是1到127位数字！' nullmsg='死亡人数不能为空！' sucmsg='死亡人数填写正确！'  maxlength="127" style="width:60%" class="clsdiv1"><font style='color:red'>*</font></td>
					<th width="15%">事故级别</th>
					<td width="35%"><cus:SelectOneTag property="caseInfo.accidentLevel" defaultText='请选择' codeName="事故级别" value="${caseInfo.accidentLevel}" datatype="*1-127" errormsg='事故级别必须是1到127位字符！' nullmsg='事故级别不能为空！' sucmsg='事故级别填写正确！'  maxlength="127" style="width:60%" class="clsdiv1"/><font style='color:red'>*</font></td>
				</tr>
				<tr id="sgdiv3" <s:if test="flag == 'add' || caseInfo.caseSource != 1">style="display:none"</s:if>>
					<th width="15%">事故类别</th>
					<td width="85%" colspan="3"><cus:SelectOneTag property="caseInfo.accidentCategory" defaultText='请选择' codeName="事故类别" value="${caseInfo.accidentCategory}" datatype="*1-127" errormsg='事故类别必须是1到127位字符！' nullmsg='事故类别不能为空！' sucmsg='事故类别填写正确！'  maxlength="127" style="width:60%" class="clsdiv1"/><font style='color:red'>*</font></td>
				</tr>
				<tr id="div1" <s:if test="flag == 'add' || caseInfo.personType != 1">style="display:none"</s:if>>
					<th width="15%">被处罚人</th>
					<td width="35%"><input name="caseInfo.person" value="${caseInfo.person}" type="text" datatype="*1-127" errormsg='被处罚人必须是1到127位字符！' nullmsg='被处罚人不能为空！' sucmsg='被处罚人填写正确！'  maxlength="127" style="width:60%" class="clsdiv"><font style='color:red'>*</font></td>
					<th width="15%">身份证号</th>
					<td width="35%"><input name="caseInfo.sfzh" value="${caseInfo.sfzh}" type="text" datatype="idcard" errormsg='身份证号格式错误！' nullmsg='身份证号不能为空！' sucmsg='身份证号填写正确！'  maxlength="18" style="width:60%" class="clsdiv"><font style='color:red'>*</font></td>
				</tr>
				<tr id="div2" <s:if test="flag == 'add' || caseInfo.personType != 1">style="display:none"</s:if>>
					<th width="15%">家庭住址</th>
					<td width="85%" colspan="3"><input name="caseInfo.address" value="${caseInfo.address}" type="text"  datatype="*1-127" errormsg='家庭住址必须是1到127位字符！' nullmsg='家庭住址不能为空！' sucmsg='家庭住址填写正确！'  maxlength="127" style="width:60%" class="clsdiv"><font style='color:red'>*</font></td>
				</tr>
				<tr id="div3" <s:if test="flag == 'add' || caseInfo.personType == 1">style="display:none"</s:if>>
					<th width="15%">法定代表人</th>
					<td width="35%"><input id="fddbr" name="caseInfo.fddbr" value="${caseInfo.fddbr}" type="text"  datatype="*1-127" errormsg='法定代表人必须是1到127位字符！' nullmsg='法定代表人不能为空！' sucmsg='法定代表人填写正确！'  maxlength="127" style="width:60%" class="clsdiv2"><font style='color:red'>*</font></td>
					<th width="15%">统一社会信用代码</th>
					<td width="35%"><input name="caseInfo.xyhm" value="${caseInfo.xyhm}" type="text"  datatype="*1-127" errormsg='统一社会信用代码必须是1到127位字符！' nullmsg='统一社会信用代码不能为空！' sucmsg='统一社会信用代码填写正确！'  maxlength="127" style="width:60%" class="clsdiv2"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">年龄</th>
					<td width="35%"><input id="age" name="caseInfo.age" value="${caseInfo.age}" type="text"  datatype="n1-3" errormsg='年龄必须是数字！' nullmsg='年龄不能为空！' sucmsg='年龄填写正确！'  maxlength="3" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">性别</th>
					<td width="35%"><cus:SelectOneTag property="caseInfo.sex" defaultText='请选择' codeName="性别" value="${caseInfo.sex}"   datatype="*1-127" errormsg='性别必须是1到127位字符！' nullmsg='性别不能为空！' sucmsg='性别填写正确！'  maxlength="127" style="width:60%" /><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">职务</th>
					<td width="35%"><input id="zw" name="caseInfo.zw" value="${caseInfo.zw}" type="text" datatype="*1-127" errormsg='职务必须是1到127位字符！' nullmsg='职务不能为空！' sucmsg='职务填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">单位地址</th>
					<td width="35%"><input id="companyAddress" name="caseInfo.companyAddress" value="${caseInfo.companyAddress}" type="text" datatype="*1-127" errormsg='单位地址是1到127位字符！' nullmsg='单位地址不能为空！' sucmsg='单位地址填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">电话</th>
					<td width="35%"><input id="tele" name="caseInfo.tele" value="${caseInfo.tele}" type="text"  datatype="*1-127" errormsg='电话必须是1到127位字符！' nullmsg='电话不能为空！' sucmsg='电话填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">邮编</th> 
					<td width="35%"><input name="caseInfo.zipCode" value="${caseInfo.zipCode}" type="text" datatype="p" errormsg='邮编格式错误！' nullmsg='邮编不能为空！' sucmsg='邮编填写正确！'  maxlength="6" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">承办人</th>
					<td width="35%">
						<input type="hidden" name="caseInfo.undertakerName1" value="${caseInfo.undertakerName1}">
						${caseInfo.undertakerName1}
					</td>
					<th width="15%">协办人</th>
					<td width="35%">
						<s:select theme="simple" emptyOption="true" name="caseInfo.undertakerId" list="%{userList}" listKey="id" listValue="displayName" value="{caseInfo.undertakerId}" cssStyle="width:60%" datatype="*1-127"  nullmsg='承办人不能为空！' sucmsg='承办人填写正确！'  maxlength="127"></s:select><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">案由</th>
					<td width="85%" colspan="3">
						<textarea name="caseInfo.caseCause" style="width:78%;height:120px" datatype="*1-2000" errormsg='案由必须是1到2000位字符！' nullmsg='案由不能为空！' sucmsg='案由填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${caseInfo.caseCause}</textarea><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">当事人基本情况</th>
					<td width="85%" colspan="3">
						<textarea name="caseInfo.personCondition" style="width:78%;height:120px" datatype="*1-2000" errormsg='当事人基本情况必须是1到2000位字符！' nullmsg='当事人基本情况不能为空！' sucmsg='当事人基本情况填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${caseInfo.personCondition}</textarea><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">案件基本情况</th>
					<td width="35%" colspan="3">
						<textarea name="caseInfo.caseCondition" style="width:78%;height:120px" datatype="*1-2000" errormsg='案件基本情况必须是1到2000位字符！' nullmsg='案件基本情况不能为空！' sucmsg='案件基本情况填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${caseInfo.caseCondition}</textarea><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">承办人意见</th>
					<td width="85%" colspan="3">
						<textarea name="caseInfo.undertakerComment" style="width:78%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${caseInfo.undertakerComment}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_caseInfo');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
