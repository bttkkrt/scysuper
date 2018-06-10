<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title></title>
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
		text-align:center;
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
				<div style="clear:both;background:#f5f8f9;border:1px solid #e8eff2;margin:6px 0 0 0">
				<table style="border-collapse:collapse;border:1px solid #d5dbdc;width:100%">
					<tr>
						<th>序号</th>
						<th>部门及镇、街道</th>
						<th>辖区内企业（单位）数</th>
						<th>上报企业（单位数）</th>
						<th>未上报企业（单位数）</th>
						<th>隐患排查数</th>
						<th>整改隐患数</th>
						<th>未整改隐患数</th>
						<th>整改率</th>
					</tr>
					<s:iterator value="tjyhList" id="tjyh" status="sta">
						<tr>
							<td><s:property value='#sta.count'/></td>
							<td><s:property value='#tjyh.dwdz'/></td>
							<td><s:property value='#tjyh.qyTotal'/></td>
							<td><s:property value='#tjyh.sbqy'/></td>
							<td><s:property value='#tjyh.wsbqy'/></td>
							<td><s:property value='#tjyh.yhTotal'/></td>
							<td><s:property value='#tjyh.zgwc'/></td>
							<td><s:property value='#tjyh.zgwwc'/></td>
							<td><s:property value='#tjyh.zgl'/></td>
						</tr>
					</s:iterator>
					<tr>
						<th colspan="2">合计</th>
						<th>${tjyhBean.qyTotal}</th>
						<th>${tjyhBean.sbqy}</th>
						<th>${tjyhBean.wsbqy}</th>
						<th>${tjyhBean.yhTotal}</th>
						<th>${tjyhBean.zgwc}</th>
						<th>${tjyhBean.zgwwc}</th>
						<th>${tjyhBean.zgl}</th>
					</tr>
				</table>
			</div>
</body>
</html>
