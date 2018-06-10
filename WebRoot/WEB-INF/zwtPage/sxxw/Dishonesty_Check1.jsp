<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
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
				<!-- <tr>
					<th width="15%">审核人员ID</th>
					<td width="35%" >${dishonesty.checkPersonId}</td>
					<th width="15%">审核人员姓名</th>
					<td width="35%" >${dishonesty.checkPersonName}</td>
				</tr>
				<tr>
					<th width="15%">审核结果</th>
					<td width="35%" >${dishonesty.checkComment}</td>
				</tr>  -->
				<c:if test="${fn:length(checkRecords) >0}">
				<tr>
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
				</c:if>
				<tr>
					<th width="15%">审核结果</th>
					<td width="96%" colspan="3">
					<s:select name="checkRecord.checkResult" list="#{'审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
					</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea name="checkRecord.checkRemark" style="width:96%;height:60px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)"></textarea></td>
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
