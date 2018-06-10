<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业安全生产承诺管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_proCom","添加企业安全生产承诺","${ctx}/jsp/qyaqsccn/proComInitEdit.action?flag=add&dt="+dt.getTime(),700,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_proCom","修改企业安全生产承诺","${ctx}/jsp/qyaqsccn/proComInitEdit.action?flag=mod&proCom.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_proCom","查看企业安全生产承诺","${ctx}/jsp/qyaqsccn/proComView.action?proCom.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_proCom();
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
		                	url : "proComDel.action",
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
		                        	search_proCom();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_proCom(){
        	var queryParams = {
				"proCom.companyName": $("#companyName").val(),
"proCom.areaId": $("#areaId").val(),
 "queryCommitmentYearStart" :$("#queryCommitmentYearStart").val(),
 "queryCommitmentYearEnd" :$("#queryCommitmentYearEnd").val()
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
				title:'企业安全生产承诺列表',
				url:'proComQuery.action',
				queryParams:{
					"proCom.companyName": $("#companyName").val(),
"proCom.areaId": $("#areaId").val(),
 "queryCommitmentYearStart" :$("#queryCommitmentYearStart").val(),
 "queryCommitmentYearEnd" :$("#queryCommitmentYearEnd").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'commitmentYear',title:'年度',width:100,formatter:function(value,rec){return value.substring(0,4);}},
{field:'commitmentContent',title:'承诺内容',width:100,formatter:function(value,rec){
if(value.length>20)
{
	return value.substring(0,20)+"...";
}
else
{
	return value;
}
}},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
if("${sessionScope['LOGIN_USER_ID']}"==rec.createUserID){
	return  button1;
}else{
	return button2;
}
}}					        ]],
				toolbar:toolbar
			}));
			if('${roleName}'=='0'){//判断登录角色
        	$('#pagination').datagrid('hideColumn', 'areaId');
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
				<td width="35%"><cus:SelectOneTag property="proCom.areaId" defaultText='请选择' codeName="企业属地" value="${proCom.areaId}" style="width:50%"/></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="proCom.companyName" id="companyName" value="${proCom.companyName}" type="text" maxlength="127" style="width:50%"></td>
			</tr>
			</s:if>
			<tr>
				<th width="15%">年度</th>
				<td width="35%"><input name="queryCommitmentYearStart" id="queryCommitmentYearStart" value="<fmt:formatDate type='both' value='${queryCommitmentYearStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy',maxDate:'#F{$dp.$D(\'queryCommitmentYearEnd\')}'})" >
					-<input name="queryCommitmentYearEnd" id="queryCommitmentYearEnd" value="<fmt:formatDate type='both' value='${queryCommitmentYearEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy',minDate:'#F{$dp.$D(\'queryCommitmentYearStart\')}'})" ></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_proCom()" >查询<b></b></a>&nbsp;
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
