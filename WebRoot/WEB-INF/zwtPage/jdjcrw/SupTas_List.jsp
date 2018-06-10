<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>监督检查任务管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_supTas","添加临时任务","${ctx}/jsp/jdjcrw/supTasInitEdit.action?flag=add&dt="+dt.getTime(),900,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supTas","修改监督检查任务","${ctx}/jsp/jdjcrw/supTasInitEdit.action?flag=mod&supTas.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supTas","查看监督检查任务","${ctx}/jsp/jdjcrw/supTasView.action?supTas.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        
        function sbxx(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supTas","上报检查信息","${ctx}/jsp/jdjcrw/supTasSbxx.action?supTas.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        
        function createXcjl(row_Id)
       {
       		var dt=new Date();
            createSimpleWindow("win_supTas","现场检查记录","${ctx}/jsp/jdjcrw/supTasXcjc.action?supTas.id="+row_Id+"&dt="+dt.getTime(),900,550);
       }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_supTas();
        }
        function del(){
        	var rows = document.getElementsByName("xxx");
        	var ids = "";
        	var len = rows.length;
			for(var i=0;i<len;i++){
				if(rows[i].checked){
					ids += rows[i].value+"|";
				}
			}
			if(ids == ""){
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
        	if(('${roleName}'==11)||('${roleName}'==09)){//判断登录角色
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
				frozen=[[
				    {field:'id',width:20,formatter:function(value,rec){
				    		var opt = '<input type="checkbox" name="xxx" value='+rec.id+'>'; 
						    if((rec.createUserID != "${sessionScope['LOGIN_USER_ID']}")||(rec.taskType=="计划任务")){
								opt = ''; 
						    } 
						    return opt ; 

				    }}
				]];
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'监督检查任务列表',
				url:'supTasQuery.action',
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
				          {field:'areaName',title:'所属网格',width:60},
{field:'companyName',title:'企业名称',width:100},
{field:'taskNum',title:'任务编号',width:120},
{field:'stime',title:'任务开始时间',width:60,formatter:function(value,rec){return value.substring(0,10);}},
{field:'ftime',title:'任务结束时间',width:60,formatter:function(value,rec){return value.substring(0,10);}},
{field:'checkUsername',title:'检查人员名称',width:60},
{field:'taskState',title:'任务状态',width:50},
{field:'taskType',title:'任务类型',width:50},
{field:'op',title:'操作',width:150,formatter:function(value,rec){


var button1="<a class='btn_03_mini' onclick=sbxx('"+rec.id+"')>上报<b></b></a>&nbsp;";
var button2="<a class='btn_04_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>&nbsp;";
var button4="<a class='btn_05_mini' onclick=createXcjl('"+rec.id+"') >现场检查记录<b></b></a>";
var button = "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;";
if(rec.taskState=="未完成"&&(rec.taskType=="临时任务")&&("${sessionScope['LOGIN_USER_ID']}"==rec.createUserID)){
	button += button2;
}
if(rec.taskState=="未完成"&&("${sessionScope['LOGIN_USER_ID']}"==rec.checkUserid)){
	button +=  button1;
}
if("${sessionScope['LOGIN_USER_ID']}"==rec.checkUserid&&rec.taskState=="已完成")
{
	button += button4;
}
return button;

}}				        ]],
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
				<td width="35%"><cus:SelectOneTag property="supTas.areaId" style="width:50%" defaultText='请选择' codeSql="select  row_id as id,GRID_NAME as name from COM_GRI_MAN where delflag = 0" value="${supTas.areaId}" maxlength="127"/></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="supTas.companyName" style="width:50%" id="companyName" value="${supTas.companyName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">任务编号</th>
				<td width="35%"><input name="supTas.taskNum" id="taskNum" style="width:50%" value="${supTas.taskNum}" type="text" maxlength="127"></td>
				<th width="15%">检查人员名称</th>
				<td width="35%"><input name="supTas.checkUsername" id="checkUsername" style="width:50%" value="${supTas.checkUsername}" type="text" maxlength="127"></td>
				</tr>
			<tr>
				<th width="15%">任务状态</th>
				<td width="35%"><s:select  id="taskState"  name="supTas.taskState" cssStyle="width:50%" list="#{'请选择':'请选择','未完成':'未完成','已完成':'已完成'}" theme="simple"/></td>
		
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
