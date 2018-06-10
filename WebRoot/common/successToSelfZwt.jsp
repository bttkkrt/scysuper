<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%
		String windowId = request.getParameter("windowId");
	%>
	<head>
		<title>Success</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
		$(function(){
			top1=($(top).height()-250)/2    
			left=($(top).width()-400)/2
			var windowId="<%=windowId%>";
			if(windowId=="null")
			windowId=parent.$("#"+window.name).parent().attr("id");
			parent.$('#'+windowId).window({
			    width:400,
			    height:250,
			    closed:false,
			    top:top1,
			    left:left
			});
		})
</script>
	</head>
	<c:set var="curr_path" value="操作结果"></c:set>
	<body>
		<div class="page_content">
			<div class="box_01">
				<div class="inner6px">
						<div class="o_tips_ok"></div>
						<div class="o_tips_say">
							操作成功！
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
