<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script src="${ctx}/webResources/kindeditor/kindeditor.js"></script>
		<script src="${ctx}/webResources/kindeditor/lang/zh_CN.js"></script>
		<script src="${ctx}/webResources/kindeditor/plugins/code/prettify.js"></script>
	<script>
	
	$(document).ready(function() {
		  uploadPic("uploadify","${receiveInformation.linkId}","receiveInformation","receiveInformationfj","fileQueue");
		});
		
		function selectUser()
		{
			popupCenter("${ctx}/jsp/swgl/selectUsers.action", "setUser", "800", "600", "no", "no", "no", "no", "no","no");
		}
		
		KindEditor.ready(function(K) {
		    var editor = K.create('textarea[name="infoContent"]', {
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
		</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="receiveInformationSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="receiveInformation.id" value="${receiveInformation.id}">
		<input type="hidden" name="receiveInformation.createTime" value="<fmt:formatDate type="both" value="${receiveInformation.createTime}" />">
		<input type="hidden" name="receiveInformation.updateTime" value="${receiveInformation.updateTime}">
		<input type="hidden" name="receiveInformation.createUserID" value="${receiveInformation.createUserID}">
		<input type="hidden" name="receiveInformation.updateUserID" value="${receiveInformation.updateUserID}">
		<input type="hidden" name="receiveInformation.deptId" value="${receiveInformation.deptId}">
		<input type="hidden" name="receiveInformation.delFlag" value="${receiveInformation.delFlag}">
		<input type="hidden" name="receiveInformation.recinfoDept" value="${user.dept.deptName}">
		<input type="hidden" name="receiveInformation.linkId" value="${receiveInformation.linkId}">
						<table width="100%" border="0">
							<tr>
								<th width="15%">
									来文标题
								</th>
								<td colspan="3">
									<input name="receiveInformation.recinfoTitle"   id="infoTitle"
										value="${receiveInformation.recinfoTitle}" type="text" datatype="*1-50"  nullmsg='请输入标题  ' sucmsg='标题填写正确！' style="width: 80%" >
									<font style='color:red'>*</font>
								</td>
							</tr>
							<tr>
								<th width="15%">来文编号</th>
								<td width="35%"><input name="receiveInformation.recinfoNum" style="width: 60%" value="${receiveInformation.recinfoNum}" type="text" maxlength="32"></td>
								<th width="15%">来文类型</th>
								<td width="35%">
									<cus:SelectOneTag property="receiveInformation.recinfoType" style="width: 60%" defaultText='请选择' codeName="来文类型" value="${receiveInformation.recinfoType}" />
								</td>
							</tr>
							<tr>
						   <th width="15%">来文单位</th>
					       <td width="35%">${user.dept.deptName}</td>
	                       </tr>
				<tr>
					<th width='15%'>
						发送给
					</th>
					<td colspan='3'>
						<textarea rows="5" style="width:85%;word-break:break-all;word-wrap:break-word;" readonly="readonly" 
						name="receiveInformation.userNames" id="userNames" datatype="*"  nullmsg='请选择人员  ' sucmsg='人员填写正确！'>${receiveInformation.userNames}</textarea><font style='color:red'>*</font>
						<a href="###" class="btn_01_mini1" onclick="selectUser();">选择</a>
						<input type="hidden" id ="userIds" name="receiveInformation.userIds" value="${receiveInformation.userIds}">
					</td>
				</tr>
				
					<tr>
					<th width="15%">附件上传</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue"/>
				    	<input type="file" name="uploadify" id="uploadify"/>
			    		<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="receiveInformationfj">
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
							<th width='15%'>
						            来文内容
					       </th>
								<td colspan="3" style="padding: 0">
									<textarea id="infoContent" name="infoContent" style="width: 100%; height: 230px;" datatype="*"  nullmsg='请填写来文内容  ' sucmsg='来文内容填写正确！'>${infoContent}</textarea><font style='color:red'>*</font>
								</td>
							</tr>
							
							
						<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="###" class="btn_01" type="submit">保存<b></b> </a>
						</s:if>
						<s:else>
							<a href="###" class="btn_01" type="submit">更新<b></b> </a>
						</s:else>

					
						<a href="###" class="btn_01"
							onclick="parent.close_win('win_receiveInformation');">关闭<b></b> </a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
