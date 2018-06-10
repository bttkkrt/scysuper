<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<style>
	th
	{
		border:1px solid #d5dbdc;
		height:24px;
		text-align:center;
		padding:0 2px 0 0;
	}
	td
	{
		border:1px solid #d5dbdc;
		height:24px;
		text-align:left;
		padding:0 0 0 2px;
		background:#fff
	}
	tr
	{
		border:1px solid #d5dbdc;
		height:24px
	}
	</style>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div style="clear:both;background:#f5f8f9;border:1px solid #e8eff2;margin:6px 0 0 0">
			<table width="100%" style="border-collapse:collapse;border:1px solid #d5dbdc;width:100%">
				<tr>
					<th width="20%">本地区打击各类行业企业情况（单位：人、家、个、处、万元）</th>
					<th width="8%">非煤矿山</th>
					<th width="8%">道路交通</th>
					<th width="8%">水上交通</th>
					<th width="8%">建筑施工</th>
					<th width="8%">消防</th>
					<th width="8%">危化品</th>
					<th width="8%">烟花爆竹</th>
					<th width="8%">民爆物品</th>
					<th width="8%">冶金</th>
					<th width="8%">其他</th>
				</tr>
				<c:forEach var="dfzwglb" items="${dfzwlist}" varStatus="status">
					<tr>
						<td width="20%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${dfzwglb.linktype}</td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${dfzwglb.num1}</td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${dfzwglb.num2}</td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${dfzwglb.num3}</td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${dfzwglb.num4}</td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${dfzwglb.num5}</td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${dfzwglb.num6}</td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${dfzwglb.num7}</td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${dfzwglb.num8}</td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${dfzwglb.num9}</td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${dfzwglb.num10}</td>
					</tr>
				</c:forEach>
				<tr>
					<th>填报单位</th>
					<td>${dfzw.szzname}</td>
					<th>填报人</th>
					<td>${dfzw.tbr}</td>
					<th>填报时间</th>
					<td><fmt:formatDate type='date' value='${dfzw.tbsj}'/></td>
				</tr>
				<tr>
					<td colspan="11" height="100px" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
