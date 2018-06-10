<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>角色管理</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
		function getResponse(data){
        	if(data.status=="y"){
        		layer.alert("保存成功");  
        		search_role();
			}else{
				layer.alert("保存失败！"+data.info);  
			}
	    }
        function reloadDate(){
        	$("#roleGrid").datagrid('reload'); 
        }
        function new_role(){
        	createSimpleWindow("edit_role","新增角色","${ctx}/jsp/admin/role/editRole.action?userRole.roleCode=" + (window.currentParentRoleCode||""), 450, 280);
        }	
       	function edit_role(roleId){
       		createSimpleWindow("edit_role","修改角色信息","${ctx}/jsp/admin/role/editRole.action?userRole.id="+roleId, 450, 280);
        }	
        function moduleright_set(roleId){
        	createSimpleWindow("edit_role_module","模块角色设置","${ctx}/jsp/admin/role/listModuleRight.action?roleId="+roleId, 650, 470);
        }
        function userright_set(roleId){
        	createSimpleWindow("edit_role_user","用户角色设置","${ctx}/jsp/admin/role/listUserRight.action?roleId="+roleId+"&dt=" + ((new Date()).getTime()), 715, 410);
        }
        function del(){
			var rows = $("#pagination").datagrid("getSelections");
        	if(rows.length==0){
        		layer.msg('请选择需要删除的数据!', {icon: 0});
				//parent.$.messager.alert("警告","请选择需要删除的数据!","warning");
				return;
			}
        	layer.confirm('确定要删除该角色？', {
        		  btn: ['确定','取消'] //按钮
        		}, function(){
        			var ids = [];
					for(var i=0;i<rows.length;i++){
						ids.push(rows[i].id);
					}
					$.ajax({
	                	url:"checkRole.action",
	                	type:"post",
	                    dataType:"json",
	                    data:"ids="+ids.join("&ids="),
	                    error:function(){
	                    	layer.msg('查询角色下的用户时出错！', {icon: 2});
	                    	//parent.$.messager.alert("错误","查询角色下的用户时出错！");
	                    },
	                    success:function(data){
	                        if(data.result){
	                        	$.ajax({
	        	                	url:"deleteRole.action",
	        	                	type:"post",
	        	                    dataType:"json",
	        	                    data:"ids="+ids.join("&ids="),
	        	                    error:function(){
	        	                    	layer.msg('删除时出错！', {icon: 2});
	        	                    	//parent.$.messager.alert("错误","删除时出错！");
	        	                    },
	        	                    success:function(data){
	        	                        if(data.result){
	        	                        	layer.msg('删除成功', {icon: 1});
	        	                        	search_role();
	        	                        }else{
	        	                        	layer.msg('删除时出错！', {icon: 2});
	        	                        	//parent.$.messager.alert("错误","删除时出错！");
	        	                        }
	        	                    }
	        	                });
	                        }else{
	                        	layer.msg('要删除的角色下有用户，不可删除！', {icon: 2});
	                        	//parent.$.messager.alert("错误","要删除的角色下有用户，不可删除！");
	                        }
	                    }
	                });
        		}, function(){
        		  return;
        		});
        	/* parent.$.messager.confirm("删除角色", "确定要删除该角色？", function(result){
				if(result){
					var ids = [];
					for(var i=0;i<rows.length;i++){
						ids.push(rows[i].id);
					}
					$.ajax({
	                	url:"checkRole.action",
	                	type:"post",
	                    dataType:"json",
	                    data:"ids="+ids.join("&ids="),
	                    error:function(){
	                    	parent.$.messager.alert("错误","查询角色下的用户时出错！");
	                    },
	                    success:function(data){
	                        if(data.result){
	                        	$.ajax({
	        	                	url:"deleteRole.action",
	        	                	type:"post",
	        	                    dataType:"json",
	        	                    data:"ids="+ids.join("&ids="),
	        	                    error:function(){
	        	                    	parent.$.messager.alert("错误","删除时出错！");
	        	                    },
	        	                    success:function(data){
	        	                        if(data.result){
	        	                        	parent.$.messager.alert("提示","删除成功！");
	        	                        	search_role();
	        	                        }else{
	        	                        	parent.$.messager.alert("错误","删除时出错！");
	        	                        }
	        	                    }
	        	                });
	                        }else{
	                        	parent.$.messager.alert("错误","要删除的角色下有用户，不可删除！");
	                        }
	                    }
	                });
				}
			}); */
        }
       function search_role(){
        	var queryParams = {
				"userRole.roleName" : $("#roleName").val(),
				"userRole.roleCode" : window.currentParentRoleCode||""
			};
        	$("#pagination").datagrid("options").queryParams = queryParams;
        	$("#pagination").datagrid("load");
        }
        $(function(){
        	$('#roleTree').tree({   
                url: 'findChildNode.action?userRole.roleType=ALL', 
                onBeforeExpand:function(node,param){
                    $('#roleTree').tree('options').url = "findChildNode.action?userRole.roleType=All&selNode=" + node.id;
                },
                onClick:function(node){
                	clear_form(document.myform);
                	window.currentParentRoleCode=node.id;
                	search_role();
              	}
            });
            
			$('#pagination').datagrid($.extend(dg_cm_pp,{
				toolbar:[{
					text:'新增',
					iconCls:'icon-add',
					handler:new_role
				},"-",{
					text:'删除',
					iconCls:'icon-remove',
					handler:del
				}],
				title:'角色列表',
				url:'listRole.action', 
				queryParams:{
					"userRole.roleName" : $("#roleName").val(),
					"userRole.roleCode" : window.currentParentRoleCode||""
				},
				columns:[[
						  {field:'id',checkbox:true},
				          {field:'roleName',title:'角色名',width:fixWidth(0.2)},
				          {field:'roleType',title:'角色类型',width:fixWidth(0.2)},
				          {field:'delFlag',title:'角色状态',width:fixWidth(0.2),formatter:function(value,rec){
				        	  if(value==0)
				        		  return "正常";
				        	  else if(value==2)
				        		  return "注销";
				          }},
				          {field:'sortSq',title:'同级排序',width:fixWidth(0.2)},
				          {field:'op',title:'操作',width:fixWidth(0.36),formatter:function(value,rec){
			          			return "<a href='#' class='btn_02_mini' onclick='edit_role(\""+rec.id+"\")'>修改<b></b></a>"
			          				  +"<a href='#' class='btn_03_mini' onclick='moduleright_set(\""+rec.id+"\")'>模块设置<b></b></a>"
			          				  +"<a href='#' class='btn_04_mini' onclick='userright_set(\""+rec.id+"\")'>用户设置<b></b></a>";
						  }}
				        ]]
			}));
		});	
		</script>
	</head>

	<body>
		<div class="page_content">
			<div class="layout_01_left">
			<div class="layout_overflow">
				<ul id="roleTree"></ul>
			</div>
			</div>
			<div class="layout_01_right">
			<div class="layout_overflow">
				<div class="inner6px submitdata">
					<div class="boxBmargin12 cell">
						<form name="myform" method="post">
							<table>
								<tr>
									<th width="15%">角色名</th>
									<td width="35%"><input name="userRole.roleName" id="roleName"></td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="btn_area_setc">
											<a href="###" class="btn_01" onclick="clear_form(document.myform);">清空<b></b></a>
											<a href="###" class="btn_01" onclick="search_role();">查询<b></b></a>
										</div>
									</td>
								</tr>
							</table>
						</form>
					</div>
					
					<div id="pagination"></div>
				</div>
			</div>
			</div>
		</div>
	</body>
</html>
