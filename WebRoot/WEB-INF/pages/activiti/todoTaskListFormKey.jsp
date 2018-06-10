<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>外部表单流程待办</title>
		<%@include file="/common/jsLib.jsp"%>
		<script type="text/javascript">		
        function reloadDate(){
        	doQuery();
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
		function doQuery(){
			var queryParams = {
				
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
		function doClaimTask(taskId){
			$.messager.confirm('签收任务', '确定要签收该任务？', function(result){
				if (result){
					$.ajax({
		            	url: '${ctx}/activiti/claimTask.action',
		            	type: 'post',
		                dataType: 'json',
		                async : false,
		                data: {
		                	"taskId" : taskId
		                },
		                error: function(error){
		                	$.messager.alert('错误','签收任务时出错！');
		                },
		                success: function(data){
		                    if(data.result){
		                    	$.messager.alert('提示','签收任务成功！','info',function(){
		                    		doQuery();
		                    	});
		                    }else{
		                    	$.messager.alert('错误','签收任务时出错！');
		                    }
		                }
		            });					
				}
			});		
		}  
		function doCompleteTask(taskId){
			$.messager.confirm('办理', '确定要办理？', function(result){
				if (result){
					if (result){
						createSimpleWindow("win_agencyInfo","","${ctx}/activiti/initCompleteTaskFormKey.action?taskId="+taskId,900,600);
						/* var location = getCenterLocation(380,320);
						openparentWindow("newWindow","办理任务",location.left,location.top,"400","380","${ctx}/activiti/initCompleteTaskFormKey.action?taskId="+taskId,true,true,true,false,true,"win");					
					 */}		
				}
			});	
		}      
        $(function(){
			$('#pagination').datagrid($.extend(dg_cm_pp,{
				title:'外部表单流程待办',
				url:'findTodoTaskListFormKey.action',
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],					
				columns:[[
				          {field:'taskDefinitionKey',title:'任务定义KEY',width:fixWidth(0.15)},
				          {field:'description',title:'描述',width:fixWidth(0.15)},
				          {field:'createTime',title:'创建时间',width:fixWidth(0.15),formatter:function(value,rec){
								if(rec.createTime==null) return;
								var date = new Date(rec.createTime.time);
								var retStr = date.format("yyyy-MM-dd hh:mm:ss");
								return retStr;
						  }},				          
				          {field:'description',title:'描述',width:fixWidth(0.15)},
				          {field:'claimTime',title:'签收时间',width:fixWidth(0.15),formatter:function(value,rec){
								if(rec.claimTime==null) return;
								var date = new Date(rec.claimTime.time);
								var retStr = date.format("yyyy-MM-dd hh:mm:ss");
								return retStr;
						  }},				          
				          {field:'dueDate',title:'过期时间',width:fixWidth(0.15),formatter:function(value,rec){
								if(rec.dueDate==null) return;
								var date = new Date(rec.dueDate.time);
								var retStr = date.format("yyyy-MM-dd hh:mm:ss");
								return retStr;
						  }},
						  {field:'leaveInfoId',title:'业务ID',width:fixWidth(0.15)},	
				          {field:'op',title:'操作',width:fixWidth(0.2),formatter:function(value,rec){
				          		if(rec.assignee==null || rec.assignee==""){
				          			return "<a href='#' onclick=\"doClaimTask('"+rec.id+"')\" class='btn_01_mini'>签收<b></b></a>";
				          		}else{
				          			return "<a href='#' onclick=\"doCompleteTask('"+rec.id+"')\" class='btn_01_mini'>办理(外置表单)<b></b></a>";
				          		}
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
					<div id="pagination"></div>
				</div>
			</div>
		</div>
	</body>
</html>