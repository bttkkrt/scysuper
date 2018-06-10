<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>危险化学品建设项目安全设施设计审查管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_cheSafDes","添加危险化学品建设项目安全设施设计审查","${ctx}/jsp/chesafedesign/cheSafDesInitEdit.action?flag=add&dt="+dt.getTime(),900,450);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheSafDes","修改危险化学品建设项目安全设施设计审查","${ctx}/jsp/chesafedesign/cheSafDesInitEdit.action?flag=mod&cheSafDes.id="+row_Id+"&dt="+dt.getTime(),900,450);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheSafDes","查看危险化学品建设项目安全设施设计审查","${ctx}/jsp/chesafedesign/cheSafDesView.action?cheSafDes.id="+row_Id+"&dt="+dt.getTime(),900,450);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_cheSafDes();
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
		                	url : "cheSafDesDel.action",
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
		                        	search_cheSafDes();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_cheSafDes(){
        	var queryParams = {
        	"cheSafDes.areaId": $("#areaId").val(),
				"cheSafDes.areaName": $("#areaName").val(),
"cheSafDes.companyName": $("#companyName").val(),
"cheSafDes.designUnit": $("#designUnit").val(),
"cheSafDes.projectNature": $("#projectNature").val(),
"cheSafDes.isComplete": $("#isComplete").val(),
 "queryReviewDateStart" :$("#queryReviewDateStart").val(),
 "queryReviewDateEnd" :$("#queryReviewDateEnd").val(),
"cheSafDes.reviewName": $("#reviewName").val(),
"cheSafDes.receptName": $("#receptName").val(),
"cheSafDes.expertReview": $("#expertReview").val()
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
				title:'危险化学品建设项目安全设施设计审查列表',
				url:'cheSafDesQuery.action',
				queryParams:{
					"cheSafDes.areaName": $("#areaName").val(),
"cheSafDes.companyName": $("#companyName").val(),
"cheSafDes.designUnit": $("#designUnit").val(),
"cheSafDes.projectNature": $("#projectNature").val(),
"cheSafDes.isComplete": $("#isComplete").val(),
 "queryReviewDateStart" :$("#queryReviewDateStart").val(),
 "queryReviewDateEnd" :$("#queryReviewDateEnd").val(),
"cheSafDes.reviewName": $("#reviewName").val(),
"cheSafDes.receptName": $("#receptName").val(),
"cheSafDes.expertReview": $("#expertReview").val()
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
{field:'designUnit',title:'设计单位',width:100},
{field:'projectNature',title:'项目性质',width:100},
{field:'reviewDate',title:'审查日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
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
				<td width="35%"><cus:SelectOneTag property="cheSafDes.areaId" style="width: 50%" defaultText='请选择' codeName="企业属地" value="${cheSafDes.areaId}"   maxlength="127"/></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="cheSafDes.companyName" id="companyName" style="width: 50%" value="${cheSafDes.companyName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">设计单位</th>
				<td width="35%"><input name="cheSafDes.designUnit" id="designUnit" style="width: 50%" value="${cheSafDes.designUnit}" type="text" maxlength="127"></td>
				<th width="15%">项目性质</th>
				<td width="35%"><cus:SelectOneTag property="cheSafDes.projectNature" style="width: 50%" defaultText='请选择' codeName="项目性质" value="${cheSafDes.projectNature}"  maxlength="127"/></td>
			</tr>
			<tr>
		
				<th width="15%">审查日期</th>
				<td width="35%"><input name="queryReviewDateStart" id="queryReviewDateStart" value="<fmt:formatDate type='both' value='${queryReviewDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryReviewDateEnd\')}'})" >
					-<input name="queryReviewDateEnd" id="queryReviewDateEnd" value="<fmt:formatDate type='both' value='${queryReviewDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',minDate:'#F{$dp.$D(\'queryReviewDateStart\')}'})" ></td>
			</tr>
			
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_cheSafDes()" >查询<b></b></a>&nbsp;
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
