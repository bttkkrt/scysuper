<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="ppeWareManagSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="ppeWareManag.id" value="${ppeWareManag.id}">
		<input type="hidden" name="ppeWareManag.createTime" value="<fmt:formatDate type="both" value="${ppeWareManag.createTime}" />">
		<input type="hidden" name="ppeWareManag.updateTime" value="${ppeWareManag.updateTime}">
		<input type="hidden" name="ppeWareManag.createUserID" value="${ppeWareManag.createUserID}">
		<input type="hidden" name="ppeWareManag.updateUserID" value="${ppeWareManag.updateUserID}">
		<input type="hidden" name="ppeWareManag.deptId" value="${ppeWareManag.deptId}">
		<input type="hidden" name="ppeWareManag.delFlag" value="${ppeWareManag.delFlag}">
		<input type="hidden" name="ppeWareManag.areaId" value="${ppeWareManag.areaId}">
		<input type="hidden" name="ppeWareManag.areaName" value="${ppeWareManag.areaName}">
		<input type="hidden" name="ppeWareManag.companyId" value="${ppeWareManag.companyId}">
		<input type="hidden" name="ppeWareManag.companyName" value="${ppeWareManag.companyName}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">用品名称</th>
					<td width="35%">
						<cus:SelectOneTag  datatype="*" style="width:60%"  property="ppeWareManag.ppeId" defaultText='请选择' codeSql="select t.ROW_ID,t.PPE_NAME from PPE_MANAG t where t.delflag = 0 and t.COMPANY_ID='${ppeWareManag.companyId}'" value="${ppeWareManag.ppeId}" /><font style='color:red'>*</font>
					</td>
					<th width="15%">类型</th>
					<td width="35%"><cus:SelectOneTag style="width:60%"  property="ppeWareManag.ppeWareType" defaultText='请选择' codeName="进库出库" value="${ppeWareManag.ppeWareType}" /></td>
					
				</tr>
				<tr>
					<th width="15%">数量</th>
					<td width="35%"><input name="ppeWareManag.ppeWareNum"  style="width:60%" value="${ppeWareManag.ppeWareNum}" type="text" maxlength="127"></td>
					<th width="15%">盘点人</th>
					<td width="35%"><input name="ppeWareManag.ppeWarePeople" style="width:60%" value="${ppeWareManag.ppeWarePeople}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					
					<th width="15%">盘点时间</th>
					<td width="35%"><input name="ppeWareManag.ppeWareTime" style="width:60%"  value="<fmt:formatDate type='both' value='${ppeWareManag.ppeWareTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="ppeWareManag.ppeWareRemark" style="width:96%;height:60px">${ppeWareManag.ppeWareRemark}</textarea></td>
					
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_ppeWareManag');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
