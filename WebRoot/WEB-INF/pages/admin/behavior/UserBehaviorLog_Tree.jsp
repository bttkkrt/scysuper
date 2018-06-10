<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
String userId = request.getParameter("userId");
%>
<%@include file="/common/jsLib.jsp"%>
<script>
	$(function(){
	        $('#roleTree').tree({   
	            checkbox: false,
				cascadeCheck:false, 
	            url: 'findAllUserBehaviors.action', 
	            onBeforeExpand:function(node,param){
	                $('#roleTree').tree('options').url = "findAllUserBehaviors.action";
				},
				onClick:function(node){
				 window.open("userBehaviorLogList.action?userBehaviorLog.behavior.id="+node.id,"_right");
				}
	        });   
	    });
</script>
</head>
<body>
	<div style="overflow: auto; height: 99%; width: 100%; border: 0px solid #c3daf9;">
		<ul id="roleTree">
		</ul>
	</div>
</body>
</html>