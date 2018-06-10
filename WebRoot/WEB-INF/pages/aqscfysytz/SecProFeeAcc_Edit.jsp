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
	<form name="myform1" method="post" enctype="multipart/form-data" action="secProFeeAccSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="secProFeeAcc.id" value="${secProFeeAcc.id}">
		<input type="hidden" name="secProFeeAcc.createTime" value="<fmt:formatDate type="both" value="${secProFeeAcc.createTime}" />">
		<input type="hidden" name="secProFeeAcc.updateTime" value="${secProFeeAcc.updateTime}">
		<input type="hidden" name="secProFeeAcc.createUserID" value="${secProFeeAcc.createUserID}">
		<input type="hidden" name="secProFeeAcc.updateUserID" value="${secProFeeAcc.updateUserID}">
		<input type="hidden" name="secProFeeAcc.deptId" value="${secProFeeAcc.deptId}">
		<input type="hidden" name="secProFeeAcc.delFlag" value="${secProFeeAcc.delFlag}">
		
			<table width="100%" border="0">
				
				<tr>
					<th width="15%">项目</th>
					<td width="35%"><cus:SelectOneTag property="secProFeeAcc.feeAccountProject" style="width:60%" defaultText='请选择' codeName="台账项目" value="${secProFeeAcc.feeAccountProject}" datatype="*"  nullmsg='项目不能为空！'/><font style='color:red'>*</font></td>
					<th width="15%">支出金额</th>
					<td width="35%"><input name="secProFeeAcc.feeAccountAmount" style="width:60%" value="${secProFeeAcc.feeAccountAmount}" type="text" datatype="*1-10" errormsg='支出金额必须是1到10位字符！' nullmsg='支出金额不能为空！' sucmsg='支出金额填写正确！'  maxlength="10"><font style='color:red'>*</font></td>
				</tr>
				<tr>	
					<th width="15%">使用月份</th>
					<td width="35%"><input name="secProFeeAcc.feeAccountMonth" style="width:60%" value="<fmt:formatDate type='date' value='${secProFeeAcc.feeAccountMonth}' pattern="yyyy-MM" />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="secProFeeAcc.feeAccountRemark" style="width:96%;height:60px">${secProFeeAcc.feeAccountRemark}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_secProFeeAcc');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
