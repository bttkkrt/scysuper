<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">计划名称</th>
					<td>${recPla.planName }</td>
				</tr>
				<tr>
					<th width="15%">整治开始时间</th>
					<td width="35%">
						<fmt:formatDate type="both"  pattern="yyyy-MM-dd"  value="${recPla.workTimeStart}" />
					</td>
					<th width="15%">整治结束时间</th>
					<td width="35%">
						<fmt:formatDate type="both" pattern="yyyy-MM-dd"  value="${recPla.workTimeEnd}" />
					</td>
				</tr>
				<tr>
					<th width="15%">工作目标</th>
					<td width="85%" colspan="3" ><textarea readOnly name="recPla.workGoal" style="width:96%;height:60px">${recPla.workGoal}</textarea></td>
					
				</tr>
				<tr>
					<th width="15%">工作内容</th>
					<td width="85%" colspan="3" ><textarea readOnly name="recPla.workContent" style="width:96%;height:60px">${recPla.workContent}</textarea></td>
					
				</tr>
				<tr>
					<th width="15%">具体措施</th>
					<td width="85%" colspan="3" ><textarea readOnly name="recPla.workMeasure" style="width:96%;height:60px">${recPla.workMeasure}</textarea></td>
					
				</tr>
				<tr>
					<th width="15%">实施步骤</th>
					<td width="85%" colspan="3" ><textarea readOnly name="recPla.workStep" style="width:96%;height:60px">${recPla.workStep}</textarea></td>
					
				</tr>
				<tr>
					<th width="15%">工作要求</th>
					<td width="85%" colspan="3" ><textarea readOnly name="recPla.workClaim" style="width:96%;height:60px">${recPla.workClaim}</textarea></td>
					
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_recPla');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
