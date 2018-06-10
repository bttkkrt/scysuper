<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String modify = request.getParameter("modify");
	request.setAttribute("modify", modify);
	String errorInfo = request.getParameter("errorInfo");
	request.setAttribute("errorInfo", errorInfo);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>修改密码</title>
		<%@include file="/common/jsLib.jsp"%>
		<script src="${ctx}/webResources/js/Validform/passwordStrength-min.js"></script>
		<script>
        function beforeSubmitCallback(){
        	var ls = 0;
        	var password = document.getElementById('newPassword').value;
 			if(password.match(/([a-z])+/)){
     			ls++;
  			}
 			if(password.match(/([0-9])+/)){
      			 ls++;  
 			}
 			if(password.match(/([A-Z])+/)){
        		ls++;
  			}
  			if(password.match(/[^a-zA-Z0-9]+/)){
        		ls++;
    		}
    		if(ls < 3){
    			alert("登录密码必须是大写字母、小写字母、数字和特殊字符中任意三个组合！");
    			return false;
    		}
    		else
    		{	
    			var con = confirm("确定要修改密码？");
           		return con;
    		}
        }
    </script>
	</head>
	
	<c:set var="curr_path" value="修改密码"></c:set>
	<body validform="true">
		<div class="page_content" style="width: 95%">
			<div class="box_01 boxBmargin12 submitdata" style="width: 100%">
				<div class="inner12px">
					<form name="passwordFrm" id="passwordFrm"
						action="${ctx}/jsp/personal/changePasswords.action" method="post">
						<input type='hidden' name="user.id" id="id"
							value='${curr_user.id }'>
						<input type='hidden' name="user.loginId" id="loginId"
							value="${curr_user.loginId }">
						<div class="cell">
							<table   >
								
								<c:if test="${not empty errorInfo}">
									<tr >
										<td colspan="2">
											<p style="text-align:center"><font style="color:red">${errorInfo}</font> </p>
										</td>
									</tr>
								</c:if>
								<c:if test="${ empty errorInfo}">
									<tr >
										<td colspan="2">
											<p style="text-align:center"><font style="color:red"> &nbsp;</font> </p>
										</td>
									</tr>
								</c:if>
								<tr>
									<th width="20%">
										原密码
										<span class="red">*</span>
									</th>
									<td>
										<input type='password' name="user.password" id="password"
											datatype="*1-50" errormsg="请填写原密码！" value=""  nullmsg="请填写原密码！" sucmsg=" " 
											class="form_text" style="width: 200px">
									</td>
								</tr>
								<tr>
									<th>
										新密码
										<span class="red">*</span>
									</th>
									<td height="35">
										<input type='password' maxlength="20" name="newPassword" id="newPassword"
											plugin="passwordStrength" datatype="*6-20" nullmsg="请填写新密码！"
											value="" class="form_text" style="width: 200px">
									</td>
								</tr>
								<tr>
									<th>
										密码强度<span class="red">&nbsp;</span>
									</th>
									<td height="35">
										<div class="passwordStrength">
											<span>弱</span><span>中</span><span class="last">强</span>
										</div>
									</td>
								</tr>
								<tr>
									<th>
										确认新密码
										<span style="color: red">*</span>
									</th>
									<td>
										<input type='password' maxlength="20" name="conPassword" id="conPassword"
											datatype="*6-20" value="" class="form_text" nullmsg="请再次输入新密码！"
											recheck="newPassword" style="width: 200px">
									</td>
								</tr>
								<tr>
									<td colspan='2'>
										<div class="btn_area_setc">
											<a href="#" class="btn_01" type="submit">确定<b></b>
											</a>
											<c:if test="${errorInfo == '密码修改成功，下次请用新密码登录！'}">
												<a href="#" class="btn_01"  onclick="parent.close_win();">关闭<b></b></a>
											</c:if>
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