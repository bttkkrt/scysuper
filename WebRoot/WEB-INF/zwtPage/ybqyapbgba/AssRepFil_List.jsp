<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>一般企业安评报告备案管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_assRepFil","添加一般企业安评报告备案","${ctx}/jsp/ybqyapbgba/assRepFilInitEdit.action?flag=add&dt="+dt.getTime(),800,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_assRepFil","修改一般企业安评报告备案","${ctx}/jsp/ybqyapbgba/assRepFilInitEdit.action?flag=mod&assRepFil.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_assRepFil","查看一般企业安评报告备案","${ctx}/jsp/ybqyapbgba/assRepFilView.action?assRepFil.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_assRepFil();
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
		                	url : "assRepFilDel.action",
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
		                        	search_assRepFil();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_assRepFil(){
        	var queryParams = {
				"assRepFil.areaId": $("#areaId").val(),
"assRepFil.companyName": $("#companyName").val(),
"assRepFil.recordNum": $("#recordNum").val(),
 "queryRecordDateStart" :$("#queryRecordDateStart").val(),
 "queryRecordDateEnd" :$("#queryRecordDateEnd").val(),
"assRepFil.ratingAgenciesName": $("#ratingAgenciesName").val(),
 "querySubmitDateStart" :$("#querySubmitDateStart").val(),
 "querySubmitDateEnd" :$("#querySubmitDateEnd").val(),
 "queryCompleteDateStart" :$("#queryCompleteDateStart").val(),
 "queryCompleteDateEnd" :$("#queryCompleteDateEnd").val(),
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
				title:'一般企业安评报告备案列表',
				url:'assRepFilQuery.action',
				queryParams:{
					"assRepFil.areaId": $("#areaId").val(),
"assRepFil.companyName": $("#companyName").val(),
"assRepFil.recordNum": $("#recordNum").val(),
 "queryRecordDateStart" :$("#queryRecordDateStart").val(),
 "queryRecordDateEnd" :$("#queryRecordDateEnd").val(),
"assRepFil.ratingAgenciesName": $("#ratingAgenciesName").val(),
 "querySubmitDateStart" :$("#querySubmitDateStart").val(),
 "querySubmitDateEnd" :$("#querySubmitDateEnd").val(),
 "queryCompleteDateStart" :$("#queryCompleteDateStart").val(),
 "queryCompleteDateEnd" :$("#queryCompleteDateEnd").val(),
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
{field:'ratingAgenciesName',title:'评价机构名称',width:100},
{field:'recordDate',title:'备案日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
//{field:'submitDate',title:'报告上传日期',width:100},
//{field:'completeDate',title:'报告完成日期',width:100},
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
				<tr id="szqy" style="display:none">
					
				<th width="15%">所在区域</th>
				<td width="35%">yle<cus:SelectOneTag style="width: 50%" property="assRepFil.areaId" defaultText='请选择' codeName="企业属地" value="${assRepFil.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="assRepFil.companyName" style="width: 50%" id="companyName" value="${assRepFil.companyName}" type="text" maxlength="127"></td>
			</tr>
			 <tr>
				<th width="15%">备案编号</th>
				<td width="35%"><input name="assRepFil.recordNum" style="width: 50%" id="recordNum" value="${assRepFil.recordNum}" type="text" maxlength="127"></td>
				<th width="15%">评价机构名称</th>
				<td width="35%"><input name="assRepFil.ratingAgenciesName" style="width: 50%" id="ratingAgenciesName" value="${assRepFil.ratingAgenciesName}" type="text" maxlength="127"></td>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_assRepFil()" >查询<b></b></a>&nbsp;
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
