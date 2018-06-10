<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>法律法规管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_law","添加法律法规","${ctx}/jsp/flfg/lawInitEdit.action?flag=add&dt="+dt.getTime(),800,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_law","修改法律法规","${ctx}/jsp/flfg/lawInitEdit.action?flag=mod&law.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_law","查看法律法规","${ctx}/jsp/flfg/lawView.action?law.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_law();
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
		                	url : "lawDel.action",
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
		                        	search_law();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_law(){
        	var queryParams = {
				"law.infoTitle": $("#infoTitle").val(),
"law.numbers": $("#numbers").val(),
 "queryPublicTimeStart" :$("#queryPublicTimeStart").val(),
 "queryPublicTimeEnd" :$("#queryPublicTimeEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'法律法规列表',
				url:'lawQuery.action',
				queryParams:{
					"law.infoTitle": $("#infoTitle").val(),
"law.numbers": $("#numbers").val(),
 "queryPublicTimeStart" :$("#queryPublicTimeStart").val(),
 "queryPublicTimeEnd" :$("#queryPublicTimeEnd").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'infoTitle',title:'信息名称',width:100},
{field:'publicAgency',title:'发布机构',width:100},
{field:'numbers',title:'索引号',width:100},
{field:'publicTime',title:'发布时间',width:100,formatter:function(value,rec){
						return value.substring(0,10);					
					}},
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
					
				<th width="15%">信息名称</th>
				<td width="35%"><input name="law.infoTitle" id="infoTitle" value="${law.infoTitle}" type="text" style="width:50%"></td>
				<th width="15%">索引号</th>
				<td width="35%"><input name="law.numbers" id="numbers" value="${law.numbers}" type="text" style="width:50%"></td>
			</tr>
			<tr>
				<th width="15%">发布时间</th>
				<td width="35%"><input name="queryPublicTimeStart" id="queryPublicTimeStart" value="<fmt:formatDate type='date' value='${queryPublicTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryPublicTimeEnd\')}'})" >
					-<input name="queryPublicTimeEnd" id="queryPublicTimeEnd" value="<fmt:formatDate type='date' value='${queryPublicTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryPublicTimeStart\')}'})" ></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_law()" >查询<b></b></a>&nbsp;
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
