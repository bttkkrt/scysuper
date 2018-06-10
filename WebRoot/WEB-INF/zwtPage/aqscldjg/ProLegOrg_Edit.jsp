<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
		$(document).ready(function() {
		  uploadPic("uploadify","${proLegOrg.linkId}","aqscldjg","aqscldjgfj","fileQueue");
		});
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
			<font style='color:red'>*主要负责人填写总经理信息，执行人填写实际负责安全生产的人员信息</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="proLegOrgSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="proLegOrg.id" value="${proLegOrg.id}">
		<input type="hidden" name="proLegOrg.createTime" value="<fmt:formatDate type="both" value="${proLegOrg.createTime}" />">
		<input type="hidden" name="proLegOrg.updateTime" value="${proLegOrg.updateTime}">
		<input type="hidden" name="proLegOrg.createUserID" value="${proLegOrg.createUserID}">
		<input type="hidden" name="proLegOrg.updateUserID" value="${proLegOrg.updateUserID}">
		<input type="hidden" name="proLegOrg.deptId" value="${proLegOrg.deptId}">
		<input type="hidden" name="proLegOrg.delFlag" value="${proLegOrg.delFlag}">
		<input type="hidden" name="proLegOrg.linkId" value="${proLegOrg.linkId}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">机构名称</th>
					<td width="35%"><input name="proLegOrg.orgenizationName" value="${proLegOrg.orgenizationName}" type="text" datatype="*1-127" errormsg='机构名称必须是1到127位字符！' nullmsg='机构名称不能为空！' sucmsg='机构名称填写正确！'   maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">成员数量</th>
					<td width="35%"><input name="proLegOrg.orgenizationMenberCount"  value="${proLegOrg.orgenizationMenberCount}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">主要负责人</th>
					<td width="35%"><input name="proLegOrg.orgenizationCharge" value="${proLegOrg.orgenizationCharge}" datatype="*1-127" errormsg='负责人必须是1到127位字符！' nullmsg='负责人不能为空！' sucmsg='负责人填写正确！'   type="text" maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">主要负责人邮箱</th>
					<td width="35%"><input name="proLegOrg.orgenizationChargeEmail"    value="${proLegOrg.orgenizationChargeEmail}" type="text" maxlength="127" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">主要负责人联系方式1</th>
					<td width="35%"><input name="proLegOrg.orgenizationChargePhone" value="${proLegOrg.orgenizationChargePhone}" type="text" datatype="m"   nullmsg='负责人联系方式1不能为空！' sucmsg='负责人联系方式1填写正确！' maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">主要负责人联系方式2</th>
					<td width="35%"><input name="proLegOrg.orgenizationChargePhone2" value="${proLegOrg.orgenizationChargePhone2}" type="text" datatype="m" ignore="ignore" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">执行人</th>
					<td width="35%"><input name="proLegOrg.orgenizationDone" value="${proLegOrg.orgenizationDone}" datatype="*1-127" errormsg='执行人必须是1到127位字符！' nullmsg='执行人不能为空！' sucmsg='执行人填写正确！'   type="text" maxlength="127" style="width:60%" ><font style='color:red'>*</font></td>
					<th width="15%">执行人联系方式</th>
					<td width="35%"><input name="proLegOrg.orgenizationDonePhone" value="${proLegOrg.orgenizationDonePhone}" type="text" datatype="m"   nullmsg='执行人联系方式不能为空！' sucmsg='执行人联系方式填写正确！' maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">机构职责</th>
					<td width="85%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="proLegOrg.orgenizationResponsibility" style="width:96%;height:60px">${proLegOrg.orgenizationResponsibility}</textarea></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="proLegOrg.orgenizationRemark" style="width:96%;height:60px">${proLegOrg.orgenizationRemark}</textarea></td>
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
						<table id="aqscldjgfj">
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
