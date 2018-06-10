<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>周边环境信息管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_envInf","添加周边环境信息","${ctx}/jsp/zbhjxx/envInfInitEdit.action?flag=add&dt="+dt.getTime(),1000,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_envInf","修改周边环境信息","${ctx}/jsp/zbhjxx/envInfInitEdit.action?flag=mod&envInf.id="+row_Id+"&dt="+dt.getTime(),1000,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_envInf","查看周边环境信息","${ctx}/jsp/zbhjxx/envInfView.action?envInf.id="+row_Id+"&dt="+dt.getTime(),1000,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_envInf();
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
		                	url : "envInfDel.action",
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
		                        	search_envInf();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_envInf(){
        	var queryParams = {
				"envInf.areaId": $("#areaId").val(),
"envInf.companyName": $("#companyName").val(),
"envInf.surroundingEnvironmentName": $("#surroundingEnvironmentName").val(),
"envInf.surroundingEnvironmentType": $("#surroundingEnvironmentType").val()
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
				title:'周边环境信息列表',
				url:'envInfQuery.action',
				queryParams:{
					"envInf.areaId": $("#areaId").val(),
"envInf.companyName": $("#companyName").val(),
"envInf.surroundingEnvironmentName": $("#surroundingEnvironmentName").val(),
"envInf.surroundingEnvironmentType": $("#surroundingEnvironmentType").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},

{field:'surroundingEnvironmentType',title:'周边环境类型',width:100},
{field:'personnelType',title:'人员数量',width:100},

{field:'surroundingEnvironmentName',title:'周边环境名称',width:100},
{field:'contactPerson',title:'联系人',width:100},
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
				<td width="35%"><cus:SelectOneTag style="width:50%;" property="envInf.areaId" defaultText='请选择' codeName="企业属地" value="${envInf.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="envInf.companyName" style="width:50%;" id="companyName" value="${envInf.companyName}" type="text"></td>
			</tr></s:if>
			<tr>
				<th width="15%">周边环境类型</th>
				<td width="35%"><cus:SelectOneTag style="width:50%;" property="envInf.surroundingEnvironmentType" defaultText='请选择' codeName="周边环境类型" value="${envInf.surroundingEnvironmentType}" /></td>
				
				<th width="15%">周边环境名称</th>
				<td width="35%"><input name="envInf.surroundingEnvironmentName" style="width:50%;" id="surroundingEnvironmentName" value="${envInf.surroundingEnvironmentName}" type="text"></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_envInf()" >查询<b></b></a>&nbsp;
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
