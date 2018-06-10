<%@ page language="java" import="com.jshx.core.utils.SysPropertiesUtil" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="shortcut icon" href="${ctx}/webResources/images/favicon.ico">
		<%-- <%@include file="/common/jsLib.jsp"%> --%>
		<script type="text/javascript" src="${ctx}/webResources/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery.easyui.min.js"></script>
		<!-- platform -->
		<script type="text/javascript" src="${ctx}/webResources/js/common.js"></script>
		<!-- 原有的表单验证工具，为了兼容旧的项目 -->
		<script src="${ctx}/webResources/js/validator.js"></script>
		<script type="text/javascript" src="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.pack.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="${ctx}/webResources/style/css/default/login.css" />
	</head>
	<body  class="wallpaper">
		<div class="login_top">
			<div class="logoo">
				<!--<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
					codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0"
					width="435" height="30">
					<param name="allowScriptAccess" value="sameDomain" />
					<param name="movie"
						value="<c:url value='/webResources/style/images/default/login_logo.swf'/>" />
					<param name="quality" value="high" />
					<param name="wmode" value="transparent" />
					<embed
						src="<c:url value='/webResources/style/images/default/login_logo.swf'/>"
						quality="high" bgcolor="#ffffff" width="435" height="30"
						name="mymovie" align="center" allowScriptAccess="sameDomain"
						type="application/x-shockwave-flash" wmode="transparent"
						pluginspage="http://www.macromedia.com/go/getflashplayer" />
				</object>-->
			</div>
			
			<div style="position: absolute; right: 115px;bottom: 15px;">
				<!-- ian 20180526
					<span style="color: #737373;margin: 0 12px;"><i><img src="webResources/images/icon_01.png"/></i><a href="#" style="color: #737373;font-size: 14px;">设为首页</a></span>
				    <span style="color: #737373;margin: 0 12px;"><i><img src="webResources/images/icon_02.png"/></i><a href="#" style="color: #737373;font-size: 14px;">加入收藏</a></span>
				    <span style="color: #737373;margin: 0 12px;"><i><img src="webResources/images/icon_03.png"/></i><a href="javascript:void(0)" style="color: #737373;font-size: 14px;" onclick="downLatestVersion()">客户端下载</a></span>

				<table>
					<tr>
						<td>Android客户端下载</td>
						<td><img src="/upload/wjxz/Android.png" style="width:80px;height:80px"/></td>
					</tr>
				</table> -->
			</div>
		</div>

		<div >
			<form method="post" action="" id="loginForm" name="loginForm">
				<input type="hidden" id="validateFlag" name="validateFlag" />
				<div class="login_frame">
					<div class="login_frame_t"></div>
					<div class="login_frame_m">
						<div class="sub_name">
							<img src="${ctx}/webResources/style/images/default/login_icon_01.png" width="16" height="16" />用户名
							<a href="${ctx}/jsp/company/companyRegisterUI.action" tabIndex=-1   target="_blank">企业注册</a>
						</div>
						<div class="sub_input">
							<input name="loginId" id="loginId" />
						</div>
						<div class="sub_name">
							<img src="${ctx}/webResources/style/images/default/login_icon_02.png" width="16" height="16" />密码
							<a href="#" tabIndex=-1 title="请联系管理员">忘记密码?</a>
						</div>
						<div class="sub_input">
							<input name="password" id="password" type="password" onkeydown="suball()" />
						</div>
						<div class="login_tips" id="loginMessage">
							${LOGIN_MESSAGE}
						</div>
					</div>
					<div class="login_frame_b">
						<div class="savemyacc">
							<input name="rem" id="rem" type="checkbox" />记住密码
						</div>
						<a href="#" class="login_btn" onclick="doLogin()"></a>

					</div>
				</div>
			</form>
		</div>

		<div class="footer">
			<div class="footer_left">
				<!-- <span>苏ICP备08103496号-4</span><span>http://www.jsict.com</span> -->
			</div>
			<div class="footer_right">
				<!-- <span>智慧安监管理平台</span><span>CopyRights©2016</span>-->
			</div>
		</div>
	<script type="text/javascript">
			$(document).ready(function(){
				$(".wallpaper").css("background","url(${ctx}/webResources/style/images/default/login_background.jpg) no-repeat center center fixed");
				getSavedUserName();
				
				/* $.ajax({
	              	url:"${ctx}/jsp/loginImage/getloginImageURL.action",
	              	type:"post",
					dataType:"json",
					success:function(data){
						if(data.status=="y"){
							$(".wallpaper").css("background","url(${ctx}"+data.info+") no-repeat center");
						}else{
							$(".wallpaper").css("background","url(${ctx}/webResources/style/images/default/login_bg.png) no-repeat center");
						}
					}
				});		 */
			
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
				$(".login_frame_m").append( '<div class="sub_name">'+
												'<img src="${ctx}/webResources/style/images/default/login_icon_03.png" width="16" height="16" />验证码</div>'+
											'<div class="sub_input">'+
												'<input name="validateCode" id="validateCode" maxlength="4" onkeydown="suball()" />'+
												'<img src="${ctx}/captcha.action" />'+
											'</div>'
				);
			}
			function doLogin() {
				if($(".login_frame_error").size()>0)
					$(".login_frame_error").removeClass("login_frame_error");
				ff = document.loginForm;
				if(ff.rem.checked){
					var expires = new Date();
					expires.setTime(expires.getTime() + 1000*60*60*24*<%=SysPropertiesUtil.getInt("COOKIES_EXPIRED_DAYS", 0)%>);
					Cookies.set("checked",ff.rem.checked,expires);
					Cookies.set("loginId",ff.loginId.value,expires);
					Cookies.set("password",ff.password.value,expires);
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
			 function downLatestVersion()
			   {
			   		location.href="http://59.45.148.2/apk/version/aj_fushun.apk";
			   }
			
		</script>
	</body>
</html>