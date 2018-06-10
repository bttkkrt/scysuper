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
					<th width="15%">所在镇</th>
					<td width="35%">
						${zjjtzsbs.szzname}
					</td>
					<th width="15%">使用单位</th>
					<td width="35%">
						${zjjtzsbs.qymc}
					</td>
				</tr>
				<tr>
					<th width="15%">注册代码</th>
					<td width="35%">${zjjtzsbs.zcdm}</td>
					<th width="15%">注册登记日期</th>
					<td width="35%">
						${zjjtzsbs.zcdjrq}
					</td>
				</tr>
				<tr>
					<th width="15%">设备档案号</th>
					<td width="35%">${zjjtzsbs.sbdah}</td>
					<th width="15%">设备类别</th>
					<td width="35%">${zjjtzsbs.sblb}</td>
				</tr>
				<tr>
					<th width="15%">新设备类别</th>
					<td width="35%">${zjjtzsbs.xsblb}</td>
					<th width="15%">设备所在地点</th>
					<td width="35%">${zjjtzsbs.szdd}</td>
				</tr>
				<tr>
					<th width="15%">设备状态</th>
					<td width="35%">${zjjtzsbs.sbzt}</td>
					<th width="15%">联系人</th>
					<td width="35%">${zjjtzsbs.dwlxr}</td>
				</tr>
				<tr>
					<th width="15%">联系电话</th>
					<td width="35%">${zjjtzsbs.dh}</td>
					<th width="15%">使用单位地址</th>
					<td width="35%">${zjjtzsbs.dwdz}</td>
				</tr>
				<tr>
					<th width="15%">出厂日期</th>
					<td width="35%">${zjjtzsbs.ccrq}</td>
					<th width="15%">出厂编号</th>
					<td width="35%">${zjjtzsbs.ccbh}</td>
				</tr>
				<tr>
					<th width="15%">检验日期</th>
					<td width="35%">${zjjtzsbs.jyrq}</td>
					<th width="15%">下次检验日期</th>
					<td width="35%">${zjjtzsbs.xcjyrq}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
