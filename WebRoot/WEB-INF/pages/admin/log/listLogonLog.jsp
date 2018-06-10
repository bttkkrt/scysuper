<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>登录日志</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
			function search_log(){
				var queryParams = {
					"logonLog.visitor.displayName" : $("#displayName").val(),
					"logonLog.loginType" : $("#loginType").val(),
					"os": $("#os").val(),
					"browser": $("#browser").val(),
					"beginDate" : $("#beginDate").val(),
					"endDate" : $("#endDate").val()
				};  
	        	$('#pagination').datagrid('options').queryParams = queryParams;
	        	$("#pagination").datagrid('load'); 
			}
			$(function(){
				$('#pagination').datagrid($.extend(dg_cm_pp,{
					title:'登录日志列表',
					url:'logonLog.action',
					queryParams:{
						"logonLog.visitor.displayName" : $("#displayName").val(),
						"beginDate" : $("#beginDate").val(),
						"endDate" : $("#endDate").val(),
						"browser": $("#browser").val(),
						"logonLog.loginType" : $("#loginType").val(),
						"os": $("#os").val()
					},
					columns:[[
					          {field:'loginId',title:'登录者ID',width:0.1,formatter:function(value,rec){
					        	  return rec.visitor.loginId;
							  }},
					          {field:'displayName',title:'登录者姓名',width:0.1,formatter:function(value,rec){
					        	  return rec.visitor.displayName;
							  }},
					          {field:'deptName',title:'登录者所属部门',width:0.1,formatter:function(value,rec){
					        	  return rec.visitor.dept.deptName;
							  }},
					          {field:'loginType',title:'登录类型',width:0.1},
					          {field:'visitedDate',title:'登录时间',width:0.2,formatter:function(value,rec){
					              if(rec.visitedDate==null) {return;}
							      var date = new Date(rec.visitedDate.time);
							      var retStr = date.format("yyyy-MM-dd hh:mm:ss");
							      return retStr;
					          }},
					          {field:'fromIp',title:'登录地址',width:0.2},
					          {field:'browser',title:'浏览器',width:0.1},
					          {field:'os',title:'操作系统',width:0.1}
					        ]]
				}));
			});
		</script>
	</head>

	<body>
		<div class="page_content">
			<div class="box_01 submitdata">
				<div class="inner12px">
					<div class="boxBmargin12 cell">
						<form name="myform" method="post">
							<table>
								<tr>
									<th width="15%">登录者ID或姓名</th>
									<td width="35%"><input id="displayName" name="logonLog.visitor.displayName"></td>
									<th width="15%">登录类型</th>
									<td width="35%"><input id="loginType" name="logonLog.loginType"></td>
								</tr>
								<tr>
									<th width="15%">操作系统</th>
									<td width="35%">
									   <select id="os" name="logonLog.os">
									      <option value="" selected="selected">请选择</option>
					                      <option value="Windows">Windows</option>
					                      <option value="Linux">Linux</option>
					                      <option value="Mac">Mac</option>
					                      <option value="其他">其他</option>
					                   </select>
									</td>
									<th width="15%">浏览器类型</th>
									<td width="35%">
									   <select id="browser" name="logonLog.browser">
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
									<th width="15%">登录时间</th>
									<td width="35%" colspan="3">
										<input type="text" name="beginDate" id="beginDate" class="Wdate"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}'})">
										~
										<input type="text" name="endDate" id="endDate" class="Wdate"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginDate\')}'})">
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="btn_area_setc">
											<a href="###" class="btn_01" onclick="clear_form(document.myform);">清空<b></b></a>
											<a href="###" class="btn_01" onclick="search_log();">查询<b></b></a>
										</div>
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
