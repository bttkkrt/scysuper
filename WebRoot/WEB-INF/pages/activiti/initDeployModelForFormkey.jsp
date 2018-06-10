<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Activiti Model部署</title>
		<%@include file="/common/jsLib.jsp"%>
		<script type="text/javascript">
			function doDeploy() {
				var formkeyFileArray = document.getElementsByName("formkeyFile");
				for(var i=0;i<formkeyFileArray.length;i++){
					if("" == formkeyFileArray[i].value){
						alert("请选择所有的.form文件！");
						return;
					}					
				}
				
				var form = document.getElementById("form");
				form.action="<c:url value="/activiti/deployModelForFormkey.action"/>";
				form.submit();			
			}
		</script>
	</head>

	<body>
		<div class="page_dialog">
			<form action="" method="post" id="form" enctype="multipart/form-data">
				<s:token />
				<input type="hidden" name="deployFlag" value="${deployFlag}" />
				<input type="hidden" name="modelId" value="${modelId}" />
				<div class="inner6px">
					<div class="cell">	
						<table>
							<c:forEach var="formkey" items="${formkeyArray}">
								<tr>
									<th width="40%">
										${formkey}
									</th>
									<td width="60%" style="text-align:left;">
										<input type="file" name="formkeyFile" id="fileOfDef" class="input_text"/>
									</td>
								</tr>					
							</c:forEach>
							<tr>
								<td colspan="4">
									<div class="btn_area_setc">
										<a href="###" class="btn_01" onclick="doDeploy()">部署<b></b></a>
										<a href="###" class="btn_01" onclick="parent.close_win('initDeployModelForFormkey_window')">关闭<b></b></a>
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