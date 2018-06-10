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
	<form name="myform1" method="post" enctype="multipart/form-data" action="scoAssLisSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="scoAssLis.id" value="${scoAssLis.id}">
		<input type="hidden" name="scoAssLis.createTime" value="<fmt:formatDate type="both" value="${scoAssLis.createTime}" />">
		<input type="hidden" name="scoAssLis.updateTime" value="${scoAssLis.updateTime}">
		<input type="hidden" name="scoAssLis.createUserID" value="${scoAssLis.createUserID}">
		<input type="hidden" name="scoAssLis.updateUserID" value="${scoAssLis.updateUserID}">
		<input type="hidden" name="scoAssLis.deptId" value="${scoAssLis.deptId}">
		<input type="hidden" name="scoAssLis.delFlag" value="${scoAssLis.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">考评类目</th>
					<td width="35%"><cus:SelectOneTag property="scoAssLis.evaluationCategory" defaultText='请选择' codeName="考评类目" value="${scoAssLis.evaluationCategory}" /></td>
					<th width="15%">实际得分</th>
					<td width="35%"><input name="scoAssLis.actualScore" value="${scoAssLis.actualScore}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">标准分值</th>
					<td width="35%"><input name="scoAssLis.score" value="${scoAssLis.score}" type="text" maxlength="127"></td>
					<th width="15%">评分方式</th>
					<td width="35%"><cus:SelectOneTag property="scoAssLis.grading" defaultText='请选择' codeName="评分方式" value="${scoAssLis.grading}" /></td>
				</tr>
				<tr>
					<th width="15%">扣分</th>
					<td width="35%"><input name="scoAssLis.mark" value="${scoAssLis.mark}" type="text" maxlength="127"></td>
					<th width="15%">标准条款</th>
					<td width="35%"><input name="scoAssLis.provision" value="${scoAssLis.provision}" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_scoAssLis');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
