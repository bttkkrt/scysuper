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
					<th width="15%">所在镇名称</th>
					<td width="35%">${jshxTzsb.szzname}</td>
					<th width="15%">企业名称</th>
					<td width="35%">${jshxTzsb.qymc}</td>
				</tr>
				<tr>
					<th width="15%">设备名称</th>
					<td width="85%" colspan="3">${jshxTzsb.sbmc}</td>
				</tr>
				<tr>
					<th width="15%">操作人员</th>
					<td width="35%">${jshxTzsb.operStaff}</td>
					<th width="15%">操作人员持证情况</th>
					<td width="35%">${jshxTzsb.cerOfOperStaff}</td>
				</tr>
				<tr>
					<th width="15%">设备种类</th>
					<td width="35%"><cus:hxlabel codeName="特种设备类型" itemValue="${jshxTzsb.sblx}" /></td>
					<th width="15%">设备位号</th>
					<td width="35%">${jshxTzsb.sbwh}</td>
				</tr>
				<tr>
					<th width="15%">使用证编号</th>
					<td width="35%">${jshxTzsb.syzbh}</td>
					<th width="15%">规格型号</th>
					<td width="35%">${jshxTzsb.ggxh}</td>
				</tr>
				<tr>
					<th width="15%">制造单位名称</th>
					<td width="35%">${jshxTzsb.zzdwmc}</td>
					<th width="15%">使用状态</th>
					<td width="35%"><cus:hxlabel codeName="特种设备使用状态" itemValue="${jshxTzsb.syzt}" /></td>
				</tr>
				<tr>
					<th width="15%">检测单位</th>
					<td width="35%">${jshxTzsb.jcdw}</td>
					<th width="15%">设备使用安装地点</th>
					<td width="35%">${jshxTzsb.sbazdd}</td>
				</tr>
				<tr>
					<th width="15%">上次检测日期</th>
					<td width="35%"><fmt:formatDate type="date" value="${jshxTzsb.scjcrq}" /></td>
					<th width="15%">下次检验日期</th>
					<td width="35%"><fmt:formatDate type="date" value="${jshxTzsb.xcjcrq}" /></td>
				</tr>
				<tr>
					<th width="15%">安全责任人</th>
					<td width="35%">${jshxTzsb.aqzrr}</td>
					<th width="15%">安全附件</th>
					<td width="35%">${jshxTzsb.aqfj}</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3">
						<textarea name="jshxTzsb.bz" style="width:100%;height:100px" readonly="readonly">${jshxTzsb.bz}</textarea>
					</td>
				</tr>
				<tr>
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
