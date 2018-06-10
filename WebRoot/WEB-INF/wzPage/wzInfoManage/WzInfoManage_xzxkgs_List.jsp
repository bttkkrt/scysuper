<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>行政许可公示管理管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_wzInfoManage","添加行政许可公示管理","${ctx}/jsp/wzInfoManage/wzInfoManageInitEdit.action?flag=add&wzInfoManage.infoType="+"${wzInfoManage.infoType}"+"&dt="+dt.getTime(),900,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_wzInfoManage","修改行政许可公示管理","${ctx}/jsp/wzInfoManage/wzInfoManageInitEdit.action?flag=mod&wzInfoManage.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_wzInfoManage","查看行政许可公示管理","${ctx}/jsp/wzInfoManage/wzInfoManageView.action?wzInfoManage.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_wzInfoManage();
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
		                	url : "wzInfoManageDel.action",
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
		                        	search_wzInfoManage();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_wzInfoManage(){
        	var queryParams = {
				"wzInfoManage.category": $("#category").val(),
					"wzInfoManage.infoType": '${wzInfoManage.infoType}',
					"wzInfoManage.companyName":$("#companyName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'行政许可公示管理列表',
				url:'wzInfoManageQuery.action',
				queryParams:{
					"wzInfoManage.category": $("#category").val(),
					"wzInfoManage.infoType": '${wzInfoManage.infoType}',
					"wzInfoManage.companyName":$("#companyName").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'companyName',title:'申报企业',width:100},
{field:'category',title:'类别',width:100},
{field:'createTime',title:'创建时间',width:100,formatter:function(value,rec){
						return value.substring(0,10);						
					}},
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
					
				<th width="15%">申报企业</th>
				<td width="35%"><input name="wzInfoManage.companyName" id="companyName" value="${wzInfoManage.companyName}" type="text"></td>
				<th width="15%">类别</th>
				<td width="35%"><input name="wzInfoManage.category" id="category" value="${wzInfoManage.category}" type="text"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_wzInfoManage()" >查询<b></b></a>&nbsp;
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
