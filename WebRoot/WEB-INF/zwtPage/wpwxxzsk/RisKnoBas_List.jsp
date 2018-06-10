<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>物品危险性知识库管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_risKnoBas","添加物品危险性知识库","${ctx}/jsp/wpwxxzsk/risKnoBasInitEdit.action?flag=add&dt="+dt.getTime(),700,480);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_risKnoBas","修改物品危险性知识库","${ctx}/jsp/wpwxxzsk/risKnoBasInitEdit.action?flag=mod&risKnoBas.id="+row_Id+"&dt="+dt.getTime(),700,480);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_risKnoBas","查看物品危险性知识库","${ctx}/jsp/wpwxxzsk/risKnoBasView.action?risKnoBas.id="+row_Id+"&dt="+dt.getTime(),700,480);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_risKnoBas();
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
		                	url : "risKnoBasDel.action",
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
		                        	search_risKnoBas();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_risKnoBas(){
        	var queryParams = {
				"risKnoBas.areaId": $("#areaId").val(),
"risKnoBas.companyName": $("#companyName").val(),
"risKnoBas.itemName": $("#itemName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        
         var roleName='';
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
									}];
									frozen=[[
									    {field:'id',checkbox:true}
									]];
							                        }
		                
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'物品危险性知识库列表',
				url:'risKnoBasQuery.action',
				queryParams:{
					"risKnoBas.areaId": $("#areaId").val(),
"risKnoBas.companyName": $("#companyName").val(),
"risKnoBas.itemName": $("#itemName").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'itemName',title:'物品名称',width:100},
{field:'dangerousContent',title:'危险性内容',width:100},
{field:'responseMeasures',title:'应对措施',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";}}
				        ]],
				toolbar:toolbar
			}));
			if('${roleName}'=='0'){//判断登录角色
        	$('#pagination').datagrid('hideColumn', 'areaId');
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
				<s:if test='roleName!="0"'>
				<tr>
					
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag style="width:50%;" property="risKnoBas.areaId" defaultText='请选择' codeName="企业属地" value="${risKnoBas.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="risKnoBas.companyName" style="width:50%;" id="companyName" value="${risKnoBas.companyName}" type="text" maxlength="127"></td>
			</tr></s:if>
			<tr>
				<th width="15%">物品名称</th>
				<td width="35%"><input name="risKnoBas.itemName" style="width:50%;" id="itemName" value="${risKnoBas.itemName}" type="text" maxlength="127"></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_risKnoBas()" >查询<b></b></a>&nbsp;
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
