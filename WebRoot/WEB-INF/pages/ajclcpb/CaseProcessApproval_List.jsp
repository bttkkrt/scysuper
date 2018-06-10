<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>案件处理呈批表管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_caseProcessApproval","添加案件处理呈批表","${ctx}/jsp/ajclcpb/caseProcessApprovalInitEdit.action?flag=add&dt="+dt.getTime(),700,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_caseProcessApproval","修改案件处理呈批表","${ctx}/jsp/ajclcpb/caseProcessApprovalInitEdit.action?flag=mod&caseProcessApproval.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_caseProcessApproval","查看案件处理呈批表","${ctx}/jsp/ajclcpb/caseProcessApprovalView.action?caseProcessApproval.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_caseProcessApproval();
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
		                	url : "caseProcessApprovalDel.action",
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
		                        	search_caseProcessApproval();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_caseProcessApproval(){
        	var queryParams = {
				"caseProcessApproval.undertaker": $("#undertaker").val(),
"caseProcessApproval.approverPerson": $("#approverPerson").val(),
"caseProcessApproval.checkPerson": $("#checkPerson").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'案件处理呈批表列表',
				url:'caseProcessApprovalQuery.action',
				queryParams:{
					"caseProcessApproval.undertaker": $("#undertaker").val(),
"caseProcessApproval.approverPerson": $("#approverPerson").val(),
"caseProcessApproval.checkPerson": $("#checkPerson").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'relatedId',title:'关联文书编号',width:100},
{field:'approverPerson',title:'审批人',width:100},
{field:'checkPerson',title:'审核人',width:100},
{field:'undertaker',title:'承办人',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_01_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_01_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";}}
				        ]],
				toolbar:[{
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
				}]
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
					
				<th width="15%">承办人</th>
				<td width="35%"><input name="caseProcessApproval.undertaker" id="undertaker" value="${caseProcessApproval.undertaker}" type="text" maxlength="127"></td>
				<th width="15%">审批人</th>
				<td width="35%"><input name="caseProcessApproval.approverPerson" id="approverPerson" value="${caseProcessApproval.approverPerson}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">审核人</th>
				<td width="35%"><input name="caseProcessApproval.checkPerson" id="checkPerson" value="${caseProcessApproval.checkPerson}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_caseProcessApproval()" >查询<b></b></a>&nbsp;
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
