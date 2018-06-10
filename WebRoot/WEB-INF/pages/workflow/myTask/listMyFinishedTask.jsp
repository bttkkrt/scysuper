<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>


<html>
	<head>
		<title>待办任务</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="description" content="模块的列表显示页面">
		<script type="text/javascript"
			src="${ctx}/webResources/js/easyui/jquery-1.4.4.min.js"></script>
		<script type="text/javascript"
			src='<c:url value="/webResources/js/common.js"/>'></script>
		<script type="text/javascript"
			src='<c:url value="/webResources/js/easyui/jquery.easyui.min.js"/>'></script>
		<link rel="stylesheet" type="text/css"
			href='<c:url value="/webResources/js/easyui/themes/${curr_user.cssId}/easyui.css"/>'>
		<link rel="stylesheet" type="text/css"
			href='<c:url value="/webResources/js/easyui/themes/icon.css"/>'>
		<script type="text/javascript"
			src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script src="${ctx}/webResources/My97DatePicker/WdatePicker.js"></script>
		<link rel="stylesheet" type="text/css"
			href="${ctx}/webResources/themes/${curr_user.cssId}/css/style.css">
		<script type="text/javascript">
  $(function(){
			$('#pagination').datagrid({
				title:'已办列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				width:fixWidth(1),
				collapsible:true,
				url:'listFinishedTasks.action',
				queryParams:{
					"bizProInfo": $("#bizProInfo").val(),
                    "taskName": $("#taskName").val() 
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'nodeName',title:'节点名称', width:setClumWidth(0.15)},
                          {field:'taskName',title:'任务名称', width:setClumWidth(0.15)},
                          {field:'currentTaskName',title:'处理时间',width:setClumWidth(0.15)},
                          {field:'x',title:'业务名称',width:setClumWidth(0.15),formatter:function(value,rec){
                                                             if(rec.businessProcess==null) return;
                                                             return rec.businessProcess.businessInfo}},
                          {field:'handleInfo',title:'处理意见',width:setClumWidth(0.15)},
                          {field:'op',title:'操作',width:100,formatter:function(value,rec){
	                         return "<a href='<c:url value='/workflow/myTask/findMyFiniTasksDetails.action?bizProcId="+rec.businessProcess.id+
	                       "&procInsId="+rec.businessProcess.processInstanceId+"'/>'>"+
	                        "<img src='<c:url value='/webResources/images/icons/edit.gif'/>' style='border: 0'>查看</a>"
															},
													width:setClumWidth(0.15)}
                                                       
				        ]],
				pagination:true,
				rownumbers:true,
				pageList:[10,20,30],
				onLoadSuccess:tabOnloadSuccess,
				onLoadError:tabOnloadSuccess,
				onHeaderContextMenu: function(e, field){
					e.preventDefault();
					if (!$('#tmenu').length){
						createColumnMenu();
					}
					$('#tmenu').menu('show', {
						left:e.pageX,
						top:e.pageY
					});
				}
			});})


	function setClumWidth(width){
      var curWidth= document.body.clientWidth;
      return curWidth*width;
}

	function doQuery() {
		  	var queryParams = {
				"bizProInfo": $("#bizProInfo").val(),
"taskName": $("#taskName").val() 
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$("#pagination").datagrid('load'); 
		}

</script>
	</head>

	<body>
		<c:set var="curr_path" value="已办任务"></c:set>
		<%@include file="/WEB-INF/template/content_title.jsp"%>

		<form action="" method="post" id="form1" enctype="multipart/form-data">
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr>
					<td>
						<div class="submitdata">
							<table border="1" width="100%">
								<tr border="1">
									<th border="1" align="center">
										业务名称：
									</th>
									<td border="1">
										<input type="text" name="bizProInfo" id="bizProInfo" value="${bizProInfo}" />
									</td>
									<th border="1" align="center">
										任务名称：
									</th>
									<td border="1">
										<input type="text" name="taskName" id="taskName" value="${taskName}" />
									</td>

									<td border="1">
									
									<a href="###" class="easyui-linkbutton" onclick="doQuery()"
											iconCls="icon-search">查询</a>&nbsp;
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div id="pagination"
							style="background: #efefef; border: 1px solid #ccc;">
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
