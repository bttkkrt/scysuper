<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模块角色管理</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
		    function RemoveSelect(ff){
		        ff=document.forms[0];
		        var options = ff.moduleIds.options;
		        var i;
		        for(i=0;i<options.length;i++){
		        	if(options[i].selected)
			        {
			            options[i]=null;
			            i--;
			        }
		        }
		    }
		
		    function confirm_onclick(ff){
		    	layer.load(0, {shade: false});
		    	ff=document.forms[0];
		    	var options = ff.moduleIds.options;
		    	var moduleCodes = "";
		    	for(i=0;i<options.length;i++){
		    		moduleCodes += options[i].value+"|";
		        }
		    	debugger;
		        ff.moduleCodes.value = moduleCodes;
		        ff.action = "saveModuleRight.action";
		        ff.submit();
		    }
			$(function(){
				$('#moduleTree').tree({
					checkbox: true,
					url:'${ctx}/jsp/admin/module/findModuleTreeForRight.action?roleId=${roleId}',
					onExpand : function(node, param){
						//var isChecked = $('#moduleTree').tree('getChecked', node.target);alert(isChecked)
						if(!node.checked)
							return false;
						else{
							var children = $('#moduleTree').tree('getChildren', node.target);
							for(var i = 0; i < children.length; i++){
								var child = children[i];
								$('#moduleTree').tree('check', child.target);
							}
						}
					},
				    onBeforeExpand:function(node,param){
						$('#moduleTree').tree('options').url = "${ctx}/jsp/admin/module/findModuleTreeForRight.action?selModule=" + node.id+"&roleId=${roleId}";
					},
					onCheck:function(node,checked){
						
						var options = document.getElementById("moduleIds").options;  
						var id = node.id;
		  	            
						if(checked){
							var isLeaf = $('#moduleTree').tree('isLeaf', node.target);
							$('#moduleTree').tree('expand', node.target);
							var children = $('#moduleTree').tree('getChildren', node.target);;
							if(children.length>0){
								isLeaf=true;
							}
							for(var i = 0; i < children.length; i++){
								var child = children[i];
								$('#moduleTree').tree('check', child.target);
							}
		   	               var i,bFlag;
						   for (i=0; i < options.length; i++){
							   if(options[i].value==id){
								   bFlag=true;
								   break;
						       }	
						   }
						   if(!bFlag && isLeaf){
			   	        	   debugger;
							   options.add(new Option(node.text,id));
							}
		   	           }else {
			   	        	var children = $('#moduleTree').tree('getChildren', node.target);;
							for(var i = 0; i < children.length; i++){
								var child = children[i];
								$('#moduleTree').tree('uncheck', child.target);
							}
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
		<div class="page_dialog">
			<div class="inner6px">
				<form method="get">
					<input type="hidden" name="moduleCodes">
					<input type="hidden" id="hideParentScroll">
					<input type="hidden" name="roleId" value="${roleId}">
					<div class="popwin_layer_01">
						<div class="popwin_layer_01_left">
							<div class="box_01">
								<div class="caption_01">待选择模块</div>
								<div class="inner6px popwin_layer_01_height">
									<ul id="moduleTree"></ul>
								</div>
							</div>
						</div>
						<div class="popwin_layer_01_right">
							<div class="box_01">
								<div class="caption_01">已选择模块
									<div class="right">
										<a href="javascript:RemoveSelect(this.form);" class="btn_01_ultra_mini" style="width:40px"><b></b>×</a>
										<a href="javascript:confirm_onclick(this.form);" class="btn_01_ultra_mini" style="width:40px"><b></b>√</a>
									</div>
								</div>
								<div class="inner6px popwin_layer_01_height">
									<select id="moduleIds" style="width:100%;height:100%;" multiple name="moduleIds">
										<c:forEach items="${rightList}" var="right">
											<c:if test="${not empty right.module.moduleCode}">
											<option value="${right.module.moduleCode}">${right.module.moduleName}</option>
											</c:if>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
