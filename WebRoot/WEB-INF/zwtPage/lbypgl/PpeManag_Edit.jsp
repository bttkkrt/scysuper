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
	<form name="myform1" method="post" enctype="multipart/form-data" action="ppeManagSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="ppeManag.id" value="${ppeManag.id}">
		<input type="hidden" name="ppeManag.createTime" value="<fmt:formatDate type="both" value="${ppeManag.createTime}" />">
		<input type="hidden" name="ppeManag.updateTime" value="${ppeManag.updateTime}">
		<input type="hidden" name="ppeManag.createUserID" value="${ppeManag.createUserID}">
		<input type="hidden" name="ppeManag.updateUserID" value="${ppeManag.updateUserID}">
		<input type="hidden" name="ppeManag.deptId" value="${ppeManag.deptId}">
		<input type="hidden" name="ppeManag.delFlag" value="${ppeManag.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					
					<th width="15%">用品编号</th>
					<td width="35%"><input name="ppeManag.ppeNo" style="width:60%" value="${ppeManag.ppeNo}" type="text"  maxlength="127"> </td>
					<th width="15%">用品名称</th>
					<td width="35%"><input name="ppeManag.ppeName" style="width:60%" value="${ppeManag.ppeName}" type="text" errormsg='用品名称必须是1到30位字符！' nullmsg='用品名称不能为空！' sucmsg='用品名称填写正确！'    datatype="*1-127"  maxlength="30"><font style='color:red'>*</font> </td>
				</tr>
				<tr>
					<th width="15%">用品数量</th>
					<td width="35%"><input name="ppeManag.ppeNum" style="width:60%" value="${ppeManag.ppeNum}"  type="text" maxlength="127"></td>
					<th width="15%">用品单位</th>
					<td width="35%"><input name="ppeManag.ppeCompany" style="width:60%" value="${ppeManag.ppeCompany}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">用品分类</th>
					<td width="35%"><cus:SelectOneTag style="width:60%"  property="ppeManag.ppeType" defaultText='请选择' codeName="劳保用品分类" value="${ppeManag.ppeType}" /></td>
					
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea name="ppeManag.ppeRemark" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:96%;height:60px">${ppeManag.ppeRemark}</textarea></td>
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
