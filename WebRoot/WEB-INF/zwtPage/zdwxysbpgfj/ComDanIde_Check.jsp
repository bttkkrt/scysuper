<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
</head>
<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="comDanIdeCheckSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="comDanIde.id" value="${comDanIde.id}">
		<input type="hidden" name="checkRecord.infoId" value="${comDanIde.id}">
			<table width="100%">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" >${comDanIde.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${comDanIde.companyName}</td>
				</tr>
				<tr>
					<th width="15%">重点危险源名称</th>
					<td width="35%" >${comDanIde.dangerName}</td>
					<th width="15%">重点危险源类别</th>
					<td width="35%" >${comDanIde.dangerType}</td>
				</tr>
				<tr>
					<th width="15%">重点危险源级别</th>
					<td width="35%" >${comDanIde.dangerLevel}</td>
					<th width="15%">重点危险源地址</th>
					<td width="35%" >${comDanIde.dangerAddress}</td>
				</tr>
				<tr>
					<th width="15%">安全负责人</th>
					<td width="35%" >${comDanIde.safePerson}</td>
					<th width="15%">联系方式</th>
					<td width="35%" >${comDanIde.tele}</td>
				</tr>
				<tr>
					<th width="15%">巡查频率</th>
					<td width="35%" ><cus:hxlabel codeName="巡查频率" itemValue="${comDanIde.checkFrequency}" /></td>
					<th width="15%">巡查人员姓名</th>
					<td width="35%" >${comDanIde.checkPeopleName}</td>
				</tr>
				<tr>
					<th width="15%">要求</th>
					<td width="35%" colspan="3"><textarea readOnly name="comDanIde.remark" style="width:96%;height:60px">${comDanIde.remark}</textarea></td>
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
					<th width="15%">审核结果</th>
					<td width="35%">
					<s:select name="checkRecord.checkResult" list="#{'审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple" cssStyle="width:60%" />
					</td>
				</tr>
				<tr>
					<th width="15%">审核备注</th>
					<td width="96%" colspan="3"><textarea name="checkRecord.checkRemark" style="width:96%;height:60px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)"></textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
 					</td>
				</tr>
			</table>
		</form>
	</div></div></div>
</body>
</html>

