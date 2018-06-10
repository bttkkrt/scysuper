<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>危险化学品建设项目安全设施试生产备案管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_cheSafPro","添加危险化学品建设项目安全设施试生产备案","${ctx}/jsp/chesafepro/cheSafProInitEdit.action?flag=add&dt="+dt.getTime(),900,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheSafPro","修改危险化学品建设项目安全设施试生产备案","${ctx}/jsp/chesafepro/cheSafProInitEdit.action?flag=mod&cheSafPro.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheSafPro","查看危险化学品建设项目安全设施试生产备案","${ctx}/jsp/chesafepro/cheSafProView.action?cheSafPro.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_cheSafPro();
        }
        function del(){
        	var rows = document.getElementsByName("xxx");
        	var ids = "";
        	var len = rows.length;
			for(var i=0;i<len;i++){
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
		                	url : "cheSafProDel.action",
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
		                        	search_cheSafPro();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_cheSafPro(){
        	var queryParams = {
        	"cheSafPro.areaId": $("#areaId").val(),
				"cheSafPro.areaName": $("#areaName").val(),
"cheSafPro.companyName": $("#companyName").val(),
"cheSafPro.projectType": $("#projectType").val(),
"cheSafPro.fileNo": $("#fileNo").val(),
"cheSafPro.receptName": $("#receptName").val(),
"cheSafPro.reviewName": $("#reviewName").val(),
"cheSafPro.recordNum": $("#recordNum").val(),
 "queryRecordDateStart" :$("#queryRecordDateStart").val(),
 "queryRecordDateEnd" :$("#queryRecordDateEnd").val(),
"cheSafPro.projectNature": $("#projectNature").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        var toolbar = [];
        	if('${roleName}'=='1'){//判断登录角色
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
				
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'危险化学品建设项目安全设施试生产备案列表',
				url:'cheSafProQuery.action',
				queryParams:{
					"cheSafPro.areaName": $("#areaName").val(),
"cheSafPro.companyName": $("#companyName").val(),
"cheSafPro.projectType": $("#projectType").val(),
"cheSafPro.fileNo": $("#fileNo").val(),
"cheSafPro.receptName": $("#receptName").val(),
"cheSafPro.reviewName": $("#reviewName").val(),
"cheSafPro.projectNature": $("#projectNature").val()
				},
				frozenColumns:[[
				     {field:'id',width:20,formatter:function(value,rec){
				    		var opt = '<input type="checkbox" name="xxx" value='+rec.id+'>'; 
						    if(rec.createUserID != "${sessionScope['LOGIN_USER_ID']}"){
								opt = ''; 
						    } 
						    return opt ; 

				    }}
				]],
				columns:[[
				
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'projectType',title:'项目类型',width:100},
{field:'projectNature',title:'项目性质',width:100},
{field:'recordNum',title:'备案编号',width:100},
{field:'recordDate',title:'备案日期',width:100,width:100,formatter:function(value,rec){return value.substring(0,10);}},
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
				<tr>
					
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag property="cheSafPro.areaId" style="width: 50%" defaultText='请选择' codeName="企业属地" value="${cheSafPro.areaId}"  /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="cheSafPro.companyName" style="width: 50%" id="companyName" value="${cheSafPro.companyName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">项目类型</th>
				<td width="35%"><cus:SelectOneTag property="cheSafPro.projectType" style="width: 50%" defaultText='请选择' codeName="项目类型" value="${cheSafPro.projectType}" /></td>
				<th width="15%">项目性质</th>
				<td width="35%"><cus:SelectOneTag property="cheSafPro.projectNature" style="width: 50%" defaultText='请选择' codeName="项目性质" value="${cheSafPro.projectNature}" /></td>
				</tr>
			<tr>
					
				<th width="15%">备案编号</th>
				<td width="35%"><input name="cheSafPro.recordNum" id="recordNum" style="width: 50%" value="${cheSafPro.recordNum}" type="text" maxlength="127"></td>
				<th width="15%">备案日期</th>
				<td width="35%"><input name="queryRecordDateStart" id="queryRecordDateStart" value="<fmt:formatDate type='both' value='${queryRecordDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryRecordDateEnd\')}'})" >
					-<input name="queryRecordDateEnd" id="queryRecordDateEnd" value="<fmt:formatDate type='both' value='${queryRecordDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',minDate:'#F{$dp.$D(\'queryRecordDateEnd\')}'})" ></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_cheSafPro()" >查询<b></b></a>&nbsp;
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
