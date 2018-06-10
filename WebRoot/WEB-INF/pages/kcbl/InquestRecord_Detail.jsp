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
					<th width="15%">勘验开始时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${inquestRecord.startTime}" /></td>
					<th width="15%">勘验结束时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${inquestRecord.endTime}" /></td>
				</tr>
				<tr>
					<th width="15%">勘验场所</th>
					<td width="35%" >${inquestRecord.inquestAddress}</td>
					<th width="15%">天气情况</th>
					<td width="35%" >${inquestRecord.weatherCondition}</td>
				</tr>
				<tr>
					<th width="15%">当事人1</th>
					<td width="35%" >${inquestRecord.party1}</td>
					<th width="15%">当事人1单位及职务</th>
					<td width="35%" >${inquestRecord.party1Company}</td>
				</tr>
				<tr>
					<th width="15%">当事人2</th>
					<td width="35%" >${inquestRecord.party2}</td>
					<th width="15%">当事人2单位及职务</th>
					<td width="35%" >${inquestRecord.party2Company}</td>
				</tr>
				<tr>
					<th width="15%">被邀请人</th>
					<td width="35%" >${inquestRecord.invitee}</td>
					<th width="15%">被邀请人单位及职务</th>
					<td width="35%" >${inquestRecord.inviteeCompany}</td>
				</tr>
				<tr>
					<th width="15%">记录人</th>
					<td width="35%" >${inquestRecord.recordPerson}</td>
					<th width="15%">记录人单位及职务</th>
					<td width="35%" >${inquestRecord.recordCompany}</td>
				</tr>
				<tr>
					<th width="15%">勘验情况</th>
					<td width="35%" >${inquestRecord.inquestCondition}</td>
					<th width="15%">勘验人</th>
					<td width="35%" >${inquestRecord.inquestPerson}</td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%" >${inquestRecord.relatedId}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_inquestRecord');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
