<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>建设项目安全设施设计审查管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_proSafDes","添加建设项目安全设施设计审查","${ctx}/jsp/prosafedesign/proSafDesInitEdit.action?flag=add&dt="+dt.getTime(),900,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_proSafDes","修改建设项目安全设施设计审查","${ctx}/jsp/prosafedesign/proSafDesInitEdit.action?flag=mod&proSafDes.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_proSafDes","查看建设项目安全设施设计审查","${ctx}/jsp/prosafedesign/proSafDesView.action?proSafDes.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_proSafDes();
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
		                	url : "proSafDesDel.action",
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
		                        	search_proSafDes();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_proSafDes(){
        	var queryParams = {
        	"proSafDes.areaId": $("#areaId").val(),
				"proSafDes.areaName": $("#areaName").val(),
 "queryRecordNumStart" :$("#queryRecordNumStart").val(),
 "queryRecordNumEnd" :$("#queryRecordNumEnd").val(),
"proSafDes.projectType": $("#projectType").val(),
"proSafDes.companyName": $("#companyName").val(),
"proSafDes.recordDate": $("#recordDate").val(),
"proSafDes.projectNature": $("#projectNature").val()
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
				title:'建设项目安全设施设计审查列表',
				url:'proSafDesQuery.action',
				queryParams:{
					"proSafDes.areaName": $("#areaName").val(),
 "queryRecordNumStart" :$("#queryRecordNumStart").val(),
 "queryRecordNumEnd" :$("#queryRecordNumEnd").val(),
"proSafDes.projectType": $("#projectType").val(),
"proSafDes.companyName": $("#companyName").val(),
"proSafDes.projectNature": $("#projectNature").val()
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
{field:'recordDate',title:'备案编号',width:100},
{field:'projectType',title:'项目类型',width:100},
{field:'recordNum',title:'备案日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'projectNature',title:'项目性质',width:100},
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
				<td width="35%"><cus:SelectOneTag property="proSafDes.areaId" style="width: 50%" defaultText='请选择' codeName="企业属地" value="${proSafDes.areaId}"  /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="proSafDes.companyName" style="width: 50%" id="companyName" value="${proSafDes.companyName}" type="text" maxlength="127"></td>		
			</tr>
			<tr>
				<th width="15%">项目类型</th>
				<td width="35%"><cus:SelectOneTag property="proSafDes.projectType" style="width: 50%" defaultText='请选择' codeName="项目类型" value="${proSafDes.projectType}" /></td>
				<th width="15%">项目性质</th>
				<td width="35%"><cus:SelectOneTag property="proSafDes.projectNature" style="width: 50%" defaultText='请选择' codeName="项目性质" value="${proSafDes.projectNature}" /></td>
				</tr>
			<tr>
			<th width="15%">备案编号</th>
				<td width="35%"><input name="proSafDes.recordDate" id="recordDate" style="width: 50%" value="${proSafDes.recordDate}" type="text" maxlength="127"></td>		
				 <th width="15%">备案日期</th>
				<td width="35%"><input name="queryRecordNumStart" id="queryRecordNumStart" value="<fmt:formatDate type='both' value='${queryRecordNumStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryRecordNumEnd\')}'})" >
					-<input name="queryRecordNumEnd" id="queryRecordNumEnd" value="<fmt:formatDate type='both' value='${queryRecordNumEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryRecordNumStart\')}'})" ></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_proSafDes()" >查询<b></b></a>&nbsp;
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
