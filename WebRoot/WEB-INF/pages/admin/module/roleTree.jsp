<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
String moduleId = request.getParameter("moduleId");
%>
<%@include file="/common/jsLib.jsp"%>
<script src="${ctx}/dwr/engine.js"></script>
<script src="${ctx}/dwr/util.js"></script>
<script type='text/javascript' src='${ctx}/dwr/interface/userRoleService.js'> </script>
<script>
	$(function(){
		$('#roleTree').tree({   
			checkbox: true,
			cascadeCheck:false, 
			url: '${ctx}/jsp/admin/role/findRoleTreeForModule.action?userRole.roleType=ALL&moduleId='+'<%=moduleId%>', 
			onBeforeExpand:function(node,param){
				$('#roleTree').tree('options').url = "${ctx}/jsp/admin/role/findRoleTreeForModule.action?userRole.roleType=ALL&selNode=" + node.id+"&moduleId="+'<%=moduleId%>';
			},
			onCheck:function(node,checked){
			//设置选中的对象
				var options = parent.document.getElementById("roleIds").options;  
				var id = node.id;
				if(node.text=="角色")
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
	<div id="tree-div" style="overflow: auto; height: 100%; width: 100%; border: 0px solid;">
		<ul id="roleTree">
		</ul>
	</div>
</body>
</html>