<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>按行政区域</title>
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
    <%@include file="/common/jsLib.jsp"%>
    <script> 
        function exportData()
        {
        	document.myform.action = "${ctx}/jsp/zycsjcry/xzqyListExport.action";
        	document.myform.submit();
        }
        
    </script>
</head>

<body>
<form name="myform" method="post">
<input type="hidden" name="flag" value="1"/>
	<div class="submitdata">
		<table width="100%">
			<tr>
				<td style="text-align:center;"><h1>
				<a href="###" class="easyui-linkbutton" onclick="exportData()" iconCls="icon-add">导出</a>&nbsp;	
				</td>
			</tr>
		</table>
	</div>
</form>
	<div style="clear:both;background:#f5f8f9;border:1px solid #e8eff2;margin:6px 0 0 0">
		<table style="border-collapse:collapse;border:1px solid #d5dbdc;width:100%">
			<tr>
				<th rowspan="2">区域</th>
				<th rowspan="2">企业总数</th>
				<th rowspan="2">劳动者总人数</th>
				<th rowspan="2">职业病累计人数</th>
				<th colspan="6">接触职业病危害人数</th>
			</tr>
			<tr>
				<th>总人数</th>
				<th>粉尘类</th>
				<th>化学性</th>
				<th>物理性</th>
				<th>放射性</th>
				<th>其他类</th>
			</tr>
			<tr>
				<td>合计</td>
				<td>${xzqy.qyzs}</td>
				<td>${xzqy.ldzzs}</td>
				<td>${xzqy.zybrs}</td>
				<td>${xzqy.jcrs}</td>
				<td>${xzqy.fcrs}</td>
				<td>${xzqy.hxrs}</td>
				<td>${xzqy.wlrs}</td>
				<td>${xzqy.fsrs}</td>
				<td>${xzqy.qtrs}</td>
			</tr>
			<c:forEach var="item" items="${xzqyList}">
				<tr>
					<td>${item.qy}</td>
					<td>${item.qyzs}</td>
					<td>${item.ldzzs}</td>
					<td>${item.zybrs}</td>
					<td>${item.jcrs}</td>
					<td>${item.fcrs}</td>
					<td>${item.hxrs}</td>
					<td>${item.wlrs}</td>
					<td>${item.fsrs}</td>
					<td>${item.qtrs}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
