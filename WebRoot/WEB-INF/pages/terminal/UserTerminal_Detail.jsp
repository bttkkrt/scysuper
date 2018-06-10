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
						<th width="15%">所属部门<font color="red">*</font></th>
						<td width="35%">
							<s:select  theme="simple" cssStyle="width:100px;" id="dept" list="%{depts}" listKey="deptCode"
								name="userTerminal.deptId" listValue="deptName"
								emptyOption="true" disabled="true">
							</s:select>
					    </td>
				</tr>
				<tr>
					<th width="15%">用户名称</th>
					<td width="35%">
						<s:select theme="simple"  cssStyle="width:100px;" id="userNum" list="%{users}" listKey="id"
								name="userTerminal.userNum" listValue="displayName"
								emptyOption="true" disabled="true">
						</s:select>
					</td>
				</tr>
				<tr>
					<th width="15%">IMSI</th>
					<td colspan="3">${userTerminal.imsi}</td>
				</tr>
				<tr>
					<th width="15%">IMEI</th>
					<td colspan="3">${userTerminal.imei}</td>
				</tr>
				<tr>
					<th width="15%">终端号码</th>
					<td colspan="3">${userTerminal.telephone}</td>
				</tr>
				<tr>
					<th width="15%">终端名称</th>
					<td colspan="3">${userTerminal.terminalName}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align: center;">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
