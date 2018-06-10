<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>DATA_LOG管理</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
        function view(row_Id){
        	var dt=new Date();
        	createSimpleWindow("dataLogWindow","查看数据操作日志","${ctx}/jsp/admin/log/dataLogView.action?dataLog.id="+row_Id+"&dt="+dt.getTime(), 800, 400);
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_dataLog();
        }
        function search_dataLog(){
        	var queryParams = {
				"dataLog.entityName": $("#entityName").val(),
                "dataLog.opType": $("#opType").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        $(function(){
 			window.autoDatagridHeight= <%=(String) session.getAttribute("autoDatagridHeight")%>;
			$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'数据操作日志列表',
				url:'dataLogQuery.action',
				queryParams:{
					"dataLog.entityName": $("#entityName").val(),
					"dataLog.opType": $("#opType").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
					{field:'entityName',title:'数据对象名称',width:fixWidth(0.25)},
					{field:'createTime',title:'操作开始时间',width:fixWidth(0.15)},
					{field:'updateTime',title:'操作结束时间',width:fixWidth(0.15)},
					{field:'recordNum',title:'记录数',width:fixWidth(0.1)},
					{field:'opDuration',title:'操作时长(ms)',width:fixWidth(0.1)},
					{field:'opType',title:'操作类型',width:fixWidth(0.08)},
					{field:'op',title:'操作',width:fixWidth(0.08),formatter:function(value,rec){
						return "<a href='#' onclick=\"view('"+rec.id+"')\" class='btn_01_mini'>查看<b></b></a>";
					}}
				]]
			}));
		});
    </script>
	</head>

	<body>
		<div class="page_content">
			<div class="box_01  submitdata">
				<div class="inner12px">
					<div class="cell boxBmargin12">
						<form name="myform" method="post">
							<table width="100%">
								<tr>
									<th width="15%">
										数据对象名称
									</th>
									<td width="35%">
										<input name="dataLog.entityName" id="entityName"
											class="form_text" value="${dataLog.entityName}" type="text">
									</td>
									<th width="15%">
										操作类型
									</th>
									<td width="35%">
										<cus:SelectOneTag property="dataLog.opType" defaultText='请选择'
											codeName="数据操作类型" value="${dataLog.opType}" />
									</td>
								</tr>
								<tr>
								</tr>
								<tr>
									<td colspan="4">
										<div class="btn_area_setc">
											<a href="###" class="btn_01" onclick="search_dataLog()">查询<b></b>
											</a>
											<a href="###" class="btn_01"
												onclick="clear_form(document.myform);">清空<b></b> </a>
										</div>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="pagination">
					</div>
				</div>
			</div>
		</div>

	</body>
</html>
