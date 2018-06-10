<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">所属县区</th>
					<td width="35%" colspan="3">${diggingsinfo.county}</td>
				</tr>
				<tr>
					<th width="15%">主矿种</th>
					<td width="35%">${diggingsinfo.mainoreType}</td>
					<th width="15%">开采方式</th>
					<td width="35%">${diggingsinfo.exploitType}</td>
				</tr>
					<th width="15%">生产能力</th>
					<td width="35%" colspan="3">${diggingsinfo.produceAbility}</td>
				<tr>
				</tr>
				<tr>
					<th width="15%">投产日期</th>
					<td width="35%"><fmt:formatDate type="both" value="${diggingsinfo.produceStartdate}" /></td>
					<th width="15%">设计服务年限</th>
					<td width="35%">${diggingsinfo.designServeAgelimit}</td>
				</tr>
				<tr>
					<th width="15%">验收文号</th>
					<td width="35%">${diggingsinfo.checkNo}</td>
					<th width="15%">投产审查单位</th>
					<td width="35%">${diggingsinfo.checkUnit}</td>
				</tr>
				<tr></tr>
				<tr>
					<th width="15%">持证特业人数</th>
					<td width="35%">${diggingsinfo.certificateSum}</td>
					<th width="15%">注安工程师人数</th>
					<td width="35%">${diggingsinfo.engineerSum}</td>
				</tr>
				<tr></tr>
				<tr>
					<th width="15%">采矿许可证有效期开始日期</th>
					<td width="35%"><fmt:formatDate type="both" value="${diggingsinfo.exploitCertificateStartdate}" /></td>
					<th width="15%">采矿许可证有效期结束日期</th>
					<td width="35%"><fmt:formatDate type="both" value="${diggingsinfo.exploitCertificateEnddate}" /></td>
				</tr>
				<tr>
					<th width="15%">采矿许可证号</th>
					<td width="35%">${diggingsinfo.exploitCertificateNo}</td>
					<th width="15%">采矿权登记机关</th>
					<td width="35%">${diggingsinfo.exploitCertificateUnit}</td>
				</tr>
				<tr>
					<th width="15%">安全生产许可证有效期开始日期</th>
					<td width="35%"><fmt:formatDate type="both" value="${diggingsinfo.safeCertificateStartdate}" /></td>
					<th width="15%">安全生产许可证有效期结束日期</th>
					<td width="35%"><fmt:formatDate type="both" value="${diggingsinfo.safeCertificateEnddate}" /></td>
				</tr>
				<tr>
					<th width="15%">安全生产许可证号</th>
					<td width="35%">${diggingsinfo.safeCertificateNo}</td>
				</tr>
				
				
				<c:if test="${ifzsqy==0}">
					<tr>
						<th width="15%" style="color:red"></th>
						<td width="100%" colspan="4">
							<font style="color:red">县级审核:</font>
							<c:if test="${xjshState==1}">
								&nbsp;&nbsp;&nbsp;通过
							</c:if>
							<c:if test="${xjshState==2}">
								&nbsp;&nbsp;&nbsp;未通过
							</c:if>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font style="color:red">备注:</font>&nbsp;&nbsp;&nbsp;${xjBack}
						</td>
					</tr>
					</c:if>
					<tr>
						<th width="15%" style="color:red"></th>
						<td width="100%" colspan="4">
							<font style="color:red">市级审核:</font>
							<c:if test="${sjshState==1}">
								&nbsp;&nbsp;&nbsp;通过
							</c:if>
							<c:if test="${sjshState==2}">
								&nbsp;&nbsp;&nbsp;未通过
							</c:if>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<font style="color:red">备注:</font>&nbsp;&nbsp;&nbsp;${sjBack}
						</td>
					</tr>
				</tr>
				
				
				<tr>
					<td colspan="4" height="100px"  style="text-align: center;">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeLayer();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
