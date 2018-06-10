<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户行为日志</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
		$(function(){
	     	$('#roleTree').tree({   
	            checkbox: false,
				cascadeCheck:false, 
	            url: 'findAllUserBehaviors.action', 
	            onBeforeExpand:function(node,param){
	                $('#roleTree').tree('options').url = "findAllUserBehaviors.action";
				},
				onClick:function(node){
					$("#behaviorId").val(node.id);
					search_userBehaviorLog();
				}
	        });   
	    });
	     function view(row_Id){
        	var dt=new Date();
    		createSimpleWindow("logWindow","查看用户行为日志","${ctx}/jsp/admin/behavior/userBehaviorLogView.action?userBehaviorLog.id="+row_Id+"&dt="+dt.getTime(), 650, 300);
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_userBehaviorLog();
        }
        function search_userBehaviorLog(){
        	var queryParams = {
				"userBehaviorLog.behavior.id" : $("#behaviorId").val(),
				"queryLoggedDateStart" :$("#queryLoggedDateStart").val(),
				"queryLoggedDateEnd" :$("#queryLoggedDateEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$("#pagination").datagrid('load'); 
        }
        $(function(){
			$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'用户行为日志列表',
				url:'userBehaviorLogQuery.action',
				queryParams:{
					"userBehaviorLog.behavior.id" : $("#behaviorId").val(),
					"queryLoggedDateStart" :$("#queryLoggedDateStart").val(),
					"queryLoggedDateEnd" :$("#queryLoggedDateEnd").val()
					
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
					{field:'loggedDate',title:'记录时间',width:0.4,formatter:function(value,rec){
						if(rec.loggedDate==null) return;
						var date = new Date(rec.loggedDate.time);
						var retStr = date.format("yyyy-MM-dd hh:mm:ss");
						return retStr;
					}},
					{field:'user.displayName',title:'操作者',width:0.15,formatter:function(value,rec){
						if(rec.user)
							return rec.user.displayName;
						else
							return "未登录用户";
					}},
					{field:'logContent',title:'日志内容',width:0.22},
					{field:'remoteIp',title:'访问IP',width:0.1},
					{field:'op',title:'操作',width:0.1,formatter:function(value,rec){
						return "<a href='#' onclick=\"view('"+rec.id+"')\" class='btn_01_mini'>查看<b></b></a>";
					}}
				]]
			}));
		});
</script>
	</head>
	<body>
		<div class="page_content">
			<div class="layout_01_left">
				<div class="layout_overflow">
					<ul id="roleTree">
					</ul>
				</div>
			</div>
			<div class="layout_01_right">
				<div class="inner12px submitdata">
					<form name="myform" method="post">
						<input name="userBehaviorLog.behavior.id" id="behaviorId"
							type="hidden" value="${userBehaviorLog.behavior.id}">
						<div class="cell  boxBmargin12">
							<table>
								<tr>
									<th>
										日志记录日期
									</th>
									<td>
										<input type="text" name="queryLoggedDateStart"
											id="queryLoggedDateStart" class="Wdate"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryLoggedDateEnd\')}'})"
											value="<fmt:formatDate type="date" value="${queryLoggedDateStart}" pattern="yyyy.MM.dd HH:mm:ss"/>">
										~
										<input type="text" name="queryLoggedDateEnd"
											id="queryLoggedDateEnd" class="Wdate"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryLoggedDateStart\')}'})"
											value="<fmt:formatDate type="date" value="${queryLoggedDateEnd}" pattern="yyyy.MM.dd HH:mm:ss"/>">
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="btn_area_setc">
											<a href="###" class="btn_01"
												onclick="search_userBehaviorLog()">查询<b></b> </a>
											<a href="###" class="btn_01"
												onclick="clear_form(document.myform);">清空<b></b> </a>
										</div>
									</td>
								</tr>
							</table>
						</div>
						<div id="pagination">
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>