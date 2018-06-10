<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>查看</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
			function createSaveCheckDoc(id)
			{
				myform.action = "${ctx}/jsp/checkBasic/createSaveCheckDoc.action?checkBasic.id="+id;
				myform.submit();
			}
			function createFieldCheckDoc(id)
			{
				myform.action = "${ctx}/jsp/checkBasic/createFieldCheckDoc.action?checkBasic.id="+id;
				myform.submit();
			}
			function createRectifyCheckDoc(id)
			{
				myform.action = "${ctx}/jsp/checkBasic/createRectifyCheckDoc.action?checkBasic.id="+id;
				myform.submit();
			}
			function createTDRectifyCheckDoc(id)
			{
				myform.action = "${ctx}/jsp/checkBasic/createTDRectifyCheckDoc.action?checkBasic.id="+id;
				myform.submit();
			}
			function createRectifyOpinionDoc(id)
			{
				myform.action = "${ctx}/jsp/checkBasic/createRectifyOpinionDoc.action?checkBasic.id="+id;
				myform.submit();
			}
		</script>
	</head>
	<body>
		<%@include file="/WEB-INF/template/content_title.jsp"%>
		<form name="myform" method="post" enctype="multipart/form-data"
			id="myform1">
			<div class="easyui-tabs" style="overflow-x: hidden; width: 1200px;">
				<div title="安全生产执法检查表" style="overflow-x: hidden;" class="tab">
					<div class="submitdata"
						style="overflow-x: hidden; width: 99%; height: 99%;">
						<table style="width: 98%">
							<tr>
								<th width="15%">
									检查人
								</th>
								<td width="35%">
									${checkBasic.checker.displayName}
								</td>
								<th width="15%">
									检查时间
								</th>
								<td width="35%">
									<fmt:formatDate type='both' value='${checkBasic.checkTime}'
										pattern="yyyy-MM-dd HH:MM:ss" />
								</td>
							</tr>
							<tr>
								<th width="15%">
									企业名称
								</th>
								<td colspan="3">
									${checkBasic.company.companyname}
								</td>
							</tr>
							<tr>
								<th width="15%">
									地 址
								</th>
								<td colspan="3">
									${checkBasic.company.dwdz}
								</td>
							</tr>
							<tr>
								<th width="15%">
									主要负责人
								</th>
								<td width="35%">
									${checkBasic.company.fddbr}
								</td>
								<th width="15%">
									联系方式
								</th>
								<td width="35%">
									${checkBasic.company.fddbrlxhm}
								</td>
							</tr>
							<tr>
								<td colspan="4" style="text-align: center;" width="100%">
									<table>
										<tr>
											<td width="10%" style="text-align: center;">
												序号
											</td>
											<td width="50%" style="text-align: center;">
												检查内容
											</td>
											<td width="10%" style="text-align: center;">
												检查结果
											</td>
											<td width="30%" style="text-align: center;">
												备注
											</td>
										</tr>
										<s:iterator value="crList" status="st" id="category">
											<tr>
												<td style="text-align: center;">
													<s:property value="#category.indexNum" />
												</td>
												<td style="text-align: center;" colspan="3">
													<s:property value="#category.categoryContent" />
												</td>
											</tr>
											<s:iterator value="#category.contentList" status="sc"
												id="content">
												<tr>
													<td style="text-align: center;">
														<s:property value="#sc.index+1" />
													</td>
													<td style="text-align: center;">
														<s:property value="#content.content" />
													</td>
													<td style="text-align: center;">
														<s:if test="#content.checkResult == 0">
															良好
														</s:if>
														<s:elseif test="#content.checkResult == 1">
															一般
														</s:elseif>
														<s:else>
															较差
														</s:else>
													</td>
													<td style="text-align: center;">
														<s:property value="#content.remark" />
													</td>
												</tr>
											</s:iterator>
										</s:iterator>
									</table>
								</td>
							</tr>
						<%-- 	<tr>
								<td colspan="4" height="100px" style="text-align: center;">
									<a href="#" class="easyui-linkbutton" iconCls="icon-add"
										onclick="createSaveCheckDoc('${checkBasic.id}');">生成安全生产执法检查表</a>
									<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
										onclick="window.close();">关闭</a>
								</td>
							</tr> --%>
						</table>
					</div>
				</div>
				<div title="隐患排查任务" closable="false" style="overflow-x: hidden;"
					class="tab">
					<div class="submitdata"
						style="overflow-x: hidden; width: 99%; height: 99%;">
						<table style="width: 98%">
							<tr>
								<th width="15%">
										检查场所
								</th>
								<td  width="35%">
									${checkBasic.checkPlace}
								</td>
								<th width="15%">
									检查时间
								</th>
								<td  width="35%">
									<fmt:formatDate type='both' value='${checkBasic.checkTime}'
										pattern="yyyy-MM-dd" />
								</td>
							</tr>
							<tr>
								<th width="15%">
									监管部门主管领导
								</th>
								<td width="35%"  >
									${checkBasic.jgld}
								</td>
								<th width="15%">
									参检专家
								</th>
								<td width="35%"  >
									${checkBasic.cjzj}
								</td>
							</tr>
							<tr>
								<th width="15%">
									公司领导
								</th>
								<td width="35%" colspan="3" >
									${checkBasic.gsld}
								</td>
								 
							</tr>
							<tr>
								<th width="15%">
									管理方面
								</th>
								<td colspan="3"><div style="width:100%;height:120px" name="checkBasic.glfm"  readonly="readonly" msg="此项为必填">${checkBasic.glfm}</div>
								</td>
							</tr>
							<tr>
								<th width="15%">
									行为方面
								</th>
								<td colspan="3"><div style="width:100%;height:120px" name="checkBasic.xwfm" readonly="readonly" msg="此项为必填">${checkBasic.xwfm}</div>
								</td>
							</tr>
							<tr>
								<th width="15%">
									作业环境及设备设施
								</th>
								<td colspan="3"><div style="width:100%;height:120px" name="checkBasic.zyhjsb" readonly="readonly" msg="此项为必填">${checkBasic.zyhjsb}</div>
								</td>
							</tr>
						<%-- 	 
							<tr>
								<th>
									执法机构
								</th>
								<td>
									${ checkBasic.checkOrganization}
								</td>
							</tr>
							<tr>
								<th width="15%">
									执法人员
								</th>
								<td width="35%">
									${lawEnforceName}
								</td>
								<th width="15%">
									证件号码
								</th>
								<td width="35%">
									${idNum}
								</td>
							</tr> --%>
							<%-- <tr>
								<th>
									检查情况
								</th>
								<td colspan="3">
									<table width="100%">
										<s:iterator id="situation" value="situationList">
											<tr>
												<td width="70%">
													<s:iterator id="attach" value="#situation.pathList">
														<c:choose>
															<c:when
																test="${fn:endsWith(fn:toLowerCase(attach),'.jpg')
														    	||fn:endsWith(fn:toLowerCase(attach),'.bmp')
														    	||fn:endsWith(fn:toLowerCase(attach),'.png')
														    	||fn:endsWith(fn:toLowerCase(attach),'.jpeg')
														    	||fn:endsWith(fn:toLowerCase(attach),'.gif')}"> 
															  	  &nbsp;&nbsp;&nbsp;
															    	 <a href="/upload/photo/${attach}"
																	rel="example_group"> <img
																		src="/upload/photo/${attach}" border='0' width='220'
																		height='150' /> </a>
															</c:when>
															<c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;<FONT style="color: red;">&nbsp;&nbsp;&nbsp;非图片</FONT>
															</c:otherwise>
														</c:choose>
													</s:iterator>
												</td>
												<td width="30%">
													${situation.discreption}
												</td>
											</tr>
										</s:iterator>
									</table>
								</td>
							</tr> --%>
							<%-- <tr>
								<td colspan="4" height="100px" style="text-align: center;">
									<a href="#" class="easyui-linkbutton" iconCls="icon-add"
										onclick="createFieldCheckDoc('${checkBasic.id}');">生成隐患排查任务记录表</a>
									<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
										onclick="window.close();">关闭</a>
								</td>
							</tr> --%>
						</table>
					</div>
				</div>
				<div title="隐患清单" closable="false" style="overflow-x: hidden;"
					class="tab">
					<div class="submitdata"
						style="overflow-x: hidden; width: 99%; height: 99%;">
						隐患信息如下：
						<table style="width: 98%">
							<tr  style="padding: 0px;margin: 0px;" id="hylist"> 
					<th colspan="4">
					<table>
					<tbody>
								<s:iterator id="yhqd" value="checkBasic.yhqdList" status="st"  >
						<tr   class="yhinfo"  >
						<td   colspan="4"  style="padding: 0px;margin: 0px; ">
							<table>
								<tr> 
									<td  colspan="4" style="text-align:center">隐患 <s:property value="#st.count" />
									</td>
								</tr>
								<tr> 
									<th width="15%">隐患内容
									</th>
									<td colspan="3">  ${yhContent} 
									</td>
								</tr>
								<tr> 
									<th width="15%">监管部门跟踪责任人
									</th>
									<td width="35%">  ${jgzrrNames} 
									</td>
									<th width="15%">整改期限
									</th>
									<td > <fmt:formatDate type='both' value='${zgqx}' pattern="yyyy-MM-dd" /> 
									</td>
								</tr>
								<tr> 
									<th width="15%">企业责任人
									</th>
									<td width="35%"> ${qyzrr} 
									</td>
									<th width="15%">企业责任人联系电话
									</th>
									<td> ${qyzrrlxdh} 
									</td>
								</tr>
								<tr> 
									<th width="15%">所需资金
									</th>
									<td width="35%" colspan="3"> ${sxzj} 
									</td>
								</tr>
								<tr> 
									<th width="15%">
										已传附件
									</th>
									<td colspan="3"> 
										 <table>
										 <!-- 迭代附件 -->
										 <s:iterator id="photoPic" value="#yhqd.picList" status="cst">
											<tr>
												<td width="40%">
														<c:choose>
															<c:when
																test="${fn:endsWith(fn:toLowerCase(picName),'.jpg')
														    	||fn:endsWith(fn:toLowerCase(picName),'.bmp')
														    	||fn:endsWith(fn:toLowerCase(picName),'.png')
														    	||fn:endsWith(fn:toLowerCase(picName),'.jpeg')
														    	||fn:endsWith(fn:toLowerCase(picName),'.gif')}"> 
															  	  &nbsp;&nbsp;&nbsp;
															    	 <a href="/upload/photo/${picName}"
																	rel="example_group"> <img
																		src="/upload/photo/${picName}" border='0' width='220'
																		height='150' /> </a>
															</c:when>
															<c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;<FONT style="color: red;">&nbsp;&nbsp;&nbsp;${fileName}</FONT>
															</c:otherwise>
														</c:choose>
												</td>
												<td width="30%">
													 ${taskRemark} 
												</td>
											</tr>
										</s:iterator>
										 </table>
									</td>
								</tr>
								<tr> 
									<th width="15%">整改方案
									</th>
									<td colspan="3"><div style="width:100%;height:120px"  readonly="readonly">${zgfa} </div>
									</td>
								</tr>
								<tr> 
									<th width="15%">防范措施
									</th>
									<td colspan="3"><div style="width:100%;height:120px" readonly="readonly">${ffcs}</div>
									</td>
								</tr>
							</table>
						</td>
							</tr>
							</s:iterator>
							</tbody>
							</table>
							</th>
							</tr>
							<%-- <tr>
								<th>
									检查情况
								</th>
								<td colspan="3">
									<table width="100%">
										<s:iterator id="situation" value="situationList">
											<tr>
												<td width="70%">
													<s:iterator id="attach" value="#situation.pathList">
														<c:choose>
															<c:when
																test="${fn:endsWith(fn:toLowerCase(attach),'.jpg')
														    	||fn:endsWith(fn:toLowerCase(attach),'.bmp')
														    	||fn:endsWith(fn:toLowerCase(attach),'.png')
														    	||fn:endsWith(fn:toLowerCase(attach),'.jpeg')
														    	||fn:endsWith(fn:toLowerCase(attach),'.gif')}"> 
															  	  &nbsp;&nbsp;&nbsp;
															    	 <a href="/upload/photo/${attach}"
																	rel="example_group"> <img
																		src="/upload/photo/${attach}" border='0' width='220'
																		height='150' /> </a>
															</c:when>
															<c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;<FONT style="color: red;">&nbsp;&nbsp;&nbsp;非图片</FONT>
															</c:otherwise>
														</c:choose>
													</s:iterator>
												</td>
												<td width="30%">
													${situation.discreption}
												</td>
											</tr>
										</s:iterator>
									</table>
								</td>
							</tr> --%>
							<%-- <tr>
								<td colspan="4" height="100px" style="text-align: center;">
									<a href="#" class="easyui-linkbutton" iconCls="icon-add"
										onclick="createFieldCheckDoc('${checkBasic.id}');">生成隐患清单记录表</a>
									<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
										onclick="window.close();">关闭</a>
								</td>
							</tr> --%>
						</table>
					</div>
				</div>
				<div title="隐患整改清单" closable="false" style="overflow-x: hidden;"
					class="tab">
					<div class="submitdata"
						style="overflow-x: hidden; width: 99%; height: 99%;">
						&nbsp;&nbsp;${checkBasic.company.companyname}:
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp; 经查，你单位存在下列问题：
						<br>
						<br>
						<table width="100%" >
							<tr  style="padding: 0px;margin: 0px;" > 
							<th>序号</th>
							<th>隐患及问题</th>
							<th>企业整改责任人</th>
							<th>企业整改责任人联系电话</th>
							<th>整改期限</th>
							<th>监管部门跟踪责任人</th>
							<th>当前状态</th>
							</tr>
						<s:iterator id="yhqd" value="checkBasic.yhqdList" status="st"  >
						<tr>
							<td><s:property value="#st.count" /></td>
							<td>${yhContent} </td>
							<td>${qyzrr}  </td>
							<td>${qyzrrlxdh} </td>
							<td><fmt:formatDate type='both' value='${zgqx}' pattern="yyyy-MM-dd" /> </td>
							<td>${jgzrrNames}</td>
							<td><s:if test="ended == -1">
								已取消处理
								</s:if>
								<s:elseif  test="dealFlag == 0">
								待处理
								</s:elseif>
								<s:elseif test="dealFlag == 1">
									<s:if test="passFlag == 0">驳回待处理</s:if>
									<s:elseif test="passFlag == 1">整改通过验收</s:elseif>
									<s:else>已处理待验收</s:else>
								</s:elseif>
							</td>
						</tr>
									</s:iterator>
