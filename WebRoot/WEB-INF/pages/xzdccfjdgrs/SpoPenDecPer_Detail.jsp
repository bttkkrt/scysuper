<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">被处罚人性别</th>
					<td width="35%" ><cus:hxlabel codeName="性别" itemValue="${spoPenDecPer.punishedSex}" /></td>
					<th width="15%">被处罚人年龄</th>
					<td width="35%" >${spoPenDecPer.punishedAge}</td>
				</tr>
				<tr>
					<th width="15%">被处罚人身份证号</th>
					<td width="35%" >${spoPenDecPer.punishedId}</td>
					<th width="15%">被处罚人家庭住址</th>
					<td width="35%" >${spoPenDecPer.punishedAddress}</td>
				</tr>
				<tr>
					<th width="15%">执法人员</th>
					<td width="35%" >${spoPenDecPer.lawOfficer}</td>
					<th width="15%">规定</th>
					<td width="35%" >${spoPenDecPer.provision}</td>
				</tr>
				<tr>
					<th width="15%">执法依据</th>
					<td width="35%" >${spoPenDecPer.lawBasic}</td>
					<th width="15%">行政处罚</th>
					<td width="35%" >${spoPenDecPer.adminPenalties}</td>
				</tr>
				<tr>
					<th width="15%">罚款方式</th>
					<td width="35%" ><cus:hxlabel codeName="罚款方式" itemValue="${spoPenDecPer.fineMethod}" /></td>
					<th width="15%">银行名称</th>
					<td width="35%" >${spoPenDecPer.bankName}</td>
				</tr>
				<tr>
					<th width="15%">银行账户</th>
					<td width="35%" >${spoPenDecPer.bankAccount}</td>
					<th width="15%">关联文书编号</th>
					<td width="35%" >${spoPenDecPer.relatedId}</td>
				</tr>
				<tr>
					<th width="15%">被处罚人职务</th>
					<td width="35%" >${spoPenDecPer.punishedPosition}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_spoPenDecPer');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
