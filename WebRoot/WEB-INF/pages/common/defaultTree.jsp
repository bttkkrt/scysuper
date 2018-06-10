<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" import="com.jshx.core.utils.Constants"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/common/header.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>快捷菜单</title>
	<link
		href="<c:url value="/webResources/themes/${curr_user.cssId}/css/${curr_user.cssId}.css"/>"
		rel="stylesheet" type="text/css" />

	<link rel="stylesheet" type="text/css"
		href='<c:url value="/webResources/js/easyui/themes/${curr_user.cssId}/easyui.css"/>'>
	<link rel="stylesheet" type="text/css"
		href='<c:url value="/webResources/js/easyui/themes/icon.css"/>'>

	<script type="text/javascript"
		src='<c:url value="/webResources/js/easyui/jquery-1.4.4.min.js"/>'></script>
	<script src="${ctx}/webResources/js/common.js"></script>
	<script type="text/javascript"
		src='<c:url value="/webResources/js/easyui/jquery.easyui.min.js"/>'></script>
	<script>
			  $(function(){
				  var dt = (new Date()).getTime();
	             $('#moduleTree').tree({   
	                 checkbox: false,   
	                 url: '${ctx}/findQuicklyStartTree.action?dt=' + dt, 
                 	 onClick:function(node){
                 	 	if(node.attributes!=null&&node.attributes!=''){
	                     	var loadFlag= document.getElementById("loadFlag").value;
	     	 				if(loadFlag==0){
		     	 				 document.getElementById("loadFlag").value='1';
		     	 				 geturl(node);
		     	 				 setTimeout(function(){document.getElementById("loadFlag").value='0';}, 6000); 
	     	 				}
	     	 				else{
			                	$.messager.show({  
	                                title: "操作提示",  
	                                msg: "页面加载中，请稍候！",  
	                                showType: 'slide',  
	                                timeout: 2000,
	                                width:192
	                               });  
	                        }
                     	}
               		 }
	             });   
	         });
            function geturl(node){
            	var dt=new Date();
            	<%
            	if(Constants.USER_TAB){
            	%> 
            	var moduleCode = node.id;
            	var text = node.text;
            	var url = "${ctx}/"+node.attributes;
            	if(node.attributes==null||node.attributes=="")
            		return;
            	if(url.indexOf("?")!=-1)
            		url+="&currModuleCode="+moduleCode;
            	else
            		url+="?currModuleCode="+moduleCode;
            	var target = node.data;
            	
            	if(target=="_blank")
            		window.open(url,node.data);
            	else{
                	parent.frames["frm"].addTab(moduleCode,text,url); 
            	}
            	<%
            	}else{
            	%>
            	var moduleCode = node.id.split("|")[0];
            	var url = node.attributes;
            	if(url==null||url=="")
            		return;
            	if(url.indexOf("?")!=-1)
            	{
            		url+="&currModuleCode="+moduleCode;
            	}
            	else
            	{
            		url+="?currModuleCode="+moduleCode;
            	}
            	
            	window.open(url,node.data);
            	<%
            	}
            	%>
            }
        </script>
</head>
<body class="tree">
	<input type="hidden" id="loadFlag" value="0" />
	<div id="tree-div">
		<ul id="moduleTree">
		</ul>
	</div>
</body>