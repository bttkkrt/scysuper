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
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">
						<s:if test="data == 5">
						上报部门
						</s:if>
						<s:else>
						所属地区
						</s:else>
						</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">总企业数</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">检查企业数</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">出动次数</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">查出隐患数</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">完成隐患数</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">隐患整改率</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">重大隐患数</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">完成重大隐患数</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">重大隐患整改率</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">整改资金</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">现场检查记录</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">责令限期整改指令书</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">强制措施决定书</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">整改复查意见书</th>
					</tr>
					<s:iterator value="qylxs" id="bean" status="sta">
						<tr style="border:1px solid #d5dbdc;height:24px">
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#sta.count'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.szz'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.qyTotal'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.jcqys'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.cdcs'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.ccyhs'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.wcyhs'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.yhzgl'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.zdyhs'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.wczdyhs'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.zdyhzgl'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.zgzj'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.xcjcjl'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.zgzls'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.qzcs'/></td>
							<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff"><s:property value='#bean.fcyjs'/></td>
						</tr>
					</s:iterator>
					
					<tr style="border:1px solid #d5dbdc;height:24px">
						<th colspan="2"  style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">合计</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${qylx.qyTotal}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${qylx.jcqys}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${qylx.cdcs}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${qylx.ccyhs}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${qylx.wcyhs}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${qylx.yhzgl}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${qylx.zdyhs}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${qylx.wczdyhs}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${qylx.zdyhzgl}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${qylx.zgzj}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${qylx.xcjcjl}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${qylx.zgzls}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${qylx.qzcs}</th>
						<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">${qylx.fcyjs}</th>
					</tr>
				</table>
			</div>
</body>
</html>
