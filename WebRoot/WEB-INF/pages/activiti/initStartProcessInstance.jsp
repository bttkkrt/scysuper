<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>启动流程</title>
	<%@include file="/common/jsLib.jsp"%>
	
	<script>
		function save(){
			if(Validator.Validate(document.startProcessInstanceForm,3)){
				
				document.startProcessInstanceForm.action="startProcessInstanceFormKey.action";
				document.startProcessInstanceForm.submit();
			}
		}
	</script>
	
</head>
<body>
	<div class="page_dialog">
		<form name="startProcessInstanceForm" method="post">
			<input type="hidden" name="processDefinitionId" value="${processDefinitionId}">
			<div class="inner6px">
				<div class="cell">			
					${formContent}
				</div>
			</div>
		</form>
	</div>
</body>
</html>