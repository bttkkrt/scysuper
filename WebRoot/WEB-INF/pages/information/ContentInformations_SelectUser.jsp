<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%@taglib prefix="ww" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>选择用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	
	<!-- jquery & easyui js -->
	<script type="text/javascript" src="${ctx}/webResources/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/js/easyui/windowOnMove.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>

	<!-- easyui css -->
	<link rel="stylesheet" type="text/css" href="${ctx}/webResources/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/webResources/js/easyui/themes/icon.css">

	<!-- datePicker -->
	<script src="${ctx}/webResources/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

	<!-- validform -->
	<script type="text/javascript" src="${ctx}/webResources/js/Validform/Validform_v5.3.2.js"></script>


	<script type="text/javascript">
     	window.autoDatagridHeight= <%=(String) session.getAttribute("autoDatagridHeight")%>;
	</script>
	<!-- platform -->
	<script type="text/javascript" src="${ctx}/webResources/js/swfobject.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/js/jquery.uploadify.v2.1.4.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/js/common.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/webResources/style/css/default/animation1.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/webResources/style/css/default/form.css">
	<!-- 原有的表单验证工具，为了兼容旧的项目 -->
	<script src="${ctx}/webResources/js/validator.js"></script>

	<script type="text/javascript" src="${ctx}/webResources/fancyboxs/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.css" media="screen" />

	<link rel="stylesheet" type="text/css" href="${ctx}/webResources/js/poshytip/tip-yellow/tip-yellow.css">
	<script type="text/javascript" src="${ctx}/webResources/js/poshytip/jquery.poshytip.min.js"></script>
	
	<script type="text/javascript" src="${ctx}/webResources/js/jquery.autocomplete.js"></script>	
	<script type="text/javascript" src="${ctx}/webResources/js/jquery/jquery.validate.min.js"></script>
	<link rel="stylesheet" href="${ctx}/webResources/ZTreeStyle3/demo.css" type="text/css"></link>
	<link rel="stylesheet" href="${ctx}/webResources/ZTreeStyle3/zTreeStyle.css" type="text/css"></link>
	<script type="text/javascript" src="${ctx}/webResources/jquery.ztree.core-3.0.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/jquery.ztree.excheck-3.0.min.js"></script>	
	<script src="${ctx}/webResources/layer/layer.js"></script>
	<script>
		
		 var setting = {
	    			check: {
	    				enable: true
	    			},
	    			data: {
	    				simpleData: {
	    					enable: true
	    				}
	    			},
	    			callback: {
	    				onCheck: onCheck
	    			}
	    		};
	    		

		 $(document).ready(function(){
			    //获取组织机构人员
			    var index = layer.load(0, {shade: false});
			    var userIds=window.opener.document.getElementById('userIds').value;
			    var userNames=window.opener.document.getElementById('userNames').value;
			    var infoType = window.opener.document.getElementById('infoType').value;
                var u=userNames.substring(0,userNames.length-1); 
                var ids=userIds.split(",");
                var names=u.split(",");
			    var t=new Date().getTime();
			    $.post('${ctx}/jsp/information/getDepartUser.action',{'t':t,'contentInformations.infoType':infoType,'userIds':userIds},function(nodes){
			    	layer.close(index);
			    	$.fn.zTree.init($("#treeDemo"), setting, nodes);
			    },'json');
			   
                if(userIds!=""){
	                for(var i=0;i<ids.length;i++){
	                	$('#lstModels').append("<option  value='"+ids[i]+"'>"+names[i]+"</option>");
	                }
                }
                
 		 });
 
 function onCheck(e, treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	
 	var options = document.all["lstModels"].options;
 	
 	if(treeNode.checked)
 		{
 			var nodeArr = treeObj.getCheckedNodes(true);
	 		for(var k=0;k<nodeArr.length;k++)
	 	 	{
	 	    		var i,bFlag=false;
	 			    
	 	    		for (i=0; i < options.length; i++) 
	 			    {
	 			        if(options[i].value==nodeArr[k].id)
	 			        {
	 			            bFlag=true;
	 			            break;
	 			        }	
	 			    }			
	 				if(!bFlag)
	 				{
	 					if(nodeArr[k].flag==1)
	 						$('#lstModels').append("<option  value='"+nodeArr[k].id+"'>"+nodeArr[k].name+"</option>");
	 				}
	 	 		
	 	 	} 	
 		}
 	else
 		{
 			var changeNodesArr = treeObj.getCheckedNodes(false);
 			var i;
 			for(var k=0;k<changeNodesArr.length;k++)
 				{
 				
	 				for (i=0; i < options.length; i++) 
	 	            {
	 	                if(options[i].value==changeNodesArr[k].id)
	 	                {
	 	                    options[i] = null;
	 	                    i--;
	 	                }	
	 	            }
 				}
       		
 		}
 	
	}
 
 function RemoveSelect()
 {
	    var options = document.all["lstModels"].options;
	    var i;
	    for (i=0; i < options.length; i++) 
	    {
	        if(options[i].selected)
	        {
	            options[i]=null;
	            i--;
	        }	
	    }			
 }
 
 function returnCheckedUsers(flowType) {
		var userids="";
		var usernames="";
		
		 var options = document.all["lstModels"].options;
		 for (i=0; i < options.length; i++) 
		 {
		        	userids=userids+$.trim(options[i].value)+",";
		        	usernames=usernames+$.trim(options[i].text)+",";
		 }
		 
		if(userids&&""!=userids){
			window.opener.document.getElementById("userIds").value = userids.substring(0,userids.length-1);
			window.opener.document.getElementById("userNames").value = usernames.substring(0,usernames.length-1);
			window.close();			
		}else{
			alert("请选择接收人");
		}
	}
