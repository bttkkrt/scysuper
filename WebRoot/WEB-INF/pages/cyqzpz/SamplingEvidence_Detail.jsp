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
					<th width="15%">现场负责人</th>
					<td width="35%" >${samplingEvidence.chargePerson}</td>
					<th width="15%">执法人员</th>
					<td width="35%" >${samplingEvidence.lawOfficer}</td>
				</tr>
				<tr>
					<th width="15%">抽样取证结束时间</th>
					<td width="35%" >${samplingEvidence.endTime}</td>
					<th width="15%">抽样地点</th>
					<td width="35%" >${samplingEvidence.forensicAddress}</td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%" >${samplingEvidence.relatedId}</td>
					<th width="15%">抽样取证开始时间</th>
					<td width="35%" >${samplingEvidence.startTime}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_samplingEvidence');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
