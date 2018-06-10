<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>部门列表</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
			function new_dept(){ 
	        	createSimpleWindow("edit_dept","新增部门信息","${ctx}/jsp/admin/dept/editDept.action?deptCode="+(window.currentDeptCode||"${dept.deptCode}")+"&deptId=${dept.id}", 350, 280);
	        }
	        function edit_dept(deptId){
	        	createSimpleWindow("edit_dept","修改部门信息","${ctx}/jsp/admin/dept/editDept.action?dept.id="+deptId, 350, 280);
	        }
	        function view_dept(deptId){
	        	createSimpleWindow("view_dept","查看部门信息","${ctx}/jsp/admin/dept/viewDept.action?dept.id="+deptId, 350, 250);
	        }
	        function set_linked_dept(deptId,linkedDeptTypeCode){
	        	if(linkedDeptTypeCode==""){
	        		parent.$.messager.alert("提示","该部门没有设置关联部门类型！");
	        	}else{
	        		createSimpleWindow("set_dept","设置关联部门","${ctx}/jsp/admin/dept/setLinkedDept.action?dept.id="+deptId+"&dept.linkedDeptTypeCode="+linkedDeptTypeCode, 300, 400);
	        	}
	        }
	        function inactive_dept(id){
	        	layer.confirm('确定要禁用该部门？', {
	        		  btn: ['确定','取消'] //按钮
	        		}, function(){
	        			//加载层
	        			var index = layer.load(0, {shade: true}); 
						$.ajax({
		                	url : "inactiveDept.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	"dept.id" : id
		                    },
		                    error: function(){
		                    	layer.msg('禁用失败', {icon: 2});
		                    },
		                    success: function(data){
		                        layer.close(index);
		                        if(data.result){
		                        	layer.msg('禁用成功', {icon: 1});
		                        	search_dept();
		                        }else{
		                        	layer.msg('禁用失败', {icon: 2});
		                        }
		                    }
		                });		
	        		}, function(){
	        			layer.close();
	        		});
	        }
	        function active_dept(id){
	        	layer.confirm('确定要激活该部门？', {
	        		  btn: ['确定','取消'] //按钮
	        		}, function(){
	        			//加载层
	        			var index = layer.load(0, {shade: true}); 
						$.ajax({
		                	url : "activeDept.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	"dept.id" : id
		                    },
		                    error: function(){
		                    	layer.msg('激活失败', {icon: 2});
		                    },
		                    success: function(data){
		                    	 layer.close(index);
		                        if(data.result){
		                        	layer.msg('激活成功', {icon: 1});
		                        	search_dept();
		                        }else{
		                        	layer.msg('激活失败', {icon: 2});
		                        }
		                    }
		                });		
	        		}, function(){
	        			layer.close();
	        		});
	        }
	        function del(){
	        	var rows = $("#pagination").datagrid("getSelections");
	        	if(rows.length==0){
	        		layer.msg('请选择需要删除的数据!', {icon: 5});
					return;
				}
	        	layer.confirm('确定要删除该部门？\n(该操作将删除当前部门所有内容，包括下属部门及用户)', {
	        		  btn: ['确定','取消'] //按钮
	        		}, function(){
	        			var ids = [];
						for(var i=0;i<rows.length;i++){
							ids.push(rows[i].id);
						}
		                $.ajax({
		                	url:"delDept.action",
		                	type:"post",
		                    dataType:"json",
		                    data:"ids="+ids.join("&ids="),
		                    error:function(){
		                    	parent.$.messager.alert("错误","删除时出错！");
		                    },
		                    success:function(data){
		                        if(data.result){
					        		  layer.msg('删除成功', {icon: 1});
		                        	search_dept();
		                        	$("#deptTree").tree('reload');
		                        }else{
		                        	layer.msg('删除失败', {icon: 2});
		                        }
		                    }
		                });
	        		}, function(){
	        			layer.close();
	        		});
	        	/* parent.$.messager.confirm("删除部门", "确定要删除该部门？\n(该操作将删除当前部门所有内容，包括下属部门及用户)", function(result){
					if(result){
						var ids = [];
						for(var i=0;i<rows.length;i++){
							ids.push(rows[i].id);
						}
		                $.ajax({
		                	url:"delDept.action",
		                	type:"post",
		                    dataType:"json",
		                    data:"ids="+ids.join("&ids="),
		                    error:function(){
		                    	parent.$.messager.alert("错误","删除时出错！");
		                    },
		                    success:function(data){
		                        if(data.result){
		                        	parent.$.messager.alert("提示","删除成功！");
		                        	search_dept();
		                        	$("#deptTree").tree('reload');
		                        }else{
		                        	parent.$.messager.alert("错误","删除时出错！");
		                        }
		                    }
		                });
					}
				}); */
	        }
	        function logic_del_dept(id){
	           	parent.$.messager.confirm('逻辑删除部门', '确定要逻辑删除该部门？\n(该操作将逻辑删除当前部门所有内容，包括下属部门及用户)', function(result){
					if (result){
		                $.ajax({
		                	url : "logicDelDept.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	"dept.id" : id
		                    },
		                    error: function(){
		                    	parent.$.messager.alert('错误','删除部门时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	parent.$.messager.alert('提示','删除部门成功！');
		                        	search_dept();
		                        }else{
		                        	parent.$.messager.alert('错误','删除部门时出错！');
		                        }
		                    }
		                });	
					}
				});
	        }
	        function search_dept(){
	        	
	        	var queryParams = {
					"dept.deptCode" : window.currentDeptCode||"${dept.deptCode}",
					"dept.deptName" : $("#deptName").val(),
					"dept.delFlag" : $("#delFlag").val()
				};        	
	        	$('#pagination').datagrid('options').queryParams = queryParams;
	        	$("#pagination").datagrid('load'); 
	        }
	        
	        $(function(){
	        	$('#deptTree').tree({   
	                url: 'findChildDeptByCurrUser.action', 
	                onBeforeExpand:function(node,param){
	                	console.log(node.id);
	                    $('#deptTree').tree('options').url = "findChildDeptByCurrUser.action?selDept=" + node.id;
                 	},
                 	onClick:function(node){
                 		clear_form(document.myform);
                 		window.currentDeptCode=node.id;
                 		search_dept();
               		}
	            });
    
				$('#pagination').datagrid($.extend(dg_cm_pp,{
					toolbar:[{
						text:'新增',
						iconCls:'icon-add',
						handler:new_dept
					},'-',{
						text:'删除', 
						iconCls:'icon-remove',
						handler:del 
//					},'-',{
//						text:'导出Excel',
//						iconCls:'icon-add',
//						handler:function(){
//							outputExcel($('#pagination'),'部门.xls','', '','', true);
//						}
//					},'-',{
//						text:'导出PDF',
//						iconCls:'icon-add',
//						handler:function(){
//							outputPDF($('#pagination'),'部门.pdf','');
//						}
					}],
					title:'部门列表',
					url:'listDept.action',
					queryParams:{
						"dept.deptCode" : "${dept.deptCode}",
						"dept.deptName" : $("#deptName").val(),
						"dept.delFlag" : $("#delFlag").val()
					},
					columns:[[
							  {field:'id',checkbox:true},
					          {field:'deptName',title:'部门名称',width:fixWidth(0.12)},
					          {field:'deptCode',title:'部门编号',width:fixWidth(0.12)},
							  {field:'parentDept.deptName',title:'上级部门',width:fixWidth(0.1),formatter:function(value,rec){
					        	  if(rec.parentDept!=null)
					        		  return rec.parentDept.deptName;
							  }},
					          {field:'sortSQ',title:'同级排序',width:fixWidth(0.08)},
					          {field:'op',title:'操作',width:fixWidth(0.4),formatter:function(value,rec){
									if(rec.delFlag==0)
										return "<a href='#' class='btn_02_mini' onclick='view_dept(\""+rec.id+"\")'>查看<b></b></a>"
											  +"<a href='#' class='btn_03_mini' onclick='edit_dept(\""+rec.id+"\")'>修改<b></b></a>"
											  +"<a href='#' class='btn_04_mini' onclick='inactive_dept(\""+rec.id+"\")'>禁用<b></b></a>";
									else
										return "<a href='#' class='btn_02_mini' onclick='view_dept(\""+rec.id+"\")'>查看<b></b></a>"
											  +"<a href='#' class='btn_03_mini' onclick='edit_dept(\""+rec.id+"\")'>修改<b></b></a>"
											  +"<a href='#' class='btn_04_mini' onclick='active_dept(\""+rec.id+"\")'>激活<b></b></a>"
							  }}
					        ]]
				}));
			});
	        function getResponse(data){
	        	if(data.status=="y"){
	        		layer.alert("保存成功");  
	        		search_dept();
					//reloadData('edit_user');
	         	 	//closeLayer();
					/* parent.$.messager.alert("成功","保存成功！", "info",function(){
					}); */
				}else{
					layer.alert("保存失败！"+data.info);  
					//parent.$.messager.alert("错误",data.info);
				}
	        }
	    </script>
	</head>

	<body>
		<div class="page_content">
			<div class="layout_01_left">
			<div class="layout_overflow">
				<ul id="deptTree"></ul>
			</div>
			</div>
			<div class="layout_01_right">
			<div class="layout_overflow">
				<div class="inner6px submitdata">
					<div class="boxBmargin12 cell">
						<form name="myform" method="post">
							<input type="hidden" name="deptCode" value="${dept.deptCode}">
							<table>
								<tr>
									<th width="15%">部门名称</th>
									<td width="35%"><input name="dept.deptName" id="deptName" value="${dept.deptName}"></td>
									<th width="15%">是否显示禁用的部门</th>
									<td width="35%">
										<select name="dept.delFlag" id="delFlag">
										    <option value="0" <c:if test="${dept.delFlag==0}">selected</c:if>>否</option>
									 		<option value="1" <c:if test="${dept.delFlag==1}">selected</c:if>>是</option>
										</select>									
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="btn_area_setc">
											<a href="###" class="btn_01" onclick="search_dept();">查询<b></b></a>
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