<%-- 							<s:iterator id="rectify" value="rectifyList">
								<tr>
									<td width="70%">
										<s:iterator id="attach" value="#rectify.pathList">
											<c:choose>
												<c:when
													test="${fn:endsWith(fn:toLowerCase(attach),'.jpg')
													    	||fn:endsWith(fn:toLowerCase(attach),'.bmp')
													    	||fn:endsWith(fn:toLowerCase(attach),'.png')
													    	||fn:endsWith(fn:toLowerCase(attach),'.jpeg')
													    	||fn:endsWith(fn:toLowerCase(attach),'.gif')}"> 
														  	  &nbsp;&nbsp;&nbsp;
														    	 <a href="/upload/photo/${attach}"
														rel="example_group"> <img
															src="/upload/photo/${attach}" border='0' width='220'
															height='150' /> </a>
												</c:when>
												<c:otherwise> 
												     			&nbsp;&nbsp;&nbsp;<FONT style="color: red;">&nbsp;&nbsp;&nbsp;非图片</FONT>
												</c:otherwise>
											</c:choose>
										</s:iterator>
									</td>
									<td width="30%">
										${rectify.discreption}
									</td>
								</tr>
							</s:iterator> --%>
						</table>
						<br>
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;现责令你单位对上述问题于整改期限前整改完毕， 达到有关法律法规规章和标准规定的要求。逾期不整改或达不到要求的，依法给予行政处罚；由此造成事故的，
						依法追究有关人员的责任。如果不服本指令，可以依法在60日内向苏州市太仓市人民政
						府或者苏州市安全生产监督管理局申请行政复议，或者在三个月内依法向苏州市太仓市人民法院提起行政诉讼，但本指
						令不停止执行，法律另有规定的除外。
						<%-- <table>
							<tr>
								<td height="100px" style="text-align: center;">
									<a href="#" class="easyui-linkbutton" iconCls="icon-add"
										onclick="createRectifyCheckDoc('${checkBasic.id}');">生成责令限期整改指令书</a>
									<a href="#" class="easyui-linkbutton" iconCls="icon-add"
										onclick="createTDRectifyCheckDoc('${checkBasic.id}');">生成套打责令限期整改指令书</a>
									<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
										onclick="window.close();">关闭</a>
								</td>
							</tr>
						</table> --%>
					</div>
				</div>
				<div title="隐患验收清单" closable="false" style="overflow-x: hidden;"
					class="tab">
					<div class="submitdata"
						style="overflow-x: hidden; width: 99%; height: 99%;">
						<table style="width: 98%;">
							&nbsp;&nbsp;${checkBasic.company.companyname}:
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp; 本机关于
							<fmt:formatDate type='both'
								value='${checkBasic.rectifyBeginTime}' pattern="yyyy-MM-dd" />
							作出了${checkBasic.reviewContent}的决定【太仓安监管责改[${checkBasic.rectifyYear}]${checkBasic.rectifyNum}号】，经对你单位整改情况进
							行复查，提出如下意见:
						</table>
						
						
						<table width="100%" >
							<tr  style="padding: 0px;margin: 0px;" > 
							<th>序号</th>
							<th>隐患及问题</th>
							<th>企业整改责任人</th>
							<th>企业整改责任人联系电话</th>
							<th>整改期限</th>
							<th>监管部门跟踪责任人</th>
							<th>当前状态</th>
						 
							<th>历史流程</th>
							 
							</tr>
						<s:iterator id="yhqd" value="checkBasic.yhqdList" status="st"  >
						<tr>
							<td><s:property value="#st.count" /></td>
							<td>${yhContent} </td>
							<td>${qyzrr}  </td>
							<td>${qyzrrlxdh} </td>
							<td><fmt:formatDate type='both' value='${zgqx}' pattern="yyyy-MM-dd" /> </td>
							<td>${jgzrrNames}</td>
							<td><s:if test="ended == -1">
								已取消处理
								</s:if>
								<s:elseif  test="dealFlag == 0">
								待处理
								</s:elseif>
								<s:elseif test="dealFlag == 1">
									<s:if test="passFlag == 0">驳回待处理</s:if>
									<s:elseif test="passFlag == 1">整改通过验收</s:elseif>
									<s:else>已处理待验收</s:else>
								</s:elseif>
							</td>
							 
							<td style="padding:0px">
							<c:if test="${empty zgysr}">无</c:if>
							<c:if test="${not empty zgysr}"> 
							<s:generator val="zgysr" separator="‖" id="zgysrList"/> 
							<c:set value="${ fn:split(yssj, '‖') }" var="yssjList" />
							<c:set value="${ fn:split(resultContent, '‖') }" var="resultContentList" />
							<table>
								<tr>
									<th style="text-align: left;"> 验收人</th>
									<th style="text-align: left;"> 验收时间</th>
									<th style="text-align: left;"> 结论</th>
								</tr>
								<s:iterator var="tzgysr"  value="zgysrList" status="st"   >
								<tr>
									<td> ${tzgysr}</td>
									<td> ${yssjList[st.index]}</td>
									<td> ${resultContentList[st.index]}</td>
								</tr>
								</s:iterator>
							</table>
							</c:if>
							</td>
						</tr>
						</s:iterator>
						</table>
						
					<%-- 	<table>
							<tr>
								<td width="15%" style="text-align: center;">
									整改意见:
								</td>
								<td width="85%">
									<table width="100%">
										<s:iterator id="opList" value="opinionList">
											<tr>
												<td width="70%">
													<s:iterator id="attach" value="#opList.pathList">
														<c:choose>
															<c:when
																test="${fn:endsWith(fn:toLowerCase(attach),'.jpg')
														    	||fn:endsWith(fn:toLowerCase(attach),'.bmp')
														    	||fn:endsWith(fn:toLowerCase(attach),'.png')
														    	||fn:endsWith(fn:toLowerCase(attach),'.jpeg')
														    	||fn:endsWith(fn:toLowerCase(attach),'.gif')}"> 
															  	  &nbsp;&nbsp;&nbsp;
															    	 <a href="/upload/photo/${attach}"
																	rel="example_group"> <img
																		src="/upload/photo/${attach}" border='0' width='220'
																		height='150' /> </a>
															</c:when>
															<c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;<FONT style="color: red;">&nbsp;&nbsp;&nbsp;非图片</FONT>
															</c:otherwise>
														</c:choose>
													</s:iterator>
												</td>
												<td width="30%">
													${opList.opinion}
												</td>
											</tr>
										</s:iterator>
									</table>
								</td>
							</tr>
						</table> --%>
				<%-- 		<table>
							<tr>
								<td height="100px" style="text-align: center;">
									<a href="#" class="easyui-linkbutton" iconCls="icon-add"
										onclick="createRectifyOpinionDoc('${checkBasic.id}');">生成word</a>
									<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
										onclick="close();">关闭</a> 
								</td>
							</tr>
						</table> --%>
					</div>
				</div>
			</div>
		</form>
		<%@include file="/WEB-INF/template/pagefoot.jsp"%>
	</body>
</html>
