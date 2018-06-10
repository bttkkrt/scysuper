<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>请假管理管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_leaveInfo","添加请假管理","${ctx}/jsp/leave/leaveInfoInitEdit.action?flag=add&dt="+dt.getTime(), 800, 480);
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_leaveInfo","修改请假管理","${ctx}/jsp/leave/leaveInfoInitEdit.action?flag=mod&leaveInfo.id="+row_Id+"&dt="+dt.getTime(), 800, 480);
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_leaveInfo","查看请假管理","${ctx}/jsp/leave/leaveInfoView.action?leaveInfo.id="+row_Id+"&dt="+dt.getTime(), 800, 480);
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_leaveInfo();
        }
        function del(){
        	var rows = $('#pagination').datagrid('getSelections');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
				ids += rows[i].id+"|";
			}
			if(rows.length<1){
			    $.messager.alert('提示','至少选择一项删除！');
			}else{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "leaveInfoDel.action",
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
		                        	search_leaveInfo();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_leaveInfo(){
        	var queryParams = {
				"queryStarttimeStart" :$("#queryStarttimeStart").val(),
 				"queryStarttimeEnd" :$("#queryStarttimeEnd").val(),
 				"queryEndtimeStart" :$("#queryEndtimeStart").val(),
				"queryEndtimeEnd" :$("#queryEndtimeEnd").val(),
				"leaveInfo.userId": $("#userId").val(),
				"leaveInfo.reason": $("#reason").val(),
				"queryApplyTimeStart" :$("#queryApplyTimeStart").val(),
				"queryApplyTimeEnd" :$("#queryApplyTimeEnd").val(),
				"leaveInfo.processInstanceId": $("#processInstanceId").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        $(function(){
			$('#pagination').datagrid($.extend(dg_cm_pp,{
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
				}],			
				title:'请假管理列表',
				url:'leaveInfoQuery.action',
				fitColumns:false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
					{field:'userId',title:'用户ID',width:fixWidth(0.2)},
					{field:'reason',title:'请假原因',width:fixWidth(0.5)},
					{field:'starttime',title:'请假开始时间',width:fixWidth(0.15)},
					{field:'endtime',title:'请假结束时间',width:fixWidth(0.15)},
					{field:'applyTime',title:'申请时间',width:fixWidth(0.15)},
					{field:'processInstanceId',title:'流程实例ID',width:fixWidth(0.1)},
					{field:'op',title:'操作',width:fixWidth(0.15),formatter:function(value,rec){
						return "<a href='#' onclick=\"view('"+rec.id+"')\" class='btn_01_mini'>查看<b></b></a><a href='#' onclick=\"edit('"+rec.id+"')\" class='btn_01_mini'>修改<b></b></a>";
					}}
				]]
			}));
		});
    </script>
</head>

<body>
<div class="page_content">
		<div class="box_01 submitdata">
			<div class="inner12px">
				<form name="myform" method="post">
					<div class="cell boxBmargin12">
						<table width="100%">
							<tr>
								<th width="15%">请假开始时间</th>
								<td width="35%"><input name="queryStarttimeStart" id="queryStarttimeStart" value="<fmt:formatDate type='both' value='${queryStarttimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryStarttimeEnd\')}'})" >
									-<input name="queryStarttimeEnd" id="queryStarttimeEnd" value="<fmt:formatDate type='both' value='${queryStarttimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryStarttimeStart\')}'})" ></td>
								<th width="15%">请假结束时间</th>
								<td width="35%"><input name="queryEndtimeStart" id="queryEndtimeStart" value="<fmt:formatDate type='both' value='${queryEndtimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryEndtimeEnd\')}'})" >
									-<input name="queryEndtimeEnd" id="queryEndtimeEnd" value="<fmt:formatDate type='both' value='${queryEndtimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryEndtimeStart\')}'})" ></td>
							</tr>
							<tr>
								<th width="15%">用户ID</th>
								<td width="35%"><input name="leaveInfo.userId" id="userId" value="${leaveInfo.userId}" type="text"></td>
								<th width="15%">请假原因</th>
								<td width="35%"><input name="leaveInfo.reason" id="reason" value="${leaveInfo.reason}" type="text"></td>
							</tr>
							<tr>
								<th width="15%">申请时间</th>
								<td width="35%"><input name="queryApplyTimeStart" id="queryApplyTimeStart" value="<fmt:formatDate type='both' value='${queryApplyTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryApplyTimeEnd\')}'})" >
									-<input name="queryApplyTimeEnd" id="queryApplyTimeEnd" value="<fmt:formatDate type='both' value='${queryApplyTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryApplyTimeStart\')}'})" ></td>
								<th width="15%">流程实例ID</th>
								<td width="35%"><input name="leaveInfo.processInstanceId" id="processInstanceId" value="${leaveInfo.processInstanceId}" type="text"></td>
							</tr>
							<tr>
							</tr>
							<tr>
								<td colspan="4">
									<div class="btn_area_setc">
										<a href="###" class="btn_01" onclick="search_leaveInfo()">查询<b></b></a>
										<a href="###" class="btn_01" onclick="clear_form(document.myform);">清空<b></b></a>
									</div>
								</td>								
							</tr>
						</table>
					</div>
					<div id="pagination"></div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
