<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Activiti工作流管理</title>
		<%@include file="/common/jsLib.jsp"%>
		<script type="text/javascript">		
        function reloadDate(){
        	doQuery();
        }
		function doDeploy(){
			createSimpleWindow("deploy_process","部署流程","${ctx}/activiti/initDeploy.action", 400, 250);
		}
		function deleteProceeDef(){
			var id = [];
			var rows = $('#pagination').datagrid('getSelections');
			for(var i=0;i<rows.length;i++){
				id.push(rows[i].deploymentId);
			}
		
			if(0 == rows.length){
				$.messager.alert('警告','请选择要删除的条目!','warning');
			}else{
	        	$.messager.confirm('删除流程', '确定要删除流程？它会级联删除流程实例', function(result){
					if (result){
						var paraIds = "";
						for(var i=0;i<rows.length;i++){
							if("" == paraIds){
								paraIds = "id="+id[i];
							}else{
								paraIds += "&id="+id[i];
							}
						}
		                $.ajax({
		                	url : "deleteProcessDef.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data: paraIds,
		                    error: function(){
		                    	$.messager.alert('错误','删除流程定义时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','删除流程定义成功！','info',function(){
		                        		doQuery();
		                        	});
		                        }else{
		                        	$.messager.alert('错误','删除流程定义时出错！');
		                        }
		                    }
		                });							
					}
				});					
			}			
		}
		function startProcessInstance(processDefinitionId){
			createSimpleWindow("start_process","启动流程","${ctx}/activiti/initStartProcessInstance.action?processDefinitionId="+processDefinitionId, 600, 400);
		}
		function suspendProcessDefinition(processDefinitionId){
			$.messager.confirm('挂起流程', '确定要挂起该流程（挂起后这个流程定义不能再创建流程实例）？', function(result){
				if (result){
					$.ajax({
		            	url : "suspendProcessDefinition.action",
		            	type: 'post',
		                dataType: 'json',
		                async : false,
		                data: {
		                	"processDefinitionId" : processDefinitionId
		                },
		                error: function(){
		                	$.messager.alert('错误','挂起流程时出错！');
		                },
		                success: function(data){
		                    if(data.result){
		                    	$.messager.alert('提示','流程挂起成功！','info',function(){
		                    		doQuery();
		                    	});
		                    }else{
		                    	$.messager.alert('错误','挂起流程时出错！');
		                    }
		                }
		            });					
				}
			});				
		}
		function activateProcessDefinition(processDefinitionId){
			$.messager.confirm('激活流程', '确定要激活该流程？', function(result){
				if (result){
					$.ajax({
		            	url : "activateProcessDefinition.action",
		            	type: 'post',
		                dataType: 'json',
		                async : false,
		                data: {
		                	"processDefinitionId" : processDefinitionId
		                },
		                error: function(){
		                	$.messager.alert('错误','激活流程时出错！');
		                },
		                success: function(data){
		                    if(data.result){
		                    	$.messager.alert('提示','流程激活成功！','info',function(){
		                    		doQuery();
		                    	});
		                    }else{
		                    	$.messager.alert('错误','激活流程时出错！');
		                    }
		                }
		            });					
				}
			});				
		}		
		function doQuery(){
			var queryParams = {
				"processDefKey" : $("#processDefKey").val(),
				"processDefName" : $("#processDefName").val()				
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        $(function(){
			$('#pagination').datagrid($.extend(dg_cm_pp,{
				toolbar:[{
					text:'部署流程',
					iconCls:'icon-add',
					handler:doDeploy
				},'-',{
					text:'删除已部署的流程',
					iconCls:'icon-remove',
					handler:deleteProceeDef
				}],			
				title:'Activiti流程定义列表',
				url:'findProcessDefList.action',
				fitColumns:false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],					
				columns:[[
				          {field:'deploymentId',title:'部署编号',width:fixWidth(0.05)},
				          {field:'key',title:'流程编号KEY',width:fixWidth(0.15)},
				          {field:'name',title:'流程名称',width:fixWidth(0.15)},
				          {field:'deploymentTime',title:'发布时间',width:fixWidth(0.15),formatter:function(value,rec){
								if(rec.deploymentTime==null) return;
								var date = new Date(rec.deploymentTime.time);
								var retStr = date.format("yyyy-MM-dd hh:mm:ss");
								return retStr;
						  }},						  			          
				          {field:'version',title:'版本号',width:fixWidth(0.05)},
				          {field:'category',title:'流程命令空间',width:fixWidth(0.25)},
				          {field:'resourceName',title:'资源文件名称',width:fixWidth(0.2)},
				          {field:'description',title:'描述',width:fixWidth(0.15)},
				          {field:'diagramResourceName',title:'图片资源文件名称',width:fixWidth(0.25)},
				          {field:'hasStartFormKey',title:'是否有启动外部表单',width:fixWidth(0.15),formatter:function(value,rec){
				                if(rec.hasStartFormKey){
				                	return "是";
				                }else{
				                	return "否";
				                }	 
				                
				          }},
				          {field:'isSuspended',title:'挂起状态',width:fixWidth(0.06),formatter:function(value,rec){
				                if(rec.isSuspended){
				                	return "是";
				                }else{
				                	return "否";
				                }	 
				                
				          }},				          
				          {field:'op',title:'操作',width:fixWidth(0.15),formatter:function(value,rec){
                        		var restr="";	
                        		if(rec.isSuspended){
                        			restr="<a href='#' onclick=\"activateProcessDefinition('"+rec.id+"')\" class='btn_01_mini'>激活<b></b></a>";
                        		}else{
                        			restr="<a href='#' onclick=\"suspendProcessDefinition('"+rec.id+"')\" class='btn_01_mini'>挂起<b></b></a>";
                        		}	
                        		
                        		if(rec.hasStartFormKey){
                        			restr+="<a href='#' onclick=\"startProcessInstance('"+rec.id+"')\" class='btn_01_mini'>启动(外部表单)<b></b></a>";
                        		}      
                        		
                        		return restr;	    
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
					<form name="myform" method="post">
						<div class="cell boxBmargin12">
							<table>
								<tr>
									<th width="15%">流程编号KEY</th>
									<td width="35%"><input type="text" id="processDefKey" name="processDefKey"></td>
									<th width="15%">流程名称</th>
									<td width="35%"><input type="text" id="processDefName" name="processDefName"></td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="btn_area_setc">
											<a href="###" class="btn_01" onclick="doQuery()">查询<b></b></a>
											<a href="###" class="btn_01" onclick="clear_form(document.myform);">清空<b></b></a>
										</div>									
									</td>
								</tr>									
							</table>
						</div>
						<div id="pagination"></div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>