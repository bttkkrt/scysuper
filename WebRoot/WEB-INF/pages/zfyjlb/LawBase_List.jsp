<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>执法依据管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_lawBase","添加执法依据","${ctx}/jsp/zfyjlb/lawBaseInitEdit.action?flag=add&dt="+dt.getTime(),1000,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_lawBase","修改执法依据","${ctx}/jsp/zfyjlb/lawBaseInitEdit.action?flag=mod&lawBase.id="+row_Id+"&dt="+dt.getTime(),1000,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_lawBase","查看执法依据","${ctx}/jsp/zfyjlb/lawBaseView.action?lawBase.id="+row_Id+"&dt="+dt.getTime(),1000,550);
        	
        }
        function viewTkx(row_Id,name){
        	var id  = "win_lawBasis";
        	var text = "管理" + name + "-条款项列表";
        	var url = "jsp/zfyj/lawBasisList.action?lawBasis.lawId="+row_Id;
    		window.parent.addTab(id,text,url);
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_lawBase();
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
		                	url : "lawBaseDel.action",
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
		                        	search_lawBase();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_lawBase(){
        	var queryParams = {
				"lawBase.lawName": $("#lawName").val(),
"lawBase.state": $("#state").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
				
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'法律法规资源库列表',
				url:'lawBaseQuery.action',
				queryParams:{
					"lawBase.lawName": $("#lawName").val(),
"lawBase.state": $("#state").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'lawName',title:'执法依据名称',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"') >修改<b></b></a>&nbsp;<a class='btn_04_mini' onclick=viewTkx('"+rec.id+"','"+rec.lawName+"') >条款项<b></b></a>";
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
        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
				<tr>
				<th width="15%">执法依据名称</th>
				<td width="35%"><input name="lawBase.lawName" id="lawName" value="${lawBase.lawName}" type="text" style="width: 50%" ></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_lawBase()" >查询<b></b></a>&nbsp;
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
