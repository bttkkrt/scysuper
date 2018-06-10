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
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${cheRepFil.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${cheRepFil.companyName}</td>
				</tr>
				<tr>
					<th width="15%">备案编号</th>
					<td width="35%" >${cheRepFil.recordNum}</td>
					<th width="15%">备案日期</th>
					<td width="35%" ><fmt:formatDate type="both" pattern="yyyy-MM-dd"  value="${cheRepFil.recordDate}" /></td>
				</tr>
				<tr>
					<th width="15%">评价机构名称</th>
					<td width="35%" >${cheRepFil.ratingAgenciesName}</td>
					<th width="15%">剧毒化学品名称</th>
					<td width="35%" >${cheRepFil.chemicalName}</td>
				</tr>
				<tr>
					<th width="15%">年使用量</th>
					<td width="35%" >${cheRepFil.annualUse}</td>
					<th width="15%">报告完成日期</th>
					<td width="35%" ><fmt:formatDate type="both" pattern="yyyy-MM-dd"  value="${cheRepFil.completeDate}" /></td>
				</tr>
				<tr>
					<th width="15%">报告上传日期</th>
					<td width="35%" ><fmt:formatDate type="both"  pattern="yyyy-MM-dd" value="${cheRepFil.submitDate}" /></td>
					<th width="15%">下次备案日期</th>
					<td width="35%" ><fmt:formatDate type="both"  pattern="yyyy-MM-dd" value="${cheRepFil.nextRecordDate}" /></td>
				</tr>
				<tr>
					<th width="15%">备案内容</th>
					<td width="85%" colspan="3" ><textarea readOnly name="cheRepFil.recordContent" style="width:96%;height:60px">${cheRepFil.recordContent}</textarea></td>
					
				</tr>
				<tr>
					<th width="15%">附件</th>
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
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_cheRepFil');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
