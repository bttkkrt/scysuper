<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>导入职业健康体检</title>

		<%@include file="/common/jsLib.jsp"%>
		<script>
	    function import_user(){
		  var userfrm = document.userFrm;
		   if(document.getElementById("userFile").value==""){
		   		alert('请选择导入的Excel文件！');
		        return false;
		   }
		   else
		   {
		   		if(document.getElementById("tjjg").value==""){
		   			alert('请填写体检机构名称！');
		        	return false;
		  	 	}
		  	 	else
		  	 	{	
		  	 		userfrm.action = "importOccHeaExa.action";
			   		userfrm.submit();
		  	 	}
		   }
		   
		   import_user = funcTwo;
	    	return false;  
	    }
	    
	    function funcTwo(){  
    		return false;  
		}  
	    
		</script>
	</head>
	<body>
		<div class="submitdata" style="width:100%; height:100%;overflow:hidden">
			<form id="userFrm" name="userFrm" method="post"
				enctype="multipart/form-data">
				<table width="350px" height="150px">
				    <tr>
						<th width="150px">
							模板文件下载：
						</th>
						<td width="200px">
							<a href="${ctx }/occHeaExa.xls" style="text-decoration:underline;color:red">模板文件</a>
						</td>
					</tr>
					<tr>
						<th>体检机构</th>
						<td><input id="tjjg" name="occHeaExa.medicalInstitutionName" value="${occHeaExa.medicalInstitutionName}" type="text" maxlength="127"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th>
							选择的Excel文件：
						</th>
						<td>
							<input type="file" class="upload" data-options="iconCls:'icon-add'" style="height: 18px;width:90%" name="userFile"   
								id="userFile">
						</td>
					</tr>
					<tr>
						<td style="text-align: center;" valign="middle" colspan="2">
							<a href="###" class="easyui-linkbutton" onclick="import_user()"
								iconCls="icon-add">导入</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>