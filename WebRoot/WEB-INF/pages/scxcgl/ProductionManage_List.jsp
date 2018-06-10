<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>生产现场管理和生产过程控制</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_productionManage","添加生产现场管理和生产过程控制","${ctx}/jsp/scxcgl/productionManageInitEdit.action?flag=add&dt="+dt.getTime(),800,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_productionManage","修改生产现场管理和生产过程控制","${ctx}/jsp/scxcgl/productionManageInitEdit.action?flag=mod&productionManage.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_productionManage","查看生产现场管理和生产过程控制","${ctx}/jsp/scxcgl/productionManageView.action?productionManage.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_productionManage();
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
		                	url : "productionManageDel.action",
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
		                        	search_productionManage();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_productionManage(){
        	var queryParams = {
				"productionManage.areaId": $("#areaId").val(),
"productionManage.personInCharge": $("#personInCharge").val(),
"queryJobTimeStart": $("#queryJobTimeStart").val(),
"queryJobTimeEnd": $("#queryJobTimeEnd").val(),
"productionManage.companyName": $("#companyName").val()
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
				    text:'导入生产现场管理',
					iconCls:'icon-add',
					handler:importUser
				
				}
				
				];
				frozen=[[
				    {field:'id',checkbox:true}
				]];
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'生产现场管理和生产过程控制列表',
				url:'productionManageQuery.action',
				queryParams:{
					"productionManage.areaId": $("#areaId").val(),
"productionManage.personInCharge": $("#personInCharge").val(),
"queryJobTimeStart": $("#queryJobTimeStart").val(),
"queryJobTimeEnd": $("#queryJobTimeEnd").val(),
"productionManage.companyName": $("#companyName").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
				          {field:'companyName',title:'企业名称',width:100},
{field:'personInCharge',title:'项目负责人',width:100},
{field:'jobTime',title:'作业时间',width:100,formatter:function(value,rec){return value.substring(0,10);}},
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
        	createSimpleWindow("importUser","批量导入生产现场管理","${ctx}/jsp/scxcgl/initImportProductionManage.action", 350, 200);
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
				<td width="35%"><cus:SelectOneTag property="productionManage.areaId" style="width:50%;" defaultText='请选择' codeName="企业属地" value="${productionManage.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="productionManage.companyName" style="width:50%;" id="companyName" value="${productionManage.companyName}" type="text" maxlength="127"></td>
				</tr>
				</s:if>
			<tr>
				<th width="15%">项目负责人</th>
				<td width="35%"><input name="productionManage.personInCharge" style="width:50%;" id="personInCharge" value="${productionManage.personInCharge}" type="text" maxlength="127"></td>
				<th width="15%">作业时间</th>
				<td width="35%"><input name="queryJobTimeStart" id="queryJobTimeStart" value="<fmt:formatDate type='date' value='${queryJobTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryJobTimeEnd\')}'})" >
					-<input name="queryJobTimeEnd" id="queryJobTimeEnd" value="<fmt:formatDate type='date' value='${queryJobTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryJobTimeStart\')}'})" ></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_productionManage()" >查询<b></b></a>&nbsp;
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
