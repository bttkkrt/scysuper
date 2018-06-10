<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>部门树</title>
		<%
			String roleId = request.getParameter("roleId");
		%>
		<link rel="stylesheet" type="text/css"
			href='<c:url value="/webResources/js/easyui/themes/${curr_user.cssId}/easyui.css"/>'>
		<link rel="stylesheet" type="text/css"
			href='<c:url value="/webResources/js/easyui/themes/icon.css"/>'>
		<script type="text/javascript" src="${ctx}/webResources/js/dept.js"></script>
		<script src="${ctx}/dwr/engine.js"></script>
		<script src="${ctx}/dwr/util.js"></script>
		<script type='text/javascript'
			src='${ctx}/dwr/interface/deptService.js'> </script>
		<script type="text/javascript"
			src='<c:url value="/webResources/js/easyui/jquery-1.4.4.min.js"/>'></script>
		<script type="text/javascript"
			src='<c:url value="/webResources/js/easyui/jquery.easyui.min.js"/>'></script>
		<script>
			var frmName = "${frmName}";
			$(function(){
	             $('#deptTree').tree({   
	                 checkbox: false,   
	                 url: 'findChildDept.action', 
	                 onBeforeExpand:function(node,param){
	                     $('#deptTree').tree('options').url = "findChildDept.action?selDept=" + node.id;
                 	 },
                 	 onClick:function(node){
                     	findUserForWorkFlow(node);//鼠标点击后的事件 
               		 }
	             });   
	         }); 
	        
	        function findUserForWorkFlow(node){
				var deptCode = node.id;
				var funcUrl="${ctx}/workflow/initTaskConfig.action?deptCode="+deptCode;
				window.open(funcUrl,"_right");
    		}
	      
        </script>
	</head>
	<body>
		<div
			style="overflow: auto; height: 99%; width: 100%;margin-top: -6px !important; margin-top: -12px; border: 0px solid #c3daf9;">
			<ul id="deptTree"></ul>
		</div>
	</body>

</html>