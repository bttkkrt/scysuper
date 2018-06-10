<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全生产管理经理管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
         function view(id,name,zh){
        	window.opener.document.getElementById('ryid').value = id;
			window.opener.document.getElementById('ryname').value = name;
			window.opener.document.getElementById('zjhm').value = zh;
			window.close();
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_secProMan();
        }
        function search_secProMan(){
        	var queryParams = {
				"secProMan.areaId": $("#areaId").val(),
"secProMan.managerSpecializedNum": $("#managerSpecializedNum").val(),
"secProMan.managerName": $("#managerName").val(),
"secProMan.companyName": $("#companyName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'安全生产管理经理列表',
				url:'secProManQuery.action',
				queryParams:{
					"secProMan.areaId": $("#areaId").val(),
"secProMan.managerSpecializedNum": $("#managerSpecializedNum").val(),
"secProMan.managerName": $("#managerName").val(),
"secProMan.companyName": $("#companyName").val()
				},
				columns:[[
{field:'managerName',title:'姓名',width:100},
{field:'managerSpecializedNum',title:'安全生产管理员资格证号',width:100},
{field:'managerPhone',title:'联系方式',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	return "<a class='btn_02_mini' onclick=view('"+rec.id+"','"+rec.managerName+"','"+rec.managerSpecializedNum+"') >选择<b></b></a>";

}}				        ]]
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
				<th width="15%">姓名</th>
				<td width="35%"><input name="secProMan.managerName" style="width:50%;" id="managerName" value="${secProMan.managerName}" type="text" maxlength="127"></td>
				<th width="15%">安全生产管理员资格证号</th>
				<td width="35%"><input name="secProMan.managerSpecializedNum" style="width:50%;" id="managerSpecializedNum" value="${secProMan.managerSpecializedNum}" type="text" maxlength="127"></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_secProMan()" >查询<b></b></a>&nbsp;
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
