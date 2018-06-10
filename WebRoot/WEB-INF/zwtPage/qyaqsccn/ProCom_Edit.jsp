<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${proCom.linkId}","qyaqsccn","qyaqsccnfj","fileQueue");
		});
		
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="proComSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="proCom.id" value="${proCom.id}">
		<input type="hidden" name="proCom.createTime" value="<fmt:formatDate type="both" value="${proCom.createTime}" />">
		<input type="hidden" name="proCom.updateTime" value="${proCom.updateTime}">
		<input type="hidden" name="proCom.createUserID" value="${proCom.createUserID}">
		<input type="hidden" name="proCom.updateUserID" value="${proCom.updateUserID}">
		<input type="hidden" name="proCom.deptId" value="${proCom.deptId}">
		<input type="hidden" name="proCom.delFlag" value="${proCom.delFlag}">
		<input type="hidden" name="proCom.linkId" value="${proCom.linkId}">
		
			<table width="100%" border="0">
			<!-- 	<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><input name="proCom.areaName" value="${proCom.areaName}" type="text" maxlength="127" style="width:60%"></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="proCom.companyName" value="${proCom.companyName}" type="text" maxlength="127" style="width:60%"></td>
				</tr> -->
				<tr>
					<th width="15%">年度</th>
					<td width="35%"><input name="proCom.commitmentYear" value="<fmt:formatDate type='date' value='${proCom.commitmentYear}' pattern="yyyy"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy'})" datatype="*1-127" errormsg='年度必须是1到127位字符！' nullmsg='年度不能为空！' sucmsg='年度填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">承诺内容</th>
					<td width="85%" colspan="3"><textarea errormsg='承诺内容必须是1到2000位字符！' nullmsg='承诺内容不能为空！' sucmsg='承诺内容填写正确！'  datatype="*1-2000" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="proCom.commitmentContent" style="width:80%;height:60px">${proCom.commitmentContent}</textarea><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea name="proCom.remark" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:80%;height:60px">${proCom.remark}</textarea></td>
				</tr>
				<tr>
					<th width="10%">附件（带总经理签名的附件）</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue"/>
				    	<input type="file" name="uploadify" id="uploadify"/>
			    		<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="qyaqsccnfj">
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
								   <a href="javascript:del('${item.id}');">删除</a>
								   </td>
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
