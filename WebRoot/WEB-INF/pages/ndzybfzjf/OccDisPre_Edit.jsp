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
	<form name="myform1" method="post" enctype="multipart/form-data" action="occDisPreSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="occDisPre.id" value="${occDisPre.id}">
		<input type="hidden" name="occDisPre.createTime" value="<fmt:formatDate type="both" value="${occDisPre.createTime}" />">
		<input type="hidden" name="occDisPre.updateTime" value="${occDisPre.updateTime}">
		<input type="hidden" name="occDisPre.createUserID" value="${occDisPre.createUserID}">
		<input type="hidden" name="occDisPre.updateUserID" value="${occDisPre.updateUserID}">
		<input type="hidden" name="occDisPre.deptId" value="${occDisPre.deptId}">
		<input type="hidden" name="occDisPre.delFlag" value="${occDisPre.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">经费</th>
					<td width="35%"><input name="occDisPre.attachment" style="width:60%" value="${occDisPre.attachment}" type="text" datatype="*1-127" errormsg='经费必须是1到127位字符！' nullmsg='经费不能为空！' sucmsg='经费填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">年度</th>
					<td width="35%"><input name="occDisPre.jshxYear" style="width:60%" value="${occDisPre.jshxYear}" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy'})" datatype="*1-127" errormsg='年度必须是1到127位字符！' nullmsg='年度不能为空！' sucmsg='年度填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">用途</th>
					<td width="85%" colspan="3"><textarea name="occDisPre.jshxUse" style="width:96%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${occDisPre.jshxUse}</textarea></td>
				</tr>
				<tr>
				<th width="15%">工作内容</th>
					<td width="85%" colspan="3"><textarea name="occDisPre.workContent" style="width:96%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${occDisPre.workContent}</textarea></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3"><textarea name="occDisPre.remark" style="width:96%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${occDisPre.remark}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_occDisPre');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
