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
			<font style='color:red'>*填写企业专门负责安全生产的与副总同一级别的人员信息</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="secProMajSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="secProMaj.id" value="${secProMaj.id}">
		<input type="hidden" name="secProMaj.createTime" value="<fmt:formatDate type="both" value="${secProMaj.createTime}" />">
		<input type="hidden" name="secProMaj.updateTime" value="${secProMaj.updateTime}">
		<input type="hidden" name="secProMaj.createUserID" value="${secProMaj.createUserID}">
		<input type="hidden" name="secProMaj.updateUserID" value="${secProMaj.updateUserID}">
		<input type="hidden" name="secProMaj.deptId" value="${secProMaj.deptId}">
		<input type="hidden" name="secProMaj.delFlag" value="${secProMaj.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="secProMaj.majordomoName" style="width:60%;" value="${secProMaj.majordomoName}" type="text" datatype="*1-127" errormsg='姓名必须是1到127位字符！' nullmsg='姓名不能为空！' sucmsg='姓名填写正确！' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">性别</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="secProMaj.majordomoSex" defaultText='请选择' codeName="性别" value="${secProMaj.majordomoSex}" /></td>
				</tr>
				<tr>
					<th width="15%">国籍</th>
					<td width="35%"><input name="secProMaj.majordomoNationnality" style="width:60%;" value="${secProMaj.majordomoNationnality}" type="text" maxlength="127"></td>
					<th width="15%">最高学历</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="secProMaj.majordomoHighestSchool" defaultText='请选择' codeName="学历" value="${secProMaj.majordomoHighestSchool}" /></td>
				</tr>
				<tr>
					<th width="15%">最高学位</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="secProMaj.majordomoHighestDegree" defaultText='请选择' codeName="学位" value="${secProMaj.majordomoHighestDegree}" /></td>
					<th width="15%">毕业院校</th>
					<td width="35%"><input name="secProMaj.majordomoSchool" style="width:60%;" value="${secProMaj.majordomoSchool}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">专业</th>
					<td width="35%"><input name="secProMaj.majordomoSpecialized" style="width:60%;" value="${secProMaj.majordomoSpecialized}" type="text" maxlength="127"></td>
					<th width="15%">职称</th>
					<td width="35%"><input name="secProMaj.majordomoTitle" style="width:60%;" value="${secProMaj.majordomoTitle}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">安全生产管理员资格证号</th>
					<td width="35%"><input name="secProMaj.majordomoSpecializedNum" style="width:60%;" value="${secProMaj.majordomoSpecializedNum}" type="text" datatype="*1-127"  errormsg='安全生产管理员资格证号必须是1到127位字符！' nullmsg='安全生产管理员资格证号不能为空！' sucmsg='安全生产管理员资格证号填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">进入本单位日期</th>
					<td width="35%"><input name="secProMaj.majordomoDutyDate" style="width:60%;" value="<fmt:formatDate  pattern="yyyy-MM-dd" type='both' value='${secProMaj.majordomoDutyDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">从事本岗位时间</th>
					<td width="35%"><input name="secProMaj.majordomoPostDate" style="width:60%;" value="<fmt:formatDate  pattern="yyyy-MM-dd" type='both' value='${secProMaj.majordomoPostDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">联系电话1</th>
					<td width="35%"><input name="secProMaj.majordomoPhone" style="width:60%;" value="${secProMaj.majordomoPhone}" type="text" datatype="m"   nullmsg='联系电话1不能为空！' sucmsg='联系电话1填写正确！' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">联系电话2</th>
					<td width="35%"><input name="secProMaj.majordomoPhone2" style="width:60%;" value="${secProMaj.majordomoPhone2}" type="text"  datatype="m" ignore="ignore"></td>
					<th width="15%">电子邮箱</th>
					<td width="35%"><input name="secProMaj.majordomoEmail" style="width:60%;" value="${secProMaj.majordomoEmail}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">住址</th>
					<td width="96%" colspan="3"><input style="width:96%" name="secProMaj.majordomoAddress" value="${secProMaj.majordomoAddress}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea name="secProMaj.majordomoRemark" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:96%;height:60px">${secProMaj.majordomoRemark}</textarea></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_secProMaj');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
