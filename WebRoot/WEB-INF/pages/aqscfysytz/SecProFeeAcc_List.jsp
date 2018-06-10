<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全生产费用使用台账管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function view(companyId,feeAccountMonth){
        	var dt=new Date();
            createSimpleWindow("win_secProFeeAcc","查看安全生产费用使用台账","${ctx}/jsp/aqscfysytz/secProFeeAccView.action?secProFeeAcc.companyId="+companyId+"&secProFeeAcc.feeAccountMonth="+feeAccountMonth+"&dt="+dt.getTime(),900,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_secProFeeAcc();
        }
        function search_secProFeeAcc(){
        	var queryParams = {
				"secProFeeAcc.areaId": $("#areaId").val(),
"secProFeeAcc.companyName": $("#companyName").val(),
"secProFeeAcc.feeAccountProject": $("#feeAccountProject").val(),
 "queryFeeAccountMonthStart" :$("#queryFeeAccountMonthStart").val(),
 "queryFeeAccountMonthEnd" :$("#queryFeeAccountMonthEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'安全生产费用使用台账列表',
				url:'secProFeeAccQuery.action',
				queryParams:{
					"secProFeeAcc.areaId": $("#areaId").val(),
"secProFeeAcc.companyName": $("#companyName").val(),
 "queryFeeAccountMonthStart" :$("#queryFeeAccountMonthStart").val(),
 "queryFeeAccountMonthEnd" :$("#queryFeeAccountMonthEnd").val()
				},
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'feeAccountAmount',title:'支出金额',width:100},
{field:'feeAccountMonth',title:'使用月份',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
		return "<a class='btn_02_mini' onclick=view('"+rec.companyId+"','"+rec.feeAccountMonth+"') >查看<b></b></a>";
}}				        ]]
				})); 
			if('${roleName}'=='0'){//判断登录角色
        	$('#pagination').datagrid('hideColumn', 'areaName');
        	$('#pagination').datagrid('hideColumn', 'companyName');
        	}
		});

         function exprtXls(){
        	document.myform1.submit();
        }
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<form name="myform1" method="post" enctype="multipart/form-data" action="secProFeeAccExportXls.action">
			<input type="hidden" name="flag" value="1"/>
			<table width="100%">
			<s:if test='roleName!="0"'>
				<tr>
					
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag property="secProFeeAcc.areaId" style="width:50%" defaultText='请选择' codeName="企业属地" value="${secProFeeAcc.areaId}"  maxlength="127"/></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="secProFeeAcc.companyName" style="width:50%" id="companyName" value="${secProFeeAcc.companyName}" type="text" maxlength="127"></td>
			</tr>
			</s:if>
			<tr>
				<th width="15%">使用月份</th>
				<td width="35%"><input name="queryFeeAccountMonthStart" id="queryFeeAccountMonthStart" value="${queryFeeAccountMonthStart}" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM',maxDate:'#F{$dp.$D(\'queryFeeAccountMonthEnd\')}'})" >
					-<input name="queryFeeAccountMonthEnd" id="queryFeeAccountMonthEnd" value="${queryFeeAccountMonthEnd}" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'queryFeeAccountMonthStart\')}'})" ></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_secProFeeAcc()" >查询<b></b></a>&nbsp;
						<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;	
						<a href="###" class="btn_01" onclick="exprtXls();" >导出<b></b></a>&nbsp;				
					</td>
				</tr>
			</table>
			</form>
		</div>
		
		<div id="pagination">
		</div>
		</div>
		</div>
	</div>
</body>
</html>
