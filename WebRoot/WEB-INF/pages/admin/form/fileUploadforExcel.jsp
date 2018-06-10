<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="ww" uri="/struts-tags"%>
<%
String action=request.getParameter("action");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>选择要上传的文件</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script>
	function uploadFile(){
		if(document.fileUploadForm.fileUpload.value!=null && document.fileUploadForm.fileUpload.value!=""){
			document.fileUploadForm.action="<%=basePath%>/jsp/<%=action%>";
			document.fileUploadForm.submit();
		}else{
			alert("请选择文件！");
		}
	}
	</script>
  </head>

  <body>
    <form name="fileUploadForm" method="post" enctype="multipart/form-data">
  		<input type="file" name="fileUpload" style="height:21px;"/>
  		<input type="button" value="上 传" style="height:21px;width:80px;" onclick="uploadFile()"/>
  	</form>
  	<a href="<%=basePath %>\webResources\template\Import_DB.xls">下载Excel模板</a>
  </body>
</html>
