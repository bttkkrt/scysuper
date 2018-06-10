<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>专项整治计划管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_recPla","添加专项整治计划","${ctx}/jsp/zxzzjh/recPlaInitEdit.action?flag=add&dt="+dt.getTime(),800,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_recPla","修改专项整治计划","${ctx}/jsp/zxzzjh/recPlaInitEdit.action?flag=mod&recPla.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_recPla","查看专项整治计划","${ctx}/jsp/zxzzjh/recPlaView.action?recPla.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_recPla();
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
		                	url : "recPlaDel.action",
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
		                        	search_recPla();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_recPla(){
        	var queryParams = {
				"recPla.planName": $("#planName").val(),
 "queryWorkTimeStart" :$("#queryWorkTimeStart").val(),
 "queryWorkTimeEnd" :$("#queryWorkTimeEnd").val()
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
				title:'专项整治计划列表',
				url:'recPlaQuery.action',
				queryParams:{
					"recPla.planName": $("#planName").val(),
 "queryWorkTimeStart" :$("#queryWorkTimeStart").val(),
 "queryWorkTimeEnd" :$("#queryWorkTimeEnd").val()
				},
				//frozenColumns:frozen,
				columns:[[
				{field:'id',width:25,formatter:function(value,rec){
	var box="<input type='checkbox' name='delBox' value='"+value+"'>";
	if(rec.createUserID=="${sessionScope['LOGIN_USER_ID']}"&&rec.status=='0'){
		return box;
	}else{
		return "";
	}
	
}},
				{field:'planName',title:'计划名称',width:100},
				          {field:'workGoal',title:'工作目标',width:100},
{field:'workTimeStart',title:'整治开始时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
{field:'workTimeEnd',title:'整治结束时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
var button3="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=viewCheck('"+rec.id+"')>检查记录<b></b></a>";
if(rec.status=='0'){
	if(rec.createUserID == "${sessionScope['LOGIN_USER_ID']}"){
		return button1;
	 }else{
		return button3;
	 }
}else{
	return button2;
}
}}
				        ]],
				toolbar:[{
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
				}]
			}));
		});

        function viewCheck(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_recRes","查看专项整治检查记录","${ctx}/jsp/zxzzjcjl/recResViewByPlan.action?recPla.id="+row_Id+"&dt="+dt.getTime(),700,550);
        	
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
				<td width="35%"><input name="recPla.planName" id="planName" style="width: 50%" value="${recPla.workGoal}" type="text" maxlength="127"></td>
				<th width="15%">整治时间</th>
				<td width="35%"><input name="queryWorkTimeStart" id="queryWorkTimeStart" value="<fmt:formatDate type='both' value='${queryWorkTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryWorkTimeEnd\')}'})" >
					-<input name="queryWorkTimeEnd" id="queryWorkTimeEnd" value="<fmt:formatDate type='both' value='${queryWorkTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryWorkTimeStart\')}'})" ></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_recPla()" >查询<b></b></a>&nbsp;
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
