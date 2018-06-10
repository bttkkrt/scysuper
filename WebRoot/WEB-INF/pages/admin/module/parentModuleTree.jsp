<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%
			String func = request.getParameter("func");
		%>

		<script type="text/javascript" src="${ctx}/webResources/js/module.js"></script>
		<script src="${ctx}/dwr/engine.js"></script>
		<script src="${ctx}/dwr/util.js"></script>
		<script type='text/javascript' src='${ctx}/dwr/interface/moduleService.js'> </script>
		<%@include file="/common/jsLib.jsp"%>
		
		<script>
		   $(function(){
	             $('#parentModuleTree').tree({   
	                 checkbox: false,   
	                 url: 'findChildModule.action', 
	                 onBeforeExpand:function(node,param){
	                     $('#parentModuleTree').tree('options').url = "findChildModule.action?selModule=" + node.id;
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
			<ul id="parentModuleTree">
			</ul>
		</div>
	</body>
</html>