<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户行为日志管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        function view(row_Id){
        	var dt=new Date();
    		createSimpleWindow("logWindow","查看用户行为日志","${ctx}/jsp/admin/behavior/userBehaviorLogView.action?userBehaviorLog.id="+row_Id+"&dt="+dt.getTime(), 500, 300);
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_userBehaviorLog();
        }
        function search_userBehaviorLog(){
        	var queryParams = {
				"userBehaviorLog.behavior.id" : "${userBehaviorLog.behavior.id}"
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$("#pagination").datagrid('load'); 
        }
        $(function(){
 			var autoDatagridHeight = <%=(String) session.getAttribute("autoDatagridHeight") %>;
			if (autoDatagridHeight) var pageHeight = "auto";
			else var pageHeight = document.body.clientHeight - $(".submitdata").outerHeight();       	
			$('#pagination').datagrid({
				title:'用户行为日志列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				height: pageHeight,
				collapsible:true,
				url:'userBehaviorLogQuery.action',
				queryParams:{
					"userBehaviorLog.behavior.id" : "${userBehaviorLog.behavior.id}"
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
					{field:'loggedDate',title:'记录时间',width:fixWidth(0.2),formatter:function(value,rec){
						if(rec.loggedDate==null) return;
						var date = new Date(rec.loggedDate.time);
						var retStr = date.format("yyyy-MM-dd hh:mm:ss");
						return retStr;
					}},
					{field:'user.displayName',title:'操作者',width:fixWidth(0.15),formatter:function(value,rec){
						if(rec.user)
							return rec.user.displayName;
						else
							return "未登录用户";
					}},
					{field:'logContent',title:'日志内容',width:fixWidth(0.5)},
					{field:'remoteIp',title:'访问IP',width:fixWidth(0.1)},
					{field:'op',title:'操作',width:fixWidth(0.1),formatter:function(value,rec){
						return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>";
					}}
				]],
				pagination:true,
				onLoadSuccess:tabOnloadSuccess,
				onLoadError:tabOnloadSuccess,
				rownumbers:true,
				pageList:[10,20,30]
			});
		});
		$(window).resize(function() {
    		var autoDatagridHeight = <%=(String) session.getAttribute("autoDatagridHeight") %>
    		if (autoDatagridHeight) var pageHeight = "auto";
    		else var pageHeight = document.body.clientHeight - $(".submitdata").outerHeight();
    		$('#pagination').datagrid('resize', {
        		height: pageHeight
    		});　　
		});	
    </script>
</head>

<body>
<form name="myform" method="post">
	<div class="submitdata">
		<table width="100%">
			<tr>
			    <th>日志记录日期</th>
			    <td colspan="2">
			        <input type="text" name="queryLoggedDateStart" id="queryLoggedDateStart"
					    class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryLoggedDateEnd\')}'})"
						value="<fmt:formatDate type="date" value="${queryLoggedDateStart}" pattern="yyyy.MM.dd HH:mm:ss"/>">
					~
					<input type="text" name="queryLoggedDateEnd" id="queryLoggedDateEnd" class="Wdate"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryLoggedDateStart\')}'})"
						 value="<fmt:formatDate type="date" value="${queryLoggedDateEnd}" pattern="yyyy.MM.dd HH:mm:ss"/>">
			    </td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a href="###" class="easyui-linkbutton" onclick="search_userBehaviorLog()" iconCls="icon-search">查询</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				</td>
			</tr>
		</table>
	</div>
	<div id="pagination" style="background:#efefef;border:1px solid #ccc;">
	</div>
</form>
</body>
</html>
