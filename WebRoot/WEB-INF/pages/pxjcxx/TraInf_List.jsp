<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>培训基础信息管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_traInf","添加培训基础信息","${ctx}/jsp/pxjcxx/traInfInitEdit.action?flag=add&dt="+dt.getTime(),700,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_traInf","修改培训基础信息","${ctx}/jsp/pxjcxx/traInfInitEdit.action?flag=mod&traInf.id="+row_Id+"&dt="+dt.getTime(),700,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_traInf","查看培训基础信息","${ctx}/jsp/pxjcxx/traInfView.action?traInf.id="+row_Id+"&dt="+dt.getTime(),700,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_traInf();
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
		                	url : "traInfDel.action",
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
		                        	search_traInf();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_traInf(){
        	var queryParams = {
				"traInf.trainTheme": $("#trainTheme").val(),
 "queryStartTimeStart" :$("#queryStartTimeStart").val(),
 "queryStartTimeEnd" :$("#queryStartTimeEnd").val(),
 "queryEndTimeStart" :$("#queryEndTimeStart").val(),
 "queryEndTimeEnd" :$("#queryEndTimeEnd").val(),
"traInf.trainMethod": $("#trainMethod").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        var toolbar = [];
        	if('${roleName}'=='1'){//判断登录角色
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
				
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'培训基础信息列表',
				url:'traInfQuery.action',
				queryParams:{
					"traInf.trainTheme": $("#trainTheme").val(),
 "queryStartTimeStart" :$("#queryStartTimeStart").val(),
 "queryStartTimeEnd" :$("#queryStartTimeEnd").val(),
 "queryEndTimeStart" :$("#queryEndTimeStart").val(),
 "queryEndTimeEnd" :$("#queryEndTimeEnd").val(),
"traInf.trainMethod": $("#trainMethod").val()
				},
				frozenColumns:[[
				    {field:'id',width:20,formatter:function(value,rec){
				    		var opt = '<input type="checkbox" name="xxx" value='+rec.id+'>'; 
						    if(rec.createUserID != "${sessionScope['LOGIN_USER_ID']}"){
								opt = ''; 
						    } 
						    return opt ; 

				    }}
				]],
				columns:[[
				          {field:'trainTheme',title:'培训主题',width:100},
{field:'startTime',title:'培训开始时间',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'endTime',title:'培训结束时间',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'trainMethod',title:'培训方式',width:100},
{field:'certificateNum',title:'发证数',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
if("${sessionScope['LOGIN_USER_ID']}"==rec.createUserID){
	return  button1;
}else{
	return button2;
}
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
					
				<th width="15%">培训主题</th>
				<td width="35%"><input name="traInf.trainTheme" id="trainTheme" style="width: 60%" value="${traInf.trainTheme}" type="text" maxlength="127"></td>
				<th width="15%">培训开始时间</th>
				<td width="35%"><input name="queryStartTimeStart" id="queryStartTimeStart" value="<fmt:formatDate type='date' value='${queryStartTimeStart}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryStartTimeEnd\')}'})" >
					-<input name="queryStartTimeEnd" id="queryStartTimeEnd" value="<fmt:formatDate type='date' value='${queryStartTimeEnd}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryStartTimeStart\')}'})" ></td>
			</tr>
			<tr>
				<th width="15%">培训结束时间</th>
				<td width="35%"><input name="queryEndTimeStart" id="queryEndTimeStart" value="<fmt:formatDate type='date' value='${queryEndTimeStart}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryEndTimeEnd\')}'})" >
					-<input name="queryEndTimeEnd" id="queryEndTimeEnd" value="<fmt:formatDate type='date' value='${queryEndTimeEnd}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryEndTimeStart\')}'})" ></td>
				<th width="15%">培训方式</th>
				<td width="35%"><input name="traInf.trainMethod" style="width: 60%" id="trainMethod" value="${traInf.trainMethod}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_traInf()" >查询<b></b></a>&nbsp;
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
