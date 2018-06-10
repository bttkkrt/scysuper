<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
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
	<form name="myform1" method="post" enctype="multipart/form-data" action="licCanInfSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="licCanInf.id" value="${licCanInf.id}">
		<input type="hidden" name="licCanInf.createTime" value="<fmt:formatDate type="both" value="${licCanInf.createTime}" />">
		<input type="hidden" name="licCanInf.updateTime" value="${licCanInf.updateTime}">
		<input type="hidden" name="licCanInf.createUserID" value="${licCanInf.createUserID}">
		<input type="hidden" name="licCanInf.updateUserID" value="${licCanInf.updateUserID}">
		<input type="hidden" name="licCanInf.deptId" value="${licCanInf.deptId}">
		<input type="hidden" name="licCanInf.delFlag" value="${licCanInf.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag property="licCanInf.areaId" style="width: 60%" defaultText='请选择' codeName="企业属地" value="${licCanInf.areaId}"  onchange="clearCompany()" dataType="*1-127" nullmsg='所在区域不能为空！' sucmsg='所在区域填写正确！'/><font style='color:red'>*</font></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input id="companyName" name="licCanInf.companyName" style="width: 60%" value="${licCanInf.companyName}" type="text" readonly="readonly" onclick="queryQy()" dataType="*1-127"  nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'/><font style='color:red'>*</font>
						<input type="hidden" id="companyId" name="licCanInf.companyId" value="${licCanInf.companyId}"/>
					</td> 
				</tr>
				<tr>
					<th width="15%">许可证名称</th>
					<td width="35%"><input name="licCanInf.licenseName" style="width: 60%" value="${licCanInf.licenseName}" type="text" datatype="*1-100" errormsg='许可证必须是1到100位字符！' nullmsg='许可证名称不能为空！' sucmsg='许可证名称填写正确！'  maxlength="100"><font style='color:red'>*</font></td>
					<th width="15%">许可证编号</th>
					<td width="35%"><input name="licCanInf.licenseNumber" style="width: 60%" value="${licCanInf.licenseNumber}" type="text" maxlength="100"></td>
				</tr>
				<tr>
					<th width="15%">注销文号</th>
					<td width="35%"><input name="licCanInf.cancellationNumber" style="width: 60%" value="${licCanInf.cancellationNumber}" type="text" maxlength="100"></td>
					<th width="15%">注销原因</th>
					<td width="35%"><input name="licCanInf.cancelReason" style="width: 60%" value="${licCanInf.cancelReason}" type="text" maxlength="100"></td>
				</tr>
				<tr>
					<th width="15%">批准机关名称</th>
					<td width="35%"><input name="licCanInf.approvalAuthority" style="width: 60%" value="${licCanInf.approvalAuthority}" type="text" maxlength="100"></td>
					<th width="15%">批准日期</th>
					<td width="35%"><input name="licCanInf.approvalDate" style="width: 60%" value="<fmt:formatDate type='date' value='${licCanInf.approvalDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="licCanInf.remark" style="width:96%;height:60px">${licCanInf.remark}</textarea></td>
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
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
