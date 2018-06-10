<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模块列表</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
        function new_module(){
        	createSimpleWindow("moduleWindow","新增模块信息", "${ctx}/jsp/admin/module/editModule.action?moduleCode="+(window.currentModuleCode||""), 620, 270);
        }
        function edit_module(id){
        	createSimpleWindow("moduleWindow","修改模块信息", "${ctx}/jsp/admin/module/editModule.action?module.id="+id, 620, 270);
        }
        function editModuleRole(id){
        	createSimpleWindow("edit_module_right","模块角色设置", "${ctx}/jsp/admin/module/editModuleRole.action?moduleId="+id, 650, 470);
        }
        function inactive_module(id){
        	$.messager.confirm('禁用模块', '确定要禁用该模块？\n(该操作会禁用该模块的下属模块)', function(result){
				if (result){
	                $.ajax({
	                	url : "inactiveModule.action",
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    data:{ 
	                    	"module.id" : id
	                    },
	                    error: function(){
	                    	$.messager.alert('错误','禁用模块时出错！');
	                    },
	                    success: function(data){
	                        if(data.result){
	                        	$.messager.alert('提示','禁用模块成功！');
	                        	search_module();
	                        }else{
	                        	$.messager.alert('错误','禁用模块时出错！');
	                        }
	                    }
	                });					
				}
			});
        }
        function active_module(id){
        	$.messager.confirm('激活模块', '确定要激活该模块？\n(该操作只激活当前模块，不会激活下属模块)', function(result){
				if (result){
	                $.ajax({
	                	url : "activeModule.action",
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    data:{ 
	                    	"module.id" : id
	                    },
	                    error: function(){
	                    	$.messager.alert('错误','激活模块时出错！');
	                    },
	                    success: function(data){
	                        if(data.result){
	                        	$.messager.alert('提示','激活模块成功！');
	                        	search_module();
	                        }else{
	                        	$.messager.alert('错误','激活模块时出错！');
	                        }
	                    }
	                });	
				}
			});
        }
        function search_module(){
        	var queryParams = {
        		"module.moduleCode" : window.currentModuleCode||"",
				"module.moduleName" : $("#moduleName").val(),
				"module.isVisible" : $("#isVisible").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$("#pagination").datagrid('load');
        }
        window.autoDatagridHeight = <%=(String)session.getAttribute("autoDatagridHeight")%>;
        $(function(){
        	$('#moduleTree').tree({
				url: 'findChildModule.action',
				onBeforeExpand:function(node){
					$('#moduleTree').tree('options').url = "findChildModule.action?selModule=" + node.id;
				},
				onClick:function(node){
					clear_form(document.myform);
					window.currentModuleCode=node.id;
					search_module();
				}
			});
       
			$('#pagination').datagrid($.extend(dg_cm_pp,{
				toolbar:[{
					text:'添加',
					iconCls:'icon-add',
					handler:new_module
				}],
				title:'模块列表',
				url:'listModule.action',
				queryParams:{
					"module.moduleName" : $("#moduleName").val(),
					"module.isVisible" : $("#isVisible").val()
				},
				columns:[[
				          {field:'moduleName',title:'模块名称',width:fixWidth(0.1)},
				          {field:'moduleFullName',title:'模块全称',width:fixWidth(0.08)},
				          {field:'moduleCode',title:'模块编码',width:fixWidth(0.08)},
					      {field:'sortSq',title:'序号',width:fixWidth(0.05)},
					      {field:'moduleAddr',title:'链接地址',width:fixWidth(0.20)},
					      {field:'target',title:'目标框架',width:fixWidth(0.1)},
				          {field:'op',title:'操作',width:fixWidth(0.20),formatter:function(value,rec){
								if(rec.isVisible==1){
									return "<a href='#' class='btn_02_mini' onclick='edit_module(\""+rec.id+"\")'>修改<b></b></a>"
										  +"<a href='#' class='btn_03_mini' onclick='inactive_module(\""+rec.id+"\")'>禁用<b></b></a>"
										  +"<a href='#' class='btn_04_mini' onclick='editModuleRole(\""+rec.id+"\")'>角色设置<b></b></a>";
								}else
									return "<a href='#' class='btn_02_mini' onclick='edit_module(\""+rec.id+"\")'>修改<b></b></a>"
										  +"<a href='#' class='btn_03_mini' onclick='active_module(\""+rec.id+"\")'>激活<b></b></a>"
										  +"<a href='#' class='btn_04_mini' onclick='editModuleRole(\""+rec.id+"\")'>角色设置<b></b></a>";
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
				<ul id="moduleTree">
				</ul>
			</div>
			</div>
			<div class="layout_01_right">
			<div class="layout_overflow">
				<div class="inner6px submitdata">
					<div class="boxBmargin12 cell">
						<form name="myform" method="post">
							<input type="hidden" name="moduleCode" value="${moduleCode}">
							<input type="hidden" name="module.moduleCode" value="${module.moduleCode}">
							<table>
								<tr>
									<th width="15%">
										模块名
									</th>
									<td width="35%">
										<input type="text" class="input_text" name="module.moduleName" id="moduleName" value="${module.moduleName}">
									</td>
									<th width="15%">
										是否显示禁用的模块
									</th>
									<td width="35%">
										<select name="module.isVisible" id="isVisible">
											<option value="0" <c:if test="${module.isVisible==0}">selected</c:if> >
												否
											</option>
											<option value="1" <c:if test="${module.isVisible==1}">selected</c:if> >
												是
											</option>
										</select>									
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="btn_area_setc">
											<a href="###" class="btn_01" onclick="clear_form(document.myform);">清空<b></b></a>
											<a href="###" class="btn_01" onclick="search_module();">查询<b></b></a>
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
