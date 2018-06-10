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
			<font style='color:red'>*填写总经理信息</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="entChaPerSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="entChaPer.id" value="${entChaPer.id}">
		<input type="hidden" name="entChaPer.createTime" value="<fmt:formatDate type="both" value="${entChaPer.createTime}" />">
		<input type="hidden" name="entChaPer.updateTime" value="${entChaPer.updateTime}">
		<input type="hidden" name="entChaPer.createUserID" value="${entChaPer.createUserID}">
		<input type="hidden" name="entChaPer.updateUserID" value="${entChaPer.updateUserID}">
		<input type="hidden" name="entChaPer.deptId" value="${entChaPer.deptId}">
		<input type="hidden" name="entChaPer.delFlag" value="${entChaPer.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="entChaPer.chargeName" value="${entChaPer.chargeName}" type="text" datatype="*1-127"  errormsg='姓名必须是1到127位字符！' nullmsg='姓名不能为空！' sucmsg='姓名填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">性别</th>
					<td width="35%"><cus:SelectOneTag property="entChaPer.chargeSex" defaultText='请选择' codeName="性别" value="${entChaPer.chargeSex}" style="width:60%"/></td>
				</tr>
				<tr>
					<th width="15%">国籍</th>
					<td width="35%"><input name="entChaPer.chargeNationnality" value="${entChaPer.chargeNationnality}" type="text" maxlength="127" style="width:60%"></td>
					<th width="15%">最高学历</th>
					<td width="35%"><cus:SelectOneTag property="entChaPer.chargeHighestSchool" defaultText='请选择' codeName="学历" value="${entChaPer.chargeHighestSchool}" style="width:60%"/></td>
				</tr>
				<tr>
					<th width="15%">最高学位</th>
					<td width="35%"><cus:SelectOneTag property="entChaPer.chargeHighestDegree" defaultText='请选择' codeName="学位" value="${entChaPer.chargeHighestDegree}" style="width:60%"/></td>
					<th width="15%">毕业院校</th>
					<td width="35%"><input name="entChaPer.chargeSchool" value="${entChaPer.chargeSchool}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">专业</th>
					<td width="35%"><input name="entChaPer.chargeSpecialized" value="${entChaPer.chargeSpecialized}" type="text" maxlength="127" style="width:60%"></td>
					<th width="15%">职称</th>
					<td width="35%"><input name="entChaPer.chargeTitle" value="${entChaPer.chargeTitle}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">主要负责人安全生产资格证号</th>
					<td width="35%"><input name="entChaPer.chargeSpecializedNum" value="${entChaPer.chargeSpecializedNum}" type="text" datatype="*1-127"  errormsg='主要负责人安全生产资格证号必须是1到127位字符！' nullmsg='主要负责人安全生产资格证号不能为空！' sucmsg='主要负责人安全生产资格证号填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">进入本单位日期</th>
					<td width="35%"><input name="entChaPer.chargeDutyDate" value="<fmt:formatDate type='both'  pattern="yyyy-MM-dd" value='${entChaPer.chargeDutyDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">从事本岗位时间</th>
					<td width="35%"><input name="entChaPer.chargePostDate" value="<fmt:formatDate type='both'  pattern="yyyy-MM-dd" value='${entChaPer.chargePostDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:60%"></td>
					<th width="15%">联系电话1</th>
					<td width="35%"><input name="entChaPer.chargePhone" value="${entChaPer.chargePhone}" type="text" datatype="m"   nullmsg='联系电话1不能为空！' sucmsg='联系电话1填写正确！' maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">联系电话2</th>
					<td width="35%"><input name="entChaPer.chargePhone2" value="${entChaPer.chargePhone2}" type="text" maxlength="127" datatype="m" ignore="ignore" style="width:60%"></td>
					<th width="15%">电子邮箱</th>
					<td width="35%"><input name="entChaPer.chargeEmail"  value="${entChaPer.chargeEmail}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">住址</th>
					<td width="85%" colspan="3"><input style="width:96%" name="entChaPer.chargeAddress" value="${entChaPer.chargeAddress}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3"><textarea name="entChaPer.chargeRemark" style="width:96%;height:60px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${entChaPer.chargeRemark}</textarea></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_entChaPer');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
