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
	<form name="myform1" method="post" enctype="multipart/form-data" action="secProInsSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="secProIns.id" value="${secProIns.id}">
		<input type="hidden" name="secProIns.createTime" value="<fmt:formatDate type="both" value="${secProIns.createTime}" />">
		<input type="hidden" name="secProIns.updateTime" value="${secProIns.updateTime}">
		<input type="hidden" name="secProIns.createUserID" value="${secProIns.createUserID}">
		<input type="hidden" name="secProIns.updateUserID" value="${secProIns.updateUserID}">
		<input type="hidden" name="secProIns.deptId" value="${secProIns.deptId}">
		<input type="hidden" name="secProIns.delFlag" value="${secProIns.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">一线员工数</th>
					<td width="35%"><input name="secProIns.insuranceWorkerCount" style="width:60%" value="${secProIns.insuranceWorkerCount}" type="text" datatype="*1-127" errormsg='一线员工数必须是1到127位字符！' nullmsg='一线员工数不能为空！' sucmsg='一线员工数填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%"></th>
					<td width="35%"></td>
				</tr>
				<tr>
				  <td colspan="4" style="text-align:center"><strong>安全生产责任险</strong></td>
				</tr>
				<tr>
					<th width="15%">安全生产责任险参保人数</th>
					<td width="35%"><input name="secProIns.insurancePersonCount" style="width:60%" value="${secProIns.insurancePersonCount}" type="text" maxlength="127"></td>
					<th width="15%">安全生产责任险总保费</th>
					<td width="35%"><input name="secProIns.insuranceTotalFee" style="width:60%" value="${secProIns.insuranceTotalFee}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">安全生产责任险总保额</th>
					<td width="35%"><input name="secProIns.insuranceTotalInsured" style="width:60%" value="${secProIns.insuranceTotalInsured}" type="text" maxlength="127"></td>
					<th width="15%"></th>
					<td width="35%"></td>
				</tr>
				<tr>
				  <td colspan="4" style="text-align:center"><strong>雇主责任险</strong></td>
				</tr>
				<tr>
					<th width="15%">雇主责任险参保人数</th>
					<td width="35%"><input name="secProIns.insuranceEmployerCount" style="width:60%" value="${secProIns.insuranceEmployerCount}" type="text" maxlength="127"></td>
					<th width="15%">雇主责任险总保费</th>
					<td width="35%"><input name="secProIns.insuranceEmployerFee" style="width:60%" value="${secProIns.insuranceEmployerFee}" type="text" maxlength="127"></td>
				<tr>
					<th width="15%">雇主责任险总保额</th>
					<td width="35%"><input name="secProIns.insuranceEmployerInsured" style="width:60%" value="${secProIns.insuranceEmployerInsured}" type="text" maxlength="127"></td>
					<th width="15%"></th>
					<td width="35%"></td>
				</tr>
				<tr>
				<td colspan="4" style="text-align:center"><strong>公众责任险</strong></td>
				</tr>
				<tr>
					<th width="15%">公众责任险总保费</th>
					<td width="35%"><input name="secProIns.insurancePublicFee" style="width:60%" value="${secProIns.insurancePublicFee}" type="text" maxlength="127"></td>
					<th width="15%">公众责任险总保额</th>
					<td width="35%"><input name="secProIns.insurancePublicInsured" style="width:60%" value="${secProIns.insurancePublicInsured}" type="text" maxlength="127"></td>
				</tr>
				<tr>
				<td colspan="4" style="text-align:center"><strong>团体人身意外伤害险</strong></td>
				</tr>
				<tr>
					<th width="15%">团体人身意外伤害险总保费</th>
					<td width="35%"><input name="secProIns.insuranceTeamFee" style="width:60%" value="${secProIns.insuranceTeamFee}" type="text" maxlength="127"></td>
					<th width="15%">团体人身意外伤害险总保额</th>
					<td width="35%"><input name="secProIns.insuranceTeamInsured" style="width:60%" value="${secProIns.insuranceTeamInsured}" type="text" maxlength="127"></td>
				</tr>
				<tr>
				  <td colspan="4" style="text-align:center"><strong>其他</strong></td>
				</tr>
				<tr>
					<th width="15%">参保人数</th>
					<td width="35%"><input name="secProIns.otherEmployerCount" style="width:60%" value="${secProIns.otherEmployerCount}" type="text" maxlength="127"></td>
					<th width="15%">总保费</th>
					<td width="35%"><input name="secProIns.otherEmployerFee" style="width:60%" value="${secProIns.otherEmployerFee}" type="text" maxlength="127"></td>
				<tr>
					<th width="15%">总保额</th>
					<td width="35%"><input name="secProIns.otherEmployerInsured" style="width:60%" value="${secProIns.otherEmployerInsured}" type="text" maxlength="127"></td>
					<th width="15%"></th>
					<td width="35%"></td>
				</tr>
				<tr>
					<th width="15%">承保保险公司</th>
					<td width="35%"><input name="secProIns.insuranceCompnay" style="width:60%" value="${secProIns.insuranceCompnay}" type="text" maxlength="127"></td>
				   	<th width="15%">投保时间</th>
					<td width="35%"><input name="secProIns.insuranceTime" style="width:60%" value="<fmt:formatDate type='date' value='${secProIns.insuranceTime}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_secProIns');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
