<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>导入用户</title>

		<%@include file="/common/jsLib.jsp"%>
		<script>
	    function import_user(){
	    	if("undefined"=="${deptId}"){
	    		alert("请先选择部门！");
	    	}else{
		      	var userfrm = document.userFrm;
		        if(document.getElementById("userFile").value==""){
		        	$.messager.alert('错误','请选择导入的Excel文件！');
		        	return false;
		        }
		        userfrm.action = "importUser.action";
		        userfrm.submit();
	    	
	    	}
	    }
		</script>
	</head>
	<body>
		<div class="submitdata" style="width:100%; height:100%;overflow:hidden">
			<form id="userFrm" name="userFrm" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="deptId" id="deptId" value="${deptId}">
				<table width="350px" height="100px">
				    <tr>
						<th width="150px">
							模板文件下载：
						</th>
						<td width="200px">
							<a href="${ctx }/user_temp.xls">模板文件</a>
						</td>
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
								iconCls="icon-add">导入用户</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>