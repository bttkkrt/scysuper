<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
		function downloadWs(row_Id)
        {
        	document.myform.action = "${ctx}/jsp/jdjcrw/supTasXcjcExport.action?supTas.id="+row_Id;
        	document.myform.submit();
        }
	</script>
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">所属网格</th>
					<td width="35%" >${supTas.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${supTas.companyName}</td>
				</tr>
				<tr>
				    <th width="15%">任务名称</th>
					<td width="35%" >${supTas.taskName}</td>
					<th width="15%">任务编号</th>
					<td width="35%" >${supTas.taskNum}</td>
				</tr>
				<tr>
				    <th width="15%">任务类型</th>
					<td width="35%" >${supTas.taskType}</td>
					<th width="15%">任务状态</th>
					<td width="35%" >${supTas.taskState}</td>
				</tr>
				<tr>
					<th width="15%">承办人</th>
					<td width="35%" >${supTas.checkUsername}</td>
					<th width="15%">协办人</th>
					<td width="35%" >${supTas.xbUserName}</td>
				</tr>
				<tr>
					<th width="15%">检查项类型</th>
					<td width="85%" colspan="3">${supTas.checkItemName}</td>
					
				</tr>
				<tr>
					<th width="15%">任务开始时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${supTas.stime}" pattern="yyyy-MM-dd"/></td>
					<th width="15%">任务结束时间</th>
					<td width="35%" ><fmt:formatDate type="date" value="${supTas.ftime}" pattern="yyyy-MM-dd"/></td>
				</tr>
			<s:if test='supTas.taskState=="已完成"'>
				<tr>  
					<th width="15%">检查开始时间</th>
					<td width="35%"><fmt:formatDate type="date" value="${supTas.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<th width="15%">检查结束时间</th>
					<td width="35%"><fmt:formatDate type="date" value="${supTas.checkTimeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>	
				<tr>
				  	<th width="15%">巡查结果</th>
					<td colspan="3">
						<table>
							<tr>
								<th style="text-align: center;">巡查项</th>
								<th style="text-align: center;">结果</th>
								<th style="text-align: center;">备注</th>
							</tr>
							<c:forEach items="${supTasResultlist }" var="result" varStatus="vstatus">
							<tr>
								<td>${result.patMan.patrolName }</td>
								<td>${result.xcxResult }</td>
								<td>${result.remark}</td>
							</tr>
							</c:forEach>
							
						</table>
					</td>
                  
				</tr>
				
				<tr>  
				<th width="15%">巡查记录</th>
				  <td width="35%" colspan="3"><textarea readOnly name="supTas.checkRecord" style="width:96%;height:60px">${supTas.checkRecord}</textarea></td>
				</tr>
				<th width="15%">内容备注</th>
				  <td width="35%" colspan="3"><textarea readOnly name="supTas.remark" style="width:96%;height:60px">${supTas.remark}</textarea></td>
				</tr>
				</s:if>
				
				<c:if test="${fn:length(supTas.fileName) >0}">
				<tr>
					<th width="15%">现场检查记录下载</th>
					<td width="96%" colspan="3">
						<a href="#" onclick="downloadWs('${supTas.id}')" style="text-decoration:underline;">${supTas.fileName}</a>
					</td>
				</tr>
				</c:if>
			</table>
		</div></div></div>
	</form>
</body>
</html>
