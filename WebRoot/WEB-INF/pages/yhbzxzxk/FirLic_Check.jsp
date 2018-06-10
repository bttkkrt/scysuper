<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="firLicCheckSave.action">
		<s:token />
			<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="firLic.id" value="${firLic.id}">
		<input type="hidden" name="checkRecord.infoId" value="${firLic.id}">
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
					<th width="15%">许可证有效期</th>
					<td width="35%" ><fmt:formatDate type="date" value="${firLic.licenseValid}" pattern="yyyy-MM-dd"/></td>
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
				<tr>
					<th width="15%">审核结果</th>
					<td width="96%" colspan="3">
					<s:select name="checkRecord.checkResult" list="#{'审核通过':'审核通过','审核未通过':'审核未通过','审核通过并上报信用平台':'审核通过并上报信用平台'}" theme="simple"/>
					</td>
				</tr>
				<tr>
					<th width="15%">审核备注</th>
					<td width="96%" colspan="3"><textarea name="checkRecord.checkRemark" style="width:96%;height:60px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)"></textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
					    <a href="#" class="btn_01" onclick="parent.close_win('win_firLic');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
