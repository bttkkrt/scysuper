<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html>
	<head>
		<title>用户管理</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript" src="${ctx}/webResources/js/common.js"></script>
		<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src='<c:url value="/webResources/js/easyui/jquery.easyui.min.js"/>'></script>
		<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/js/easyui/themes/${curr_user.cssId}/easyui.css"/>'> 
		<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/js/easyui/themes/icon.css"/>'>
		<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>  
		<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/themes/${curr_user.cssId}/css/style.css" />' />
		<script type="text/javascript">
			function saveBizProc() {
			   var form2=document.getElementById("form2");
			 /*	if (isEmpty($("input[name='businessProcess.businessId']").val())) {
					alert("业务名称不能为空");
					return;
				}*/	
				 var businessInfo=document.getElementById("businessInfo");
			   
			  	form2.action='saveBizProc.action';
			  	form2.submit();
			}
			
			function doReturn() {
				window.history.go(-1);
			}
		</script>
	</head>
	<body>
		<c:set var="curr_path" value="编辑业务流程"></c:set>
		<%@include file="/WEB-INF/template/content_title.jsp"%>


		<form action="" method="post" id="form2">
		 <s:token />
			<input type="hidden" name="businessProcess.id"
				value="${businessProcess.id}" />
			<input type="hidden" name="businessProcess.registerTime"
				value='<fmt:formatDate value="${businessProcess.registerTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' />
			<input type="hidden" name="businessProcess.processDefinitionId"
				value="${businessProcess.processDefinitionId}" />
			<input type="hidden" name="businessProcess.processInstanceId"
				value="${businessProcess.processInstanceId}" />
			<input type="hidden" name="businessProcess.processStatus"
				value="${businessProcess.processStatus}" />
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr>
					<td>
					    <div class="submitdata">
						<table cellspacing="0" cellpadding="0" width="100%" border="0">
							<tr>
								<td border="1">
								   业务名称（String）
								</td>
								<td>
									<input type="text" name="businessProcess.businessInfo"
										value="${businessProcess.businessInfo }" maxlength="650"/>
								</td>
								<td border="1">
								        备注信息（String）
								</td>
								<td>
									<input type="text" name="businessProcess.remark"
										value="${businessProcess.remark}" />
								</td>
							</tr>
						</table>
						</div>
					</td>

				<tr>
					<td style="text-align: center;" valign="middle" colspan="4">
						<a href="###" class="easyui-linkbutton" onclick="saveBizProc()" iconCls="icon-save">保存</a>
						<a href="###" class="easyui-linkbutton" onclick="doReturn()" iconCls="icon-back">返回</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
