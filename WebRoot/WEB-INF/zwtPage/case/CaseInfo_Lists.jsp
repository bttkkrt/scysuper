<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>案件管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        function view(id,name){
        	window.returnValue=id + ";" + name;
			window.close();
        }
        
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_caseInfo();
        }
        function search_caseInfo(){
        	var queryParams = {
				"caseInfo.areaId": $("#areaId").val(),
"caseInfo.companyName": $("#companyName").val(),
"caseInfo.caseId": $("#caseId").val(),
"caseInfo.caseSource": $("#caseSource").val(),
"caseInfo.caseStatus": "2",
"caseInfo.caseName": $("#caseName").val(),
 "queryCaseTimeStart" :$("#queryCaseTimeStart").val(),
 "queryCaseTimeEnd" :$("#queryCaseTimeEnd").val(),
 "type":"1"
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'案件列表',
				url:'caseInfoQuery.action',
				queryParams:{
					"caseInfo.areaId": $("#areaId").val(),
"caseInfo.companyName": $("#companyName").val(),
"caseInfo.caseId": $("#caseId").val(),
"caseInfo.caseSource": $("#caseSource").val(),
"caseInfo.caseStatus": "2",
"caseInfo.caseName": $("#caseName").val(),
 "queryCaseTimeStart" :$("#queryCaseTimeStart").val(),
 "queryCaseTimeEnd" :$("#queryCaseTimeEnd").val(),
 "type":"1"
				},
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'caseName',title:'案件名称',width:100},
{field:'caseTime',title:'案件时间',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'caseSource',title:'案件来源',width:100},
{field:'undertakerName',title:'承办人',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
var aa = "<a class='btn_02_mini' onclick=view('"+rec.id+"','"+rec.caseName+"') >选择<b></b></a>&nbsp;";
return aa;

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
				<th width="15%">所在区域</th>
				<td width="35%">
					<cus:SelectOneTag property="caseInfo.areaId" style="width:50%" defaultText='请选择' codeName="企业属地" value="${caseInfo.areaId}" maxlength="127"/>
				</td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="caseInfo.companyName" style="width:50%" id="companyName" value="${caseInfo.companyName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">案件名称</th>
				<td width="35%"><input name="caseInfo.caseName" style="width:50%" id="caseName" value="${caseInfo.caseName}" type="text" maxlength="127"></td>
				<th width="15%">案件时间</th>
				<td width="35%"><input name="queryCaseTimeStart" id="queryCaseTimeStart" value="<fmt:formatDate type='date' value='${queryCaseTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryCaseTimeEnd\')}'})" >
					-<input name="queryCaseTimeEnd" id="queryCaseTimeEnd" value="<fmt:formatDate type='date' value='${queryCaseTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryCaseTimeStart\')}'})" ></td>
			</tr>
			<tr>
				<th width="15%">案件来源</th>
				<td width="35%">
					<cus:SelectOneTag property="caseInfo.caseSource" style="width:50%" defaultText='请选择' codeName="案件来源" value="${caseInfo.caseSource}" maxlength="127"/>
				</td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_caseInfo()" >查询<b></b></a>&nbsp;
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
