<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>审核记录</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="instrumentsInfoShenheAllSave.action">
		<s:token />
		<input type="hidden" name="ids" value="${ids}">
			<table width="100%">
				<tr>
					<th width="15%">审核结果</th>
					<td width="35%">
						<s:select name="instrumentsInfo.result" list="#{'0':'审核通过','1':'审核不通过'}" theme="simple"/>
					</td>
				</tr>
				<tr>
					<th width="15%">审核意见</th>
					<td width="85%" colspan="3"><textarea name="instrumentsInfo.remark" style="width:96%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)"></textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >确认<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_instrumentsInfo');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
