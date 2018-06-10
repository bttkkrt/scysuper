<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<c:set var="curr_path" value="在线用户列表"></c:set>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>在线用户列表</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
        function reloadDate(){
        	search_user();
        }
		function search_user(){
        	$('#onlineUserGrid').datagrid('options').queryParams = queryParams;
        	$("#onlineUserGrid").datagrid('load'); 
        }
        $(function(){
			var autoDatagridHeight = <%=(String) session.getAttribute("autoDatagridHeight") %>;
			if (autoDatagridHeight) var pageHeight = "auto";
			else var pageHeight = document.body.clientHeight;         
			$('#onlineUserGrid').datagrid({
				title:'在线人员列表',
				iconCls:'icon-save',
				height: pageHeight,
				striped: true,
				fitColumns: true,
				nowrap: false,
				collapsible:true,
				url:'onLineUserList.action',
				queryParams:{
				},
				sortName: '',
				idField:'id',
				sortOrder: 'asc',
				remoteSort: false,
				columns:[[
				          {field:'displayName',title:'姓名',width:fixWidth(0.2),formatter:function(value,rec){
		        			return rec.user.displayName;
						  }},
				          {field:'loginId',title:'用户名',width:fixWidth(0.3),formatter:function(value,rec){
		        			return rec.user.loginId;
						  }},
				          {field:'loginTime',title:'登录时间',width:fixWidth(0.2),formatter:function(value,rec){
							if(rec.loginTime==null) {return;}
							var date = new Date(rec.loginTime.time);
							var month = parseInt(date.getMonth()+1);
							return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
						  }},
				          {field:'loginType',title:'登录方式',width:fixWidth(0.2)}
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
    		else var pageHeight = document.body.clientHeight;
    		$('#onlineUserGrid').datagrid('resize', {
        		height: pageHeight
    		});　　
		});			
    	</script>
	</head>

	<body>
		<div id="onlineUserGrid" style="background:#efefef;border:1px solid #ccc;"></div>
	</body>
</html>