</script>
	
<style>
.ac_results {
	padding: 0px;
	border: 1px solid black;
	background-color: white;
	overflow: hidden;
	z-index: 99999;
}

.ac_results ul {
	width: 100%;
	list-style-position: outside;
	list-style: none;
	padding: 0;
	margin: 0;
}

.ac_results li {
	margin: 0px;
	padding: 2px 5px;
	cursor: default;
	display: block;
	font: menu;
	font-size: 12px;
	line-height: 20px;
	overflow: hidden;
}

.ac_loading {
	background: white url('${ctx}/webResources/ZTreeStyle3/img/indicator.gif') right center no-repeat;
}

.ac_odd {
	background-color: white;
}

.ac_over {
	background-color: #A8D5FC;
	color: black;
}

.ztree li button.ico_loading{margin-right:2px; background:url(${ctx}/webResources/ZTreeStyle3/img/loading.gif) no-repeat scroll 0 0 transparent; vertical-align:top; }

.ztree li button {width:16px; height:16px; vertical-align:middle; border:0 none; cursor: pointer;
	background-color:transparent; background-repeat:no-repeat; background-attachment: scroll;
	background-image:url("${ctx}/webResources/ZTreeStyle3/img/zTreeStandard.png");}

button.tmpzTreeMove_arrow {width:16px; height:16px; padding:0; margin:2px 0 0 1px; border:0 none; position:absolute;
	background-color:transparent; background-repeat:no-repeat; background-attachment: scroll;
	background-position:-80px -80px; background-image:url("${ctx}/webResources/ZTreeStyle3/img/zTreeStandard.png"); }

</style>	
</head>
<body>
		<input type="hidden" name="userNameIds" id="userNameIds">
			<table width="100%" border="0">
				<tr width="100%">
				<td  style="vertical-align:top;" width="50%">
				
				 <div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree">
					</ul>
				 </div>
				 </td>
				<td  style="vertical-align:top;" width="50%">
				<div class="zTreeDemoBackground left">
					<ul id="tree1" class="ztree">
					  <select id="lstModels" style="width: 100%; height: 340px;" multiple name="lstModels" ondblclick="RemoveSelect()" >
                      </select>
					</ul>
				</div>
				</td>
				</tr>
				<tr>
					<td colspan="2" height="100px" style="text-align:center">
							<a href="#" class="easyui-linkbutton" onclick="returnCheckedUsers()" iconCls="icon-save">确认</a>&nbsp;
							<a href="javascript:RemoveSelect();" class="easyui-linkbutton" iconCls="icon-remove">删除</a>&nbsp;
							<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">取消</a>
					</td>
				</tr>
			</table>
</body>
</html>
