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
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${newWorTra.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${newWorTra.companyName}</td>
				</tr>
				<tr>
					<th width="15%">培训班名称</th>
					<td width="35%" >${newWorTra.trainingName}</td>
					<th width="15%">培训讲师</th>
					<td width="35%" >${newWorTra.trainingTeacher}</td>
				</tr>
				<tr>
				<th width="15%">培训开始时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${newWorTra.trainingTime}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">培训结束时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${newWorTra.trainingTimeEnd}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">培训人数</th>
					<td width="35%" >${newWorTra.trainingPersons}</td>
					
				</tr>
				<tr>
				<th width="15%">培训内容</th>
					<td width="35%" colspan="3"><textarea readOnly name="newWorTra.trainingContent" style="width:96%;height:100px">${newWorTra.trainingContent}</textarea></td>
				</tr>
				<tr>
				<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea readOnly name="newWorTra.trainingRemark" style="width:96%;height:100px">${newWorTra.trainingRemark}</textarea></td>
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
