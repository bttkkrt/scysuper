<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>特种作业人员管理</title>
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
        	search_speJobPer();
        }
        function search_speJobPer(){
        	var queryParams = {
						"speJobPer.areaId": $("#areaId").val(),
"speJobPer.companyName": $("#companyName").val(),
"speJobPer.specialName": $("#specialName").val(),
"speJobPer.specialJobCradnum": $("#specialJobCradnum").val(),
"speJobPer.specialJobType": $("#specialJobType").val(),
 "querySpecialVerificationDateStart" :$("#querySpecialVerificationDateStart").val(),
 "querySpecialVerificationDateEnd" :$("#querySpecialVerificationDateEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'特种作业人员列表',
				url:'speJobPerQuery.action',
				queryParams:{
						"speJobPer.areaId": $("#areaId").val(),
"speJobPer.companyName": $("#companyName").val(),
"speJobPer.specialName": $("#specialName").val(),
"speJobPer.specialJobCradnum": $("#specialJobCradnum").val(),
"speJobPer.specialJobType": $("#specialJobType").val(),
 "querySpecialVerificationDateStart" :$("#querySpecialVerificationDateStart").val(),
 "querySpecialVerificationDateEnd" :$("#querySpecialVerificationDateEnd").val()
				},
				columns:[[
{field:'specialName',title:'姓名',width:100},
{field:'specialJobType',title:'特种作业类型',width:100},
{field:'specialJobCradnum',title:'特种作业证号',width:100},
{field:'specialVerificationDate',title:'复审日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	return "<a class='btn_02_mini' onclick=view('"+rec.id+"','"+rec.specialName+"','"+rec.specialJobCradnum+"') >选择<b></b></a>";

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
				<td width="35%"><input name="speJobPer.specialName" style="width:50%;" id="specialName" value="${speJobPer.specialName}" type="text" maxlength="127"></td>
				<th width="15%">特种作业证号</th>
				<td width="35%"><input id="specialJobCradnum" style="width:50%;" name="speJobPer.specialJobCradnum" value="${speJobPer.specialJobCradnum}" type="text"  maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">特种作业类型</th>
				<td width="35%"><cus:SelectOneTag style="width:50%;" property="speJobPer.specialJobType" defaultText='请选择' codeName="特种作业类型" value="${speJobPer.specialJobType}"/></td>
				<th width="15%">复审日期</th>
				<td width="35%">
					<input name="querySpecialVerificationDateStart" id="querySpecialVerificationDateStart" value="<fmt:formatDate type='date' value='${querySpecialVerificationDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'querySpecialVerificationDateEnd\')}'})" >
					-<input name="querySpecialVerificationDateEnd" id="querySpecialVerificationDateEnd" value="<fmt:formatDate type='date' value='${querySpecialVerificationDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'querySpecialVerificationDateStart\')}'})" >
				</td>
			</tr>
			<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_speJobPer()" >查询<b></b></a>&nbsp;
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
