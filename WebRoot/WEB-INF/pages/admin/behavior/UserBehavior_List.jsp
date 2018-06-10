<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户行为管理</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>        
        function addNew(){
        	var dt = new Date();
    		createSimpleWindow("behavoirWindow","添加用户行为","${ctx}/jsp/admin/behavior/userBehaviorInitEdit.action?flag=add&dt="+dt.getTime(), 600, 400);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
        	createSimpleWindow("behavoirWindow","修改用户行为","${ctx}/jsp/admin/behavior/userBehaviorInitEdit.action?flag=mod&userBehavior.id="+row_Id+"&dt="+dt.getTime(), 600, 400);
        	
        }
        function view(row_Id){
        	var dt=new Date();
        	createSimpleWindow("behavoirWindow","查看用户行为","${ctx}/jsp/admin/behavior/userBehaviorView.action?userBehavior.id="+row_Id+"&dt="+dt.getTime(), 600, 400);
        	
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
		                	url : "userBehaviorDel.action",
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
		                        	search_userBehavior();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_userBehavior(){
        	var queryParams = {
				"userBehavior.behaviorType": $("#behaviorType").val(),
				"userBehavior.behaviorName": $("#behaviorName").val(),
				"userBehavior.behaviorUrl": $("#behaviorUrl").val(),
				"userBehavior.isContinue": $("#isContinue").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
			$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'用户行为列表',
				url:'userBehaviorQuery.action',
				toolbar:[{
					text:'新建',
					iconCls:'icon-add',
					handler:addNew
				},{
					text:'删除',
					iconCls:'icon-remove',
					handler:del
				}],
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
					        {field:'behaviorType',title:'类别',width:0.1},
							{field:'behaviorName',title:'用户行为名称',width:0.1},
							{field:'behaviorUrl',title:'用户行为地址',width:0.3},
							{field:'behaviorService',title:'用户行为处理服务',width:0.2},
							{field:'isContinue',title:'是否到指定的URL',width:0.1},
					        {field:'op',title:'操作',width:0.1,formatter:function(value,rec){
					        	return "<a href='#' onclick=\"view('"+rec.id+"')\" class='btn_01_mini'>查看<b></b></a><a href='#' onclick=\"edit('"+rec.id+"')\" class='btn_01_mini'>修改<b></b></a>";
		                    }}
				        ]],
			}));
		});
    </script>
	</head>

	<body>
		<div class="page_content">
			<div class="box_01 submitdata">
				<div class="inner12px">
					<form name="myform" method="post">
						<div class="cell boxBmargin12">
							<table width="100%">
								<tr>
									<th width="15%">
										类别
									</th>
									<td width="35%">
										<cus:SelectOneTag property="userBehavior.behaviorType"
											defaultText='请选择' codeName="用户行为类别"
											value="${userBehavior.behaviorType}" />
									</td>
									<th width="15%">
										用户行为名称
									</th>
									<td width="35%">
										<input name="userBehavior.behaviorName" id="behaviorName"
											class="form_text" value="${userBehavior.behaviorName}"
											type="text">
									</td>
								</tr>
								<tr>
									<th width="15%">
										用户行为地址
									</th>
									<td width="35%">
										<input name="userBehavior.behaviorUrl" id="behaviorUrl"
											class="form_text" value="${userBehavior.behaviorUrl}"
											type="text">
									</td>
									<th width="15%">
										是否到指定的URL
									</th>
									<td width="35%">
										<cus:SelectOneTag property="userBehavior.isContinue"
											defaultText='请选择' codeName="是或否"
											value="${userBehavior.isContinue}" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="btn_area_setc">
											<a href="###" class="btn_01" onclick="search_userBehavior()">查询<b></b>
											</a>
											<a href="###" class="btn_01"
												onclick="clear_form(document.myform);">清空<b></b> </a>
										</div>
									</td>
								</tr>
							</table>
						</div>
						<div id="pagination">
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
