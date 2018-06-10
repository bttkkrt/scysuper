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
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="speJobPerSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="speJobPer.id" value="${speJobPer.id}">
		<input type="hidden" name="speJobPer.createTime" value="<fmt:formatDate type="both" value="${speJobPer.createTime}" />">
		<input type="hidden" name="speJobPer.updateTime" value="${speJobPer.updateTime}">
		<input type="hidden" name="speJobPer.createUserID" value="${speJobPer.createUserID}">
		<input type="hidden" name="speJobPer.updateUserID" value="${speJobPer.updateUserID}">
		<input type="hidden" name="speJobPer.deptId" value="${speJobPer.deptId}">
		<input type="hidden" name="speJobPer.delFlag" value="${speJobPer.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="speJobPer.specialName" style="width:60%;" value="${speJobPer.specialName}" type="text" datatype="*1-127" errormsg='姓名必须是1到127位字符！' nullmsg='姓名不能为空！' sucmsg='姓名填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">性别</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="speJobPer.specialSex" defaultText='请选择' codeName="性别" value="${speJobPer.specialSex}" /></td>
				</tr>
				<tr>
					<th width="15%">国籍</th>
					<td width="35%"><input name="speJobPer.specialNationnality" style="width:60%;" value="${speJobPer.specialNationnality}" type="text" maxlength="127"></td>
					<th width="15%">最高学历</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="speJobPer.specialHighestSchool" defaultText='请选择' codeName="学历" value="${speJobPer.specialHighestSchool}" /></td>
				</tr>
				<tr>
					<th width="15%">最高学位</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="speJobPer.specialHighestDegree" defaultText='请选择' codeName="学位" value="${speJobPer.specialHighestDegree}" /></td>
					<th width="15%">毕业院校</th>
					<td width="35%"><input name="speJobPer.specialSchool" style="width:60%;" value="${speJobPer.specialSchool}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">专业</th> 
					<td width="35%"><input name="speJobPer.specialSpecialized" style="width:60%;" value="${speJobPer.specialSpecialized}" type="text" maxlength="127"></td>
					<th width="15%">职称</th>
					<td width="35%"><input name="speJobPer.specialTitle" style="width:60%;" value="${speJobPer.specialTitle}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">电子邮箱</th>
					<td width="35%"><input name="speJobPer.specialEmail" style="width:60%;" value="${speJobPer.specialEmail}" type="text" maxlength="127"></td>
					<th width="15%">进入本单位日期</th>
					<td width="35%"><input name="speJobPer.specialDutyDate" style="width:60%;" value="<fmt:formatDate type='both'  pattern="yyyy-MM-dd" value='${speJobPer.specialDutyDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">从事本岗位时间</th>
					<td width="35%"><input name="speJobPer.specialPostDate" style="width:60%;" value="<fmt:formatDate type='both'  pattern="yyyy-MM-dd" value='${speJobPer.specialPostDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">特种作业类型</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="speJobPer.specialJobType" defaultText='请选择' codeName="特种作业类型" value="${speJobPer.specialJobType}" datatype="*1-127" errormsg='特种作业类型必须是1到127位字符！' nullmsg='特种作业类型不能为空！' sucmsg='特种作业类型填写正确！'/><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">特种作业证号</th>
					<td width="35%"><input name="speJobPer.specialJobCradnum" style="width:60%;" value="${speJobPer.specialJobCradnum}" type="text" datatype="*1-127"  errormsg='特种作业证号必须是1到127位字符！' nullmsg='特种作业证号不能为空！' sucmsg='特种作业证号填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">培训单位</th>
					<td width="35%"><input name="speJobPer.specialTrainingUnit" style="width:60%;" value="${speJobPer.specialTrainingUnit}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">发证机关</th>
					<td width="35%"><input name="speJobPer.specialCardInstitution" style="width:60%;" value="${speJobPer.specialCardInstitution}" type="text" maxlength="127"></td>
					<th width="15%">初领日期</th>
					<td width="35%"><input name="speJobPer.specialFirstPickDate" id="specialFirstPickDate" style="width:60%;" value="<fmt:formatDate type='both'   pattern="yyyy-MM-dd" value='${speJobPer.specialFirstPickDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'specialVerificationDate\')}'})"></td>
				</tr>
				<tr>
					<th width="15%">复审日期</th>
					<td width="35%"><input name="speJobPer.specialVerificationDate" id="specialVerificationDate"  style="width:60%;" value="<fmt:formatDate type='both'   pattern="yyyy-MM-dd" value='${speJobPer.specialVerificationDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'specialFirstPickDate\')}'})" datatype="*1-127" errormsg='复审日期必须是1到127位字符！' nullmsg='复审日期不能为空！' sucmsg='复审日期填写正确！'><font style='color:red'>*</font></td>
					<th width="15%">联系电话1</th>
					<td width="35%"><input name="speJobPer.specialPhone" style="width:60%;" value="${speJobPer.specialPhone}" type="text" datatype="m"   nullmsg='联系电话1不能为空！' sucmsg='联系电话1填写正确！' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">联系电话2</th>
					<td width="35%"><input name="speJobPer.specialPhone2" style="width:60%;" value="${speJobPer.specialPhone}" type="text" datatype="m" ignore="ignore"></td>
			 	</tr>
				<tr>
					<th width="15%">住址</th>
					<td width="96%" colspan="3"><input style="width:96%" name="speJobPer.specialAddress" value="${speJobPer.specialAddress}" type="text" maxlength="127"></td>
				
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea name="speJobPer.specialRemark" style="width:96%;height:60px"  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${speJobPer.specialRemark}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_speJobPer');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
