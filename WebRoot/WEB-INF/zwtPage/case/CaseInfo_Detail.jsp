<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<style>
		*, *:before, *:after {
box-sizing: border-box;
}

.wizard-steps li {
display: table-cell;
text-align: center;
width: 1%;
}
		.wizard-steps {
list-style: none;
display: table;
width: 90%;
padding: 0;
margin: 0;
position: relative;
}

			.wizard-steps li .step {
border: 5px solid #ced1d6;
color: #546474;
font-size: 15px;
border-radius: 100%;
background-color: #FFF;
position: relative;
z-index: 2;
display: inline-block;
width: 40px;
height: 40px;
line-height: 30px;
text-align: center;
}


.wizard-steps li .title {
display: block;
margin-top: 4px;
max-width: 100%;
color: #949ea7;
font-size: 14px;
z-index: 104;
text-align: center;
table-layout: fixed;
word-wrap: break-word;
}

.wizard-steps li.active:before, .wizard-steps li.complete:before, .wizard-steps li.active .step, .wizard-steps li.complete .step {
border-color: #5293c4;
}

.wizard-steps li:first-child:before {
max-width: 51%;
left: 50%;
}


.wizard-steps li:last-child:before {
max-width: 51%;
}

.wizard-steps li:before {
display: block;
content: "";
width: 100%;
height: 1px;
font-size: 0;
overflow: hidden;
border-top: 4px solid #ced1d6;
position: relative;
top: 21px;
z-index: 1;
}

