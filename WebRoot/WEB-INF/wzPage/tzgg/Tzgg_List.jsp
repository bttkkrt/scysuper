<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>通知公告管理</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
        function addNew(){
        	createSimpleWindow("win_tzgg","添加通知公告","${ctx}/jsp/tzgg/tzggInitEdit.action?flag=add", 1050, 550);
        }
        function edit(row_Id){
        	createSimpleWindow("win_tzgg","修改通知公告","${ctx}/jsp/tzgg/tzggInitEdit.action?flag=mod&tzgg.id="+row_Id, 1050, 550);
        }
        function view(row_Id){
        	createSimpleWindow("win_tzgg","查看通知公告","${ctx}/jsp/tzgg/tzggView.action?tzgg.id="+row_Id, 1050, 550);
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
		                	url : "tzggDel.action",
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
		                        	search_tzgg();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_tzgg(){
        	var queryParams = {
					"tzgg.infoTitle": $("#infoTitle").val(),
					"username": $("#username").val(),
					"queryPublicDateStart" :$("#queryPublicDateStart").val(),
					"queryPublicDateEnd" :$("#queryPublicDateEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        $(function(){
			$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'通知公告列表',
				url:'tzggQuery.action',
				queryParams:{
					"tzgg.infoTitle": $("#infoTitle").val(),
					"username": $("#username").val(),
					"queryPublicDateStart" :$("#queryPublicDateStart").val(),
					"queryPublicDateEnd" :$("#queryPublicDateEnd").val()
				},
				frozenColumns: [[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				    {field:'infoTitle',title:'标题',width:0.3,formatter:function(value,rec){
			             if(rec.infoTitle.length>20){
			                var temp=rec.infoTitle.substr(0,20)+'...';
			             	return "<span title="+rec.infoTitle+">"+temp+"</span>";
			             }	
			             else{
			                var temp1=rec.infoTitle;
			              	return "<span title="+rec.infoTitle+">"+temp1+"</span>";
			             }  
				    }},
					{field:'userId',title:'发布人',width:0.1,formatter:function(value,rec){
						return rec.user.displayName;
					}},
					{field:'deptId',title:'所属部门',width:0.1,formatter:function(value,rec){
						return rec.dept.deptName;
					}},
					{field:'publicDate',title:'发布日期',width:0.2,formatter:function(value,rec){
						return value.substring(0,10);						
					}},
		            {field:'op',title:'操作',width:0.2,formatter:function(value,rec){
		            	var b="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>&nbsp;<a class='btn_04_mini' onclick=move('"+rec.id+"')>移动<b></b></a>";
		            	if(rec.topTime=='2001-01-01 00:00:01'){
		            		b+="&nbsp;<a class='btn_05_mini' onclick=toTop('"+rec.id+"')>置顶<b></b></a>";
		            	}else{
		            		b+="&nbsp;<a class='btn_05_mini' onclick=cancelTop('"+rec.id+"')>取消置顶<b></b></a>";
		            	}
                      return b;
                    }}
				]],
				toolbar:[{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						addNew();
					}
				},{
					id:'btncut',
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						del();
					}
				}]
			}));
		});
		
		function move(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_wzInfoManage","移动通知公告信息","${ctx}/jsp/wzInfoManage/wzInfoManageMove.action?flag=move&type=1&ids="+row_Id+"&dt="+dt.getTime(),500,150);
        	
        }
        
        function toTop(id){
        	$.messager.confirm("置顶","确定要置顶吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "tzggToTop.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : id
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','置顶时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','置顶成功！');
		                        	search_tzgg();
		                        }else{
		                        	$.messager.alert('错误','置顶时出错！');
		                        }
		                    }
		                });
			        }
			    });
        }
        
        function cancelTop(id){
        	$.messager.confirm("取消置顶","确定要取消置顶吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "tzggToTopCancel.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : id
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','取消置顶时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','取消置顶成功！');
		                        	search_tzgg();
		                        }else{
		                        	$.messager.alert('错误','取消置顶时出错！');
		                        }
		                    }
		                });
			        }
			    });
        }
    </script>
	</head>

	<body>
		<div class="page_content">
			<div class="box_01 submitdata">
				<div class="inner12px">
					<form name="myform" method="post">
						<div class="cell boxBmargin12">
							<table>
								<tbody>
									<tr>
										<th width="15%">
											标题
										</th>
										<td width="35%">
											<input name="tzgg.infoTitle" id="infoTitle"
												class="form_text" value="${tzgg.infoTitle}"
												type="text" maxlength="127" style="width:50%">
										</td>
										<th width="15%">
											发布人
										</th>
										<td width="35%">
											<input name="username" id="username" value="${username}"
												class="form_text" type="text" maxlength="127" style="width:50%">
										</td>
									</tr>
									<tr>
										<th width="15%">
											发布日期
										</th>
										<td width="35%">
											<input style="width:35%;" name="queryPublicDateStart" id="queryPublicDateStart"
												value="<fmt:formatDate type='date' value='${queryPublicDateStart}' />"
												type="text" class="Wdate"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryPublicDateEnd\')}'})">
											-
											<input style="width:35%;" name="queryPublicDateEnd" id="queryPublicDateEnd"
												value="<fmt:formatDate type='date' value='${queryPublicDateEnd}' />"
												type="text" class="Wdate"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryPublicDateStart\')}'})">
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<div class="btn_area_setc">
												<a href="###" class="btn_01"
													onclick="search_tzgg()">查询<b></b> </a>
												<a href="###" class="btn_01"
													onclick="clear_form(document.myform);">清空<b></b> </a>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div id="pagination">
						</div>
					</form>
				</div>
				
			</div>
		</div>
		</div>
	</body>
</html>
