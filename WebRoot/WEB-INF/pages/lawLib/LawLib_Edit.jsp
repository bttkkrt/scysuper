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
		$(document).ready(function() {
			  uploadPdfOnly("uploadify","${lawLib.linkId}","lawLib","lawLibfj","fileQueue");
			});
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: auto;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: ">
	<form name="myform1" method="post" enctype="multipart/form-data" action="lawLibSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="lawLib.id" value="${lawLib.id}">
		<input type="hidden" name="lawLib.createTime" value="<fmt:formatDate type="both" value="${lawLib.createTime}" />">
		<input type="hidden" name="lawLib.updateTime" value="${lawLib.updateTime}">
		<input type="hidden" name="lawLib.createUserID" value="${lawLib.createUserID}">
		<input type="hidden" name="lawLib.updateUserID" value="${lawLib.updateUserID}">
		<input type="hidden" name="lawLib.companyId" value="${lawLib.companyId}">
		<input type="hidden" name="lawLib.deptId" value="${lawLib.deptId}">
		<input type="hidden" name="lawLib.delFlag" value="${lawLib.delFlag}">
		<input type="hidden" name="lawLib.linkId" value="${lawLib.linkId}">
		<input type="hidden" name="lawLib.state" value="${lawLib.state}">
			<table width="100%" border="0"> 
				<tr>
					<th width="15%">法律法规名称</th>
					<td width="35%"><input style="width:60%" name="lawLib.lawName" value="${lawLib.lawName}" type="text" datatype="*1-127" errormsg='法律法规名称必须是1到127位字符！' nullmsg='法律法规名称不能为空！' sucmsg='法律法规名称填写正确！' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">法律法规类型</th>
					<td width="35%"><s:select name="lawLib.type" list="#{'1':'法律','2':'法规','3':'规章','4':'规范性文件','5':'技术标准'}"  theme="simple"  cssStyle="width:60%"/></td>
				</tr>
				<tr>
					<th width="15%">索引号</th>
					<td width="35%"><input style="width:60%" name="lawLib.syh" value="${lawLib.syh}" type="text" maxlength="127"></td>
					<th width="15%">发布机构</th>
					<td width="35%"><input style="width:60%" name="lawLib.fbjg" value="${lawLib.fbjg}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">发布日期</th>
					<td width="35%"><input name="lawLib.createDay" value="<fmt:formatDate type='date' value='${lawLib.createDay}'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  maxlength="127" style="width:60%"></td>
					<th width="15%">实施日期</th>
					<td width="35%"><input name="lawLib.pubDay" value="<fmt:formatDate type='date' value='${lawLib.pubDay}'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">内容概述</th>
					<td colspan="3">
						<textarea name="lawLib.gs" style="width: 80%; height: 100px;" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${lawLib.gs}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">附件</th>
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
						<table id="lawLibfj">
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_lawLib');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
