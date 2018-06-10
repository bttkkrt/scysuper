<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>重点危化品储量情况表管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_cheRes","添加重点危化品储量情况表","${ctx}/jsp/zdwhpclqkb/cheResInitEdit.action?flag=add&dt="+dt.getTime(),800,400);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheRes","修改重点危化品储量情况表","${ctx}/jsp/zdwhpclqkb/cheResInitEdit.action?flag=mod&cheRes.id="+row_Id+"&dt="+dt.getTime(),800,400);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheRes","查看重点危化品储量情况表","${ctx}/jsp/zdwhpclqkb/cheResView.action?cheRes.id="+row_Id+"&dt="+dt.getTime(),800,400);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_cheRes();
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
		                	url : "cheResDel.action",
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
		                        	search_cheRes();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_cheRes(){
        	var queryParams = {
				"cheRes.areaId": $("#areaId").val(),
"cheRes.companyName": $("#companyName").val(),
"cheRes.dangerousChemicalName": $("#dangerousChemicalName").val(),
"cheRes.riskGauge": $("#riskGauge").val()
//"cheRes.unNumber": $("#unNumber").val()
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
				title:'重点危化品储量情况表列表',
				url:'cheResQuery.action',
				queryParams:{
					"cheRes.areaId": $("#areaId").val(),
"cheRes.companyName": $("#companyName").val(),
"cheRes.dangerousChemicalName": $("#dangerousChemicalName").val(),
"cheRes.riskGauge": $("#riskGauge").val()
//"cheRes.unNumber": $("#unNumber").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'dangerousChemicalName',title:'危险化学品名称',width:100},
{field:'riskGauge',title:'危规号',width:100},
{field:'unNumber',title:'UN号',width:100},
{field:'annualUsage',title:'年使用量',width:100},
//{field:'storage',title:'现有储存量',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	if('${roleName}'=='0'){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	}else{
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	}

}}					        ]],
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
				<td width="35%"><cus:SelectOneTag style="width:50%" property="cheRes.areaId" defaultText='请选择' codeName="企业属地" value="${cheRes.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="cheRes.companyName" style="width:50%" id="companyName" value="${cheRes.companyName}" type="text" maxlength="127"></td>
			</tr></s:if>
			<tr>
				<th width="15%">危险化学品名称</th>
				<td width="35%"><input name="cheRes.dangerousChemicalName" style="width:50%" id="dangerousChemicalName" value="${cheRes.dangerousChemicalName}" type="text" maxlength="127"></td>
				<th width="15%">危规号</th>
				<td width="35%"><input name="cheRes.riskGauge" id="riskGauge" style="width:50%" value="${cheRes.riskGauge}" type="text" maxlength="127"></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_cheRes()" >查询<b></b></a>&nbsp;
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
