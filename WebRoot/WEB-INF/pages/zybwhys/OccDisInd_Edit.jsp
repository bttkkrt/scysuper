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
	<form name="myform1" method="post" enctype="multipart/form-data" action="occDisIndSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="occDisInd.id" value="${occDisInd.id}" >
		<input type="hidden" name="occDisInd.createTime" value="<fmt:formatDate type="both" value="${occDisInd.createTime}" />">
		<input type="hidden" name="occDisInd.updateTime" value="${occDisInd.updateTime}">
		<input type="hidden" name="occDisInd.createUserID" value="${occDisInd.createUserID}">
		<input type="hidden" name="occDisInd.updateUserID" value="${occDisInd.updateUserID}">
		<input type="hidden" name="occDisInd.deptId" value="${occDisInd.deptId}">
		<input type="hidden" name="occDisInd.delFlag" value="${occDisInd.delFlag}">
		<input type="hidden" name="occDisInd.proId" value="${occDisInd.proId}">
		<input type="hidden" name="occDis.id" value="${occDis.id}">
		
			<table width="100%" border="0">
				<tr>
					<th width="18%">职业病危害因素名称</th>
					<td width="32%"><input name="occDisInd.occupationalDiseaseName" style="width: 60%" datatype="*1-127" errormsg='职业病危害因素名称必须是1到127位字符！' nullmsg='职业病危害因素名称不能为空！' sucmsg='职业病危害因素名称填写正确！' value="${occDisInd.occupationalDiseaseName}" type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="18%">现场浓度</th>
					<td width="32%"><input name="occDisInd.fieldConcentration" style="width: 60%" value="${occDisInd.fieldConcentration}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="18%">接触人数（可重复）</th>
					<td width="32%"><input name="occDisInd.contactNumber"  style="width: 60%" value="${occDisInd.contactNumber}" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_occDisInd');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
