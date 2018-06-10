<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>审核记录</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="instrumentsInfoShenheSave.action">
		<s:token />
		<input type="hidden" name="instrumentsInfo.id" value="${instrumentsInfo.id}">
		<input type="hidden" name="inquiryNotice.id" value="${inquiryNotice.id}">
		<input type="hidden" name="inquiryRecord.id" value="${inquiryRecord.id}">
		<input type="hidden" name="inquestRecord.id" value="${inquestRecord.id}">
		<input type="hidden" name="samplingEvidence.id" value="${samplingEvidence.id}">
		<input type="hidden" name="preserveEvidence.id" value="${preserveEvidence.id}">
		<input type="hidden" name="noticeEvidence.id" value="${noticeEvidence.id}">
		<input type="hidden" name="inventoryCheck.id" value="${inventoryCheck.id}">
		<input type="hidden" name="inventoryDecision.id" value="${inventoryDecision.id}">
		<input type="hidden" name="siteCheckRecord.id" value="${siteCheckRecord.id}">
		<input type="hidden" name="liveActionDecision.id" value="${liveActionDecision.id}">
		<input type="hidden" name="orderDeadlineBook.id" value="${orderDeadlineBook.id}">
		<input type="hidden" name="reviewSubmission.id" value="${reviewSubmission.id}">
		<input type="hidden" name="enforenceDecision.id" value="${enforenceDecision.id}">
		<input type="hidden" name="identifyAttorney.id" value="${identifyAttorney.id}">
		<input type="hidden" name="penaltyNotice.id" value="${penaltyNotice.id}">
		<input type="hidden" name="partyStateNote.id" value="${partyStateNote.id}">
		<input type="hidden" name="hearingTell.id" value="${hearingTell.id}">
		<input type="hidden" name="hearingNotice.id" value="${hearingNotice.id}">
		<input type="hidden" name="hearingNote.id" value="${hearingNote.id}">
		<input type="hidden" name="hearingReport.id" value="${hearingReport.id}">
		<input type="hidden" name="caseProcessApproval.id" value="${caseProcessApproval.id}">
		<input type="hidden" name="penBraRec.id" value="${penBraRec.id}">
		<input type="hidden" name="spoPenDecCom.id" value="${spoPenDecCom.id}">
		<input type="hidden" name="spoPenDecPer.id" value="${spoPenDecPer.id}">
		<input type="hidden" name="penDecCom.id" value="${penDecCom.id}">
		<input type="hidden" name="penDecPer.id" value="${penDecPer.id}">
		<input type="hidden" name="posFinApp.id" value="${posFinApp.id}">
		<input type="hidden" name="posFinRat.id" value="${posFinRat.id}">
		<input type="hidden" name="enfApp.id" value="${enfApp.id}">
		<input type="hidden" name="closeApproval.id" value="${closeApproval.id}">
		<input type="hidden" name="caseRefer.id" value="${caseRefer.id}">
		<input type="hidden" name="caseTransfer.id" value="${caseTransfer.id}">
		<input type="hidden" name="dcbg.id" value="${dcbg.id}">
		<input type="hidden" name="specialItem.id" value="${specialItem.id}">
		
			<table width="100%">
				<tr>
					<th width="15%">案件名称</th>
					<td width="85%" colspan="3">${instrumentsInfo.caseName}</td>
				</tr>
				<tr>
					<th width="15%">文书名称</th>
					<td width="35%" >${instrumentsInfo.instrumentName}</td>
					<th width="15%">文书类型</th>
					<td width="35%" ><cus:hxlabel codeName="文书类型" itemValue="${instrumentsInfo.instrumentType}" /></td>
				</tr>
				<tr>
					<th width="15%">文书时间</th>
					<td width="35%"><fmt:formatDate type="date" value="${instrumentsInfo.time}"  pattern='yyyy-MM-dd'/></td>
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
						<th width="15%">承办人意见</th>
						<td width="85%" colspan="3">
							<textarea name="caseProcessApproval.undertakerComment" style="width:90%;height:120px" readonly="readonly">${caseProcessApproval.undertakerComment}</textarea>
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
						<th width="15%">执行情况</th>
						<td width="85%" colspan="3">
							<textarea name="closeApproval.executeCondition" style="width:90%;height:120px" readonly="readonly">${closeApproval.executeCondition}</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">处理结果</th>
						<td width="85%" colspan="3">
							<textarea name="closeApproval.approvalResult" style="width:90%;height:120px" readonly="readonly">${closeApproval.approvalResult}</textarea>
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
				<tr>
					<th width="15%">审核记录</th>
					<td width="85%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="date" value="${cr.checkTime}" />，${cr.checkUsername}${cr.checkResult}[备注：${cr.checkRemark}]<br/>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th width="15%">审核结果</th>
					<td width="35%">
						<s:select name="instrumentsInfo.result" list="#{'0':'审核通过','1':'审核不通过'}" theme="simple"/>
					</td>
					<th width="15%">审核时间</th>
					<td width="35%"><input name="instrumentsInfo.checkTime" value="<fmt:formatDate type='date' value='${instrumentsInfo.time}' pattern='yyyy-MM-dd'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*1-127"  nullmsg='审核时间不能为空！' sucmsg='审核时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">审核意见</th>
					<td width="85%" colspan="3"><textarea name="instrumentsInfo.remark" style="width:96%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)"></textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >确认<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_instrumentsInfo');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
