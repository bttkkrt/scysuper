<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>综合查询</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var loginId = "${loginId}";
        var deptCode = "${deptCode}";
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_entBaseInfo","添加企业基本信息","${ctx}/jsp/qyjbxx/entBaseInfoInitEdit.action?flag=add&dt="+dt.getTime(),800,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_entBaseInfo","修改企业基本信息","${ctx}/jsp/qyjbxx/entBaseInfoInitEdit.action?flag=mod&entBaseInfo.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function check(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_entBaseInfo","审核企业基本信息","${ctx}/jsp/qyjbxx/entBaseInfoCheck.action?flag=mod&entBaseInfo.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_entBaseInfo","查看企业基本信息","${ctx}/jsp/qyjbxx/entBaseInfoView.action?entBaseInfo.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
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
"entBaseInfo.enterprisePossession": $("#enterprisePossession").val(),
"entBaseInfo.enterpriseName": $("#enterpriseName").val(),
"entBaseInfo.enterpriseCategory": $("#enterpriseCategory").val(),
"entBaseInfo.enterpriseScale": $("#enterpriseScale").val(),
"entBaseInfo.gridName": $("#gridName").val(),
"entBaseInfo.enterpriseType": $("#enterpriseType").val(),
"total1": $("#total1").val(),
"total2": $("#total2").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	var toolbar = [];
        	var frozenColumns = [[]];
        	if(loginId == '402880e92db726b5012db729f65f0001')
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
"entBaseInfo.enterprisePossession": $("#enterprisePossession").val(),
"entBaseInfo.enterpriseName": $("#enterpriseName").val(),
"entBaseInfo.enterpriseCategory": $("#enterpriseCategory").val(),
"entBaseInfo.enterpriseScale": $("#enterpriseScale").val(),
"entBaseInfo.gridName": $("#gridName").val(),
"entBaseInfo.enterpriseType": $("#enterpriseType").val(),
"total1": $("#total1").val(),
"total2": $("#total2").val()
				},
				frozenColumns:frozenColumns,
				columns:[[
				          {field:'enterpriseName',title:'企业名称',width:100,formatter:function(value,rec){
	if(rec.ifCz == '1')
	{
		return value;
	}
	else
	{
		return "<font style='color:red'>" + value + "</font>";
	}
}},
				           {field:'enterprisePossessionName',title:'企业属地',width:100},
{field:'registrationNumber',title:'工商注册号',width:100},
{field:'enterpriseCode',title:'组织机构代码',width:100},
{field:'enterpriseLegalName',title:'法人代表姓名',width:100},
{field:'enterpriseNature',title:'企业性质',width:100},
{field:'gridName',title:'所属网格',width:100},
{field:'basePass',title:'企业状态',width:100,formatter:function(value,rec){
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
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	return "<a class='btn_02_mini' onclick=link('"+rec.enterpriseName+"','"+rec.id+"') >查看<b></b></a>";
}}
				        ]],
				toolbar:toolbar
			}));
		});

        
        function link(companyName,row_Id){
            var id="newWindow";
    		var text = companyName+"综合查询";
    		var url ="/jsp/qyjbxx/zhcxTab.action?entBaseInfo.id="+row_Id+"&entBaseInfo.enterpriseName="+encodeURIComponent(companyName);
             window.parent.addTab(id,text,url);
        	
        }
        
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
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.gridName" defaultText='请选择' codeSql="select  GRID_NAME as id,GRID_NAME as name from COM_GRI_MAN where delflag = 0" value="${entBaseInfo.gridName}" maxlength="127"/></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="entBaseInfo.enterpriseName" id="enterpriseName" value="${entBaseInfo.enterpriseName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">行业类别</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterpriseCategory" defaultText='请选择' codeName="行业类别" value="${entBaseInfo.enterpriseCategory}" maxlength="127"/></td>
					<th width="15%">企业规模</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterpriseScale" defaultText='请选择' codeName="企业规模" value="${entBaseInfo.enterpriseScale}" maxlength="127"/></td>
				</tr>
				<tr>	
					<th width="15%">企业属地</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterprisePossession" defaultText='请选择' codeName="企业属地" value="${entBaseInfo.enterprisePossession}" maxlength="127"/></td>
					<th width="15%">企业分类</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterpriseType" defaultText='请选择' codeName="企业分类" value="${entBaseInfo.enterpriseType}" maxlength="127"/></td>
				
				</tr>
				<tr>	
					<th width="15%">总人数</th>
					<td width="35%"><input name="total1" id="total1"   type="text" dataType="n1-7" maxlength="127">-<input name="total2" id="total2"   type="text" dataType="n1-7" maxlength="127"></td>
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
