<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title></title>
		<%
			String func = request.getParameter("func");
		%>
		
		<script type="text/javascript" src="${ctx}/webResources/js/module.js"></script>
		
		<script src="${ctx}/dwr/engine.js"></script>
		<script src="${ctx}/dwr/util.js"></script>
		<script type='text/javascript'
			src='${ctx}/dwr/interface/moduleService.js'> </script>
		
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
	             $('#moduleTree').tree({   
	                 checkbox: false,   
	                 url: 'queryCategory.action', 
                 	 onClick:function(node){
                     	<%=func%>(node);//鼠标点击后的事件 
               		 }
	             });   
	         });
	         
	         //str 返回
	         function returnStr(node)
	         {
	         	var categoryId = node.id;
				var categoryName = node.text;
				var arr=[];
				arr.push(categoryId);
				arr.push(categoryName);
				window.returnValue= arr;
				window.close();	
	         }
        </script>
	</head>
	<body>
		<div id="tree-div"
			style="overflow: auto; height: 100%; width: 100%; border: 0px solid;">
			<ul id="moduleTree">
			</ul>
		</div>
	</body>
</html>