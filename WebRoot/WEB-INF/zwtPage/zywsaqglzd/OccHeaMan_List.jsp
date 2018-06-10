<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>职业卫生安全管理制度管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_occHeaMan","添加职业卫生安全管理制度","${ctx}/jsp/zywsaqglzd/occHeaManInitEdit.action?flag=add&dt="+dt.getTime(),700,450);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occHeaMan","修改职业卫生安全管理制度","${ctx}/jsp/zywsaqglzd/occHeaManInitEdit.action?flag=mod&occHeaMan.id="+row_Id+"&dt="+dt.getTime(),700,450);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occHeaMan","查看职业卫生安全管理制度","${ctx}/jsp/zywsaqglzd/occHeaManView.action?occHeaMan.id="+row_Id+"&dt="+dt.getTime(),700,450);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_occHeaMan();
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
		                	url : "occHeaManDel.action",
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
		                        	search_occHeaMan();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_occHeaMan(){
        	var queryParams = {
				"occHeaMan.areaId": $("#areaId").val(),
				"occHeaMan.companyName": $("#companyName").val(),
"occHeaMan.systemName": $("#systemName").val(),
"occHeaMan.systemType": $("#systemType").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        var toolbar = [];
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
				};
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'职业卫生安全管理制度列表',
				url:'occHeaManQuery.action',
				queryParams:{
					"occHeaMan.areaId": $("#areaId").val(),
					"occHeaMan.companyName": $("#companyName").val(),
"occHeaMan.systemName": $("#systemName").val(),
"occHeaMan.systemType": $("#systemType").val()
				},
				frozenColumns:[[
				    {field:'${roleName}'=='0'?'id':'',
				    checkbox:'${roleName}'=='0'?true:false}
				]],
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'systemName',title:'制度名称',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
		if('${roleName}'!='0'){
			return button1;
		}
		else{
			return button2;
		}}
}
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
				<s:if test='roleName!="0"'>
				<tr>
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag property="occHeaMan.areaId" defaultText='请选择' codeName="企业属地" value="${occHeaMan.areaId}" style="width:50%"/></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="occHeaMan.companyName" id="companyName" value="${occHeaMan.companyName}" type="text" maxlength="127" style="width:50%"></td>
			</tr></s:if>
			<tr>
				<th width="15%">制度名称</th>
				<td width="35%"><input name="occHeaMan.systemName" id="systemName" value="${occHeaMan.systemName}" type="text" maxlength="127" style="width:50%"></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_occHeaMan()" >查询<b></b></a>&nbsp;
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
