<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
		$(function(){
			if("${dishonesty.isPublic}"=='1')
			{
				$('.clsdiv').each(function(){
     				$(this).removeData("dataIgnore");
				});
			}
			else
			{
				$('.clsdiv').each(function(){
     				$(this).data("dataIgnore","dataIgnore");
				});
			}
		});
		function queryQy()
		{
			var szzid = document.getElementById('areaId').value;
			popupCenter("${ctx}/jsp/qyjbxx/queryCompanyList.action?flag=sxxw&entBaseInfo.enterprisePossession="+szzid, "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
		}
		
        function clearCompany(){
        	document.getElementById("companyName").value="";
        	document.getElementById("companyId").value="";
        }
        
        function changDiv(obj)
        {
        	if(obj == 1)
        	{
        		document.getElementById('div1').style.display = "";
        		$('.clsdiv').each(function(){
     				$(this).removeData("dataIgnore");
				});
        	}
        	else
        	{
        		document.getElementById('div1').style.display = "none";
        		$('.clsdiv').each(function(){
     				$(this).data("dataIgnore","dataIgnore");
				});	
        	}
        }
        
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="dishonestySave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="dishonesty.id" value="${dishonesty.id}">
		<input type="hidden" name="dishonesty.createTime" value="<fmt:formatDate type="both" value="${dishonesty.createTime}" />">
		<input type="hidden" name="dishonesty.updateTime" value="${dishonesty.updateTime}">
		<input type="hidden" name="dishonesty.createUserID" value="${dishonesty.createUserID}">
		<input type="hidden" name="dishonesty.updateUserID" value="${dishonesty.updateUserID}">
		<input type="hidden" name="dishonesty.deptId" value="${dishonesty.deptId}">
		<input type="hidden" name="dishonesty.delFlag" value="${dishonesty.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag style="width: 60%"   property="dishonesty.areaId" defaultText='请选择' codeName="企业属地" value="${dishonesty.areaId}" onchange="clearCompany()" dataType="*1-127" nullmsg='所在区域不能为空！' sucmsg='所在区域填写正确！'/><font style='color:red'>*</font></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input id="companyName" name="dishonesty.companyName" style="width: 60%" value="${dishonesty.companyName}" type="text" readonly="readonly" onclick="queryQy()" dataType="*1-127"  nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'/><font style='color:red'>*</font>
						<input type="hidden" id="companyId" name="dishonesty.companyId" value="${dishonesty.companyId}"/>
					</td>
				</tr> 
				<tr>
					<th width="15%">组织机构代码</th>
					<td width="35%"><input name="dishonesty.organizationCode" style="width: 60%" value="${dishonesty.organizationCode}" id='zzjgm' type="text" maxlength="100" readonly></td>
					<th width="15%">工商注册号</th>
					<td width="35%"><input name="dishonesty.businessRegistration" style="width: 60%" value="${dishonesty.businessRegistration}" id='gszch' type="text" maxlength="100" readonly></td>
				</tr>
				<tr>
					<th width="15%">处罚名称</th>
					<td width="35%"><input name="dishonesty.punishName" style="width: 60%" value="${dishonesty.punishName}" type="text" datatype="*1-100"  errormsg='处罚名称必须是1到100位字符！' nullmsg='处罚名称不能为空！' sucmsg='处罚名称填写正确！'  maxlength="100"><font style='color:red'>*</font></td>
					<th width="15%">处罚决定书文号</th>
					<td width="35%"><input name="dishonesty.symbolDecision" style="width: 60%" value="${dishonesty.symbolDecision}" type="text" datatype="*1-100"  errormsg='处罚决定书文号必须是1到100位字符！' nullmsg='处罚决定书文号不能为空！' sucmsg='处罚决定书文号填写正确！'  maxlength="100"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">被处罚当事人</th>
					<td width="35%"><input name="dishonesty.punishedParty" style="width: 60%" value="${dishonesty.punishedParty}" type="text" datatype="*1-100"  errormsg='被处罚当事人必须是1到100位字符！' nullmsg='被处罚当事人不能为空！' sucmsg='被处罚当事人填写正确！'  maxlength="100"><font style='color:red'>*</font></td>
					<th width="15%">被处罚当事人证件号码</th>
					<td width="35%"><input name="dishonesty.punishedNumber" style="width: 60%" value="${dishonesty.punishedNumber}" type="text" datatype="*1-100"  errormsg='被处罚当事人证件号码必须是1到100位字符！' nullmsg='被处罚当事人证件号码不能为空！' sucmsg='被处罚当事人证件号码填写正确！'  maxlength="100"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">处罚事由</th>
					<td width="35%"><input name="dishonesty.punishedSubject" style="width: 60%" value="${dishonesty.punishedSubject}" type="text" datatype="*1-100"  errormsg='处罚事由必须是1到100位字符！' nullmsg='处罚事由不能为空！' sucmsg='处罚事由填写正确！'  maxlength="100"><font style='color:red'>*</font></td>
					<th width="15%">处罚种类</th>
					<td width="35%"><cus:SelectOneTag style="width: 60%" datatype="*1-100" nullmsg='处罚种类必选！' property="dishonesty.punishedSpecies" defaultText='请选择' codeName="处罚种类" value="${dishonesty.punishedSpecies}" /><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">行政处罚依据</th>
					<td width="35%"><input name="dishonesty.punishedBasis" style="width: 60%" value="${dishonesty.punishedBasis}" type="text" datatype="*1-100"  errormsg='行政处罚依据必须是1到100位字符！' nullmsg='行政处罚依据不能为空！' sucmsg='行政处罚依据填写正确！'  maxlength="100"><font style='color:red'>*</font></td>
					<th width="15%">行政处罚结论</th>
					<td width="35%"><input name="dishonesty.punishedConclusion" style="width: 60%" value="${dishonesty.punishedConclusion}" type="text" datatype="*1-100"  errormsg='行政处罚结论必须是1到100位字符！' nullmsg='行政处罚结论不能为空！' sucmsg='行政处罚结论填写正确！'  maxlength="100"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">没收违法所得</th>
					<td width="35%"><input name="dishonesty.illegalIncome" style="width: 60%" value="${dishonesty.illegalIncome}" type="text" datatype="*1-100"  errormsg='没收违法所得必须是1到100位字符！' nullmsg='没收违法所得不能为空！' sucmsg='没收违法所得填写正确！'  maxlength="100"><font style='color:red'>*</font></td>
					<th width="15%">罚款金额</th>
					<td width="35%"><input name="dishonesty.fines" style="width: 60%" value="${dishonesty.fines}" type="text" datatype="*1-100"  errormsg='罚款金额必须是1到100位字符！' nullmsg='罚款金额不能为空！' sucmsg='罚款金额填写正确！'  maxlength="100"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">失信等级</th>
					<td width="35%"><cus:SelectOneTag style="width: 60%"   datatype="*1-100" nullmsg="失信等级必选！" property="dishonesty.creditRating" defaultText="请选择" codeName="失信等级" value="${dishonesty.creditRating}"   /><font style='color:red'>*</font></td>
					<th width="15%">处罚机关全称</th>
					<td width="35%"><input name="dishonesty.penalizingOrgan" style="width: 60%" value="${dishonesty.penalizingOrgan}" type="text" datatype="*1-100"  errormsg='处罚机关全称必须是1到100位字符！' nullmsg='处罚机关全称不能为空！' sucmsg='处罚机关全称填写正确！'  maxlength="100"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">行政处罚日期</th>
					<td width="35%"><input name="dishonesty.penalizingDate" style="width: 60%" datatype="*1-100"  nullmsg='行政处罚日期不能为空！'  value="<fmt:formatDate type='both' pattern="yyyy-MM-dd"  value='${dishonesty.penalizingDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font style='color:red'>*</font></td>
					<th width="15%">是否公示</th>
					<td width="35%"><cus:SelectOneTag  style="width: 60%" datatype="*1-100" nullmsg='是否公示必选！' property="dishonesty.isPublic" defaultText='请选择' codeName="是或否" value="${dishonesty.isPublic}" onchange="changDiv(this.value)"/><font style='color:red'>*</font></td>
				</tr>
				<tr id="div1" <s:if test="dishonesty.isPublic != 1">style="display:none"</s:if>>
					<th width="15%">公示起日期</th>
					<td width="35%"><input name="dishonesty.publicStartDate" style="width: 60%" id="publicStartDate" datatype="*1-100"  nullmsg='公示起日期不能为空！' value="<fmt:formatDate type='both'  pattern="yyyy-MM-dd" value='${dishonesty.publicStartDate}' />" type="text" class="Wdate clsdiv" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'publicEndDate\')}'})" ><font style='color:red'>*</font></td>
					<th width="15%">公示止日期</th>
					<td width="35%"><input name="dishonesty.publicEndDate" style="width: 60%" id="publicEndDate" datatype="*1-100"  nullmsg='公示止日期不能为空！' value="<fmt:formatDate type='both' pattern="yyyy-MM-dd"  value='${dishonesty.publicEndDate}' />" type="text" class="Wdate clsdiv" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'publicStartDate\')}'})" ><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">执行完成日期</th>
					<td width="35%"><input name="dishonesty.finishTime" style="width: 60%" datatype="*1-100"  nullmsg='执行完成日期不能为空！' value="<fmt:formatDate type='both' pattern="yyyy-MM-dd"  value='${dishonesty.finishTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font style='color:red'>*</font></td>
					<th width="15%">执行情况</th>
					<td width="35%"><input name="dishonesty.implementation" style="width: 60%" value="${dishonesty.implementation}" type="text" datatype="*1-100"  errormsg='执行情况必须是1到100位字符！' nullmsg='执行情况不能为空！' sucmsg='执行情况填写正确！'  maxlength="100"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
