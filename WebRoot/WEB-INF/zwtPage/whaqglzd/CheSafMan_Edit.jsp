<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${cheSafMan.linkId}","whaqglzd","whaqglzdfj","fileQueue");
		});
		
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="cheSafManSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="cheSafMan.id" value="${cheSafMan.id}">
		<input type="hidden" name="cheSafMan.createTime" value="<fmt:formatDate type="both" value="${cheSafMan.createTime}" />">
		<input type="hidden" name="cheSafMan.updateTime" value="${cheSafMan.updateTime}">
		<input type="hidden" name="cheSafMan.createUserID" value="${cheSafMan.createUserID}">
		<input type="hidden" name="cheSafMan.updateUserID" value="${cheSafMan.updateUserID}">
		<input type="hidden" name="cheSafMan.deptId" value="${cheSafMan.deptId}">
		<input type="hidden" name="cheSafMan.delFlag" value="${cheSafMan.delFlag}">
		<input type="hidden" name="cheSafMan.linkId" value="${cheSafMan.linkId}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">制度名称</th>
					<td width="85%" colspan="3"><input name="cheSafMan.systemName" value="${cheSafMan.systemName}" style="width: 60%" type="text" errormsg='制度名称必须是1到127位字符！' nullmsg='制度名称不能为空！' sucmsg='制度名称填写正确！'   datatype="*1-127"  maxlength="127"><font style='color:red'>*（可多选）</font></td>
				</tr>
				<tr>
					<th width="15%">制度类型</th>
					<td width="85%" colspan="3"><cus:hxcheckbox property="cheSafMan.systemType" codeName="危化安全管理制度类型" value="${cheSafMan.systemType}" /></td>
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
						<table id="whaqglzdfj">
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
