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
	<form name="myform1" method="post" enctype="multipart/form-data" action="secProManSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="secProMan.id" value="${secProMan.id}">
		<input type="hidden" name="secProMan.createTime" value="<fmt:formatDate type="both" value="${secProMan.createTime}" />">
		<input type="hidden" name="secProMan.updateTime" value="${secProMan.updateTime}">
		<input type="hidden" name="secProMan.createUserID" value="${secProMan.createUserID}">
		<input type="hidden" name="secProMan.updateUserID" value="${secProMan.updateUserID}">
		<input type="hidden" name="secProMan.deptId" value="${secProMan.deptId}">
		<input type="hidden" name="secProMan.delFlag" value="${secProMan.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="secProMan.managerName" style="width:60%;" value="${secProMan.managerName}" type="text" datatype="*1-127" errormsg='姓名必须是1到127位字符！' nullmsg='姓名不能为空！' sucmsg='姓名填写正确！' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">性别</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="secProMan.managerSex" defaultText='请选择' codeName="性别" value="${secProMan.managerSex}" /></td>
				</tr>
				<tr>
					<th width="15%">国籍</th>
					<td width="35%"><input name="secProMan.managerNationnality" style="width:60%;" value="${secProMan.managerNationnality}" type="text" maxlength="127"></td>
					<th width="15%">最高学历</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="secProMan.managerHighestSchool" defaultText='请选择' codeName="学历" value="${secProMan.managerHighestSchool}" /></td>
				</tr>
				<tr>
					<th width="15%">最高学位</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="secProMan.managerHighestDegree" defaultText='请选择' codeName="学位" value="${secProMan.managerHighestDegree}" /></td>
					<th width="15%">毕业院校</th>
					<td width="35%"><input name="secProMan.managerSchool" style="width:60%;" value="${secProMan.managerSchool}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">专业</th>
					<td width="35%"><input name="secProMan.managerSpecialized" style="width:60%;" value="${secProMan.managerSpecialized}" type="text" maxlength="127"></td>
					<th width="15%">职称</th>
					<td width="35%"><input name="secProMan.managerTitle" style="width:60%;" value="${secProMan.managerTitle}" type="text"   maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">安全生产管理员资格证号</th>
					<td width="35%"><input name="secProMan.managerSpecializedNum" style="width:60%;" value="${secProMan.managerSpecializedNum}" type="text" datatype="*1-127"  errormsg='安全生产管理员资格证号必须是1到127位字符！' nullmsg='安全生产管理员资格证号不能为空！' sucmsg='安全生产管理员资格证号填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">进入本单位日期</th>
					<td width="35%"><input name="secProMan.managerDutyDate" style="width:60%;" value="<fmt:formatDate type='both'  pattern="yyyy-MM-dd" value='${secProMan.managerDutyDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">从事本岗位时间</th>
					<td width="35%"><input name="secProMan.managerPostDate" style="width:60%;" value="<fmt:formatDate type='both'  pattern="yyyy-MM-dd" value='${secProMan.managerPostDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">联系电话1</th>
					<td width="35%"><input name="secProMan.managerPhone" style="width:60%;" value="${secProMan.managerPhone}" type="text" datatype="m"   nullmsg='联系电话1不能为空！' sucmsg='联系电话1填写正确！' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">联系电话2</th>
					<td width="35%"><input name="secProMan.managerPhone2" style="width:60%;" value="${secProMan.managerPhone2}" type="text" datatype="m" ignore="ignore"></td>
					<th width="15%">电子邮箱</th>
					<td width="35%"><input name="secProMan.managerEmail"   style="width:60%;" value="${secProMan.managerEmail}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">住址</th>
					<td width="96%" colspan="3"><input style="width:96%" name="secProMan.managerAddress" value="${secProMan.managerAddress}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea name="secProMan.managerRemark" style="width:96%;height:60px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${secProMan.managerRemark}</textarea></td>
				</tr>
				<tr>
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
