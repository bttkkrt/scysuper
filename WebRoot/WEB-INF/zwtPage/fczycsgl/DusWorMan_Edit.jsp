<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
		<script>
	$(document).ready(function() {
		  uploadPic("uploadify1","${dusWorMan.linkId}","fczycsgl1","fczycsglfj1","fileQueue1");
		  uploadPic("uploadify2","${dusWorMan.linkId}","fczycsgl2","fczycsglfj2","fileQueue2");
		  uploadPic("uploadify3","${dusWorMan.linkId}","fczycsgl3","fczycsglfj3","fileQueue3");
		  uploadPic("uploadify4","${dusWorMan.linkId}","fczycsgl4","fczycsglfj4","fileQueue4");
		  uploadPic("uploadify5","${dusWorMan.linkId}","fczycsgl5","fczycsglfj5","fileQueue5");
		});
		
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="dusWorManSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="dusWorMan.id" value="${dusWorMan.id}">
		<input type="hidden" name="dusWorMan.createTime" value="<fmt:formatDate type="both" value="${dusWorMan.createTime}" />">
		<input type="hidden" name="dusWorMan.updateTime" value="${dusWorMan.updateTime}">
		<input type="hidden" name="dusWorMan.createUserID" value="${dusWorMan.createUserID}">
		<input type="hidden" name="dusWorMan.updateUserID" value="${dusWorMan.updateUserID}">
		<input type="hidden" name="dusWorMan.deptId" value="${dusWorMan.deptId}">
		<input type="hidden" name="dusWorMan.delFlag" value="${dusWorMan.delFlag}">
		<input type="hidden" name="dusWorMan.linkId" value="${dusWorMan.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">作业场所名称</th>
					<td width="35%"><input name="dusWorMan.workplaceName" style="width:60%" value="${dusWorMan.workplaceName}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">所属行业</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.agencyResponsible" style="width:60%" defaultText='请选择' codeName="粉尘行业" value="${dusWorMan.agencyResponsible}" /></td>
				</tr>
				<tr>
					<th width="15%">粉尘种类</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.industryType"  style="width:60%" defaultText='请选择'codeName="粉尘种类" value="${dusWorMan.industryType}" /></td>
					<th width="15%">除尘器种类</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.dustWiperType" style="width:60%" defaultText='请选择' codeName="除尘器种类" value="${dusWorMan.dustWiperType}" /></td>
				</tr>
				<tr>
					<th width="15%">企业规模</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.enterpriseScale" style="width:60%" defaultText='请选择' codeName="粉尘企业规模" value="${dusWorMan.enterpriseScale}" /></td>
					<th width="15%">从业人数</th>
					<td width="35%"><input name="dusWorMan.employeeNumber" style="width:60%" value="${dusWorMan.employeeNumber}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">车间结构</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.workshopStructure" style="width:60%" defaultText='请选择' codeName="粉尘车间结构" value="${dusWorMan.workshopStructure}" /></td>
					<th width="15%">车间布局</th>
					<td width="35%"><input name="dusWorMan.workshopLayout" style="width:60%" value="${dusWorMan.workshopLayout}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">最近一次粉尘检测的日期</th>
					<td width="35%"><input name="dusWorMan.recentlyDustDetectTime" style="width:60%" value="<fmt:formatDate type='date' value='${dusWorMan.recentlyDustDetectTime}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">检测值</th>
					<td width="35%"><input name="dusWorMan.testValue" value="${dusWorMan.testValue}" style="width:60%" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">是否合格</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.isQualified" style="width:60%" defaultText='请选择' codeName="是或否" value="${dusWorMan.isQualified}" /></td>
					<th width="15%">作业方式</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.operationMode" style="width:60%" defaultText='请选择' codeName="粉尘作业方式" value="${dusWorMan.operationMode}" /></td>
				</tr>
				<tr>
					<th width="15%">是否有除尘器</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.hasDustWiper" style="width:60%" defaultText='请选择' codeName="是或否" value="${dusWorMan.hasDustWiper}" /></td>
					<th width="15%">除尘形式</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.dustRemovalForm" style="width:60%" defaultText='请选择' codeName="粉尘除尘形式" value="${dusWorMan.dustRemovalForm}" /></td>
				</tr>
				<tr>
					<th width="15%">除尘器数量</th>
					<td width="35%"><input name="dusWorMan.dustWiperNumber" style="width:60%" value="${dusWorMan.dustWiperNumber}" type="text" maxlength="127"></td>
					<th width="15%">投入使用时间</th>
					<td width="35%"><input name="dusWorMan.wiperInUseTime" style="width:60%" value="<fmt:formatDate type='date' value='${dusWorMan.wiperInUseTime}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">除尘器是否经环保部门验收</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.isWiperAccepted" style="width:60%" defaultText='请选择' codeName="是或否" value="${dusWorMan.isWiperAccepted}" /></td>
					<th width="15%">除尘系统是否设置隔爆阀</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.hasExplosionProof" style="width:60%" defaultText='请选择' codeName="是或否" value="${dusWorMan.hasExplosionProof}" /></td>
				</tr>
				<tr>
					<th width="15%">除尘器是否有泄爆口</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.hasVentPort" style="width:60%" defaultText='请选择' codeName="是或否" value="${dusWorMan.hasVentPort}" /></td>
					<th width="15%">泄爆口位置</th>
					<td width="35%"><input name="dusWorMan.ventPortPosition" style="width:60%" value="${dusWorMan.ventPortPosition}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">除尘器是否在负压下工作</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.isWorkUnderNegative" style="width:60%" defaultText='请选择' codeName="是或否" value="${dusWorMan.isWorkUnderNegative}" /></td>
					<th width="15%">除尘器是否安装于室外</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.isInstalledOutdoor" style="width:60%" defaultText='请选择' codeName="是或否" value="${dusWorMan.isInstalledOutdoor}" /></td>
				</tr>
				<tr>
					<th width="15%">是否有自动卸灰锁气装置</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.hasAutoUnloadLock" style="width:60%" defaultText='请选择' codeName="是或否" value="${dusWorMan.hasAutoUnloadLock}" /></td>
					<th width="15%">除尘器目前状态</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.dustWiperCurrentStatus" style="width:60%" defaultText='请选择' codeName="粉尘除尘器目前状态" value="${dusWorMan.dustWiperCurrentStatus}" /></td>
				</tr>
				<tr>
					<th width="15%">企业目前状态</th>
					<td width="35%"><cus:SelectOneTag property="dusWorMan.enterpriseCurrentStatus" style="width:60%" defaultText='请选择' codeName="企业目前状态" value="${dusWorMan.enterpriseCurrentStatus}" /></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="dusWorMan.remark" style="width:96%;height:60px">${dusWorMan.remark}</textarea></td>
				</tr>
				<tr>
					<th width="15%">隔爆阀照片</th>
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
						<table id="fczycsglfj1">
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
					<th width="15%">泄爆口照片</th>
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
						<table id="fczycsglfj2">
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
					<th width="15%">自动卸灰锁气装置图片</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue3"/>
				    	<input type="file" name="uploadify3" id="uploadify3"/>
			    		<a href="javascript:jQuery('#uploadify3').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify3').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="fczycsglfj3">
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
								   <a href="javascript:del('${item.id}');">删除</a></td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>	
				
				<tr>
					<th width="15%">作业场所图片</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue4"/>
				    	<input type="file" name="uploadify4" id="uploadify4"/>
			    		<a href="javascript:jQuery('#uploadify4').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify4').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="fczycsglfj4">
							  <c:forEach var="item" items="${picList4}">
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
					<th width="15%">除尘器全景图片</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue5"/>
				    	<input type="file" name="uploadify5" id="uploadify5"/>
			    		<a href="javascript:jQuery('#uploadify5').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify5').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="fczycsglfj5">
							  <c:forEach var="item" items="${picList5}">
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
