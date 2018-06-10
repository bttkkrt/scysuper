<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户管理</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
        function reloadDate(){
        	$("#userGrid").datagrid('reload'); 
        }
        function view_user(id){
        	createSimpleWindow("view_user","查看用户信息详情","${ctx}/jsp/admin/user/viewUser.action?user.id="+id, 450, 450);
        }
        function set_userLinkedDept(id){
        	createSimpleWindow("set_userLinkedDept","设置用户关联部门","${ctx}/jsp/admin/user/deptTree.action?userId="+id, 300, 400);
        }
        function new_user(){
        	createSimpleWindow("edit_user","新增用户信息详情","${ctx}/jsp/admin/user/editUser.action?deptCode="+(window.currentDeptCode||"${user.deptCode}"), 450, 450);
        }
       	function edit_user(id){
       		createSimpleWindow("edit_user","修改用户信息详情","${ctx}/jsp/admin/user/editUser.action?user.id="+id, 450, 450);
        }
        function editUserRight(id){ 
        	createSimpleWindow("edit_user_right","用户角色设置","${ctx}/jsp/admin/user/editUserRight.action?userId="+id, 650, 470);
        }
        function importUser(){ 
        	if(!window.currentDeptCode){
        		$.messager.alert('错误','请先选择导入的部门！');
        		return false;
        	}
        	createSimpleWindow("importUser","批量导入用户","${ctx}/jsp/admin/user/initImportUser.action?deptId="+window.currentDeptCode, 350, 150);
        }
        function del(){
        	var rows = $("#pagination").datagrid("getSelections");
        	if(rows.length==0){
        		layer.msg('请选择需要删除的数据!', {icon: 0});
				return;
			}
        	layer.confirm('确定要删除该用户？', {
      		  btn: ['确定','取消'] //按钮
      		}, function(){
					var ids = [];
					for(var i=0;i<rows.length;i++){
						ids.push(rows[i].id);
					}
	                $.ajax({
	                	url:"delUser.action",
	                	type:"post",
	                    dataType:"json",
	                    data:"ids="+ids.join("&ids="),
	                    error:function(){
	                    	layer.msg('删除时出错！', {icon: 2});
	                    },
	                    success:function(data){
	                        if(data.result){
	                        	layer.msg('删除成功', {icon: 1});
	                        	search_user();
	                        }else{
	                        	layer.msg('删除时出错！', {icon: 2});
	                        }
	                    }
	                });
      		}, function(){
      		  return;
      		});
        	
        }
        function logic_del_user(id){
        	$.messager.confirm('逻辑删除用户', '该用户所有相关数据均会清除，确定要删除该用户？', function(result){
				if (result){
	                $.ajax({
	                	url : "logicDelUser.action",
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    data:{ 
	                    	"user.id" : id
	                    },
	                    error: function(){
	                    	$.messager.alert('错误','删除用户时出错！');
	                    },
	                    success: function(data){
	                        if(data.result){
	                        	$.messager.alert('提示','删除用户成功！');
	                        	search_user();
	                        	
	                        }else{
	                        	$.messager.alert('错误','删除用户时出错！');
	                        }
	                    }
	                });					
				}
			});
        	search_user();
        }
        function inactive_user(id){
        	$.messager.confirm('禁用用户', '确定要禁用该用户？', function(result){
				if (result){
	                $.ajax({
	                	url : "inactiveUser.action",
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    data:{ 
	                    	"user.id" : id
	                    },
	                    error: function(){
	                    	$.messager.alert('错误','禁用用户时出错！');
	                    },
	                    success: function(data){
	                        if(data.result){
	                        	$.messager.alert('提示','禁用用户成功！');
	                        	search_user();
	                        }else{
	                        	$.messager.alert('错误','禁用用户时出错！');
	                        }
	                    }
	                });					
				}
			});
        }
        function active_user(id){
           	$.messager.confirm('激活用户', '确定要激活该用户？', function(result){
				if (result){
	                $.ajax({
	                	url : "activeUser.action",
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    data:{ 
	                    	"user.id" : id
	                    },
	                    error: function(){
	                    	$.messager.alert('错误','激活用户时出错！');
	                    },
	                    success: function(data){
	                        if(data.result){
	                        	$.messager.alert('提示','激活用户成功！');
	                        	search_user();
	                        }else{
	                        	$.messager.alert('错误','激活用户时出错！');
	                        }
	                    }
	                });	
				}
			});
        }
       function search_user(){
        	var queryParams = {
				"user.deptCode" : window.currentDeptCode||"${user.deptCode}",
				"user.loginId" : $("#loginId").val(),
				"user.displayName" : $("#displayName").val(),
				"user.delFlag" : $("#delFlag").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$("#pagination").datagrid('load'); 
        }
       
        window.autoDatagridHeight = <%=(String)session.getAttribute("autoDatagridHeight")%>;
        $(function(){
        	$('#deptTree').tree({   
                url: '${ctx}/jsp/admin/dept/findChildDeptByCurrUser.action', 
                onBeforeExpand:function(node){
                    $('#deptTree').tree('options').url = "${ctx}/jsp/admin/dept/findChildDeptByCurrUser.action?selDept=" + node.id;
            	},
                onClick:function(node){
                	clear_form(document.myform);
					window.currentDeptCode=node.id;
					search_user();
              	}
            });

			$('#pagination').datagrid($.extend(dg_cm_pp,{
				toolbar:[{
					text:'新增',
					iconCls:'icon-add',
					handler:new_user
				},'-',{
					text:'删除',
					iconCls:'icon-remove',
					handler:del
				},'-',{
					text:'导出Excel',
					iconCls:'icon-add',
					handler:function(){
						outputExcel($('#pagination'),"系统用户.xls","","","",true);
					}
				},'-',{
					text:'导出PDF',
					iconCls:'icon-add',
					handler:function(){
						outputPDF($('#pagination'),'系统用户.pdf');
					}
				},'-',{
					text:'导入用户',
					iconCls:'icon-add',
					handler:importUser
				}],
				title:'人员列表',
				url:'listUser.action',
				queryParams:{
					"user.deptCode" : "${user.deptCode}",
					"user.loginId" : $("#loginId").val(),
					"user.displayName" : $("#displayName").val(),
					"user.delFlag" : $("#delFlag").val()
				},
				columns:[[
						  {field:'id',checkbox:true},
				          {field:'displayName',title:'姓名',width:fixWidth(0.2)},
				          {field:'loginId',title:'用户名',width:fixWidth(0.1)},
				          {field:'dept.deptName',title:'所属部门',width:fixWidth(0.1),formatter:function(value,rec){
				        	  return rec.dept.deptName;
						  }},
				          {field:'duty',title:'职位',width:fixWidth(0.1)},
				          {field:'mobile',title:'手机',width:fixWidth(0.1)},
				          {field:'op',title:'操作',width:fixWidth(0.3),formatter:function(value,rec){
				          		var retStr = "<a href='#' class='btn_02_mini' onclick='view_user(\""+rec.id+"\")'>查看<b></b></a>"
				          					+"<a href='#' class='btn_03_mini' onclick='edit_user(\""+rec.id+"\")'>修改<b></b></a>";	
								if(rec.delFlag==0){
									//retStr += "<a href='#' class='btn_01_mini' onclick='inactive_user(\""+rec.id+"\")'>禁用<b></b></a>"
											 //+"<a href='#' class='btn_01_mini' onclick='logic_del_user(\""+rec.id+"\")'>逻辑删除<b></b></a>";
								}else{ 
					  				//retStr += "<a href='#' class='btn_01_mini' onclick='active_user(\""+rec.id+"\")'>激活<b></b></a>"
					  						 //+"<a href='#' class='btn_01_mini' onclick='logic_del_user(\""+rec.id+"\")'>逻辑删除<b></b></a>";
						  		}
						  		//retStr += "<a href='#' class='btn_01_mini' onclick='del_user(\""+rec.id+"\")'>删除<b></b></a>";
						  		retStr += "<a href='#' class='btn_04_mini' onclick='editUserRight(\""+rec.id+"\")'>角色设置<b></b></a>";
						  		//retStr += "<a href='#' class='btn_01_mini' onclick='set_userLinkedDept(\""+rec.id+"\")'>设置关联部门<b></b></a>";
						  		//set_userLinkedDept(id)
						  		
						  		return retStr;
						  }}
				        ]]
			}));
		});
        
        function getResponse(data){
        	if(data.status=="y"){
        		layer.alert("保存成功");  
        		search_user();
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
				<ul id="deptTree">
				</ul>
			</div>
			</div>
			<div class="layout_01_right">
			<div class="layout_overflow">
				<div class="inner6px submitdata">
					<div class="boxBmargin12 cell">
						<form name="myform" method="post">
							<input type="hidden" name="deptId" value="${deptId}">
							<input type="hidden" name="user.deptCode" value="${user.deptCode}">
							<table>
								<tr>
									<th width="15%">用户名</th>
									<td width="35%"><input class="form_text" name="user.loginId" id="loginId" value="${user.loginId}"></td>
									<th width="15%">姓名</th>
									<td width="35%"><input class="form_text" name="user.displayName" id="displayName" value="${user.displayName}"></td>
								</tr>
								<tr>
									<th width="15%">是否显示禁用的用户</th>
									<td width="35%" colspan="3">
										<select name="user.delFlag" id="delFlag">
											<option value="0" <c:if test="${user.delFlag==0}">selected</c:if>>否</option>
											<option value="1" <c:if test="${user.delFlag==1}">selected</c:if>>是</option>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="btn_area_setc">
											<a href="###" class="btn_01" onclick="clear_form(document.myform);">清空<b></b></a>
											<a href="###" class="btn_01" onclick="search_user();">查询<b></b></a>
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
