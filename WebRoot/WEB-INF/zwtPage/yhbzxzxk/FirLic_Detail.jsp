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
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${firLic.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${firLic.companyName}</td>
				</tr>
				<tr>
					<th width="15%">档案号</th>
					<td width="35%" >${firLic.itemNo}</td>
					<th width="15%">局审会意见</th>
					<td width="35%" >${firLic.agencyComment}</td>
				</tr>
				<tr>
					<th width="15%">材料审查人员</th>
					<td width="35%" >${firLic.checkPerson}</td>
					<th width="15%">材料接收人员</th>
					<td width="35%" >${firLic.receivePerson}</td>
				</tr>
				<tr>
					<th width="15%">材料接收日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${firLic.receiveDate}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">受理材料日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${firLic.dealDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">证书编号</th>
					<td width="35%" >${firLic.zsbh}</td>
					<th width="15%">仓库设施地址</th>
					<td width="35%" >${firLic.ckssdz}</td>
				</tr>
				<tr>
					<th width="15%">许可证有效起始日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${firLic.licenseValid}" pattern="yyyy-MM-dd"/></td>
				    <th width="15%">许可证有效截至日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${firLic.licenseValidEnd}" pattern="yyyy-MM-dd"/></td>
				<tr>
				    <th width="15%">发证日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${firLic.fzrq}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">发证单位</th>
					<td width="35%" >${firLic.lssuingUnit}</td>
				</tr>
				<tr>
					<th width="15%">本次领证情况</th>
					<td width="35%" >${firLic.licenseCondition}</td>
					<th width="15%">申请材料是否齐全</th>
					<td width="35%" ><cus:hxlabel codeName="是或否" itemValue="${firLic.applyCondition}" /></td>
				</tr>
				<tr>
					<th width="15%">签字领导</th>
					<td width="35%" >${firLic.signLeader}</td>
					<th width="15%">预审意见</th>
					<td width="35%" >${firLic.preComment}</td>
				</tr>
				<tr>
					<th width="15%">现场检查部门</th>
					<td width="35%" >${firLic.checkDepart}</td>
					<th width="15%">核查结论</th>
					<td width="35%" >${firLic.checkConclusion}</td>
				</tr>
				<tr>
					<th width="15%">材料审查情况</th>
					<td width="35%" >${firLic.materialCondition}</td>
					<th width="15%">行政许可建议</th>
					<td width="35%" >${firLic.adminSuggest}</td>
				</tr>
				<tr>
				    <th width="15%">变更日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${firLic.bgrq}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">经营范围</th>
					<td width="35%" >${firLic.jyfw}</td>
				</tr>
				<tr>
					<th width="15%">烟花爆竹经营许可证申请书</th>
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
				<tr>
					<th width="15%">烟花爆竹经营许可证申请材料</th>
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
					<th width="15%">行政审批文件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${picList3}">
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
					<th width="15%">烟花爆竹经营许可证扫描件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${picList4}">
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
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
