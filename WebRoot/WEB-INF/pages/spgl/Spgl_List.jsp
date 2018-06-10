<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>视频管理管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_spgl","添加视频管理","${ctx}/jsp/spgl/spglInitEdit.action?flag=add&dt="+dt.getTime(),700,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_spgl","修改视频管理","${ctx}/jsp/spgl/spglInitEdit.action?flag=mod&spgl.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_spgl","查看视频管理","${ctx}/jsp/spgl/spglView.action?spgl.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_spgl();
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
		                	url : "spglDel.action",
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
		                        	search_spgl();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_spgl(){
        	var queryParams = {
				"spgl.loginName": $("#loginName").val(),
				"spgl.guid": '${spgl.guid}',
 "queryLoginTimeStart" :$("#queryLoginTimeStart").val(),
 "queryLoginTimeEnd" :$("#queryLoginTimeEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'视频管理列表',
				url:'spglQuery.action',
				queryParams:{
					"spgl.loginName": $("#loginName").val(),
					"spgl.guid": '${spgl.guid}',
 "queryLoginTimeStart" :$("#queryLoginTimeStart").val(),
 "queryLoginTimeEnd" :$("#queryLoginTimeEnd").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'loginName',title:'查看人名称',width:100},
{field:'loginTime',title:'查看时间',width:100}
				        ]],
				toolbar:[
				]
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
					
				<th width="15%">登录人名称</th>
				<td width="35%"><input name="spgl.loginName" style="width: 50%" id="loginName" value="${spgl.loginName}" type="text"></td>
				<th width="15%">登录时间</th>
				<td width="35%"><input name="queryLoginTimeStart" id="queryLoginTimeStart" value="<fmt:formatDate type='both' value='${queryLoginTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryLoginTimeEnd\')}'})" >
					-<input name="queryLoginTimeEnd" id="queryLoginTimeEnd" value="<fmt:formatDate type='both' value='${queryLoginTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryLoginTimeStart\')}'})" ></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_spgl()" >查询<b></b></a>&nbsp;
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
