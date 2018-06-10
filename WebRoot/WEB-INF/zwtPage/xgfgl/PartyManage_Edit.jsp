<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify1","${partyManage.linkId}","xgfgl","zzzmfj","fileQueue1");
		  uploadPic("uploadify2","${partyManage.linkId}","xgfgl","aqxyfj","fileQueue2");
		  uploadPicOnly("uploadify3","${partyManage.linkId}","xgfgl","xcjcfj","fileQueue3");
		});
		
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="partyManageSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="partyManage.id" value="${partyManage.id}">
		<input type="hidden" name="partyManage.createTime" value="<fmt:formatDate type="both" value="${partyManage.createTime}" />">
		<input type="hidden" name="partyManage.updateTime" value="${partyManage.updateTime}">
		<input type="hidden" name="partyManage.createUserID" value="${partyManage.createUserID}">
		<input type="hidden" name="partyManage.updateUserID" value="${partyManage.updateUserID}">
		<input type="hidden" name="partyManage.deptId" value="${partyManage.deptId}">
		<input type="hidden" name="partyManage.delFlag" value="${partyManage.delFlag}">
		<input type="hidden" name="partyManage.areaId" value="${partyManage.areaId}">
		<input type="hidden" name="partyManage.areaName" value="${partyManage.areaName}">
		<input type="hidden" name="partyManage.companyId" value="${partyManage.companyId}">
		<input type="hidden" name="partyManage.companyName" value="${partyManage.companyName}">
		<input type="hidden" name="partyManage.linkId" value="${partyManage.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">相关方名称</th>
					<td width="35%"><input name="partyManage.partyName" style="width:60%;" value="${partyManage.partyName}" type="text" datatype="*1-127" errormsg='相关方名称必须是1到127位字符！' nullmsg='相关方名称不能为空！' sucmsg='相关方名称填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">服务类型</th>
					<td width="35%"><input name="partyManage.serviceType" style="width:60%;" value="${partyManage.serviceType}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">行为风险</th>
					<td width="96%" colspan="3"><textarea name="partyManage.behaveRisk" style="width:96%;height:30px"  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${partyManage.behaveRisk}</textarea></td>
				</tr>
				<tr>
					<th width="15%">控制措施</th>
					<td width="96%" colspan="3"><textarea name="partyManage.controlMeasures" style="width:96%;height:30px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${partyManage.controlMeasures}</textarea></td>
				</tr>
				<tr>
					<th width="15%">义务</th>
					<td width="96%" colspan="3"><textarea name="partyManage.obligation" style="width:96%;height:30px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${partyManage.obligation}</textarea></td>
				</tr>
				<tr>
					<th width="15%">安全生产责任</th>
					<td width="96%" colspan="3"><textarea name="partyManage.safetyProDuty" style="width:96%;height:30px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${partyManage.safetyProDuty}</textarea></td>
				</tr>
				<tr>
					<th width="10%">资质证明</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue1"/>
				    	<input type="file" name="uploadify1" id="uploadify1"/>
			    		<a href="javascript:jQuery('#uploadify1').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify1').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3"  style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="zzzmfj">
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
								   <a href="javascript:del('${item.id}');">删除</a>
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				
				<tr>
					<th width="10%">安全协议</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue2"/>
				    	<input type="file" name="uploadify2" id="uploadify2"/>
			    		<a href="javascript:jQuery('#uploadify2').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify2').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="aqxyfj">
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
								   <a href="javascript:del('${item.id}');">删除</a>
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				
				<tr>
					<th width="10%">现场检查图片</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue3"/>
				    	<input type="file" name="uploadify3" id="uploadify3"/>
			    		<a href="javascript:jQuery('#uploadify3').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify3').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="xcjcfj">
							  <c:forEach var="item" items="${picList3}">
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
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea name="partyManage.remark" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:96%;height:30px">${partyManage.remark}</textarea></td>
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
