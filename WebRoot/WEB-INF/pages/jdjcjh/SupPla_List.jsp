<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>监督检查计划管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_supPla","添加监督检查计划","${ctx}/jsp/jdjcjh/supPlaInitEdit.action?flag=add&dt="+dt.getTime(),1000,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supPla","修改监督检查计划","${ctx}/jsp/jdjcjh/supPlaInitEdit.action?flag=mod&supPla.id="+row_Id+"&dt="+dt.getTime(),1000,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supPla","查看监督检查计划","${ctx}/jsp/jdjcjh/supPlaView.action?supPla.id="+row_Id+"&dt="+dt.getTime(),1000,550);
        	
        }
        function link(row_Id){
            var id="newWindow";
    		var text = "查看关联任务";
    		var url ="/jsp/jdjcjh/supPlaLink.action?supPla.id="+row_Id;
             window.parent.addTab(id,text,url);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_supPla();
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
		                	url : "supPlaDel.action",
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
		                        	search_supPla();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_supPla(){
        	var queryParams = {
				"supPla.planName": $("#planName").val(),
"supPla.planType": $("#planType").val(),
"supPla.checkItemType": $("#checkItemType").val(),
"supPla.checkCompanyName": $("#checkCompanyName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
         var toolbar = [];
        	if('${roleName}'=='09'){//判断登录角色
				toolbar = [{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						addNew();
					}
				},'-',{
					text:'导入年计划',
					iconCls:'icon-add',
					handler:importPlan
				}];
			
			}else if('${roleName}'=='1'){//判断登录角色
				toolbar = [{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						addNew();
					}
				}];
			
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'监督检查计划列表',
				url:'supPlaQuery.action',
				queryParams:{
					"supPla.planName": $("#planName").val(),
"supPla.planType": $("#planType").val(),
"supPla.checkItemType": $("#checkItemType").val(),
"supPla.checkCompanyName": $("#checkCompanyName").val()
				},
				
				columns:[[
				          {field:'planName',title:'计划名称',width:100},
{field:'planType',title:'计划类型',width:100},
{field:'checkCompanyName',title:'检查对象名称',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>&nbsp;<a class='btn_04_mini' onclick=link('"+rec.id+"')>关联任务<b></b></a>";
var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=link('"+rec.id+"')>关联任务<b></b></a>";
var button3="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
var button4="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
if(("${sessionScope['LOGIN_USER_ID']}"==rec.createUserID)&&(rec.planType!="年计划")&&(rec.ifywc=="0")&&(rec.planType!="临时计划")){
	return  button1;
}else if(("${sessionScope['LOGIN_USER_ID']}"!=rec.createUserID&&(rec.planType!="年计划")&&(rec.planType!="临时计划"))){
	return button2;
}else if(((rec.planType!="年计划")&&(rec.ifywc=="1")&&(rec.planType!="临时计划"))){
return button2;
}
else if("${sessionScope['LOGIN_USER_ID']}"==rec.createUserID&&((rec.planType=="年计划")||(rec.planType=="临时计划"))){
    return button3;
}else{
   return button4;
}
}}				        ]],

				toolbar:toolbar
			}));
		});


	function importPlan(){ 
        	createSimpleWindow("importPlan","导入年计划","${ctx}/jsp/jdjcjh/initImportPlan.action", 350, 200);
        }
        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
				<tr>
					
				<th width="15%">计划名称</th>
				<td width="35%"><input name="supPla.planName" id="planName" value="${supPla.planName}" type="text" maxlength="127" style="width:50%"></td>
				<th width="15%">计划类型</th>
				<td width="35%"><cus:SelectOneTag property="supPla.planType" defaultText='请选择' codeName="监督检查计划类型" style="width:50%" value="${supPla.planType}" /></td>
			</tr>
			<tr>
				<th width="15%">检查对象名称</th>
				<td width="35%"><input name="supPla.checkCompanyName" id="checkCompanyName" value="${supPla.checkCompanyName}" style="width:50%" type="text" maxlength="127"></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_supPla()" >查询<b></b></a>&nbsp;
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
