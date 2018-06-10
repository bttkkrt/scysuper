<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html>
	<head>
		<title>用户管理</title>
		<link rel="stylesheet" type="text/css"
			href='<c:url value="/webResources/themes/${curr_user.cssId}/css/style.css" />' />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript"
			src="${ctx}/webResources/js/easyui/jquery-1.4.4.min.js"></script>
		<script type="text/javascript"
			src='<c:url value="/webResources/js/easyui/jquery.easyui.min.js"/>'></script>
		<link rel="stylesheet" type="text/css"
			href='<c:url value="/webResources/js/easyui/themes/${curr_user.cssId}/easyui.css"/>'>
		<link rel="stylesheet" type="text/css"
			href='<c:url value="/webResources/js/easyui/themes/icon.css"/>'>
		<script type="text/javascript"
			src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script src='<c:url value="/webResources/js/common.js"/>'></script>
		<script type="text/javascript">
	 
	function saveBizProc() {
	   var form1=document.getElementById("form2");
	    var businessInfo=document.getElementById("businessInfo12");
	    
		if (businessInfo.value==null) {
				$.messager.alert("警告","业务名称不能为空！",'warning');
			return;
		}
	    	
	  	form1.action='saveBizProc.action'
	  	form1.submit();
	}
	
	function doReturn() {
		window.history.go(-1);
	}
	
	
	
	</script>

	</head>

	<body>
		<c:set var="curr_path" value="业务流程管理"></c:set>
		<%@include file="/WEB-INF/template/content_title.jsp"%>
		<div>
			<form action="" method="post" id="form2">
				 <s:token />
				<table cellspacing="0" cellpadding="0" width="100%" border="0">
					<tr>
						<td>
							<div class="submitdata">
								<table cellspacing="0" cellpadding="0" width="100%" border="0">
									<tr>
										<td border="1">
											业务名称
										</td>
										<td>
											<input type="text" name="businessProcess.businessInfo"
												value="${businessProcess.businessInfo}" id="businessInfo12"
												maxlength="650" />
											<font color="red">*</font>
										</td>
										<td border="1">
											备注信息
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
						<td colspan="4" valign="top">
							<div align="center">
								<BR>
								<a href="###" class="easyui-linkbutton" onclick="saveBizProc()"
									iconCls="icon-save">保存</a>&nbsp;
							</div>
						</td>
					</tr>

				</table>

			</form>

		</div>

	</body>



</html>
