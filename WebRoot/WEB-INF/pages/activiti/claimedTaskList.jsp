<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Activiti办理中任务</title>
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
        $(function(){
			$('#pagination').datagrid($.extend(dg_cm_pp,{
				title:'Activiti办理中任务',
				url:'findClaimedTaskList.action',
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
				          {field:'op',title:'操作',width:fixWidth(0.2),formatter:function(value,rec){
				          		//TODO:根据不同的任务名称实现不同的操作
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