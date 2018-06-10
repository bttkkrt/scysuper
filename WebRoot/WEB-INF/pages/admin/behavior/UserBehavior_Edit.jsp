<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/jsLib.jsp"%>
	</head>
	<body validform="true">
		<div class="page_dialog">
			<form name="myform1" method="post" enctype="multipart/form-data"
				action="userBehaviorSave.action">
				<s:token />
				<input type="hidden" name="flag" value="${flag}">
				<input type="hidden" name="userBehavior.id"
					value="${userBehavior.id}">
				<input type="hidden" name="userBehavior.createTime"
					value="<fmt:formatDate type='both' value='${userBehavior.createTime}' />">
				<input type="hidden" name="userBehavior.updateTime"
					value="${userBehavior.updateTime}">
				<input type="hidden" name="userBehavior.createUserID"
					value="${userBehavior.createUserID}">
				<input type="hidden" name="userBehavior.updateUserID"
					value="${userBehavior.updateUserID}">
				<input type="hidden" name="userBehavior.deptId"
					value="${userBehavior.deptId}">
				<input type="hidden" name="userBehavior.delFlag"
					value="${userBehavior.delFlag}">

				<div class="inner6px">
					<div class="cell">
						<table width="100%">
							<tr>
								<th width="15%">
									类别
									<font color="red">*</font>
								</th>
								<td width="35%">
									<cus:SelectOneTag property="userBehavior.behaviorType"
										defaultText="请选择" codeName="用户行为类别"
										value="${userBehavior.behaviorType}" dataType="*" />
								</td>
							</tr>
							<tr>
								<th width="15%">
									用户行为名称
									<font color="red">*</font>
								</th>
								<td width="35%">
									<input name="userBehavior.behaviorName" class="form_text" style="width: 80%"
										value="${userBehavior.behaviorName}" type="text"
										datatype="*1-50">
								</td>
							</tr>
							<tr>
								<th width="15%">
									用户行为地址
								</th>
								<td width="35%">
									<input name="userBehavior.behaviorUrl" class="form_text" style="width: 80%"
										value="${userBehavior.behaviorUrl}" type="text"
										datatype="*1-200" ignore="ignore">
								</td>
							</tr>
							<tr>
								<th width="15%">
									用户行为处理服务
								</th>
								<td width="35%">
									<input name="userBehavior.behaviorService" class="form_text" style="width: 80%"
										value="${userBehavior.behaviorService}" type="text"
										datatype="*1-50" ignore="ignore">
								</td>
							</tr>
							<tr>
								<th width="15%">
									用户行为默认日志
								</th>
								<td width="35%">
									<textarea name="userBehavior.defaultLog"
										style="width: 90%; height: 100px" datatype="*1-500"
										ignore="ignore">${userBehavior.defaultLog}</textarea>
								</td>
							</tr>
							<tr>
								<th width="15%">
									是否到指定的URL
								</th>
								<td width="35%">
									<cus:SelectOneTag property="userBehavior.isContinue"
										defaultText='请选择' codeName="是或否"
										value="${userBehavior.isContinue}" />
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
										</a>
										<a href="#" class="btn_01"
											onclick="parent.close_win('behavoirWindow');">关闭<b></b>
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
