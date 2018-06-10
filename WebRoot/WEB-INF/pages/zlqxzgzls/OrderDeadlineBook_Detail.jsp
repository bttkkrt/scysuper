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
					<th width="15%">问题</th>
					<td width="35%" >${orderDeadlineBook.problem}</td>
					<th width="15%">执法人员</th>
					<td width="35%" >${orderDeadlineBook.lawOfficer}</td>
				</tr>
				<tr>
					<th width="15%">整改开始时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${orderDeadlineBook.startTime}" /></td>
					<th width="15%">整改结束时间</th>
					<td width="35%" ><fmt:formatDate type="both" value="${orderDeadlineBook.endTime}" /></td>
				</tr>
				<tr>
					<th width="15%">关联文书编号</th>
					<td width="35%" >${orderDeadlineBook.relatedId}</td>
					<th width="15%">修改项</th>
					<td width="35%" >${orderDeadlineBook.changeItem}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_orderDeadlineBook');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
