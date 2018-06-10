<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>劳保用品管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_ppeManag","添加劳保用品管理","${ctx}/jsp/lbypgl/ppeManagInitEdit.action?flag=add&dt="+dt.getTime(),700,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_ppeManag","修改劳保用品管理","${ctx}/jsp/lbypgl/ppeManagInitEdit.action?flag=mod&ppeManag.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_ppeManag","查看劳保用品管理","${ctx}/jsp/lbypgl/ppeManagView.action?ppeManag.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_ppeManag();
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
		                	url : "ppeManagDel.action",
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
		                        	search_ppeManag();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_ppeManag(){
        	var queryParams = {
				"ppeManag.areaId": $("#areaId").val(),
"ppeManag.companyName": $("#companyName").val(),
"ppeManag.ppeType": $("#ppeType").val(),
"ppeManag.ppeName": $("#ppeName").val(),
"ppeManag.ppeNo": $("#ppeNo").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        
 var toolbar = [];
        var frozen=[];
        	if('${roleName}'==0){//判断登录角色
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
				title:'劳保用品管理列表',
				url:'ppeManagQuery.action',
				queryParams:{
					"ppeManag.areaId": $("#areaId").val(),
"ppeManag.companyName": $("#companyName").val(),
"ppeManag.ppeType": $("#ppeType").val(),
"ppeManag.ppeName": $("#ppeName").val(),
"ppeManag.ppeNo": $("#ppeNo").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'ppeNo',title:'用品编号',width:100},
{field:'ppeName',title:'用品名称',width:100},
{field:'ppeNum',title:'用品数量',width:100},
{field:'ppeType',title:'用品分类',width:100},
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
			<c:if test='${roleName!=0}'>
				<tr>
					
				<th width="15%">所在区域</th>
				<td width="35%">
					<cus:SelectOneTag style="width:50%"  property="ppeManag.areaId" defaultText='请选择' codeName="企业属地" value="${ppeManag.areaId}" />
				</td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="ppeManag.companyName" style="width:50%" id="companyName" value="${ppeManag.companyName}" type="text" maxlength="127"></td>
			</tr>
			</c:if>
			<tr>
				<th width="15%">用品编号</th>
				<td width="35%"><input name="ppeManag.ppeNo" style="width:50%" id="ppeNo" value="${ppeManag.ppeNo}" type="text" maxlength="127"></td>
				<th width="15%">用品名称</th>
				<td width="35%"><input name="ppeManag.ppeName" style="width:50%" id="ppeName" value="${ppeManag.ppeName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">用品分类</th>
				<td width="35%"><cus:SelectOneTag style="width:50%"  property="ppeManag.ppeType" defaultText='请选择' codeName="劳保用品分类" value="${ppeManag.ppeType}" /></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_ppeManag()" >查询<b></b></a>&nbsp;
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
