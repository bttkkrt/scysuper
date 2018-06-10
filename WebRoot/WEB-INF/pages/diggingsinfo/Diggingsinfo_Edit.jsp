<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				document.myform1.action="diggingsinfoSave.action";
				document.myform1.submit();
			}
		}
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="diggingsinfo.id" value="${diggingsinfo.id}">
		<input type="hidden" name="diggingsinfo.createTime" value="<fmt:formatDate type="both" value="${diggingsinfo.createTime}" />">
		<input type="hidden" name="diggingsinfo.updateTime" value="${diggingsinfo.updateTime}">
		<input type="hidden" name="diggingsinfo.createUserID" value="${diggingsinfo.createUserID}">
		<input type="hidden" name="diggingsinfo.updateUserID" value="${diggingsinfo.updateUserID}">
		<input type="hidden" name="diggingsinfo.deptId" value="${diggingsinfo.deptId}">
		<input type="hidden" name="diggingsinfo.delFlag" value="${diggingsinfo.delFlag}">
		
		<input type="hidden" name="diggingsinfo.state" value="${diggingsinfo.state}">
		<input type="hidden" name="diggingsinfo.shbs" value="${diggingsinfo.shbs}">
		
		<input name="diggingsinfo.ifzsqy" value="${diggingsinfo.ifzsqy}" type="hidden" >
		<input name="diggingsinfo.zsqytype" value="${diggingsinfo.zsqytype}" type="hidden" >
		<input name="diggingsinfo.dutyFlag" value="${diggingsinfo.dutyFlag}" type="hidden" >
		
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
				<tr>
					<td colspan="4" height="100px" style="text-align: center;">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeLayer();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
