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
		    var editor = K.create('textarea[name="lawBase.content"]', {
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
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: auto;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: ">
	<form name="myform1" method="post" enctype="multipart/form-data" action="lawBaseSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="lawBase.id" value="${lawBase.id}">
		<input type="hidden" name="lawBase.createTime" value="<fmt:formatDate type="both" value="${lawBase.createTime}" />">
		<input type="hidden" name="lawBase.updateTime" value="${lawBase.updateTime}">
		<input type="hidden" name="lawBase.createUserID" value="${lawBase.createUserID}">
		<input type="hidden" name="lawBase.updateUserID" value="${lawBase.updateUserID}">
		<input type="hidden" name="lawBase.deptId" value="${lawBase.deptId}">
		<input type="hidden" name="lawBase.delFlag" value="${lawBase.delFlag}">
			<table width="100%" border="0"> 
				<tr>
					<th width="15%">执法依据名称</th>
					<td colspan="3"><input style="width:80%" name="lawBase.lawName" value="${lawBase.lawName}" type="text" datatype="*1-127" errormsg='法律法规名称必须是1到127位字符！' nullmsg='法律法规名称不能为空！' sucmsg='法律法规名称填写正确！' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">索引号</th>
					<td width="35%"><input style="width:60%" name="lawBase.syh" value="${lawBase.syh}" type="text" maxlength="127"></td>
					<th width="15%">发布机构</th>
					<td width="35%"><input style="width:60%" name="lawBase.fbjg" value="${lawBase.fbjg}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">发布日期</th>
					<td width="35%"><input name="lawBase.createDay" value="<fmt:formatDate type='date' value='${lawBase.createDay}'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  maxlength="127" style="width:60%"></td>
					<th width="15%">实施日期</th>
					<td width="35%"><input name="lawBase.pubDay" value="<fmt:formatDate type='date' value='${lawBase.pubDay}'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">内容概述</th>
					<td colspan="3">
						<textarea name="lawBase.gs" style="width: 80%; height: 100px;" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${lawBase.gs}</textarea>
					</td>
				</tr>
				<tr>
					<th width='15%'>执法依据内容</th>
					<td colspan="3" style="padding: 0">
						<textarea name="lawBase.content" style="width: 100%; height: 230px;">${lawBase.content}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="document.myform1.reset()">重置<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_lawBase');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
