<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%
String roleId = request.getParameter("roleId");
%>
<%@include file="/common/jsLib.jsp"%>

<script>
		    Ext.onReady(function() {
	    		 Ext.BLANK_IMAGE_URL = '${ctx}/webResources/ext/resources/images/default/s.gif';
		    	 tree = new Ext.tree.TreePanel({
		    	     el:'tree-div',
		    	     autoHeight:true,
		    	     rootVisible:true,
		    	     animate:true,
		    	     autoScroll:true,
		    	     collapsible:false,
		    	     bodyStyle:'background:transparent;',
		    	     border:false,
		    	     enableDD:true,containerScroll:true,
		    	     selModel: new Ext.tree.MultiSelectionModel(),
		    	     title: '',
		    	     width:178,
		    	     lines:true,
		    	     frame:false,
		    	     loader: new Ext.ux.DWRTreeLoader({
		    	         dwrCall:moduleService.findModuleTreeForUser
		    	     }),
		    	     root: new Ext.tree.AsyncTreeNode({
		    	         text: '我的模块',
		    	         hasChildren:true,
		    	         id:   'M||${curr_user.id}'
		    	     })
		    	 });
		    	 tree.on('checkchange', function(node, checked) {
	    	         DWREngine.setAsync(false);
	    	         //设置选中的对象
	    	         var options = parent.document.getElementById("moduleId").options;  
	    	         var ids = node.id.split("|");
  	                 var id = ids[1];
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
	    	           node.expand();
	    	           node.attributes.checked = checked;  
	    	           node.eachChild(function(child){
		    	           child.ui.toggleCheck(checked);
		    	           child.attributes.checked = checked;
		    	           child.fireEvent('checkchange', child, checked);
		    	       }); 
		    	       DWREngine.setAsync(true);
                 }, tree);   
		    	 tree.render();
		    	 tree.getRootNode().expand();
		    });*/
		    
		    $(function(){
	             $('#moduleTree').tree({   
	                 checkbox: true,
	                 onlyLeafCheck:true,
	                 url: '${ctx}/jsp/personal/findModuleTreeForQuicklyStart.action', 
	                 onBeforeExpand:function(node,param){
	                     $('#moduleTree').tree('options').url = "${ctx}/jsp/personal/findModuleTreeForQuicklyStart.action?selModule=" + node.id;
                 	 },
                 	 onCheck:function(node,checked){
                     	//设置选中的对象
		    	         var options = parent.document.getElementById("moduleId").options;  
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