<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>救援物资管理及提醒管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_aid","添加救援物资管理及提醒","${ctx}/jsp/aid/aidInitEdit.action?flag=add&dt="+dt.getTime(),700,580);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_aid","修改救援物资管理及提醒","${ctx}/jsp/aid/aidInitEdit.action?flag=mod&aid.id="+row_Id+"&dt="+dt.getTime(),700,580);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_aid","查看救援物资管理及提醒","${ctx}/jsp/aid/aidView.action?aid.id="+row_Id+"&dt="+dt.getTime(),700,580);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_aid();
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
		                	url : "aidDel.action",
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
		                        	search_aid();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_aid(){
        	var queryParams = {
				"aid.suppliedLevel": $("#suppliedLevel").val(),
"aid.suppliedName": $("#suppliedName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'救援物资管理及提醒列表',
				url:'aidQuery.action',
				queryParams:{
					"aid.suppliedLevel": $("#suppliedLevel").val(),
"aid.suppliedName": $("#suppliedName").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'suppliedName',title:'物资名称',width:100},
{field:'suppliedSpecificate',title:'物资规格',width:100},
{field:'suppliedLevel',title:'物资级别',width:100},
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
					
				<th width="15%">物资级别</th>
				<td width="35%"><cus:SelectOneTag property="aid.suppliedLevel" defaultText='请选择' codeName="应急物资级别" value="${aid.suppliedLevel}"  maxlength="127"  style="width:50%"/></td>
				<th width="15%">物资名称</th>
				<td width="35%"><input name="aid.suppliedName" id="suppliedName" value="${aid.suppliedName}" type="text" maxlength="127" style="width:50%"></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_aid()" >查询<b></b></a>&nbsp;
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
