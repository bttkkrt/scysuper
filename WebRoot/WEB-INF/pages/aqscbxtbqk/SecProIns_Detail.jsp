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
			      <th width="15%">所在区域</th>
				  <td width="35%" >${secProIns.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${secProIns.companyName}</td>
			  </tr>
			  <tr>
					<th width="15%">企业总人数</th>
					<td width="35%" >${secProIns.insuranceEnterprisePersons}</td>
					<th width="15%">一线员工数</th>
					<td width="35%" >${secProIns.insuranceWorkerCount}</td>
			  </tr>
			  <tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>安全生产责任险</strong></td>
				</tr>
			  <tr>		
					<th width="15%">安全生产责任险参保人数</th>
					<td width="35%" >${secProIns.insurancePersonCount}</td>
				
					<th width="15%">安全生产责任险总保费</th>
					<td width="35%" >${secProIns.insuranceTotalFee}</td>
				</tr>
				<tr>
					<th width="15%">安全生产责任险总保额</th>
					<td width="35%" colspan="3">${secProIns.insuranceTotalInsured}</td>
				</tr>
				
				 <tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>雇主责任险</strong></td>
				</tr>
				<tr>
					<th width="15%">雇主责任险参保人数</th>
					<td width="35%" >${secProIns.insuranceEmployerCount}</td>
					<th width="15%">雇主责任险总保费</th>
					<td width="35%" >${secProIns.insuranceEmployerFee}</td>
				</tr>
				<tr>
					<th width="15%">雇主责任险总保额</th>
					<td width="35%" colspan="3">${secProIns.insuranceEmployerInsured}</td>
				</tr>
				 <tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>公众责任险</strong></td>
				</tr>
					<th width="15%">公众责任险总保费</th>
					<td width="35%" >${secProIns.insurancePublicFee}</td>
					<th width="15%">公众责任险总保额</th>
					<td width="35%" >${secProIns.insurancePublicInsured}</td>
				</tr>
				<tr>
				<td width="15%" colspan="4" style="text-align:center"><strong>团体人身意外伤害险</strong></td>
				</tr>
				<tr>
					<th width="15%">团体人身意外伤害险总保费</th>
					<td width="35%" >${secProIns.insuranceTeamFee}</td>
					<th width="15%">团体人身意外伤害险总保额</th>
					<td width="35%" >${secProIns.insuranceTeamInsured}</td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>其他</strong></td>
				</tr>
				<tr>
					<th width="15%">参保人数</th>
					<td width="35%">${secProIns.otherEmployerCount}</td>
					<th width="15%">总保费</th>
					<td width="35%">${secProIns.otherEmployerFee}</td>
				<tr>
					<th width="15%">总保额</th>
					<td width="35%" colspan="3">${secProIns.otherEmployerInsured}</td>
				</tr>
				<tr>
					<th width="15%">承保保险公司</th>
					<td width="35%" >${secProIns.insuranceCompnay}</td>
				  <th width="15%">投保时间</th>
				  <td width="35%" ><fmt:formatDate type="date" value="${secProIns.insuranceTime}" pattern="yyyy-MM-dd"/></td>	
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_secProIns');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
