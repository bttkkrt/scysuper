<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%
String roleId = request.getParameter("roleId");
%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

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
               checkbox: true,
               onlyLeafCheck:true,
               url: '${ctx}/jsp/admin/module/findModuleTreeForRight.action?roleId='+'<%=roleId%>', 
               onBeforeExpand:function(node,param){
                   $('#moduleTree').tree('options').url = "${ctx}/jsp/admin/module/findModuleTreeForRight.action?selModule=" + node.id+"&roleId="+'<%=roleId%>';
              	 },
              	 onCheck:function(node,checked){
                  	//设置选中的对象
   	         var options = parent.document.getElementById("moduleIds").options;  
   	         var id = node.id;
  	         
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
            });   
        });
       </script>
</head>
<body>
	<div id="tree-div"
		style="overflow: auto; height: 100%; width: 100%; margin-top: -6px !important; margin-top: -12px; border: 0px solid;">
		<ul id="moduleTree">
		</ul>
	</div>
</body>
</html>