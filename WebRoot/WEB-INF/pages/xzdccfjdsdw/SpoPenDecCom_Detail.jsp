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
					<th width="15%">规定</th>
					<td width="35%" >${spoPenDecCom.provision}</td>
					<th width="15%">执法依据</th>
					<td width="35%" >${spoPenDecCom.lawBasic}</td>
				</tr>
				<tr>
					<th width="15%">执法人员</th>
					<td width="35%" >${spoPenDecCom.lawOfficer}</td>
					<th width="15%">罚款方式</th>
					<td width="35%" ><cus:hxlabel codeName="罚款方式" itemValue="${spoPenDecCom.fineMethod}" /></td>
				</tr>
				<tr>
					<th width="15%">银行名称</th>
					<td width="35%" >${spoPenDecCom.bankName}</td>
					<th width="15%">银行账户</th>
					<td width="35%" >${spoPenDecCom.bankAccount}</td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%" >${spoPenDecCom.relatedId}</td>
					<th width="15%">行政处罚</th>
					<td width="35%" >${spoPenDecCom.adminPenalties}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_spoPenDecCom');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
