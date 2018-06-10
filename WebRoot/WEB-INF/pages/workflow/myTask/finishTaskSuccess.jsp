<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html>
	<head>
		<title>任务完成</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
        <link rel="stylesheet" type="text/css"
              href='<c:url value="/webResources/themes/${curr_user.cssId}/css/style.css" />' />
        <script src='<c:url value="/webResources/js/common.js"/>' ></script>
        <script src='<c:url value="/dwr/engine.js" />'></script>
        <script src='<c:url value="/dwr/util.js" />'></script>
        <script type='text/javascript'
	            src='<c:url value="/dwr/interface/sysSettingManager.js"/>'> </script>	</head>
	<body>
		<c:set var="curr_path" value="操作成功"></c:set>
		<%@include file="/WEB-INF/template/content_title.jsp"%>
		<table width="100%">
			<tr>
			<td>
			    <div class="submitdata">
				<table border="0" width="100%">
					<tr>
						<td align="center">
							<img src='<c:url value="/webResources/images/icons/use.gif"/>'
								style="border: 0" height="30" width="30">
							<span style="font-size: 20; ">任务处理成功。流程已提交： </span>
							<span style="font-size: 20;color: red; "> <c:out value="${myTaskObject.actorsName}" /> </span>
							<span style="font-size: 20; ">处理,节点名称为:</span>
							<span style="font-size: 20;color: red; ">
							<c:out value="${myTaskObject.nodeName}" /></span>
							<span style="font-size: 20; ">,任务名称为:</span>
							<span style="font-size: 20;color: red; "> <c:out value="${myTaskObject.taskName}" /> </span>
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="button" value="确定" class="input_button"
								onclick='javascript:window.location="<c:url value="/workflow/myTask/listMyTask.action?"/>"' />
						</td>
					</tr>
				</table>
				</div>
				</td>
			</tr>
		</table>
	</body>
</html>
