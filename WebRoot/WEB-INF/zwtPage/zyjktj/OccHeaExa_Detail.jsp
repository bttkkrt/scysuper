<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">	
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${occHeaExa.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${occHeaExa.companyName}</td>
				</tr>
				<tr>
					<th width="15%">体检机构</th>
					<td width="35%" >${occHeaExa.medicalInstitutionName}</td>
					<th width="15%">录入时间</th>
					<td width="35%" ><fmt:formatDate type="both" pattern='yyyy-MM-dd' value="${occHeaExa.createTime}" /></td>
				</tr>
				 <tr>
					<td colspan="4">
						<div id="more">
						<table>
							<tr>
						    	<td  style="text-align:center;width:5%">序号</td>
								<td  style="text-align:center;width:15%">职业病危害因素名称</td>
								<td  style="text-align:center;width:15%">岗前职业健康体检人数</td>
								<td  style="text-align:center;width:15%">岗中职业健康体检人数</td>
								<td  style="text-align:center;width:15%">离岗职业健康体检人数</td>
								<td  style="text-align:center;width:15%">体检发现应调岗离岗人数</td>
								<td  style="text-align:center;width:15%">实际调离岗位人数</td>
							</tr>	
							<c:forEach var="occHeaExaList" items="${occHeaExaLists}"  varStatus="status">
								<tr style="text-align: center" id="${occHeaExaList.id}">
									<td style='text-align:center'>${status.index+1}</td>
									<td style='text-align:center'>${occHeaExaList.occupationalDiseasName}</td>
									<td style='text-align:center'>${occHeaExaList.preOccupationHealthNumber}
									<td style='text-align:center'>${occHeaExaList.postOccupationalHealth}
									<td style='text-align:center'>${occHeaExaList.postOccupationHealthNumber}
									<td style='text-align:center'>${occHeaExaList.foundPostsNumber}
									<td style='text-align:center'>${occHeaExaList.actualPositionNumber}
								</tr>
							</c:forEach>
						</table>
						</div>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
