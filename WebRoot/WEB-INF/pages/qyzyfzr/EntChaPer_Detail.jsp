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
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${entChaPer.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${entChaPer.companyName}</td>
				</tr>
				<tr>
					<th width="15%">姓名</th>
					<td width="35%" >${entChaPer.chargeName}</td>
					<th width="15%">性别</th>
					<td width="35%" ><cus:hxlabel codeName="性别" itemValue="${entChaPer.chargeSex}" /></td>
				</tr>
				<tr>
					<th width="15%">国籍</th>
					<td width="35%" >${entChaPer.chargeNationnality}</td>
					<th width="15%">最高学历</th>
					<td width="35%" ><cus:hxlabel codeName="学历" itemValue="${entChaPer.chargeHighestSchool}" /></td>
				</tr>
				<tr>
					<th width="15%">最高学位</th>
					<td width="35%" ><cus:hxlabel codeName="学位" itemValue="${entChaPer.chargeHighestDegree}" /></td>
					<th width="15%">毕业院校</th>
					<td width="35%" >${entChaPer.chargeSchool}</td>
				</tr>
				<tr>
					<th width="15%">专业</th>
					<td width="35%" >${entChaPer.chargeSpecialized}</td>
					<th width="15%">职称</th>
					<td width="35%" >${entChaPer.chargeTitle}</td>
				</tr>
				<tr>
					<th width="15%">主要负责人安全生产资格证号</th>
					<td width="35%" >${entChaPer.chargeSpecializedNum}</td>
					<th width="15%">进入本单位日期</th>
					<td width="35%" ><fmt:formatDate type="both"  pattern="yyyy-MM-dd" value="${entChaPer.chargeDutyDate}" /></td>
				</tr>
				<tr>
					<th width="15%">从事本岗位时间</th>
					<td width="35%" ><fmt:formatDate type="both"  pattern="yyyy-MM-dd" value="${entChaPer.chargePostDate}" /></td>
					<th width="15%">联系电话1</th>
					<td width="35%" >${entChaPer.chargePhone}</td>
				</tr>
				<tr>
					<th width="15%">联系电话2</th>
					<td width="35%" >${entChaPer.chargePhone2}</td>
					<th width="15%">电子邮箱</th>
					<td width="35%" >${entChaPer.chargeEmail}</td>
				</tr>
				<tr>	
					<th width="15%">住址</th>
					<td width="85%" colspan="3" >${entChaPer.chargeAddress}</td>
				</tr>
				<tr>
					
					<th width="15%">备注</th>
					<td width="85%" colspan="3"  ><textarea readOnly name="entChaPer.chargeRemark" style="width:96%;height:60px">${entChaPer.chargeRemark}</textarea></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_entChaPer');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
