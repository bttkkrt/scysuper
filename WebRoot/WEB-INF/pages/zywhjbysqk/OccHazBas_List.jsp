<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>职业危害基本因素情况管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_occHazBas","添加职业危害基本因素情况","${ctx}/jsp/zywhjbysqk/occHazBasInitEdit.action?flag=add&dt="+dt.getTime(),1000,450);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occHazBas","修改职业危害基本因素情况","${ctx}/jsp/zywhjbysqk/occHazBasInitEdit.action?flag=mod&occHazBas.id="+row_Id+"&dt="+dt.getTime(),1000,450);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occHazBas","查看职业危害基本因素情况","${ctx}/jsp/zywhjbysqk/occHazBasView.action?occHazBas.id="+row_Id+"&dt="+dt.getTime(),1000,450);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_occHazBas();
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
		                	url : "occHazBasDel.action",
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
		                        	search_occHazBas();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_occHazBas(){
        	var queryParams = {
				"occHazBas.areaId": $("#areaId").val(),
"occHazBas.areaName": $("#areaName").val(),
"occHazBas.companyName": $("#companyName").val()
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
									}];
									frozen=[[
									    {field:'id',checkbox:true}
									]];
							                        }
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'职业危害基本因素情况列表',
				url:'occHazBasQuery.action',
				queryParams:{
					"occHazBas.areaId": $("#areaId").val(),
"occHazBas.areaName": $("#areaName").val(),
"occHazBas.companyName": $("#companyName").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
//{field:'total',title:'合计',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";}}
//{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_01_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_01_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";}}

				        ]],
				toolbar:toolbar
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
 					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag property="occHazBas.areaId" defaultText='请选择' codeName="企业属地" value="${occHazBas.areaId}" style="width:50%"/></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="occHazBas.companyName" id="companyName" value="${occHazBas.companyName}" type="text" maxlength="127" style="width:50%"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_occHazBas()" >查询<b></b></a>&nbsp;
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
