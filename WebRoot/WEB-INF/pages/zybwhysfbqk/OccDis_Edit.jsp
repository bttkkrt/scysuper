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
	<form name="myform1" method="post" enctype="multipart/form-data" action="occDisSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="occDis.id" value="${occDis.id}">
		<input type="hidden" name="occDis.createTime" value="<fmt:formatDate type="both" value="${occDis.createTime}" />">
		<input type="hidden" name="occDis.updateTime" value="${occDis.updateTime}">
		<input type="hidden" name="occDis.createUserID" value="${occDis.createUserID}">
		<input type="hidden" name="occDis.updateUserID" value="${occDis.updateUserID}">
		<input type="hidden" name="occDis.deptId" value="${occDis.deptId}">
		<input type="hidden" name="occDis.delFlag" value="${occDis.delFlag}">
		<input type="hidden" name="occDis.proId" value="${occDis.proId}">
		<input type="hidden" name="occHazBas.id" value="${occHazBas.id}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">作业场所名称</th>
					<td width="35%"><input name="occDis.workPlace" style="width:60%"  datatype="*1-127" value="${occDis.workPlace}" errormsg='作业场所名称必须是1到127位字符！' nullmsg='作业场所名称不能为空！' sucmsg='作业场所名称填写正确！'  type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">接触人数</th>
					<td width="35%"><input name="occDis.contactNum"  style="width:60%"  value="${occDis.contactNum}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_occDis');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
