<%@ page language="java" pageEncoding="UTF-8" import="com.jshx.core.utils.SysPropertiesUtil"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="${ctx}/webResources/js/common.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx}/webResources/style/css/default/animation1.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/webResources/style/css/default/login1.css" />
		
		<script>
			$(document).ready(function(){
				getSavedUserName();
				
				$.ajax({
	              	url:"${ctx}/jsp/loginImage/getloginImageURL.action",
	              	type:"post",
					dataType:"json",
					success:function(data){
						if(data.status=="y"){
							$(".wallpaper").css("background","url(${ctx}"+data.info+") no-repeat center");
						}else{
							$(".wallpaper").css("background","url(${ctx}/webResources/style/images/default/login1_login_wallpaper.jpg) no-repeat center");
						}
					}
				});		
			
				var needCaptcha = <%=SysPropertiesUtil.getProperty("needCaptcha")%>;
				
				if(0==needCaptcha){
					//登录页始终不显示验证码校验，后台不做验证
					
				}else if(1==needCaptcha){
					//登录页始终显示验证码校验，后台做验证
					addvalidateCode();
				}else if(2==needCaptcha){
					//登录页开始不显示验证码校验，当用户输入用户名密码错误超过3次以后显示验证码校验，后台开始做验证
					var loginFail = <%=(String) session.getAttribute("LOGIN_FAIL")%>;
					if(null==loginFail){
						Cookies.set("loginFailCount",Number(0));
					}else if(true==loginFail){
						var loginFailCount = Cookies.get("loginFailCount");
						Cookies.set("loginFailCount",Number(loginFailCount)+1);
					}
					
					if(Cookies.get("loginFailCount")>2){
						$("#validateFlag").val("true");
						addvalidateCode();
					}			
				}
			});
			function addvalidateCode(){
				var html='<div class="input_bgd_03"><input name="validateCode" id="validateCode" type="text" /><div class="vcode"><img id="img1" src="${ctx}/captcha.action" width="89" height="33" /></div></div>';
				$(html).insertAfter(".input_bgd_02");
			}
			function doLogin() {
				$("#loginMessage").empty();

				ff = document.loginForm;
				if(ff.rem.checked){
					var expires = new Date();
					expires.setTime(expires.getTime() + 1000*60*60*24*<%=SysPropertiesUtil.getInt("COOKIES_EXPIRED_DAYS", 0)%>);
					
					Cookies.set("loginId",ff.loginId.value,expires);
					Cookies.set("password",ff.password.value,expires);
					Cookies.set("checked",ff.rem.checked,expires);
				}else{
					Cookies.clear("loginId");
					Cookies.clear("password");
					Cookies.clear("checked");
				}
			  	ff.action="${ctx}/userLogin.action";
			  	ff.submit();
			}
		    function suball(){
				if(event.keyCode==13){
					 doLogin();
				}
			}
			function getSavedUserName(){
				var userName = Cookies.get("loginId");
				var password = Cookies.get("password");
				if(userName!=null){
					$("#loginId").val(userName);
					$("#password").val(password);
					$("#rem").attr("checked",true);
				}
			}
		</script>
	</head>
	<body>		
		<div class="wallpaper">
			<img src="${ctx}/webResources/style/images/default/login1_login_wallpaper.jpg" />
		</div>
	
		<div class="leftbar">
			<div class="leftbar_img"></div>
		</div>
	
		<div class="sidebar">
			<div class="sidebar_content">
				<div class="logo">
					<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0" width="435" height="30">
						<param name="allowScriptAccess" value="sameDomain" />
						<param name="movie" value="${ctx}/webResources/style/images/default/login1_login_logo.swf" />
						<param name="quality" value="high" />
						<param name="wmode" value="transparent" />
						<embed src="${ctx}/webResources/style/images/default/login1_login_logo.swf" quality="high" bgcolor="#ffffff" width="435"  height="30" name="mymovie" align="center" allowScriptAccess="sameDomain"  type="application/x-shockwave-flash" wmode="transparent" pluginspage="http://www.macromedia.com/go/getflashplayer" />
					</object>
				</div>
	
				<div class="message">请使用您的账户登录。<p id="loginMessage">${LOGIN_MESSAGE}</p></div>
				
				<form name="loginForm">
					<div class="input_bgd_01"><input name="loginId" id="loginId" type="text" /></div>
					<div class="input_bgd_02"><input name="password" id="password" type="password" /></div>
		
					<div class="keepi"><input name="rem" id="rem" type="checkbox" />使我保持登录状态</div>
				</form>
				
				<a href="#" class="login_btn" onclick="doLogin();"></a>
	
				<div class="tipi">
					<a href="#">无法访问我的账户？</a>
					<p>
						<a href="#" class="reg">创建帐号</a><span>|</span>
						<a href="#" class="reg">帮助手册</a><span>|</span>
						<a href="#" class="reg">客服中心</a>
					</p>
				</div>
	
				<div class="copyrights">南京拓构软件有限公司版权所有<p>Coyrights 2013</p></div>
			</div>
		</div>
	</body>
</html>