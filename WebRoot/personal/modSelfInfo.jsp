<%@page language="java" pageEncoding="UTF-8"
	import="com.jshx.core.utils.Constants" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>修改个人信息</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
	        function beforeSubmitCallback(){
	           	var con = confirm("确定要修改个人信息嘛？");
	           	return con;
	        }
	    </script>
	</head>
	<%
		String modify = request.getParameter("modify");
		request.setAttribute("modify", modify);
	%>
	<c:set var="curr_path" value="新建/修改用户"></c:set>

	<body validform="true">
		<div class="page_content">
			<div class="box_01 boxBmargin12 submitdata" style="width: 60%">
				<div class="inner12px">
					<form method="post" enctype="multipart/form-data" name="myform"
						action="${ctx}/jsp/personal/saveUser.action">

						<input type="hidden" name="user.dept.id"
							value="${curr_user.dept.id}">
						<input type="hidden" name="user.id" value="${curr_user.id}">
						<input type="hidden" name="user.delFlag"
							value="${curr_user.delFlag}">
						<input type='hidden' name="user.sortSq"
							value="${curr_user.sortSq }">
						<div class="cell">
						<c:if test="${not empty modify}">
									<tr>
										<td colspan="2">
											<b><font color="red">用户信息已经修改，需要重新登录后才能生效！</font> </b>
										</td>
									</tr>
								</c:if>
							<table>
								
								<tr>
									<th width="20%">
										所属部门
									</th>
									<td>
										${curr_user.dept.deptName}
										<input type="hidden" name="user.deptCode" class="form_text"
											value="${curr_user.deptCode}">
									</td>
								</tr>
								<tr>
									<th>
										姓名
										<span style="color: red">*</span>
									</th>
									<td>
										<input type='text' name="user.displayName" class="form_text"
											style="width: 200px" value="${curr_user.displayName}" maxlength="50"
											datatype="s2-50" errormsg="姓名格式为2-50位字母、数字！">
									</td>
								</tr>
								<tr>
									<th>
										用户名
									</th>
									<td>
										${curr_user.loginId}
										<input type='hidden' name="user.loginId" class="form_text"
											style="width: 200px" value="${curr_user.loginId}">
									</td>
								</tr>
								<tr>
									<th>
										职务
									</th>
									<td>
										<input type='text' name="user.duty" value="${curr_user.duty}"
											style="width: 200px" class="form_text">
									</td>
								</tr>
								<tr>
									<th>
										电话
									</th>
									<td>
										<input type='text' name="user.tel" value="${curr_user.tel}"
											style="width: 200px" class="form_text">
									</td>
								</tr>
								<tr>
									<th>
										手机
										<%
										if (Constants.MOBILE_NO_UNIQUE) {
									%>
										<span style="color: red">*</span>
										<%
											}
										%>
									</th>
									<td>
										<input type='text' name="user.mobile" style="width: 200px"
											value="${curr_user.mobile}" class="form_text" maxlength="11"
											datatype="m" >
									</td>
								</tr>
								<tr>
									<th>
										电子邮箱
									</th>
									<td>
										<input type='text' name="user.email"
											value="${curr_user.email}" class="form_text" maxlength="50"
											style="width: 200px" datatype="e" errormsg='邮箱格式不正确！' nullmsg='邮箱不能为空！' sucmsg='邮箱填写正确！' ignore="ignore">
									</td>
								</tr>
								<tr>
									<td colspan='2'>
										<div class="btn_area_setc">
											<a href="#" class="btn_01"
												type="submit">保存<b></b> </a>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>