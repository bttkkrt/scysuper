<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>导入安全检查项配置</title>

		<%@include file="/common/jsLib.jsp"%>
		<script>
			
	    function importSave(){
	        	
	        if(document.getElementById("importFile").value==""){
	        	alert("请选择导入的Excel文件！");
	        	return false;
	        }
	        document.myform1.action = "${ctx}/jsp/inspectItem/inspectItemImportSave.action";
	        document.myform1.submit();
	    }
		</script>
	</head>
	<body>
		<%@include file="/WEB-INF/template/content_title.jsp"%>
		<div class="submitdata">
			<form id="myform1" name="myform1" method="post"
				enctype="multipart/form-data">
				<table>
					<tr></tr>
					<tr>
						<th>
							选择的Excel文件：
						</th>
						<td>
							<input type="file" style="height: 18px" name="importFile"
								id="importFile">
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: left;color:red" valign="middle">
							<a href="${ctx}/importInspectItem.xls" style="color:red">点击下载模板</a>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;" valign="middle" colspan="2">
							<a href="###" class="easyui-linkbutton" onclick="importSave();"
								iconCls="icon-add">导入安全检查项配置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<%@include file="/WEB-INF/template/pagefoot.jsp"%>
	</body>
</html>