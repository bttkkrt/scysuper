<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="safetyExpertsSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="safetyExperts.id" value="${safetyExperts.id}">
		<input type="hidden" name="safetyExperts.createTime" value="<fmt:formatDate type="both" value="${safetyExperts.createTime}" />">
		<input type="hidden" name="safetyExperts.updateTime" value="${safetyExperts.updateTime}">
		<input type="hidden" name="safetyExperts.createUserID" value="${safetyExperts.createUserID}">
		<input type="hidden" name="safetyExperts.updateUserID" value="${safetyExperts.updateUserID}">
		<input type="hidden" name="safetyExperts.deptId" value="${safetyExperts.deptId}">
		<input type="hidden" name="safetyExperts.delFlag" value="${safetyExperts.delFlag}">
		
			<table width="100%" border="0">
				<tr>
				<th width="15%">姓名</th>
					<td width="35%"><input name="safetyExperts.safetyName" value="${safetyExperts.safetyName}" type="text" datatype="*1-127" errormsg='姓名必须是1到127位字符！' nullmsg='姓名不能为空！' sucmsg='姓名填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
				<th width="15%">电子邮箱</th>
					<td width="35%"><input name="safetyExperts.email" value="${safetyExperts.email}" type="text" maxlength="127"  style="width:60%"></td>
				<tr>
					<th width="15%">性别</th>
					<td width="35%"><cus:SelectOneTag property="safetyExperts.sex" defaultText='请选择' codeName="性别" value="${safetyExperts.sex}"  style="width:60%"/></td>
					<th width="15%">出生年月</th>
					<td width="35%"><input name="safetyExperts.birth" value="<fmt:formatDate type='date' value='${safetyExperts.birth}'  pattern="yyyy-MM"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"  style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">所学专业</th>
					<td width="35%"><input name="safetyExperts.professional" value="${safetyExperts.professional}" type="text" datatype="*1-127" errormsg='所学专业必须是1到127位字符！' nullmsg='所学专业不能为空！' sucmsg='所学专业填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">学历</th>
					<td width="35%"><cus:SelectOneTag property="safetyExperts.education" defaultText='请选择' codeName="学历" value="${safetyExperts.education}" datatype="*1-127" errormsg='学历必须是1到127位字符！' nullmsg='学历不能为空！' sucmsg='学历填写正确！'  maxlength="127" style="width:60%"/><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">毕业时间</th>
					<td width="35%"><input name="safetyExperts.graduationTime" value="<fmt:formatDate type='date' value='${safetyExperts.graduationTime}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:60%"></td>
					<th width="15%">职称</th>
					<td width="35%"><input name="safetyExperts.jobTitle" value="${safetyExperts.jobTitle}" type="text" maxlength="127"  style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">工作单位</th>
					<td width="35%"><input name="safetyExperts.employer" value="${safetyExperts.employer}" type="text" maxlength="127"  style="width:60%"></td>
					<th width="15%">专长</th>
					<td width="35%"><input name="safetyExperts.specialty" value="${safetyExperts.specialty}" type="text" datatype="*1-127" errormsg='专长必须是1到127位字符！' nullmsg='专长不能为空！' sucmsg='专长填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">住址</th>
					<td width="35%"><input name="safetyExperts.address" value="${safetyExperts.address}" type="text" maxlength="127" style="width:60%"></td>
					<th width="15%">联系电话</th>
					<td width="35%"><input name="safetyExperts.mobile" value="${safetyExperts.mobile}" type="text" datatype="*1-127" errormsg='联系电话必须是1到127位字符！' nullmsg='联系电话不能为空！' sucmsg='联系电话填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">工作记录</th>
					<td width="35%" colspan="3"><textarea name="safetyExperts.workRecord" style="width:96%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${safetyExperts.workRecord}</textarea></td>
				</tr>
				<th width="15%">教育情况</th>
					<td width="35%" colspan="3"><textarea name="safetyExperts.educationSec" style="width:96%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${safetyExperts.educationSec}</textarea></td>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_safetyExperts');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
