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
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${speJobPer.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${speJobPer.companyName}</td>
				</tr>
				<tr>
					<th width="15%">姓名</th>
					<td width="35%" >${speJobPer.specialName}</td>
					<th width="15%">性别</th>
					<td width="35%" ><cus:hxlabel codeName="性别" itemValue="${speJobPer.specialSex}" /></td>
				</tr>
				<tr>
					<th width="15%">国籍</th>
					<td width="35%" >${speJobPer.specialNationnality}</td>
					<th width="15%">最高学历</th>
					<td width="35%" ><cus:hxlabel codeName="学历" itemValue="${speJobPer.specialHighestSchool}" /></td>
				</tr>
				<tr>
					<th width="15%">最高学位</th>
					<td width="35%" ><cus:hxlabel codeName="学位" itemValue="${speJobPer.specialHighestDegree}" /></td>
					<th width="15%">毕业院校</th>
					<td width="35%" >${speJobPer.specialSchool}</td>
				</tr>
				<tr>
					<th width="15%">专业</th>
					<td width="35%" >${speJobPer.specialSpecialized}</td>
					<th width="15%">职称</th>
					<td width="35%" >${speJobPer.specialTitle}</td>
				</tr>
				<tr>
					<th width="15%">电子邮箱</th>
					<td width="35%">${speJobPer.specialEmail}</td>
					<th width="15%">进入本单位日期</th>
					<td width="35%" ><fmt:formatDate type="both"  pattern="yyyy-MM-dd" value="${speJobPer.specialDutyDate}" /></td>
				</tr>
				<tr>
					<th width="15%">从事本岗位时间</th>
					<td width="35%" ><fmt:formatDate type="both"  pattern="yyyy-MM-dd" value="${speJobPer.specialPostDate}" /></td>
					<th width="15%">特种作业类型</th>
					<td width="35%"><cus:hxlabel codeName="特种作业类型" itemValue="${speJobPer.specialJobType}" /></td>
				</tr>
				<tr>
					<th width="15%">特种作业证号</th>
					<td width="35%">${speJobPer.specialJobCradnum}</td>
					<th width="15%">培训单位</th>
					<td width="35%">${speJobPer.specialTrainingUnit}</td>
				</tr>
				<tr>
					<th width="15%">发证机关</th>
					<td width="35%">${speJobPer.specialCardInstitution}</td>
					<th width="15%">初领日期</th>
					<td width="35%"><fmt:formatDate type="both"   pattern="yyyy-MM-dd" value="${speJobPer.specialFirstPickDate}" /></td>
				</tr>
				<tr>
					<th width="15%">复审日期</th>
					<td width="35%"><fmt:formatDate type="both"   pattern="yyyy-MM-dd" value="${speJobPer.specialVerificationDate}" /></td>
					<th width="15%">联系电话1</th>
					<td width="35%" >${speJobPer.specialPhone}</td>
				</tr>
				<tr>
					<th width="15%">联系电话2</th>
					<td width="35%" >${speJobPer.specialPhone2}</td>
			 	</tr>
			 	
				<tr>
					<th width="15%">住址</th>
					<td width="96%" colspan="3" >${speJobPer.specialAddress}</td>
				</tr>
				<tr>
					
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea readOnly name="speJobPer.specialRemark" style="width:96%;height:60px">${speJobPer.specialRemark}</textarea></td>
				</tr>
				 
			</table>
		</div></div></div>
	</form>
</body>
</html>
