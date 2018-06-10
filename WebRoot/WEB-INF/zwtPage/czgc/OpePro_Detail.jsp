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
					<td width="35%" >${opePro.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${opePro.companyName}</td>
				</tr>
				<tr>
					<th width="15%">车间名称</th>
					<td width="35%" >${opePro.operationWorkshopName}</td>
					<th width="15%">岗位名称</th>
					<td width="35%" >${opePro.operationPostname}</td>
				</tr>
				<tr>
					<th width="15%">最大工作时间</th>
					<td width="35%" >${opePro.operationMostWorktime}</td>
					<th width="15%">岗位员工数</th>
					<td width="35%" >${opePro.operationPostCount}</td>
				</tr>
				<tr>
					<th width="15%">是否倒班</th>
					<td width="35%" ><cus:hxlabel codeName="是或否" itemValue="${opePro.operationShiftsOrnot}" /></td>
					<th width="15%">倒班总人数</th>
					<td width="35%" >${opePro.operationShiftsPersons}</td>
				</tr>
				<tr>
					<th width="15%">起草人</th>
					<td width="35%" >${opePro.operationDraftPerson}</td>
					<th width="15%">批准人</th>
					<td width="35%" >${opePro.operationAuthorization}</td>
				</tr>
				<tr>
					<th width="15%">操作规程编号</th>
					<td width="35%" >${opePro.operationCode}</td>
					<th width="15%">有效日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${opePro.effectiveDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${picList}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">
								   		<c:choose>
											<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
											||fn:endsWith(fn:toLowerCase(item.picName),'.png')
											||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
											<a href="${item.httpUrl}/upload/photo/${item.picName}" rel="example_group">	
												<img src="${item.httpUrl}/upload/photo/${item.picName}"
												border='0' width='220' height='150'/>
											</a>
											</c:when> 
											<c:otherwise> 
												&nbsp;&nbsp;&nbsp;${item.fileName}
											</c:otherwise>
										</c:choose>
								   </td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;</td>
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
