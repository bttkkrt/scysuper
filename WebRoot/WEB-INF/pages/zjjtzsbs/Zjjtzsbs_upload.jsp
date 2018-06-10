<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>文件上传</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <%@include file="/common/jsLib.jsp"%>
	<link href="<c:url value='/webResources/js/uploadify.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src='<c:url value="/webResources/js/swfobject.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/webResources/js/jquery.uploadify.v2.1.4.js"/>'></script>
	<script>
	    $(document).ready(function(){
		   	　$("#uploadify").uploadify({
	　		'uploader': '<c:url value="/webResources/js/uploadify.swf"/>',  
	 		 'buttonImg':  '<c:url value="/webResources/js/browse_sc.jpg"/>', 
			　'script':'zjjtzsbsSaveFile.action',  
			　'cancelImg': '<c:url value="/webResources/js/cancel.png"/>',             
			　'queueID' : 'fileQueue', //和存放队列的DIV的id一致  
			　'auto'  : false, //是否自动开始  
	　		'multi': false, //是否支持多文件上传  
			  'buttonText': 'BROWSE', //按钮上的文字  
			　'simUploadLimit' : 1, //一次同步上传的文件数目  
			　'sizeLimit': 19871202, //设置单个文件大小限制，单位为byte  
			　'queueSizeLimit' : 1, //队列中同时存在的文件个数限制  
	　		'fileDesc': '支持格式:xls,xlsx', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
			　'fileExt': '*.xls;*.xlsx;',//允许的格式
			'onComplete': function (event, queueID, fileObj, response, data) {
	　},
		'onAllComplete': function (event, data) { 
			alert('上传成功');
			window.opener.reloadDate();
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
			<table class="submitdata" width="100%" height="100%">
					<tr>
						<th>模板下载：</th>
						<td><a href="/ajj/zjjtzsb.xls"><font style="color:blue;">点击下载</font></a></td>
					</tr>
					<tr>
						<th width="15%">excel导入：</th>
						<td width="60%">
								<div id="fileQueue" />
								<input type="file" name="uploadify" id="uploadify"/>
							   <input type="hidden" value="" id="filePath"/>
							   <input id="sabtn" type="button" class="btnbg" value="确定" onclick="saveData()" >
							   <input type="button" class="btnbg" value="取消" onclick="window.close();">
					   </td>
		  	 		 </tr>
		      		 <tr id="piv"></tr>
			</table>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>