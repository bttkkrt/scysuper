<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全生产事故情况管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_aqscsgqk","添加安全生产事故情况","${ctx}/jsp/aqscsgqk/aqscsgqkInitEdit.action?flag=add&dt="+dt.getTime(),700,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_aqscsgqk","修改安全生产事故情况","${ctx}/jsp/aqscsgqk/aqscsgqkInitEdit.action?flag=mod&aqscsgqk.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_aqscsgqk","查看安全生产事故情况","${ctx}/jsp/aqscsgqk/aqscsgqkView.action?aqscsgqk.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
         function exportData()
        {
         var queryMonthTimeStart = $("#queryMonthTimeStart").val();
            if(queryMonthTimeStart == ""){
               alert("请选择导出时间");
            }else{
        	document.myform.action = "${ctx}/jsp/aqscsgqk/aqscsgqkExport.action";
        	document.myform.submit();
        	}
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_aqscsgqk();
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
		                	url : "aqscsgqkDel.action",
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
		                        	search_aqscsgqk();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_aqscsgqk(){
        	var queryParams = {
				"aqscsgqk.areaName": $("#areaName").val(),
 "queryMonthTimeStart" :$("#queryMonthTimeStart").val(),
 "queryMonthTimeEnd" :$("#queryMonthTimeEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'安全生产事故情况列表',
				url:'aqscsgqkQuery.action',
				queryParams:{
					"aqscsgqk.areaName": $("#areaName").val(),
 "queryMonthTimeStart" :$("#queryMonthTimeStart").val(),
 "queryMonthTimeEnd" :$("#queryMonthTimeEnd").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				         
{field:'monthTime',title:'月份',width:100,formatter:function(value,rec){return value.substring(0,7);}},
 {field:'areaName',title:'区域',width:100},
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
<form name="myform" method="post">
<input type="hidden" name="flag" value="1"/>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
				<tr>
				<th width="15%">月份</th>
				<td width="35%"><input name="queryMonthTimeStart" id="queryMonthTimeStart"  type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})" >
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_aqscsgqk()" >查询<b></b></a>&nbsp;
						<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;	
						<a href="###" class="btn_01" onclick="exportData();" >导出<b></b></a>&nbsp;					
					</td>
				</tr>
			</table>
		</div>
		
		<div id="pagination" >
		</div>
		</div>
		</div>
	</div>
	</form>
</body>
</html>
