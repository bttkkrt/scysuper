<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script src="${ctx}/webResources/kindeditor/kindeditor.js"></script>
	<script src="${ctx}/webResources/kindeditor/lang/zh_CN.js"></script>
	<script src="${ctx}/webResources/kindeditor/plugins/code/prettify.js"></script>
	
	<script>
		KindEditor.ready(function(K) {
		    var editor = K.create('textarea[name="lawsRegulations.content"]', {
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
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="lawsRegulationsSave.action";
				document.myform1.submit();
			}
		}
		//页面展示办公用品类别树
		$(function(){
			getSuppliesTypeTree('mfSuppliesTypeParentId','citySel');
		});
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="lawsRegulations.id" value="${lawsRegulations.id}">
		<input type="hidden" name="lawsRegulations.createTime" value="<fmt:formatDate type="both" value="${lawsRegulations.createTime}" />">
		<input type="hidden" name="lawsRegulations.updateTime" value="${lawsRegulations.updateTime}">
		<input type="hidden" name="lawsRegulations.createUserID" value="${lawsRegulations.createUserID}">
		<input type="hidden" name="lawsRegulations.updateUserID" value="${lawsRegulations.updateUserID}">
		<input type="hidden" name="lawsRegulations.deptId" value="${lawsRegulations.deptId}">
		<input type="hidden" name="lawsRegulations.delFlag" value="${lawsRegulations.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">标题</th>
					<td colspan="3"><input style="width: 97.2%;" name="lawsRegulations.title" value="${lawsRegulations.title}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">发布时间</th>
					<td width="35%"><input name="lawsRegulations.fbtime" style="width: 93%;" value="<fmt:formatDate type='both' value='${lawsRegulations.fbtime}' />" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"><font style="color:red">*</font></td>
					<th width="15%">生效时间</th>
					<td width="35%"><input name="lawsRegulations.sxtime" style="width: 93%;" value="<fmt:formatDate type='both' value='${lawsRegulations.sxtime}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
				</tr>
				<tr>
					<th width="15%">发布人</th>
					<td width="35%"><input name="lawsRegulations.fabr" style="width: 93%;" value="${lawsRegulations.fabr}" type="text" maxlength="50" readonly="readonly"></td>
					<th width="15%">状态</th>
					<td width="35%">
						<cus:SelectOneTag style="width:93%"  property="lawsRegulations.fgstatus"  codeName="法规状态"
									value="${lawsRegulations.fgstatus}" datatype="*1-8" errormsg='此项为必填' maxlength="9" />
					</td>
				</tr>
				<tr>
					<th width="15%">法规级别</th>
					<td width="35%">
						<input id="citySel" type="text" value="${lawsRegulations.regutionsid }"></input><font style="color:red">*</font>
						<input type="hidden" name="lawsRegulations.regutionsid"
							id="mfSuppliesTypeParentId"
							value="${lawsRegulations.regutionsid }">
					</td>
					<th width="15%">行业分类</th>
					<td width="35%">
						<cus:SelectOneTag property="lawsRegulations.hyfl" defaultText='请选择' codeName="企业行业分类" value="${lawsRegulations.hyfl}" dataType="Require" msg="此项为必选" style="width: 93%;" /><font style="color:red">*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">
						法规内容
					</th>
					<td colspan="3">
						<textarea id="infoContent" name="lawsRegulations.content" style="width: 100%; height: 230px;">${lawsRegulations.content}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center">
						<center>
							<s:if test="flag=='add'">
								<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
							</s:if>
							<s:else>
								<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
							</s:else>						
							<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
							<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeLayer();">关闭</a>
						</center>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
