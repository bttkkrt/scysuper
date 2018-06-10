<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>事故报告调查和处理管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_accRepInvHan","添加事故报告调查和处理","${ctx}/jsp/sgbgdchcl/accRepInvHanInitEdit.action?flag=add&dt="+dt.getTime(),700,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_accRepInvHan","修改事故报告调查和处理","${ctx}/jsp/sgbgdchcl/accRepInvHanInitEdit.action?flag=mod&accRepInvHan.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_accRepInvHan","查看事故报告调查和处理","${ctx}/jsp/sgbgdchcl/accRepInvHanView.action?accRepInvHan.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_accRepInvHan();
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
		                	url : "accRepInvHanDel.action",
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
		                        	search_accRepInvHan();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_accRepInvHan(){
        	var queryParams = {
				"accRepInvHan.areaId": $("#areaId").val(),
"accRepInvHan.companyName": $("#companyName").val(),
"accRepInvHan.accidentId": $("#accidentId").val(),
"accRepInvHan.accidentName": $("#accidentName").val(),
 "queryAccidentTimeStart" :$("#queryAccidentTimeStart").val(),
 "queryAccidentTimeEnd" :$("#queryAccidentTimeEnd").val(),
"accRepInvHan.accidentLevel": $("#accidentLevel").val(),
"accRepInvHan.accidentType": $("#accidentType").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
          var toolbar = [];
       	 var frozen=[];
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
				frozen=[[
				    {field:'id',checkbox:true}
				]];
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'事故报告调查和处理列表',
				url:'accRepInvHanQuery.action',
				queryParams:{
					"accRepInvHan.areaId": $("#areaId").val(),
"accRepInvHan.companyName": $("#companyName").val(),
"accRepInvHan.accidentId": $("#accidentId").val(),
"accRepInvHan.accidentName": $("#accidentName").val(),
 "queryAccidentTimeStart" :$("#queryAccidentTimeStart").val(),
 "queryAccidentTimeEnd" :$("#queryAccidentTimeEnd").val(),
"accRepInvHan.accidentLevel": $("#accidentLevel").val(),
"accRepInvHan.accidentType": $("#accidentType").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'accidentId',title:'事故编号',width:100},
{field:'accidentName',title:'事故名称',width:100},
{field:'accidentTime',title:'事故发生时间',width:100,width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'accidentLevel',title:'事故级别',width:100},
{field:'accidentType',title:'事故类别',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	if('${roleName}'=='0'){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	}else{
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	}

}}				        ]],
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
				<td width="35%"><cus:SelectOneTag property="accRepInvHan.areaId" style="width:50%" defaultText='请选择' codeName="企业属地" value="${accRepInvHan.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="accRepInvHan.companyName" style="width:50%" id="companyName" value="${accRepInvHan.companyName}" type="text" maxlength="127"></td>
			</tr>
			</s:if>
			<tr>
				<th width="15%">事故编号</th>
				<td width="35%"><input name="accRepInvHan.accidentId" style="width:50%" id="accidentId" value="${accRepInvHan.accidentId}" type="text" maxlength="127"></td>
				<th width="15%">事故名称</th>
				<td width="35%"><input name="accRepInvHan.accidentName" style="width:50%" id="accidentName" value="${accRepInvHan.accidentName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">事故发生时间</th>
				<td width="35%"><input name="queryAccidentTimeStart" id="queryAccidentTimeStart" value="<fmt:formatDate type='both' value='${queryAccidentTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryAccidentTimeEnd\')}'})" >
					-<input name="queryAccidentTimeEnd" id="queryAccidentTimeEnd" value="<fmt:formatDate type='both' value='${queryAccidentTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryAccidentTimeStart\')}'})" ></td>
				<th width="15%">事故级别</th>
				<td width="35%"><cus:SelectOneTag property="accRepInvHan.accidentLevel" style="width:50%" defaultText='请选择' codeName="事故级别" value="${accRepInvHan.accidentLevel}" /></td>
			</tr>
			<tr>
				<th width="15%">事故类别</th>
				<td width="35%"><cus:SelectOneTag property="accRepInvHan.accidentType" style="width:50%" defaultText='请选择' codeName="事故类别" value="${accRepInvHan.accidentType}" /></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_accRepInvHan()" >查询<b></b></a>&nbsp;
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
