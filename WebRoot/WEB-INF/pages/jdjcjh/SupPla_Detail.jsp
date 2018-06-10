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
			<s:if test="mark == 0 ">
				<tr>
					<th width="15%">计划名称</th>
					<td width="35%" >${supPla.planName}</td>
					<th width="15%">计划类型</th>
					<td width="35%" ><cus:hxlabel codeName="监督检查计划类型" itemValue="${supPla.planType}" /></td>
				</tr>
				<tr>
					<th width="15%">计划开始时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${supPla.planStartTime}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">计划结束时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${supPla.planEndTime}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<c:if test="${supPla.addType=='1' }">
					<tr>
						<th width="15%">计划完成度</th>
						<td width="35%" >${supPla.finishState}</td>
					</tr>
				</c:if>
				<tr>	
					<c:if test="${supPla.addType=='1'}">
						<td colspan="4">
						<table>
							<tr   >
								<th style="white-space: nowrap; text-align: center;">企业名称</th>
								<th style="white-space: nowrap; text-align: center;">所属区域</th>
								<th style="white-space: nowrap; text-align: center;">地  址</th>
								<th style="white-space: nowrap; text-align: center;">主要负责人</th>
								<th style="white-space: nowrap; text-align: center;">联系电话</th>
								<th style="white-space: nowrap; text-align: center;">联系人</th>
								<th style="white-space: nowrap; text-align: center;">联系电话</th>
								<th style="white-space: nowrap; text-align: center;">邮 箱</th>
								<th style="white-space: nowrap; text-align: center;">监管项目</th>
							</tr>
							<c:forEach var="cip" items="${cipList}" varStatus="status">
							<tr>
								<td>${cip.companyName }</td>
								<td>${cip.area }</td>
								<td>${cip.address }</td>
								<td>${cip.charger }</td>
								<td>${cip.chargerPhone }</td>
								<td>${cip.contact }</td>
								<td>${cip.contactPhone }</td>
								<td>${cip.email }</td>
								<td>${cip.typeDetail }</td>
							</tr>
							</c:forEach>
							
						</table>
					</td>
					</c:if>
					<c:if test="${supPla.addType!='1'}">
					<th width="15%">检查对象名称</th>
					<td width="35%" colspan="3"><textarea readOnly name="supPla.checkCompanyName" style="width:96%;height:60px">${supPla.checkCompanyName}</textarea></td>
					</c:if>
				</tr>
				</s:if>
				<s:if test="mark == 4 ">
				<tr>
					<th width="15%">计划名称</th>
					<td width="35%" >${supPla.planName}</td>
					<th width="15%">计划类型</th>
					<td width="35%" ><cus:hxlabel codeName="监督检查计划类型" itemValue="${supPla.planType}" /></td>
				</tr>
				<tr>
					<th width="15%">计划开始时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${supPla.planStartTime}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">计划结束时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${supPla.planEndTime}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>	
					<th width="15%">检查对象名称</th>
					<td width="35%" colspan="3"><textarea readOnly name="supPla.checkCompanyName" style="width:96%;height:60px">${supPla.checkCompanyName}</textarea></td>
				</tr>
				</s:if>
				<s:if test="mark == 1">
				<tr>
					<th width="15%">计划名称</th>
					<td width="35%" >${supPla.planName}</td>
					<th width="15%">计划类型</th>
					<td width="35%" ><cus:hxlabel codeName="监督检查计划类型" itemValue="${supPla.planType}" /></td>
				</tr>
				<tr>
					<th width="15%">计划开始时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${supPla.planStartTime}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">计划结束时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${supPla.planEndTime}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
				    <th width="15%">检查项类型</th>
					<td width="85%" colspan="3"><cus:hxmulselectlabel   codeSql="select t.row_id,t.PATROL_TYPE_NAME from PAT_TYP_MAN t where t.delflag = 0" itemValue="${supPla.checkItemType}" /></td>
				</tr>
				<tr>
					<th width="15%">承办人</th>
					<td width="35%" >${supPla.checkUserName}</td>
					<th width="15%">协办人</th>
					<td width="35%" >${supPla.xbUserName}</td>
				</tr>
				<c:if test="${supPla.addType=='1' }">
					<tr>
						<th width="15%">计划完成度</th>
						<td width="35%" >${supPla.finishState}</td>
					</tr>
					</c:if>
				<tr>	
					<c:if test="${supPla.addType=='1'}">
						<td colspan="4">
						<table>
							<tr  style="white-space: nowrap; text-align: center;">
								<th style="white-space: nowrap; text-align: center;">企业名称</th>
								<th style="white-space: nowrap; text-align: center;">所属区域</th>
								<th style="white-space: nowrap; text-align: center;">地  址</th>
								<th style="white-space: nowrap; text-align: center;">主要负责人</th>
								<th style="white-space: nowrap; text-align: center;">联系电话</th>
								<th style="white-space: nowrap; text-align: center;">联系人</th>
								<th style="white-space: nowrap; text-align: center;">联系电话</th>
								<th style="white-space: nowrap; text-align: center;">邮 箱</th>
								<th style="white-space: nowrap; text-align: center;">监管项目</th>
							</tr>
							<c:forEach var="cip" items="${cipList}" varStatus="status">
							<tr>
								<td>${cip.companyName }</td>
								<td>${cip.area }</td>
								<td>${cip.address }</td>
								<td>${cip.charger }</td>
								<td>${cip.chargerPhone }</td>
								<td>${cip.contact }</td>
								<td>${cip.contactPhone }</td>
								<td>${cip.email }</td>
								<td>${cip.typeDetail }</td>
							</tr>
							</c:forEach>
							
						</table>
					</td>
					</c:if>
					<c:if test="${supPla.addType!='1'}">
					<th width="15%">检查对象名称</th>
					<td width="35%" colspan="3"><textarea readOnly name="supPla.checkCompanyName" style="width:96%;height:60px">${supPla.checkCompanyName}</textarea></td>
					</c:if>
				</tr>
				</s:if>
				<s:if test="mark == 2">
				<tr>
					<th width="15%">计划名称</th>
					<td width="35%" >${supPla.planName}</td>
					<th width="15%">计划类型</th>
					<td width="35%" ><cus:hxlabel codeName="监督检查计划类型" itemValue="${supPla.planType}" /></td>
				</tr>
				<tr>
					<th width="15%">计划开始时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${supPla.planStartTime}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">计划结束时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${supPla.planEndTime}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
				    <th width="15%">检查项类型</th>
					<td width="35%" ><cus:hxmulselectlabel   codeSql="select t.row_id,t.PATROL_TYPE_NAME from PAT_TYP_MAN t where t.delflag = 0" itemValue="${supPla.checkItemType}" /></td>
					<th width="15%">检查部门名称</th>
					<td width="35%" >${supPla.checkDeptName}</td>
				</tr>
				<tr>
					<th width="15%">检查对象名称</th>
					<td width="35%" >${supPla.checkCompanyName}</td>
				</tr>
				</s:if>
				
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_supPla');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
