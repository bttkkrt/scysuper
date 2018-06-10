<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${occHeaMan.linkid}","zywsaqglzd","zdfj","fileQueue");
		});
		
</script>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="occHeaManSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="occHeaMan.id" value="${occHeaMan.id}">
		<input type="hidden" name="occHeaMan.createTime" value="<fmt:formatDate type="both" value="${occHeaMan.createTime}" />">
		<input type="hidden" name="occHeaMan.updateTime" value="${occHeaMan.updateTime}">
		<input type="hidden" name="occHeaMan.createUserID" value="${occHeaMan.createUserID}">
		<input type="hidden" name="occHeaMan.updateUserID" value="${occHeaMan.updateUserID}">
		<input type="hidden" name="occHeaMan.deptId" value="${occHeaMan.deptId}">
		<input type="hidden" name="occHeaMan.delFlag" value="${occHeaMan.delFlag}">
		<input type="hidden" name="occHeaMan.uploadTime" value="<fmt:formatDate type="both" value="${occHeaMan.uploadTime}" />">
		<input type="hidden" name="occHeaMan.linkid" value="${occHeaMan.linkid}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">制度名称</th>
					<td width="85%" colspan="3"><input name="occHeaMan.systemName" style="width:60%" value="${occHeaMan.systemName}" type="text" datatype="*1-127" errormsg='制度名称必须是1到127位字符！' nullmsg='制度名称不能为空！' sucmsg='制度名称填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">制度类型</th>
					<td width="85%" colspan="3"><cus:hxcheckbox property="occHeaMan.systemType" codeName="职业卫生安全制度类型" value="${occHeaMan.systemType}" datatype="*1-127" errormsg='制度类型必须是1到127位字符！' nullmsg='制度类型不能为空！' sucmsg='制度类型填写正确！'  maxlength="127"/><font style='color:red'>*（可多选）</font></td>
				</tr>
				<tr>
					<th width="15%">制度附件</th>
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
						<table id="zdfj">
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