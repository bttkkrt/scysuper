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
			<font style='color:red'>*填写分管安全生产的副总信息</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="secProChaSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="secProCha.id" value="${secProCha.id}">
		<input type="hidden" name="secProCha.createTime" value="<fmt:formatDate type="both" value="${secProCha.createTime}" />">
		<input type="hidden" name="secProCha.updateTime" value="${secProCha.updateTime}">
		<input type="hidden" name="secProCha.createUserID" value="${secProCha.createUserID}">
		<input type="hidden" name="secProCha.updateUserID" value="${secProCha.updateUserID}">
		<input type="hidden" name="secProCha.deptId" value="${secProCha.deptId}">
		<input type="hidden" name="secProCha.delFlag" value="${secProCha.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="secProCha.chargerName" style="width:60%;" value="${secProCha.chargerName}" type="text" datatype="*1-127" errormsg='姓名必须是1到127位字符！' nullmsg='姓名不能为空！' sucmsg='姓名填写正确！' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">性别</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="secProCha.chargerSex" defaultText='请选择' codeName="性别" value="${secProCha.chargerSex}" /></td>
				</tr>
				<tr>
					<th width="15%">国籍</th>
					<td width="35%"><input name="secProCha.chargerNationnality" style="width:60%;" value="${secProCha.chargerNationnality}" type="text" maxlength="127"></td>
					<th width="15%">最高学历</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="secProCha.chargerHighestSchool" defaultText='请选择' codeName="学历" value="${secProCha.chargerHighestSchool}" /></td>
				</tr>
				<tr>
					<th width="15%">最高学位</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="secProCha.chargerHighestDegree" defaultText='请选择' codeName="学位" value="${secProCha.chargerHighestDegree}" /></td>
					<th width="15%">毕业院校</th>
					<td width="35%"><input name="secProCha.chargerSchool" style="width:60%;" value="${secProCha.chargerSchool}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">专业</th>
					<td width="35%"><input name="secProCha.chargerSpecialized" style="width:60%;" value="${secProCha.chargerSpecialized}" type="text" maxlength="127"></td>
					<th width="15%">职称</th>
					<td width="35%"><input name="secProCha.chargerTitle" style="width:60%;" value="${secProCha.chargerTitle}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">安全生产管理员资格证号</th>
					<td width="35%"><input name="secProCha.chargerSpecializedNum" style="width:60%;" value="${secProCha.chargerSpecializedNum}" type="text" datatype="*1-127"  errormsg='安全生产管理员资格证号必须是1到127位字符！' nullmsg='安全生产管理员资格证号不能为空！' sucmsg='安全生产管理员资格证号填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">进入本单位日期</th>
					<td width="35%"><input name="secProCha.chargerDutyDate" style="width:60%;" value="<fmt:formatDate pattern="yyyy-MM-dd" type='both' value='${secProCha.chargerDutyDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">从事本岗位时间</th>
					<td width="35%"><input name="secProCha.chargerPostDate" style="width:60%;" value="<fmt:formatDate pattern="yyyy-MM-dd" type='both' value='${secProCha.chargerPostDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">联系电话1</th>
					<td width="35%"><input name="secProCha.chargerPhone" style="width:60%;" value="${secProCha.chargerPhone}" type="text" datatype="m"   nullmsg='联系电话1不能为空！' sucmsg='联系电话1填写正确！' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">联系电话2</th>
					<td width="35%"><input name="secProCha.chargerPhone2" style="width:60%;" value="${secProCha.chargerPhone2}" type="text" maxlength="127" datatype="m" ignore="ignore"></td>
					<th width="15%">电子邮箱</th>
					<td width="35%"><input name="secProCha.chargerEmail" style="width:60%;" value="${secProCha.chargerEmail}" type="text" maxlength="127"></td>
					
				</tr>
				<tr>
					<th width="15%">住址</th>
					<td width="96%" colspan="3"><input   style="width:96%" name="secProCha.chargerAddress" value="${secProCha.chargerAddress}" type="text" maxlength="127"></td>
				
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="secProCha.chargerRemark" style="width:96%;height:60px">${secProCha.chargerRemark}</textarea></td>
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
