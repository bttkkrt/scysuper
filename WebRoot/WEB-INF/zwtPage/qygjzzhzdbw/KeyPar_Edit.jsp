<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify1","${keyPar.linkId}","qygjzzhzdbw1","qygjzzhzdbwfj1","fileQueue1");
		  uploadPic("uploadify2","${keyPar.linkId}","qygjzzhzdbw2","qygjzzhzdbwfj2","fileQueue2");
		});
	
   
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="keyParSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="keyPar.id" value="${keyPar.id}">
		<input type="hidden" name="keyPar.createTime" value="<fmt:formatDate type="both" value="${keyPar.createTime}" />">
		<input type="hidden" name="keyPar.updateTime" value="${keyPar.updateTime}">
		<input type="hidden" name="keyPar.createUserID" value="${keyPar.createUserID}">
		<input type="hidden" name="keyPar.updateUserID" value="${keyPar.updateUserID}">
		<input type="hidden" name="keyPar.deptId" value="${keyPar.deptId}">
		<input type="hidden" name="keyPar.delFlag" value="${keyPar.delFlag}">
		<input type="hidden" name="keyPar.linkId" value="${keyPar.linkId}">
			<table width="100%" border="0">
			
				<tr>
					<th width="15%">关键装置重点部位名称</th>
					<td width="35%"><input name="keyPar.keyPartName" style="width:60%;" value="${keyPar.keyPartName}" type="text" datatype="*1-127" errormsg='名称必须是1到127位字符！' nullmsg='名称不能为空！' sucmsg='名称填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">主要危险因素</th>
					<td width="35%"><input name="keyPar.majorRiskFactors" style="width:60%;" value="${keyPar.majorRiskFactors}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					
					<th width="15%">责任人</th>
					<td width="35%"><input name="keyPar.responsiblePerson" style="width:60%;" value="${keyPar.responsiblePerson}" type="text" datatype="*1-127" errormsg='责任人必须是1到127位字符！' nullmsg='责任人不能为空！' sucmsg='责任人填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">岗位员工数量</th>
					<td width="35%"><input name="keyPar.postEmployeeNumber" style="width:60%;" value="${keyPar.postEmployeeNumber}" type="text" datatype="*1-127" errormsg='岗位员工数量必须是1到127位字符！' nullmsg='岗位员工数量不能为空！' sucmsg='岗位员工数量填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">安全操作规程</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue1"/>
				    	<input type="file" name="uploadify1" id="uploadify1"/>
			    		<a href="javascript:jQuery('#uploadify1').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify1').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
						
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:80px;">
						<table id="qygjzzhzdbwfj1">
							  <c:forEach var="item" items="${picList1}">
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
					<th width="15%">现场图片</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue2"/>
				    	<input type="file" name="uploadify2" id="uploadify2"/>
			    		<a href="javascript:jQuery('#uploadify2').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify2').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:80px;">
						<table id="qygjzzhzdbwfj2">
							  <c:forEach var="item" items="${picList2}">
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
