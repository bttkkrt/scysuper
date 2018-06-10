<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>应急设施装备物资管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        var type="${type}";
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_emeFac","添加应急设施装备物资","${ctx}/jsp/yjsszbwz/emeFacInitEdit.action?flag=add&dt="+dt.getTime()+"&type="+type,900,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_emeFac","修改应急设施装备物资","${ctx}/jsp/yjsszbwz/emeFacInitEdit.action?flag=mod&emeFac.id="+row_Id+"&dt="+dt.getTime()+"&type="+type,900,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_emeFac","查看应急设施装备物资","${ctx}/jsp/yjsszbwz/emeFacView.action?emeFac.id="+row_Id+"&dt="+dt.getTime()+"&type="+type,900,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_emeFac();
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
		                	url : "emeFacDel.action",
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
		                        	search_emeFac();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_emeFac(){
        	var queryParams = {
        	"emeFac.type": $("#type").val(),
				"emeFac.areaId": $("#areaId").val(),
"emeFac.companyName": $("#companyName").val(),
"emeFac.facilityName": $("#facilityName").val(),
"emeFac.type": "1",
"emeFac.facilityLevel": $("#facilityLevel").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
       $(function(){
       var toolbar = [];
        	if('${roleName}'!='0'){//判断登录角色
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
				title:'应急设施装备物资列表',
				url:'emeFacQuery.action',
				queryParams:{
					"emeFac.areaId": $("#areaId").val(),
"emeFac.companyName": $("#companyName").val(),
"emeFac.type": "1",
"emeFac.facilityLevel": $("#facilityLevel").val()
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

{field:'facilityName',title:'物资名称',width:100},
{field:'facilityLevel',title:'物资级别',width:100},
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
			    <th width="15%">物资名称</th>
				<td width="35%"><input name="emeFac.facilityName" style="width:50%" id="facilityName" value="${emeFac.facilityName}" type="text" maxlength="127"></td>
				<th width="15%">物资级别</th>
				<td width="35%"><cus:SelectOneTag style="width:50%" property="emeFac.facilityLevel" defaultText='请选择' codeName="应急物资级别" value="${emeFac.facilityLevel}" /></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_emeFac()" >查询<b></b></a>&nbsp;
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
