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
			return "${envInf.mapkey}";
		}
	</script>
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="17%">所在区域</th>
					<td width="33%" ><cus:hxlabel codeName="企业属地" itemValue="${envInf.areaId}" /></td>
					<th width="17%">企业名称</th>
					<td width="33%" >${envInf.companyName}</td>
				</tr>
				<tr>
					<th width="17%">周边环境类型</th>
					<td width="33%" ><cus:hxlabel codeName="周边环境类型" itemValue="${envInf.surroundingEnvironmentType}" /></td>
					<th width="17%">周边环境名称</th>
					<td width="33%" >${envInf.surroundingEnvironmentName}</td>
				</tr>
				<tr>
					<th width="17%">周边环境方位</th>
					<td width="33%" ><cus:hxlabel codeName="周边环境方位" itemValue="${envInf.surroundingEnvironment}" /></td>
					<th width="17%">与危险源最小距离</th>
					<td width="33%" >${envInf.minimumDistance}</td>
				</tr>
				<tr>
					<th width="17%">建筑结构</th>
					<td width="33%" ><cus:hxlabel codeName="建筑结构" itemValue="${envInf.buildingStructure}" /></td>
					<th width="17%">建筑高度</th>
					<td width="33%" >${envInf.buildingHeight}</td>
				</tr>
				<tr>
					<th width="17%">人员类型</th>
					<td width="33%" ><cus:hxlabel codeName="周围环境人员类型" itemValue="${envInf.dangerousChemicalName}" /></td>
					<th width="17%">人员数量</th>
					<td width="33%" >${envInf.personnelType}</td>
				</tr>
				<tr>
					<th width="17%">联系人</th>
					<td width="33%" >${envInf.contactPerson}</td>
					<th width="17%">联系人固定电话</th>
					<td width="33%" >${envInf.telephone}</td>
				</tr>
				<tr>
					<th width="17%">联系人移动电话</th>
					<td width="33%" >${envInf.mobile}</td>
					<th width="17%">联系人电子邮箱</th>
					<td width="33%" >${envInf.email}</td>
				</tr>
				<tr>
					<th width="17%">相关照片</th>
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
					<th width="17%">备注</th>
					<td width="33%" colspan="3" ><textarea readOnly name="envInf.remark" style="width:96%;height:60px">${envInf.remark}</textarea></td>
				</tr>
				<tr>
					<td width="100%" colspan="4">
						<div style="width:100%;">
				        	<iframe id="map" name="map" src="${ctx}/webResources/Map/index.html"  style="height:300px;width:100%; border:hidden; "scrolling="no" ></iframe>

				    	</div>
					</td>
				</tr>
				
			</table>
		</div></div></div>
	</form>
</body>
</html>
