<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="proManOrgSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="proManOrg.id" value="${proManOrg.id}">
		<input type="hidden" name="proManOrg.createTime" value="<fmt:formatDate type="both" value="${proManOrg.createTime}" />">
		<input type="hidden" name="proManOrg.updateTime" value="${proManOrg.updateTime}">
		<input type="hidden" name="proManOrg.createUserID" value="${proManOrg.createUserID}">
		<input type="hidden" name="proManOrg.updateUserID" value="${proManOrg.updateUserID}">
		<input type="hidden" name="proManOrg.deptId" value="${proManOrg.deptId}">
		<input type="hidden" name="proManOrg.delFlag" value="${proManOrg.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">机构名称</th>
					<td width="35%"><input name="proManOrg.orgenizationName" value="${proManOrg.orgenizationName}" type="text" datatype="*1-127" errormsg='机构名称必须是1到127位字符！' nullmsg='机构名称不能为空！' sucmsg='机构名称填写正确！'   maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">成员数量</th>
					<td width="35%"><input name="proManOrg.orgenizationMenberCount"   value="${proManOrg.orgenizationMenberCount}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">负责人</th>
					<td width="35%"><input name="proManOrg.orgenizationCharge" value="${proManOrg.orgenizationCharge}" type="text" datatype="*1-127" errormsg='负责人必须是1到127位字符！' nullmsg='负责人不能为空！' sucmsg='负责人填写正确！'     maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">负责人邮箱</th>
					<td width="35%"><input name="proManOrg.orgenizationChargeEmail"   value="${proManOrg.orgenizationChargeEmail}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">负责人联系方式1</th>
					<td width="35%"><input name="proManOrg.orgenizationChargePhone" value="${proManOrg.orgenizationChargePhone}" type="text" datatype="m"   nullmsg='负责人联系方式1不能为空！' sucmsg='负责人联系方式1填写正确！' maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">负责人联系方式2</th>
					<td width="35%"><input name="proManOrg.orgenizationChargePhone2" value="${proManOrg.orgenizationChargePhone2}" type="text"  datatype="m" ignore="ignore" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">机构职责</th>
					<td width="85%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="proManOrg.orgenizationResponsibility" style="width:96%;height:60px">${proManOrg.orgenizationResponsibility}</textarea></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td  width="85%" colspan="3"><textarea name="proManOrg.orgenizationRemark" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:96%;height:60px">${proManOrg.orgenizationRemark}</textarea></td>
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
