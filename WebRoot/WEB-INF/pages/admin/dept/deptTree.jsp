<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>部门树</title>
		<%
			String func = request.getParameter("func");
		%>
		<script type="text/javascript" src="${ctx}/webResources/js/dept.js"></script>
		<script src="${ctx}/dwr/engine.js"></script>
		<script src="${ctx}/dwr/util.js"></script>
		<script type='text/javascript' src='${ctx}/dwr/interface/deptService.js'> </script>
		<%@include file="/common/jsLib.jsp"%>
			
		<script>
			var frmName = "${frmName}";
			$(function(){
	             $('#deptTree').tree({   
	                 checkbox: false,   
	                 url: 'findChildDeptByCurrUser.action', 
	                 onBeforeExpand:function(node,param){
	                     $('#deptTree').tree('options').url = "findChildDeptByCurrUser.action?selDept=" + node.id;
                 	 },
                 	 onClick:function(node){
                     	<%=func%>(node);//鼠标点击后的事件 
               		 }
	             });   
	         }); 
        </script>
	</head>
	<body>
		<div style="overflow: auto; height: 99%; width: 100%; border: 0px solid #c3daf9;">
			<ul id="deptTree"></ul>
		</div>
	</body>
</html>