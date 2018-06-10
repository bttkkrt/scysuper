<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>视频录像管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_splx","添加视频录像","${ctx}/jsp/splx/splxInitEdit.action?flag=add&dt="+dt.getTime(),700,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_splx","修改视频录像","${ctx}/jsp/splx/splxInitEdit.action?flag=mod&splx.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_splx","查看视频录像","${ctx}/jsp/splx/splxView.action?splx.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        
        function vedio(row_Id){
        	window.open("${ctx}/jsp/splx/splxVedio.action?splx.id="+row_Id);
        	
        }
        
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_splx();
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
		                	url : "splxDel.action",
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
		                        	search_splx();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_splx(){
        	var queryParams = {
				"splx.fileName": $("#fileName").val(),
 "queryBeginTimeStart" :$("#queryBeginTimeStart").val(),
 "queryBeginTimeEnd" :$("#queryBeginTimeEnd").val(),
 "queryEndTimeStart" :$("#queryEndTimeStart").val(),
 "queryEndTimeEnd" :$("#queryEndTimeEnd").val(),
 "splx.guid": "${guid}"
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'视频录像列表',
				url:'splxQuery.action',
				queryParams:{
					"splx.fileName": $("#fileName").val(),
 "queryBeginTimeStart" :$("#queryBeginTimeStart").val(),
 "queryBeginTimeEnd" :$("#queryBeginTimeEnd").val(),
 "queryEndTimeStart" :$("#queryEndTimeStart").val(),
 "queryEndTimeEnd" :$("#queryEndTimeEnd").val(),
 "splx.guid": "${guid}"
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'fileName',title:'文件名称',width:100},
{field:'beginTime',title:'开始时间',width:100},
{field:'endTime',title:'结束时间',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=vedio('"+rec.id+"') >播放<b></b></a>";}}
				        ]],
				toolbar:[]
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
					
				<th width="15%">文件名称</th>
				<td width="35%"><input name="splx.fileName" id="fileName" value="${splx.fileName}" type="text"></td>
				<th width="15%">开始时间</th>
				<td width="35%"><input name="queryBeginTimeStart" id="queryBeginTimeStart" value="<fmt:formatDate type='both' value='${queryBeginTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryBeginTimeEnd\')}'})" >
					-<input name="queryBeginTimeEnd" id="queryBeginTimeEnd" value="<fmt:formatDate type='both' value='${queryBeginTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryBeginTimeStart\')}'})" ></td>
			</tr>
			<tr>
				<th width="15%">结束时间</th>
				<td width="35%"><input name="queryEndTimeStart" id="queryEndTimeStart" value="<fmt:formatDate type='both' value='${queryEndTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryEndTimeEnd\')}'})" >
					-<input name="queryEndTimeEnd" id="queryEndTimeEnd" value="<fmt:formatDate type='both' value='${queryEndTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryEndTimeStart\')}'})" ></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_splx()" >查询<b></b></a>&nbsp;
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
