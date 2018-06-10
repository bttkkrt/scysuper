<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业基本信息管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        var loginId = "${loginId}";
        var deptCode = "${deptCode}";
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_entBaseInfo","添加企业基本信息","${ctx}/zwt/entBaseInfoInitEdit.action?flag=add&dt="+dt.getTime(),800,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_entBaseInfo","修改企业基本信息","${ctx}/zwt/entBaseInfoInitEdit.action?flag=mod&entBaseInfo.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function check(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_entBaseInfo","审核企业基本信息","${ctx}/zwt/entBaseInfoCheck.action?flag=mod&entBaseInfo.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_entBaseInfo","查看企业基本信息","${ctx}/zwt/entBaseInfoView.action?entBaseInfo.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_entBaseInfo();
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
		                	url : "entBaseInfoDel.action",
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
		                        	search_entBaseInfo();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_entBaseInfo(){
        	var queryParams = {
				"entBaseInfo.enterpriseName": $("#enterpriseName").val(),
"entBaseInfo.registrationNumber": $("#registrationNumber").val(),
"entBaseInfo.enterpriseCode": $("#enterpriseCode").val(),
"entBaseInfo.enterpriseLegalName": $("#enterpriseLegalName").val(),
"entBaseInfo.enterpriseNature": $("#enterpriseNature").val(),
"entBaseInfo.gridName": $("#gridName").val(),
"entBaseInfo.basePass": $("#basePass").val(),
"entBaseInfo.enterprisePossession": $("#enterprisePossession").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	var toolbar = [];
        	var frozenColumns = [[]];
        	if(loginId == '1')
        	{
        		toolbar = [{
					id:'btncut',
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						del();
					}
				}];
				frozenColumns = [[
				    {field:'id',checkbox:true}
				]]
        	}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'企业基本信息列表',
				url:'entBaseInfoQuery.action',
				queryParams:{
					"entBaseInfo.enterpriseName": $("#enterpriseName").val(),
"entBaseInfo.registrationNumber": $("#registrationNumber").val(),
"entBaseInfo.enterpriseCode": $("#enterpriseCode").val(),
"entBaseInfo.enterpriseLegalName": $("#enterpriseLegalName").val(),
"entBaseInfo.enterpriseNature": $("#enterpriseNature").val(),
"entBaseInfo.gridName": $("#gridName").val(),
"entBaseInfo.basePass": $("#basePass").val(),
"entBaseInfo.enterprisePossession": $("#enterprisePossession").val()
				},
				frozenColumns:frozenColumns,
				columns:[[
				          {field:'enterpriseName',title:'企业名称',width:150,formatter:function(value,rec){
	if(rec.ifCz == '1')
	{
		return value;
	}
	else
	{
		return "<font style='color:red'>" + value + "</font>";
	}
}},
				           {field:'enterprisePossessionName',title:'企业属地',width:60},
{field:'registrationNumber',title:'工商注册号',width:100},
{field:'enterpriseCode',title:'组织机构代码',width:80},
{field:'enterpriseLegalName',title:'法人代表姓名',width:100},
{field:'enterpriseNature',title:'企业性质',width:50},
{field:'gridName',title:'所属网格',width:50},
{field:'basePass',title:'企业状态',width:60,formatter:function(value,rec){
	if(value == '1')
	{
		return "审核通过";
	}
	else if(value == '2')
	{
		return "审核不通过";
	}
	else
	{
		return "待审核";
	}
}},
{field:'op',title:'操作',width:150,formatter:function(value,rec){
var button1="<a class='btn_04_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
var button2="<a class='btn_03_mini' onclick=check('"+rec.id+"')>审核<b></b></a>";
var button3="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	if(deptCode == rec.gridManageteamCode || loginId == '1') 
	{
		button3 += button1;
		if(rec.basePass=="0")
		{
			button3 += button2;
		}
	}
	return button3;
}}
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
					<th width="15%">网格名称</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.gridName" style="width:50%" defaultText='请选择' codeSql="select  GRID_NAME as id,GRID_NAME as name from COM_GRI_MAN where delflag = 0" value="${entBaseInfo.gridName}" maxlength="127"/></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="entBaseInfo.enterpriseName" id="enterpriseName" style="width:50%" value="${entBaseInfo.enterpriseName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">工商注册号</th>
					<td width="35%"><input name="entBaseInfo.registrationNumber" style="width:50%" id="registrationNumber" value="${entBaseInfo.registrationNumber}" type="text" maxlength="127"></td>
					<th width="15%">组织机构代码</th>
					<td width="35%"><input name="entBaseInfo.enterpriseCode" style="width:50%" id="enterpriseCode" value="${entBaseInfo.enterpriseCode}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">法人代表姓名</th>
					<td width="35%"><input name="entBaseInfo.enterpriseLegalName" style="width:50%" id="enterpriseLegalName" value="${entBaseInfo.enterpriseLegalName}" type="text" maxlength="127"></td>
					<th width="15%">企业性质</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterpriseNature" style="width:50%" defaultText='请选择' codeName="企业性质" value="${entBaseInfo.enterpriseNature}" maxlength="127"/></td>
				</tr>
				<tr>
					<th width="15%">企业属地</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterprisePossession" style="width:50%" defaultText='请选择' codeName="企业属地" value="${entBaseInfo.enterprisePossession}" maxlength="127"/></td>
					<th width="15%">企业状态</th>
					<td width="35%"><s:select id="basePass" name="entBaseInfo.basePass" cssStyle="width:50%" list='#{"":"--请选择---","0":"待审核","1":"审核通过","2":"审核不通过"}' theme="simple"></s:select></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_entBaseInfo()" >查询<b></b></a>&nbsp;
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
