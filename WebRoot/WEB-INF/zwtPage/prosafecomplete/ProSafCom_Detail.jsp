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
					<td width="35%" >${proSafCom.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${proSafCom.companyName}</td>
				</tr>
				<tr>
					<th width="15%">审批编号</th>
					<td width="35%" >${proSafCom.approvalNum}</td>
					<th width="15%">审批日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${proSafCom.approvalDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
				    <th width="15%">项目内容</th>
					<td width="35%" >${proSafCom.projectContent}</td>
					<th width="15%">验收日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${proSafCom.acceptanceDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">验收专家</th>
					<td width="35%" >${proSafCom.acceptanceExpert}</td>
					<th width="15%">项目类型</th>
					<td width="35%" ><cus:hxlabel codeName="项目类型" itemValue="${proSafCom.projectType}" /></td>
				</tr>
				<tr>
					<th width="15%">项目性质</th>
					<td width="35%" ><cus:hxlabel codeName="项目性质" itemValue="${proSafCom.projectNature}" /></td>
					<th width="15%">评价单位</th>
					<td width="35%" >${proSafCom.evaluationUnit}</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea readOnly name="proSafCom.remark" style="width:96%;height:60px">${proSafCom.remark}</textarea></td>
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
