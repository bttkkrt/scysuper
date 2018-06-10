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
	<form name="myform1" method="post" enctype="multipart/form-data" action="aidSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="aid.id" value="${aid.id}">
		<input type="hidden" name="aid.createTime" value="<fmt:formatDate type="both" value="${aid.createTime}" />">
		<input type="hidden" name="aid.updateTime" value="${aid.updateTime}">
		<input type="hidden" name="aid.createUserID" value="${aid.createUserID}">
		<input type="hidden" name="aid.updateUserID" value="${aid.updateUserID}">
		<input type="hidden" name="aid.deptId" value="${aid.deptId}">
		<input type="hidden" name="aid.delFlag" value="${aid.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">物资名称</th>
					<td width="35%"><input name="aid.suppliedName" value="${aid.suppliedName}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">保管人联系方式</th>
					<td width="35%"><input name="aid.custodianMoblie" value="${aid.custodianMoblie}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">物资级别</th>
					<td width="35%"><cus:SelectOneTag property="aid.suppliedLevel" defaultText='请选择' codeName="应急物资级别" value="${aid.suppliedLevel}"  style="width:60%"/></td>
					<th width="15%">物资数量</th>
					<td width="35%"><input name="aid.suppliedCount" value="${aid.suppliedCount}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">物资型号</th>
					<td width="35%"><input name="aid.suppliedModel" value="${aid.suppliedModel}" type="text" maxlength="127" style="width:60%"></td>
					<th width="15%">物资规格</th>
					<td width="35%"><input name="aid.suppliedSpecificate" value="${aid.suppliedSpecificate}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">购入日期</th>
					<td width="35%"><input name="aid.purchaseDate" value="<fmt:formatDate type='both' value='${aid.purchaseDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:60%"></td>
					<th width="15%">生产厂家</th>
					<td width="35%"><input name="aid.manufacture" value="${aid.manufacture}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">出厂日期</th>
					<td width="35%"><input name="aid.manufactureDate" value="<fmt:formatDate type='both' value='${aid.manufactureDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:60%"></td>
					<th width="15%">有效期至</th>
					<td width="35%"><input name="aid.validity" value="<fmt:formatDate type='both' value='${aid.validity}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">存放地点</th>
					<td width="35%"><input name="aid.storageLocation" value="${aid.storageLocation}" type="text" maxlength="127" style="width:60%"></td>
					<th width="15%">负责保管人</th>
					<td width="35%"><input name="aid.custodian" value="${aid.custodian}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">用途说明</th>
					<td width="35%" colspan="3"><textarea name="aid.application" style="width:100%;height:90px">${aid.application}</textarea></td>
				</tr>
				<tr>
					<th width="15%">性能说明</th>
					<td width="35%" colspan="3"><textarea name="aid.performance" style="width:100%;height:90px">${aid.performance}</textarea></td>
				</tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea name="aid.remark" style="width:100%;height:90px">${aid.remark}</textarea></td>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_aid');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
