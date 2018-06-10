<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Success</title>
		<%@include file="/common/jsLib.jsp"%>
		
	</head>
<%-- 	<c:set var="curr_path" value="操作结果"></c:set>
 --%>	<body>
		<%-- <div class="page_content">
			<div class="box_01">
				<div class="inner6px">
					<c:if test="${not empty message}">
						<div class="o_tips_error"></div>
						<div class="o_tips_say">
							${message}
						</div>
					</c:if>
					<c:if test="${empty message}">
						<div class="o_tips_ok"></div>
						<div class="o_tips_say">
							操作成功！
					</div>
					</c:if>
					<div class="btn_area_setc">
						<a href="#" class="btn_01" onclick="reloadData('<%=windowId%>')"><b></b>确认</a>
					</div>
				</div>
			</div>
		</div> --%>
	</body>
	<script>
		$(function(){
			//配置一个透明的询问框
			layer.msg('操作结果', {
			  time: 2000, //2s后自动关闭
			  btnAlign: 'c', //按钮居中
			  shade: 0.8,
			  btn: ['操作成功']
			}); 
			setTimeout(function(){
				closeLayer();
				//重新加载父页面
				window.parent.document.location.reload();
			},1500);
			// var index = parent.parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			//parent.parent.layer.close(index); //再执行关闭  
		})
	</script>
</html>
