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


	function doReturn() {
		window.history.go(-1);
	}

	</script>
	</head>
	<body>
		<c:set var="curr_path" value="已办任务详情"></c:set>
		<%@include file="/WEB-INF/template/content_title.jsp"%>

		<!-- 以下定义tab的content -->
		<div class="easyui-tabs" style="width: 1048%">
			<div title="处理信息" style="padding: 10px;">
				<div class="outputdata" style="width: 100%; height: 100%;">
					<table id="tabContent1" cellpadding="0" cellspacing="0"
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
			<div title="流程监控" style="padding: 10px;">
				<table id="tabContent2" cellpadding="0" cellspacing="0" width="100%"
					border="1">
					<tr>
						<td>
							<jbpm:processimageToken token="${tokenId}" />
						</td>
				</table>
			</div>
		</div>
	</body>

</html>
