<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>
<body>
	<div style="clear:both;background:#f5f8f9;border:1px solid #e8eff2;margin:6px 0 0 0">
				<table style="border-collapse:collapse;border:1px solid #d5dbdc;width:100%">
					<tr style="border:1px solid #d5dbdc;height:24px">
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">序号</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">企业类型</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">总企业数</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">检查企业数</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">隐患数</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">整改数</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">整改率</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">整改资金</th>
					</tr>
					<s:iterator value="datas" id="bean" status="sta">
						<tr style="border:1px solid #d5dbdc;height:24px">
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#sta.count'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.qylx'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.qyTotal'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.jcqys'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.yhs'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.zgs'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.zgl'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.zgzj'/></td>
						</tr>
					</s:iterator>
					
					<tr style="border:1px solid #d5dbdc;height:24px">
						<th colspan="2" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">合计</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${data.qyTotal}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${data.jcqys}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${data.yhs}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${data.zgs}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${data.zgl}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${data.zgzj}</th>
					</tr>
				</table>
			</div>
</body>
</html>
