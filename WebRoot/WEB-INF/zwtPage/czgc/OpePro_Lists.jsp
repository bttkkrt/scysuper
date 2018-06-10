<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>操作规程管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
       function view(id,name){
        	window.opener.document.getElementById('yjid').value = id;
			window.opener.document.getElementById('yjname').value = name;
			window.close();
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_opePro();
        }
        function search_opePro(){
        	var queryParams = {
"opePro.operationPostname": $("#operationPostname").val(),
"opePro.operationWorkshopName": $("#operationWorkshopName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'操作规程列表',
				url:'opeProQuery.action',
				queryParams:{
"opePro.operationPostname": $("#operationPostname").val(),
"opePro.operationWorkshopName": $("#operationWorkshopName").val()
				},
				columns:[[
{field:'operationWorkshopName',title:'车间名称',width:100},
{field:'operationPostname',title:'岗位名称',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	return "<a class='btn_02_mini' onclick=view('"+rec.id+"','"+rec.operationPostname+"') >选择<b></b></a>";
	}}				        
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
			 <th width="15%">车间名称</th>
				<td width="35%"><input name="opePro.operationWorkshopName" style="width:50%" id="operationWorkshopName" value="${opePro.operationWorkshopName}" type="text" maxlength="127"></td>
				 <th width="15%">岗位名称</th>
				<td width="35%"><input name="opePro.operationPostname" style="width:50%" id="operationPostname" value="${opePro.operationPostname}" type="text" maxlength="127"></td>
			</tr>
			
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_opePro()" >查询<b></b></a>&nbsp;
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
