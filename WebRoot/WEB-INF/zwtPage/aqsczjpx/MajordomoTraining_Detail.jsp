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
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${majordomoTraining.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${majordomoTraining.companyName}</td>
				</tr>
				<tr>
					<th width="15%">安全生产总监姓名</th>
					<td width="35%" >${majordomoTraining.trainingPersonName}</td>
					<th width="15%">培训开始时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${majordomoTraining.trainingTime}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
				<th width="15%">培训结束时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${majordomoTraining.trainingTimeEnd}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">授课人</th>
					<td width="35%" >${majordomoTraining.trainingTeacher}</td>
				</tr>
				<tr>
					<th width="15%">培训单位</th>
					<td width="35%" >${majordomoTraining.trainingAddress}</td>
					<th width="15%">培训学时</th>
					<td width="35%" >${majordomoTraining.trainingTeacheTime}</td>
				</tr>
				<tr>
					<th width="15%">证书号码</th>
					<td width="35%" >${majordomoTraining.trainingCardnum}</td>
					<th width="15%">证书发证日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${majordomoTraining.trainingCardPickDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">有效期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${majordomoTraining.trainingCardValidity}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">发证单位</th>
					<td width="35%">${majordomoTraining.fzdw}</td>
				</tr>
				<tr>
					<th width="15%">培训内容</th>
					<td width="35%" colspan="3"><textarea readOnly name="majordomoTraining.trainingContent" style="width:96%;height:120px">${majordomoTraining.trainingContent}</textarea></td>
				</tr>
				<tr>
					<th width="15%">证书扫描件</th>
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
