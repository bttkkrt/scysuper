<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>生产设备设施管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_proDev","添加生产设备设施","${ctx}/jsp/scsbss/proDevInitEdit.action?flag=add&dt="+dt.getTime(),800,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_proDev","修改生产设备设施","${ctx}/jsp/scsbss/proDevInitEdit.action?flag=mod&proDev.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_proDev","查看生产设备设施","${ctx}/jsp/scsbss/proDevView.action?proDev.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_proDev();
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
		                	url : "proDevDel.action",
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
		                        	search_proDev();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_proDev(){
        	var queryParams = {
				"proDev.areaId": $("#areaId").val(),
"proDev.companyName": $("#companyName").val(),
//"proDev.deviceCode": $("#deviceCode").val(),
"proDev.deviceName": $("#deviceName").val(),
"proDev.deviceWorkshopName": $("#deviceWorkshopName").val(),
"proDev.deviceWorkshopId": $("#deviceWorkshopId").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        var toolbar = [];
        var frozen=[];
        	if('${roleName}'==0){//判断登录角色
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
				    text:'导入生产设备设施',
					iconCls:'icon-add',
					handler:importUser
				
				}];
				frozen=[[
				    {field:'id',checkbox:true}
				]];
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'生产设备设施列表',
				url:'proDevQuery.action',
				queryParams:{
					"proDev.areaId": $("#areaId").val(),
"proDev.companyName": $("#companyName").val(),
//"proDev.deviceCode": $("#deviceCode").val(),
"proDev.deviceName": $("#deviceName").val(),
"proDev.deviceWorkshopName": $("#deviceWorkshopName").val(),
"proDev.deviceWorkshopId": $("#deviceWorkshopId").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'deviceCode',title:'设备编号',width:100},
{field:'deviceName',title:'设备名称',width:100},
{field:'deviceWorkshopName',title:'车间名称',width:100},
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

        function importUser(){ 
        	createSimpleWindow("importUser","批量导入生产设备设施","${ctx}/jsp/scsbss/initImportProDev.action", 350, 200);
        }
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
			<c:if test='${roleName!=0}'>
				<tr>
					
				<th width="15%">所在区域</th>
				<td width="35%">
					<cus:SelectOneTag style="width:50%;" property="proDev.areaId" defaultText='请选择' codeName="企业属地" value="${proDev.areaId}" />
				</td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="proDev.companyName" style="width:50%;" id="companyName" value="${proDev.companyName}" type="text" maxlength="127"></td>
			</tr>
			</c:if>
			<tr>
				<th width="15%">车间名称</th>
				<td width="35%"><input name="proDev.deviceWorkshopName" style="width:50%;" id="deviceWorkshopName" value="${proDev.deviceWorkshopName}" type="text" maxlength="127"></td>
				<th width="15%">设备名称</th>
				<td width="35%"><input name="proDev.deviceName" id="deviceName" style="width:50%;" value="${proDev.deviceName}" type="text" maxlength="127"></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_proDev()" >查询<b></b></a>&nbsp;
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
