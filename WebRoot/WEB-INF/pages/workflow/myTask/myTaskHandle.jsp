<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html>

	<head>
		<title>模块列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="description" content="模块的列表显示页面">
		<link rel="stylesheet" type="text/css"
			href='<c:url value="/webResources/themes/${curr_user.cssId}/css/style.css" />' />
			<script type="text/javascript"
			src="${ctx}/webResources/js/easyui/jquery-1.4.4.min.js"></script>
		<script type="text/javascript"
			src='<c:url value="/webResources/js/common.js"/>'></script>
		<script type="text/javascript"
			src='<c:url value="/webResources/js/easyui/jquery.easyui.min.js"/>'></script>
		<link rel="stylesheet" type="text/css"
			href='<c:url value="/webResources/js/easyui/themes/${curr_user.cssId}/easyui.css"/>'>
		<link rel="stylesheet" type="text/css"
			href='<c:url value="/webResources/js/easyui/themes/icon.css"/>'>
		<script type="text/javascript"
			src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript">
	        function reloadDate(){
	        	search_user();
	        }
	        
	        function close_win(){
	        alert("父页面");
	        	$("#newWindow").window("close");
	        }
	        
			function endProcess(transName,nodeType,nodeId) {
				document.getElementById("transName").value=transName;
				var form1=document.getElementById("form1");
		
				if("E"==nodeType){
					form1.action='<c:url value="/workflow/myTask/endProcessIns.action"/>';
					form1.submit();
				}else if("D"==nodeType){
				    form1.action='<c:url value="/workflow/myTask/finishDecisionTask.action"/>';
				 	form1.submit();
				}else if("C"==nodeType){
					form1.action='<c:url value="/workflow/myTask/finishMyTask.action"/>';
					selectSubProcParticipant(nodeId);
				}else{
					form1.action='<c:url value="/workflow/myTask/finishMyTask.action"/>';
					selectParticipant(nodeId);
				}
			}
			function doMyTask() {
				form1.action='<c:url value="/workflow/myTask/finishMyTask.action"/>';
				form1.submit();
			}
		
			function dispatchTask(nodeId) {
				form1.action='<c:url value="/workflow/myTask/dispatchTask.action"/>';
				selectDispatchUser(nodeId);
			}
		
			function previousTask() {
				form1.action='<c:url value="/workflow/myTask/previousTask.action"/>';
				form1.submit();
			}
		
			function doReturn() {
				window.history.go(-1);
			}
			
			var location = getCenterLocation(380,320);
			function selectParticipant(nodeId){
				openparentWindow("newWindow","选择人员",location.left,location.top,"450","350","${ctx}/workflow/myTask/findUserByNodeId.action?nodeId="+nodeId,true,true,true,false,true,"win");
			}
			
			function selectDispatchUser(nodeId){
				openparentWindow("newWindow","选择人员",location.left,location.top,"380","320","${ctx}/workflow/myTask/findDispatchUser.action?nodeId="+nodeId,true,true,true,false,true,"win");
			}
			
			function selectSubProcParticipant(nodeId){
				openparentWindow("newWindow","选择人员",location.left,location.top,"380","320","${ctx}/workflow/myTask/findSubProcUser.action?nodeId="+nodeId,true,true,true,false,true,"win");
			}
		</script>
	</head>

	<body>
		<c:set var="curr_path" value="待办任务"></c:set>
		<%@include file="/WEB-INF/template/content_title.jsp"%>

		<form name="hiddenFormForAttach" id="hiddenFormForAttach"
			style="display: none" method="post" targer="hiddenFrameForAttach"></form>
		<form name="usrFrm" id="usrFrm">
			<input type="hidden" name="nodeId" />
		</form>

		<div class="right_maincont">
			<!-- 以下定义tab的content -->

			<form action="xx.action" method="post" id="form1"
				enctype="multipart/form-data">
				<input type="hidden" name="myTaskObject.nodeId" class="btnbg"
					value="${myTaskObject.nodeId}" />
				<input type="hidden"
					name="myTaskObject.businessProcess.processDefinitionId"
					value="${myTaskObject.procDefId}" />
				<input type="hidden" name="myTaskObject.businessProcess.id"
					value="${myTaskObject.businessProcess.id}" />
				<input type="hidden" name="myTaskObject.taskInstanceId"
					value="${taskInsId}" />
				<input type="hidden" name="myTaskObject.transName" id="transName">
				<input type="hidden" name="taskids" id="taskids" />
				<input type="hidden" name="userids" id="userids" />
				<input type="hidden" name="checkUserName" id="checkUserName" />
				<input type="hidden" name="nextNodeId" id="nextNodeId" />
				<input type="hidden" name="procNodeId" id="procNodeId" />
				<div class="easyui-tabs" style="width: 1048%">
					<div title="任务处理" style="padding: 10px;">
						<table cellspacing="0" id="tabContent1" cellpadding="0"
							width="100%" border="1">
							<tr>
								<td>
									<div class="submitdata">
										<table cellspacing="0" cellpadding="0" width="100%" border="1">
											<tr>
												<th style="width: 17%">
													流程名称
												</th>
												<td style="width: 33%">
													${myTaskObject.processName}
												</td>
												<th style="width: 17%">
													任务名称
												</th>
												<td style="width: 33%">
													${myTaskObject.taskName}
												</td>
												<c:if test="${numOfTask gt 1}">
													<tr>
														<th>
															该节点共有任务个数
														</th>
														<td>
															${numOfTask}
														</td>

														<th>
															已完成任务数
														</th>
														<td>
															${numOfDoneTask}
														</td>
													</tr>
												</c:if>
											<tr>
												<th>
													业务摘要
												</th>
												<td>
													${bizProc.businessInfo}
												</td>
												<th>
													流程创建时间
												</th>
												<td>
													${myTaskObject.businessProcess.registerTime}
												</td>
											</tr>
											<c:if
												test="${not empty myTaskObject.businessProcess.baseInfoUrl}">
												<tr>
													<iframe style="width: 100%" scrolling="no"
														src='<c:url value="${myTaskObject.businessProcess.baseInfoUrl}"/>'>
													</iframe>
												</tr>
											</c:if>
											<tr>
												<th>
													节点名称
												</th>
												<td>
													${myTaskObject.nodeName}
												</td>
												<th>
													任务类型
												</th>
												<td>
													<c:if test="${numOfTask gt 1}">
														<c:out value="会签任务"></c:out>
													</c:if>
													<c:if test="${numOfTask eq 1}">
														<c:out value="一般任务"></c:out>
													</c:if>
												</td>
											</tr>
											<tr>
												<th>
													备注信息
												</th>
												<td>
													${bizProc.remark}
												</td>
											</tr>
											<tr>
												<th>
													处理意见
												</th>
												<td colspan="3">
													<textarea style="width: 100%" name="handleInfo" rows="5">${myTaskObject.handleInfo}</textarea>
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<div align="center">
														<c:if test="${numOfTask gt 1}">
															<c:if test="${flagOfLastTask eq 'N'}">
																<a href="###" class="easyui-linkbutton"
																	iconCls="icon-ok" title="${wfButton.transName }"
																	onclick="doMyTask()">处理当前任务</a>
															</c:if>
															<c:if test="${flagOfLastTask eq 'Y'}">
																<c:forEach items="${wfButtonList}" var="wfButton"
																	varStatus="status">
																	<a href="###" class="easyui-linkbutton"
																		iconCls="icon-ok" title="${wfButton.transName }"
																		onclick="endProcess('${wfButton.transName}','${wfButton.nodeType}','${wfButton.nodeId }')">${wfButton.nodeName
																		}</a>
																</c:forEach>
															</c:if>
														</c:if>
														<c:if test="${numOfTask eq 1}">
															<c:forEach items="${wfButtonList}" var="wfButton"
																varStatus="status">
																<a href="###" class="easyui-linkbutton"
																	iconCls="icon-ok" title="${wfButton.transName }"
																	onclick="endProcess('${wfButton.transName}','${wfButton.nodeType}','${wfButton.nodeId }')">${wfButton.nodeName
																	}</a>
															</c:forEach>
														</c:if>

														<c:if test="${typeOfSignal eq 1}">
															<a href="###" class="easyui-linkbutton"
																iconCls="icon-ok" title="${wfButton.transName }"
																onclick="dispatchTask(${myTaskObject.nodeId })">传阅</a>
														</c:if>
														<a href="###" class="easyui-linkbutton"
															iconCls="icon-back" title="${wfButton.transName }"
															onclick="previousTask()">任务回退</a>
													</div>
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<div title="审批记录" style="padding: 10px;">
						<div class="outputdata" style="width: 100%; height: 100%;">
							<table id="tabContent2" cellpadding="0" cellspacing="0"
								width="100%" border="1">
								<tr>
									<th style="width: 10%">
										流程名称
									</th>
									<th style="width: 10%">
										节点名称
									</th>
									<th style="width: 10%">
										任务名称
									</th>
									<th style="width: 10%">
										处理人
									</th>
									<th style="width: 10%">
										处理内容
									</th>
									<th style="width: 10%">
										处理时间
									</th>
								</tr>
								<tr>
									<c:forEach items="${approvList}" var="approvNode"
										varStatus="status">
										<c:if test="${status.index %2==1}">
											<tr height="22px" class="DataGridAlternatingItemStyle">
										</c:if>
										<c:if test="${status.index%2==0}">
											<tr height="22px" class="DataGridRowStyle">
										</c:if>
										<td align="center">
											${approvNode[0]}
										</td>
										<td align="center">
											${approvNode[1]}
										</td>
										<td align="center">
											${approvNode[2]}
										</td>
										<td align="center">
											${approvNode[3]}
										</td>
										<td align="center">
											${approvNode[4]}
										</td>
										<td align="center">
											${approvNode[5]}
										</td>
									</c:forEach>
								</tr>

							</table>
						</div>
					</div>
					<div title="流程监控">
						<table id="tabContent3" cellpadding="0" cellspacing="0"
							width="100%">
							<tr>
								<td>
									<jbpm:processimageToken token="${tokenId}" />
								</td>
						</table>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
