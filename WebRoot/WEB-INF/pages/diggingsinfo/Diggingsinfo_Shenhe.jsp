<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>信息审核</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	 function save(){
			var state = document.getElementById('state').value;
			var remark = document.getElementById('shbs').value;
			if(state == "1" && remark == "")
			{
				alert("不合格必须注明理由！");
			}
			else
			{
				document.myform1.submit();
			}
		}
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data" action="${ctx}/jsp/diggingsinfo/diggingsinfoShenheSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="diggingsinfo.id" value="${diggingsinfo.id}">
		<input type="hidden" name="diggingsinfo.createTime" value="<fmt:formatDate type="both" value="${diggingsinfo.createTime}" />">
		<input type="hidden" name="diggingsinfo.updateTime" value="${diggingsinfo.updateTime}">
		<input type="hidden" name="diggingsinfo.createUserID" value="${diggingsinfo.createUserID}">
		<input type="hidden" name="diggingsinfo.updateUserID" value="${diggingsinfo.updateUserID}">
		<input type="hidden" name="diggingsinfo.deptId" value="${diggingsinfo.deptId}">
		<input type="hidden" name="diggingsinfo.delFlag" value="${diggingsinfo.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所属县区</th>
					<td width="35%" colspan="3"><input name="diggingsinfo.county" value="${diggingsinfo.county}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">主矿种</th>
					<td width="35%"><input name="diggingsinfo.mainoreType" value="${diggingsinfo.mainoreType}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
					<th width="15%">开采方式</th>
					<td width="35%"><input name="diggingsinfo.exploitType" value="${diggingsinfo.exploitType}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">生产能力</th>
					<td width="35%" colspan="3"><input name="diggingsinfo.produceAbility" value="${diggingsinfo.produceAbility}" style="width: 450px" type="text" dataType="Require" msg="此项为必填" maxlength="150"><font color="red">*</font></td>
				
				</tr>
				<tr>
					<th width="15%">投产日期</th>
					<td width="35%"><input name="diggingsinfo.produceStartdate" value="<fmt:formatDate type='both' value='${diggingsinfo.produceStartdate}' />" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font color="red">*</font></td>
					<th width="15%">设计服务年限</th>
					<td width="35%"><input name="diggingsinfo.designServeAgelimit" value="${diggingsinfo.designServeAgelimit}" type="text" dataType="Require" msg="此项为必填" maxlength="10"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">验收文号</th>
					<td width="35%"><input name="diggingsinfo.checkNo" value="${diggingsinfo.checkNo}" type="text" dataType="Require" msg="此项为必填" maxlength="100"><font color="red">*</font></td>
					<th width="15%">投产审查单位</th>
					<td width="35%"><input name="diggingsinfo.checkUnit" value="${diggingsinfo.checkUnit}" type="text" dataType="Require" msg="此项为必填" maxlength="100"><font color="red">*</font></td>
				</tr>
				<tr></tr>
				<tr>
					<th width="15%">持证特业人数</th>
					<td width="35%"><input name="diggingsinfo.certificateSum" value="${diggingsinfo.certificateSum}" type="text" maxlength="15"></td>
					<th width="15%">注安工程师人数</th>
					<td width="35%"><input name="diggingsinfo.engineerSum" value="${diggingsinfo.engineerSum}" type="text" maxlength="15"></td>
				</tr>
				<tr></tr>
				<tr>
					<th width="15%">采矿许可证有效期开始日期</th>
					<td width="35%"><input name="diggingsinfo.exploitCertificateStartdate" value="<fmt:formatDate type='both' value='${diggingsinfo.exploitCertificateStartdate}' />" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font color="red">*</font></td>
					<th width="15%">采矿许可证有效期结束日期</th>
					<td width="35%"><input name="diggingsinfo.exploitCertificateEnddate" value="<fmt:formatDate type='both' value='${diggingsinfo.exploitCertificateEnddate}' />" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">采矿许可证号</th>
					<td width="35%"><input name="diggingsinfo.exploitCertificateNo" value="${diggingsinfo.exploitCertificateNo}" type="text" dataType="Require" msg="此项为必填" maxlength="200"><font color="red">*</font></td>
					<th width="15%">采矿权登记机关</th>
					<td width="35%"><input name="diggingsinfo.exploitCertificateUnit" value="${diggingsinfo.exploitCertificateUnit}" type="text" maxlength="200"></td>
				</tr>
				<tr>
					<th width="15%">安全生产许可证有效期开始日期</th>
					<td width="35%"><input name="diggingsinfo.safeCertificateStartdate" value="<fmt:formatDate type='both' value='${diggingsinfo.safeCertificateStartdate}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">安全生产许可证有效期结束日期</th>
					<td width="35%"><input name="diggingsinfo.safeCertificateEnddate" value="<fmt:formatDate type='both' value='${diggingsinfo.safeCertificateEnddate}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">安全生产许可证号</th>
					<td width="35%" colspan="3"><input name="diggingsinfo.safeCertificateNo" value="${diggingsinfo.safeCertificateNo}" style="width: 450px" type="text" maxlength="200"></td>
				</tr>
				
				<s:if test="diggingsinfo.state!=1||diggingsinfo.dutyFlag!=1" >
					<c:if test="${deptCodeLenth==6}">
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
							<font style="color:red">市级审核:</font><cus:SelectOneTag property="diggingsinfo.state" defaultText='请选择' codeName="审核结果"  dataType="Require" msg="此项为必选" /><font color="color:red">*</font>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font style="color:red">备注:</font><input id="shbs"
								name="diggingsinfo.shbs" 
								style="width: 50%">
							</input><font style="color:red">*(若未通过，请填写原由。)</font>
						</td>
					</tr>
					</c:if>
					
					<c:if test="${ifzsqy==0}">
						<c:if test="${deptCodeLenth==9}">
					<tr>
						<th width="15%" style="color:red"></th>
						<td width="100%" colspan="4">
							<font style="color:red">县级审核:</font><cus:SelectOneTag property="diggingsinfo.state" defaultText='请选择' codeName="审核结果"  dataType="Require" msg="此项为必选" /><font color="color:red">*</font>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font style="color:red">备注:</font><input id="shbs"
								name="diggingsinfo.shbs"  
								style="width: 50%">
							</input><font style="color:red">*(若未通过，请填写原由。)</font>
						</td>
					</tr>
					
					</c:if>
					</c:if>
				
				</s:if>
				
				
				<!-- 
				<tr>
					<th width="15%">审核结果</th>
					<td width="35%" colspan="3">
						<s:select id="state" name="diggingsinfo.state" list="#{'0':'通过','1':'不通过'}" theme="simple"/>
					</td>
				</tr>
				<tr>
					<th width="15%">审核备注</th>
					<td width="35%" colspan="3">
						<textarea id="shbs" name="diggingsinfo.shbs" style="width:100%;height:120px">${diggingsinfo.shbs}</textarea>
					</td>
				</tr>
				 -->
				 
				<tr>
					<td colspan="4" height="100px" style="text-align: center;">
						<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">确定</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeLayer();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
