<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	$(function(){
		if("${dishonesty.creditRating}"=='3')
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
	
	function checkChange(){
		if("${dishonesty.creditRating}"=='3'){
			if($("#checkSelect").val()=='审批通过' || $("#checkSelect").val()=='审批通过并上报信用平台'){
				document.getElementById('term1').style.display="";
				document.getElementById('term2').style.display="";
				$('.clsdiv').each(function(){
     				$(this).removeData("dataIgnore");
				});
			}else{
				document.getElementById('term1').style.display="none";
				document.getElementById('term2').style.display="none";
				$('.clsdiv').each(function(){
     				$(this).data("dataIgnore","dataIgnore");
				});	
			}
		
		}
	}
	</script>
</head>
<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="dishonestyCheckSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="dishonesty.id" value="${dishonesty.id}">
		<input type="hidden" name="checkRecord.infoId" value="${dishonesty.id}">
			<table width="100%">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${dishonesty.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${dishonesty.companyName}</td>
				</tr>
				<tr>
					<th width="15%">组织机构代码</th>
					<td width="35%" >${dishonesty.organizationCode}</td>
					<th width="15%">工商注册号</th>
					<td width="35%" >${dishonesty.businessRegistration}</td>
				</tr>
				<tr>
					<th width="15%">处罚名称</th> 
					<td width="35%" >${dishonesty.punishName}</td>
					<th width="15%">处罚决定书文号</th>
					<td width="35%" >${dishonesty.symbolDecision}</td>
				</tr>
				<tr>
					<th width="15%">被处罚当事人</th>
					<td width="35%" >${dishonesty.punishedParty}</td>
					<th width="15%">被处罚当事人证件号码</th>
					<td width="35%" >${dishonesty.punishedNumber}</td>
				</tr>
				<tr>
					<th width="15%">处罚事由</th>
					<td width="35%" >${dishonesty.punishedSubject}</td>
					<th width="15%">处罚种类</th>
					<td width="35%" ><cus:hxlabel codeName="处罚种类" itemValue="${dishonesty.punishedSpecies}" /></td>
				</tr>
				<tr>
					<th width="15%">行政处罚依据</th>
					<td width="35%" >${dishonesty.punishedBasis}</td>
					<th width="15%">行政处罚结论</th>
					<td width="35%" >${dishonesty.punishedConclusion}</td>
				</tr>
				<tr>
					<th width="15%">没收违法所得</th>
					<td width="35%" >${dishonesty.illegalIncome}</td>
					<th width="15%">罚款金额</th>
					<td width="35%" >${dishonesty.fines}</td>
				</tr>
				<tr>
					<th width="15%">失信等级</th>
					<td width="35%" ><cus:hxlabel codeName="失信等级" itemValue="${dishonesty.creditRating}" /></td>
					<th width="15%">处罚机关全称</th>
					<td width="35%" >${dishonesty.penalizingOrgan}</td>
				</tr>
				<tr>
					<th width="15%">是否公示</th>
					<td width="35%" ><cus:hxlabel codeName="是或否" itemValue="${dishonesty.isPublic}" /></td>
					<th width="15%">执行完成日期</th>
					<td width="35%" ><fmt:formatDate type="both" pattern="yyyy-MM-dd"  value="${dishonesty.finishTime}" /></td>
				</tr>
				<s:if test="dishonesty.isPublic == 1">
				<tr>
					<th width="15%">公示起日期</th>
					<td width="35%" ><fmt:formatDate type="both" pattern="yyyy-MM-dd"  value="${dishonesty.publicStartDate}" /></td>
					<th width="15%">公示止日期</th>
					<td width="35%" ><fmt:formatDate type="both" pattern="yyyy-MM-dd"  value="${dishonesty.publicEndDate}" /></td>
				</tr>
				</s:if>
				<tr>
					<th width="15%">执行情况</th>
					<td width="35%" >${dishonesty.implementation}</td>
					<th width="15%">行政处罚日期</th>
					<td width="35%" ><fmt:formatDate type="both" pattern="yyyy-MM-dd"  value="${dishonesty.penalizingDate}" /></td>
				</tr>
				<tr> 
					<th width="15%">审批记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both"   value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
				<tr>
					<th width="15%">审批结果</th>
					<td width="96%" >
						<s:select name="checkRecord.checkResult" id="checkSelect" list="#{'审批通过':'审批通过','审批未通过':'审批未通过','审批通过并上报信用平台':'审批通过并上报信用平台'}" theme="simple" onchange="checkChange();"/>
					</td>
					<c:if test="${dishonesty.creditRating=='3'}">
					<th width="15%" id="term1" style="display:">管理期限</th>
					<td width="96%" id="term2" style="display:">
						<input name="manageTerm" id='manageTerm' style="width:60%"   datatype="*1-127"  value="<fmt:formatDate pattern='yyyy-MM-dd' type='both' value='${manageTerm}' />" type="text" class="Wdate clsdiv" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" nullmsg="管理期限不能为空!"><font style='color:red'>*</font> 
					</td>
					</c:if>
					
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea name="checkRecord.checkRemark"  style="width:96%;height:60px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)"></textarea></td>
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
