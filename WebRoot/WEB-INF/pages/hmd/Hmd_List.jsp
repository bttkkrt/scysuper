<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全生产黑名单表管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_hmd","添加安全生产黑名单表","${ctx}/jsp/hmd/hmdInitEdit.action?flag=add&dt="+dt.getTime(),700,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_hmd","修改安全生产黑名单表","${ctx}/jsp/hmd/hmdInitEdit.action?flag=mod&hmd.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_hmd","查看安全生产黑名单表","${ctx}/jsp/hmd/hmdView.action?hmd.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_hmd();
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
		                	url : "hmdDel.action",
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
		                        	search_hmd();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_hmd(){
        	var queryParams = {
				"hmd.areaId": $("#areaId").val(),
"hmd.companyName": $("#companyName").val(),
"startProDate":$("#startProDate").val(),
"endProDate":$("#endProDate").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'安全生产黑名单表列表',
				url:'hmdQuery.action',
				queryParams:{
					"hmd.areaId": $("#areaId").val(),
"hmd.companyName": $("#companyName").val(),
"startProDate":$("#startProDate").val(),
"endProDate":$("#endProDate").val()
				},
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'startTime',title:'发生时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";}}
				        ]]
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
				<td width="35%"><cus:SelectOneTag property="hmd.areaId" style="width: 50%"  defaultText='请选择' codeName="企业属地" value="${hmd.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="hmd.companyName" id="companyName" style="width: 50%"  value="${hmd.companyName}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">发生时间</th>
				<td width="35%">
						<input name="startProDate" style="width:43%;" id="startProDate" value="<fmt:formatDate type='both' value='${startProDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endProDate\')}'})" >
						- <input name="endProDate" style="width:43%;" id="endProDate" value="<fmt:formatDate type='both' value='${endProDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startProDate\')}'})" > 
				</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_hmd()" >查询<b></b></a>&nbsp;
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
