<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${traInf.linkId}","pxjcxx","pxfj","fileQueue");
		});
		
</script>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="traInfSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="traInf.id" value="${traInf.id}">
		<input type="hidden" name="traInf.createTime" value="<fmt:formatDate type="both" value="${traInf.createTime}" />">
		<input type="hidden" name="traInf.updateTime" value="${traInf.updateTime}">
		<input type="hidden" name="traInf.createUserID" value="${traInf.createUserID}">
		<input type="hidden" name="traInf.updateUserID" value="${traInf.updateUserID}">
		<input type="hidden" name="traInf.deptId" value="${traInf.deptId}">
		<input type="hidden" name="traInf.delFlag" value="${traInf.delFlag}">
		<input type="hidden" name="traInf.linkId" value="${traInf.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">培训主题</th>
					<td width="35%"><input name="traInf.trainTheme" style="width: 60%" value="${traInf.trainTheme}" type="text" datatype="*1-127" errormsg='培训主题必须是1到127位字符！' nullmsg='培训主题不能为空！' sucmsg='培训主题填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">培训开始时间</th>
					<td width="35%"><input name="traInf.startTime" style="width: 60%" id="trainingTime" value="<fmt:formatDate type='date' value='${traInf.startTime}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'trainingTimeEnd\')}'})" datatype="*1-127" errormsg='培训开始时间必须是1到127位字符！' nullmsg='培训开始时间不能为空！' sucmsg='培训开始时间填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">培训结束时间</th>
					<td width="35%"><input name="traInf.endTime"  style="width: 60%" id="trainingTimeEnd" value="<fmt:formatDate type='date' value='${traInf.endTime}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'trainingTime\')}'})" datatype="*1-127" errormsg='培训结束时间必须是1到127位字符！' nullmsg='培训结束时间不能为空！' sucmsg='培训结束时间填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">培训地点</th>
					<td width="35%"><input name="traInf.trainAddress" style="width: 60%" value="${traInf.trainAddress}" type="text" datatype="*1-127" errormsg='培训地点必须是1到127位字符！' nullmsg='培训地点不能为空！' sucmsg='培训地点填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">培训单位</th>
					<td width="35%"><input name="traInf.trainTeacher" style="width: 60%" value="${traInf.trainTeacher}" type="text" datatype="*1-127" errormsg='培训单位必须是1到127位字符！' nullmsg='培训单位不能为空！' sucmsg='培训单位填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">培训方式</th>
					<td width="35%"><input name="traInf.trainMethod" style="width: 60%" value="${traInf.trainMethod}" type="text" datatype="*1-127" errormsg='培训方式必须是1到127位字符！' nullmsg='培训方式不能为空！' sucmsg='培训方式填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">发证数</th>
					<td width="35%"><input name="traInf.certificateNum" style="width: 60%" value="${traInf.certificateNum}" type="text" datatype="*1-127" errormsg='发证数必须是1到127位字符！' nullmsg='发证数不能为空！' sucmsg='发证数填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea name="traInf.remark" style="width:96%;height:90px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${traInf.remark}</textarea></td>
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
						<table id="pxfj">
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
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_traInf');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
