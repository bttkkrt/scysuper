<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${secProCha.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${secProCha.companyName}</td>
				</tr>
				<tr>
					<th width="15%">姓名</th>
					<td width="35%" >${secProCha.chargerName}</td>
					<th width="15%">性别</th>
					<td width="35%" ><cus:hxlabel codeName="性别" itemValue="${secProCha.chargerSex}" /></td>
				</tr>
				<tr>
					<th width="15%">国籍</th>
					<td width="35%" >${secProCha.chargerNationnality}</td>
					<th width="15%">最高学历</th>
					<td width="35%" ><cus:hxlabel codeName="学历" itemValue="${secProCha.chargerHighestSchool}" /></td>
				</tr>
				<tr>
					<th width="15%">最高学位</th>
					<td width="35%" ><cus:hxlabel codeName="学位" itemValue="${secProCha.chargerHighestDegree}" /></td>
					<th width="15%">毕业院校</th>
					<td width="35%" >${secProCha.chargerSchool}</td>
				</tr>
				<tr>
					<th width="15%">专业</th>
					<td width="35%" >${secProCha.chargerSpecialized}</td>
					<th width="15%">职称</th>
					<td width="35%" >${secProCha.chargerTitle}</td>
				</tr>
				<tr>	
					<th width="15%">安全生产管理员资格证号</th>
					<td width="35%" >${secProCha.chargerSpecializedNum}</td>
					<th width="15%">进入本单位日期</th>
					<td width="35%" ><fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${secProCha.chargerDutyDate}" /></td>
				</tr>
				<tr>
					<th width="15%">从事本岗位时间</th>
					<td width="35%" ><fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${secProCha.chargerPostDate}" /></td>
					<th width="15%">联系电话1</th>
					<td width="35%" >${secProCha.chargerPhone}</td>
				</tr>
				<tr>
					<th width="15%">联系电话2</th>
					<td width="35%" >${secProCha.chargerPhone2}</td>
					<th width="15%">电子邮箱</th>
					<td width="35%" >${secProCha.chargerEmail}</td>
				</tr> 
				<tr>
					<th width="15%">住址</th>
					<td width="96%" colspan="3">${secProCha.chargerAddress}</td>
				</tr>
				<tr>
					
					<th width="15%">备注</th>
					<td width="96%" colspan="3" ><textarea readOnly name="secProCha.chargerRemark" style="width:96%;height:60px">${secProCha.chargerRemark}</textarea></td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
