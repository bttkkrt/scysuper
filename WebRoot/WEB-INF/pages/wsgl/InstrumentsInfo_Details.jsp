<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function downloadWs(row_Id)
        {
        	document.myform.action = "${ctx}/jsp/wsgl/instrumentsInfoExport.action?instrumentsInfo.id="+row_Id;
        	document.myform.submit();
        }
        
        function downloadlsws(fileName)
        {
        	document.myform.action = "${ctx}/jsp/wsgl/instrumentsInfoExport.action?instrumentsInfo.id=${instrumentsInfo.id}&fileName="+encodeURIComponent(fileName);
        	document.myform.submit();
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
					<th width="15%">文书名称</th>
					<td width="35%" >${instrumentsInfo.instrumentName}</td>
					<th width="15%">文书类型</th>
					<td width="35%" ><cus:hxlabel codeName="文书类型" itemValue="${instrumentsInfo.instrumentType}" /></td>
				</tr>
				<tr>
					<th width="15%">文书时间</th>
					<td width="35%"><fmt:formatDate type="date" value="${instrumentsInfo.time}" /></td>
					<th width="15%">文书编号</th>
					<td width="35%">${instrumentsInfo.wsh}</td>
				</tr>
				<tr>
					<th width="15%">被检查单位</th>
					<td width="35%">${instrumentsInfo.companyName}</td>
					<th width="15%">处罚类型</th>
					<td width="35%">
						<s:if test="instrumentsInfo.personType == 0">
							违法处罚
						</s:if>
						<s:elseif test="instrumentsInfo.personType == 1">
							事故处罚
						</s:elseif>
					</td>
				</tr>
				<!-- 现场检查记录  div9 -->
				<s:if test="instrumentsInfo.instrumentType==9">
					<tr>
						<th width="15%">单位地址</th>
						<td width="35%">${siteCheckRecord.companyAddress}</td>
						<th width="15%">法定代表人（负责人）</th>
						<td width="35%">${siteCheckRecord.chargePerson}</td>
					</tr>
					<tr>	
						<th width="15%">职务</th>
						<td width="35%">${siteCheckRecord.chargePersonZw}</td>
						<th width="15%">联系电话</th>
						<td width="35%">${siteCheckRecord.chargePersonTel}</td>
					</tr>
					<tr>
						<th width="15%">检查开始时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${siteCheckRecord.startTime}"/></td>
						<th width="15%">检查结束时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${siteCheckRecord.endTime}"/></td>
					</tr>
					<tr>
						<th width="15%">检查人员</th>
						<td width="35%">
							${siteCheckRecord.checkPersonName}
						</td>
						<th width="15%">检查场所</th>
						<td width="35%">${siteCheckRecord.checkAddress}</td>
					</tr>
					<tr>
						<th width="15%">检查情况</th>
						<td width="85%" colspan="3">
							<textarea name="siteCheckRecord.checkCondition" style="width:90%;height:120px" readonly="readonly">${siteCheckRecord.checkCondition}</textarea>
						</td>
					</tr>
				</s:if>
	
	
				<!-- 责令限期整改指令书  div11 -->
			 	<s:if test="instrumentsInfo.instrumentType==11">	
			 		<tr>
						<th width="15%">执法人员</th>
						<td width="35%">
							${orderDeadlineBook.lawOfficerName}
						</td>
					</tr>
					<tr>
						<th width="15%">整改项</th>
						<td width="35%">${orderDeadlineBook.changeItem}</td>
						<th width="15%">整改期限</th>
						<td width="35%"><fmt:formatDate type="date" value="${orderDeadlineBook.startTime}"/></td>
					</tr>
					<tr>
						<th width="15%">问题</th>
						<td width="85%" colspan="3">
							<textarea name="orderDeadlineBook.problem" style="width:90%;height:120px" readonly="readonly">${orderDeadlineBook.problem}</textarea>
						</td>
					</tr>
				</s:if>
	
				<!-- 整改复查意见书  div12 -->
				<s:if test="instrumentsInfo.instrumentType==12">
					<tr>
						<th width="15%">执法人员</th>
						<td width="35%">${reviewSubmission.lawOfficerName}</td>
						<th width="15%">责令整改时间</th>
						<td width="35%"><fmt:formatDate type='date' value='${reviewSubmission.xqzgTime}' pattern='yyyy-MM-dd'/></td>
					</tr>
					<tr>
						<th width="15%">责令整改文号</th>
						<td width="85%" colspan="3">${reviewSubmission.jdajbz}〔${reviewSubmission.zfh}〕${reviewSubmission.zfhNum}号</td>
					</tr>
					<tr>
						<th width="15%">责令整改决定</th>
						<td width="85%" colspan="3">${reviewSubmission.zgjd}</td>
					</tr>
					<tr>
						<th width="15%">复查意见</th>
						<td width="85%" colspan="3">
							<textarea name="reviewSubmission.reviewComment" style="width:90%;height:120px" readonly="readonly">${reviewSubmission.reviewComment}</textarea>
						</td>
					</tr>
				</s:if>
	
				<s:if test="instrumentsInfo.ifPrint == 1">
					<tr>
						<th width="15%">文书下载</th>
						<td width="96%" colspan="3">
							<a href="#" onclick="downloadWs('${instrumentsInfo.id}')">${instrumentsInfo.fileName}</a>
						</td>
					</tr>
					<tr>
						<th width="15%">文书回执图片</th>
						<td width="85%" colspan="3">
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
				</s:if>
				<c:if test="${fn:length(lastFile) >0}">
					<tr>
						<th width="15%">历史版本下载</th>
						<td width="96%" colspan="3" style="padding:0 0 0 0;">
							<table style="padding:0 0 0 0;">
								<tr style="text-align: center">
									<c:forEach items="${lastFile}" var="filename" varStatus="status">
										<td width="50%"><a href="#" onclick="downloadlsws('${filename}')">${filename}</a></td>
											<c:if test="${status.count%2==0}"> 
									 			</tr>
									 			<tr style="text-align: center">
									 		</c:if>
									</c:forEach>
							</table>
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_instrumentsInfo');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
