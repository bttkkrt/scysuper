<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Activiti新增模型</title>
		<%@include file="/common/jsLib.jsp"%>
		<script type="text/javascript">
			function add() {
				if(""==$("#modelName").val()){
					$.messager.alert('提示',"请输入新建模型名称！");
					return false;
				}
				if(""==$("#modelKey").val()){
					$.messager.alert('提示',"请输入新建模型KEY！");
					return false;
				}		
				
				var form = document.getElementById("form");
				form.action="<c:url value="/activiti/addModel.action"/>";
				form.target="_blank";
				form.submit();
				
				top.$("#add_model").window("close", true);
			}
		</script>
	</head>

	<body>
		<div class="page_dialog">
			<form action="" method="post" id="form">
				<div class="inner6px">
					<div class="cell">
						<table>
							<tr>
								<th>
									名称
								</th>
								<td>
									<input type="text" name="modelName" id="modelName" class="input_text"/><font style="color:red">*</font>
								</td>
							</tr>
							<tr>
								<th>
									KEY
								</th>
								<td>
									<input type="text" name="modelKey" id="modelKey" class="input_text"/><font style="color:red">*</font>
								</td>
							</tr>
							<tr>
								<th>
									描述
								</th>
								<td>
									<textarea style="width:90%;height:150px;" id="modelDesc" name="modelDesc"></textarea>
								</td>
							</tr>				
							<tr>
								<td colspan="4">
									<div class="btn_area_setc">
										<a href="###" class="btn_01" onclick="add()">创建<b></b></a>
										<a href="###" class="btn_01" onclick="parent.close_win('add_model')">关闭<b></b></a>
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