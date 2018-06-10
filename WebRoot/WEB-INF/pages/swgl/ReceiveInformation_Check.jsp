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
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="receiveInformationCheckSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="receiveInformation.id" value="${receiveInformation.id}">
		<input type="hidden" name="checkRecord.infoId" value="${receiveInformation.id}">
			<table width="100%">
				<tr>
					<th width="15%">来文标题</th>
					<td width="35%" >${receiveInformation.recinfoTitle}</td>
				</tr>
				<tr>
					<th width="15%">来文编号</th>
					<td width="35%" >${receiveInformation.recinfoNum}</td>
					<th width="15%">来文类型</th>
					<td width="35%" ><cus:hxlabel codeName="来文类型" itemValue="${receiveInformation.recinfoType}" /></td>
				</tr>
				<tr>
					<th width="15%">来文单位</th>
					<td width="35%" >${receiveInformation.recinfoDept}</td>
					<th width="15%">收文时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${receiveInformation.recinfoTime}" /></td>
				</tr>
			
				<tr>
					<th width="15%">审核结果</th>
					<td width="96%" colspan="3">
					<s:select name="checkRecord.checkResult" list="#{'审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
					</td>
				</tr>
				<tr>
					<th width="15%">审核备注</th>
					<td width="96%" colspan="3"><textarea name="checkRecord.checkRemark" style="width:96%;height:60px"></textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
					    <a href="#" class="btn_01" onclick="parent.close_win('win_receiveInformation');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
