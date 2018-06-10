<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="secProChaPerSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="secProChaPer.id" value="${secProChaPer.id}">
		<input type="hidden" name="secProChaPer.createTime" value="<fmt:formatDate type="both" value="${secProChaPer.createTime}" />">
		<input type="hidden" name="secProChaPer.updateTime" value="${secProChaPer.updateTime}">
		<input type="hidden" name="secProChaPer.createUserID" value="${secProChaPer.createUserID}">
		<input type="hidden" name="secProChaPer.updateUserID" value="${secProChaPer.updateUserID}">
		<input type="hidden" name="secProChaPer.deptId" value="${secProChaPer.deptId}">
		<input type="hidden" name="secProChaPer.delFlag" value="${secProChaPer.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="secProChaPer.chargeName" style="width:60%;" value="${secProChaPer.chargeName}" type="text" datatype="*1-127" errormsg='姓名必须是1到127位字符！' nullmsg='姓名不能为空！' sucmsg='姓名填写正确！' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">性别</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="secProChaPer.chargeSex" defaultText='请选择' codeName="性别" value="${secProChaPer.chargeSex}" /></td>
				</tr>
				<tr>
					<th width="15%">国籍</th>
					<td width="35%"><input name="secProChaPer.chargeNationnality" style="width:60%;" value="${secProChaPer.chargeNationnality}" type="text" maxlength="127"></td>
					<th width="15%">最高学历</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="secProChaPer.chargeHighestSchool" defaultText='请选择' codeName="学历" value="${secProChaPer.chargeHighestSchool}" /></td>
				</tr>
				<tr>
					<th width="15%">最高学位</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="secProChaPer.chargeHighestDegree" defaultText='请选择' codeName="学位" value="${secProChaPer.chargeHighestDegree}" /></td>
					<th width="15%">毕业院校</th>
					<td width="35%"><input name="secProChaPer.chargeSchool" style="width:60%;" value="${secProChaPer.chargeSchool}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">专业</th>
					<td width="35%"><input name="secProChaPer.chargeSpecialized" style="width:60%;" value="${secProChaPer.chargeSpecialized}" type="text" maxlength="127"></td>
					<th width="15%">职称</th>
					<td width="35%"><input name="secProChaPer.chargeTitle" style="width:60%;" value="${secProChaPer.chargeTitle}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">安全生产管理员资格证号</th>
					<td width="35%"><input name="secProChaPer.chargeSpecializedNum" style="width:60%;" value="${secProChaPer.chargeSpecializedNum}" type="text" datatype="*1-127"  errormsg='安全生产管理员资格证号必须是1到127位字符！' nullmsg='安全生产管理员资格证号不能为空！' sucmsg='安全生产管理员资格证号填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">进入本单位日期</th>
					<td width="35%"><input name="secProChaPer.chargeDutyDate" style="width:60%;" value="<fmt:formatDate type='both'  pattern="yyyy-MM-dd" value='${secProChaPer.chargeDutyDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">从事本岗位时间</th>
					<td width="35%"><input name="secProChaPer.chargePostDate" style="width:60%;" value="<fmt:formatDate type='both'  pattern="yyyy-MM-dd" value='${secProChaPer.chargePostDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">联系电话1</th>
					<td width="35%"><input name="secProChaPer.chargePhone" style="width:60%;" value="${secProChaPer.chargePhone}" type="text" datatype="m"   nullmsg='联系电话1不能为空！' sucmsg='联系电话1填写正确！' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">联系电话2</th>
					<td width="35%"><input name="secProChaPer.chargePhone2"  style="width:60%;" value="${secProChaPer.chargePhone2}" type="text"  datatype="m" ignore="ignore"></td>
					<th width="15%">电子邮箱</th>
					<td width="35%"><input name="secProChaPer.chargeEmail" style="width:60%;" value="${secProChaPer.chargeEmail}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">住址</th>
					<td width="96%" colspan="3"><input style="width:96%" name="secProChaPer.chargeAddress" value="${secProChaPer.chargeAddress}" type="text" maxlength="127"></td>
				
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea name="secProChaPer.chargeRemark" style="width:96%;height:60px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${secProChaPer.chargeRemark}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
