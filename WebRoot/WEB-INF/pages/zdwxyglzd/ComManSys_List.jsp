<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业重点危险源的管理制度管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_comManSys","添加企业重点危险源的管理制度","${ctx}/jsp/zdwxyglzd/comManSysInitEdit.action?flag=add&dt="+dt.getTime(),700,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_comManSys","修改企业重点危险源的管理制度","${ctx}/jsp/zdwxyglzd/comManSysInitEdit.action?flag=mod&comManSys.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_comManSys","查看企业重点危险源的管理制度","${ctx}/jsp/zdwxyglzd/comManSysView.action?comManSys.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_comManSys();
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
		                	url : "comManSysDel.action",
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
		                        	search_comManSys();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_comManSys(){
        	var queryParams = {
				"comManSys.areaId": $("#areaId").val(),
"comManSys.companyName": $("#companyName").val(),
"comManSys.systemName": $("#systemName").val(),
"comManSys.dangerName": $("#dangerName").val()
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
				title:'企业重点危险源的管理制度列表',
				url:'comManSysQuery.action',
				queryParams:{
					"comManSys.areaId": $("#areaId").val(),
"comManSys.companyName": $("#companyName").val(),
"comManSys.systemName": $("#systemName").val(),
"comManSys.dangerName": $("#dangerName").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'systemName',title:'制度名称',width:100},
{field:'dangerName',title:'危险源名称',width:100},
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
				<td width="35%"><cus:SelectOneTag property="comManSys.areaId" style="width:50%" defaultText='请选择' codeName="企业属地" value="${comManSys.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="comManSys.companyName" style="width:50%" id="companyName" value="${comManSys.companyName}" type="text" maxlength="127"></td>
			</tr>
			</s:if>
			<tr>
				<th width="15%">制度名称</th>
				<td width="35%"><input id="systemName" style="width:50%"  name="comManSys.systemName" value="${comManSys.systemName}" type="text" maxlength="127"></td>
				<th width="15%">重点危险源名称</th>
				<td width="35%"><input id="dangerName" style="width:50%" name="comManSys.dangerName" value="${comManSys.dangerName}" type="text"  maxlength="127"></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_comManSys()" >查询<b></b></a>&nbsp;
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
