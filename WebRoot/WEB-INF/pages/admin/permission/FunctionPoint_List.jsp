<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>功能点管理</title>
	    <%@include file="/common/jsLib.jsp"%>
		<script>
	        function add(){
	        	var dt=new Date();
	            createSimpleWindow("win_functionPoint","添加功能点","${ctx}/jsp/admin/permission/functionPointInitEdit.action?flag=add&dt="+dt.getTime(),400,150);
	        }
	        function edit(id){
	        	var dt=new Date();
	            createSimpleWindow("win_functionPoint","修改功能点","${ctx}/jsp/admin/permission/functionPointInitEdit.action?flag=mod&functionPoint.id="+id+"&dt="+dt.getTime(),400,150);
	        }
	        function view(id){
	        	var dt=new Date();
	            createSimpleWindow("win_functionPoint","查看功能点","${ctx}/jsp/admin/permission/functionPointView.action?functionPoint.id="+id+"&dt="+dt.getTime(),400,150);
	        }
	        function set_role(id){
	        	createSimpleWindow("edit_func_right","功能点角色设置","${ctx}/jsp/admin/permission/editFuncRole.action?functionPoint.id="+id,650,470);
	        }
	        function set_user(id){
	        	createSimpleWindow("edit_func_user","功能点用户设置","${ctx}/jsp/admin/permission/editFuncUser.action?functionPoint.id="+id,650,470);
	        }
	        function del(){
	        	var rows = $('#pagination').datagrid('getSelections');
	        	var ids = "";
				for(var i=0;i<rows.length;i++){
					ids += rows[i].id+"|";
				}
				if(rows.length<1){
				    $.messager.alert('提示','至少选择一项删除！');
				}else{
				    $.messager.confirm("删除","确定要删除吗?",function(result){
				        if(result){
			                $.ajax({
			                	url : "functionPointDel.action",
			                	type: 'post',
			                    dataType: 'json',
			                    async : false,
			                    data:{ 
			                    	ids : ids
			                    },
			                    error: function(){
			                    	$.messager.alert('错误','删除时出错！');
			                    },
			                    success: function(data){
			                        if(data.result){
			                        	$.messager.alert('提示','删除成功！');
			                        	search_functionPoint();
			                        }else{
			                        	$.messager.alert('错误','删除时出错！');
			                        }
			                    }
			                });
				        }
				    });
				}
	        }
	        function search_functionPoint(){
	        	var queryParams = {
					"functionPoint.funcName": $("#funcName").val(),
				};        	
	        	$('#pagination').datagrid('options').queryParams = queryParams;
	        	$("#pagination").datagrid('load'); 
	        }
	        $(function(){
				$('#pagination').datagrid($.extend(dg_cm_pp,{
					toolbar:[{
						text:'添加',
						iconCls:'icon-add',
						handler:add
					},{
						text:'删除',
						iconCls:'icon-remove',
						handler:del
					}],
					title:'功能点列表',
					url:'functionPointQuery.action',
					columns:[[
							  {field:'id',checkbox:true},
					          {field:'funcName',title:'功能点名称',width:1},
							  {field:'funcPermission',title:'权限表达式',width:1},
							  {field:'op',title:'操作',width:1,formatter:function(value,rec){
									return "<a href='#' class='btn_01_mini' onclick=view('"+rec.id+"')>查看<b></b></a>"
										  +"<a href='#' class='btn_01_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>"
										  +"<a href='#' class='btn_01_mini' onclick=set_role('"+rec.id+"')>设置角色<b></b></a>"
										  +"<a href='#' class='btn_01_mini' onclick=set_user('"+rec.id+"')>设置用户<b></b></a>";
							  }}
					        ]]
				}));
			});
	    </script>
	</head>

	<body>
		<div class="page_content">
			<div class="box_01 submitdata">
				<div class="inner12px">
					<div class="boxBmargin12 cell">
						<table>
							<tr>
								<th width="15%">功能点名称</th>
								<td width="35%"><input name="functionPoint.funcName" id="funcName"></td>
							</tr>
							<tr>
								<td colspan="4">
									<div class="btn_area_setc">
										<a href="###" class="btn_01" onclick="clear_form(document.myform);">清空<b></b></a>
										<a href="###" class="btn_01" onclick="search_functionPoint();">查询<b></b></a>
									</div>
								</td>
							</tr>
						</table>
					</div>
					
					<div id="pagination"></div>
				</div>
			</div>
		</div>
	</body>
</html>
