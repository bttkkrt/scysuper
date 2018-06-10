<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全生产管理经理培训管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_managerTraining","添加安全生产管理经理培训","${ctx}/jsp/aqscgljlpx/managerTrainingInitEdit.action?flag=add&dt="+dt.getTime(),800,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_managerTraining","修改安全生产管理经理培训","${ctx}/jsp/aqscgljlpx/managerTrainingInitEdit.action?flag=mod&managerTraining.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_managerTraining","查看安全生产管理经理培训","${ctx}/jsp/aqscgljlpx/managerTrainingView.action?managerTraining.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_managerTraining();
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
		                	url : "managerTrainingDel.action",
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
		                        	search_managerTraining();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_managerTraining(){
        	var queryParams = {
				"managerTraining.areaId": $("#areaId").val(),
 "queryTrainingTimeStart" :$("#queryTrainingTimeStart").val(),
 "queryTrainingTimeEnd" :$("#queryTrainingTimeEnd").val(),
  "queryTrainingTimeEndStart" :$("#queryTrainingTimeEndStart").val(),
 "queryTrainingTimeEndEnd" :$("#queryTrainingTimeEndEnd").val(),
"managerTraining.trainingCardnum": $("#trainingCardnum").val(),
"managerTraining.trainingPersonName": $("#trainingPersonName").val(),
"managerTraining.companyName": $("#companyName").val()
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
				}];
				};
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'安全生产管理经理培训列表',
				url:'managerTrainingQuery.action',
				queryParams:{
					"managerTraining.areaId": $("#areaId").val(),
 "queryTrainingTimeStart" :$("#queryTrainingTimeStart").val(),
 "queryTrainingTimeEnd" :$("#queryTrainingTimeEnd").val(),
  "queryTrainingTimeEndStart" :$("#queryTrainingTimeEndStart").val(),
 "queryTrainingTimeEndEnd" :$("#queryTrainingTimeEndEnd").val(),
"managerTraining.trainingCardnum": $("#trainingCardnum").val(),
"managerTraining.trainingPersonName": $("#trainingPersonName").val(),
"managerTraining.companyName": $("#companyName").val()
				},
				frozenColumns:[[
				    {field:'${roleName}'=='0'?'id':'',
				    checkbox:'${roleName}'=='0'?true:false}
				]],
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'trainingPersonName',title:'安全生产管理经理姓名',width:100},
{field:'trainingCardnum',title:'证书号码',width:100},
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
		}
}
}
				        ]],
				toolbar:toolbar
				})); 
			if('${roleName}'=='0'){//判断登录角色
        	$('#pagination').datagrid('hideColumn', 'areaName');
        	$('#pagination').datagrid('hideColumn', 'companyName');
        	}
		});

        
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
				<td width="35%"><cus:SelectOneTag property="managerTraining.areaId" style="width:50%" defaultText='请选择' codeName="企业属地" value="${managerTraining.areaId}"  maxlength="127"/></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="managerTraining.companyName" style="width:50%" id="companyName" value="${managerTraining.companyName}" type="text" maxlength="127"></td>
			</tr></s:if>
			<tr>
				<th width="15%">安全生产管理经理姓名</th>
				<td width="35%"><input name="managerTraining.trainingPersonName" style="width:50%" id="trainingPersonName" value="${managerTraining.trainingPersonName}" type="text" maxlength="127"></td>
				<th width="15%">证书号码</th>
				<td width="35%"><input id="trainingCardnum" name="managerTraining.trainingCardnum" style="width:50%" value="${managerTraining.trainingCardnum}" type="text"  maxlength="127"></td>
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
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_managerTraining()" >查询<b></b></a>&nbsp;
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
