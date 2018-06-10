<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>访问日志</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
		function search_log(){
			var queryParams = {
				"opLog.visitor.displayName" : $("#displayName").val(),
				"opLog.url" : $("#url").val(),
				"beginDate" : $("#beginDate").val(),
				"endDate" : $("#endDate").val()
			};  
		    $('#logGrid').datagrid('options').queryParams = queryParams;
		    $("#logGrid").datagrid('load'); 
		}
		window.autoDatagridHeight = <%=(String)session.getAttribute("autoDatagridHeight")%>;
		$(function(){
			$('#pagination').datagrid($.extend(dg_cm_pp,{
				title:'访问日志列表',
				url:'opLog.action',
				queryParams:{
					"opLog.visitor.displayName" : $("#displayName").val(),
					"opLog.url" : $("#url").val(),
					"beginDate" : $("#beginDate").val(),
					"endDate" : $("#endDate").val()
				},
				columns:[[
							{field:'loginId',title:'访问者ID',width:0.1,formatter:function(value,rec){
					        	return rec.visitor.loginId;
							}},
							{field:'displayName',title:'访问者姓名',width:0.1,formatter:function(value,rec){
								return rec.visitor.displayName;
							}},
							{field:'deptName',title:'访问者所属部门',width:0.1,formatter:function(value,rec){
							    return rec.visitor.dept.deptName;
							}},				
							{field:'visitedDate',title:'访问日期',width:0.1,formatter:function(value,rec){
								if(rec.visitedDate==null) {return;}
								var date = new Date(rec.visitedDate.time);
								var month = parseInt(date.getMonth()+1);
								return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
							}},
							{field:'moduleName',title:'访问模块',width:0.1,formatter:function(value,rec){
								if(rec.module == null)
									return "";
								else
							    	return rec.module.moduleName;
							}},	
							{field:'url',title:'访问地址',width:0.1}
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
							<input type="hidden" value="${module.moduleCode}" name="module.moduleCode">
							<table>
								<tr>
									<th width="15%">登录者ID或姓名</th>
									<td width="35%"><input id="displayName" name="opLog.visitor.displayName" value="${opLog.visitor.displayName}"></td>
									<th width="15%">登录日期</th>
									<td width="35%">
										<input type="text" name="beginDate" id="beginDate" class="Wdate"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}'})"
											value="<fmt:formatDate type="date" value="${beginDate}" pattern="yyyy.MM.dd HH:mm:ss"/>">
										~
										<input type="text" name="endDate" id="endDate" class="Wdate"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginDate\')}'})"
											value="<fmt:formatDate type="date" value="${endDate}" pattern="yyyy.MM.dd HH:mm:ss"/>">							
									</td>
								</tr>
								<tr>
									<th width="15%">访问地址</th>
									<td width="35%"><input name="opLog.url" id="url" value="${opLog.url}"></td>
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
