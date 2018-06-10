<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%@taglib prefix="ww" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>选择用户</title>
		<%@include file="/common/jsLib.jsp"%>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript" src="${ctx}/webResources/js/jquery.autocomplete.js"></script>	
		<script type="text/javascript" src="${ctx}/webResources/js/jquery/jquery.validate.min.js"></script>
		<link rel="stylesheet" href="${ctx}/webResources/ZTreeStyle3/demo.css" type="text/css"></link>
		<link rel="stylesheet" href="${ctx}/webResources/ZTreeStyle3/zTreeStyle.css" type="text/css"></link>
		<script type="text/javascript" src="${ctx}/webResources/jquery.ztree.core-3.0.js"></script>
		<script type="text/javascript" src="${ctx}/webResources/jquery.ztree.excheck-3.0.min.js"></script>	
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
			   	var t=new Date().getTime();
			   	$.post(
			   		'${ctx}/jsp/checkBasic/getDepartUser.action?checkBasic.id=${checkBasic.id}',
		   			{'t':t},
		   			function(nodes){
			   			$.fn.zTree.init($("#treeDemo"), setting, nodes);
			   		},
			   		'json'
			   	);
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

			function RemoveSelect(){
		    	var options = document.all["lstModels"].options;
			    var i;
			    for (i=0; i < options.length; i++){
			        if(options[i].selected){
			            options[i]=null;
			            i--;
			        }	
			    }			
		 	}
 
 			function returnCheckedUsers(flowType) {
				var userids="";
				var usernames="";
		 		var options = document.all["lstModels"].options;
			 	for (i=0; i < options.length; i++){
	        		userids=userids+$.trim(options[i].value)+"|";
		        	usernames=usernames+$.trim(options[i].text)+"|";
			 	}
		 
				if(userids&&""!=userids){
					var arr=[];
					arr.push(userids.substring(0,userids.length-1));
					arr.push(usernames.substring(0,usernames.length-1));
					window.returnValue= arr;
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
			.ztree li button.ico_loading{
				margin-right:2px; 
				background:url(${ctx}/webResources/ZTreeStyle3/img/loading.gif) no-repeat scroll 0 0 transparent; 
				vertical-align:top; 
			}
			.ztree li button {
				width:16px; 
				height:16px; 
				vertical-align:middle; 
				border:0 none; 
				cursor: pointer;
				background-color:transparent; 
				background-repeat:no-repeat; 
				background-attachment: scroll;
				background-image:url("${ctx}/webResources/ZTreeStyle3/img/zTreeStandard.png");
			}
			button.tmpzTreeMove_arrow {
				width:16px; 
				height:16px; 
				padding:0; 
				margin:2px 0 0 1px; 
				border:0 none; 
				position:absolute;
				background-color:transparent; 
				background-repeat:no-repeat; 
				background-attachment: scroll;
				background-position:-80px -80px; 
				background-image:url("${ctx}/webResources/ZTreeStyle3/img/zTreeStandard.png"); 
			}
		</style>	
	</head>
	<body>
		<input type="hidden" name="userNameIds" id="userNameIds">
		<table width="100%" border="0">
			<tr width="100%">
				<td  style="vertical-align:top;" width="50%">
		 			<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
			 		</div>
			 	</td>
				<td  style="vertical-align:top;" width="50%">
					<div class="zTreeDemoBackground left">
						<ul id="tree1" class="ztree">
					  		<select id="lstModels" 
				  				style="width: 100%; height: 340px;" multiple name="lstModels" ondblclick="RemoveSelect()" >
				  				<c:forEach items="${checkLawEnforceList}" var="checkLawEnforce">
									<option value="${checkLawEnforce.checkUser.id}">
									${checkLawEnforce.checkUser.displayName}
									</option>
								</c:forEach>
	                      	</select>
						</ul>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" height="100px" style="text-align:center">
					<a href="#" class="easyui-linkbutton" onclick="returnCheckedUsers()" iconCls="icon-save">提交</a>&nbsp;
					<a href="javascript:RemoveSelect();" class="easyui-linkbutton" iconCls="icon-remove">删除</a>&nbsp;
					<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">取消</a>
				</td>
			</tr>
		</table>
	</body>
</html>
