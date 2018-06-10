<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><s:if test="flag=='add'">新增</s:if> <s:else>修改</s:else>记录</title>
		<%@include file="/common/jsLib.jsp"%>
		<script src="${ctx}/webResources/kindeditor/kindeditor.js"></script>
		<script src="${ctx}/webResources/kindeditor/lang/zh_CN.js"></script>
		<script src="${ctx}/webResources/kindeditor/plugins/code/prettify.js"></script>

		<script>
		$(document).ready(function() {
		  uploadPic("uploadify","${gzdt.linkId}","wzInfo","wzInfofj","fileQueue");
		});

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
		<form name="myform1" method="post" enctype="multipart/form-data" action="gzdtSave.action">
				<s:token />
				<input type="hidden" name="flag" value="${flag}">
				<input type="hidden" name="gzdt.id"
					value="${gzdt.id}">
				<input type="hidden" name="gzdt.createTime"
					value="<fmt:formatDate type="both" value="${gzdt.createTime}" />">
				<input type="hidden" name="gzdt.updateTime"
					value="${gzdt.updateTime}">
				<input type="hidden" name="gzdt.createUserID"
					value="${gzdt.createUserID}">
				<input type="hidden" name="gzdt.updateUserID"
					value="${gzdt.updateUserID}">
				<input type="hidden" name="gzdt.deptId"
					value="${gzdt.deptId}">
				<input type="hidden" name="gzdt.delFlag"
					value="${gzdt.delFlag}">
				<input type="hidden" name="gzdt.userId"
					value="${gzdt.userId}">
					<input type="hidden" name="gzdt.linkId" value="${gzdt.linkId}">
					<input type="hidden" name="gzdt.readNum" value="${gzdt.readNum}">
				<div class="inner6px">
					<div class="cell">
						<table>
							<tr>
								<th width="15%">
									第一标题
							</th>
							<td colspan="3">
								<input name="gzdt.firTitle"  id="firTitle" value="${gzdt.firTitle}" type="text" maxlength="127" style="width:80%">
							</td>
							</tr>
							<tr>
								<th width="15%">
									第二标题
								</th>
								<td colspan="3">
									<input name="gzdt.infoTitle"  id="infoTitle"
										value="${gzdt.infoTitle}" type="text" datatype="*1-127"
										nullmsg='请输入标题  ' sucmsg='标题填写正确！'
										class="form_text" maxlength="127" style="width:80%">
									<span class="red">*</span>
								</td>
							</tr>
							<tr>
								<th width="15%">
									发布人
								</th>
								<s:if test="flag=='add'">
									<td width="35%">
										${user.displayName}
									</td>
								</s:if>
								<s:if test="flag=='mod'">
									<td width="35%">
										${gzdt.user.displayName}
									</td>
								</s:if>
								<th width="15%">
									所属部门
								</th>
								<s:if test="flag=='add'">
									<td width="35%">
										${user.dept.deptName}
									</td>
								</s:if>
								<s:if test="flag=='mod'">
									<td width="35%">
										${gzdt.dept.deptName}
									</td>
								</s:if>
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
							<th width='15%'>
						            工作动态内容
					       </th>
								<td colspan="3" style="padding: 0">
									<textarea id="infoContent" name="infoContent" style="width: 100%; height: 230px;">${infoContent}</textarea>
								</td>
							</tr>
						</table>
					</div>

				</div>
				<div class="inner6px">
					<div class="btn_area_setc">
						<s:if test="flag=='add'">
							<a href="###" class="btn_01" type="submit">保存<b></b> </a>
						</s:if>
						<s:else>
							<a href="###" class="btn_01" type="submit">更新<b></b> </a>
						</s:else>
						<a href="###" class="btn_01"
							onclick="parent.close_win('win_gzdt');">关闭<b></b> </a>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
