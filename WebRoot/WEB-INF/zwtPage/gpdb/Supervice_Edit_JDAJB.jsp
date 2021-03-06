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
	<form name="myform1" method="post" enctype="multipart/form-data" action="superviceSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="supervice.id" value="${supervice.id}">
		<input type="hidden" name="supervice.createTime" value="<fmt:formatDate type="both" value="${supervice.createTime}" />">
		<input type="hidden" name="supervice.updateTime" value="${supervice.updateTime}">
		<input type="hidden" name="supervice.createUserID" value="${supervice.createUserID}">
		<input type="hidden" name="supervice.updateUserID" value="${supervice.updateUserID}">
		<input type="hidden" name="supervice.deptId" value="${supervice.deptId}">
		<input type="hidden" name="supervice.delFlag" value="${supervice.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag property="supervice.areaName" defaultText='请选择' codeName="企业属地" value="${supervice.areaName}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="supervice.companyName" value="${supervice.companyName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">隐患名称</th>
					<td width="35%"><input name="supervice.dangerName" value="${supervice.dangerName}" type="text" errormsg='隐患名称必须是1到127位字符！' nullmsg='隐患名称不能为空！' sucmsg='隐患名称填写正确！' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">挂牌时间</th>
					<td width="35%"><input name="supervice.listingTime" style="width:150px;" value="<fmt:formatDate type='both'  pattern="yyyy-MM-dd" value='${supervice.listingTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
				</tr>
				<tr>
					<th width="15%">隐患类别</th>
					<td width="35%"><cus:SelectOneTag property="supervice.dangerSort" defaultText='请选择' codeName="隐患类别" value="${supervice.dangerSort}" /></td>
					<th width="15%">隐患级别</th>
					<td width="35%"><cus:SelectOneTag property="supervice.dangerLevel" defaultText='请选择' codeName="隐患级别" value="${supervice.dangerLevel}" /></td>
				</tr>
				<tr>
					<th width="15%">隐患内容</th>
					<td width="35%"><input name="supervice.dangerContent" value="${supervice.dangerContent}" type="text" maxlength="127"></td>
					<th width="15%">责任人</th>
					<td width="35%"><input name="supervice.responsible" value="${supervice.responsible}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">责任人联系电话</th>
					<td width="35%"><input name="supervice.responsibleMobile" value="${supervice.responsibleMobile}" type="text" maxlength="127"></td>
					<th width="15%">责任单位</th>
					<td width="35%"><input name="supervice.responsibleUnit" value="${supervice.responsibleUnit}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">地址</th>
					<td width="35%"><input name="supervice.address" value="${supervice.address}" type="text" maxlength="127"></td>
					<th width="15%">整改期限</th>
					<td width="35%"><input name="supervice.rectificationTerm" value="<fmt:formatDate type='both'  pattern="yyyy-MM-dd" value='${supervice.rectificationTerm}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">整改状态</th>
					<td width="35%"><cus:SelectOneTag property="supervice.rectificationState" defaultText='请选择' codeName="隐患整改状态" value="${supervice.rectificationState}" /></td>
					<th width="15%">整改资金（元）</th>
					<td width="35%"><input name="supervice.rectificationMoney" value="${supervice.rectificationMoney}" type="text" maxlength="127"></td>
				<tr>
					<th width="15%">整改完成时间</th>
					<td width="35%"><input name="supervice.recfinishTime" value="<fmt:formatDate type='both'  pattern="yyyy-MM-dd" value='${supervice.recfinishTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">验收时间</th>
					<td width="35%"><input name="supervice.acceptTime" value="<fmt:formatDate type='both' value='${supervice.acceptTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
				</tr>
				<tr>
					<th width="15%">隐患整改数</th>
					<td width="35%"><input name="supervice.danrecNum" value="${supervice.danrecNum}" type="text" maxlength="127"></td>
					<th width="15%">附件关联id</th>
					<td width="35%"><input name="supervice.linkId" value="${supervice.linkId}" type="text" maxlength="127"></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
						</s:else>						
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
