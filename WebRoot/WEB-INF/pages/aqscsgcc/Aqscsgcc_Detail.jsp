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
		background:#fff;
		text-align:center;
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
			<table width="100%" border="0" style="border-collapse:collapse;border:1px solid #d5dbdc;width:100%">
				<tr>
					<th width="16%">月份</th>
					<td width="24%" colspan="2">${aqscsgcc.yf}</td>
				</tr>
				<tr>
					<th width="16%">项目内容</th>
					<th width="12%">计量单位</th>
					<th width="12%">合计</th>
					<th width="12%">危险化学品</th>
					<th width="12%">烟花爆竹</th>
					<th width="12%">冶金</th>
					<th width="12%">有色</th>
					<th width="12%">其它</th>
				</tr>
				<s:iterator id="aqscsgccglb" value="%{list}" status="sta">
					<s:if test="%{#aqscsgccglb.linktype == '17' || #aqscsgccglb.linktype == '20' || #aqscsgccglb.linktype == '23'}">
						<tr>
							<th><s:property value="#aqscsgccglb.xmrr"/></th>
							<th><s:property value="#aqscsgccglb.jldw"/></th>
							<td><s:property value="#aqscsgccglb.hj"/></td>
							<th>乡科级</th>
							<th colspan="2">其它</th>
							<td colspan="2">
							</td>
						</tr>
					</s:if>
					<s:elseif test="%{#aqscsgccglb.linktype == '18' || #aqscsgccglb.linktype == '21' || #aqscsgccglb.linktype == '24'}">
						<tr>
							<th><s:property value="#aqscsgccglb.xmrr"/></th>
							<th><s:property value="#aqscsgccglb.jldw"/></th>
							<td><s:property value="#aqscsgccglb.hj"/></td>
							<td><s:property value="#aqscsgccglb.wxp"/></td>
							<td colspan="2"><s:property value="#aqscsgccglb.yhbz"/></td>
							<td colspan="2">
							</td>
						</tr>
					</s:elseif>
					<s:else>
						<tr>
							<th><s:property value="#aqscsgccglb.xmrr"/></th>
							<th><s:property value="#aqscsgccglb.jldw"/></th>
							<td><s:property value="#aqscsgccglb.hj"/></td>
							<td><s:property value="#aqscsgccglb.wxp"/></td>
							<td><s:property value="#aqscsgccglb.yhbz"/></td>
							<td><s:property value="#aqscsgccglb.yj"/></td>
							<td><s:property value="#aqscsgccglb.youse"/></td>
							<td><s:property value="#aqscsgccglb.qt"/></td>
						</tr>
					</s:else>
				</s:iterator>
				<tr>
					<th width="16%">负责人</th>
					<td width="24%" colspan="2">${aqscsgcc.fzr}</td>
					<th width="12%">填表人</th>
					<td width="24%" colspan="2">${aqscsgcc.tbr}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="8" height="100px" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
