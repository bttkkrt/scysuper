<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>危险化学品建设项目安全设施竣工验收管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_cheSafCom","添加危险化学品建设项目安全设施竣工验收","${ctx}/jsp/chesafecomplete/cheSafComInitEdit.action?flag=add&dt="+dt.getTime(),900,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheSafCom","修改危险化学品建设项目安全设施竣工验收","${ctx}/jsp/chesafecomplete/cheSafComInitEdit.action?flag=mod&cheSafCom.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheSafCom","查看危险化学品建设项目安全设施竣工验收","${ctx}/jsp/chesafecomplete/cheSafComView.action?cheSafCom.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_cheSafCom();
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
		                	url : "cheSafComDel.action",
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
		                        	search_cheSafCom();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_cheSafCom(){
        	var queryParams = {
        	"cheSafCom.areaId": $("#areaId").val(),
				"cheSafCom.areaName": $("#areaName").val(),
"cheSafCom.companyName": $("#companyName").val(),
"cheSafCom.evaluationUnit": $("#evaluationUnit").val(),
"cheSafCom.acceptanceExpert": $("#acceptanceExpert").val(),
"cheSafCom.fileNo": $("#fileNo").val(),
 "queryApprovalDateStart" :$("#queryApprovalDateStart").val(),
 "queryApprovalDateEnd" :$("#queryApprovalDateEnd").val(),
"cheSafCom.receptName": $("#receptName").val(),
"cheSafCom.reviewName": $("#reviewName").val(),
 "queryAcceptanceDateStart" :$("#queryAcceptanceDateStart").val(),
 "queryAcceptanceDateEnd" :$("#queryAcceptanceDateEnd").val()
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
				title:'危险化学品建设项目安全设施竣工验收列表',
				url:'cheSafComQuery.action',
				queryParams:{
					"cheSafCom.areaName": $("#areaName").val(),
"cheSafCom.companyName": $("#companyName").val(),
"cheSafCom.evaluationUnit": $("#evaluationUnit").val(),
"cheSafCom.acceptanceExpert": $("#acceptanceExpert").val(),
"cheSafCom.fileNo": $("#fileNo").val(),
 "queryApprovalDateStart" :$("#queryApprovalDateStart").val(),
 "queryApprovalDateEnd" :$("#queryApprovalDateEnd").val(),
"cheSafCom.receptName": $("#receptName").val(),
"cheSafCom.reviewName": $("#reviewName").val(),
 "queryAcceptanceDateStart" :$("#queryAcceptanceDateStart").val(),
 "queryAcceptanceDateEnd" :$("#queryAcceptanceDateEnd").val()
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
{field:'evaluationUnit',title:'评价单位',width:100},
{field:'approvalDate',title:'审批日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'acceptanceDate',title:'验收日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
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
				<td width="35%"><cus:SelectOneTag property="cheSafCom.areaId" style="width: 50%" defaultText='请选择' codeName="企业属地" value="${cheSafCom.areaId}"  /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="cheSafCom.companyName" style="width: 50%" id="companyName" value="${cheSafCom.companyName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">评价单位</th>
				<td width="35%"><input name="cheSafCom.evaluationUnit" style="width: 50%" id="evaluationUnit" value="${cheSafCom.evaluationUnit}" type="text" maxlength="127"></td>
				<th width="15%">审批日期</th>
				<td width="35%"><input name="queryApprovalDateStart" id="queryApprovalDateStart" value="<fmt:formatDate type='both' value='${queryApprovalDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryApprovalDateEnd\')}'})" >
					-<input name="queryApprovalDateEnd" id="queryApprovalDateEnd" value="<fmt:formatDate type='both' value='${queryApprovalDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryApprovalDateStart\')}'})" ></td>
			</tr>
			<tr>
				<th width="15%">验收日期</th>
				<td width="35%"><input name="queryAcceptanceDateStart" id="queryAcceptanceDateStart" value="<fmt:formatDate type='both' value='${queryAcceptanceDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryAcceptanceDateEnd\')}'})" >
					-<input name="queryAcceptanceDateEnd" id="queryAcceptanceDateEnd" value="<fmt:formatDate type='both' value='${queryAcceptanceDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryAcceptanceDateStart\')}'})" ></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_cheSafCom()" >查询<b></b></a>&nbsp;
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
