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
					<td width="35%" >${accRepInvHan.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${accRepInvHan.companyName}</td>
				</tr>
				<tr>
					<th width="15%">事故编号</th>
					<td width="35%" >${accRepInvHan.accidentId}</td>
					<th width="15%">事故名称</th>
					<td width="35%" >${accRepInvHan.accidentName}</td>
				</tr>
				<tr>
					<th width="15%">事故发生时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${accRepInvHan.accidentTime}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">事故经过</th>
					<td width="35%" >${accRepInvHan.accidentDescrip}</td>
				</tr>
				<tr>
					<th width="15%">事故原因</th>
					<td width="35%" >${accRepInvHan.accidentReason}</td>
					<th width="15%">轻伤人数</th>
					<td width="35%" >${accRepInvHan.concussionsNum}</td>
				</tr>
				<tr>
					<th width="15%">重伤人数</th>
					<td width="35%" >${accRepInvHan.woundedNum}</td>
					<th width="15%">死亡人数</th>
					<td width="35%" >${accRepInvHan.deathNum}</td>
				</tr>
				<tr>
					<th width="15%">经济损失</th>
					<td width="35%" >${accRepInvHan.economicLoss}</td>
					<th width="15%">事故级别</th>
					<td width="35%" ><cus:hxlabel codeName="事故级别" itemValue="${accRepInvHan.accidentLevel}" /></td>
				</tr>
				<tr>
					<th width="15%">事故类别</th>
					<td width="35%" ><cus:hxlabel codeName="事故类别" itemValue="${accRepInvHan.accidentType}" /></td>
					<th width="15%">调查组成员</th>
					<td width="35%" >${accRepInvHan.inverstTeamNumber}</td>
				</tr>
				<tr>
					<th width="15%">事故责任</th>
					<td width="35%" >${accRepInvHan.accidentResponsible}</td>
					<th width="15%">处理建议</th>
					<td width="35%" >${accRepInvHan.handleSuggest}</td>
				</tr>
				<tr>
					<th width="15%">整改措施</th>
					<td width="35%" >${accRepInvHan.method}</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea readOnly name="accRepInvHan.remark" style="width:96%;height:60px">${accRepInvHan.remark}</textarea></td>
				</tr>
				<tr>
					<th width="15%">事故图片</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${picList1}">
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
				
				<tr>
					<th width="15%">整改后图片</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${picList2}">
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
