<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript">
		function getMark(){
			return '-1';
		}
		function getChlidCode(){
			return "${comDanIde.mapkey}";
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
					<th width="15%">所在区域</th>
					<td width="35%" >${comDanIde.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${comDanIde.companyName}</td>
				</tr>
				<tr>
					<th width="15%">重点危险源名称</th>
					<td width="35%" >${comDanIde.dangerName}</td>
					<th width="15%">重点危险源类别</th>
					<td width="35%" >${comDanIde.dangerType}</td>
				</tr>
				<tr>
					<th width="15%">重点危险源级别</th>
					<td width="35%" >${comDanIde.dangerLevel}</td>
					<th width="15%">重点危险源地址</th>
					<td width="35%" >${comDanIde.dangerAddress}</td>
				</tr>
				<tr>
					<th width="15%">安全负责人</th>
					<td width="35%" >${comDanIde.safePerson}</td>
					<th width="15%">联系方式</th>
					<td width="35%" >${comDanIde.tele}</td>
				</tr>
				<tr>
					<th width="15%">巡查频率</th>
					<td width="35%" ><cus:hxlabel codeName="巡查频率" itemValue="${comDanIde.checkFrequency}" /></td>
					<th width="15%">巡查人员姓名</th>
					<td width="35%" >${comDanIde.checkPeopleName}</td>
				</tr>
				<tr>
					<th width="15%">要求</th>
					<td width="35%" colspan="3"><textarea readOnly name="comDanIde.remark" style="width:96%;height:60px">${comDanIde.remark}</textarea></td>
				</tr>
				
				<tr>
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="4">
						<div style="width:100%;">
				        	<iframe id="map" name="map" src="${ctx}/webResources/Map/index.html"  style="height:500px;width:100%; border:hidden; "scrolling="no" ></iframe>

				    	</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_comDanIde');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
