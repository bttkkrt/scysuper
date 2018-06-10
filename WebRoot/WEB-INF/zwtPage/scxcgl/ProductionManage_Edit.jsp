<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
		<script>
	$(document).ready(function() {
		  uploadPic("uploadify1","${productionManage.linkId}","scxcgl1","scxcglfj1","fileQueue1");
		  uploadPicOnly("uploadify2","${productionManage.linkId}","scxcgl2","scxcglfj2","fileQueue2");
		});
		
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
			<font style='color:red'>*填写高危作业信息，一个施工点填写一条信息，项目负责人填写企业内部负责本次施工的人员信息，施工负责人填写施工方负责人信息</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="productionManageSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="productionManage.id" value="${productionManage.id}">
		<input type="hidden" name="productionManage.createTime" value="<fmt:formatDate type="both" value="${productionManage.createTime}" />">
		<input type="hidden" name="productionManage.updateTime" value="${productionManage.updateTime}">
		<input type="hidden" name="productionManage.createUserID" value="${productionManage.createUserID}">
		<input type="hidden" name="productionManage.updateUserID" value="${productionManage.updateUserID}">
		<input type="hidden" name="productionManage.deptId" value="${productionManage.deptId}">
		<input type="hidden" name="productionManage.delFlag" value="${productionManage.delFlag}">
		<input type="hidden" name="productionManage.linkId" value="${productionManage.linkId}">
			<table width="100%" border="0">
				
				<tr>
					<th width="15%">项目负责人</th>
					<td width="35%"><input name="productionManage.personInCharge" style="width:60%;" value="${productionManage.personInCharge}" type="text" datatype="*1-127" errormsg='负责人必须是1到127位字符！' nullmsg='负责人不能为空！' sucmsg='负责人填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">施工负责人</th>
					<td width="35%"><input name="productionManage.personName" style="width:60%;" value="${productionManage.personName}" type="text"   maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">作业时间</th>
					<td width="35%"><input name="productionManage.jobTime" style="width:60%;" value="<fmt:formatDate type='date' value='${productionManage.jobTime}'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">作业类型</th>
					<td width="85%" colspan="3"><cus:hxcheckbox property="productionManage.jobType" codeName="生产作业类型" value="${productionManage.jobType}"  />（可多选）</td>
				</tr>
				<tr>
					<th width="15%">作业内容</th>
					<td width="85%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="productionManage.jobContent" style="width:96%;height:60px">${productionManage.jobContent}</textarea></td>
				</tr>
				<tr>
					<th width="15%">危害因素分析</th>
					<td width="85%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="productionManage.hazardAnalysiss" style="width:96%;height:60px">${productionManage.hazardAnalysiss}</textarea></td>
				</tr>
				<tr>
					<th width="15%">安全措施</th>
					<td width="85%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="productionManage.safeMeasures" style="width:96%;height:60px">${productionManage.safeMeasures}</textarea></td>
				</tr>
				<tr>
					<th width="15%">应急措施</th>
					<td width="85%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="productionManage.emerMeasure" style="width:96%;height:60px">${productionManage.emerMeasure}</textarea></td>
				</tr>
				<tr>
					<th width="15%">作业许可证</th>
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
						<table id="scxcglfj1">
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
					<th width="15%">施工图片</th>
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
						<table id="scxcglfj2">
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
