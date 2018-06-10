<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	 //查看计划关联任务
        function taskList(row_Id){
        	var url =  "jsp/wxyxcrwgl/danTasManList.action?ids="+row_Id
	        window.parent.addTab("win_taskList","查看任务列表",url);
        }
   </script>
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" >${danTasMan.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${danTasMan.companyName}</td>
				</tr>
				<tr>
					<th width="15%">巡查单号</th>
					<td width="35%">${danTasMan.chenkNo}</td>
					<th width="15%">任务名称</th>
					<td width="35%" >${danTasMan.taskName}</td>
				</tr>
				<tr>
					<th width="15%">巡检点名称</th>
					<td width="35%" >${danTasMan.checkName}</td>
					<th width="15%">巡查人员姓名</th>
					<td width="35%" >${danTasMan.checkPeopleName}</td>
				</tr>
				<tr>
					<th width="15%">巡查开始时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${danTasMan.checkTime}" /></td>
					<th width="15%">巡查结束时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${danTasMan.checkTimeEnd}" /></td>
				</tr>
				
				<tr>
					<th width="15%">巡查结果</th>
					<td width="35%">${danTasMan.result}</td>
					<th width="15%">实际巡查时间</th>
					<td width="35%" >${danTasMan.reportTime}</td>
				</tr>
				<tr>
					<th width="15%">实际巡查项</th>
					<td width="85%" colspan="3">${danTasMan.realCheckTerm}</td>
					
				</tr>
				<tr>
					<th width="15%">巡查反馈备注</th>
					<td width="85%"  colspan="3">${danTasMan.remark}</td>
				</tr>
				
				
			</table>
		</div></div></div>
	</form>
</body>
</html>
