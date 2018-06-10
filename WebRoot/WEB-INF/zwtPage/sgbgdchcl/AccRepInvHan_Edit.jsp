<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
		<script>
	$(document).ready(function() {
		  uploadPic("uploadify1","${accRepInvHan.linkId}","sgbgdchcl1","sgbgdchclfj1","fileQueue1");
		  uploadPic("uploadify2","${accRepInvHan.linkId}","sgbgdchcl2","sgbgdchclfj2","fileQueue2");
		});
		
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
			<font style='color:red'>*走工伤理赔程序的事故必须上传</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="accRepInvHanSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="accRepInvHan.id" value="${accRepInvHan.id}">
		<input type="hidden" name="accRepInvHan.createTime" value="<fmt:formatDate type="both" value="${accRepInvHan.createTime}" />">
		<input type="hidden" name="accRepInvHan.updateTime" value="${accRepInvHan.updateTime}">
		<input type="hidden" name="accRepInvHan.createUserID" value="${accRepInvHan.createUserID}">
		<input type="hidden" name="accRepInvHan.updateUserID" value="${accRepInvHan.updateUserID}">
		<input type="hidden" name="accRepInvHan.deptId" value="${accRepInvHan.deptId}">
		<input type="hidden" name="accRepInvHan.delFlag" value="${accRepInvHan.delFlag}">
		<input type="hidden" name="accRepInvHan.linkId" value="${accRepInvHan.linkId}">
			<table width="100%" border="0">
				
				<tr>
					<th width="15%">事故编号</th>
					<td width="35%"><input name="accRepInvHan.accidentId" style="width:60%" value="${accRepInvHan.accidentId}" type="text" maxlength="127"></td>
					<th width="15%">事故名称</th>
					<td width="35%"><input name="accRepInvHan.accidentName" style="width:60%" value="${accRepInvHan.accidentName}" type="text" datatype="*1-127" errormsg='事故名称必须是1到127位字符！' nullmsg='事故名称不能为空！' sucmsg='事故名称填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">事故发生时间</th>
					<td width="35%"><input name="accRepInvHan.accidentTime" style="width:60%" value="<fmt:formatDate type='date' value='${accRepInvHan.accidentTime}' pattern="yyyy-MM-dd" />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">事故经过</th>
					<td width="35%"><input name="accRepInvHan.accidentDescrip" style="width:60%" value="${accRepInvHan.accidentDescrip}" type="text" maxlength="2000"></td>
				</tr>
				<tr>
					<th width="15%">事故原因</th>
					<td width="35%"><input name="accRepInvHan.accidentReason" style="width:60%" value="${accRepInvHan.accidentReason}" type="text" maxlength="2000"></td>
					<th width="15%">轻伤人数</th>
					<td width="35%"><input name="accRepInvHan.concussionsNum" style="width:60%" value="${accRepInvHan.concussionsNum}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">重伤人数</th>
					<td width="35%"><input name="accRepInvHan.woundedNum" style="width:60%" value="${accRepInvHan.woundedNum}" type="text" maxlength="127"></td>
					<th width="15%">死亡人数</th>
					<td width="35%"><input name="accRepInvHan.deathNum" style="width:60%" value="${accRepInvHan.deathNum}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">经济损失</th>
					<td width="35%"><input name="accRepInvHan.economicLoss" style="width:60%" value="${accRepInvHan.economicLoss}" type="text" maxlength="127"></td>
					<th width="15%">事故级别</th>
					<td width="35%"><cus:SelectOneTag property="accRepInvHan.accidentLevel" style="width:60%" defaultText='请选择' codeName="事故级别" value="${accRepInvHan.accidentLevel}" /></td>
				</tr>
				<tr>
					<th width="15%">事故类别</th>
					<td width="35%"><cus:SelectOneTag property="accRepInvHan.accidentType" style="width:60%" defaultText='请选择' codeName="事故类别" value="${accRepInvHan.accidentType}" /></td>
					<th width="15%">调查组成员</th>
					<td width="35%"><input name="accRepInvHan.inverstTeamNumber" style="width:60%" value="${accRepInvHan.inverstTeamNumber}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">事故责任</th>
					<td width="35%"><input name="accRepInvHan.accidentResponsible" style="width:60%" value="${accRepInvHan.accidentResponsible}" type="text" maxlength="127"></td>
					<th width="15%">处理建议</th>
					<td width="35%"><input name="accRepInvHan.handleSuggest" style="width:60%" value="${accRepInvHan.handleSuggest}" type="text" maxlength="127"></td>
				</tr>
				<tr>
				<th width="15%">整改措施</th>
					<td width="85%" colspan="3"><input name="accRepInvHan.method" style="width:96%" value="${accRepInvHan.method}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="accRepInvHan.remark" style="width:96%;height:60px">${accRepInvHan.remark}</textarea></td>
				</tr>
				<tr>
					<th width="15%">事故图片</th>
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
						<div style="color:green;overflow:auto;height:175px;">
						<table id="sgbgdchclfj1">
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
					<th width="15%">整改后图片</th>
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
						<div style="color:green;overflow:auto;height:175px;">
						<table id="sgbgdchclfj2">
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
