<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function queryQy()
		{
			var szzid = document.getElementById('areaId').value;
			popupCenter("${ctx}/jsp/qyjbxx/queryCompanyList.action?flag=idmcqy&entBaseInfo.enterprisePossession="+szzid, "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
		}
		  function clearCompany(){
        	document.getElementById("companyName").value="";
        	document.getElementById("companyId").value="";
        }
   
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="detOverStaManSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="detOverStaMan.id" value="${detOverStaMan.id}">
		<input type="hidden" name="detOverStaMan.createTime" value="<fmt:formatDate type="both" value="${detOverStaMan.createTime}" />">
		<input type="hidden" name="detOverStaMan.updateTime" value="${detOverStaMan.updateTime}">
		<input type="hidden" name="detOverStaMan.createUserID" value="${detOverStaMan.createUserID}">
		<input type="hidden" name="detOverStaMan.updateUserID" value="${detOverStaMan.updateUserID}">
		<input type="hidden" name="detOverStaMan.deptId" value="${detOverStaMan.deptId}">
		<input type="hidden" name="detOverStaMan.delFlag" value="${detOverStaMan.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">样品编码</th>
					<td width="35%"><input name="detOverStaMan.sampleEncoding" value="${detOverStaMan.sampleEncoding}" type="text" datatype="*1-127" errormsg='样品编码必须是1到127位字符！' nullmsg='样品编码不能为空！' sucmsg='样品编码填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">检测类别</th>
					<td width="35%"><cus:SelectOneTag property="detOverStaMan.detectionCategory" defaultText='请选择' codeName="检测类别" value="${detOverStaMan.detectionCategory}" datatype="*"  nullmsg='类别不能为空！' style="width:60%"/><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">检测岗位</th>
					<td width="35%"><input name="detOverStaMan.testPosition" value="${detOverStaMan.testPosition}" type="text" maxlength="127" style="width:60%"></td>
					<th width="15%">检测项目</th>
					<td width="35%"><input name="detOverStaMan.testItems" value="${detOverStaMan.testItems}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">检测结果</th>
					<td width="35%"><input name="detOverStaMan.testResult" value="${detOverStaMan.testResult}" type="text" maxlength="127" style="width:60%"></td>
					<th width="15%">标准值</th>
					<td width="35%"><input name="detOverStaMan.standardValue" value="${detOverStaMan.standardValue}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">检测机构</th>
					<td width="35%"><input name="detOverStaMan.detectionMechanism" value="${detOverStaMan.detectionMechanism}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="detOverStaMan.remark" style="width:96%;height:60px">${detOverStaMan.remark}</textarea></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_detOverStaMan');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
