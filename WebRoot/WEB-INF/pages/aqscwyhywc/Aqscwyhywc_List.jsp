<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全生产委员会主要工作推进进度月完成情况管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_aqscwyhywc","添加安全生产委员会主要工作推进进度月完成情况","${ctx}/jsp/aqscwyhywc/aqscwyhywcInitEdit.action?flag=add&aqscwyhywc.glId="+"${glId}"+"&dt="+dt.getTime(),700,400);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_aqscwyhywc","修改安全生产委员会主要工作推进进度月完成情况","${ctx}/jsp/aqscwyhywc/aqscwyhywcInitEdit.action?flag=mod&aqscwyhywc.id="+row_Id+"&dt="+dt.getTime(),700,400);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_aqscwyhywc","查看安全生产委员会主要工作推进进度月完成情况","${ctx}/jsp/aqscwyhywc/aqscwyhywcView.action?aqscwyhywc.id="+row_Id+"&dt="+dt.getTime(),700,400);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_aqscwyhywc();
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
		                	url : "aqscwyhywcDel.action",
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
		                        	search_aqscwyhywc();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_aqscwyhywc(){
        	var queryParams = {
				 "queryMonthTimeStart" :$("#queryMonthTimeStart").val(),
 "queryMonthTimeEnd" :$("#queryMonthTimeEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'安全生产委员会主要工作推进进度月完成情况列表',
				url:'aqscwyhywcQuery.action',
				queryParams:{
				"aqscwyhywc.glId": "${glId}",
					 "queryMonthTimeStart" :$("#queryMonthTimeStart").val(),
 "queryMonthTimeEnd" :$("#queryMonthTimeEnd").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'monthTime',title:'月份',width:100,formatter:function(value,rec){return value.substring(0,7);}},
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
					
				<th width="15%">月份</th>
				<td width="35%"><input name="queryMonthTimeStart" id="queryMonthTimeStart"  type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM',maxDate:'#F{$dp.$D(\'queryMonthTimeEnd\')}'})" >
					-<input name="queryMonthTimeEnd" id="queryMonthTimeEnd"  type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'queryMonthTimeStart\')}'})" ></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_aqscwyhywc()" >查询<b></b></a>&nbsp;
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
