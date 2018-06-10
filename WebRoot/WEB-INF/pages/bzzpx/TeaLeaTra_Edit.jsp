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
			<font style='color:red'>*需先在企业作业安全-生产车间管理内录入车间信息，此处车间才能选择</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="teaLeaTraSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="teaLeaTra.id" value="${teaLeaTra.id}">
		<input type="hidden" name="teaLeaTra.createTime" value="<fmt:formatDate type="both" value="${teaLeaTra.createTime}" />">
		<input type="hidden" name="teaLeaTra.updateTime" value="${teaLeaTra.updateTime}">
		<input type="hidden" name="teaLeaTra.createUserID" value="${teaLeaTra.createUserID}">
		<input type="hidden" name="teaLeaTra.updateUserID" value="${teaLeaTra.updateUserID}">
		<input type="hidden" name="teaLeaTra.deptId" value="${teaLeaTra.deptId}">
		<input type="hidden" name="teaLeaTra.delFlag" value="${teaLeaTra.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">车间名称</th>
					<td width="35%">
					<cus:SelectOneTag property="teaLeaTra.trainingWorkshopId" defaultText='请选择' style="width:60%" codeSql="select t.row_id,t.WORKSHOP_NAME from WORKSHOP t where t.delflag = 0 and t.COMPANY_ID='${teaLeaTra.companyId}'" value="${teaLeaTra.trainingWorkshopId}"  datatype="*1-127" errormsg='车间名称必须是1到127位字符！' nullmsg='车间名称不能为空！' sucmsg='车间名称填写正确！'  maxlength="127"/><font style='color:red'>*</font>
					</td>
					<th width="15%">姓名</th>
					<td width="35%"><input name="teaLeaTra.trainingName" value="${teaLeaTra.trainingName}" style="width:60%" type="text"  datatype="*1-127" errormsg='姓名必须是1到127位字符！' nullmsg='姓名不能为空！' sucmsg='姓名填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">性别</th>
					<td width="35%"><cus:SelectOneTag property="teaLeaTra.trainingSex" style="width:60%" defaultText='请选择' codeName="性别" value="${teaLeaTra.trainingSex}" /></td>
					<th width="15%">职位</th>
					<td width="35%"><input name="teaLeaTra.trainingPosition" style="width:60%" value="${teaLeaTra.trainingPosition}" type="text"  datatype="*1-127" errormsg='职位必须是1到127位字符！' nullmsg='职位不能为空！' sucmsg='职位填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">电话</th>
					<td width="35%"><input name="teaLeaTra.trainingPhone" style="width:60%" value="${teaLeaTra.trainingPhone}" type="text"   maxlength="127"></td>
					<th width="15%">授课人</th>
					<td width="35%"><input name="teaLeaTra.trainingTeacher" style="width:60%" value="${teaLeaTra.trainingTeacher}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">培训单位</th>
					<td width="35%"><input name="teaLeaTra.trainingAddress" style="width:60%" value="${teaLeaTra.trainingAddress}" type="text" maxlength="127"></td>
					<th width="15%">培训学时</th>
					<td width="35%"><input name="teaLeaTra.trainingTeacheTime" style="width:60%" value="${teaLeaTra.trainingTeacheTime}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">培训开始时间</th>
					<td width="35%"><input name="teaLeaTra.trainingTime" style="width:60%" id="trainingTime" value="<fmt:formatDate type='date' value='${teaLeaTra.trainingTime}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'trainingTimeEnd\')}'})" datatype="*1-127" errormsg='培训开始时间必须是1到127位字符！' nullmsg='培训开始时间不能为空！' sucmsg='培训开始时间填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">培训结束时间</th>
					<td width="35%"><input name="teaLeaTra.trainingTimeEnd" style="width:60%" id="trainingTimeEnd" value="<fmt:formatDate type='date' value='${teaLeaTra.trainingTimeEnd}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'trainingTime\')}'})" datatype="*1-127" errormsg='培训结束时间必须是1到127位字符！' nullmsg='培训结束时间不能为空！' sucmsg='培训结束时间填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">培训内容</th>
					<td width="35%" colspan="3"><textarea name="teaLeaTra.trainingContent" style="width:96%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${teaLeaTra.trainingContent}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_teaLeaTra');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
