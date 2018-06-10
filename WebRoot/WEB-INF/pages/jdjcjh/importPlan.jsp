<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>导入用户</title>

		<%@include file="/common/jsLib.jsp"%>
		<script>
	    function import_user(){
	    	if($("#startDate").val()==''){
	    		alert('请先选计划开始时间！');
	    		return false;
	    	}else if($("#endDate").val()==''){
	    		alert('请先选计划结束时间！');
	    		return false;
	    	}else if($("#planName").val()==''){
	    		alert('请输入计划名称！');
	    		return false;
	    	}else{
		      	var userfrm = document.userFrm;
		        if(document.getElementById("userFile").value==""){
		        	alert('请选择导入的Excel文件！');
		        	return false;
		        }
		        else
		        {
		        	userfrm.action = "importPlan.action";
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
				<input type="hidden" name="deptId" id="deptId" value="${deptId}">
				<table width="350px" height="100px">
				    <tr>
						<th width="150px">
							模板文件下载：
						</th>
						<td width="200px">
							<a href="${ctx}/importYearPlan.xls" style="text-decoration:underline;color:red">模板文件</a>
						</td>
					</tr>
					<tr>
						<th>
							计划开始时间：
						</th>
						<td>
							<input id="startDate" name="supPla.planStartTime" value="<fmt:formatDate type='both' value='${supPla.planStartTime}' pattern="yyyy-MM-dd"/>" type="text"  datatype="*"  nullmsg='计划开始时间不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'})"><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th>
							计划结束时间：
						</th>
						<td>
							<input id="endDate" name="supPla.planEndTime" value="<fmt:formatDate type='both' value='${supPla.planEndTime}' pattern="yyyy-MM-dd"/>" type="text" datatype="*"  nullmsg='计划结束时间不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})"><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th>
							计划名称：
						</th>
						<td>
							<input name="supPla.planName" id="planName"  type="text" datatype="*1-15" errormsg='计划名称必须是1到15位字符！' nullmsg='计划名称不能为空！' sucmsg='计划名称填写正确！'  maxlength="15"><font style='color:red'>*</font>
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
								iconCls="icon-add">导入年计划</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>