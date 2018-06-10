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
	                 url: 'findChildDept.action?deptId='+"${deptId}", 
	                 onBeforeExpand:function(node,param){
	                     //var doc = window.parent.document.getElementsByTagName("iframe")["edit_dept_frm"].contentWindow.document;
	                     var doc = window.parent.document.getElementById("dept.deptCode").value;
	                     if("<%=func%>"=="setParentDept" && node.id==doc)
	                     	return false;
	                     $('#deptTree').tree('options').url = "findChildDept.action?selDept="+node.id+"&deptId="+"${deptId}";
                 	 },
                 	 onClick:function(node){
                 	 	//var doc = window.parent.document.getElementsByTagName("iframe")["edit_dept_frm"].contentWindow.document;
                 	 	var doc = window.parent.document.getElementById("dept.deptCode").value;
                 	 	if("<%=func%>"=="setParentDept" && node.id==doc)
	                     	return;
                     	<%=func%>(node);//鼠标点击后的事件 
               		 }
	             });   
	         }); 
        </script>
	</head>
	<body>
		<div style="overflow: auto; height: 100%; width: 100%; border: 0px solid #c3daf9;">
			<ul id="deptTree"></ul>
		</div>
	</body>

</html>