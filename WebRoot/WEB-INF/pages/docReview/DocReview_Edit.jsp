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
				
				document.myform1.action="docReviewSave.action";

				document.myform1.submit();
			}
		}
		
		$(document).ready(function() {
		  var picNames="";
		  var data = '${ctx}/jsp/docReview/fileUpload.action?docReview.proId='+'${docReview.proId}';
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
			$.ajax({url: "${ctx}/jsp/docReview/deleteFile.action"
				,data:{fileId:aid}
				,type: "POST",
				success:function(){
					$("tr").remove("tr[id="+aid+"]");
				}
		    });
        }
        
        //下载附件
        function downloadFile(attachId){
                location.href = "${ctx}/jsp/docReview/download.action?fileId="+attachId;
        }
        
        function selectUser()
		{
			var str = showModalDialog("${ctx}/jsp/docReview/selectUsers.action",window,'dialogWidth:650px;dialogHeight:500px;scroll:no;center:yes');
			if(str != null && str.length > 0)
			{
				if("" == "${userIds}"){
					$("#userIds").val(str[0]);
					$("#userNames").val(str[1]);
				}else{
					var tempUserIds = "${userIds}"+ "," + str[0];
					var tempUserNames = "${userNames}"+ "," + str[1];
					$("#userIds").val(tempUserIds);
					$("#userNames").val(tempUserNames);
				}
			}
			
		}
        
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="docReview.id" value="${docReview.id}">
		<input type="hidden" name="docReview.createTime" value="<fmt:formatDate type="both" value="${docReview.createTime}" />">
		<input type="hidden" name="docReview.updateTime" value="${docReview.updateTime}">
		<input type="hidden" name="docReview.createUserID" value="${docReview.createUserID}">
		<input type="hidden" name="docReview.updateUserID" value="${docReview.updateUserID}">
		<input type="hidden" name="docReview.deptId" value="${docReview.deptId}">
		<input type="hidden" name="docReview.delFlag" value="${docReview.delFlag}">
		<input type="hidden" name="docReview.proId" value="${docReview.proId}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="10%">公文标题<font style="color: red;">*</font></th>
					<td colspan="3"><input name="docReview.docTitle" value="${docReview.docTitle}" type="text" dataType="Require" msg="此项为必填" maxlength="4000" style="width: 50.2%;" ></td>
				</tr>
				<tr>
					<th width="10%">上报人<font style="color: red;">*</font></th>
					<td colspan="3">
						${user.displayName}
					</td>
				</tr>
				<tr>
					<th width="10%">公文内容<font style="color: red;">*</font></th>
					<td colspan="3">
						<textarea id="infoContent" name="docReview.docContent" style="width: 80%; height: 200px;">${docReview.docContent}</textarea>
					</td>
				</tr>
				<tr>
					<th width='15%'>
						接收人<font style="color: red;">*</font>
					</th>
					<td colspan='3'>
						<textarea rows="5" style="width:80%;word-break:break-all;word-wrap:break-word;" readonly="readonly" 
						name="docReview.userNames" id="userNames">${docReview.userNames}</textarea><input type="button" onclick="selectUser();" value="选择">
						<input type="hidden" id ="userIds" name="docReview.userIds" value="${docReview.userIds}">
					</td>
				</tr>
				<tr>
					<th width="10%">上传附件</th>
					<td width="70%">
				   		<div id="fileQueue"/>
				    	<input type="file" name="uploadify" id="uploadify"/>
			    		<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
				    </td>
				</tr>
				<tr>
					<td colspan="2">
						<div style="color:green;overflow:auto;height:130px;">
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
				</tr>
				<tr>
					<td colspan="4" height="100px"  style="text-align: center;">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">提交</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
