<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="curr_path" value="模块角色设置"></c:set>
<head>
<title>模块角色设置</title>
<%@include file="/common/jsLib.jsp"%>
<script>
    function changeRole(ff){
        ff.action = "listModuleRight.action";
        ff.submit();
    }
    function RemoveSelect(ff){
        ff=document.forms[0];
        var options = ff.roleIds.options;
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
    	//加载层
    	var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
    	ff=document.forms[0];
    	var options = ff.roleIds.options;
    	var roleCodes = "";
    	for(i=0;i<options.length;i++){
    		roleCodes += options[i].value+"|";
        }
        ff.roleCodes.value = roleCodes;
        
        ff.action = "saveModuleRight.action";
        ff.submit();
    }
	$(function(){
		$('#roleTree').tree({   
			checkbox: true,
			url: '${ctx}/jsp/admin/role/findRoleTreeForModule.action?userRole.roleType=ALL&moduleId=${moduleId}', 
			onBeforeExpand:function(node,param){
				$('#roleTree').tree('options').url = "${ctx}/jsp/admin/role/findRoleTreeForModule.action?userRole.roleType=ALL&selNode=" + node.id+"&moduleId=${moduleId}";
			},
			onCheck:function(node,checked){
				var options = document.getElementById("roleIds").options;  
				var id = node.id;
				if(node.text=="角色")
					return;
				else{
					if(checked){
						var isLeaf = $('#roleTree').tree('isLeaf', node.target);
						$('#roleTree').tree('expand', node.target);
						var children = $('#roleTree').tree('getChildren', node.target);;
						if(children.length>0){
							isLeaf=true;
						}
						for(var i = 0; i < children.length; i++){
							var child = children[i];
							$('#roleTree').tree('check', child.target);
						}
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
						var children = $('#roleTree').tree('getChildren', node.target);;
						for(var i = 0; i < children.length; i++){
							var child = children[i];
							$('#roleTree').tree('uncheck', child.target);
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
			}
		});   
	});
</script>
</head>

	<body>
		<div class="page_dialog">
			<div class="inner6px">
				<form method="get">
					<input type="hidden" name="roleCodes">
					<input type="hidden" id="hideParentScroll">
					<input type="hidden" name="moduleId" value="${moduleId}">
					<div class="popwin_layer_01">
						<div class="popwin_layer_01_left">
							<div class="box_01">
								<div class="caption_01">待选择角色</div>
								<div class="inner6px popwin_layer_01_height">
									<ul id="roleTree"></ul>
								</div>
							</div>
						</div>
						<div class="popwin_layer_01_right">
							<div class="box_01">
								<div class="caption_01">已选择角色
									<div class="right">
										<a href="javascript:RemoveSelect(this.form);" class="btn_01_ultra_mini" style="width:40px"><b></b>×</a>
										<a href="javascript:confirm_onclick(this.form);" class="btn_01_ultra_mini" style="width:40px"><b></b>√</a>
									</div>
								</div>
								<div class="inner6px popwin_layer_01_height">
									<select id="roleIds" style="width:100%;height:100%" multiple name="roleIds">
				                        <c:forEach items="${moduleRightList}" var="right">
											<option value="${right.role.roleCode}">
												${right.role.roleName}
											</option>
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
