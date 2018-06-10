<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>按危害因素</title>
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
        	document.myform.action = "${ctx}/jsp/zycsjcry/whysListExport.action";
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
				<th>危害因素名称</th>
				<th>涉及的企业数</th>
				<th>接触危害的人数</th>
			</tr>
			<tr>
				<td>合计</td>
				<td>${whys.qyzs}</td>
				<td>${whys.jcrs}</td>
			</tr>
			<c:forEach var="item" items="${whysList}">
				<tr>
					<td>${item.whysmc}</td>
					<td>${item.qyzs}</td>
					<td>${item.jcrs}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
