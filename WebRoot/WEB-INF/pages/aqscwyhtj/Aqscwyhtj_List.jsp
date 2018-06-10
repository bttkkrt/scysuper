<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全生产委员会主要工作推进进度年度计划管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_aqscwyhtj","添加安全生产委员会主要工作推进进度年度计划","${ctx}/jsp/aqscwyhtj/aqscwyhtjInitEdit.action?flag=add&dt="+dt.getTime(),850,400);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_aqscwyhtj","修改安全生产委员会主要工作推进进度年度计划","${ctx}/jsp/aqscwyhtj/aqscwyhtjInitEdit.action?flag=mod&aqscwyhtj.id="+row_Id+"&dt="+dt.getTime(),850,400);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_aqscwyhtj","查看安全生产委员会主要工作推进进度年度计划","${ctx}/jsp/aqscwyhtj/aqscwyhtjView.action?aqscwyhtj.id="+row_Id+"&dt="+dt.getTime(),850,400);
        	
        }
        
        function ywc(row_Id){
        	
        	var id="newWindow";
    		var text = "添加月完成情况";
    		var url = "/jsp/aqscwyhywc/aqscwyhywcList.action?aqscwyhywc.glId="+row_Id;
			window.parent.addTab(id,text,url);
        }
        
         function exportData(row_Id)
        {
     
        	document.myform.action = "${ctx}/jsp/aqscwyhtj/aqscwyhtjExport.action?aqscwyhtj.id="+row_Id;
        	document.myform.submit();
        	
        }
        
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_aqscwyhtj();
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
		                	url : "aqscwyhtjDel.action",
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
		                        	search_aqscwyhtj();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_aqscwyhtj(){
        	var queryParams = {
				 "queryYearTimeStart" :$("#queryYearTimeStart").val(),
 "queryYearTimeEnd" :$("#queryYearTimeEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'安全生产委员会主要工作推进进度年度计划列表',
				url:'aqscwyhtjQuery.action',
				queryParams:{
					 "queryYearTimeStart" :$("#queryYearTimeStart").val(),
 "queryYearTimeEnd" :$("#queryYearTimeEnd").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'yearTime',title:'年份',width:100,formatter:function(value,rec){return value.substring(0,4);}},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>&nbsp;<a class='btn_04_mini' onclick=ywc('"+rec.id+"')>月完成情况<b></b></a>&nbsp;<a class='btn_04_mini' onclick=exportData('"+rec.id+"')>导出<b></b></a>";}}
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
					
				<th width="15%">年份</th>
				<td width="35%"><input name="queryYearTimeStart" id="queryYearTimeStart" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy',maxDate:'#F{$dp.$D(\'queryYearTimeEnd\')}'})" >
					-<input name="queryYearTimeEnd" id="queryYearTimeEnd" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy',minDate:'#F{$dp.$D(\'queryYearTimeStart\')}'})" ></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_aqscwyhtj()" >查询<b></b></a>&nbsp;
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
	</form>
</body>
</html>
