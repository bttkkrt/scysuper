<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>导入用户</title>

		<%@include file="/common/jsLib.jsp"%>
		<script>
			
	    function import_company(){
	      	var companyFrm = document.companyFrm;
	        	
	        if(document.getElementById("companyFile").value==""){
	        	alert("请选择导入的Excel文件！");
	        	return false;
	        }
	        companyFrm.action = "importCompany.action";
	        companyFrm.submit();
	    }
		</script>
	</head>
	<body>
		<%@include file="/WEB-INF/template/content_title.jsp"%>
		<div class="submitdata">
			<form id="companyFrm" name="companyFrm" method="post"
				enctype="multipart/form-data">
				<table>
					<tr></tr>
					<tr>
						<th>
							选择的Excel文件：
						</th>
						<td>
							<input type="file" style="height: 18px" name="companyFile" id="companyFile">
						</td>
					</tr>
					<tr>
						<td style="text-align: center;" valign="middle" colspan="2">
							<a href="###" class="easyui-linkbutton" onclick="import_company()"
								iconCls="icon-add">导入企业</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<%@include file="/WEB-INF/template/pagefoot.jsp"%>
	</body>
</html>