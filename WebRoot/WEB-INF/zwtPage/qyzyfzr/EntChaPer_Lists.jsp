<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业主要负责人管理</title>
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
        	search_entChaPer();
        }
        function search_entChaPer(){
        	var queryParams = {
				"entChaPer.areaId": $("#areaId").val(),
"entChaPer.companyName": $("#companyName").val(),
"entChaPer.chargeName": $("#chargeName").val(),
//"entChaPer.chargeTitle": $("#chargeTitle").val(),
"entChaPer.chargeSpecializedNum": $("#chargeSpecializedNum").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'企业主要负责人列表',
				url:'entChaPerQuery.action',
				queryParams:{
					"entChaPer.areaId": $("#areaId").val(),
"entChaPer.companyName": $("#companyName").val(),
"entChaPer.chargeName": $("#chargeName").val(),
//"entChaPer.chargeTitle": $("#chargeTitle").val(),
"entChaPer.chargeSpecializedNum": $("#chargeSpecializedNum").val()
				},
				columns:[[
{field:'chargeName',title:'姓名',width:100},
{field:'chargeSpecializedNum',title:'主要负责人安全生产资格证号',width:100},
{field:'chargePhone',title:'联系方式',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"','"+rec.chargeName+"','"+rec.chargeSpecializedNum+"') >选择<b></b></a>";
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
				<td width="35%"><input name="entChaPer.chargeName" id="chargeName" value="${entChaPer.chargeName}" type="text" maxlength="127" style="width:50%;"></td>
				<th width="15%">主要负责人安全生产资格证号</th>
				<td width="35%"><input name="entChaPer.chargeSpecializedNum" id="chargeSpecializedNum" value="${entChaPer.chargeSpecializedNum}" type="text" maxlength="127" style="width:50%;"></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_entChaPer()" >查询<b></b></a>&nbsp;
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
