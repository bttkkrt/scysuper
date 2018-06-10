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
					<td width="35%">${jshxAqfj.szzname}</td>
					<th width="15%">企业名称</th>
					<td width="35%">${jshxAqfj.qymc}</td>
				</tr>
				<tr>
					<th width="15%">安全附件名称</th>
					<td width="35%">${jshxAqfj.aqfjmc}</td>
					<th width="15%">型号</th>
					<td width="35%">${jshxAqfj.xh}</td>
				</tr>
				<tr>
					<th width="15%">所属设备名称</th>
					<td width="35%">${jshxAqfj.sssbmc}</td>
					<th width="15%">所属特种设备出厂编号</th>
					<td width="35%">${jshxAqfj.sstzsbccbh}</td>
				</tr>
				<tr>
					<th width="15%">投用日期</th>
					<td width="35%"><fmt:formatDate type="date" value="${jshxAqfj.tyrq}" /></td>
					<th width="15%">制造单位名称</th>
					<td width="35%">${jshxAqfj.zzdwmc}</td>
				</tr>
				<tr>
					<th width="15%">使用部门</th>
					<td width="35%">${jshxAqfj.sybm}</td>
					<th width="15%">使用状态</th>
					<td width="35%">${jshxAqfj.syzt}</td>
				</tr>
				<tr>	
					<th width="15%">设备安装地点</th>
					<td width="85%" colspan="3">${jshxAqfj.sbazdd}</td>
				</tr>
				<tr>
					<th width="15%">上次检测日期</th>
					<td width="35%"><fmt:formatDate type="date" value="${jshxAqfj.scjcsj}" /></td>
					<th width="15%">下次检测日期</th>
					<td width="35%"><fmt:formatDate type="date" value="${jshxAqfj.xcjcsj}" /></td>
				</tr>
				<tr>
					<th width="15%">安全责任人</th>
					<td width="35%">${jshxAqfj.aqzrr}</td>
					<th width="15%">检验情况记录</th>
					<td width="35%">${jshxAqfj.jyqkjl}</td>
				</tr>
				<tr>
					<th width="15%">检验报告编号</th>
					<td width="85%" colspan="3">
						<textarea name="jshxAqfj.jyqkjl" style="width:100%;height:100px" readonly="readonly">${jshxAqfj.jyqkjl}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">检验结论</th>
					<td width="35%">${jshxAqfj.jyjl}</td>
					<th width="15%">检测单位</th>
					<td width="35%">${jshxAqfj.jcdw}</td>
				</tr>
				<tr>
					<th width="15%">整改情况</th>
					<td width="85%" colspan="3">
						<textarea name="jshxAqfj.zgqk" style="width:100%;height:100px" readonly="readonly">${jshxAqfj.zgqk}</textarea>
					</td>
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
