<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>操作规程管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_opePro","添加操作规程","${ctx}/jsp/czgc/opeProInitEdit.action?flag=add&dt="+dt.getTime(),700,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_opePro","修改操作规程","${ctx}/jsp/czgc/opeProInitEdit.action?flag=mod&opePro.id="+row_Id+"&dt="+dt.getTime(),700,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_opePro","查看操作规程","${ctx}/jsp/czgc/opeProView.action?opePro.id="+row_Id+"&dt="+dt.getTime(),700,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_opePro();
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
		                	url : "opeProDel.action",
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
		                        	search_opePro();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_opePro(){
        	var queryParams = {
				"opePro.areaId": $("#areaId").val(),
				"opePro.areaName": $("#areaName").val(),
"opePro.companyName": $("#companyName").val(),
"opePro.operationCode": $("#operationCode").val(),
"opePro.operationPostname": $("#operationPostname").val(),
"opePro.operationWorkshopId": $("#operationWorkshopId").val(),
"opePro.operationWorkshopName": $("#operationWorkshopName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
       var toolbar = [];
       	 var frozen=[];
        	if('${roleName}'=='0'){//判断登录角色
				toolbar = [{
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
				},{
				    text:'导入操作规程',
					iconCls:'icon-add',
					handler:importUser
				
				}
				
				];
				frozen=[[
				    {field:'id',checkbox:true}
				]];
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'操作规程列表',
				url:'opeProQuery.action',
				queryParams:{
					"opePro.areaId": $("#areaId").val(),
					"opePro.areaName": $("#areaName").val(),
"opePro.companyName": $("#companyName").val(),
"opePro.operationWorkshopId": $("#operationWorkshopId").val(),

"opePro.operationWorkshopName": $("#operationWorkshopName").val()
				},
			frozenColumns:frozen,
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'operationWorkshopName',title:'车间名称',width:100},
{field:'operationPostname',title:'岗位名称',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	if('${roleName}'=='0'){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	}else{
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	}

}}				        ]],
				toolbar:toolbar
			})); 
			if('${roleName}'=='0'){//判断登录角色
        	$('#pagination').datagrid('hideColumn', 'areaName');
        	$('#pagination').datagrid('hideColumn', 'companyName');
        	}
		});

function importUser(){ 
        	createSimpleWindow("importUser","批量导入操作规程","${ctx}/jsp/czgc/initImportOpePro.action", 350, 200);
        }
        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
			<s:if test='roleName!="0"'>
				<tr>
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag property="opePro.areaId" style="width:50%" defaultText='请选择' codeName="企业属地" value="${opePro.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="opePro.companyName" style="width:50%" id="companyName" value="${opePro.companyName}" type="text" maxlength="127"></td>
			</tr>
			</s:if>
			<tr>
			 <th width="15%">车间名称</th>
				<td width="35%"><input name="opePro.operationWorkshopName" style="width:50%" id="operationWorkshopName" value="${opePro.operationWorkshopName}" type="text" maxlength="127"></td>
				 <th width="15%">岗位名称</th>
				<td width="35%"><input name="opePro.operationPostname" style="width:50%" id="operationPostname" value="${opePro.operationPostname}" type="text" maxlength="127"></td>
			</tr>
			
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_opePro()" >查询<b></b></a>&nbsp;
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
