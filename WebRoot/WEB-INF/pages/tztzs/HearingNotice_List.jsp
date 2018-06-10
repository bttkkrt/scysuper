<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>听证通知书管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_hearingNotice","添加听证通知书","${ctx}/jsp/tztzs/hearingNoticeInitEdit.action?flag=add&dt="+dt.getTime(),700,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_hearingNotice","修改听证通知书","${ctx}/jsp/tztzs/hearingNoticeInitEdit.action?flag=mod&hearingNotice.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_hearingNotice","查看听证通知书","${ctx}/jsp/tztzs/hearingNoticeView.action?hearingNotice.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_hearingNotice();
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
		                	url : "hearingNoticeDel.action",
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
		                        	search_hearingNotice();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_hearingNotice(){
        	var queryParams = {
				"hearingNotice.hearingChairperson": $("#hearingChairperson").val(),
"hearingNotice.clerk": $("#clerk").val(),
"hearingNotice.hearingOfficer": $("#hearingOfficer").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'听证通知书列表',
				url:'hearingNoticeQuery.action',
				queryParams:{
					"hearingNotice.hearingChairperson": $("#hearingChairperson").val(),
"hearingNotice.clerk": $("#clerk").val(),
"hearingNotice.hearingOfficer": $("#hearingOfficer").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'relatedId',title:'关联文书编号',width:100},
{field:'hearingTime',title:'听证会时间',width:100},
{field:'clerk',title:'书记员',width:100},
{field:'hearingOfficer',title:'听证员',width:100},
{field:'hearingChairperson',title:'听证主持人',width:100},
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
					
				<th width="15%">听证主持人</th>
				<td width="35%"><input name="hearingNotice.hearingChairperson" id="hearingChairperson" value="${hearingNotice.hearingChairperson}" type="text"></td>
				<th width="15%">书记员</th>
				<td width="35%"><input name="hearingNotice.clerk" id="clerk" value="${hearingNotice.clerk}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">听证员</th>
				<td width="35%"><input name="hearingNotice.hearingOfficer" id="hearingOfficer" value="${hearingNotice.hearingOfficer}" type="text"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_hearingNotice()" >查询<b></b></a>&nbsp;
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
