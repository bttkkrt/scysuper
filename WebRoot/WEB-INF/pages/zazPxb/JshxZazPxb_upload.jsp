<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>附件上传</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <%@include file="/common/jsLib.jsp"%>
	<link href="<c:url value='/webResources/js/uploadify.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src='<c:url value="/webResources/js/swfobject.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/webResources/js/jquery.uploadify.v2.1.4.js"/>'></script>
	<script>
		var filename="";
		var picName="";
	    $(document).ready(function(){
	    	var data = "jshxZazPxbSaveExcel.action";
		   	　$("#uploadify").uploadify({
	　		'uploader': '<c:url value="/webResources/js/uploadify.swf"/>',  
	 		 'buttonImg':  '<c:url value="/webResources/js/browse_sc.jpg"/>', 
			　'script':data, 
			　'cancelImg': '<c:url value="/webResources/js/cancel.png"/>',             
			　'queueID' : 'fileQueue', //和存放队列的DIV的id一致  
			　'auto'  : false, //是否自动开始  
	　		'multi': true, //是否支持多文件上传  
			  'buttonText': 'BROWSE', //按钮上的文字  
			　'simUploadLimit' : 1, //一次同步上传的文件数目  
			　'sizeLimit': 19871202, //设置单个文件大小限制，单位为byte  
			　'queueSizeLimit' : 10, //队列中同时存在的文件个数限制  
	　		'fileDesc': '所有文件：*.*', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
			　'fileExt': '*.*;',//允许的格式
			'onComplete': function (event, queueID, fileObj, response, data) {
			
	　},
		'onAllComplete': function (event, data){
			alert('上传成功');
			window.close();
	　}, 
		　'onError': function(event, queueID, fileObj) {  
	　		alert("文件:" + fileObj.name + "上传失败");  
                },
	　	'onCancel':function(event, queueID, fileObj){
			return true;
  			  }
	 });
	});
		function saveData(){
			//主要用于刷新列表页面
			jQuery('#uploadify').uploadifyUpload();
			
		}
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form id ="myform" name="myform" method="post" enctype="multipart/form-data">
			<table>
					<tr>
						<td>模板下载：</td>
						<td><a href="/ajj/moban01.xls" style="color:blue;">点击下载</a></td>
					</tr>
					<tr>
						<td width="60%" colspan="2">
								<div id="fileQueue" />
								<input type="file" name="uploadify" id="uploadify"/>
			  	  			   <!-- <a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
							    <a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消上传</a>  -->
							   <input type="hidden" value="" id="filePath"/>
					   </td>
		  	 		 </tr>
		      		 <tr id="piv"></tr>
				<tr>
					<td><input id="sabtn" type="button" class="btnbg" value="确定" onClick="saveData()" ></td>
					<td><input type="button" class="btnbg" value="取消" onClick="window.close();"></td>
				</tr>
			</table>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>