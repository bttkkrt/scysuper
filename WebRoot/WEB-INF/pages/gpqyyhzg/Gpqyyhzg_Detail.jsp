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
					<th width="20%">所在镇</th>
					<td width="20%">${gpqyyhzg.szzname}</td>
					<th width="20%">填报人</th>
					<td width="40%">${gpqyyhzg.tbr}</td>
				</tr>
				<tr>
					<th width="20%">统计月份</th>
					<td width="20%" colspan="3">${gpqyyhzg.tjyf}</td>
				</tr>
				<tr>
					<th width="20%" rowspan="2">责任书签订情况</th>
					<th width="50%" colspan="2">政府与村</th>
					<td width="20%">${gpqyyhzg.zfyc}</td>
				</tr>
				<tr>
					<th width="40%" colspan="2">村与企业</th>
					<td width="20%">${gpqyyhzg.cyqy}</td>
				</tr>
				<tr>
					<th width="20%" rowspan="12">挂牌督办完成情况</th>
					<th width="20%" rowspan="4">市</th>
					<th width="20%">挂牌数</th>
					<td width="20%">${gpqyyhzg.sgps}</td>
				</tr>
				<tr>
					<th width="20%">已整改</th>
					<td width="20%">${gpqyyhzg.syzg}</td>
				</tr>
				<tr>
					<th width="20%">整改中</th>
					<td width="20%">${gpqyyhzg.szgz}</td>
				</tr>
				<tr>
					<th width="20%">未整改</th>
					<td width="20%">${gpqyyhzg.swzg}</td>
				</tr>
				<tr>
					<th width="20%" rowspan="4">区</th>
					<th width="20%">挂牌数</th>
					<td width="20%">${gpqyyhzg.qgps}</td>
				</tr>
				<tr>
					<th width="20%">已整改</th>
					<td width="20%">${gpqyyhzg.qyzg}</td>
				</tr>
				<tr>
					<th width="20%">整改中</th>
					<td width="20%">${gpqyyhzg.yzgz}</td>
				</tr>
				<tr>
					<th width="20%">未整改</th>
					<td width="20%">${gpqyyhzg.qwzg}</td>
				</tr>
				<tr>
					<th width="20%" rowspan="4">镇</th>
					<th width="20%">挂牌数</th>
					<td width="20%">${gpqyyhzg.zgps}</td>
				</tr>
				<tr>
					<th width="20%">已整改</th>
					<td width="20%">${gpqyyhzg.zyzg}</td>
				</tr>
				<tr>
					<th width="20%">整改中</th>
					<td width="20%">${gpqyyhzg.zzgz}</td>
				</tr>
				<tr>
					<th width="20%">未整改</th>
					<td width="20%">${gpqyyhzg.zwzg}</td>
				</tr>
				<tr>
					<th width="20%" rowspan="3">检查情况</th>
					<th width="40%" colspan="2">检查企业数</th>
					<td width="20%">${gpqyyhzg.jcqys}</td>
				</tr>
				<tr>
					<th width="40%" colspan="2">发现隐患数</th>
					<td width="20%">${gpqyyhzg.fxyhs}</td>
				</tr>
				<tr>
					<th width="40%" colspan="2">整改隐患数</th>
					<td width="20%">${gpqyyhzg.zgyhs}</td>
				</tr>
				<tr>
					<th width="20%" rowspan="3">培训情况</th>
					<th width="40%" colspan="2">年度目标(人)</th>
					<td width="20%">${gpqyyhzg.ndmb}</td>
				</tr>
				<tr>
					<th width="40%" colspan="2">完成人数（人）</th>
					<td width="20%">${gpqyyhzg.wcrs}</td>
				</tr>
				<tr>
					<th width="40%" colspan="2">完成率%</th>
					<td width="20%">${gpqyyhzg.wcl}</td>
				</tr>
				<tr>
					<th width="20%">备注</th>
					<td width="80%" colspan="3"><textarea name="gpqyyhzg.bz" style="width:100%;height:120px" readonly="readonly">${gpqyyhzg.bz}</textarea></td>
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
