<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: auto;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="lawBasisSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="lawBasis.id" value="${lawBasis.id}">
		<input type="hidden" name="lawBasis.createTime" value="<fmt:formatDate type="both" value="${lawBasis.createTime}" />">
		<input type="hidden" name="lawBasis.updateTime" value="${lawBasis.updateTime}">
		<input type="hidden" name="lawBasis.createUserID" value="${lawBasis.createUserID}">
		<input type="hidden" name="lawBasis.updateUserID" value="${lawBasis.updateUserID}">
		<input type="hidden" name="lawBasis.deptId" value="${lawBasis.deptId}">
		<input type="hidden" name="lawBasis.delFlag" value="${lawBasis.delFlag}">
		<input type="hidden" name="lawBasis.lawId" value="${lawBasis.lawId}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">条款项</th>
					<td width="35%" colspan="3"><input name="lawBasis.lawProvision" value="${lawBasis.lawProvision}" type="text" datatype="*1-127" errormsg='条款项必须是1到127位字符！' nullmsg='条款项不能为空！' sucmsg='条款项填写正确！'  maxlength="127" style="width:80%;"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">内容</th>
					<td width="35%" colspan="3"><textarea name="lawBasis.lawContent" style="width:80%;height:120px" datatype="*1-2000" errormsg='内容必须是1到2000位字符！' nullmsg='内容不能为空！' sucmsg='内容填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${lawBasis.lawContent}</textarea><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea name="lawBasis.remark" style="width:80%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${lawBasis.remark}</textarea></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_lawBasis');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
