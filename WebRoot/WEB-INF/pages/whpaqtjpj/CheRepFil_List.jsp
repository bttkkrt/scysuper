<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>危化品安全条件评价报告备案管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_cheRepFil","添加危化品安全条件评价报告备案","${ctx}/jsp/whpaqtjpj/cheRepFilInitEdit.action?flag=add&dt="+dt.getTime(),700,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheRepFil","修改危化品安全条件评价报告备案","${ctx}/jsp/whpaqtjpj/cheRepFilInitEdit.action?flag=mod&cheRepFil.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheRepFil","查看危化品安全条件评价报告备案","${ctx}/jsp/whpaqtjpj/cheRepFilView.action?cheRepFil.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_cheRepFil();
        }
        function del(){
        	var rows = document.getElementsByName('delBox');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
				if(rows[i].checked){
					ids += rows[i].value+"|";
				}
			}
			
			if(ids == ""){
			    $.messager.alert('提示','至少选择一项删除！');
			}else{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "cheRepFilDel.action",
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
		                        	search_cheRepFil();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_cheRepFil(){
        	var queryParams = {
				"cheRepFil.areaId": $("#areaId").val(),
"cheRepFil.companyName": $("#companyName").val(),
"cheRepFil.recordNum" :$("#recordNum").val(),
 "queryRecordDateStart" :$("#queryRecordDateStart").val(),
 "queryRecordDateEnd" :$("#queryRecordDateEnd").val(),
"cheRepFil.ratingAgenciesName": $("#ratingAgenciesName").val(),
 "queryCompleteDateStart" :$("#queryCompleteDateStart").val(),
 "queryCompleteDateEnd" :$("#queryCompleteDateEnd").val(),
 "querySubmitDateStart" :$("#querySubmitDateStart").val(),
 "querySubmitDateEnd" :$("#querySubmitDateEnd").val(),
 "queryNextRecordDateStart" :$("#queryNextRecordDateStart").val(),
 "queryNextRecordDateEnd" :$("#queryNextRecordDateEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        var roleName='';
	        var toolbar = [];
	        var frozen=[];
        	if("${operateRight}"=="add"){
		    	roleName='0';
		        toolbar = [{
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
				}];
				frozen=[[
					{field:'id',checkbox:true}
				]];	
			}else if("${operateRight}"=="qy"){
				document.getElementById('szqy').style.display='none';			                        	
			}else{
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'危化品安全条件评价报告备案列表',
				url:'cheRepFilQuery.action',
				queryParams:{
					"cheRepFil.areaId": $("#areaId").val(),
"cheRepFil.companyName": $("#companyName").val(),
 "cheRepFil.recordNum" :$("#recordNum").val(),
 "queryRecordDateStart" :$("#queryRecordDateStart").val(),
 "queryRecordDateEnd" :$("#queryRecordDateEnd").val(),
"cheRepFil.ratingAgenciesName": $("#ratingAgenciesName").val(),
 "queryCompleteDateStart" :$("#queryCompleteDateStart").val(),
 "queryCompleteDateEnd" :$("#queryCompleteDateEnd").val(),
 "querySubmitDateStart" :$("#querySubmitDateStart").val(),
 "querySubmitDateEnd" :$("#querySubmitDateEnd").val(),
 "queryNextRecordDateStart" :$("#queryNextRecordDateStart").val(),
 "queryNextRecordDateEnd" :$("#queryNextRecordDateEnd").val()
				},
				//frozenColumns:frozen,
				columns:[[
{field:'id',width:25,formatter:function(value,rec){
	var box="<input type='checkbox' name='delBox' value='"+value+"'>";
	if(rec.createUserID=="${sessionScope['LOGIN_USER_ID']}"){
		return box;
	}else{
		return "";//return "<input type='checkbox' name='delBox' disabled value='"+value+"'>";
	}
	
}},
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'recordNum',title:'备案编号',width:100},
{field:'recordDate',title:'备案日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'ratingAgenciesName',title:'评价机构名称',width:100},
//{field:'completeDate',title:'报告完成日期',width:100},
//{field:'submitDate',title:'报告上传日期',width:100},
//{field:'nextRecordDate',title:'下次备案日期',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
if("${sessionScope['LOGIN_USER_ID']}"==rec.createUserID){
	return  button1;
}else{
	return button2;
}
}}				        ]],
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
			<table width="100%">
				<tr id="szqy" style="display:">
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag style="width: 50%"  property="cheRepFil.areaId" defaultText='请选择' codeName="企业属地" value="${cheRepFil.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="cheRepFil.companyName" style="width: 50%" id="companyName" value="${cheRepFil.companyName}" type="text" maxlength="127"></td>
				</tr>
			<tr>
				<th width="15%">备案编号</th>
				<td width="35%"><input name="cheRepFil.recordNum" style="width: 50%" id="recordNum" value="${cheRepFil.recordNum}" type="text" maxlength="127"></td>
				<th width="15%">评价机构名称</th>
				<td width="35%"><input name="cheRepFil.ratingAgenciesName" style="width: 50%" id="ratingAgenciesName" value="${cheRepFil.ratingAgenciesName}" type="text" maxlength="127"></td>
			</tr>
			<!-- <tr>
				<th width="15%">备案日期</th>
				<td width="35%"><input name="queryRecordDateStart" id="queryRecordDateStart" value="<fmt:formatDate type='both' value='${queryRecordDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryRecordDateEnd\')}'})" >
					-<input name="queryRecordDateEnd" id="queryRecordDateEnd" value="<fmt:formatDate type='both' value='${queryRecordDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryRecordDateStart\')}'})" ></td>
				<th width="15%">报告完成日期</th>
				<td width="35%"><input name="queryCompleteDateStart" id="queryCompleteDateStart" value="<fmt:formatDate type='both' value='${queryCompleteDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryCompleteDateEnd\')}'})" >
					-<input name="queryCompleteDateEnd" id="queryCompleteDateEnd" value="<fmt:formatDate type='both' value='${queryCompleteDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryCompleteDateStart\')}'})" ></td>
			</tr>
			<tr>
				<th width="15%">报告上传日期</th>
				<td width="35%"><input name="querySubmitDateStart" id="querySubmitDateStart" value="<fmt:formatDate type='both' value='${querySubmitDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'querySubmitDateEnd\')}'})" >
					-<input name="querySubmitDateEnd" id="querySubmitDateEnd" value="<fmt:formatDate type='both' value='${querySubmitDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'querySubmitDateStart\')}'})" ></td>
				<th width="15%">下次备案日期</th>
				<td width="35%"><input name="queryNextRecordDateStart" id="queryNextRecordDateStart" value="<fmt:formatDate type='both' value='${queryNextRecordDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryNextRecordDateEnd\')}'})" >
					-<input name="queryNextRecordDateEnd" id="queryNextRecordDateEnd" value="<fmt:formatDate type='both' value='${queryNextRecordDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryNextRecordDateStart\')}'})" ></td>
			</tr> -->
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_cheRepFil()" >查询<b></b></a>&nbsp;
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
