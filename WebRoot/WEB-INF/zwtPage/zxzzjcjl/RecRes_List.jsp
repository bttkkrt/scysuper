<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>专项整治检查记录管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_recRes","添加专项整治检查记录","${ctx}/jsp/zxzzjcjl/recResInitEdit.action?flag=add&dt="+dt.getTime(),700,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_recRes","修改专项整治检查记录","${ctx}/jsp/zxzzjcjl/recResInitEdit.action?flag=mod&recRes.id="+row_Id+"&dt="+dt.getTime(),700,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_recRes","查看专项整治检查记录","${ctx}/jsp/zxzzjcjl/recResView.action?recRes.id="+row_Id+"&dt="+dt.getTime(),700,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_recRes();
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
		                	url : "recResDel.action",
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
		                        	search_recRes();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_recRes(){
        	var queryParams = {
				 "queryCheckTimeStart" :$("#queryCheckTimeStart").val(),
 "queryCheckTimeEnd" :$("#queryCheckTimeEnd").val(),
"recRes.checkDept": $("#checkDept").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	var toolbar=[];
        	if("${addRight}"=='y'){
        		toolbar=[{
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
				title:'专项整治检查记录列表',
				url:'recResQuery.action',
				queryParams:{
					 "queryCheckTimeStart" :$("#queryCheckTimeStart").val(),
 "queryCheckTimeEnd" :$("#queryCheckTimeEnd").val(),
"recRes.checkDept": $("#checkDept").val()
				},
				//frozenColumns:frozen,
				columns:[[
				{field:'id',width:15,formatter:function(value,rec){
	var box="<input type='checkbox' name='delBox' value='"+value+"'>";
	if(rec.createUserID=="${sessionScope['LOGIN_USER_ID']}"){
		return box;
	}else{
		return "";
	}
	
}},
				{field:'planName',title:'计划名称',width:100},
				          {field:'checkTime',title:'检查时间',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'checkDept',title:'检查部门',width:100},
{field:'rectificationDate',title:'整改期限',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	 if(rec.createUserID == "${sessionScope['LOGIN_USER_ID']}"){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	 }else{
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	 }
	
}}
				        ]],
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
					
				<th width="15%">检查时间</th>
				<td width="35%"><input name="queryCheckTimeStart" id="queryCheckTimeStart" value="<fmt:formatDate type='both' value='${queryCheckTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryCheckTimeEnd\')}'})" >
					-<input name="queryCheckTimeEnd" id="queryCheckTimeEnd" value="<fmt:formatDate type='both' value='${queryCheckTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryCheckTimeStart\')}'})" ></td>
				<th width="15%">检查部门</th>
				<td width="35%"><input name="recRes.checkDept" style="width: 50%" id="checkDept" value="${recRes.checkDept}" type="text" maxlength="127"></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_recRes()" >查询<b></b></a>&nbsp;
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
