<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
			if(windowId)
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
						<c:if test="${not empty message}">${message}</c:if>
						<c:if test="${empty message}">操作成功！</c:if>
					</div>
					<div class="btn_area_setc">
						<a href="#" class="btn_01" onclick="reloadData()"><b></b>返回</a>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
