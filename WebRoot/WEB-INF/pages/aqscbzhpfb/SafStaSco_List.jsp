<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全生产标准化评分表管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_safStaSco","添加安全生产标准化评分表","${ctx}/jsp/aqscbzhpfb/safStaScoInitEdit.action?flag=add&dt="+dt.getTime(),700,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_safStaSco","修改安全生产标准化评分表","${ctx}/jsp/aqscbzhpfb/safStaScoInitEdit.action?flag=mod&safStaSco.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_safStaSco","查看安全生产标准化评分表","${ctx}/jsp/aqscbzhpfb/safStaScoView.action?safStaSco.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_safStaSco();
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
		                	url : "safStaScoDel.action",
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
		                        	search_safStaSco();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_safStaSco(){
        	var queryParams = {
				"safStaSco.areaName": $("#areaName").val(),
 "queryPatingDateStart" :$("#queryPatingDateStart").val(),
 "queryPatingDateEnd" :$("#queryPatingDateEnd").val(),
"safStaSco.companyName": $("#companyName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'安全生产标准化评分表列表',
				url:'safStaScoQuery.action',
				queryParams:{
					"safStaSco.areaName": $("#areaName").val(),
 "queryPatingDateStart" :$("#queryPatingDateStart").val(),
 "queryPatingDateEnd" :$("#queryPatingDateEnd").val(),
"safStaSco.companyName": $("#companyName").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'patingDate',title:'评分时间',width:100},
{field:'ratingUserId',title:'评分人',width:100},
{field:'totalScore',title:'总分',width:100},
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
					
				<th width="15%">所在区域</th>
				<td width="35%"><input name="safStaSco.areaName" id="areaName" value="${safStaSco.areaName}" type="text" maxlength="127"  style="width:50%"></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="safStaSco.companyName" id="companyName" value="${safStaSco.companyName}" type="text" maxlength="127" style="width:50%"></td>
			</tr>
			<tr>
				<th width="15%">评分时间</th>
				<td width="35%"><input name="queryPatingDateStart" id="queryPatingDateStart" value="<fmt:formatDate type='both' value='${queryPatingDateStart}' />" type="text" class="Wdaonclick="WdatePicker(ker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryPatingDateEnd\')}'})" >
					-<input name="queryPatingDateEnd" id="queryPatingDateEnd" value="<fmt:formatDate type='both' value='${queryPatingDateEnd}' />" type="text" class="Wdonclick="WdatePicker(cker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryPatingDateStart\')}'})" ></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_safStaSco()" >查询<b></b></a>&nbsp;
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
