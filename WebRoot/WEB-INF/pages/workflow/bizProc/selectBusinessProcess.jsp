<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@ taglib prefix="ww" uri="/struts-tags"%>
<html>
	<head>
		<title>用户管理</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/themes/${curr_user.cssId}/css/style.css" />' />
        <script type='text/javascript' src='<c:url value="/webResources/js/displayTag.js"/>'> </script>
		<script src='<c:url value="/webResources/js/common.js"/>'></script>
		<script src='<c:url value="/dwr/engine.js" />'></script>
		<script src='<c:url value="/dwr/util.js" />'></script>
		<style>
.w1 {
	width: 25%;
}
}
</style>

		<script>
function selectComplete(flowtype)
{
	alert("选择完成！");
	opener.getSelectFlowType(flowtype);
	window.close();
}

</script>

	</head>

	<body>
		<c:set var="curr_path" value="启动流程"></c:set>
		<%@include file="/WEB-INF/template/content_title.jsp"%>
		<div>
			<div>
				<form action="/bizProc/selectWorkflow.action" method="post"  id="form1">
					<input type="hidden" name="bizProcId" value="${bizProcId}">
					<input type="hidden" name="id" value="${id}" />

					<table cellspacing="0" cellpadding="0" width="100%" border="0">
						<tr>
							<td>
							  <div class="submitdata">
								<table cellspacing="0" cellpadding="0" width="100%" border="0">
									<tr>
									    <td border="1">
								           流程定义ID
								        </td>
										
										<td>
											<input type="text" name="processDefinitionId"
												value="${processDefinitionId}" />
										</td>
										<td border="1">
								           流程名称
								        </td>
										<td>
											<input type="text" name="processName" value="${processName}" />
										</td>
										<td border="1">
								           创建人姓名
								        </td>
										<td>
											<input type="text" name="createUserName"
												value="${createUserName}" />
										</td>
									</tr>
								</table>
							</div>
							</td>

						<tr>
							<td colspan="4" valign="top">
								<div align="center">
									<BR>
									<input type="button" value="查询" icon="icon-search"
										onclick="doQuery()" class="input_button">
									<input type="button" value="返回" icon="input_button"
										onclick="doReturn()" class="input_button">
								</div>
							</td>
					</table>
					<br>
					<table cellspacing="0" cellpadding="0" width="100%" border="0">
						<tr>
							<td class="content0201"></td>
							<td>
							  <div class="outputdata">
								<table width="99%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<display:table name="pagination" cellspacing="0"
												cellpadding="0" requestURI="/bizProc/selectWorkflow.action"
												defaultsort="1" id="row" pagesize="10"
												export="false" htmlId="checkTable" style="width:100%">

												<display:column title="流程定义ID" style="text-align:center"
													headerClass="w1">
													<c:out value="${row.processDefinitionId}" />
												</display:column>
												<display:column title="流程名称" style="text-align:center"
													headerClass="w1">
													<c:out value="${row.processName}" />
												</display:column>
												<display:column title="创建人姓名" style="text-align:center"
													headerClass="w1">
													<c:out value="${row.createUserName}" />
												</display:column>
												<display:column title="操作" style="text-align:center"
													headerClass="w1">
													<ww:if test="selectType==-1">
														<a
															href='<c:url value="/bizProc/startWorkFlow.action"/>?bizProcId=${bizProcId }&id=${row.id }'>
															<img
																src='<c:url value="/webResources/images/icons/retry.png"/>'>启动工作流
														</a>
														<!-- <a
															href='<c:url value="/bizProc/startWorkFlowInterface.action"/>?businessId=${businessId }&id=${row.id }'>
															<img
																src='<c:url value="/webResources/images/icons/retry.png"/>'>启动工作流（接口）
														</a> -->
													</ww:if>
												</display:column>

											</display:table>
										</td>
									</tr>

								</table>
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<script type="text/javascript">
		
	function doQuery() {//查询流程定义列表
	    var form1=document.getElementById("form1");
	    form1.action="selectWorkflow.action"; 
	    form1.submit();
	}
	
	function doDeploy() {//发布新流程
		location.href='<c:url value="/workflow/initDeploy.action"/>';
	}
	function doReturn() {
		window.history.go(-1);
	}
</script>
	</body>
</html>
