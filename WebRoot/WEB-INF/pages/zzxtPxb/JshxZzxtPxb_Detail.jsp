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
					<td width="35%">${jshxZzxtPxb.szzname}</td>
					<th width="15%">企业名称</th>
					<td width="35%">${jshxZzxtPxb.qymc}</td>
				</tr>
				<tr>
					<th width="15%">培训人姓名</th>
					<td width="35%">${jshxZzxtPxb.personName}</td>
					<th width="15%">性别</th>
					<td width="35%"><cus:hxlabel codeName="性别" itemValue="${jshxZzxtPxb.sex}" /></td>
				</tr>
				<tr>
					<th width="15%">文化程度</th>
					<td width="35%"><cus:hxlabel codeName="学历" itemValue="${jshxZzxtPxb.whcd}" /></td>
					<th width="15%">从事岗位</th>
					<td width="35%">${jshxZzxtPxb.csgw}</td>
				</tr>
				<tr>
					<th width="15%">培训时间</th>
					<td width="35%"><fmt:formatDate type="date" value="${jshxZzxtPxb.pxsj}" /></td>
					<th width="15%">培训学时</th>
					<td width="35%">${jshxZzxtPxb.pxxs}</td>
				</tr>
				<tr>
					<th width="15%">授课人</th>
					<td width="35%">${jshxZzxtPxb.skr}</td>
					<th width="15%">考核成绩</th>
					<td width="35%">${jshxZzxtPxb.khcj}</td>
				</tr>
				<tr>
					<th width="15%">培训内容</th>
					<td width="85%" colspan="3">
						<textarea name="jshxZzxtPxb.pxnr" style="width:100%;height:150px" readonly="readonly">${jshxZzxtPxb.pxnr}</textarea>
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
