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
				 <tr >
					<td colspan="4" style="text-align:center">基本信息</td>
				</tr>
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${occHeaInfo.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${occHeaInfo.companyName}</td>
				</tr>
				<tr>
					<th width="15%">女职工人数</th>
					<td width="35%" >${occHeaInfo.femaleWorkersNumber}</td>
					<th width="15%">接触职业病危害因素人数</th>
					<td width="35%" >${occHeaInfo.occupationalDiseasersNumber}</td>
				</tr>
				<tr>
					<th width="15%">企业职业病危害类别</th>
					<td width="35%" ><cus:hxlabel codeName="企业职业病危害类别" itemValue="${occHeaInfo.companyHazardCategory}" /></td>
					<th width="15%">职业病危害行业类别</th>
					<td width="35%" ><cus:hxlabel codeName="职业病危害行业类别" itemValue="${occHeaInfo.hazardIndustryCategory}" /></td>
				</tr>
				<tr>
					<th width="15%">接触职业病危害因素女工人数</th>
					<td width="35%" >${occHeaInfo.femaleWorkersDiseasesNumber}</td>
					<th width="15%">职业病危害岗位数</th>
					<td width="35%" >${occHeaInfo.occupationDiseasePosts}</td>
				</tr>
				<tr   >
					<td colspan="4" style="text-align:center">职业卫生分管负责人</td>
				</tr>
				<tr>
					<th width="15%">姓名</th>
					<td width="35%">${occHeaInfo.zywsfgfzrName}</td>
					<th width="15%">职务</th>
					<td width="35%">${occHeaInfo.zywsfgfzrDuty}</td>
				</tr>
				<tr>
					<th width="15%">办公电话</th>
					<td width="35%">${occHeaInfo.zywsfgfzrTelephone}</td>
					<th width="15%">手机</th>
					<td width="35%">${occHeaInfo.zywsfgfzrMobile}</td>
				</tr>
				<tr>
					<th width="15%">学历</th>
					<td width="35%"><cus:hxlabel    codeName="学历" itemValue="${occHeaInfo.zywsfgfzrEducation}" /></td>
					<th width="15%">专业</th>
					<td width="35%">${occHeaInfo.zywsfgfzrProfession}</td>
				</tr>
				<tr>
					<th width="15%">培训日期</th>
					<td width="35%"><fmt:formatDate pattern="yyyy-MM-dd"  type='both' value='${occHeaInfo.zywsfgfzrTrainingDateStart}' />-<fmt:formatDate pattern="yyyy-MM-dd"  type='both' value='${occHeaInfo.zywsfgfzrTrainingDateEnd}' /></td>
					<th width="15%">培训合格证号</th>
					<td width="35%">${occHeaInfo.zywsfgfzrTrainingNo}</td>
				</tr>
				
				<tr  >
					<td colspan="4" style="text-align:center">职业卫生管理机构负责人</td>
				</tr>
				<tr>
					<th width="15%">姓名</th>
					<td width="35%">${occHeaInfo.zywsgljgfzrName}</td>
					<th width="15%">职务</th>
					<td width="35%">${occHeaInfo.zywsgljgfzrDuty}</td>
				</tr>
				<tr>
					<th width="15%">办公电话</th>
					<td width="35%">${occHeaInfo.zywsgljgfzrTelephone}</td>
					<th width="15%">手机</th>
					<td width="35%">${occHeaInfo.zywsgljgfzrMobile}</td>
				</tr>
				<tr>
					<th width="15%">学历</th>
					<td width="35%"><cus:hxlabel    codeName="学历" itemValue="${occHeaInfo.zywsgljgfzrEducation}" /></td>
					<th width="15%">专业</th>
					<td width="35%">${occHeaInfo.zywsgljgfzrProfession}</td>
				</tr>
				<tr>
					<th width="15%">培训日期</th>
					<td width="35%"><fmt:formatDate pattern="yyyy-MM-dd" type="date"  value='${occHeaInfo.zywsgljgfzrTrainingDateStart}' />-<fmt:formatDate pattern="yyyy-MM-dd" type="date"  value='${occHeaInfo.zywsgljgfzrTrainingDateEnd}' /></td>
					<th width="15%">培训合格证号</th>
					<td width="35%">${occHeaInfo.zywsgljgfzrTrainingNo}</td>
				</tr>
				
				
				<tr  >
			   	 <td  colspan="4" style="text-align:center">职业卫生管理人员</td>
			    </tr>
			    
			    
			    <tr>
					<td colspan="4">
						<div id="more">
						<table>
							<tr>
						    	<td  style="text-align:center">序号</td>
								<td  style="text-align:center">姓名<font style="color:red">*</font></td>
								<td  style="text-align:center">职务</td>
								<td  style="text-align:center">办公电话</td>
								<td  style="text-align:center">手机</td>
								<td  style="text-align:center">学历</td>
								<td  style="text-align:center">专业</td>
								<td  style="text-align:center">培训日期</td>
								<td  style="text-align:center">培训合格证号</td>
							</tr>	
							<c:forEach var="occChaInf" items="${occChaInfs}"  varStatus="status">
								<tr style="text-align: center" id="${occChaInf.id}">
								
									<td style='text-align:center'>${status.index+1}</td>
									<td style='text-align:center'> ${occChaInf.jshxName} </td>
									<td style='text-align:center'> ${occChaInf.duty} </td>
									<td style='text-align:center'> ${occChaInf.telephone} </td>
									<td style='text-align:center'> ${occChaInf.mobile} </td>
									<td style='text-align:center'><cus:hxlabel    codeName="学历" itemValue="${occChaInf.degreeEducation}" /></td>
									<td style='text-align:center'> ${occChaInf.professional} </td>
									<td style='text-align:center'> <fmt:formatDate type='date' pattern="yyyy-MM-dd"  value='${occChaInf.trainingDateStart}' /> 
										- <fmt:formatDate type='date' pattern="yyyy-MM-dd"  value='${occChaInf.trainingDateEnd}' /> 
									</td>
									<td style='text-align:center'> ${occChaInf.trainingCertificatNumber} </td>
								</tr>
							</c:forEach>
						</table>
						</div>
					</td>
				</tr>
				
			</table>
		</div></div></div>
	</form>
</body>
</html>
