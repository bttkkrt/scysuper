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
	<form name="myform1" method="post" enctype="multipart/form-data" action="emeAgeSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="emeAge.id" value="${emeAge.id}">
		<input type="hidden" name="emeAge.createTime" value="<fmt:formatDate type="both" value="${emeAge.createTime}" />">
		<input type="hidden" name="emeAge.updateTime" value="${emeAge.updateTime}">
		<input type="hidden" name="emeAge.createUserID" value="${emeAge.createUserID}">
		<input type="hidden" name="emeAge.updateUserID" value="${emeAge.updateUserID}">
		<input type="hidden" name="emeAge.deptId" value="${emeAge.deptId}">
		<input type="hidden" name="emeAge.delFlag" value="${emeAge.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">机构名称</th>
					<td width="35%"><input name="emeAge.agencyName" style="width:60%" value="${emeAge.agencyName}" type="text" datatype="*1-127" errormsg='机构名称必须是1到127位字符！' nullmsg='机构名称不能为空！' sucmsg='机构名称填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">机构职责</th>
					<td width="35%"><input name="emeAge.agencyResponsible" style="width:60%" value="${emeAge.agencyResponsible}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">成员数量</th>
					<td width="35%"><input name="emeAge.memberNumber" style="width:60%" value="${emeAge.memberNumber}" type="text" maxlength="127"></td>
					<th width="15%">负责人</th>
					<td width="35%"><input name="emeAge.personInCharge" style="width:60%" value="${emeAge.personInCharge}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">负责人联系方式</th>
					<td width="35%"><input name="emeAge.inChargePhone" style="width:60%" value="${emeAge.inChargePhone}" type="text" maxlength="127"></td>
					<th width="15%">负责人邮箱</th>
					<td width="35%"><input name="emeAge.inChargeEmail" style="width:60%" value="${emeAge.inChargeEmail}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="emeAge.remark" style="width:96%;height:60px">${emeAge.remark}</textarea></td>
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
