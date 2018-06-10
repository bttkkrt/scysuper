<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>班组长培训管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_teaLeaTra","添加班组长培训","${ctx}/jsp/bzzpx/teaLeaTraInitEdit.action?flag=add&dt="+dt.getTime(),800,450);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_teaLeaTra","修改班组长培训","${ctx}/jsp/bzzpx/teaLeaTraInitEdit.action?flag=mod&teaLeaTra.id="+row_Id+"&dt="+dt.getTime(),800,450);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_teaLeaTra","查看班组长培训","${ctx}/jsp/bzzpx/teaLeaTraView.action?teaLeaTra.id="+row_Id+"&dt="+dt.getTime(),800,450);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_teaLeaTra();
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
		                	url : "teaLeaTraDel.action",
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
		                        	search_teaLeaTra();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_teaLeaTra(){
        	var queryParams = {
				"teaLeaTra.areaId": $("#areaId").val(),
"teaLeaTra.companyName": $("#companyName").val(),
"teaLeaTra.trainingName": $("#trainingName").val(),
"teaLeaTra.trainingCardnum": $("#trainingCardnum").val(),
 "queryTrainingTimeStart" :$("#queryTrainingTimeStart").val(),
 "queryTrainingTimeEnd" :$("#queryTrainingTimeEnd").val(),
  "queryTrainingTimeEndStart" :$("#queryTrainingTimeEndStart").val(),
 "queryTrainingTimeEndEnd" :$("#queryTrainingTimeEndEnd").val(),
"teaLeaTra.trainingWorkshopName": $("#trainingWorkshopName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        var toolbar = [];
        	if('${roleName}'=='0'){//判断登录角色
				toolbar = [{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						addNew();
					}
				},{
					id:'btncut',
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						del();
					}
				},{
					text:'导入班组长培训',
					iconCls:'icon-add',
					handler:importUser
				}];
				};
        
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'班组长培训列表',
				url:'teaLeaTraQuery.action',
				queryParams:{
					"teaLeaTra.areaId": $("#areaId").val(),
"teaLeaTra.companyName": $("#companyName").val(),
"teaLeaTra.trainingName": $("#trainingName").val(),
"teaLeaTra.trainingCardnum": $("#trainingCardnum").val(),
 "queryTrainingTimeStart" :$("#queryTrainingTimeStart").val(),
 "queryTrainingTimeEnd" :$("#queryTrainingTimeEnd").val(),
  "queryTrainingTimeEndStart" :$("#queryTrainingTimeEndStart").val(),
 "queryTrainingTimeEndEnd" :$("#queryTrainingTimeEndEnd").val(),
"teaLeaTra.trainingWorkshopName": $("#trainingWorkshopName").val()
				},
				frozenColumns:[[
				   {field:'${roleName}'=='0'?'id':'',
				    checkbox:'${roleName}'=='0'?true:false}
				]],
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'trainingName',title:'姓名',width:100},
{field:'trainingWorkshopName',title:'车间名称',width:100},
{field:'trainingTime',title:'培训开始时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
{field:'trainingTimeEnd',title:'培训结束时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
		if('${roleName}'!='0'){
			return button1;
		}
		else{
			return button2;
		}}
}
				        ]],
				toolbar:toolbar
			})); 
			if('${roleName}'=='0'){//判断登录角色
        	$('#pagination').datagrid('hideColumn', 'areaName');
        	$('#pagination').datagrid('hideColumn', 'companyName');
        	}
		});
			
		function importUser(){ 
        	createSimpleWindow("importUser","批量导入班组长培训","${ctx}/jsp/bzzpx/initImportTeaLeaTra.action", 350, 200);
        }
        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
				<s:if test='roleName!="0"'>
				<tr>
					
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag property="teaLeaTra.areaId" style="width:50%" defaultText='请选择' codeName="企业属地" value="${teaLeaTra.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="teaLeaTra.companyName" style="width:50%" id="companyName" value="${teaLeaTra.companyName}" type="text" maxlength="127"></td>
			</tr></s:if>
			<tr>
				<th width="15%">姓名</th>
				<td width="35%"><input name="teaLeaTra.trainingName" style="width:50%" id="trainingName" value="${teaLeaTra.trainingName}" type="text" maxlength="127"></td>
				<th width="15%">车间名称</th>
				<td width="35%"><input name="teaLeaTra.trainingWorkshopName" style="width:50%" id="trainingWorkshopName" value="${teaLeaTra.trainingWorkshopName}" type="text" maxlength="127"></td>
			</tr>
			<tr>	
				<th width="15%">培训开始时间</th>
				<td width="35%"><input name="queryTrainingTimeStart" id="queryTrainingTimeStart" value="<fmt:formatDate type='date' value='${queryTrainingTimeStart}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryTrainingTimeEnd\')}'})" >
					-<input name="queryTrainingTimeEnd" id="queryTrainingTimeEnd" value="<fmt:formatDate type='date' value='${queryTrainingTimeEnd}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryTrainingTimeStart\')}'})" ></td>
				<th width="15%">培训结束时间</th>
				<td width="35%"><input name="queryTrainingTimeEndStart" id="queryTrainingTimeEndStart" value="<fmt:formatDate type='date' value='${queryTrainingTimeEndStart}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryTrainingTimeEndEnd\')}'})" >
					-<input name="queryTrainingTimeEndEnd" id="queryTrainingTimeEndEnd" value="<fmt:formatDate type='date' value='${queryTrainingTimeEndEnd}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryTrainingTimeEndStart\')}'})" ></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_teaLeaTra()" >查询<b></b></a>&nbsp;
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
