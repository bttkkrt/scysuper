<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>应急预案管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        function view(id,name){
        	window.opener.document.getElementById('yjid').value = id;
			window.opener.document.getElementById('yjname').value = name;
			window.close();
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_emePla();
        }
        function search_emePla(){
        	var queryParams = {
        	"emePla.type": $("#type").val(),
"emePla.planName": $("#planName").val(),
"emePla.planType": $("#planType").val(),
"emePla.planLevel": $("#planLevel").val(),
"emePla.type": "0",
"emePla.planFilingNumber": $("#planFilingNumber").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'应急预案列表',
				url:'emePlaQuery.action',
				queryParams:{
"emePla.planName": $("#planName").val(),
"emePla.planType": $("#planType").val(),
"emePla.planLevel": $("#planLevel").val(),
"emePla.type": "0",
"emePla.planFilingNumber": $("#planFilingNumber").val()
				},
				columns:[[
{field:'planName',title:'预案名称',width:100},
{field:'planType',title:'预案类别',width:100},
{field:'planLevel',title:'预案级别',width:100},
{field:'planFilingNumber',title:'预案备案编号',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	return "<a class='btn_02_mini' onclick=view('"+rec.id+"','"+rec.planName+"') >选择<b></b></a>";
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
				<th width="15%">预案名称</th>
				<td width="35%"><input name="emePla.planName" id="planName" style="width:50%" value="${emePla.planName}" type="text" maxlength="127"></td>
				<th width="15%">预案类别</th>
				<td width="35%"><cus:SelectOneTag property="emePla.planType" style="width:50%" defaultText='请选择' codeName="应急预案类别" value="${emePla.planType}" /></td>
			</tr>
			<tr>
				<th width="15%">预案级别</th>
				<td width="35%"><cus:SelectOneTag property="emePla.planLevel" style="width:50%" defaultText='请选择' codeName="应急预案级别" value="${emePla.planLevel}" /></td>
				<th width="15%">预案备案编号</th>
				<td width="35%"><input name="emePla.planFilingNumber" style="width:50%" id="planFilingNumber" value="${emePla.planFilingNumber}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_emePla()" >查询<b></b></a>&nbsp;
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
