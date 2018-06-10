<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户登出日志管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        function search_logoffLog(){
        	var queryParams = {
				"logoffLog.user.displayName" : $("#displayName").val(),
				"logoffLog.logoffType" : $("#logoffType").val(),
				"logoffLog.operationsystem": $("#os").val(),
				"logoffLog.browser": $("#browser").val(),
				"queryLogoffDateStart" : $("#beginDate").val(),
				"queryLogoffDateEnd" : $("#endDate").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'用户登出日志列表',
				url:'logoffLogQuery.action',
				queryParams:{
					"logoffLog.user.displayName" : $("#displayName").val(),
					"logoffLog.logoffType" : $("#logoffType").val(),
					"logoffLog.operationsystem": $("#os").val(),
					"logoffLog.browser": $("#browser").val(),
					"queryLogoffDateStart" : $("#beginDate").val(),
					"queryLogoffDateEnd" : $("#endDate").val()
				},
				columns:[[
							{field:'loginId',title:'登出者ID',width:0.1,formatter:function(value,rec){
								if(null!=rec.user.loginId)
								  	return rec.user.loginId;
								else
									return "";
							}},
							{field:'displayName',title:'登出者姓名',width:0.1,formatter:function(value,rec){
								if(null!=rec.user.displayName)  
									return rec.user.displayName;
								else
									return "";
							}},
							{field:'deptName',title:'登出者所属部门',width:0.1,formatter:function(value,rec){
								if(null!=rec.user.dept.deptName)  
									return rec.user.dept.deptName;
								else
									return "";
							}},
							{field:'logoffType',title:'登出类型',width:0.1},
							{field:'logoffDate',title:'登出时间',width:0.2,formatter:function(value,rec){
							    if(null!=rec.logoffDate){
								    var date = new Date(rec.logoffDate.time);
								    var retStr = date.format("yyyy-MM-dd hh:mm:ss");
								    return retStr;							    	
							    }else
							    	return "";

							}},
							{field:'fromIp',title:'登出地址',width:0.2},
							{field:'browser',title:'浏览器',width:0.1},
							{field:'operationsystem',title:'操作系统',width:0.1}
				        ]]
			}));
		}); 
    </script>
</head>
<body>
    <div class="page_content">
	    <div class="box_01 submitdata">
			<div class="inner12px">
				<div class="cell boxBmargin12">
					<form name="myform" method="post">
						<table width="100%">
							<tr>
								<th width="15%">登出用户ID或姓名</th>
								<td width="35%"><input id="displayName" name="logoffLog.user.displayName"></td>
								<th width="15%">登出类型</th>
								<td width="35%"><input id="logoffType" name="logoffLog.logoffType"></td>
							</tr>
							<tr>
								<th width="15%">操作系统</th>
								<td width="35%">
								   <select id="os" name="logoffLog.operationsystem">
								      <option value="" selected="selected">请选择</option>
				                      <option value="Windows">Windows</option>
				                      <option value="Linux">Linux</option>
				                      <option value="Mac">Mac</option>
				                      <option value="其他">其他</option>
				                   </select>
								</td>
								<th width="15%">浏览器类型</th>
								<td width="35%">
								   <select id="browser" name="logoffLog.browser">
								      <option value="" selected="selected">请选择</option>
				                      <option value="IE">IE</option>
				                      <option value="Firefox">Firefox</option>
				                      <option value="Chrome">Chrome</option>
				                      <option value="Safari">Safari</option>
				                      <option value="Opera">Opera</option>
				                      <option value="其他">其他</option>
				                   </select>
								</td>
							</tr>
							<tr>
								<th width="15%">登出时间</th>
								<td width="35%" colspan="3">
									<input type="text" name="beginDate" id="beginDate" class="Wdate"
							onclick="WdatePicker(ker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}'})">
									~
									<input type="text" name="endDate" id="endDate" class="Wdate"
							onclick="WdatePicker(ker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginDate\')}'})">
								</td>
							</tr>					
							<tr>
								<td colspan="4" align="center">
									<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;	
									<a href="###" class="btn_01" onclick="search_logoffLog()" >查询<b></b></a>&nbsp;
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div id="pagination"></div>
			</div>
		</div>
	</div>
</body>
</html>
