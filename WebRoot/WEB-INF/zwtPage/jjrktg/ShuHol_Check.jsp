<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
</head>
<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="shuHolCheckSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="shuHol.id" value="${shuHol.id}">
		<input type="hidden" name="shuHol.linkId" value="${shuHol.linkId}">
		<input type="hidden" name="checkRecord.infoId" value="${shuHol.id}">
			<table width="100%">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${shuHol.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${shuHol.companyName}</td>
				</tr>
				<tr>
					<th width="15%">放假开始时间</th>
					<td width="35%" ><fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${shuHol.holidayTimeStart}" /></td>
					<th width="15%">放假结束时间</th>
					<td width="35%" ><fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${shuHol.holidayTimeEnd}" /></td>
				</tr>
				<tr>
					<th width="15%">是否开工</th>
					<td width="35%" ><cus:hxlabel codeName="是或否" itemValue="${shuHol.ifStart}" /></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3" >${shuHol.remark}</td>
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
				
				<c:if test="${fn:length(checkRecords) >0}">
				<tr>
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
				</c:if>
				<tr>
					<th width="15%">审核结果</th>
					<td width="96%" colspan="3">
					<s:select name="checkRecord.checkResult" list="#{'审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
					</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea name="checkRecord.checkRemark" style="width:96%;height:60px"></textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
					
					</td>
				</tr>
			</table>
		</form>
	</div></div></div>
</body>
</html>

