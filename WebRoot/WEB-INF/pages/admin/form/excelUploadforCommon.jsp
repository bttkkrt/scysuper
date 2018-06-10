<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="ww" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>选择要导入的Excel</title>
    
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

			var fileName = document.fileUploadForm.fileUpload.value;
			if(fileName.indexOf(".xls")!=fileName.length-4)
	        {
	        	alert("请选择xls格式 的Excel文件!");
	        	return;
	        }
			
			document.fileUploadForm.action="<%=basePath%>/jsp/admin/form/importExcelForCommon.action";
			document.fileUploadForm.submit();
			document.fileUploadForm.uploadButton.disabled =true; 
		}else{
			alert("请选择文件！");
		}
	}

  
    function check(fileName) {
        if(fileName.indexOf(".xls")!=fileName.length-4)
        {
        	alert("请选择xls格式 的Excel文件!");
        	document.fileUploadForm.fileUpload.value = "";
        }
        
    }

	
	</script>
  </head>

  <body>
  	<table width="100%">
  	<tr>
  	<td align="center">
  	 <form name="fileUploadForm" method="post" enctype="multipart/form-data">
    	<input type="hidden" name="thisTableIdString" value="<%=request.getParameter("tableId") %>">
  		<input type="file"  onchange="check(this.value);" name="fileUpload" style="height:21px;"/>
  		<input type="button" value="上 传" style="height:21px;width:60px;" name="uploadButton" onclick="uploadFile()"/>
  	</form>
  
  	</td>
  	</tr>
  	<tr>
  	<td>
  	&nbsp;&nbsp;&nbsp;&nbsp;通用EXCEL模板：<a href="<%=basePath %>/jsp/admin/form/getExceltemplate.action?tableId=<%=request.getParameter("tableId") %>" style="color:red">下载</a><br>
	&nbsp;&nbsp;&nbsp;&nbsp;1.用需要导入的字段显示名作为excel的第一行题头<br>
	&nbsp;&nbsp;&nbsp;&nbsp;2.数据从第二行开始写，每个文件最多1000行<br>
	&nbsp;&nbsp;&nbsp;&nbsp;3.支持字符型，数字型，日期型字段<br>
	&nbsp;&nbsp;&nbsp;&nbsp;4.只支持2003版本的xls文件<br>
	</td>
  	</tr>
  	</table>
   
  </body>
</html>
