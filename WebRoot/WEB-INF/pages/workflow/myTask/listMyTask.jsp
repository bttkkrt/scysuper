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
				title:'待办列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'findMyTasks.action',
				queryParams:{
				"taskName": $("#taskName").val(),
				"bizProInfo": $("#bizProInfo").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				           {field:'x',title:'业务名称',width:setClumWidth(0.1),formatter:function(value,rec){
                                                             return rec.businessProcess.businessInfo}},
                          {field:'processName',title:'流程名称',width:setClumWidth(0.15)},
                          {field:'x',title:'流程备注',width:setClumWidth(0.1),formatter:function(value,rec){
                                                             if(rec.businessProcess==null) return;
                                                             return rec.businessProcess.remark}},
                          {field:'nodeName',title:'节点名称', width:setClumWidth(0.15)},
                          {field:'taskName',title:'任务名称', width:setClumWidth(0.15)},
                          {field:'registerTime',title:'流程启动时间',formatter:function(value,rec){
                                                             if(rec.businessProcess==null) return;
                                                             var date = new Date(rec.businessProcess.registerTime.time);
                                                             var month = parseInt(date.getMonth()+1);
                                                              return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();},
                                                              width:setClumWidth(0.15)},
                          {field:'op',title:'操作',width:100,formatter:function(value,rec){
	                        return "<a href='<c:url value='/workflow/myTask/myTaskHandle.action?bizProcId="+rec.businessProcess.id+"&taskInsId="+rec.taskInstanceId+"'/>'>"+
								   "<img src='<c:url value='/webResources/images/icons/edit.gif'/>' style='border: 0'> 处理</a>" },
													width:setClumWidth(0.13)}
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
		
		
		
		
		
		
		
	function taskHandle(businessId, taskInstanceId, numOfTask){
		var intNumOfTask = parseInt(numOfTask,10);
		if(intNumOfTask > 1){
			//节点任务数大于1个,会签任务
			alert(numOfTask);
		 	form1.action='<c:url value="/workflow/myTask/multiTaskHandle.action"/>?bizProcId=' + businessId + '&taskInsId=' + taskInstanceId + '&numOfTask=' + numOfTask;
		}else{
			//节点任务数等于1个
			alert(numOfTask);
		 	form1.action='<c:url value="/workflow/myTask/myTaskHandle.action"/>?bizProcId=' + businessId + '&taskInsId=' + taskInstanceId;
		}

		form1.submit();
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
		<c:set var="curr_path" value="待办任务"></c:set>
		<%@include file="/WEB-INF/template/content_title.jsp"%>

		<form action="" method="post" id="form1" enctype="multipart/form-data">
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr>
					<td valign="top">
						<table cellspacing="0" cellpadding="0" width="100%" align="left"
							border="0">
							<tr>
								<td align="left">
									<div class="submitdata">
										<table cellspacing="0" cellpadding="0" width="100%" border="1">
											<tr>
												<th align="center">
													业务名称：
												</th>
												<td>
													<input type="text" name="bizProInfo" id="bizProInfo" value="${bizProInfo}" />
												</td>
												<th align="center">
													任务名称：
												</th>
												<td>
													<input type="text" name="taskName" id="taskName" value="${taskName}" />
												</td>
												<td align="right">
												<a href="###" class="easyui-linkbutton" onclick="doQuery()"
											iconCls="icon-search">查询</a>&nbsp;
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
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
