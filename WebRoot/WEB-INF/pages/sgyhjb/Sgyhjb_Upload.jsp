<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>事故隐患上报</title>
<link href="${ctx}/webResources/css/style.css" rel="stylesheet" type="text/css" />
<%@include file="/common/jsLib.jsp"%>
<script>
		function save(){
			
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="${ctx}/jsp/sgyhjb/sgyhjbUploadSave.action";
				document.myform1.submit();
			}
		}
	</script>
</head>

<body>
<form name="myform1" method="post" enctype="multipart/form-data">
<div class="top">
<div class="topav">
<div class="logo"><a href="#"><img src="${ctx}/images/login_logo.png"/></a></div>
<!--<div class="user">
    <span><i><img src="images/icon_01.png"/></i><a href="#">设为首页</a></span>
    <span><i><img src="images/icon_02.png"/></i><a href="#">加入收藏</a></span>
    <span><i><img src="images/icon_03.png"/></i><a href="#">客户端下载</a></span>
    <span><i><img src="images/icon_04.png"/></i><a href="#">隐患事故举报</a></span>
</div>-->
</div>
</div>




<div class="report">
<div class="report_area">
	<div class="pns"><img src="${ctx}/webResources/images/icon_jb.png"/></div>
    <div class="tjbg">
		<div class="cell">
           <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="130" align="right"><span class="red">*</span>举报人：</td>
                <td><input name="sgyhjb.jbr" value="${sgyhjb.jbr}" type="text" maxlength="255" dataType="Require" msg="必填" class="form_text" style="width:330px;"/></td>
              </tr>
              <tr>
                <td width="130" align="right"><span class="red">*</span>举报人电话：</td>
                <td><input name="sgyhjb.jbrdh" value="${sgyhjb.jbrdh}" type="text" maxlength="255" dataType="Require" msg="必填" class="form_text" style="width:330px;"/></td>
              </tr>
              <tr>
                <td width="130" align="right"><span class="red">*</span>隐患等级：</td>
                <td><cus:SelectOneTag property="sgyhjb.yhjb" defaultText='请选择' codeName="隐患等级" value="${sgyhjb.yhjb}" dataType="Require" msg="必填" class="form_text" style="width:330px;"/></td>
              </tr>
              <tr>
                <td width="130" align="right">举报人所在地：</td>
                <td><input name="sgyhjb.jbszd" value="${sgyhjb.jbszd}" type="text" maxlength="255" class="form_text" style="width:330px;"></td>
              </tr>
              <tr>
                <td width="130" align="right">举报内容：</td>
                <td><textarea name="sgyhjb.jbnr" >${sgyhjb.jbnr}</textarea></td>
              </tr>
              <tr>
                <td align="right" colspan="2"><div class="report_btn"><a href="#" class="btn_login" onclick="save()">提&nbsp;&nbsp;&nbsp;&nbsp;交</a></div></td>
              </tr>
				<tr>
					<td colspan="2" height="100px" style="text-align:center">
						<input type="button" value="确定" style="width:102px; height:32px; border:0px; color:#000; font-size:14px; cursor:pointer; line-height:32px;margin-Top:10px;background:url(${ctx}/webResources/images/tosn_btnmiddle.png) no-repeat 0 -96px;" onclick="save();">
					</td>
				</tr>
            </table>

        </div>    
    </div>
</div>
</div>

<div class="footer">
<div class="footer_l">请使用IE7.0以上版本运行本软件</div>
<div class="footer_r"><span>版权所有&nbsp;&nbsp;智慧安监管理平台</span><span>COPYRIGHTS 2014</span></div>
</div>


</form>
</body>
</html>
