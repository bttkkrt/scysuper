<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>事故隐患上报</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="sgyhjbUploadSave.action";
				document.myform1.submit();
			}
		}
	</script>
	
</head>
<body>
<div style="margin-top:20px;margin-left:10px;">
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		
		<div>
			<table width="100%" border="0" bgcolor="#F9F9F9">
				<tr>
					<th width="35%" align="right">举报人</th>
					<td><input name="sgyhjb.jbr" value="${sgyhjb.jbr}" type="text" maxlength="255" dataType="Require" msg="此项为必选" /><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="35%" align="right">举报人电话</th>
					<td><input name="sgyhjb.jbrdh" value="${sgyhjb.jbrdh}" type="text" maxlength="255" dataType="Require" msg="此项为必选" /><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="35%" align="right">举报所在地</th>
					<td><input name="sgyhjb.jbszd" value="${sgyhjb.jbszd}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="35%" align="right">隐患等级</th>
					<td><cus:SelectOneTag property="sgyhjb.yhjb" defaultText='请选择' codeName="隐患等级" value="${sgyhjb.yhjb}" dataType="Require" msg="此项为必选" /><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="35%" align="right">举报内容</th>
					<td>
						<textarea name="sgyhjb.jbnr" style="width:80%;height:120px">${sgyhjb.jbnr}</textarea><font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td colspan="2" height="100px" style="text-align:center">
						<input type="button" value="确定" style="width:102px; height:32px; border:0px; color:#000; font-size:14px; cursor:pointer; line-height:32px;margin-Top:10px;background:url(${ctx}/webResources/images/tosn_btnmiddle.png) no-repeat 0 -96px;" onclick="save();">
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
</body>
</html>
