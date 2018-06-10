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
						<th rowspan="5">行业分类</th>
						<th colspan="3">开展排查治理事故隐患的企业（单位）</th>
						<th colspan="3">一般事故隐患</th>
						<th colspan="9">重大事故隐患</th>
						<th rowspan="3">整改资金</th>
					</tr>
					<tr>
						<th rowspan="2">应排查治理事故隐患的企业（单位）</th>
						<th rowspan="2">实排查治理事故隐患的企业（单位）</th>
						<th rowspan="2">覆盖率</th>
						<th rowspan="2">排查一般事故隐患</th>
						<th rowspan="2">其中：已整改（<font style="color:red;">*</font>）</th>
						<th rowspan="2">整改率</th>
						<th colspan="3">一级隐患</th>
						<th colspan="3">二级隐患</th>
						<th colspan="3">三级隐患</th>
					</tr>
					
					<tr>
						<th>排查隐患数</th>
						<th>其中：已整改（<font style="color:red;">*</font>）</th>
						<th>整改率</th>
						<th>排查隐患数</th>
						<th>其中：已整改（<font style="color:red;">*</font>）</th>
						<th>整改率</th>
						<th>排查隐患数</th>
						<th>其中：已整改（<font style="color:red;">*</font>）</th>
						<th>整改率</th>
					</tr>
					<tr>
						<th>（家）</th>
						<th>（家）</th>
						<th>（%）</th>
						<th>（项）</th>
						<th>（项）</th>
						<th>（%）</th>
						<th>（项）</th>
						<th>（项）</th>
						<th>（%）</th>
						<th>（项）</th>
						<th>（项）</th>
						<th>（%）</th>
						<th>（项）</th>
						<th>（项）</th>
						<th>（%）</th>
						<th>（万元）</th>
					</tr>
					<tr>
						<th>1</th>
						<th>2</th>
						<th>3</th>
						<th>4</th>
						<th>5</th>
						<th>6</th>
						<th>7</th>
						<th>8</th>
						<th>9</th>
						<th>10</th>
						<th>11</th>
						<th>12</th>
						<th>13</th>
						<th>14</th>
						<th>15</th>
						<th>16</th>
					</tr>
					<s:iterator value="hyfls" id="tjyh" status="sta">
						<tr>
							<td><s:property value='#tjyh.hyfl'/></td>
							<td><s:property value='#tjyh.qyTotal'/></td>
							<td><s:property value='#tjyh.sbqy'/></td>
							<td><s:property value='#tjyh.fgl'/></td>
							<td><s:property value='#tjyh.jb01'/></td>
							<td><s:property value='#tjyh.jb0101'/></td>
							<td><s:property value='#tjyh.zgl01'/></td>
							<td><s:property value='#tjyh.jb02'/></td>
							<td><s:property value='#tjyh.jb0202'/></td>
							<td><s:property value='#tjyh.zgl02'/></td>
							<td><s:property value='#tjyh.jb03'/></td>
							<td><s:property value='#tjyh.jb0303'/></td>
							<td><s:property value='#tjyh.zgl03'/></td>
							<td><s:property value='#tjyh.jb04'/></td>
							<td><s:property value='#tjyh.jb0404'/></td>
							<td><s:property value='#tjyh.zgl04'/></td>
							<td><s:property value='#tjyh.zgzj'/></td>
						</tr>
					</s:iterator>
					<tr>
							<td>小计</td>
							<th>${hyfl.qyTotal}</th>
							<th>${hyfl.sbqy}</th>
							<th>${hyfl.fgl}</th>
							<th>${hyfl.jb01}</th>
							<th>${hyfl.jb0101}</th>
							<th>${hyfl.zgl01}</th>
							<th>${hyfl.jb02}</th>
							<th>${hyfl.jb0202}</th>
							<th>${hyfl.zgl02}</th>
							<th>${hyfl.jb03}</th>
							<th>${hyfl.jb0303}</th>
							<th>${hyfl.zgl03}</th>
							<th>${hyfl.jb04}</th>
							<th>${hyfl.jb0404}</th>
							<th>${hyfl.zgl04}</th>
							<th>${hyfl.zgzj}</th>
						</tr>
						
					<tr>
							<td>合计</td>
							<th colspan="16"><font style="font-family:'幼圆';font-size:15px">
								排查隐患总数：${hyfl.yhtotal}&nbsp;&nbsp;&nbsp;&nbsp;
								其中已整改：${hyfl.yzgTotal}&nbsp;&nbsp;&nbsp;&nbsp;
								整改率：${hyfl.totalZgl}</font>
								</th>
						</tr>
				</table>
			</div>
</body>
</html>
