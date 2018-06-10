<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>先行登记保存证据审批表管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_preserveEvidence","添加先行登记保存证据审批表","${ctx}/jsp/xxdjzjspb/preserveEvidenceInitEdit.action?flag=add&dt="+dt.getTime(),700,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_preserveEvidence","修改先行登记保存证据审批表","${ctx}/jsp/xxdjzjspb/preserveEvidenceInitEdit.action?flag=mod&preserveEvidence.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_preserveEvidence","查看先行登记保存证据审批表","${ctx}/jsp/xxdjzjspb/preserveEvidenceView.action?preserveEvidence.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_preserveEvidence();
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
		                	url : "preserveEvidenceDel.action",
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
		                        	search_preserveEvidence();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_preserveEvidence(){
        	var queryParams = {
				"preserveEvidence.undertaker": $("#undertaker").val(),
"preserveEvidence.officePerson": $("#officePerson").val(),
"preserveEvidence.departPerson": $("#departPerson").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'先行登记保存证据审批表列表',
				url:'preserveEvidenceQuery.action',
				queryParams:{
					"preserveEvidence.undertaker": $("#undertaker").val(),
"preserveEvidence.officePerson": $("#officePerson").val(),
"preserveEvidence.departPerson": $("#departPerson").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'relatedId',title:'关联文书编号',width:100},
{field:'preserveMethod',title:'保存方式',width:100},
{field:'undertaker',title:'承办人',width:100},
{field:'officeComment',title:'机关负责人意见',width:100},
{field:'departPerson',title:'部门负责人',width:100},
{field:'departComment',title:'部门负责人意见',width:100},
{field:'officePerson',title:'机关负责人',width:100},
{field:'undertakerComment',title:'承办人意见',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";}}
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
				<td width="35%"><input name="preserveEvidence.undertaker" id="undertaker" value="${preserveEvidence.undertaker}" type="text"></td>
				<th width="15%">机关负责人</th>
				<td width="35%"><input name="preserveEvidence.officePerson" id="officePerson" value="${preserveEvidence.officePerson}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">部门负责人</th>
				<td width="35%"><input name="preserveEvidence.departPerson" id="departPerson" value="${preserveEvidence.departPerson}" type="text"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_preserveEvidence()" >查询<b></b></a>&nbsp;
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
