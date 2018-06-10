<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>角色树</title>
		<%
			String func = request.getParameter("func");
		%>
		
		<script src="${ctx}/dwr/engine.js"></script>
		<script src="${ctx}/dwr/util.js"></script>
		<script type='text/javascript'
			src='${ctx}/dwr/interface/userRoleService.js'> </script>
			
				<link rel="stylesheet" type="text/css"
			href='<c:url value="/webResources/js/easyui/themes/${curr_user.cssId}/easyui.css"/>'>
		<link rel="stylesheet" type="text/css"
			href='<c:url value="/webResources/js/easyui/themes/icon.css"/>'>
		
		<script type="text/javascript"
			src='<c:url value="/webResources/js/easyui/jquery-1.4.4.min.js"/>'></script>
		<script type="text/javascript"
			src='<c:url value="/webResources/js/easyui/jquery.easyui.min.js"/>'></script>
		
		<script>
			$(function(){
	             $('#roleTree').tree({   
	                 checkbox: false,   
	                 url: 'findChildNode.action?userRole.roleType=${userRole.roleType}', 
	                 onBeforeExpand:function(node,param){
	                     $('#roleTree').tree('options').url = "findChildNode.action?userRole.roleType=${userRole.roleType}&selNode=" + node.id;
                 	 },
                 	 onClick:function(node){
                     	<%=func%>(node);//鼠标点击后的事件 
               		 }
	             });   
	         });
		    function view_role(node, e){
		    	var id = node.id;
		    	if(id.length>1)
		    		window.open("list.action?userRole.roleType=${userRole.roleType}&userRole.roleCode="+id,"role_right");
		    	else
		    		window.open("list.action","role_right");
		    }
		    function setParentRole(node, e){
		    	var id = node.id;
		    	opener.document.getElementById("parentRoleCode").value = id;
		    	opener.document.getElementById("parentRoleName").value = node.text;
		    	userRoleService.createRoleCode(id,function(newCode){
		    		opener.document.getElementById("roleCode").value = newCode;
		    		window.close();
               });
		    }
        </script>
	</head>

	<body>
		<div style="overflow:auto; width: 150px;">
			<ul id="roleTree">
			</ul>
		</div>
	</body>
</html>