<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>职业病危害建设项目预评价审核管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_occConEva","添加职业病危害建设项目预评价审核","${ctx}/jsp/occconevalue/occConEvaInitEdit.action?flag=add&dt="+dt.getTime(),900,400);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occConEva","修改职业病危害建设项目预评价审核","${ctx}/jsp/occconevalue/occConEvaInitEdit.action?flag=mod&occConEva.id="+row_Id+"&dt="+dt.getTime(),900,400);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occConEva","查看职业病危害建设项目预评价审核","${ctx}/jsp/occconevalue/occConEvaView.action?occConEva.id="+row_Id+"&dt="+dt.getTime(),900,400);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_occConEva();
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
		                	url : "occConEvaDel.action",
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
		                        	search_occConEva();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_occConEva(){
        	var queryParams = {
        	"occConEva.areaId": $("#areaId").val(),
				"occConEva.areaName": $("#areaName").val(),
"occConEva.ratingAgenciesName": $("#ratingAgenciesName").val(),
"occConEva.occupationalClassification": $("#occupationalClassification").val(),
"occConEva.companyName": $("#companyName").val(),
"occConEva.receptName": $("#receptName").val(),
"occConEva.reviewName": $("#reviewName").val(),
"occConEva.fileNo": $("#fileNo").val(),
 "queryReceptDateStart" :$("#queryReceptDateStart").val(),
 "queryReceptDateEnd" :$("#queryReceptDateEnd").val(),
 "queryReviewDateStart" :$("#queryReviewDateStart").val(),
 "queryReviewDateEnd" :$("#queryReviewDateEnd").val()
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
				title:'职业病危害建设项目预评价审核列表',
				url:'occConEvaQuery.action',
				queryParams:{
					"occConEva.areaName": $("#areaName").val(),
"occConEva.ratingAgenciesName": $("#ratingAgenciesName").val(),
"occConEva.occupationalClassification": $("#occupationalClassification").val(),
"occConEva.companyName": $("#companyName").val(),
"occConEva.receptName": $("#receptName").val(),
"occConEva.reviewName": $("#reviewName").val(),
"occConEva.fileNo": $("#fileNo").val(),
 "queryReceptDateStart" :$("#queryReceptDateStart").val(),
 "queryReceptDateEnd" :$("#queryReceptDateEnd").val(),
 "queryReviewDateStart" :$("#queryReviewDateStart").val(),
 "queryReviewDateEnd" :$("#queryReviewDateEnd").val()
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
{field:'occupationalClassification',title:'职业病危害风险分类',width:100},
{field:'fileNo',title:'档案编号',width:100},
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
				<td width="35%"><cus:SelectOneTag property="occConEva.areaId" style="width: 50%"  defaultText='请选择' codeName="企业属地" value="${occConEva.areaId}"  /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="occConEva.companyName" id="companyName" style="width: 50%"  value="${occConEva.companyName}" type="text" maxlength="127"></td>
				
			</tr>
			<tr>
				<th width="15%">职业病危害风险分类</th>
				<td width="35%"><cus:SelectOneTag property="occConEva.occupationalClassification" style="width: 50%"  defaultText='请选择' codeName="职业病危害风险分类" value="${occConEva.occupationalClassification}" /></td>
				<th width="15%">档案编号</th>
				<td width="35%"><input name="occConEva.fileNo" id="fileNo" style="width: 50%"  value="${occConEva.fileNo}" type="text"></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_occConEva()" >查询<b></b></a>&nbsp;
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
