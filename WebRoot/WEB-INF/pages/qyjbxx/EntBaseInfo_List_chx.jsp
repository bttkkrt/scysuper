<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业基本信息管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        function saveData(id,name,szzid){
        
        	var rows = $('#pagination').datagrid('getSelections');
        	var ids = "";
        	var names="";
			for(var i=0;i<rows.length;i++){
				ids += rows[i].id+",";
				names += rows[i].enterpriseName+",";
			}
			
			var s=ids.substring(0,ids.length-1); 
			var n=names.substring(0,names.length-1); 
			if(rows.length<1){
			    $.messager.alert('提示','至少选择一项!');
			  }else{
			  	window.returnValue=s + ";" + n;
				window.close();
			  }
        
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_entBaseInfo();
        }
        function search_entBaseInfo(){
        	var queryParams = {
					"entBaseInfo.enterpriseName": $("#enterpriseName").val(),
"entBaseInfo.registrationNumber": $("#registrationNumber").val(),
"entBaseInfo.enterpriseCode": $("#enterpriseCode").val(),
"entBaseInfo.enterpriseLegalName": $("#enterpriseLegalName").val(),
"entBaseInfo.enterpriseNature": $("#enterpriseNature").val(),
"entBaseInfo.gridName": $("#gridName").val(),
"entBaseInfo.basePass": "1",
"entBaseInfo.gridManageteamCode": "${checkDeptId}",
"entBaseInfo.id": "${checkCompanyId}",
"entBaseInfo.enterprisePossession": $("#enterprisePossession").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'企业基本信息列表',
				url:'entBaseInfoQuery.action',
				queryParams:{
					"entBaseInfo.enterpriseName": $("#enterpriseName").val(),
"entBaseInfo.registrationNumber": $("#registrationNumber").val(),
"entBaseInfo.enterpriseCode": $("#enterpriseCode").val(),
"entBaseInfo.enterpriseLegalName": $("#enterpriseLegalName").val(),
"entBaseInfo.enterpriseNature": $("#enterpriseNature").val(),
"entBaseInfo.gridName": $("#gridName").val(),
"entBaseInfo.basePass": "1",
"entBaseInfo.gridManageteamCode": "${checkDeptId}",
"entBaseInfo.id": "${checkCompanyId}",
"checkUserId": "${checkUserId}",
"entBaseInfo.enterprisePossession": $("#enterprisePossession").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'enterpriseName',title:'企业名称',width:100},
				           {field:'enterprisePossessionName',title:'企业属地',width:100},
{field:'registrationNumber',title:'工商注册号',width:100},
{field:'enterpriseCode',title:'组织机构代码',width:100},
{field:'enterpriseLegalName',title:'法人代表姓名',width:100},
{field:'enterpriseNature',title:'企业性质',width:100},
{field:'gridName',title:'所属网格',width:100}]]
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
					<th width="15%">企业属地</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterprisePossession" style="width:50%" defaultText='请选择' codeName="企业属地" value="${entBaseInfo.enterprisePossession}" maxlength="127"/></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="entBaseInfo.enterpriseName" id="enterpriseName" style="width:50%" value="${entBaseInfo.enterpriseName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">工商注册号</th>
					<td width="35%"><input name="entBaseInfo.registrationNumber" id="registrationNumber" style="width:50%" value="${entBaseInfo.registrationNumber}" type="text" maxlength="127"></td>
					<th width="15%">组织机构代码</th>
					<td width="35%"><input name="entBaseInfo.enterpriseCode" id="enterpriseCode" style="width:50%" value="${entBaseInfo.enterpriseCode}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">法人代表姓名</th>
					<td width="35%"><input name="entBaseInfo.enterpriseLegalName" style="width:50%" id="enterpriseLegalName" value="${entBaseInfo.enterpriseLegalName}" type="text" maxlength="127"></td>
					<th width="15%">企业性质</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterpriseNature" style="width:50%" defaultText='请选择' codeName="企业性质" value="${entBaseInfo.enterpriseNature}" maxlength="127"/></td>
				</tr>
				<tr>
					<th width="15%">网格名称</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.gridName" style="width:50%" defaultText='请选择' codeSql="select  GRID_NAME as id,GRID_NAME as name from COM_GRI_MAN where delflag = 0" value="${entBaseInfo.gridName}" maxlength="127"/></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_entBaseInfo()" >查询<b></b></a>&nbsp;
						<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;
						<a href="###" class="btn_01" onclick="saveData();" >提交<b></b></a>&nbsp;				
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
