<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备设施空间职业卫生作业点布局管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_equAndFac","添加设备设施空间职业卫生作业点布局","${ctx}/jsp/sbsskjzywszydbj/equAndFacInitEdit.action?flag=add&dt="+dt.getTime(),1000,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_equAndFac","修改设备设施空间职业卫生作业点布局","${ctx}/jsp/sbsskjzywszydbj/equAndFacInitEdit.action?flag=mod&equAndFac.id="+row_Id+"&dt="+dt.getTime(),1000,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_equAndFac","查看设备设施空间职业卫生作业点布局","${ctx}/jsp/sbsskjzywszydbj/equAndFacView.action?equAndFac.id="+row_Id+"&dt="+dt.getTime(),1000,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_equAndFac();
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
		                	url : "equAndFacDel.action",
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
		                        	search_equAndFac();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_equAndFac(){
        	var queryParams = {
				"equAndFac.areaId": $("#areaId").val(),
"equAndFac.companyName": $("#companyName").val(),
"equAndFac.equipmentName": $("#equipmentName").val(),
"equAndFac.equipmentNumber": $("#equipmentNumber").val()
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
				title:'设备设施空间职业卫生作业点布局列表',
				url:'equAndFacQuery.action',
				queryParams:{
					"equAndFac.areaId": $("#areaId").val(),
"equAndFac.companyName": $("#companyName").val(),
"equAndFac.equipmentName": $("#equipmentName").val(),
"equAndFac.equipmentNumber": $("#equipmentNumber").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'equipmentName',title:'设备名称',width:100},
{field:'equipmentNumber',title:'设备编号',width:100},
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
				<td width="35%"><cus:SelectOneTag property="equAndFac.areaId" defaultText='请选择' codeName="企业属地" value="${equAndFac.areaId}" style="width:50%"/></td>
				
				<th width="15%">企业名称</th>
				<td width="35%"><input name="equAndFac.companyName" id="companyName" value="${equAndFac.companyName}" type="text" maxlength="127" style="width:50%"></td>
			</tr></s:if>
			<tr>
				<th width="15%">设备名称</th>
				<td width="35%"><input name="equAndFac.equipmentName" id="equipmentName" value="${equAndFac.equipmentName}" type="text" maxlength="127" style="width:50%"></td>
				<th width="15%">设备编号</th>
				<td width="35%"><input name="equAndFac.equipmentNumber" id="equipmentNumber" value="${equAndFac.equipmentNumber}" type="text" maxlength="127" style="width:50%"></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_equAndFac()" >查询<b></b></a>&nbsp;
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
