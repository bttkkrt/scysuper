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
	KindEditor.ready(function(K) {
		    var editor = K.create('textarea[name="lxwm.infoContent"]', {
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
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="lxwmSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="lxwm.id" value="${lxwm.id}">
		<input type="hidden" name="lxwm.createTime" value="<fmt:formatDate type="both" value="${lxwm.createTime}" />">
		<input type="hidden" name="lxwm.updateTime" value="${lxwm.updateTime}">
		<input type="hidden" name="lxwm.createUserID" value="${lxwm.createUserID}">
		<input type="hidden" name="lxwm.updateUserID" value="${lxwm.updateUserID}">
		<input type="hidden" name="lxwm.deptId" value="${lxwm.deptId}">
		<input type="hidden" name="lxwm.delFlag" value="${lxwm.delFlag}">
		
			<table width="100%" border="0">
				<tr>
							<th width='15%'>
						            内容
					       </th>
								<td colspan="3" style="padding: 0">
									<textarea id="infoContent" name="lxwm.infoContent" style="width: 100%; height: 230px;">${lxwm.infoContent}</textarea>
								</td>
							</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					
						
							<a href="#" class="btn_01" type="submit" >保存<b></b></a>&nbsp;
						
						
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
