<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<link href="<c:url value='/webResources/js/uploadify.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src='<c:url value="/webResources/js/swfobject.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/webResources/js/jquery.uploadify.v2.1.4.js"/>'></script>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="jshxGwaqSave.action";
				document.myform1.submit();
			}
		}
		
		$(document).ready(function() {
		  var picNames="";
		  var data = '${ctx}/jsp/gwaq/fileUpload.action?jshxGwaq.proId='+'${jshxGwaq.proId}';
		　$("#uploadify").uploadify({
		　'uploader': '<c:url value="/webResources/js/uploadify.swf"/>',  
		  'buttonImg': '<c:url value="/webResources/js/browse_sc.jpg"/>', 
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
	         addmore(response);
		　},
		 'onAllComplete': function (event, data) { 
		 	alert("上传成功");
		　},  
		　'onError': function(event, queueID, fileObj) {  
		　	alert("文件:" + fileObj.name + "上传失败");  
		　},  
		　'onCancel':function(event, queueID, fileObj){}
		　});
		});
		
		 function addmore(name){
	   		var a = name.split(";");
			var filename = a[0];
			var extname = a[1];
			var table = document.getElementById("more");
			var tr = table.insertRow();
			tr.id=extname;
			var td1 = document.createElement("td");
			var span1 =document.createElement("span");
			span1.innerHTML=filename;
			td1.appendChild(span1);
			tr.appendChild(td1);
			var td2 = document.createElement("td");
			var aa1 =document.createElement("a");
			aa1.href="javascript:downloadFile('"+ extname + "');";
			var text1=document.createTextNode("下载");
			aa1.appendChild(text1); 
			td2.appendChild(aa1);
			var span2 =document.createElement("span");
			span2.innerHTML="&nbsp;&nbsp;";
			td2.appendChild(span2);
			var aa2 =document.createElement("a");
			aa2.href="javascript:del('"+ extname + "');";
			var text2=document.createTextNode("删除");
			aa2.appendChild(text2); 
			td2.appendChild(aa2);
			tr.appendChild(td2);
		}
		
		//删除附件
		function del(aid){
			$.ajax({url: "${ctx}/jsp/gwaq/deleteFile.action"
				,data:{fileId:aid}
				,type: "POST",
				success:function(){
					$("tr").remove("tr[id="+aid+"]");
				}
		    });
        }
        
        //下载附件
        function downloadFile(attachId){
                location.href = "${ctx}/jsp/gwaq/download.action?fileId="+attachId;
        }
        
        function validateNum(event,obj)
        {
        	event = window.event||event; 
       	 	if(event.keyCode == 37 | event.keyCode == 39){ 
           		return; 
       	 	} 
	        //先把非数字的都替换掉，除了数字和. 
	        obj.value = obj.value.replace(/[^\d.]/g,""); 
	        //必须保证第一个为数字而不是. 
	        obj.value = obj.value.replace(/^\./g,""); 
	        //保证只有出现一个.而没有多个. 
	        obj.value = obj.value.replace(/\.{2,}/g,"."); 
	        //保证.只出现一次，而不能出现两次以上 
	        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	        if(obj.value.length >= 2 && obj.value.substring(0,1) == "0" && obj.value.substring(1,2) != ".")
        	{
        		obj.value = obj.value.substring(1,obj.value.length);
        	}
        }
        
        function validate(event,obj)
        {
        	event = window.event||event; 
        	if(event.keyCode == 37 | event.keyCode == 39){ 
           	 	return; 
        	} 
        	obj.value = obj.value.replace(/[^\d]/g,""); 
        	if(obj.value.length >= 2 && obj.value.substring(0,1) == "0")
        	{
        		obj.value = obj.value.substring(1,obj.value.length);
        	}
        }
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="jshxGwaq.id" value="${jshxGwaq.id}">
		<input type="hidden" name="jshxGwaq.createTime" value="<fmt:formatDate type="both" value="${jshxGwaq.createTime}" />">
		<input type="hidden" name="jshxGwaq.updateTime" value="${jshxGwaq.updateTime}">
		<input type="hidden" name="jshxGwaq.createUserID" value="${jshxGwaq.createUserID}">
		<input type="hidden" name="jshxGwaq.updateUserID" value="${jshxGwaq.updateUserID}">
		<input type="hidden" name="jshxGwaq.deptId" value="${jshxGwaq.deptId}">
		<input type="hidden" name="jshxGwaq.delFlag" value="${jshxGwaq.delFlag}">
		<input type="hidden" name="jshxGwaq.szzname" value="${jshxGwaq.szzname}">
		<input type="hidden" name="jshxGwaq.qymc" value="${jshxGwaq.qymc}">
		<input type="hidden" name="jshxGwaq.szzid" value="${jshxGwaq.szzid}">
		<input type="hidden" name="jshxGwaq.qyid" value="${jshxGwaq.qyid}">
		<input type="hidden" name="jshxGwaq.proId" value="${jshxGwaq.proId}">
		<input type="hidden" name="jshxGwaq.qylx" value="${jshxGwaq.qylx}">
		<input type="hidden" name="jshxGwaq.hyfl" value="${jshxGwaq.hyfl}">
		<input type="hidden" name="jshxGwaq.qygm" value="${jshxGwaq.qygm}">
		<input type="hidden" name="jshxGwaq.qyzclx" value="${jshxGwaq.qyzclx}">
		<input type="hidden" name="jshxGwaq.ifwhpqylx" value="${jshxGwaq.ifwhpqylx}">
		<input type="hidden" name="jshxGwaq.ifzywhqylx" value="${jshxGwaq.ifzywhqylx}">
		<input type="hidden" name="jshxGwaq.ifyhbzjyqy" value="${jshxGwaq.ifyhbzjyqy}">
		<input type="hidden" name="jshxGwaq.szc" value="${jshxGwaq.szc}">
		<input type="hidden" name="jshxGwaq.szcname" value="${jshxGwaq.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="30%">岗位名称</th>
					<td width="70%"><input name="jshxGwaq.gwmc" value="${jshxGwaq.gwmc}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="30%">岗位员工数</th>
					<td width="70%"><input name="jshxGwaq.gwygs" value="${jshxGwaq.gwygs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="30%">最大工作时间</th>
					<td width="70%"><input name="jshxGwaq.zdgzsj" value="${jshxGwaq.zdgzsj}" type="text" maxlength="255" onKeyUp="validateNum(event,this)">小时</td>
				</tr>
				<tr>
					<th width="30%">是否倒班</th>
					<td width="70%"><cus:SelectOneTag property="jshxGwaq.sfdb" defaultText='请选择' codeName="是或否" value="${jshxGwaq.sfdb}"/></td>
				</tr>
				<tr>
					<th width="30%">倒班总人数</th>
					<td width="70%"><input name="jshxGwaq.dbzrs" value="${jshxGwaq.dbzrs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="30%">本岗位安全操作规程</th>
					<td width="70%">
				   		<div id="fileQueue"/>
				    	<input type="file" name="uploadify" id="uploadify"/>
			    		<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
				    </td>
				</tr>
				<tr>
					<th width="30%">已添加安全操作规程</th>
					<td width="70%">
						<div style="color:green;overflow:auto;height:160px;">
						<table id="more">
							  <c:forEach var="item" items="${picList}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">${item.fileName}</td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;
								   <a href="javascript:del('${item.id}');">删除</a></td>
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
