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
					<th width="10%">所在镇名称</th>
					<td width="23%">${jshxXrcPxb.szzname}</td>
					<th width="10%">企业名称</th>
					<td width="23%">${jshxXrcPxb.qymc}</td>
					<th width="10%">姓名</th>
					<td width="24%">${jshxXrcPxb.personName}</td>
				</tr>
				<tr>
					<th width="10%">性别</th>
					<td width="23%"><cus:hxlabel codeName="性别" itemValue="${jshxXrcPxb.sex}" /></td>
					<th width="10%">文化程度</th>
					<td width="23%"><cus:hxlabel codeName="学历" itemValue="${jshxXrcPxb.whcd}" /></td>
					<th width="10%">从事岗位</th>
					<td width="24%">${jshxXrcPxb.csgw}</td>
				</tr>
				<tr>
					<th width="10%">培训学时</th>
					<td width="23%">${jshxXrcPxb.pxxs}</td>
				</tr>
				<tr>
					<th width="100%" colspan="6" style="text-align:center">厂级培训内容</th>
				</tr>
				<tr>
					<th width="10%">培训时间</th>
					<td width="23%"><fmt:formatDate type="date" value="${jshxXrcPxb.facpxsj}" /></td>
					<th width="10%">培训授课人</th>
					<td width="23%">${jshxXrcPxb.facpxskr}</td>
					<th width="10%">培训考核成绩</th>
					<td width="24%">${jshxXrcPxb.facpxkhcj}</td>
				</tr>
				<tr>
					<th width="10%">培训内容</th>
					<td width="90%" colspan="5">
						<textarea name="jshxXrcPxb.facpxnr" style="width:100%;height:100px" readonly="readonly">${jshxXrcPxb.facpxnr}</textarea>
					</td>
				</tr>
				<tr>
					<th width="100%" colspan="6" style="text-align:center">车间培训内容</th>
				</tr>
				<tr>
					<th width="10%">培训时间</th>
					<td width="23%"><fmt:formatDate type="date" value="${jshxXrcPxb.cjpxsj}" /></td>
					<th width="10%">培训授课人</th>
					<td width="23%">${jshxXrcPxb.cjpxskr}</td>
					<th width="10%">培训考核成绩</th>
					<td width="24%">${jshxXrcPxb.cjpxkhcj}</td>
				</tr>
				<tr>
					<th width="10%">培训内容</th>
					<td width="90%" colspan="5">
						<textarea name="jshxXrcPxb.cjpxnr" style="width:100%;height:100px" readonly="readonly">${jshxXrcPxb.cjpxnr}</textarea>
					</td>
				</tr>
				<tr>
					<th width="100%" colspan="6" style="text-align:center">班组培训内容</th>
				</tr>
				<tr>
					<th width="10%">培训时间</th>
					<td width="23%"><fmt:formatDate type="date" value="${jshxXrcPxb.bzpxsj}" /></td>
					<th width="10%">培训授课人</th>
					<td width="23%">${jshxXrcPxb.bzpxskr}</td>
					<th width="10%">培训考核成绩</th>
					<td width="24%">${jshxXrcPxb.bzpxkhcj}</td>
				</tr>
				<tr>
					<th width="10%">培训内容</th>
					<td width="90%" colspan="5">
						<textarea name="jshxXrcPxb.bzpxnr" style="width:100%;height:100px" readonly="readonly">${jshxXrcPxb.bzpxnr}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="6" height="100px" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
