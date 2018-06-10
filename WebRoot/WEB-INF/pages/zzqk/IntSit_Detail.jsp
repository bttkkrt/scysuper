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
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${intSit.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${intSit.companyName}</td>
				</tr>
				<tr>
					<th width="15%">证书编号</th>
					<td width="35%" >${intSit.intelligenceCardnum}</td>
					<th width="15%">证书名称</th>
					<td width="35%" >${intSit.intelligenceCardname}</td>
				</tr>
				<tr>
					<th width="15%">发证机关</th>
					<td width="35%" >${intSit.intelligenceInstitution}</td>
					<th width="15%">发证日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${intSit.intelligenceCardDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">有效期起始日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${intSit.intelligenceValidityStart}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">有效期截止日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${intSit.intelligenceValidityEnd}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">资质类型</th>
					<td width="35%" ><cus:hxlabel codeName="资质类型" itemValue="${intSit.intelligenceType}" /></td>
					<th width="15%">资质级别</th>
					<td width="35%" >${intSit.zzjb}</td>
				</tr>
				<tr>
					<th width="15%">变更日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${intSit.changeDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
				<th width="15%">业务范围</th>
				<td width="35%" colspan="3"><textarea readOnly name="intSit.bussinessScope" style="width:96%;height:100px">${intSit.bussinessScope}</textarea></td>
				</tr>
				<tr>
				<th width="15%">资质内容</th>
				<td width="35%" colspan="3"><textarea readOnly name="intSit.intelligenceContent" style="width:96%;height:100px">${intSit.intelligenceContent}</textarea></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea readOnly name="intSit.intelligenceRemark" style="width:96%;height:100px">${intSit.intelligenceRemark}</textarea></td>
				</tr>
				<tr>
					<th width="15%">证书扫描件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:120px;">
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
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						 <a href="#" class="btn_01" onclick="parent.close_win('win_intSit');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
