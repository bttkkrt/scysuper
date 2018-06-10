<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>报表管理</title>
		<%@include file="/common/jsLib.jsp"%>
		<script type="text/javascript">		
        function reloadDate(){
        	doQuery();
        }
		function doDeploy(){
			createSimpleWindow("deploy_report","发布报表","${ctx}/report/initDeploy.action", 450, 350);
		}
       	function doEdit(reportId){
			createSimpleWindow("deploy_report","编辑报表信息","${ctx}/report/initEditReport.action?reportId="+reportId, 800, 480);        
        }
		function doDelete(){
        	$.messager.confirm('删除报表', '确定要删除报表信息？', function(result){
				if (result){
					var id = [];
					var rows = $('#reportGrid').datagrid('getSelections');
					for(var i=0;i<rows.length;i++){
						id.push(rows[i].id);
					}
					if(0 == rows.length){
						$.messager.alert('警告','请选择要删除的条目!','warning');
					}else{
						var paraIds = "";
						for(var i=0;i<rows.length;i++){
							if("" == paraIds){
								paraIds = "id="+id[i];
							}else{
								paraIds += "&id="+id[i];
							}
						}
		                $.ajax({
		                	url : "removeReport.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data: paraIds,
		                    error: function(){
		                    	$.messager.alert('错误','删除报表信息时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','删除报表信息成功！');
		                        	doQuery();
		                        }else{
		                        	$.messager.alert('错误','删除报表信息时出错！');
		                        }
		                    }
		                });							
					}
				}
			});
		}
		function doQuery(){
			var queryParams = {
				"report.reportName" : $("#reportName").val(),
				"report.deployFileName" : $("#deployFileName").val(),
				"report.deployer" : $("#deployer").val()
			};        	
        	$('#reportGrid').datagrid('options').queryParams = queryParams;
        	$("#reportGrid").datagrid('load'); 
        }
        $(function(){
			var autoDatagridHeight = <%=(String) session.getAttribute("autoDatagridHeight") %>;
			if (autoDatagridHeight) var pageHeight = "auto";
			else var pageHeight = document.body.clientHeight - $(".submitdata").outerHeight();        	
			$('#reportGrid').datagrid({
				toolbar:[{
					text:'发布',
					iconCls:'icon-add',
					handler:doDeploy
				},'-',{
					text:'删除',
					iconCls:'icon-remove',
					handler:doDelete
				}],					
				title:'报表列表',
				iconCls:'icon-save',
				width:fixWidth(1),
				height: pageHeight,
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'findReportList.action',
				queryParams:{
					"report.reportName" : $("#reportName").val(),
					"report.deployFileName" : $("#deployFileName").val(),
					"report.deployer" : $("#deployer").val()
				},
				sortName: 'reportName',
				idField:'id',
				sortOrder: 'asc',
				remoteSort: false,
				columns:[[
						  {field:'id',checkbox:true},
				          {field:'reportName',title:'报表名称',width:fixWidth(0.08)},
				          {field:'deployFileName',title:'发布文件名',width:fixWidth(0.15)},
				          {field:'url',title:'访问地址',width:fixWidth(0.3)},
				          {field:'latestVersion',title:'最新版本号',width:fixWidth(0.06)},
				          {field:'deployer',title:'发布者',width:fixWidth(0.08)},
				          {field:'updateTime',title:'发布时间',width:fixWidth(0.06),formatter:function(value,rec){
								if(rec.updateTime==null) return;
								var date = new Date(rec.updateTime.time);
								var month = parseInt(date.getMonth()+1);
								return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
						  }},
				          {field:'op',title:'操作',width:fixWidth(0.2),formatter:function(value,rec){
				          		return "<a style='color:#000' href='<c:url value='/report/initUpdateVersion.action?reportId="+rec.id+"'/>'>"
				          			+"&nbsp;&nbsp;&nbsp;<img src='<c:url value='/webResources/images/icons/edit.gif'/>' style='border: 0'>更新版本</a>"
				          			+"<a style='color:#000' href='<c:url value='/report/findHistoryVersions.action?reportId="+rec.id+"'/>'>"
				          			+"&nbsp;&nbsp;&nbsp;<img src='<c:url value='/webResources/images/icons/example.gif'/>' style='border: 0'>历史版本</a>";
						  }}
				        ]],
				pagination:true,
				rownumbers:true,
				pageList:[10,20,30],
				onLoadSuccess:tabOnloadSuccess,
				onLoadError:tabOnloadSuccess
			});
		});
		$(window).resize(function() {
    		var autoDatagridHeight = <%=(String) session.getAttribute("autoDatagridHeight") %>
    		if (autoDatagridHeight) var pageHeight = "auto";
    		else var pageHeight = document.body.clientHeight - $(".submitdata").outerHeight();
    		$('#reportGrid').datagrid('resize', {
        		height: pageHeight
    		});
		});        
		</script>
	</head>
	
	<body>
		<div class="submitdata">
			<table cellspacing="0" cellpadding="0" width="100%" border="1">
				<tr>
					<th align="center">报表名称：</th>
					<td>
						<input type="text" id="reportName" name="report.reportName" value="${report.reportName}" />
					</td>
					<th align="center">发布文件名：</th>
					<td>
						<input type="text" id="deployFileName" name="report.deployFileName" value="${report.deployFileName}" />
					</td>
					<th align="center">发布者：</th>
					<td>
						<input type="text" id="deployer" name="report.deployer" value="${report.deployer}" />
					</td>
					<td align="right">
						<a href="###" class="easyui-linkbutton" onclick="doQuery()" iconCls="icon-search">查询</a>
					</td>
				</tr>
			</table>
		</div>
		<div id="reportGrid" style="background:#efefef;border:1px solid #ccc;"></div>
	</body>
</html>
