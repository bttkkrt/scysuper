<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>多级一维代码</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css"
	href="${ctx}/webResources/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="${ctx}/webResources/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/webResources/ext/ext-all.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/code.js"></script>
<script type="text/javascript"
	src="${ctx}/webResources/ext/adapter/dwr/DWRTreeLoader.js"></script>
<script src="${ctx}/dwr/engine.js"></script>
<script type='text/javascript'
	src='${ctx}/dwr/interface/CodeService.js'> </script>
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
		    	     useArrows:false,
		    	     loader: new Ext.ux.DWRTreeLoader({  
		    	         dwrCall:CodeService.findCodeByTree  
		    	     }),   	  
		    	     root: new Ext.tree.AsyncTreeNode({          
		    	         text: '${code.codeName}',  
		    	         hasChildren:true,  
		    	         id:   '${code.id}|0|0|0|${showCheck}|${isChecked}'
		    	     }) 	       		    
		    	 });
		    	 tree.on("click",function(node,e){
		    		 var ids = node.id.split("|");
		    		 var codeId = ids[0];
		    		 var itemValue = ids[2];
		    		 var itemText = node.text;
		    		 ${func}(node,e);
		    	 });  
		    	 tree.render();
		    	 tree.getRootNode().expand();
		    });
        </script>
</head>

<body>
<div id="tree-div"
	style="overflow: auto; height: 99%; width: 100%; border: 0px solid #c3daf9;"></div>
</body>
</html>