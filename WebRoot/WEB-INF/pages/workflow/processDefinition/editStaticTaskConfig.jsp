<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>

<html>
<head>
    <title>工作流管理</title>
   <link rel="stylesheet" type="text/css"
         href='<c:url value="/webResources/themes/${curr_user.cssId}/css/style.css" />' />

   
   <script type="text/javascript"	src='<c:url value="/webResources/js/easyui/jquery-1.4.4.min.js"/>'></script>
	<script type="text/javascript" src="${ctx}/javascript/util/myCalendar.js"></script>
	<script type="text/javascript"
		src="${ctx}/javascript/validate/validate.js"></script>
	<script type="text/javascript"
		src="${ctx}/javascript/util/pico-button.js"></script>
		<script type="text/javascript">
	//插入人员
	function insertOption_task() {
		var sels = $("#sel3 option:selected").clone();
		var stid = document.getElementById("staticTaskNodeID").value;
		var pdid = document.getElementById("procDefId").value;
		var tid = document.getElementById("taskId").value;
				
		$.each(sels,function(i, n) {
			var userID = $(n).val();
			$.post("saveTaskConf.action", 
					{userId:userID, staticTaskNodeID:stid, procDefId:pdid, taskId:tid},
					function(data, textStatus) {
						if (data != "-1") {
							$(n).val(data);
							$("#sel4").append(n);
						}
					});
		});
	}
	
	function delOption_task() {
	   var sels =$("#sel4 option:selected").clone();
	   
		$.each(sels,function (i,n) {
			var tnpi = $(n).val();
			$.post("deleteNodeConf.action", 
					{taskNodeParticipantId:tnpi},
					function(data, textStatus) {
										
					});
		});
		$("#sel4 option:selected").remove();
	}
	
</script>
</head>


<body topmargin="0" leftmargin="0">
	<input type="hidden" name="staticTaskNodeID" id="staticTaskNodeID" value="${staticTaskNodeID}">
	<input type="hidden" name="taskId" id="taskId" value="${taskId}">
	<input type="hidden" name="procDefId" id="procDefId" value="${procDefId}">
	
	<div class="submitdata" id="listTable">
	<table align="center" border="1" width="99%">
		<tr>
			<td>
				任务人员选择：
			</td>
			<td>
				<select id="sel3" style="height:240px;width:100px" multiple="multiple">
					<c:forEach items="${userList}" var="user">
						<option value="${user.id}">
							${user.displayName}
						</option>
					</c:forEach>
				</select>
			</td>
			<td width="4%" >
				<input type="button" class="input_button"  onclick="insertOption_task()"
					value="&gt;&gt;" />
				<br>
				<input type="button" class="input_button"  onclick="delOption_task()"
					value="&lt;&lt;" />
				<br>
			</td>
			<td>
				<select id="sel4" style="height:240px;width:100px" multiple="multiple">
					<c:forEach items="${taskNodeParticipants}" var="taskNodeParticipant">
						<option value="${taskNodeParticipant.id}">
							${taskNodeParticipant.userName}
						</option>
					</c:forEach>
				</select>
			</td>	
		</tr>
	</table>
	</div>
</body>
</html>