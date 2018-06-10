<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>导入操作规程</title>

		<%@include file="/common/jsLib.jsp"%>
		<script>
	    function import_user(){
		   var userfrm = document.userFrm;
		   if(document.getElementById("userFile").value==""){
		   		alert('请选择导入的Excel文件！');
		        return false;
		   }
		  	else if($("#operationWorkshopId").val()!=null&&""!=$("#operationWorkshopId").val()){
			   userfrm.action = "importOpePro.action?ids="+$("#operationWorkshopId").val();
			   userfrm.submit();
			}else{
				alert('请选择车间名称！');
		        return false;
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
							<a href="${ctx}/opePro.xls" style="text-decoration:underline;color:red">模板文件</a>
						</td>
					</tr>
					<tr>
						<th width="15%">车间名称</th>
						<td width="35%">
						<cus:SelectOneTag property="opePro.operationWorkshopId" defaultText='请选择' codeSql="select t.row_id,t.WORKSHOP_NAME from WORKSHOP t where t.delflag = 0 and t.COMPANY_ID='${opePro.companyId}'" value="${opePro.operationWorkshopId}"  datatype="*1-127" errormsg='车间名称必须是1到127位字符！' nullmsg='车间名称不能为空！' sucmsg='车间名称填写正确！'  maxlength="127" style="width:60%"/><font style='color:red'>*</font>
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
							<a href="###" class="easyui-linkbutton" onclick="return import_user()"
								iconCls="icon-add">导入</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>