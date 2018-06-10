<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script type="text/javascript">
		function getMark(){
			return '-1';
		}
		function getChlidCode(){
			return "${emeFac.mapkey}";
		}
	</script>
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
			<s:if test="emeFac.type==0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" >${emeFac.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${emeFac.companyName}</td>
				</tr>
				</s:if>
				<tr>
					<th width="15%">物资名称</th>
					<td width="35%" >${emeFac.facilityName}</td>
					<th width="15%">物资级别</th>
					<td width="35%" ><cus:hxlabel codeName="应急物资级别" itemValue="${emeFac.facilityLevel}" /></td>
				</tr>
				<tr>
					<th width="15%">物资数量</th>
					<td width="35%" >${emeFac.facilityNumber}</td>
					<th width="15%">物资型号</th>
					<td width="35%" >${emeFac.facilityModel}</td>
				</tr>
				<tr>
					<th width="15%">物资规格</th>
					<td width="35%" >${emeFac.facilitySpecific}</td>
					<th width="15%">购入日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${emeFac.purchaseDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">生产厂家</th>
					<td width="35%" >${emeFac.vender}</td>
					<th width="15%">出厂日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${emeFac.produceTime}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">有效期至</th>
					<td width="35%" ><fmt:formatDate type="date" value="${emeFac.expiryDate}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">用途说明</th>
					<td width="35%" >${emeFac.purposeDescrip}</td>
				</tr>
				<tr>
					<th width="15%">性能说明</th>
					<td width="35%" >${emeFac.performanceDescrip}</td>
					<th width="15%">存放地点</th>
					<td width="35%" >${emeFac.storageLocation}</td>
				</tr>
				<tr>
					<th width="15%">负责保管人</th>
					<td width="35%" >${emeFac.keeper}</td>
					<th width="15%">保管人联系方式</th>
					<td width="35%" >${emeFac.keeperPhone}</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" >${emeFac.remark}</td>
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
				<tr>
					<td width="100%" colspan="4">
						<div style="width:100%;">
				        	<iframe id="map" name="map" src="${ctx}/webResources/Map/index.html"  style="height:500px;width:100%; border:hidden; "scrolling="no" ></iframe>

				    	</div>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
