<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业基本信息管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function view(id,name,szzid,zzjgm,gszch){
        	var flag = "${flag}";
        	if(flag == 'sxxw')
        	{
        		window.opener.document.getElementById('companyId').value = id;
				window.opener.document.getElementById('companyName').value = name;
				window.opener.document.getElementById('areaId').value = szzid;
				window.opener.document.getElementById('zzjgm').value = zzjgm;
				window.opener.document.getElementById('gszch').value = gszch;
        	}
        	else if(flag == 'idmc')
        	{
        		window.opener.document.getElementById('companyId').value = id;
				window.opener.document.getElementById('companyName').value = name;
        	}
        	else if(flag == 'idmcqy')
        	{
        		window.opener.document.getElementById('companyId').value = id;
				window.opener.document.getElementById('companyName').value = name;
				window.opener.document.getElementById('areaId').value = szzid;
        	}
			window.close();
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
"entBaseInfo.enterprisePossession": $("#enterprisePossession").val()
				},
				columns:[[
				          {field:'enterpriseName',title:'企业名称',width:100},
				           {field:'enterprisePossessionName',title:'企业属地',width:100},
{field:'registrationNumber',title:'工商注册号',width:100},
{field:'enterpriseCode',title:'组织机构代码',width:100},
{field:'enterpriseLegalName',title:'法人代表姓名',width:100},
{field:'enterpriseNature',title:'企业性质',width:100},
{field:'gridName',title:'所属网格',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=view('"+rec.id+"','"+rec.enterpriseName+"','"+rec.enterprisePossession+"','"+rec.enterpriseCode+"','"+rec.registrationNumber+"') >选择<b></b></a>";}}
				        ]]
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
					<th width="15%">网格名称</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.gridName" style="width:50%" defaultText='请选择' codeSql="select  GRID_NAME as id,GRID_NAME as name from COM_GRI_MAN where delflag = 0" value="${entBaseInfo.gridName}" maxlength="127"/>
					</td>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="entBaseInfo.enterpriseName" style="width:50%" id="enterpriseName" value="${entBaseInfo.enterpriseName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">工商注册号</th>
					<td width="35%"><input name="entBaseInfo.registrationNumber" style="width:50%" id="registrationNumber" value="${entBaseInfo.registrationNumber}" type="text" maxlength="127"></td>
					<th width="15%">组织机构代码</th>
					<td width="35%"><input name="entBaseInfo.enterpriseCode" style="width:50%" id="enterpriseCode" value="${entBaseInfo.enterpriseCode}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">法人代表姓名</th>
					<td width="35%"><input name="entBaseInfo.enterpriseLegalName" style="width:50%" id="enterpriseLegalName" value="${entBaseInfo.enterpriseLegalName}" type="text" maxlength="127"></td>
					<th width="15%">企业性质</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterpriseNature" style="width:50%" defaultText='请选择' codeName="企业性质" value="${entBaseInfo.enterpriseNature}" maxlength="127"/></td>
				</tr>
				<tr>
					<th width="15%">企业属地</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterprisePossession" style="width:50%" defaultText='请选择' codeName="企业属地" value="${entBaseInfo.enterprisePossession}" maxlength="127"/></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_entBaseInfo()" >查询<b></b></a>&nbsp;
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
