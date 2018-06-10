<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>隐患表管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_troMan","添加隐患","${ctx}/jsp/yhb/troManInitEdit.action?flag=add&dt="+dt.getTime()+"&troMan.taskId=${troMan.taskId}",900,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","修改隐患","${ctx}/jsp/yhb/troManInitEdit.action?flag=mod&troMan.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","查看隐患","${ctx}/jsp/yhb/troManView.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        //上传整改信息
       function uploadRect(row_Id){
       		var dt=new Date();
            createSimpleWindow("win_troMan","上传整改信息","${ctx}/jsp/yhb/troManUploadRect.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,500);
       }
       //审核信息
       function audit(row_Id){
       		var dt=new Date();
            createSimpleWindow("win_troMan","审核信息","${ctx}/jsp/yhb/troManAudit.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,500);
       }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_troMan();
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
		                	url : "troManDel.action",
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
		                        	search_troMan();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_troMan(){
        	var queryParams = {
				"troMan.troubleName": $("#troubleName").val(),
"troMan.troubleSource": $("#troubleSource").val(),
"troMan.areaId": $("#areaId").val(),
"troMan.companyName": $("#companyName").val(),
"troMan.userName": $("#userName").val(),
"troMan.troubleLevel": $("#troubleLevel").val(),
"troMan.troubleSort": $("#troubleSort").val(),
"troMan.taskId": '${troMan.taskId}',
"troMan.rectificationState": $("#rectificationState").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	var toolbar = [];
        	var frozen=[];
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'企业隐患',
				url:'companyTroManTongji.action',
				queryParams:{
					"troMan.companyName": $("#companyName").val(),
					"starttime":$("#starttime").val(),
			        "endtime":$("#endtime").val()
				},
				frozenColumns:frozen,
				columns:[[
				{field:'dwdz',title:'企业名称',width:100},
{field:'yhTotal',title:'隐患总数',width:100},
{field:'zgwc',title:'整改完成数',width:100},
{field:'zgwwc',title:'未整改数',width:100},
{field:'zgl',title:'整改率',width:100}
				        ]],
				toolbar:toolbar 
			}));
		});
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<form name="myform1" method="post" enctype="multipart/form-data" action="troManExportXls.action">
			<input type="hidden" name="flag" value="qy"/>
			<input type="hidden" name="ids" id="ids" value=""/>
			<table width="100%">
			<tr style="display:none">
				<th width="15%">上报时间</th>
				<td width="35%"><input name="starttime" id="starttime" value="${starttime}" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')}'})" >
					-<input name="endtime" id="endtime" value="${endtime}" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'starttime\')}'})" ></td>
				
			</tr>
			<tr>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="troMan.companyName" id="companyName" value="${troMan.companyName}" type="text" maxlength="127"></td>
			</tr
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_troMan()" >查询<b></b></a>&nbsp;
						<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;				
					</td>
				</tr>
			</table>
			</form>
		</div>
		
		<div id="pagination" >
		</div>
		</div>
		</div>
	</div>
</body>
</html>
