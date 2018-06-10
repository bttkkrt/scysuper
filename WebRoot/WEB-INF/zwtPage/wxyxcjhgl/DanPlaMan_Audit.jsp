<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>审核记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="danPlaManSaveAudit.action">
		<input type="hidden" name="danPlaMan.id" value="${danPlaMan.id}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%">
						<cus:hxlabel  codeName="企业属地" itemValue="${danPlaMan.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%">
						${danPlaMan.companyName}
					</td>
				</tr>
				<tr>
					<th width="15%">计划名称</th>
					<td colspan="3" width="35%" >${danPlaMan.planName}</td>
					
				</tr>
				<tr>
					<th width="15%">巡检点</th>
					<td colspan="3" width="35%" >${danPlaMan.checkName}</td>
					
				</tr>
				<tr>
					<th width="15%">计划开始时间</th>
					<td width="35%"><fmt:formatDate type="date" value="${danPlaMan.planStartTime}" /></td>
					<th width="15%">计划结束时间</th>
					<td width="35%"><fmt:formatDate type="date" value="${danPlaMan.planEndTime}" /></td>
				</tr>
				<tr>
					<th width="15%">巡查频率</th>
					<td width="35%">
						<cus:hxlabel   codeName="巡查频率" itemValue="${danPlaMan.checkFrequency}" /></td>
					<th width="15%">巡查人员姓名</th>
					<td width="35%">
						${danPlaMan.checkPeopleName}
					</td>
				</tr>
				
				<tr>
					<th width="15%">审核结果</th>
					<td width="35%" colspan="3">
						<s:select  name="danPlaMan.auditResult" list="{'审核通过','审核未通过'}" theme="simple" headerKey="" headerValue="--请选择--" datatype="*1-127" errormsg='审核结果必须选择！' nullmsg='审核结果必须选择！' sucmsg='审核结果选择正确！'  maxlength="127"></s:select>
						<font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">审核备注</th>
					<td width="35%" colspan="3">
						<textarea style="width:100%;height:100px;" name="danPlaMan.remark" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)"></textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
 					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
