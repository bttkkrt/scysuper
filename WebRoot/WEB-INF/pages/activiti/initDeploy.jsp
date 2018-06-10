<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Activiti流程部署</title>
		<%@include file="/common/jsLib.jsp"%>
		<script type="text/javascript">
			$(document).ready(function() {
				if(null!="${sessionScope.FileUploadErrorMsg}" && ""!="${sessionScope.FileUploadErrorMsg}"){
					$.messager.alert('提示',"${sessionScope.FileUploadErrorMsg}");
				}
			});
		
			function doDeploy() {
				var fileOfDef = document.getElementById("fileOfDef").value;
				
				if("" == fileOfDef){
					$.messager.alert('提示',"请选择流程定义文件！");
					return;
				}
				
				if(fileOfDef.lastIndexOf(".")!= -1){
					var fileType = (fileOfDef.substring(fileOfDef.lastIndexOf(".")+1, fileOfDef.length)).toLowerCase();
					alert("fileType="+fileType);
					//TODO:
					
					
					
					
					
					var form = document.getElementById("form");
					form.action="<c:url value="/activiti/deploy.action"/>";
					form.submit();			
				}
			}
		</script>
	</head>

	<body>
		<div class="page_dialog">
			<form action="" method="post" id="form" enctype="multipart/form-data">
				<s:token />
				<div class="inner6px">
					<div class="cell">
						<table>
							<tr>
								<th width="50%" style="text-align:left;">
									<font color="red">&nbsp; 支持文件格式：<br/>&nbsp; .zip<br/>&nbsp; .bar<br/>&nbsp; .bpmn<br/>&nbsp; .bpmn20.xml</font>
								</th>
								<td width="50%" style="text-align:left;">
									<input type="file" name="file" id="fileOfDef" class="input_text"/>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="btn_area_setc">
										<a href="###" class="btn_01" onclick="doDeploy()">发布流程定义文件<b></b></a>
										<a href="###" class="btn_01" onclick="parent.close_win('deploy_process')">关闭<b></b></a>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>