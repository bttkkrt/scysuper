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
	<form name="myform1" method="post" enctype="multipart/form-data" action="saveUploadZjlb.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="caseZj.id" value="${caseZj.id}">
		<input type="hidden" name="caseZj.caseId" value="${caseZj.caseId}">
		<input type="hidden" name="caseZj.createTime" value="<fmt:formatDate type="both" value="${caseZj.createTime}" />">
		<input type="hidden" name="caseZj.updateTime" value="${caseZj.updateTime}">
		<input type="hidden" name="caseZj.createUserID" value="${caseZj.createUserID}">
		<input type="hidden" name="caseZj.updateUserID" value="${caseZj.updateUserID}">
		<input type="hidden" name="caseZj.deptId" value="${caseZj.deptId}">
		<input type="hidden" name="caseZj.delFlag" value="${caseZj.delFlag}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">证据内容</th>
					<td width="85%" colspan="3">
						<textarea name="caseZj.zjContent" style="width:78%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" datatype="*1-2000"  nullmsg='证据内容不能为空！' sucmsg='证据内容输入正确！'>${caseZj.zjContent}</textarea><font style='color:red'>*</font>
				   	</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
