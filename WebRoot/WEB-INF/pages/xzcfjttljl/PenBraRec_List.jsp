<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>行政处罚集体讨论记录管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_penBraRec","添加行政处罚集体讨论记录","${ctx}/jsp/xzcfjttljl/penBraRecInitEdit.action?flag=add&dt="+dt.getTime(),700,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_penBraRec","修改行政处罚集体讨论记录","${ctx}/jsp/xzcfjttljl/penBraRecInitEdit.action?flag=mod&penBraRec.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_penBraRec","查看行政处罚集体讨论记录","${ctx}/jsp/xzcfjttljl/penBraRecView.action?penBraRec.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_penBraRec();
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
		                	url : "penBraRecDel.action",
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
		                        	search_penBraRec();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_penBraRec(){
        	var queryParams = {
				"penBraRec.chairperson": $("#chairperson").val(),
"penBraRec.recordPerson": $("#recordPerson").val(),
"penBraRec.reportPerson": $("#reportPerson").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'行政处罚集体讨论记录列表',
				url:'penBraRecQuery.action',
				queryParams:{
					"penBraRec.chairperson": $("#chairperson").val(),
"penBraRec.recordPerson": $("#recordPerson").val(),
"penBraRec.reportPerson": $("#reportPerson").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'relatedId',title:'关联文书编号',width:100},
{field:'recordPerson',title:'记录人',width:100},
{field:'reportPerson',title:'汇报人',width:100},
{field:'chairperson',title:'主持人',width:100},
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
					
				<th width="15%">主持人</th>
				<td width="35%"><input name="penBraRec.chairperson" id="chairperson" value="${penBraRec.chairperson}" type="text"></td>
				<th width="15%">记录人</th>
				<td width="35%"><input name="penBraRec.recordPerson" id="recordPerson" value="${penBraRec.recordPerson}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">汇报人</th>
				<td width="35%"><input name="penBraRec.reportPerson" id="reportPerson" value="${penBraRec.reportPerson}" type="text"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_penBraRec()" >查询<b></b></a>&nbsp;
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
