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
					<td width="35%" >${emeDri.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${emeDri.companyName}</td>
				</tr>
				<tr>
					<th width="15%">演练名称</th>
					<td width="35%" >${emeDri.drillName}</td>
					<th width="15%">演练类型</th>
					<td width="35%" ><cus:hxlabel codeName="应急演练类型" itemValue="${emeDri.drillType}" /></td>
				</tr>
				<tr>
					<th width="15%">演练地点</th>
					<td width="35%" >${emeDri.drillAddress}</td>
					<th width="15%">演练目的</th>
					<td width="35%" >${emeDri.drillPurpose}</td>
				</tr>
				<tr>
					<th width="15%">演练形式</th>
					<td width="35%" >${emeDri.drillForm}</td>
					<th width="15%">演练内容</th>
					<td width="35%" >${emeDri.drillContent}</td>
				</tr>
				<tr>
					<th width="15%">演练开始时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${emeDri.drillStartTime}" pattern="yyyy-MM-dd" /></td>
					<th width="15%">演练结束时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${emeDri.drillStopTime}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">主办单位</th>
					<td width="35%" >${emeDri.organizer}</td>
					<th width="15%">演练单位</th>
					<td width="35%" >${emeDri.drillCompany}</td>
				</tr>
				<tr>
					<th width="15%">参演人数</th>
					<td width="35%" >${emeDri.drillPersonNum}</td>
					<th width="15%">评估总结</th>
					<td width="35%" >${emeDri.evaluateSummary}</td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
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
					<th width="15%">演练图片</th>
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
					    <a href="#" class="btn_01" onclick="parent.close_win('win_emeDri');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
