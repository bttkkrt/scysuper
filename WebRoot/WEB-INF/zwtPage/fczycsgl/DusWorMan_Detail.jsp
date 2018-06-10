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
					<td width="35%" >${dusWorMan.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${dusWorMan.companyName}</td>
				</tr>
				<tr>
					<th width="15%">作业场所名称</th>
					<td width="35%" >${dusWorMan.workplaceName}</td>
					<th width="15%">所属行业</th>
					<td width="35%" ><cus:hxlabel codeName="粉尘行业" itemValue="${dusWorMan.agencyResponsible}" /></td>
				</tr>
				<tr>
					<th width="15%">粉尘种类</th>
					<td width="35%" ><cus:hxmulselectlabel codeName="粉尘种类" itemValue="${dusWorMan.industryType}" /></td>
					<th width="15%">除尘器种类</th>
					<td width="35%" ><cus:hxlabel codeName="除尘器种类" itemValue="${dusWorMan.dustWiperType}" /></td>
				</tr>
				<tr>
					<th width="15%">企业规模</th>
					<td width="35%" ><cus:hxlabel codeName="粉尘企业规模" itemValue="${dusWorMan.enterpriseScale}" /></td>
					<th width="15%">从业人数</th>
					<td width="35%" >${dusWorMan.employeeNumber}</td>
				</tr>
				<tr>
					<th width="15%">车间结构</th>
					<td width="35%" ><cus:hxlabel codeName="粉尘车间结构" itemValue="${dusWorMan.workshopStructure}" /></td>
					<th width="15%">车间布局</th>
					<td width="35%" >${dusWorMan.workshopLayout}</td>
				</tr>
				<tr>
					<th width="15%">最近一次粉尘检测的日期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${dusWorMan.recentlyDustDetectTime}" pattern="yyyy-MM-dd" /></td>
					<th width="15%">检测值</th>
					<td width="35%" >${dusWorMan.testValue}</td>
				</tr>
				<tr>
					<th width="15%">是否合格</th>
					<td width="35%" ><cus:hxlabel codeName="是或否" itemValue="${dusWorMan.isQualified}" /></td>
					<th width="15%">作业方式</th>
					<td width="35%" ><cus:hxlabel codeName="粉尘作业方式" itemValue="${dusWorMan.operationMode}" /></td>
				</tr>
				<tr>
					<th width="15%">是否有除尘器</th>
					<td width="35%" ><cus:hxlabel codeName="是或否" itemValue="${dusWorMan.hasDustWiper}" /></td>
					<th width="15%">除尘形式</th>
					<td width="35%" ><cus:hxlabel codeName="粉尘除尘形式" itemValue="${dusWorMan.dustRemovalForm}" /></td>
				</tr>
				<tr>
					<th width="15%">除尘器数量</th>
					<td width="35%" >${dusWorMan.dustWiperNumber}</td>
					<th width="15%">投入使用时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${dusWorMan.wiperInUseTime}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">除尘器是否经环保部门验收</th>
					<td width="35%" ><cus:hxlabel codeName="是或否" itemValue="${dusWorMan.isWiperAccepted}" /></td>
					<th width="15%">除尘系统是否设置隔爆阀</th>
					<td width="35%" ><cus:hxlabel codeName="是或否" itemValue="${dusWorMan.hasExplosionProof}" /></td>
				</tr>
				<tr>
					<th width="15%">除尘器是否有泄爆口</th>
					<td width="35%" ><cus:hxlabel codeName="是或否" itemValue="${dusWorMan.hasVentPort}" /></td>
					<th width="15%">泄爆口位置</th>
					<td width="35%" >${dusWorMan.ventPortPosition}</td>
				</tr>
				<tr>
					<th width="15%">除尘器是否在负压下工作</th>
					<td width="35%" ><cus:hxlabel codeName="是或否" itemValue="${dusWorMan.isWorkUnderNegative}" /></td>
					<th width="15%">除尘器是否安装于室外</th>
					<td width="35%" ><cus:hxlabel codeName="是或否" itemValue="${dusWorMan.isInstalledOutdoor}" /></td>
				</tr>
				<tr>
					<th width="15%">是否有自动卸灰锁气装置</th>
					<td width="35%" ><cus:hxlabel codeName="是或否" itemValue="${dusWorMan.hasAutoUnloadLock}" /></td>
					<th width="15%">除尘器目前状态</th>
					<td width="35%" ><cus:hxlabel codeName="粉尘除尘器目前状态" itemValue="${dusWorMan.dustWiperCurrentStatus}" /></td>
				</tr>
				<tr>
					<th width="15%">企业目前状态</th>
					<td width="35%" ><cus:hxlabel codeName="企业目前状态" itemValue="${dusWorMan.enterpriseCurrentStatus}" /></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea readOnly name="dusWorMan.remark" style="width:96%;height:60px">${dusWorMan.remark}</textarea></td>
				</tr>
				<tr>
					<th width="15%">隔爆阀照片</th>
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
					<th width="15%">泄爆口照片</th>
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
					<th width="15%">自动卸灰锁气装置图片</th>
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
					<th width="15%">作业场所图片</th>
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
					<th width="15%">除尘器全景图片</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${picList5}">
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
