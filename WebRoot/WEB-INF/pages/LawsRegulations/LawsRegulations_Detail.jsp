<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script src="${ctx}/webResources/kindeditor/kindeditor.js"></script>
	<script src="${ctx}/webResources/kindeditor/lang/zh_CN.js"></script>
	<script src="${ctx}/webResources/kindeditor/plugins/code/prettify.js"></script>
	<script type="text/javascript">
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
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">标题</th>
					<td colspan="3">${lawsRegulations.title}</td>
				</tr>
				<tr>
					<th width="15%">发布时间</th>
					<td width="35%"><fmt:formatDate type="both" value="${lawsRegulations.fbtime}" /></td>
					<th width="15%">生效时间</th>
					<td width="35%"><fmt:formatDate type="both" value="${lawsRegulations.sxtime}" /></td>
				</tr>
				<tr>
					<th width="15%">发布人</th>
					<td width="35%">${lawsRegulations.fabr}</td>
					<th width="15%">状态</th>
					<td width="35%"><cus:hxlabel codeName="法规状态" itemValue="${lawsRegulations.fgstatus}" /></td>
				</tr>
				<tr>
					<th width="15%">法规级别</th>
					<td width="35%">${lawsRegulations.regutionsid}</td>
					<th width="15%">行业分类</th>
					<td width="35%"><cus:hxlabel codeName="企业行业分类" itemValue="${lawsRegulations.hyfl}" /></td>
				</tr>
				<tr>
					<th width="15%">
						法规内容
					</th>
					<td colspan="3">
						<textarea id="infoContent" name="lawsRegulations.content"
										style="width: 100%; height: 230px;" readonly="readonly">
											${lawsRegulations.content}
										</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center">
						<center>
							<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeLayer();">关闭</a>
						</center>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
