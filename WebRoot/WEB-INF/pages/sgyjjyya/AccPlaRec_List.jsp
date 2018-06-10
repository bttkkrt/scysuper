<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>事故应急救援预案备案管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_accPlaRec","添加事故应急救援预案备案","${ctx}/jsp/sgyjjyya/accPlaRecInitEdit.action?flag=add&dt="+dt.getTime(),700,480);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_accPlaRec","修改事故应急救援预案备案","${ctx}/jsp/sgyjjyya/accPlaRecInitEdit.action?flag=mod&accPlaRec.id="+row_Id+"&dt="+dt.getTime(),700,480);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_accPlaRec","查看事故应急救援预案备案","${ctx}/jsp/sgyjjyya/accPlaRecView.action?accPlaRec.id="+row_Id+"&dt="+dt.getTime(),700,480);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_accPlaRec();
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
		                	url : "accPlaRecDel.action",
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
		                        	search_accPlaRec();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_accPlaRec(){
        	var queryParams = {
				"accPlaRec.areaId": $("#areaId").val(),
"accPlaRec.companyName": $("#companyName").val(),
"accPlaRec.recordNum": $("#recordNum").val(),
"accPlaRec.recordAgency": $("#recordAgency").val(),
 "queryRecordDateStart" :$("#queryRecordDateStart").val(),
 "queryRecordDateEnd" :$("#queryRecordDateEnd").val()
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
				title:'事故应急救援预案备案列表',
				url:'accPlaRecQuery.action',
				queryParams:{
					"accPlaRec.areaId": $("#areaId").val(),
"accPlaRec.companyName": $("#companyName").val(),
"accPlaRec.recordNum": $("#recordNum").val(),
"accPlaRec.recordAgency": $("#recordAgency").val(),
 "queryRecordDateStart" :$("#queryRecordDateStart").val(),
 "queryRecordDateEnd" :$("#queryRecordDateEnd").val()
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
{field:'recordAgency',title:'备案机构',width:100},
{field:'recordDate',title:'备案日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
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
					<td width="35%"><cus:SelectOneTag style="width: 50%"  property="accPlaRec.areaId" defaultText='请选择' codeName="企业属地" value="${accPlaRec.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="accPlaRec.companyName" style="width: 50%" id="companyName" value="${accPlaRec.companyName}" type="text" maxlength="127"></td>
				</tr>
			<tr>
				<th width="15%">备案编号</th>
				<td width="35%"><input name="accPlaRec.recordNum" style="width: 50%" id="recordNum" value="${accPlaRec.recordNum}" type="text" maxlength="127"></td>
				<th width="15%">备案机构</th>
				<td width="35%"><input name="accPlaRec.recordAgency" style="width: 50%" id="recordAgency" value="${accPlaRec.recordAgency}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">备案日期</th>
				<td width="35%"><input name="queryRecordDateStart" id="queryRecordDateStart" value="<fmt:formatDate type='both' value='${queryRecordDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryRecordDateEnd\')}'})" >
					-<input name="queryRecordDateEnd" id="queryRecordDateEnd" value="<fmt:formatDate type='both' value='${queryRecordDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryRecordDateStart\')}'})" ></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_accPlaRec()" >查询<b></b></a>&nbsp;
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
