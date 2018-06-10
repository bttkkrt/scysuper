<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
		<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${comOpeRul.linkId}","zdwxyczgc","zdwxyczgcfj","fileQueue");
		});
		
	function queryQy()
		{
			popupCenter("${ctx}/jsp/czgc/opeProLists.action", "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
		}
		
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="comOpeRulSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="comOpeRul.id" value="${comOpeRul.id}">
		<input type="hidden" name="comOpeRul.createTime" value="<fmt:formatDate type="both" value="${comOpeRul.createTime}" />">
		<input type="hidden" name="comOpeRul.updateTime" value="${comOpeRul.updateTime}">
		<input type="hidden" name="comOpeRul.createUserID" value="${comOpeRul.createUserID}">
		<input type="hidden" name="comOpeRul.updateUserID" value="${comOpeRul.updateUserID}">
		<input type="hidden" name="comOpeRul.deptId" value="${comOpeRul.deptId}">
		<input type="hidden" name="comOpeRul.delFlag" value="${comOpeRul.delFlag}">
		<input type="hidden" name="comOpeRul.linkId" value="${comOpeRul.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">操作规程名称</th>
					<td width="35%">
						<input id="yjname" name="comOpeRul.ruleName" style="width:60%" value="${comOpeRul.ruleName}" type="text" datatype="*1-127" errormsg='操作规程名称必须是1到127位字符！' nullmsg='操作规程名称不能为空！' sucmsg='操作规程名称填写正确！'  maxlength="127" onclick="queryQy()"><font style='color:red'>*</font>
						<input type="hidden" id="yjid" name="opePro.id" value="${opePro.id}"/>
					</td>
					<th width="15%">重点危险源名称</th>
					<td width="35%"><input id="dangerName" style="width:60%" name="comOpeRul.dangerName" value="${comOpeRul.dangerName}" type="text" datatype="*1-127" errormsg='重点危险源必须是1到127位字符！' nullmsg='重点危险源不能为空！' sucmsg='重点危险源填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">重点危险源类别</th>
					<td width="35%"><input name="comOpeRul.dangerType" style="width:60%" value="${comOpeRul.dangerType}" type="text"  maxlength="127"></td>
					<th width="15%">重点危险源级别</th>
					<td width="35%"><input name="comOpeRul.dangerLevel" style="width:60%" value="${comOpeRul.dangerLevel}" type="text"  maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="comOpeRul.remark" style="width:96%;height:60px">${comOpeRul.remark}</textarea></td>
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
						<table id="zdwxyczgcfj">
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
 					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
