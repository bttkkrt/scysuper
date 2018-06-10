<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>年度职业病防治经费管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_occDisPre","添加年度职业病防治经费","${ctx}/jsp/ndzybfzjf/occDisPreInitEdit.action?flag=add&dt="+dt.getTime(),800,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occDisPre","修改年度职业病防治经费","${ctx}/jsp/ndzybfzjf/occDisPreInitEdit.action?flag=mod&occDisPre.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occDisPre","查看年度职业病防治经费","${ctx}/jsp/ndzybfzjf/occDisPreView.action?occDisPre.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_occDisPre();
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
		                	url : "occDisPreDel.action",
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
		                        	search_occDisPre();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_occDisPre(){
        	var queryParams = {
				"occDisPre.areaId": $("#areaId").val(),
"occDisPre.companyName": $("#companyName").val(),
"occDisPre.jshxYear": $("#jshxYear").val(),
"occDisPre.jshxUse": $("#jshxUse").val()
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
				title:'年度职业病防治经费列表',
				url:'occDisPreQuery.action',
				queryParams:{
					"occDisPre.areaId": $("#areaId").val(),
"occDisPre.companyName": $("#companyName").val(),
"occDisPre.jshxYear": $("#jshxYear").val(),
"occDisPre.jshxUse": $("#jshxUse").val()
				},
				frozenColumns:[[
				    {field:'${roleName}'=='0'?'id':'',
				    checkbox:'${roleName}'=='0'?true:false}
				]],
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'jshxYear',title:'年度',width:100},
{field:'jshxUse',title:'用途',width:100},
{field:'attachment',title:'经费',width:100},
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
				<td width="35%"><cus:SelectOneTag property="occDisPre.areaId" defaultText='请选择' codeName="企业属地" value="${occDisPre.areaId}" style="width:50%"/></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="occDisPre.companyName" id="companyName" value="${occDisPre.companyName}" type="text" maxlength="127" style="width:50%"></td>
			</tr></s:if>
			<tr>
				<th width="15%">年度</th>
				<td width="35%"><input id="jshxYear" name="occDisPre.jshxYear" value="${occDisPre.jshxYear}" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy'})"  maxlength="127" style="width:50%"></td>
				<th width="15%">用途</th>
				<td width="35%"><input name="occDisPre.jshxUse" id="jshxUse" value="${occDisPre.jshxUse}" type="text" maxlength="127" style="width:50%"></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_occDisPre()" >查询<b></b></a>&nbsp;
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
