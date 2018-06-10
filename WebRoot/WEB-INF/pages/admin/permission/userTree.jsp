<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title></title>
<%
String funcId = request.getParameter("funcId");
%>
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
	                 checkbox: true,
	                 onlyLeafCheck:true,
					 cascadeCheck:false, 
	                 url: '${ctx}/jsp/admin/permission/findUserTreeForFunc.action?functionPoint.id='+'<%=funcId%>', 
	                 onBeforeExpand:function(node,param){
	                     $('#roleTree').tree('options').url = "${ctx}/jsp/admin/permission/findUserTreeForFunc.action?selDept=" + node.id+"&functionPoint.id="+'<%=funcId%>';
                 	 },
                 	 onCheck:function(node,checked){
                     	//设置选中的对象
		    	         var options = parent.document.getElementById("roleIds").options;  
		    	         var id = node.id;
		    	         if(node.id.indexOf("D")!=-1)
		    	         	return;
		    	     	 else{
			    	     	  if(checked){
			    	               var i,bFlag;
								   for (i=0; i < options.length; i++){
									   if(options[i].value==id){
										   bFlag=true;
										   break;
								       }	
								   }
								   if(!bFlag){
									   options.add(new Option(node.text,id));
									}
			    	           }else {
				    	           var i;
				    	           for (i=0; i < options.length; i++) {
					    	            if(options[i].value==id){
						    	            options[i] = null;
					                        i--;
					                    }	
					                }
			    	           }
			    	           node.checked = checked;  
		    	     	 }  
               		 }
	             });   
	         });
        </script>
</head>
<body>
	<div id="tree-div"
		style="overflow: auto; height: 100%; width: 100%; margin-top: -6px !important; margin-top: -12px; border: 0px solid;">
		<ul id="roleTree"></ul>
	</div>
</body>
</html>