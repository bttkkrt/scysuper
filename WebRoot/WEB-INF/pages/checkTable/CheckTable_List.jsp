<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>六类检查表管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_checkTable","添加六类检查表","${ctx}/jsp/checkTable/checkTableInitEdit.action?flag=add&checkTable.companyType=${checkTable.companyType}&dt="+dt.getTime(),850,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_checkTable","修改六类检查表","${ctx}/jsp/checkTable/checkTableInitEdit.action?flag=mod&checkTable.id="+row_Id+"&dt="+dt.getTime(),850,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_checkTable","查看六类检查表","${ctx}/jsp/checkTable/checkTableView.action?checkTable.id="+row_Id+"&dt="+dt.getTime(),850,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_checkTable();
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
		                	url : "checkTableDel.action",
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
		                        	search_checkTable();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_checkTable(){
        	var queryParams = {
				"checkTable.companyName": $("#companyName").val(),
				"checkTable.companyType": "${checkTable.companyType}",
 "queryCheckTimeStart" :$("#queryCheckTimeStart").val(),
 "queryCheckTimeEnd" :$("#queryCheckTimeEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	var toolbar=[{
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
				}];
			var frozenColumns=[[
				    {field:'id',checkbox:true}
				]];
			if('${roleName}'!='0'){
				toolbar=[];
				frozenColumns=[];
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'六类检查表列表',
				url:'checkTableQuery.action',
				queryParams:{
					"checkTable.areaId": $("#areaId").val(),
					"checkTable.companyType": "${checkTable.companyType}",
					"checkTable.companyName": $("#companyName").val(),
					 "queryCheckTimeStart" :$("#queryCheckTimeStart").val(),
					 "queryCheckTimeEnd" :$("#queryCheckTimeEnd").val()
				},
				frozenColumns:frozenColumns,
				columns:[[
							{field:'areaName',title:'所属区域',width:100},
				          {field:'companyName',title:'企业名称',width:100},
{field:'checkTime',title:'检查时间',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
/* if('${roleName}'=='0'){
}else{
	return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	} */
}}
				        ]],
				toolbar:toolbar
			}));
			if('${roleName}'=='0'){//判断登录角色
        	$('#pagination').datagrid('hideColumn', 'areaName');
        	$('#pagination').datagrid('hideColumn', 'companyName');
        	}
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
					
				<th width="15%">企业名称</th>
				<td width="35%"><input name="checkTable.companyName" style="width: 50%" id="companyName" value="${checkTable.companyName}" type="text"></td>
				<th width="15%">检查时间</th>
				<td width="35%"><input name="queryCheckTimeStart" id="queryCheckTimeStart" value="<fmt:formatDate type='both' value='${queryCheckTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryCheckTimeEnd\')}'})" >
					-<input name="queryCheckTimeEnd" id="queryCheckTimeEnd" value="<fmt:formatDate type='both' value='${queryCheckTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryCheckTimeStart\')}'})" ></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_checkTable()" >查询<b></b></a>&nbsp;
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
