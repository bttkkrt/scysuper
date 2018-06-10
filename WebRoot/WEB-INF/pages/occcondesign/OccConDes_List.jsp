<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>职业病危害建设项目防护设计审查管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_occConDes","添加职业病危害建设项目防护设计审查","${ctx}/jsp/occcondesign/occConDesInitEdit.action?flag=add&dt="+dt.getTime(),900,400);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occConDes","修改职业病危害建设项目防护设计审查","${ctx}/jsp/occcondesign/occConDesInitEdit.action?flag=mod&occConDes.id="+row_Id+"&dt="+dt.getTime(),900,400);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occConDes","查看职业病危害建设项目防护设计审查","${ctx}/jsp/occcondesign/occConDesView.action?occConDes.id="+row_Id+"&dt="+dt.getTime(),900,400);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_occConDes();
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
		                	url : "occConDesDel.action",
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
		                        	search_occConDes();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_occConDes(){
        	var queryParams = {
        	"occConDes.areaId": $("#areaId").val(),
				"occConDes.areaName": $("#areaName").val(),
"occConDes.companyName": $("#companyName").val(),
"occConDes.industryCategory": $("#industryCategory").val(),
"occConDes.designUnit": $("#designUnit").val(),
 "queryReviewDateStart" :$("#queryReviewDateStart").val(),
 "queryReviewDateEnd" :$("#queryReviewDateEnd").val(),
"occConDes.reviewName": $("#reviewName").val(),
"occConDes.expertReview": $("#expertReview").val(),
"occConDes.review": $("#review").val(),
"occConDes.receptName": $("#receptName").val()
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
				title:'职业病危害建设项目防护设计审查列表',
				url:'occConDesQuery.action',
				queryParams:{
					"occConDes.areaName": $("#areaName").val(),
"occConDes.companyName": $("#companyName").val(),
"occConDes.industryCategory": $("#industryCategory").val(),
"occConDes.designUnit": $("#designUnit").val(),
 "queryReviewDateStart" :$("#queryReviewDateStart").val(),
 "queryReviewDateEnd" :$("#queryReviewDateEnd").val(),
"occConDes.reviewName": $("#reviewName").val(),
"occConDes.expertReview": $("#expertReview").val(),
"occConDes.review": $("#review").val(),
"occConDes.receptName": $("#receptName").val()
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
{field:'industryCategory',title:'行业类别',width:100},
{field:'reviewDate',title:'审查日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'review',title:'审查结果',width:100},
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
				<td width="35%"><cus:SelectOneTag property="occConDes.areaId" style="width: 50%"  defaultText='请选择' codeName="企业属地" value="${occConDes.areaId}"  /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="occConDes.companyName" id="companyName" style="width: 50%"  value="${occConDes.companyName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">行业类别</th>
				<td width="35%"><cus:SelectOneTag property="occConDes.industryCategory" style="width: 50%"  defaultText='请选择' codeName="行业类别" value="${occConDes.industryCategory}" /></td>
				<th width="15%">审查日期</th>
				<td width="35%"><input name="queryReviewDateStart" id="queryReviewDateStart" value="<fmt:formatDate type='both' value='${queryReviewDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryReviewDateEnd\')}'})" >
					-<input name="queryReviewDateEnd" id="queryReviewDateEnd" value="<fmt:formatDate type='both' value='${queryReviewDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryReviewDateStart\')}'})" ></td>
			</tr>
			<tr>
				<th width="15%">审查结果</th>
				<td width="35%"><input name="occConDes.review" id="review" style="width: 50%"  value="${occConDes.review}" type="text" maxlength="127"></td>
			</tr>
			
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_occConDes()" >查询<b></b></a>&nbsp;
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
