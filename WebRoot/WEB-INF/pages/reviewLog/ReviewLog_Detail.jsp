<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">审核项目id</th>
					<td width="35%">${reviewLog.itemId}</td>
					<th width="15%">审核项目类型</th>
					<td width="35%">${reviewLog.itemType}</td>
				</tr>
				<tr>
					<th width="15%">审核状态</th>
					<td width="35%">${reviewLog.state}</td>
					<th width="15%">审核岗位标记</th>
					<td width="35%">${reviewLog.dutyFlag}</td>
				</tr>
				<tr>
					<th width="15%">审核人Id</th>
					<td width="35%">${reviewLog.userId}</td>
					<th width="15%">审核人名称</th>
					<td width="35%">${reviewLog.userName}</td>
				</tr>
				<tr>
					<th width="15%">审核人部门编码</th>
					<td width="35%">${reviewLog.userDeptCode}</td>
					<th width="15%">审核开始时间</th>
					<td width="35%"><fmt:formatDate type="both" value="${reviewLog.startTime}" /></td>
				</tr>
				<tr>
					<th width="15%">审核结束时间</th>
					<td width="35%"><fmt:formatDate type="both" value="${reviewLog.endTime}" /></td>
					<th width="15%">审核记录</th>
					<td width="35%">${reviewLog.record}</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%">${reviewLog.remark}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
