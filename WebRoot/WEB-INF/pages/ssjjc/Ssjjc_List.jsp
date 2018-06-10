<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>双随机检查管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_ssjjc","添加双随机检查","${ctx}/jsp/ssjjc/ssjjcInitEdit.action?dt="+dt.getTime(),1000,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_ssjjc","查看双随机检查","${ctx}/jsp/ssjjc/ssjjcView.action?ssjjc.id="+row_Id+"&dt="+dt.getTime(),1000,500);
        	
        }
        
        function createTask(row_Id)
        {
        	var dt=new Date();
            createSimpleWindow("win_ssjjc","生成临时检查任务","${ctx}/jsp/ssjjc/ssjjcTaskInitEdit.action?ssjjc.id="+row_Id+"&dt="+dt.getTime(),1000,500);
        }
        
        function viewTask(row_Id)
        {
        	var id="newWindow";
    		var text = "查看关联任务";
    		var url ="jsp/jdjcjh/supPlaLink.action?supPla.id="+row_Id;
             window.parent.addTab(id,text,url);
        }
        
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_ssjjc();
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
		                	url : "ssjjcDel.action",
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
		                        	search_ssjjc();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_ssjjc(){
        	var queryParams = {
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        
 var toolbar = [];
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'双随机检查列表',
				url:'ssjjcQuery.action',
				queryParams:{
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'jcsj',title:'检查时间',width:100,formatter:function(value,rec){
	return value.substring(0,11);
}},
{field:'jcbl',title:'检查企业比例',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	if(rec.ifrw == '1')
	{
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_02_mini' onclick=viewTask('"+rec.id+"') >查看任务<b></b></a>";
	}
	else
	{
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_02_mini' onclick=createTask('"+rec.id+"') >生成任务<b></b></a>";
	}

}}				        ]],
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

        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
		
		<div id="pagination" >
		</div>
		</div>
		</div>
	</div>
</body>
</html>
