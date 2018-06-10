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
	<form name="myform1" method="post" enctype="multipart/form-data" action="maiMatSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="maiMat.id" value="${maiMat.id}">
		<input type="hidden" name="maiMat.createTime" value="<fmt:formatDate type="both" value="${maiMat.createTime}" />">
		<input type="hidden" name="maiMat.updateTime" value="${maiMat.updateTime}">
		<input type="hidden" name="maiMat.createUserID" value="${maiMat.createUserID}">
		<input type="hidden" name="maiMat.updateUserID" value="${maiMat.updateUserID}">
		<input type="hidden" name="maiMat.deptId" value="${maiMat.deptId}">
		<input type="hidden" name="maiMat.delFlag" value="${maiMat.delFlag}">
		
			<table width="100%" border="0">
				
				<tr>
					<th width="15%">物料</th>
					<td width="35%"><input name="maiMat.material" value="${maiMat.material}" style="width:60%;" type="text" datatype="*1-127" datatype="*1-127" errormsg='物料必须是1到127位字符！' nullmsg='物料不能为空！' sucmsg='物料填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">危险性分析</th>
					<td width="35%"><input name="maiMat.riskAnalysis" value="${maiMat.riskAnalysis}" style="width:60%;" type="text"  maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">存放方式</th>
					<td width="35%"><input name="maiMat.storageMode" value="${maiMat.storageMode}" style="width:60%;" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
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
