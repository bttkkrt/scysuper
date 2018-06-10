<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>先行登记保存证据清单关联表管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_inventoryAssociate","添加先行登记保存证据清单关联表","${ctx}/jsp/xxdjbczjqdglb/inventoryAssociateInitEdit.action?flag=add&dt="+dt.getTime(),700,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_inventoryAssociate","修改先行登记保存证据清单关联表","${ctx}/jsp/xxdjbczjqdglb/inventoryAssociateInitEdit.action?flag=mod&inventoryAssociate.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_inventoryAssociate","查看先行登记保存证据清单关联表","${ctx}/jsp/xxdjbczjqdglb/inventoryAssociateView.action?inventoryAssociate.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_inventoryAssociate();
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
		                	url : "inventoryAssociateDel.action",
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
		                        	search_inventoryAssociate();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_inventoryAssociate(){
        	var queryParams = {
				"inventoryAssociate.evidenceName": $("#evidenceName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'先行登记保存证据清单关联表列表',
				url:'inventoryAssociateQuery.action',
				queryParams:{
					"inventoryAssociate.evidenceName": $("#evidenceName").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'evidenceName',title:'证据名称',width:100},
{field:'specificationModel',title:'规格型号',width:100},
{field:'inventoryNum',title:'数量',width:100},
{field:'condition',title:'成色',width:100},
{field:'price',title:'价格',width:100},
{field:'originPlace',title:'产地',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";}}
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

        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
				<tr>
					
				<th width="15%">证据名称</th>
				<td width="35%"><input name="inventoryAssociate.evidenceName" id="evidenceName" value="${inventoryAssociate.evidenceName}" type="text"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_inventoryAssociate()" >查询<b></b></a>&nbsp;
						<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;				
					</td>
				</tr>
			</table>
		</div>
		
		<div id="pagination" >
		</div>
		</div>
		</div>
	</div>
</body>
</html>
