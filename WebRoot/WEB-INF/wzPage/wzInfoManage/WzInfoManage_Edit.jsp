<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script src="${ctx}/webResources/kindeditor/kindeditor.js"></script>
	<script src="${ctx}/webResources/kindeditor/lang/zh_CN.js"></script>
	<script src="${ctx}/webResources/kindeditor/plugins/code/prettify.js"></script>
	<script type="text/javascript">
	KindEditor.ready(function(K) {
		    var editor = K.create('textarea[name="wzInfoManage.infoContent"]', {
		        cssPath: '${ctx}/webResources/kindeditor/plugins/code/prettify.css',
		        uploadJson: '${ctx}/webResources/kindeditor/upload_json.jsp',
		        fileManagerJson: '${ctx}/webResources/kindeditor/file_manager_json.jsp',
		        allowFileManager: true,
		        afterCreate: function() {
		            var self = this;
		            K.ctrl(document, 13,
		            function() {
		                self.sync();
		                save();
		            });
		            K.ctrl(self.edit.doc, 13,
		            function() {
		                self.sync();
		                save();
		            });
		        },
		        afterBlur: function() {
		            this.sync();
		        }
		    });
		    prettyPrint();
		});
		
		$(document).ready(function() {
			  uploadPic("uploadify","${wzInfoManage.linkId}","wzInfo","wzInfofj","fileQueue");
			});
	</script>
		
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="wzInfoManageSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="wzInfoManage.id" value="${wzInfoManage.id}">
		<input type="hidden" name="wzInfoManage.createTime" value="<fmt:formatDate type="both" value="${wzInfoManage.createTime}" />">
		<input type="hidden" name="wzInfoManage.updateTime" value="${wzInfoManage.updateTime}">
		<input type="hidden" name="wzInfoManage.createUserID" value="${wzInfoManage.createUserID}">
		<input type="hidden" name="wzInfoManage.updateUserID" value="${wzInfoManage.updateUserID}">
		<input type="hidden" name="wzInfoManage.deptId" value="${wzInfoManage.deptId}">
		<input type="hidden" name="wzInfoManage.delFlag" value="${wzInfoManage.delFlag}">
		<input type="hidden" name="wzInfoManage.infoType" value="${wzInfoManage.infoType}">
		<input type="hidden" name="wzInfoManage.clickTime" value="${wzInfoManage.clickTime}">
		<input type="hidden" name="wzInfoManage.linkId" value="${wzInfoManage.linkId}">
			<table width="100%" border="0">
				<tr>
						<th width="15%">
									第一标题
						</th>
						<td colspan="3">
							<input name="wzInfoManage.firTitle"  id="firTitle" value="${wzInfoManage.firTitle}" type="text" maxlength="127" style="width:80%">
						</td>
				</tr>
				<tr>
					<th width="15%">第二标题</th>
					<td width="85%" colspan='3'><input name="wzInfoManage.infotitle" style="width:80%" value="${wzInfoManage.infotitle}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127" style="width:80%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="10%">附件</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue"/>
				    	<input type="file" name="uploadify" id="uploadify"/>
			    		<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="wzInfofj">
							  <c:forEach var="item" items="${picList}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">
								   		<c:choose>
											<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
											||fn:endsWith(fn:toLowerCase(item.picName),'.png')
											||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
												<img src="${item.httpUrl}/upload/photo/${item.picName}"
												border='0' width='220' height='150'/><br>&nbsp;&nbsp;&nbsp;${item.fileName}
											</c:when> 
											<c:otherwise> 
												&nbsp;&nbsp;&nbsp;${item.fileName}
											</c:otherwise>
										</c:choose>
								   </td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;
								   <a href="javascript:del('${item.id}');">删除</a></td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width='15%'>信息内容</th>
					<td colspan="3" style="padding: 0">
						<textarea id="infoContent" name="wzInfoManage.infoContent" style="width:100%; height:430px;">${wzInfoManage.infoContent}</textarea>
					</td>
				</tr>
				<!--<tr>
					<th width="15%">信息类型</th>
					<td width="35%"><input name="wzInfoManage.infoType" value="${wzInfoManage.infoType}" type="text" maxlength="1"></td>
					<th width="15%">点击量</th>
					<td width="35%"><input name="wzInfoManage.clickTime" value="${wzInfoManage.clickTime}" type="text" datatype="n1-20" errormsg='此项请填入整数' ignore="ignore" maxlength="10"></td>
				</tr>
				--><tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_wzInfoManage');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
