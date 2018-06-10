<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Activiti模型管理</title>
		<%@include file="/common/jsLib.jsp"%>
		<script type="text/javascript">		
        function reloadDate(){
        	doQuery();
        }
		function addModel(){
			createSimpleWindow("add_model","新建模型","${ctx}/activiti/initAddModel.action", 600, 400);
		}
		function deployModel(modelId,isFormkey,formkeyArrayStr){
        	$.messager.confirm('部署模型', '确定要部署此模型？', function(result){
				if (result){
					if(true == isFormkey){//需要上传.form文件的情况
						var formkeyArray = new Array();
						formkeyArray = formkeyArrayStr.split("|");
						var param = "";
						for(var i=0;i<formkeyArray.length;i++){
							if(""!=formkeyArray[i]){
								if("" == param){
									param = "formkeyArray="+formkeyArray[i];
								}else{
									param += "&formkeyArray="+formkeyArray[i];
								}						
							}
						}
						createSimpleWindow("initDeployModelForFormkey_window","上传.form文件","${ctx}/activiti/initDeployModelForFormkey.action?"+param+"&modelId="+modelId, 600, 400);
					}else{//无.form文件上传情况
						$.ajax({
	                		url : "deployModel.action",
	                		type: 'post',
	                    	dataType: 'json',
	                    	async : false,
	                    	data: {"modelId" : modelId},
	                    	error: function(){
	                    		$.messager.alert('错误','模型部署时出错！');
	                   	 	},
	                    	success: function(data){
	                       	 	if(data.result){
	                        		$.messager.alert("提示","模型部署成功！","info",function(){
	                        			doQuery();
	                        		});
	                       	 	}else{
	                        		$.messager.alert('错误','模型部署时出错！');
	                    	 	}
	                	    }
	                	});					
					}			
				}
			});	
		}
		function deleteModel(modelId){
			var id = [];
			var rows = $('#pagination').datagrid('getSelections');
			for(var i=0;i<rows.length;i++){
				id.push(rows[i].id);
			}
			
			if(0 == rows.length){
				$.messager.alert('警告','请选择要删除的条目!','warning');
			}else{
	        	$.messager.confirm('删除模型', '确定要删除模型？', function(result){
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
		                	url : "deleteModel.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data: paraIds,
		                    error: function(){
		                    	$.messager.alert('错误','模型删除时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','模型删除成功！','info',function(){
		                        		doQuery();
		                        	});
		                        }else{
		                        	$.messager.alert('错误','模型删除时出错！');
		                        }
		                    }
		                });							
					}
				});					
			}			
		}
		function doQuery(){
			var queryParams = {
				"modelKey" : $("#modelKey").val(),
				"modelName" : $("#modelName").val()					
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        $(function(){
			$('#pagination').datagrid($.extend(dg_cm_pp,{
				toolbar:[{
					text:'新建模型',
					iconCls:'icon-add',
					handler:addModel
				},'-',{
					text:'删除',
					iconCls:'icon-remove',
					handler:deleteModel
				}],			
				title:'Activiti模型管理',
				url:'findModelList.action',
				fitColumns:false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],						
				columns:[[
				          {field:'key',title:'标识',width:fixWidth(0.15)},
				          {field:'name',title:'名称',width:fixWidth(0.15)},
				          {field:'category',title:'分类',width:fixWidth(0.1)},
				          {field:'deploymentId',title:'发布编号',width:fixWidth(0.1)},
				          {field:'metaInfo',title:'元信息',width:fixWidth(0.4)},
				          {field:'createTime',title:'创建时间',width:fixWidth(0.15),formatter:function(value,rec){
								if(rec.createTime==null) return;
								var date = new Date(rec.createTime.time);
								var retStr = date.format("yyyy-MM-dd hh:mm:ss");
								return retStr;
						  }},						  			          
				          {field:'version',title:'版本号',width:fixWidth(0.05)},
				          {field:'lastUpdateTime',title:'最后更新时间',width:fixWidth(0.15),formatter:function(value,rec){
								if(rec.lastUpdateTime==null) return;
								var date = new Date(rec.lastUpdateTime.time);
								var retStr = date.format("yyyy-MM-dd hh:mm:ss");
								return retStr;
						  }},
						  {field:'isFormkey',title:'外部表单',width:fixWidth(0.05),formatter:function(value,rec){
								if(rec.isFormkey==true){
									return "是";
								}else{
									return "否";
								}
						  }},
						  
						  {field:'formkeyArrayStr',title:'外部表单项',width:fixWidth(0.2),formatter:function(value,rec){
						  		var retStr = "";
						  		var formkeyArray = new Array();
						  		formkeyArray = rec.formkeyArrayStr.split("|");
						  		for(var i=0;i<formkeyArray.length;i++){
						  			retStr += "<div>"+ formkeyArray[i] +"</div>"; 
						  		}
								return retStr;
						  }},
				          {field:'op',title:'操作',width:fixWidth(0.2),formatter:function(value,rec){
				          		return "<a href='${ctx}/activiti-modeler/service/editor?id="+rec.id+"' target='_blank' class='btn_01_mini'>编辑<b></b></a>" + 
				          			   "<a href='${ctx}/activiti/exportModelXml.action?modelId="+ rec.id +"' target='_blank' class='btn_01_mini'>导出XML<b></b></a>" + 
				          			   "<a href='###' onclick='deployModel("+ rec.id +","+ rec.isFormkey +",\""+ rec.formkeyArrayStr +"\");' class='btn_01_mini'>部署<b></b></a>";
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
									<th width="15%">标识（精确查询）</th>
									<td width="35%"><input type="text" id="modelKey" name="modelKey"></td>
									<th width="15%">模型名称</th>
									<td width="35%"><input type="text" id="modelName" name="modelName"></td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="btn_area_setc">
											<a href="###" class="btn_01" onclick="doQuery()" >查询<b></b></a>
											<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>				
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