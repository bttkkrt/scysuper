<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>告知卡管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_informCard","添加告知卡","${ctx}/jsp/gzk/informCardInitEdit.action?flag=add&dt="+dt.getTime(),1000,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_informCard","修改告知卡","${ctx}/jsp/gzk/informCardInitEdit.action?flag=mod&informCard.id="+row_Id+"&dt="+dt.getTime(),1000,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_informCard","查看告知卡","${ctx}/jsp/gzk/informCardView.action?informCard.id="+row_Id+"&dt="+dt.getTime(),1000,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_informCard();
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
		                	url : "informCardDel.action",
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
		                        	search_informCard();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_informCard(){
        	var queryParams = {
				"informCard.areaId": $("#areaId").val(),
"informCard.companyName": $("#companyName").val(),
"informCard.informType": $("#informType").val(),
"informCard.informName": $("#informName").val(),
"informCard.informNo": $("#informNo").val()
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
				},{
				    text:'导入告知卡',
					iconCls:'icon-add',
					handler:importUser
				
				}];
				frozen=[[
				    {field:'id',checkbox:true}
				]];
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'告知卡列表',
				url:'informCardQuery.action',
				queryParams:{
					"informCard.areaId": $("#areaId").val(),
"informCard.companyName": $("#companyName").val(),
"informCard.informType": $("#informType").val(),
"informCard.informName": $("#informName").val(),
"informCard.informNo": $("#informNo").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'informType',title:'告知卡类别',width:100},
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

	function importUser(){ 
        	createSimpleWindow("importUser","批量导入告知卡","${ctx}/jsp/gzk/initImportInformCard.action", 350, 200);
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
				<td width="35%"><cus:SelectOneTag property="informCard.areaId" style="width:50%;" defaultText='请选择' codeName="企业属地" value="${informCard.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="informCard.companyName" style="width:50%;" id="companyName" value="${informCard.companyName}" type="text" maxlength="127"></td>
			</tr>
			</s:if>
			<tr>
				<th width="15%">告知卡类别</th>
				<td width="35%"><input id="informType" name="informCard.informType" style="width:50%;" value="${informCard.informType}" type="text" maxlength="127"></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_informCard()" >查询<b></b></a>&nbsp;
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
