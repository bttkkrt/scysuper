<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>职业病危害建设项目防护设施竣工审查管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_occConCom","添加职业病危害建设项目防护设施竣工审查","${ctx}/jsp/occconcomplete/occConComInitEdit.action?flag=add&dt="+dt.getTime(),900,400);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occConCom","修改职业病危害建设项目防护设施竣工审查","${ctx}/jsp/occconcomplete/occConComInitEdit.action?flag=mod&occConCom.id="+row_Id+"&dt="+dt.getTime(),900,400);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occConCom","查看职业病危害建设项目防护设施竣工审查","${ctx}/jsp/occconcomplete/occConComView.action?occConCom.id="+row_Id+"&dt="+dt.getTime(),900,400);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_occConCom();
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
		                	url : "occConComDel.action",
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
		                        	search_occConCom();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_occConCom(){
        	var queryParams = {
        	"occConCom.areaId": $("#areaId").val(),
				"occConCom.areaName": $("#areaName").val(),
"occConCom.companyName": $("#companyName").val(),
"occConCom.occupationalClassification": $("#occupationalClassification").val(),
"occConCom.acceptanceExpert": $("#acceptanceExpert").val(),
"occConCom.evaluationUnit": $("#evaluationUnit").val(),
"occConCom.receptName": $("#receptName").val(),
"occConCom.reviewName": $("#reviewName").val(),
"occConCom.industryCategory": $("#industryCategory").val()
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
				title:'职业病危害建设项目防护设施竣工审查列表',
				url:'occConComQuery.action',
				queryParams:{
					"occConCom.areaName": $("#areaName").val(),
"occConCom.companyName": $("#companyName").val(),
"occConCom.occupationalClassification": $("#occupationalClassification").val(),
"occConCom.acceptanceExpert": $("#acceptanceExpert").val(),
"occConCom.evaluationUnit": $("#evaluationUnit").val(),
"occConCom.receptName": $("#receptName").val(),
"occConCom.reviewName": $("#reviewName").val(),
"occConCom.industryCategory": $("#industryCategory").val()
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
{field:'acceptanceExpert',title:'验收专家',width:100},
{field:'industryCategory',title:'行业类别',width:100},
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
				<td width="35%"><cus:SelectOneTag property="occConCom.areaId" style="width: 50%"  defaultText='请选择' codeName="企业属地" value="${occConCom.areaId}"  /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="occConCom.companyName" style="width: 50%"  id="companyName" value="${occConCom.companyName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">职业病危害风险分类</th>
				<td width="35%"><cus:SelectOneTag property="occConCom.occupationalClassification" style="width: 50%"  defaultText='请选择' codeName="职业病危害风险分类" value="${occConCom.occupationalClassification}" /></td>
				<th width="15%">验收专家</th>
				<td width="35%"><input name="occConCom.acceptanceExpert" style="width: 50%"  id="acceptanceExpert" value="${occConCom.acceptanceExpert}" type="text" maxlength="127"></td>
			</tr>

			<tr>
  				<th width="15%">行业类别</th>
				<td width="35%"><cus:SelectOneTag property="occConCom.industryCategory" style="width: 50%"  defaultText='请选择' codeName="行业类别" value="${occConCom.industryCategory}" /></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_occConCom()" >查询<b></b></a>&nbsp;
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
