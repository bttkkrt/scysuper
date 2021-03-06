<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>粉尘作业场所管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_dusWorMan","添加粉尘作业场所管理","${ctx}/jsp/fczycsgl/dusWorManInitEdit.action?flag=add&dt="+dt.getTime(),1000,500);
        	//window.open("${ctx}/jsp/fczycsgl/dusWorManInitEdit.action?flag=add&dt="+dt.getTime());
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_dusWorMan","修改粉尘作业场所管理","${ctx}/jsp/fczycsgl/dusWorManInitEdit.action?flag=mod&dusWorMan.id="+row_Id+"&dt="+dt.getTime(),1000,500);
        	//window.open("${ctx}/jsp/fczycsgl/dusWorManInitEdit.action?flag=mod&dusWorMan.id="+row_Id+"&dt="+dt.getTime());
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_dusWorMan","查看粉尘作业场所管理","${ctx}/jsp/fczycsgl/dusWorManView.action?dusWorMan.id="+row_Id+"&dt="+dt.getTime(),1000,500);
        	//window.open("${ctx}/jsp/fczycsgl/dusWorManView.action?dusWorMan.id="+row_Id+"&dt="+dt.getTime());
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_dusWorMan();
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
		                	url : "dusWorManDel.action",
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
		                        	search_dusWorMan();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_dusWorMan(){
        	var queryParams = {
				"dusWorMan.areaId": $("#areaId").val(),
"dusWorMan.companyName": $("#companyName").val(),
"dusWorMan.workplaceName": $("#workplaceName").val(),
"dusWorMan.agencyResponsible": $("#agencyResponsible").val(),
"dusWorMan.industryType": $("#industryType").val()
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
				title:'粉尘作业场所管理列表',
				url:'dusWorManQuery.action',
				queryParams:{
					"dusWorMan.areaId": $("#areaId").val(),
"dusWorMan.companyName": $("#companyName").val(),
"dusWorMan.workplaceName": $("#workplaceName").val(),
"dusWorMan.agencyResponsible": $("#agencyResponsible").val(),
"dusWorMan.industryType": $("#industryType").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'workplaceName',title:'作业场所名称',width:100},
{field:'agencyResponsible',title:'所属行业',width:100},
{field:'industryType',title:'粉尘种类',width:100},
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
				<td width="35%"><cus:SelectOneTag property="dusWorMan.areaId" style="width:50%" defaultText='请选择' codeName="企业属地" value="${dusWorMan.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="dusWorMan.companyName" style="width:50%" id="companyName" value="${dusWorMan.companyName}" type="text" maxlength="127"></td>
			</tr>
			</s:if>
			<tr>
				<th width="15%">作业场所名称</th>
				<td width="35%"><input name="dusWorMan.workplaceName" style="width:50%" id="workplaceName" value="${dusWorMan.workplaceName}" type="text" maxlength="127"></td>
				<th width="15%">所属行业</th>
				<td width="35%"><cus:SelectOneTag property="dusWorMan.agencyResponsible" style="width:50%" defaultText='请选择' codeName="粉尘行业" value="${dusWorMan.agencyResponsible}" /></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_dusWorMan()" >查询<b></b></a>&nbsp;
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
