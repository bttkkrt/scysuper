<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>企业注册</title>
	<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
</head>
  <body >
	<div class="ajtop">
		<div class="ajtopav">
			<div class="ajlogo">企业基本信息注册</div>
			<div class="user"></div>
		</div>
	</div>
<div class="content">
	<div class="wrap">
    	<div class="steps"><span>1.登录信息</span><b></b><span>2.基本信息</span><b></b><span>3.经营信息</span><b></b><span>4.分类信息</span><b class="i1"></b><span class="current">5.注册完成</span></div>
		<div class="cell" style="margin-top:100px;">
           <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <%-- <tr>
                <td align="center"><a href="javaScript:window.location.href='${urladdress}'"><img src="${ctx }/webResourecs/qyzc/images/default/icon_ok.png"/></a></td>
              </tr>  --%>
              <tr>
                <td align="center"><div class="reg_ok">
               <a href="javaScript:window.location.href='${urladdress}'" style="font-size: 20px;">恭喜您，已注册成功！我们会在最快时间内审核，点我返回登陆页。</a>
               </div></td>
              </tr>                  
            </table>
        </div>
    </div>
</div>
<script>
function goToHome()
{
	window.location.href='${urladdress}';
}
//setTimeout("goToHome()",5000);
</script>
<div class="footer">
<div class="footer_l"></div>
<div class="footer_r"><span>版权所有&nbsp;&nbsp;南京拓构软件有限公司</span><span>COPYRIGHTS 2014</span></div>
</div>
</body>
</html>
