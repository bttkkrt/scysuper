<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业网格化监督管理 管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_comGriMan","添加企业网格化监督管理 ","${ctx}/jsp/qywghjdgl/comGriManInitEdit.action?flag=add&dt="+dt.getTime(),800,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_comGriMan","修改企业网格化监督管理 ","${ctx}/jsp/qywghjdgl/comGriManInitEdit.action?flag=mod&comGriMan.id="+row_Id+"&dt="+dt.getTime(),800,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_comGriMan","查看企业网格化监督管理 ","${ctx}/jsp/qywghjdgl/comGriManView.action?comGriMan.id="+row_Id+"&dt="+dt.getTime(),800,300);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_comGriMan();
        }
		function del(){
        	var rows = document.getElementsByName('delBox');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
				if(rows[i].checked){
					ids += rows[i].value+"|";
				}
			}
			if(ids == ""){
			    $.messager.alert('提示','至少选择一项删除！');
			}else{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "comGriManDel.action",
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
		                        	search_comGriMan();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_comGriMan(){
        	var queryParams = {
				"comGriMan.gridName": $("#gridName").val(),
"comGriMan.gridManageDeptName": $("#gridManageDeptName").val(),
"comGriMan.gridManagePersonName": $("#gridManagePersonName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'企业网格化监督管理列表',
				url:'comGriManQuery.action',
				queryParams:{
					"comGriMan.gridName": $("#gridName").val(),
"comGriMan.gridManageDeptName": $("#gridManageDeptName").val(),
"comGriMan.gridManagePersonName": $("#gridManagePersonName").val()
				},
				//frozenColumns:frozen,
				columns:[[
				{field:'id',width:15,formatter:function(value,rec){
	var box="<input type='checkbox' name='delBox' value='"+value+"'>";
	if(rec.createUserID=="${sessionScope['LOGIN_USER_ID']}"){
		return box;
	}else{
		return "";//return "<input type='checkbox' name='delBox' disabled value='"+value+"'>";
	}
	
}},
				          {field:'gridName',title:'网格名称',width:100},
{field:'gridManageDeptName',title:'网格管理中队',width:100},
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
					
				<th width="15%">网格名称</th>
				<td width="35%"><input name="comGriMan.gridName" id="gridName" style="width: 50%"  value="${comGriMan.gridName}" type="text" maxlength="127"></td>
				<th width="15%">网格管理中队</th>
				<td width="35%"><input name="comGriMan.gridManageDeptName" style="width: 50%"  id="gridManageDeptName" value="${comGriMan.gridManageDeptName}" type="text" maxlength="127"></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_comGriMan()" >查询<b></b></a>&nbsp;
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
