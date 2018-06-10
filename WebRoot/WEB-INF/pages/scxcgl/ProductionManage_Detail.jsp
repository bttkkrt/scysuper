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
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${productionManage.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${productionManage.companyName}</td>
				</tr>
				<tr>
					<th width="15%">项目负责人</th>
					<td width="35%" >${productionManage.personInCharge}</td>
					<th width="15%">施工负责人</th>
					<td width="35%">${productionManage.personName}</td>
				</tr>
				<tr>
					<th width="15%">作业时间</th>
					<td width="35%"><fmt:formatDate type="date" value="${productionManage.jobTime}"/></td>
				</tr>
				<tr>
					<th width="15%">作业类型</th>
					<td width="85%" colspan="3"><cus:hxmulselectlabel codeName="生产作业类型" itemValue="${productionManage.jobType}" /></td>
				</tr>
				<tr>
					<th width="15%">作业内容</th>
					<td width="85%" colspan="3"><textarea readOnly name="productionManage.jobContent" style="width:96%;height:60px">${productionManage.jobContent}</textarea></td>
				</tr>
				<tr>
					<th width="15%">危害因素分析</th>
					<td width="85%" colspan="3"><textarea readOnly name="productionManage.hazardAnalysiss" style="width:96%;height:60px">${productionManage.hazardAnalysiss}</textarea></td>
				</tr>
				<tr>
					<th width="15%">安全措施</th>
					<td width="85%" colspan="3"><textarea readOnly name="productionManage.safeMeasures" style="width:96%;height:60px">${productionManage.safeMeasures}</textarea></td>
				</tr>
				<tr>
					<th width="15%">应急措施</th>
					<td width="85%" colspan="3"><textarea readOnly name="productionManage.emerMeasure" style="width:96%;height:60px">${productionManage.emerMeasure}</textarea></td>
				</tr>
				
				<tr>
					<th width="15%">作业许可证</th>
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
					<th width="15%">施工图片</th>
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
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_productionManage');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
