<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>办理任务</title>
	<%@include file="/common/jsLib.jsp"%>
	
	<script>
		function save(){
			if(Validator.Validate(document.completeTaskForm,3)){
				
				document.completeTaskForm.action="completeTaskFormKey.action";
				document.completeTaskForm.submit();
			}
		}
	</script>
	
</head>
<body>
	<div class="page_dialog">
		<form name="completeTaskForm" method="post">
			<input type="hidden" name="processDefinitionId" value="${processDefinitionId}">
			<input type="hidden" name="taskId" value="${taskId}">
			<div class="inner6px">
				<div class="cell">	
					${formContent}
				</div>
			</div>
		</form>
	</div>
</body>
</html>