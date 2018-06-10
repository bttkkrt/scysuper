<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="licCanInfCheckSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="licCanInf.id" value="${licCanInf.id}">
		<input type="hidden" name="checkRecord.infoId" value="${licCanInf.id}">
			<table width="100%">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" >${licCanInf.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${licCanInf.companyName}</td>
				</tr>
				<tr>
					<th width="15%">许可证名称</th>
					<td width="35%" >${licCanInf.licenseName}</td>
					<th width="15%">许可证编号</th>
					<td width="35%" >${licCanInf.licenseNumber}</td>
				</tr>
				<tr>
					<th width="15%">注销文号</th>
					<td width="35%" >${licCanInf.cancellationNumber}</td>
					<th width="15%">注销原因</th>
					<td width="35%" >${licCanInf.cancelReason}</td>
				</tr>
				<tr>
					<th width="15%">批准机关名称</th>
					<td width="35%" >${licCanInf.approvalAuthority}</td>
					<th width="15%">批准日期</th>
					<td width="35%" ><fmt:formatDate type="both" value="${licCanInf.approvalDate}" /></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3" ><textarea readOnly name="licCanInf.remark" style="width:96%;height:60px">${licCanInf.remark}</textarea></td>
				</tr>
				
					<tr>
					<th width="15%">审核结果</th>
					<td width="96%" colspan="3">
					<s:select name="checkRecord.checkResult" list="#{'审核通过':'审核通过','审核未通过':'审核未通过','审核通过并上报信用平台':'审核通过并上报信用平台'}" theme="simple"/>
					</td>
				</tr>
				<tr>
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
			<tr>
					<th width="15%">审核备注</th>
					<td width="96%" colspan="3"><textarea name="checkRecord.checkRemark" style="width:96%;height:60px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)"></textarea></td>
				</tr>
				
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_licCanInf');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</form>
	</div></div></div>
</body>
</html>

