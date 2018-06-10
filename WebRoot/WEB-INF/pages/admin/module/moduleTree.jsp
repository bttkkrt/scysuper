<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%
			String func = request.getParameter("func");
		%>
		<%@include file="/common/jsLib.jsp"%>
		<script type="text/javascript" src="${ctx}/webResources/js/module.js"></script>
		
		<script>
			$(function(){
				$('#moduleTree').tree({   
					checkbox: false,   
					url: 'findChildModule.action', 
					onBeforeExpand:function(node,param){
						$('#moduleTree').tree('options').url = "findChildModule.action?selModule=" + node.id;
					},
					onClick:function(node){
						<%=func%>(node);//鼠标点击后的事件 
					}
				});   
			});
        </script>
	</head>
	<body>
		<div id="tree-div" style="overflow: auto; height: 100%; width: 100%; border: 0px solid;">
			<ul id="moduleTree">
			</ul>
		</div>
	</body>
</html>