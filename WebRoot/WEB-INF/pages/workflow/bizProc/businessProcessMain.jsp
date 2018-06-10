<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>模块列表</title>
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
				<script src="${ctx}/webResources/My97DatePicker/WdatePicker.js"></script>
		<link rel="stylesheet" type="text/css"
			href="${ctx}/webResources/themes/${curr_user.cssId}/css/style.css">
		<script type="text/javascript">
  $(function(){
			$('#pagination').datagrid({
				title:'流程列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				width:fixWidth(1),
				collapsible:true,
				url:'listBizProc.action',
				queryParams:{
				"businessInfo": $("#businessInfo").val(),
				"registerTime": $("#registerTime").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'businessInfo',title:'流程名',width:setClumWidth(0.1)},
                          {field:'remark',title:'业务备注',width:setClumWidth(0.1)},
                          {field:'x',title:'流程名称',width:setClumWidth(0.1),formatter:function(value,rec){
                                                             if(rec.staticProcessDefinition==null) return;
                                                             return rec.staticProcessDefinition.processName}},
                          {field:'createTime',title:'登记时间',formatter:function(value,rec){
                                                             if(rec.createTime==null) return;
                                                             var date = new Date(rec.createTime.time);
                                                             var month = parseInt(date.getMonth()+1);
                                                              return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();},
                                                              width:setClumWidth(0.2)},
                          {field:'registerTime',title:'流程启动时间',formatter:function(value,rec){
                                                             if(rec.registerTime==null) return;
                                                             var date = new Date(rec.registerTime.time);
                                                             var month = parseInt(date.getMonth()+1);
                                                              return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();},
                                                              width:setClumWidth(0.15)},
                          {field:'Ccc',title:'流程状态',formatter:function(value,rec){
                                                             if(rec.processStatus=='U') return '使用中';
                                                             if(rec.processStatus=='E') return '已结束';},
                                                              width:setClumWidth(0.1)},
                          {field:'op',title:'操作',width:100,formatter:function(value,rec){
                          if(rec.processInstanceId==0) return  "<a href='<c:url value='/bizProc/showEditBizProc.action?bizProcId="+rec.id+"'/>'>"+
													"<img src='<c:url value='/webResources/images/icons/edit.gif'/>' style='border: 0'> 编辑</a>"
													+"<a href='<c:url value='/bizProc/selectWorkflow.action?bizProcId="+rec.id+"'/>'>"+
														"<img src='<c:url value='/webResources/images/icons/use.gif'/>' style='border: 0'>启动流程</a>" ;
	                      if(rec.processInstanceId!=0)  return "<a href='<c:url value='/bizProc/showEditBizProc.action?bizProcId="+rec.id+"'/>'>"+
													"<img src='<c:url value='/webResources/images/icons/edit.gif'/>' style='border: 0'> 编辑</a>" },
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
				"businessInfo": $("#businessInfo").val(),
				"registerTime": $("#registerTime").val() 
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$("#pagination").datagrid('load'); 
		}

		function doAdd() {
			var location = getCenterLocation(380,320);
		    openparentWindow("newWindow","流程发布",location.left,location.top,"600","200","<c:url value="/bizProc/initAddBizProc.action"/>",true,true,true,false,true,"win");
		}
		function procSupervise(procInsId){
			var url ='<c:url value="/bizProc/procSupervise.action"/>?procInsId='+procInsId;
			window.open (url, '流程监控', 'height=520, width=670, top=100, left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
		}
		
		function doCancel(rowId){
		 
		 var r=confirm("确认删除吗？");
		 if (r==true)
           {
          
             location.href='<c:url value="/bizProc/removeBizProc.action"/>?bizProcId='+rowId;
           }
         else
           {
             return;
           }
          
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
		<c:set var="curr_path" value="业务流程管理"></c:set>
		<%@include file="/WEB-INF/template/content_title.jsp"%>
		<form name="form1" action="" method="post" id="form1">
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr>
					<td>
						<div class="submitdata">
							<table cellspacing="0" cellpadding="0" width="100%" border="1">
								<tr border="1">
									<th border="1">
										业务摘要
									</th>
									<td>
										<input type="text" name="businessInfo" id="businessInfo"
											value="${businessInfo }" />
									</td>
									<th border="1">
										启动时间
									</th>
									<td>
										<input name="registerTime" class="WDate" id="registerTime" 
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
									</td>

									<td align="right">
										<a href="###" class="easyui-linkbutton" onclick="doQuery()"
											iconCls="icon-search">查询</a>&nbsp;
										<a href="###" class="easyui-linkbutton" onclick="doAdd()"
											iconCls="icon-add">添加</a>&nbsp;
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
