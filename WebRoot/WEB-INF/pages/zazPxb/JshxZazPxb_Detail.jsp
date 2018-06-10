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
						${jshxZazPxb.szzname}
					</td>
					<th width="15%">企业名称</th>
					<td width="35%">
						${jshxZazPxb.qymc}
					</td>
				</tr>
				<tr>
					<th width="15%">姓名</th>
					<td width="35%">${jshxZazPxb.personName}</td>
					<th width="15%">性别</th>
					<td width="35%">
						${jshxZazPxb.sex}
					</td>
				</tr>
				<tr>
					<th width="15%">职务</th>
					<td width="35%">
						${jshxZazPxb.zw}
					</td>
					<th width="15%">文化</th>
					<td width="35%">
						${jshxZazPxb.xl}
					</td>
				</tr>
				<tr>
					<th width="15%">电话</th>
					<td width="35%">${jshxZazPxb.lxfs}</td>
					<th width="15%">身份证</th>
					<td width="35%">${jshxZazPxb.sfz}</td>
				</tr>
				<tr>
					<th width="15%">地址</th>
					<td width="85%" colspan="3">${jshxZazPxb.address}</td>
					
				</tr>
				<tr>
					<th width="15%">初培时间</th>
					<td width="35%">${jshxZazPxb.cpsj}</td>
					<th width="15%">资格证号</th>
					<td width="35%">${jshxZazPxb.pxzh}</td>
				</tr>
				<tr>
					<th width="15%">成绩</th>
					<td width="35%">${jshxZazPxb.kscj}</td>
					<th width="15%">危化品企业类型</th>
					<td width="35%">
						${jshxZazPxb.whpqylx}
					</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3">
						<textarea  style="width:600px;" name="jshxZazPxb.bz" disabled="true">${jshxZazPxb.bz}</textarea>
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
