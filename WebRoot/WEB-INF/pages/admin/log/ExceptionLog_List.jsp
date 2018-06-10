<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>异常日志</title>
	    <%@include file="/common/jsLib.jsp"%>
		<script>
	        function view(row_Id){
	        	var dt = (new Date()).getTime();
	            createSimpleWindow("expWindow","查看详情","${ctx}/jsp/admin/log/exceptionLogView.action?exceptionLog.id="+row_Id+"&dt="+dt, 800, 260);
	        }
	        function search(){
	        	var queryParams = {
					"exceptionLog.className":$("#className").val(),
					"exceptionLog.mothodName":$("#mothodName").val()
				};        	
	        	$("#pagination").datagrid('options').queryParams = queryParams;
	        	$("#pagination").datagrid('load'); 
	        }
	       	window.autoDatagridHeight = <%=(String)session.getAttribute("autoDatagridHeight")%>;
	        $(function(){
				$('#pagination').datagrid($.extend(dg_cm_pp,{
					title:'异常日志列表',
					url:'exceptionLogQuery.action',
					columns:[[
					          {field:'className', title:'类名', width:0.45},
					          {field:'mothodName', title:'方法名', width:0.1},
					          {field:'logLevel', title:'日志级别', width:0.1},
					          {field:'createTime', title:'发生时间', width:0.15},
					          {field:'displayName', title:'操作者', width:0.1, formatter:function(value, rec){
					        	  if(rec.oprator)
					        		  return rec.oprator.displayName;
					          }},
					          {field:'op', title:'操作', width:0.1, formatter:function(value,rec){
		                          return "<a href='#' class='btn_01_mini' onclick=\"view('"+rec.id+"')\">查看<b></b></a>";
	                          }}
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
									<th width="15%">类名</th>
									<td width="35%"><input name="exceptionLog.className" id="className"></td>
									<th width="15%">方法名</th>
									<td width="35%"><input name="exceptionLog.mothodName" id="mothodName"></td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="btn_area_setc">
											<a href="###" class="btn_01" onclick="clear_form(document.myform);">清空<b></b></a>
											<a href="###" class="btn_01" onclick="search();">查询<b></b></a>
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
