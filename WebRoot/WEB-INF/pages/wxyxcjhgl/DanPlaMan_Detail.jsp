<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${danPlaMan.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${danPlaMan.companyName}</td>
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
					<td width="35%" ><fmt:formatDate type="date" value="${danPlaMan.planStartTime}" /></td>
					<th width="15%">计划结束时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${danPlaMan.planEndTime}" /></td>
				</tr>
				<tr>
					<th width="15%">巡查频率</th>
					<td width="35%" ><cus:hxlabel codeName="巡查频率" itemValue="${danPlaMan.checkFrequency}" /></td>
					<th width="15%">巡查人员姓名</th>
					<td width="35%" >${danPlaMan.checkPeopleName}</td>
				</tr>
			
				<c:if test="${danPlaMan.isAudit=='1'}">
					<th width="15%">审核人姓名</th>
					<td width="35%" >${danPlaMan.auditPersonName}</td>
				</tr>
				<tr>
					<th width="15%">审核结果</th>
					<td width="35%" colspan="3">${danPlaMan.auditResult}</td>
				
				</tr>
				<tr>
					<th width="15%">审核备注</th>
					<td width="35%" colspan="3">${danPlaMan.remark}</td>
				
				</tr>
				<tr>
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
				</c:if>	
				<c:if test="${danPlaMan.isAudit=='0'}">
				<th width="15%">是否上报审核</th>
					<td width="35%" >否</td>
				</tr>
				</c:if>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_danPlaMan');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
