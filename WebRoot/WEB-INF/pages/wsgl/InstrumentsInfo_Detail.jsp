<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
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
					<th width="15%">案件名称</th>
					<td width="35%">${instrumentsInfo.caseName}</td>
					<th width="15%">企业名称</th>
					<td width="35%">${instrumentsInfo.companyName}</td>
				</tr>
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
				<!-- 询问通知书  -->
				<s:if test="instrumentsInfo.instrumentType==1">
					<tr>
						<th width="15%">被询问人姓名</th>
						<td width="35%">${inquiryNotice.askPerson}</td>
						<th width="15%">询问时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${inquiryNotice.inquiryTime}" /></td>
					</tr>
					<tr>
						<th width="15%">询问地点</th>
						<td width="85%" colspan="3">${inquiryNotice.inquiryAddress}</td>
					</tr>
					<tr>
						<th width="15%">询问材料</th>
						<td width="85%" colspan="3">
							<input type="checkbox" id="check1" name="check1" <s:if test="inquiryNotice.sfz == 1">checked</s:if> disabled/>&nbsp;身份证&nbsp;
							<input type="checkbox" id="check2" name="check2"  <s:if test="inquiryNotice.yyzz == 1">checked</s:if> disabled/>&nbsp;营业执照&nbsp;
							<input type="checkbox" id="check3" name="check3"  <s:if test="inquiryNotice.fddbzm == 1">checked</s:if> disabled/>&nbsp;法定代表人身份证明或者委托书&nbsp;
							<input type="checkbox" id="check4" name="check4" <s:if test="inquiryNotice.qt == 1">checked</s:if> disabled/>&nbsp;${inquiryNotice.docMaterial}
						</td>
					</tr>
				</s:if>
				<!-- 询问笔录  div2 -->
				<s:if test="instrumentsInfo.instrumentType==2">
					<tr>
						<th width="15%">询问开始时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${inquiryRecord.inquiryPeriod}"/></td>
						<th width="15%">询问结束时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${inquiryRecord.endTime}"/></td>
					</tr>
					<tr>
						<th width="15%">询问次数</th>
						<td width="35%">${inquiryRecord.xwcs}</td>
						<th width="15%">询问地点</th>
						<td width="35%">${inquiryRecord.inquiryAddress}</td>
					</tr>
					<tr>
						<th width="15%">被询问人姓名</th>
						<td width="35%">${inquiryRecord.askedPerson}</td>
						<th width="15%">性别</th>
						<td width="35%"><cus:hxlabel codeName="性别" itemValue="${inquiryRecord.sex}" /></td>
					</tr>
					<tr>
						<th width="15%">年龄</th>
						<td width="35%">${inquiryRecord.peopleAge}</td>
						<th width="15%">身份证号</th>
						<td width="35%">${inquiryRecord.cardId}</td>
					</tr>
					<tr>
						<th width="15%">工作单位</th>
						<td width="35%">${inquiryRecord.companyName}</td>
						<th width="15%">职位</th>
						<td width="35%">${inquiryRecord.position}</td>
					</tr>
					<tr>	
						<th width="15%">住址</th>
						<td width="35%">${inquiryRecord.address}</td>
						<th width="15%">电话</th>
						<td width="35%">${inquiryRecord.tele}</td>
					</tr>
					<tr>
						<th width="15%">询问人</th>
						<td width="35%">
							${inquiryRecord.inquiryPersonName}
						</td>
						<th width="15%">记录人</th>
						<td width="35%">
							${inquiryRecord.recordPersonName}
						</td>
					</tr>
					<tr>	
						<th width="15%">在场人</th>
						<td width="85%" colspan="3">${inquiryRecord.presentPeople}</td>
					</tr>
					<tr>
						<th width="15%">询问记录</th>
						<td width="85%" colspan="3">
							<table style="padding:0 0 0 0;">
								<c:forEach var="inqRecRecord" items="${recordList}"  varStatus="status">
									<tr>
										<td>
											问：${inqRecRecord.askRecord}
										</td>
									</tr>
									<tr>
										<td>
											答：${inqRecRecord.recRecord}
										</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</s:if>
				<!-- 勘验笔录  div3 -->
				<s:if test="instrumentsInfo.instrumentType==3">
					<tr>
						<th width="15%">勘验开始时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${inquestRecord.startTime}"/></td>
						<th width="15%">勘验结束时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${inquestRecord.endTime}"/></td>
					</tr>
					<tr>
						<th width="15%">勘验场所</th>
						<td width="85%" colspan="3">${inquestRecord.inquestAddress}</td>
					</tr>
					<tr>
						<th width="15%">天气情况</th>
						<td width="35%">${inquestRecord.weatherCondition}</td>
						<th width="15%">当事人1</th>
						<td width="35%">${inquestRecord.party1}</td>
					</tr>
						<th width="15%">当事人1单位职务</th>
						<td width="35%">${inquestRecord.party1Company}</td>
						<th width="15%">当事人1联系方式</th>
						<td width="35%">${inquestRecord.party1Tel}</td>
					</tr>
					<tr>
						<th width="15%">当事人2</th>
						<td width="35%">${inquestRecord.party2}</td>
						<th width="15%">当事人2单位职务</th>
						<td width="35%">${inquestRecord.party2Company}</td>
					</tr>
					<tr>
						<th width="15%">当事人2联系方式</th>
						<td width="35%">${inquestRecord.party2Tel}</td>
						<th width="15%">被邀请人</th>
						<td width="35%">${inquestRecord.invitee}</td>
					</tr>
					<tr>
						<th width="15%">被邀请人单位职务</th>
						<td width="35%">${inquestRecord.inviteeCompany}</td>
						<th width="15%">被邀请人联系方式</th>
						<td width="35%">${inquestRecord.inviteeTel}</td>
					</tr>
					<tr>	
						<th width="15%">勘验人</th>
						<td width="35%">
							${inquestRecord.inquestPersonName}
						</td>
						<th width="15%">记录人</th>
						<td width="35%">
							${inquestRecord.recordPersonName}
						</td>
					</tr>
					<tr>
						<th width="15%">勘验情况</th>
						<td width="85%" colspan="3">
							<textarea name="inquestRecord.inquestCondition" style="width:90%;height:120px" readonly="readonly">${inquestRecord.inquestCondition}</textarea>
						</td>
					</tr>
				</s:if>
				<!-- 抽样取证凭证  div4 -->
				<s:if test="instrumentsInfo.instrumentType==4">
					<tr>
						<th width="15%">现场负责人</th>
						<td width="35%">${samplingEvidence.chargePerson}</td>
						<th width="15%">抽样地点</th>
						<td width="35%">${samplingEvidence.forensicAddress}</td>
					</tr>
					<tr>
						<th width="15%">抽样取证开始时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${samplingEvidence.startTime}"/></td>
						<th width="15%">抽样取证结束时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${samplingEvidence.endTime}"/></td>
					</tr>
					<tr>
						<th width="15%">执法人员</th>
						<td width="35%">
							${samplingEvidence.lawOfficerName}
						</td>
					</tr>
					<tr>
						<th colspan="4" style="text-align:center">抽样取证物品清单</th>
					</tr>
					<tr>
						<td colspan="4" style="padding:0 0 0 0;">
							<div id="more">
								<table>
									<tr>
										<td  style="text-align:center">证据物品名称</td>
										<td  style="text-align:center">规格及批号</td>
										<td  style="text-align:center">数量</td>
									</tr>	
									<c:forEach var="samplingAssociate" items="${cyqyglbList}"  varStatus="status">
									<tr style="text-align: center" id="${samplingAssociate.id}">
										<td style='text-align:center'>
											${samplingAssociate.evidenceName}
										</td>
										<td style='text-align:center'>
											${samplingAssociate.specificationLot}
										</td>
										<td style='text-align:center'>
											${samplingAssociate.samplingNum}
										</td>
									</tr>
									</c:forEach>
								</table>
							</div>
						</td>
					</tr>
				</s:if>
	
				<!-- 先行登记保存证据审批表  div5 -->
				<s:if test="instrumentsInfo.instrumentType==5">
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">
							${preserveEvidence.undertakerName}
						</td>
					</tr>
					<tr>
						<th width="15%">承办人意见</th>
						<td width="85%" colspan="3">
							<textarea name="preserveEvidence.undertakerComment" style="width:90%;height:120px" readonly="readonly">${preserveEvidence.undertakerComment}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">提请理由及依据</th>
						<td width="85%" colspan="3">
							<textarea name="preserveEvidence.reasonBasic" style="width:90%;height:120px" readonly="readonly">${preserveEvidence.reasonBasic}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">保存方式</th>
						<td width="85%" colspan="3">
							<textarea name="preserveEvidence.preserveMethod" style="width:90%;height:120px" readonly="readonly">${preserveEvidence.preserveMethod}</textarea>
						</td>
					</tr>
					<tr>
						<th colspan="4" style="text-align:center">证据清单</th>
					</tr>
					<tr>
						<td colspan="4" style="padding:0 0 0 0;">
							<div id="more1">
								<table>
									<tr>
										<td  style="text-align:center">证据名称</td>
										<td  style="text-align:center">规格型号</td>
										<td  style="text-align:center">产地</td>
										<td  style="text-align:center">成色（品级）</td>
										<td  style="text-align:center">单位</td>
										<td  style="text-align:center">价格</td>
										<td  style="text-align:center">数量</td>
										<td  style="text-align:center">备注</td>
									</tr>	
									<c:forEach var="inventoryAssociate" items="${zzdjbczjglbList}"  varStatus="status">
									<tr style="text-align: center">
										<td style='text-align:center'>
											${inventoryAssociate.evidenceName}
										</td>
										<td style='text-align:center'>
											${inventoryAssociate.specificationModel}
										</td>
										<td style='text-align:center'>
											${inventoryAssociate.originPlace}
										</td>
										<td style='text-align:center'>
											${inventoryAssociate.condition}
										</td>
										<td style='text-align:center'>
											${inventoryAssociate.company}
										</td>
										<td style='text-align:center'>
											${inventoryAssociate.price}
										</td>
										<td style='text-align:center'>
											${inventoryAssociate.inventoryNum}
										</td>
										<td style='text-align:center'>
											${inventoryAssociate.remark}
										</td>
									</tr>
									</c:forEach>
								</table>
							</div>
						</td>
					</tr>
				</s:if>				
				<!-- 先行登记保存证据通知书  div6 -->
				<s:if test="instrumentsInfo.instrumentType==6">
					<tr>
						<th width="15%">处理地点</th>
						<td width="35%">${noticeEvidence.dealAddress}</td>
						<th width="15%">处理时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${noticeEvidence.dealTime}"/></td>
					</tr>
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">
							${noticeEvidence.undertakerName}
						</td>
					</tr>
					<tr>
						<th width="15%">违法行为</th>
						<td width="85%" colspan="3">
							<textarea name="noticeEvidence.violation" style="width:90%;height:120px" readonly="readonly">${noticeEvidence.violation}</textarea>
						</td>
					</tr>
				</s:if>
	
				<!-- 先行登记保存证据处理审批表  div7 -->
				<s:if test="instrumentsInfo.instrumentType==7">
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">
							${inventoryCheck.undertakerName}
						</td>
					</tr>
					<tr>
						<th width="15%">承办人意见</th>
						<td width="85%" colspan="3">
							<textarea name="inventoryCheck.undertakerComment" style="width:90%;height:120px" readonly="readonly">${inventoryCheck.undertakerComment}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">提请理由及依据</th>
						<td width="85%" colspan="3">
							<textarea name="inventoryCheck.reasonBasic" style="width:90%;height:120px" readonly="readonly">${inventoryCheck.reasonBasic}</textarea>
						</td>
					</tr>
				</s:if>
	
				<!-- 先行登记保存证据处理决定书  div8 -->
				<s:if test="instrumentsInfo.instrumentType==8">
					<tr>
						<th width="15%">处理</th>
						<td width="85%" colspan="3">
							<textarea name="inventoryDecision.deal" style="width:90%;height:120px" readonly="readonly">${inventoryDecision.deal}</textarea>
						</td>
					</tr>
				</s:if>
	
				<!-- 现场检查记录  div9 -->
				<s:if test="instrumentsInfo.instrumentType==9">
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
	
				<!-- 现场处理措施决定书  div10 -->
				<s:if test="instrumentsInfo.instrumentType==10">
					<tr>
						<th width="15%">执法人员</th>
						<td width="35%">
							${liveActionDecision.lawOfficerName}
						</td>
					</tr>
					<tr>
						<th width="15%">违法违规行为</th>
						<td width="85%" colspan="3">
							<textarea name="liveActionDecision.illegalAccident" style="width:90%;height:120px" readonly="readonly">${liveActionDecision.illegalAccident}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">处理决定</th>
						<td width="85%" colspan="3">
							<textarea name="liveActionDecision.dealDecision" style="width:90%;height:120px" readonly="readonly">${liveActionDecision.dealDecision}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">执法依据</th>
						<td width="85%" colspan="3">
							${liveActionDecision.lawName}
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
						<td width="35%">
							${reviewSubmission.lawOfficerName}</td>
					</tr>
					<tr>
						<th width="15%">复查意见</th>
						<td width="85%" colspan="3">
							<textarea name="reviewSubmission.reviewComment" style="width:90%;height:120px" readonly="readonly">${reviewSubmission.reviewComment}</textarea>
						</td>
					</tr>
				</s:if>
	
				<!-- 强制措施决定书  div13 -->
				<s:if test="instrumentsInfo.instrumentType==13">
					<tr>
						<th width="15%">问题</th>
						<td width="85%" colspan="3">
							<textarea name="enforenceDecision.problem" style="width:90%;height:120px" readonly="readonly">${enforenceDecision.problem}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">执法依据</th>
						<td width="85%" colspan="3">
							${enforenceDecision.lawName}
						</td>
					</tr>
					<tr>
						<th width="15%">措施</th>
						<td width="85%" colspan="3">
							<textarea name="enforenceDecision.method" style="width:90%;height:120px" readonly="readonly">${enforenceDecision.method}</textarea>
						</td>
					</tr>
				</s:if>
	
				<!-- 鉴定委托书  div14 -->
				<s:if test="instrumentsInfo.instrumentType==14">
					<tr>
						<th width="15%">鉴定机构名称</th>
						<td width="35%">${identifyAttorney.jdjgName}</td>
						<th width="15%">提交时间</th>
						<td width="35%"><fmt:formatDate type="date" value="${identifyAttorney.submitTime}"/></td>
					</tr>
					<tr>
						<th width="15%">鉴定要求</th>
						<td width="85%" colspan="3">
							<textarea name="identifyAttorney.inentifyRequire" style="width:90%;height:120px" readonly="readonly">${identifyAttorney.inentifyRequire}</textarea>
						</td>
					</tr>
					<tr>
						<th colspan="4" style="text-align:center">鉴定物品清单</th>
					</tr>
					<tr>
						<td colspan="4" style="padding:0 0 0 0;">
							<div id="more2">
								<table>
									<tr>
										<td  style="text-align:center">物品名称</td>
										<td  style="text-align:center">规格型号</td>
										<td  style="text-align:center">数量</td>
										<td  style="text-align:center">备注</td>
									</tr>	
									<c:forEach var="identifyItemAssociate" items="${jdwpList}"  varStatus="status">
									<tr style="text-align: center">
										<td style='text-align:center'>
											${identifyItemAssociate.itemName}
										</td>
										<td style='text-align:center'>
											${identifyItemAssociate.specificationModel}
										</td>
										<td style='text-align:center'>
											${identifyItemAssociate.identifyNum}
										</td>
										<td style='text-align:center'>
											${identifyItemAssociate.remark}
										</td>
									</tr>
									</c:forEach>
								</table>
							</div>
						</td>
					</tr>
				</s:if>
	
	
				<!-- 行政处罚告知书  div15 -->
				<s:if test="instrumentsInfo.instrumentType==15">
					<tr>	
						<td colspan="4">
						${penaltyNotice.caseCondition}经查，你（单位）${penaltyNotice.wfxw}。以上事实违反了${penaltyNotice.proName}的规定，
						依据${penaltyNotice.lawName}的规定，拟给予你（单位）${penaltyNotice.adminPenality}的行政处罚。
						</td>
					</tr>
				</s:if>
	
				<!-- 当事人陈述申辩笔录  div16 -->
				<s:if test="instrumentsInfo.instrumentType==16">
					<tr>
						<th width="15%">陈述开始时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${partyStateNote.startTime}"/></td>
						<th width="15%">陈述结束时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${partyStateNote.endTime}"/></td>
					</tr>
					<tr>
						<th width="15%">陈述地点</th>
						<td width="85%" colspan="3">${partyStateNote.stateAddress}</td>
					</tr>
					<tr>
						<th width="15%">陈述申辩人</th>
						<td width="35%">${partyStateNote.stateDefense}</td>
						<th width="15%">性别</th>
						<td width="35%"><cus:hxlabel codeName="性别" itemValue="${partyStateNote.sex}" /></td>
					</tr>
					<tr>
						<th width="15%">职务</th>
						<td width="35%">${partyStateNote.position}</td>
						<th width="15%">电话</th>
						<td width="35%">${partyStateNote.tele}</td>							
					</tr>
					<tr>
						<th width="15%">联系地址</th>
						<td width="35%">${partyStateNote.address}</td>
						<th width="15%">邮编</th>
						<td width="35%">${partyStateNote.zipCode}</td>
					</tr>
					<tr>
						<th width="15%">陈述申辩记录</th>
						<td width="85%" colspan="3">
							<textarea name="partyStateNote.stateRecord" style="width:90%;height:120px" readonly="readonly">${partyStateNote.stateRecord}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">
							${partyStateNote.undertakerName}</td>
						<th width="15%">记录人</th>
						<td width="35%">
							${partyStateNote.recorderName}</td>
					</tr>
				</s:if>
				
				<!-- 听证告知书  div17 -->
				<s:if test="instrumentsInfo.instrumentType==17">
					<tr>	
						<td colspan="4">
						${penaltyNotice.caseCondition}经查，你（单位）${penaltyNotice.wfxw}。以上事实违反了${penaltyNotice.proName}的规定，
						依据${penaltyNotice.lawName}的规定，拟给予你（单位）${penaltyNotice.adminPenality}的行政处罚。
						</td>
					</tr>
				</s:if>
	
				<!-- 听证会通知书  div18 -->
				<s:if test="instrumentsInfo.instrumentType==18">
					<tr>
						<th width="15%">听证会时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${hearingNotice.hearingTime}"/></td>
						<th width="15%">是否公开</th>
						<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${hearingNotice.publicCondition}" /></td>
					</tr>
					<tr>
						<th width="15%">听证地点</th>
						<td width="85%" colspan="3">${hearingNotice.hearingAddress}</td>
					</tr>
					<tr>
						<th width="15%">听证主持人姓名</th>
						<td width="35%">
							${hearingNotice.hearingChairpersonName}
						</td>
						<th width="15%">书记员</th>
						<td width="35%">
							${hearingNotice.clerkName}
						</td>
					</tr>
					<tr>
						<th width="15%">听证员</th>
						<td width="85%" colspan="3">
							${hearingNotice.hearingOfficerName}
						</td>
					</tr>
				</s:if>
	
				<!-- 听证笔录  div19 -->
				<s:if test="instrumentsInfo.instrumentType==19">
					<tr>
						<th width="15%">听证开始时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${hearingNote.startTime}"/></td>
						<th width="15%">听证结束时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${hearingNote.endTime}"/></td>
					</tr>
					<tr>
						<th width="15%">听证地点</th>
						<td width="85%" colspan="3">${hearingNotice.hearingAddress}</td>
					</tr>
					<tr>
						<th width="15%">听证主持人姓名</th>
						<td width="35%">
							${hearingNotice.hearingChairpersonName}
						</td>
						<th width="15%">书记员</th>
						<td width="35%">
							${hearingNotice.clerkName}
						</td>
					</tr>
					<tr>
						<th width="15%">听证员</th>
						<td width="85%" colspan="3">
							${hearingNotice.hearingOfficerName}
						</td>
					</tr>
					<tr>
						<th width="15%">委托代理人1</th>
						<td width="35%">${hearingNote.attorney1}</td>
						<th width="15%">委托代理人1性别</th>
						<td width="35%"><cus:hxlabel codeName="性别" itemValue="${hearingNote.attorney1Sex}" /></td>
					</tr>
					<tr>
						<th width="15%">委托代理人1工作单位</th>
						<td width="35%">${hearingNote.attorney1Company}</td>
						<th width="15%">委托代理人1年龄</th>
						<td width="35%">${hearingNote.attorney1Age}</td>
						
					</tr>
					<tr>
						<th width="15%">委托代理人2</th>
						<td width="35%">${hearingNote.attorney2}</td>
						<th width="15%">委托代理人2性别</th>
						<td width="35%"><cus:hxlabel codeName="性别" itemValue="${hearingNote.attorney2Sex}" /></td>
					</tr>
					<tr>
						<th width="15%">委托代理人2工作单位</th>
						<td width="35%">${hearingNote.attorney2Company}</td>
						<th width="15%">委托代理人2年龄</th>
						<td width="35%">${hearingNote.attorney2Age}</td>
						
					</tr>
					<tr>
						<th width="15%">调查人员</th>
						<td width="35%">
							${hearingNote.undertakerName}
						</td>
						<th width="15%">第三人</th>
						<td width="35%">${hearingNote.thirdPerson}</td>
					</tr>
					<tr>
						<th width="15%">其他参与人员</th>
						<td width="85%" colspan="3">${hearingNote.otherParticipants}</td>
					</tr>
					<tr>	
						<th width="15%">听证记录</th>
						<td width="85%" colspan="3">
							<textarea name="hearingNote.hearingRecord" style="width:90%;height:120px" readonly="readonly">${hearingNote.hearingRecord}</textarea>
						</td>
					</tr>
				</s:if>
	
				<!-- 听证会报告书  div20 -->
				<s:if test="instrumentsInfo.instrumentType==20">
					<tr>
						<th width="15%">听证主持人姓名</th>
						<td width="35%">
							${hearingNotice.hearingChairpersonName}
						</td>
						<th width="15%">书记员</th>
						<td width="35%">
							${hearingNotice.clerkName}
						</td>
					</tr>
					<tr>
						<th width="15%">听证员</th>
						<td width="85%" colspan="3">
							${hearingNotice.hearingOfficerName}
						</td>
					</tr>
					<tr>
						<th width="15%">听证会基本情况摘要</th>
						<td width="85%" colspan="3">
							<textarea name="hearingReport.hearingSummary" style="width:90%;height:120px" readonly="readonly">${hearingReport.hearingSummary}</textarea>
						</td>
					</tr>
				</s:if>
				
				<!-- 案件处理呈批表  div21 -->
				<s:if test="instrumentsInfo.instrumentType==21">
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">
							${caseProcessApproval.undertakerName}
						</td>
					</tr>
					<tr>
						<th width="15%">违法事实及处罚依据</th>
						<td width="85%" colspan="3">
							<textarea name="caseProcessApproval.wfss" style="width:90%;height:120px" readonly="readonly">${caseProcessApproval.wfss}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">当事人申辩意见</th>
						<td width="85%" colspan="3">
							<textarea name="caseProcessApproval.dsrsbyj" style="width:90%;height:120px" readonly="readonly">${caseProcessApproval.dsrsbyj}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">承办人意见</th>
						<td width="85%" colspan="3">
							<textarea name="caseProcessApproval.undertakerComment" style="width:90%;height:120px" readonly="readonly">${caseProcessApproval.undertakerComment}</textarea>
						</td>
					</tr>
				</s:if>
	
	
				<!-- 行政处罚集体讨论记录  div22 -->
				<s:if test="instrumentsInfo.instrumentType==22">
					<tr>
						<th width="15%">讨论开始时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${penBraRec.startTime}"/></td>
						<th width="15%">讨论结束时间</th>
						<td width="35%"><fmt:formatDate type="both" value="${penBraRec.endTime}"/></td>
					</tr>
					<tr>
						<th width="15%">讨论地点</th>
						<td width="35%">${penBraRec.discussionAddress}</td>
						<th width="15%">主持人</th>
						<td width="35%">${penBraRec.chairpersonName}
						</td>
					</tr>
					<tr>
						<th width="15%">汇报人</th>
						<td width="35%">${penBraRec.reportPersonName}
						</td>
						<th width="15%">记录人</th>
						<td width="35%">${penBraRec.recordPersonName}
						</td>
					</tr>
					<tr>
						<th width="15%">出席人员姓名及职务</th>
						<td width="85%" colspan="3">
							${penBraRec.attendName}
						</td>
					</tr>
					<tr>
						<th width="15%">讨论内容</th>
						<td width="85%" colspan="3">
							<textarea name="penBraRec.discussionContent" style="width:90%;height:120px" readonly="readonly">${penBraRec.discussionContent}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">讨论记录</th>
						<td width="85%" colspan="3">
							<textarea name="penBraRec.discussionRecord" style="width:90%;height:120px" readonly="readonly">${penBraRec.discussionRecord}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">结论性意见</th>
						<td width="85%" colspan="3">
							<textarea name="penBraRec.conclusionComment" style="width:90%;height:120px" readonly="readonly">${penBraRec.conclusionComment}</textarea>
						</td>
					</tr>
				</s:if>
	
				<!-- 行政（当场）处罚决定书（单位）  div23 -->
				<s:if test="instrumentsInfo.instrumentType==23">	
					<tr>
						<th width="15%">执法人员</th>
						<td width="35%">${spoPenDecCom.lawOfficerName}
						</td>
						<th width="15%">没收违法所得</th>
						<td width="35%">${spoPenDecCom.illegalIncome}</td>
					</tr>
					<tr>
						<th width="15%">处罚种类</th>
						<td width="85%" colspan="3"><cus:hxmulselectlabel codeName="处罚种类" itemValue="${spoPenDecCom.punishedSpecies}" /></td>
					</tr>
					<tr>
						<th width="15%">罚款金额</th>
						<td width="35%">${spoPenDecCom.fines}</td>
						<th width="15%">罚款方式</th>
						<td width="35%">
							<s:if test="spoPenDecCom.fineMethod == 0">
								当场缴纳
							</s:if>
							<s:else>
								缴纳至银行卡
							</s:else>
						</td>
					</tr>
					<tr>
						<th width="15%">行政处罚</th>
						<td width="85%" colspan="3">
							<textarea name="spoPenDecCom.adminPenalties" style="width:90%;height:120px" readonly="readonly">${spoPenDecCom.adminPenalties}</textarea>
						</td>
					</tr>
				</s:if>
	
				<!-- 行政（当场）处罚决定书（个人）  div24 -->
				<s:if test="instrumentsInfo.instrumentType==24">
					<tr>
						<th width="15%">执法人员</th>
						<td width="35%">${spoPenDecPer.lawOfficerName}
						</td>
						<th width="15%">没收违法所得</th>
						<td width="35%">${spoPenDecPer.illegalIncome}</td>
					</tr>
					<tr>
						<th width="15%">处罚种类</th>
						<td width="85%" colspan="3"><cus:hxmulselectlabel codeName="处罚种类" itemValue="${spoPenDecPer.punishedSpecies}" /></td>
						
					</tr>
					<tr>
						<th width="15%">罚款金额</th>
						<td width="35%">${spoPenDecPer.fines}</td>
						<th width="15%">罚款方式</th>
						<td width="35%">
							<s:if test="spoPenDecPer.fineMethod == 0">
								当场缴纳
							</s:if>
							<s:else>
								缴纳至银行卡
							</s:else>
						</td>
					</tr>
					<tr>
						<th width="15%">行政处罚</th>
						<td width="85%" colspan="3">
							<textarea name="spoPenDecPer.adminPenalties" style="width:90%;height:120px" readonly="readonly">${spoPenDecPer.adminPenalties}</textarea>
						</td>
					</tr>
				</s:if>
	
	
				<!-- 行政处罚决定书（单位）  div25 -->
				<s:if test="instrumentsInfo.instrumentType==25">
					<tr>
						<th width="15%">违法事实</th>
						<td width="85%" colspan="3">
							<textarea name="penDecCom.wfss" style="width:90%;height:120px" readonly="readonly">${penDecCom.wfss}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">违反规定</th>
						<td width="85%" colspan="3">
							${penaltyNotice.proName}
						</td>
					</tr>
					<tr>
						<th width="15%">执法依据</th>
						<td width="85%" colspan="3">
							${penaltyNotice.lawName}
						</td>
					</tr>
					<tr>
						<th width="15%">行政处罚</th>
						<td width="85%" colspan="3">
							<textarea name="penDecCom.adminPenalties" style="width:90%;height:120px" readonly="readonly">${penDecCom.adminPenalties}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">处罚种类</th>
						<td width="85%" colspan="3"><cus:hxmulselectlabel codeName="处罚种类" itemValue="${penDecCom.punishedSpecies}" /></td>
					</tr>
					<tr>
						<th width="15%">没收违法所得</th>
						<td width="35%">${penDecCom.illegalIncome}</td>
						<th width="15%">罚款金额</th>
						<td width="35%">${penDecCom.fines}</td>
					</tr>
				</s:if>
	
				<!-- 行政处罚决定书（个人）  div26 -->
				<s:if test="instrumentsInfo.instrumentType==26">
					<tr>
						<th width="15%">违法事实</th>
						<td width="85%" colspan="3">
							<textarea name="penDecPer.wfss" style="width:90%;height:120px" readonly="readonly">${penDecPer.wfss}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">违反规定</th>
						<td width="85%" colspan="3">
							${penaltyNotice.proName}
						</td>
					</tr>
					<tr>
						<th width="15%">执法依据</th>
						<td width="85%" colspan="3">
							${penaltyNotice.lawName}
						</td>
					</tr>
					<tr>
						<th width="15%">行政处罚</th>
						<td width="85%" colspan="3">
							<textarea name="penDecPer.adminPenalties" style="width:90%;height:120px" readonly="readonly">${penDecPer.adminPenalties}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">处罚种类</th>
						<td width="85%" colspan="3"><cus:hxmulselectlabel codeName="处罚种类" itemValue="${penDecPer.punishedSpecies}" /></td>
					</tr>
					<tr>
						<th width="15%">没收违法所得</th>
						<td width="35%">${penDecPer.illegalIncome}</td>
						<th width="15%">罚款金额</th>
						<td width="35%">${penDecPer.fines}</td>
					</tr>
				</s:if>
	
	
				<!-- 延期（分期）缴纳罚款审批表  div28 -->
				<s:if test="instrumentsInfo.instrumentType==28">
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">
							${posFinApp.undertakerName}
						</td>
					</tr>
					<tr>
						<th width="15%">承办人意见</th>
						<td width="85%" colspan="3">
							<textarea name="posFinApp.undertakerComment" style="width:90%;height:120px" readonly="readonly">${posFinApp.undertakerComment}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">理由</th>
						<td width="85%" colspan="3">
							<textarea name="posFinApp.reason" style="width:90%;height:120px" readonly="readonly">${posFinApp.reason}</textarea>
						</td>
					</tr>
				</s:if>
	
				<!-- 延期（分期）缴纳罚款批准书  div29 -->
				<s:if test="instrumentsInfo.instrumentType==29">
					<tr>
						<th width="15%">罚款(大写)</th>
						<td width="35%">${posFinRat.fineUppercase}</td>
						<th width="15%">延期方式</th>
						<td width="35%">
							<s:if test="posFinRat.postponeMethod==0">
								延期缴纳罚款
							</s:if>
							<s:else>
								分期缴纳罚款
							</s:else>
					</tr>
					<tr>
						<th width="15%">缴费期限</th>
						<td width="35%">${posFinRat.repayPeriod}</td>
					</tr>
					<s:if test="posFinRat.postponeMethod==1">
						<tr>
							<th width="15%">缴费期</th>
							<td width="35%">${posFinRat.stageLength}</td>
							<th width="15%">缴纳罚款(大写)</th>
							<td width="35%">${posFinRat.pay}</td>
						</tr>
						<tr>
							<th width="15%">尚未缴纳罚款(大写)</th>
							<td width="35%">${posFinRat.noPay}</td>
						</tr>
					</s:if>
				</s:if>
	
				<!-- 强制执行申请书  div30 -->
				<s:if test="instrumentsInfo.instrumentType==30">
					<tr>
						<th width="15%">法院名称</th>
						<td width="85%" colspan="3">${enfApp.courtName}人民法院</td>
					</tr>
					<tr>
						<th width="15%">有关材料</th>
						<td width="85%" colspan="3">
							<textarea name="enfApp.ygcl" style="width:90%;height:120px" readonly="readonly">${enfApp.ygcl}</textarea>
						</td>
					</tr>
				</s:if>
	
	
				<!-- 结案审批表  div31 -->
				<s:if test="instrumentsInfo.instrumentType==31">
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">${closeApproval.undertakerName}
						</td>
					</tr>
					<tr>
						<th width="15%">处理结果</th>
						<td width="85%" colspan="3">
							<textarea name="closeApproval.approvalResult" style="width:90%;height:120px" readonly="readonly">${closeApproval.approvalResult}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">执行情况</th>
						<td width="85%" colspan="3">
							<textarea name="closeApproval.executeCondition" style="width:90%;height:120px" readonly="readonly">${closeApproval.executeCondition}</textarea>
						</td>
					</tr>
				</s:if>
	
				<!-- 案件移送审批表 div32 -->
				<s:if test="instrumentsInfo.instrumentType==32">
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">
							${caseRefer.undertakerName}
						</td>
						<th width="15%">受移送机关</th>
						<td width="35%">${caseRefer.transferAuthority}</td>
					</tr>
					<tr>
						<th width="15%">承办人意见</th>
						<td width="85%" colspan="3">
							<textarea name="caseRefer.undertakerComment" style="width:90%;height:120px" readonly="readonly">${caseRefer.undertakerComment}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">移送理由</th>
						<td width="85%" colspan="3">
							<textarea name="caseRefer.feedingGrounds" style="width:90%;height:120px" readonly="readonly">${caseRefer.feedingGrounds}</textarea>
						</td>
					</tr>
				</s:if>
	
				<!-- 案件移送书  div33 -->
				<s:if test="instrumentsInfo.instrumentType==33">
					<tr>
						<th width="15%">规定</th>
						<td width="85%" colspan="3">
							${caseTransfer.proName}
						</td>
					</tr>
					<tr>
						<th width="15%">有关材料</th>
						<td width="85%" colspan="3">
							<textarea name="caseTransfer.ygcl" style="width:90%;height:120px" readonly="readonly">${caseTransfer.ygcl}</textarea>
						</td>
					</tr>
				</s:if>
				
				<!-- 调查报告  div34 -->
				<s:if test="instrumentsInfo.instrumentType==34">
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">${dcbg.undertakerName}
						</td>
					</tr>
					<tr>
						<th width="15%">案件基本情况</th>
						<td width="35%" colspan="3">
							<textarea name="dcbg.caseCondition" style="width:90%;height:120px" readonly="readonly">${dcbg.caseCondition}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">调查取证情况</th>
						<td width="85%" colspan="3">
							<textarea name="dcbg.dcbgqk" style="width:90%;height:120px" readonly="readonly">${dcbg.dcbgqk}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">违法违规情况</th>
						<td width="85%" colspan="3">
							<textarea name="dcbg.wfwgxw" style="width:90%;height:120px" readonly="readonly">${dcbg.wfwgxw}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">拟办意见</th>
						<td width="85%" colspan="3">
							<textarea name="dcbg.undertakerComment" style="width:90%;height:120px" readonly="readonly">${dcbg.undertakerComment}</textarea>
						</td>
					</tr>
				</s:if>	
				
				<!-- 特殊事项审批表  div35 -->
				<s:if test="instrumentsInfo.instrumentType==35">
					<tr>
						<th width="15%">事项标题</th>
						<td width="85%" colspan="3">
							${specialItem.title}
						</td>
					</tr>
					<tr>
						<th width="15%">事项理由</th>
						<td width="85%" colspan="3">
							<textarea name="specialItem.content" style="width:90%;height:120px" readonly="readonly">${specialItem.content}</textarea>
						</td>
					</tr>
				</s:if>
				
				<!-- 案件信息 -->
				<s:if test="instrumentsInfo.instrumentType==100">
					<tr>
					<th width="15%">案件编号</th>
					<td width="35%">${caseInfo.caseId}</td>
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
				</s:if>
				
				<c:if test="${fn:length(checkRecords) >0}">
					<tr>
						<th width="15%">审核记录</th>
						<td width="85%" colspan="3">
							<c:forEach items="${checkRecords }" var="cr">
								<fmt:formatDate type="date" value="${cr.checkTime}" />，${cr.checkUsername}${cr.checkResult}[备注：${cr.checkRemark}]<br/>
							</c:forEach>
						</td>
					</tr>
				</c:if>
				<tr>
					<th width="15%">审核流程</th>
					<td width="96%" colspan="3">
						<ul class="wizard-steps">
							<s:if test="instrumentsInfo.ifCheck == 4">
								<li>
									<span class="step">1</span>
									<span class="title">承办人修改信息</span>
								</li>
								<c:forEach items="${proList}" var="pro">
									<li>
										<span class="step">${pro.num}</span>
										<span class="title">${pro.name}</span>
									</li>
								</c:forEach>
							</s:if>
							<s:else>
								<li class="active">
									<span class="step">1</span>
									<span class="title">承办人提交信息</span>
								</li>
								<c:forEach items="${proList}" var="pro">
									<li <c:if test="${pro.sfwc == 1}">class="active"</c:if>>
										<span class="step">${pro.num}</span>
										<span class="title">${pro.name}</span>
									</li>
								</c:forEach>
							</s:else>
							
						</ul>
					</td>
				</tr>
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
