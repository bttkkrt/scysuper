<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Activiti待签收任务</title>
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
		            	url : "claimTask.action",
		            	type: 'post',
		                dataType: 'json',
		                async : false,
		                data: {
		                	"taskId" : taskId
		                },
		                error: function(){
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
        $(function(){
			$('#pagination').datagrid($.extend(dg_cm_pp,{
				title:'Activiti待签收任务',
				url:'findToClaimTaskList.action',
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],	
				columns:[[
				          {field:'name',title:'任务名称',width:fixWidth(0.15)},
				          {field:'createTime',title:'创建时间',width:fixWidth(0.15),formatter:function(value,rec){
								if(rec.createTime==null) return;
								var date = new Date(rec.createTime.time);
								var retStr = date.format("yyyy-MM-dd hh:mm:ss");
								return retStr;
						  }},				          
				          {field:'description',title:'描述',width:fixWidth(0.15)},
				          {field:'dueDate',title:'过期时间',width:fixWidth(0.15),formatter:function(value,rec){
								if(rec.dueDate==null) return;
								var date = new Date(rec.dueDate.time);
								var retStr = date.format("yyyy-MM-dd hh:mm:ss");
								return retStr;
						  }},						  			          
				          {field:'op',title:'操作',width:fixWidth(0.2),formatter:function(value,rec){
				          		return "<a href='#' class='btn_01_mini' onclick=\"doClaimTask('"+rec.id+"')\">签收<b></b></a>";
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
