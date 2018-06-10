<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html>
	<head>
		<title>工作流管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
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
		<link rel="stylesheet" type="text/css"
			href="${ctx}/webResources/themes/${curr_user.cssId}/css/style.css">
		<script type="text/javascript">		
	function doQuery() {//查询流程定义列表
	  	var queryParams = {
							"processName": $("#processName").val(),
							"createUserName": $("#createUserName").val() 
							};        	
       	$('#pagination').datagrid('options').queryParams = queryParams;
       	$("#pagination").datagrid('load'); 
	}
	
	function setClumWidth(width){
		var curWidth= document.body.clientWidth;
		return curWidth*width;
	}
	
	function doDeploy() {//发布新流程
		popupCenter("<c:url value="/workflow/initDeploy.action"/>", "Deploy", "380", "200", 
                    "no", "no", "no", "no", "no","no");
	}	
	
	function doDelete(rowId) {
		if(true == confirm("确认删除吗？")) {
			location.href='<c:url value="/workflow/removeProcessDefinition.action"/>?procDefId='+rowId;
		}else {
			return;
		}
	}
	        $(function(){
        	
			$('#pagination').datagrid({
				title:'流程列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'listProcDef.action',
				queryParams:{
					"processName": $("#processName").val(),
                    "createUserName": $("#createUserName").val() 
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'processName',title:'流程名',width:setClumWidth(0.3)},
                          {field:'createUserName',title:'发布人',width:setClumWidth(0.2)},
                          {field:'createTime',title:'创建时间',formatter:function(value,rec){
                                                             if(rec.createTime==null) return;
                                                             var date = new Date(rec.createTime.time);
                                                             var month = parseInt(date.getMonth()+1);
                                                              return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();},
                                                              width:setClumWidth(0.3)},
                          {field:'op',title:'操作',width:100,formatter:function(value,rec){
	                              return "<a href='<c:url value='/workflow/showProcessDefinitionDetail.action?procDefId="+rec.id+"'/>'>"+
													"<img src='<c:url value='/webResources/images/icons/edit.gif'/>' style='border: 0'> 配置</a>";
	                                                       },width:setClumWidth(0.1)}
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
			
		function new_deploy(){
			var location = getCenterLocation(380,320);
        	openparentWindow("newWindow","流程发布",location.left,location.top,"400","300","<c:url value='/workflow/initDeploy.action'/>",true,true,true,false,true,"win");
        }
        
        function close_win(){
        	$("#newWindow").window("close");
        }
        
        function reloadDate(){
        	doQuery();
        }
			
</script>
	</head>

	<body>
		<c:set var="curr_path" value="工作流定义管理"></c:set>
		<%@include file="/WEB-INF/template/content_title.jsp"%>
		<form action="" method="post" id="form1">
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
												<th align="center" style="">
													流程名称：
												</th>
												<td>
													<input type="text" name="processName" id="processName"
														value="${processName}" />
												</td>
												<th align="center">
													创建人姓名：
												</th>
												<td>
													<input type="text" name="createUserName"
														id="createUserName" value="${createUserName}" />
												</td>
												<td align="right">
													<a href="###" class="easyui-linkbutton" onclick="doQuery()"
														iconCls="icon-search">查询</a>&nbsp;
													<a href="###" class="easyui-linkbutton" onclick="new_deploy()"
														iconCls="icon-add">添加</a>&nbsp;
												
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