.wizard-steps li.complete .title, .wizard-steps li.active .title {
color: #2b3d53;
}


		</style>
	<script>
		function downloadAllFile(caseId,flag)
		{
			document.myform.action = "${ctx}/jsp/case/downloadAllFile.action?caseInfo.id="+caseId+"&flag="+flag;
			document.myform.submit();		
		}
		
		function downloadWs(row_Id)
        {
        	document.myform.action = "${ctx}/jsp/wsgl/instrumentsInfoExport.action?instrumentsInfo.id="+row_Id;
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
					<th width="15%">案件名称</th>
					<td width="35%">${caseInfo.caseName}</td>
					<th width="15%">案件时间</th>
					<td width="35%"><fmt:formatDate type='date' value='${caseInfo.caseTime}' /></td>
				</tr>
				<tr>
					<th width="15%">案发区域</th>
					<td width="35%">${caseInfo.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%">${caseInfo.companyName}</td>
				</tr>
				<tr>
					<th width="15%">立案时间</th>
					<td width="35%"><fmt:formatDate type='date' value='${caseInfo.laTime}'/></td>
					<th width="15%">案件编号</th>
					<td width="35%">${caseInfo.caseId}</td>
				</tr>
				<tr>
					<th width="15%">案由</th>
					<td width="85%" colspan="3">
						<textarea name="caseInfo.caseCause" style="width:96%;height:120px" readonly="readonly">${caseInfo.caseCause}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">案件来源</th>
					<td width="35%">
						<cus:hxlabel codeName="案件来源" itemValue="${caseInfo.caseSource}" />
					</td>
					<th width="15%">处罚对象</th>
					<td width="35%">
						<cus:hxlabel codeName="当事人类别" itemValue="${caseInfo.personType}" />
					</td>
				</tr>
				<s:if test="caseInfo.caseSource == 1">
				<tr>
					<th width="15%">轻伤人数</th>
					<td width="35%">${caseInfo.miborNum}</td>
					<th width="15%">重伤人数</th>
					<td width="35%">${caseInfo.injuriesNum}</td>
				</tr>
				<tr>
					<th width="15%">死亡人数</th>
					<td width="35%">${caseInfo.dieNum}</td>
					<th width="15%">事故级别</th>
					<td width="35%">
						<cus:hxlabel codeName="事故级别" itemValue="${caseInfo.accidentLevel}" />
					</td>
				</tr>
				<tr>
					<th width="15%">事故类别</th>
					<td width="85%" colspan="3">
						<cus:hxlabel codeName="事故类别" itemValue="${caseInfo.accidentCategory}" />
					</td>
				</tr>
				</s:if>
				<s:if test="caseInfo.personType == 1">
				<tr>
					<th width="15%">被处罚人</th>
					<td width="35%">${caseInfo.person}</td>
					<th width="15%">身份证号</th>
					<td width="35%">${caseInfo.sfzh}</td>
				</tr>
				<tr>
					<th width="15%">家庭住址</th>
					<td width="85%" colspan="3">${caseInfo.address}</td>
				</tr>
				</s:if>
				<s:if test="caseInfo.personType == 2">
				<tr>
					<th width="15%">法定代表人</th>
					<td width="35%">${caseInfo.fddbr}</td>
					<th width="15%">统一社会信用代码</th>
					<td width="35%">${caseInfo.xyhm}</td>
				</tr>
				</s:if>
				<tr>
					<th width="15%">年龄</th>
					<td width="35%">${caseInfo.age}</td>
					<th width="15%">性别</th>
					<td width="35%"><cus:hxlabel codeName="性别" itemValue="${caseInfo.sex}" /></td>
				</tr>
				<tr>
					<th width="15%">职务</th>
					<td width="35%">${caseInfo.zw}</td>
					<th width="15%">单位地址</th>
					<td width="35%">${caseInfo.companyAddress}</td>
				</tr>
				<tr>
					<th width="15%">电话</th>
					<td width="35%">${caseInfo.tele}</td>
					<th width="15%">邮编</th>
					<td width="35%">${caseInfo.zipCode}</td>
				</tr>
				<tr>
					<th width="15%">处罚类型</th>
					<td width="35%">
						<s:if test="caseInfo.fineType == 0">
							违法处罚
						</s:if>
						<s:elseif test="caseInfo.fineType == 1">
							事故处罚
						</s:elseif>
					</td>
					<th width="15%">承办人</th>
					<td width="35%">
						${caseInfo.undertakerName}
					</td>
				</tr>
				<tr>
					<th width="15%">当事人基本情况</th>
					<td width="85%" colspan="3">
						<textarea name="caseInfo.personCondition" style="width:96%;height:120px" readonly="readonly">${caseInfo.personCondition}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">案件基本情况</th>
					<td width="85%" colspan="3">
						<textarea name="caseInfo.caseCondition" style="width:96%;height:120px" readonly="readonly">${caseInfo.caseCondition}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="date" value="${cr.checkTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th width="15%">审核流程</th>
					<td width="96%" colspan="3">
						<ul class="wizard-steps">
							<s:if test="caseInfo.caseStatus == 5">
								<li>
									<span class="step">1</span>
									<span class="title">承办人修改信息</span>
								</li>
								<li>
									<span class="step">2</span>
									<span class="title">法务审核</span>
								</li>

								<li>
									<span class="step">3</span>
									<span class="title">电子签名确认</span>
								</li>

								<li>
									<span class="step">4</span>
									<span class="title">监察大队大队长审核</span>
								</li>

								<li>
									<span class="step">5</span>
									<span class="title">局长审核</span>
								</li>
							</s:if>
							<s:else>
								<li class="active">
									<span class="step" style="border-color:#311FD5;">1</span>
									<span class="title">承办人提交信息</span>
								</li>
								<li <s:if test="caseInfo.fwcheck == 1">class="active"</s:if>>
									<span class="step" style="border-color:#F2DE14;">2</span>
									<span class="title">法务审核</span>
								</li>

								<li <s:if test="caseInfo.dzqmcheck == 1">class="active"</s:if>>
									<span class="step" style="border-color:#0B0B0B;">3</span>
									<span class="title">电子签名确认</span>
								</li>

								<li <s:if test="caseInfo.dzcheck == 1">class="active"</s:if>>
									<span class="step" style="border-color:#187915;">4</span>
									<span class="title">监察大队大队长审核</span>
								</li>

								<li <s:if test="caseInfo.jzcheck == 1">class="active"</s:if>>
									<span class="step" style="border-color:#DA1313;">5</span>
									<span class="title">局长审核</span>
								</li>
							</s:else>
						</ul>
					</td>
				</tr>
				<s:if test="%{caseInfo.caseStatus == 2 || caseInfo.caseStatus == 3 || caseInfo.caseStatus == 4}">
					<tr>
						<th width="15%">文书下载</th>
						<td width="85%" colspan="3" style="padding:0 0 0 0;">
							<table style="padding:0 0 0 0;">
								<s:if test="%{caseInfo.caseStatus == 4}">
									<tr style="text-align: center">
										<td width="50%"><a href="javascript:downloadAllFile('${caseInfo.id}','1');">案卷（首页）.docx</a></td>
										<td width="50%"><a href="javascript:downloadAllFile('${caseInfo.id}','3');">封面.docx</a></td>
									</tr>
									<tr style="text-align: center">
										<td width="50%"><a href="javascript:downloadAllFile('${caseInfo.id}','2');">卷内目录.docx</a></td>
										<td width="50%"><a href="javascript:downloadAllFile('${caseInfo.id}','4');">照片.docx</a></td>
									</tr>
									<tr style="text-align: center">
										<td width="50%"><a href="javascript:downloadAllFile('${caseInfo.id}','5');">证据清单及证明内容.docx</a></td>
									<c:forEach var="wsxx" items="${wsList}" varStatus="status">
								  		<td width="50%"><a href="javascript:downloadWs('${wsxx.id}');">${wsxx.instrumentName}.docx</a></td>
									 	<c:if test="${status.count%2==1}"> 
									 		</tr>
									 		<tr style="text-align: center">
									 	</c:if>
							  		</c:forEach>
								</s:if>
								<s:else>
								<tr style="text-align: center">
									<c:forEach var="wsxx" items="${wsList}" varStatus="status">
								  		<td width="50%"><a href="javascript:downloadWs('${wsxx.id}');">${wsxx.instrumentName}.docx</a></td>
									 	<c:if test="${status.count%2==0}"> 
									 		</tr>
									 		<tr style="text-align: center">
									 	</c:if>
							  		</c:forEach>
							  	</tr>
							  	</s:else>
							</table>
						</td>
					</tr>
				</s:if>
				<tr>
					<th width="15%">案件材料</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table style="padding:0 0 0 0;">
							<c:forEach var="caseCl" items="${clList}">
								<tr style="text-align: center">
									<td width="30%">
										<c:if test="${caseCl.zjType == 1}">
											现场照片
										</c:if>
										<c:if test="${caseCl.zjType == 2}">
											罚没款收据回执
										</c:if>
									</td>
									<td width="70%" style="padding:0 0 0 0;">
										<table>
										 	<c:forEach var="item" items="${caseCl.picList}">
										 		<tr>
								   					<c:choose>
														<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
														||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
														||fn:endsWith(fn:toLowerCase(item.picName),'.png')
														||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
														||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
															<td><a href="${item.httpUrl}/upload/photo/${item.picName}" rel="example_group">	
																<img src="${item.httpUrl}/upload/photo/${item.picName}"
																border='0' width='220' height='150'/>
															</a></td>
															<td><a href="javascript:downloadFile('${item.id}');">下载</a></td>
														</c:when> 
														<c:otherwise> 
															<td>${item.fileName}</td>
															<td><a href="javascript:downloadFile('${item.id}');">下载</a></td>
														</c:otherwise>
													</c:choose>
							 	 				</tr>
							 	 			</c:forEach>
							 	 		</table>
									</td>
								</tr>
							</c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width="15%">证据</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table style="padding:0 0 0 0;">
							<c:forEach var="caseZj" items="${zjList}" varStatus="status">
								<tr>
									<td>${status.count}.${caseZj.zjContent}</td>
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
