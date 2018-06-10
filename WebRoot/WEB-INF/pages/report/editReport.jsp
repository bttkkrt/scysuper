<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>报表信息修改</title>
		<%@include file="/common/jsLib.jsp"%>
		<script type="text/javascript">
			function saveReport(reprotId){
				var reportName = document.getElementById("reportName").value;
				if("" == reportName){
					alert("报表名不能为空");
					return;
				}else{
					var form = document.getElementById("editForm");
					form.action="<c:url value='/report/saveReport.action'/>?reportId="+reprotId;
					form.submit();
				}
			}
		</script>
	</head>

	<body>
		<c:set var="curr_path" value="报表信息修改"></c:set>
		<form action="" method="post" id="editForm" >
			<div class="submitdata">
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr>
					<th>报表名称</th>
					<td>
						<input type="text" name="report.reportName" id="reportName" value="${report.reportName}" 
							class="input_text"/>
					</td>
				</tr>
				<tr>
					<td colspan='2' class="set_c">
						<input type='button' value='保存'  class="input_button"
							onclick="saveReport('${report.id}')" />
						<input type='button' value='关闭' onclick="window.close('_self')"
							 class="input_button"/>
					</td>
				</tr>
			</table>
			</div>
		</form>
	</body>
</html>