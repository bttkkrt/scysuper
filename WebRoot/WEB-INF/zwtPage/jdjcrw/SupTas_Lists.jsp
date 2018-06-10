<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>监督检查任务管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_supTas","添加临时任务","${ctx}/jsp/jdjcrw/supTasInitEdit.action?flag=add&dt="+dt.getTime(),700,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supTas","修改监督检查任务","${ctx}/jsp/jdjcrw/supTasInitEdit.action?flag=mod&supTas.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supTas","查看监督检查任务","${ctx}/jsp/jdjcrw/supTasView.action?supTas.id="+row_Id+"&dt="+dt.getTime(),700,400);
        	
        }
        
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_supTas();
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
		                	url : "supTasDel.action",
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
		                        	search_supTas();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_supTas(){
        	var queryParams = {
				"supTas.areaId": $("#areaId").val(),
"supTas.companyName": $("#companyName").val(),
"supTas.taskNum": $("#taskNum").val(),
 "queryCheckTimeStart" :$("#queryCheckTimeStart").val(),
 "queryCheckTimeEnd" :$("#queryCheckTimeEnd").val(),
"supTas.checkUsername": $("#checkUsername").val(),
"supTas.taskState": $("#taskState").val(),
"supTas.taskType": $("#taskType").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        var toolbar = [];
       	 var frozen=[];
        	
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'监督检查结果列表',
				url:'supTasQuerys.action',
				queryParams:{
					"supTas.areaId": $("#areaId").val(),
"supTas.companyName": $("#companyName").val(),
"supTas.taskNum": $("#taskNum").val(),
 "queryCheckTimeStart" :$("#queryCheckTimeStart").val(),
 "queryCheckTimeEnd" :$("#queryCheckTimeEnd").val(),
"supTas.checkUsername": $("#checkUsername").val(),
"supTas.taskState": $("#taskState").val(),
"supTas.taskType": $("#taskType").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaName',title:'所属网格',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'taskNum',title:'任务编号',width:100},
{field:'stime',title:'任务开始时间',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'ftime',title:'任务结束时间',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'checkUsername',title:'检查人员名称',width:100},
{field:'taskType',title:'任务类型',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";}}
		        ]],
				toolbar:toolbar
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
				<th width="15%">所属网格</th>
				<td width="35%"><cus:SelectOneTag property="supTas.areaId" style="width:50%" defaultText='请选择' codeSql="select  row_id as id,GRID_NAME as name from COM_GRI_MAN where delflag = 0" value="${supTas.areaId}" maxlength="127"/></td></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="supTas.companyName" id="companyName" style="width:50%" value="${supTas.companyName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">任务编号</th>
				<td width="35%"><input name="supTas.taskNum" id="taskNum" style="width:50%" value="${supTas.taskNum}" type="text" maxlength="127"></td>
				<th width="15%">检查人员名称</th>
				<td width="35%"><input name="supTas.checkUsername" id="checkUsername" style="width:50%" value="${supTas.checkUsername}" type="text" maxlength="127"></td>
				</tr>
			<tr>
				<th width="15%">任务类型</th>
				<td width="35%"><s:select  id="taskType"  name="supTas.taskType" cssStyle="width:50%" list="#{'请选择':'请选择','计划任务':'计划任务','临时任务':'临时任务'}" theme="simple"/></td>
				</tr>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_supTas()" >查询<b></b></a>&nbsp;
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
