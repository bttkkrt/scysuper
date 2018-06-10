<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="/common/jsLib.jsp"%>
</head>

<body validform="true">
	<div class="page_dialog">
		<form name="myform1" method="post" enctype="multipart/form-data" action="loginImageSave.action">
			<s:token />
			<input type="hidden" name="flag" value="${flag}">
			<input type="hidden" name="loginImage.id" value="${loginImage.id}">
			<input type="hidden" name="loginImage.createTime" value="<fmt:formatDate type="both" value="${loginImage.createTime}" />">
			<input type="hidden" name="loginImage.updateTime" value="${loginImage.updateTime}">
			<input type="hidden" name="loginImage.createUserID" value="${loginImage.createUserID}">
			<input type="hidden" name="loginImage.updateUserID" value="${loginImage.updateUserID}">
			<input type="hidden" name="loginImage.deptId" value="${loginImage.deptId}">
			<input type="hidden" name="loginImage.delFlag" value="${loginImage.delFlag}">
			
			<div class="inner6px">
				<div class="cell">
					<table width="100%">
						<tr>
							<th width="15%">图片名</th>
							<td width="35%"><input name="loginImage.imageName" value="${loginImage.imageName}" type="text" maxlength="128" datatype="s1-50"></td>
						</tr>
						<tr>
							<th width="15%">上传图片</th>
							<td width="35%"><input type="file" name="file" class="input_text" datatype="*" nullmsg="请选择需要上传的图片！"/></td>
						</tr>
						<tr>
							<th width="15%">使用状态</th>
							<td width="35%">
								<select id="isUsing" name="loginImage.isUsing" style="width: 136px;">
									<option value="">---请选择---</option>
									<option value="0"
										<c:if test="${loginImage.isUsing==0}">selected</c:if>>
										不启用
									</option>
									<option value="1"
										<c:if test="${loginImage.isUsing==1}">selected</c:if>>
										启用
									</option>
								</select>
							</td>						
						</tr>
						<tr>
							<td colspan="2">
								<div class="btn_area_setc">
									<s:if test="flag=='add'">
										<a href="#" class="btn_01" type="submit">添加<b></b>
										</a>
									</s:if>
									<s:else>
										<a href="#" class="btn_01" type="submit">更新<b></b>
										</a>
									</s:else>
									<a href="#" class="btn_01"
										onclick="parent.close_win('win_loginImage');">关闭<b></b>
									</a>
								</div>
							</td>						
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>
</body>
</html>