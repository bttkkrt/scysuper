<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify1","${cheSafDes.linkId}","chesafedesign1","chesafedesignfj1","fileQueue1");
		  uploadPic("uploadify2","${cheSafDes.linkId}","chesafedesign2","chesafedesignfj2","fileQueue2");
		  uploadPic("uploadify3","${cheSafDes.linkId}","chesafedesign3","chesafedesignfj3","fileQueue3");
		  uploadPic("uploadify4","${cheSafDes.linkId}","chesafedesign4","chesafedesignfj4","fileQueue4");
		  uploadPic("uploadify5","${cheSafDes.linkId}","chesafedesign5","chesafedesignfj5","fileQueue5");
		  uploadPic("uploadify6","${cheSafDes.linkId}","chesafedesign6","chesafedesignfj6","fileQueue6");
		  uploadPic("uploadify7","${cheSafDes.linkId}","chesafedesign7","chesafedesignfj7","fileQueue7");
		  uploadPic("uploadify8","${cheSafDes.linkId}","chesafedesign8","chesafedesignfj8","fileQueue8");
		  uploadPic("uploadify10","${cheSafDes.linkId}","chesafedesign10","chesafedesignfj10","fileQueue10");
		});
		function queryQy()
		{
			var szzid = document.getElementById('areaId').value;
			popupCenter("${ctx}/jsp/qyjbxx/queryCompanyList.action?flag=idmcqy&entBaseInfo.enterprisePossession="+szzid, "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
		}
		  function clearCompany(){
        	document.getElementById("companyName").value="";
        	document.getElementById("companyId").value="";
        }
   
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="cheSafDesSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="cheSafDes.id" value="${cheSafDes.id}">
		<input type="hidden" name="cheSafDes.createTime" value="<fmt:formatDate type="both" value="${cheSafDes.createTime}" />">
		<input type="hidden" name="cheSafDes.updateTime" value="${cheSafDes.updateTime}">
		<input type="hidden" name="cheSafDes.createUserID" value="${cheSafDes.createUserID}">
		<input type="hidden" name="cheSafDes.updateUserID" value="${cheSafDes.updateUserID}">
		<input type="hidden" name="cheSafDes.deptId" value="${cheSafDes.deptId}">
		<input type="hidden" name="cheSafDes.delFlag" value="${cheSafDes.delFlag}">
		<input type="hidden" name="cheSafDes.linkId" value="${cheSafDes.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag property="cheSafDes.areaId" style="width: 50%" defaultText='请选择' codeName="企业属地" value="${cheSafDes.areaId}" onchange="clearCompany()" dataType="*1-127" nullmsg='所在区域不能为空！' sucmsg='所在区域填写正确！'/><font style='color:red'>*</font></td>
					<th width="15%">企业名称</th> 
					<td width="35%"><input id="companyName" name="cheSafDes.companyName" style="width: 50%" value="${cheSafDes.companyName}" type="text" readonly="readonly" onclick="queryQy()" dataType="*1-127"  nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'/><font style='color:red'>*</font>
						<input type="hidden" id="companyId" name="cheSafDes.companyId" value="${cheSafDes.companyId}"/>
					</td>
				</tr>
				<tr>
					<th width="15%">设计单位</th>
					<td width="35%"><input name="cheSafDes.designUnit" value="${cheSafDes.designUnit}" style="width: 50%" type="text" datatype="*1-127" errormsg='设计单位必须是1到127位字符！' nullmsg='设计单位不能为空！' sucmsg='设计单位填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">项目内容</th>
					<td width="35%"><input name="cheSafDes.projectContent" style="width: 50%" value="${cheSafDes.projectContent}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">项目性质</th>
					<td width="35%"><cus:SelectOneTag property="cheSafDes.projectNature" style="width: 50%" defaultText='请选择' codeName="项目性质" value="${cheSafDes.projectNature}" /></td>
					<th width="15%">项目类型</th>
					<td width="35%"><cus:SelectOneTag property="cheSafDes.projectType" style="width: 50%" defaultText='请选择' codeName="项目类型" value="${cheSafDes.projectType}" /></td>
				</tr>
				<tr>
					<th width="15%">审查专家</th>
					<td width="35%"><input name="cheSafDes.expertReview" style="width: 50%" value="${cheSafDes.expertReview}" type="text" maxlength="127"></td>
					<th width="15%">审查日期</th>
					<td width="35%"><input name="cheSafDes.reviewDate" style="width: 50%" value="<fmt:formatDate type='date' value='${cheSafDes.reviewDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">审批日期</th>
					<td width="35%"><input name="cheSafDes.approvalDate" style="width: 50%" value="<fmt:formatDate type='date' value='${cheSafDes.approvalDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">审批编号</th>
					<td width="35%"><input name="cheSafDes.approvalNum" style="width: 50%" value="${cheSafDes.approvalNum}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">材料审查人员</th>
					<td width="35%"><input name="cheSafDes.reviewName" style="width: 50%" value="${cheSafDes.reviewName}" type="text" maxlength="127"></td>
					<th width="15%">材料接收人员</th>
					<td width="35%"><input name="cheSafDes.receptName" style="width: 50%" value="${cheSafDes.receptName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">申请材料是否齐全</th>
					<td width="35%"><cus:SelectOneTag property="cheSafDes.isComplete" style="width: 50%" defaultText='请选择' codeName="是或否" value="${cheSafDes.isComplete}" /></td>
					<th width="15%">档案编号</th>
					<td width="35%"><input name="cheSafDes.fileNo" style="width: 50%" value="${cheSafDes.fileNo}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">材料审查情况</th>
					<td width="35%"><input name="cheSafDes.materialReview" style="width: 50%" value="${cheSafDes.materialReview}" type="text" maxlength="127"></td>
					<th width="15%">签字领导</th>
					<td width="35%"><input name="cheSafDes.signLeader" style="width: 50%" value="${cheSafDes.signLeader}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">行政许可建议</th>
					<td width="35%"><input name="cheSafDes.proposal" style="width: 50%" value="${cheSafDes.proposal}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">会审记录</th>
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
						<table id="chesafedesignfj1">
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
					<th width="15%">建设项目安全设施设计的审查申请表</th>
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
						<table id="chesafedesignfj2">
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
					<th width="15%">设计单位的设计资质证明文件</th>
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
						<table id="chesafedesignfj3">
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
					<th width="15%">经过专家审查并修改确认后的建设项目安全设施设计专篇</th>
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
						<table id="chesafedesignfj4">
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
					<th width="15%">危险化学品建设项目安全条件审查意见书（扫描件）</th>
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
						<table id="chesafedesignfj5">
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
					<th width="15%">投资管理部门出具的建设项目审批（核准、备案）文件（扫描件）</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue6"/>
				    	<input type="file" name="uploadify6" id="uploadify6"/>
			    		<a href="javascript:jQuery('#uploadify6').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify6').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="chesafedesignfj6">
							  <c:forEach var="item" items="${picList6}">
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
					<th width="15%">规划管理部门出具的规划许可文件（扫描件）</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue7"/>
				    	<input type="file" name="uploadify7" id="uploadify7"/>
			    		<a href="javascript:jQuery('#uploadify7').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify7').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="chesafedesignfj7">
							  <c:forEach var="item" items="${picList7}">
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
					<th width="15%">危险化学品建设项目安全审查专家审查意见（扫描件）</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue8"/>
				    	<input type="file" name="uploadify8" id="uploadify8"/>
			    		<a href="javascript:jQuery('#uploadify8').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify8').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="chesafedesignfj8">
							  <c:forEach var="item" items="${picList8}">
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
					<th width="15%">危险化学品建设项目安全审查审查表</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue10"/>
				    	<input type="file" name="uploadify10" id="uploadify10"/>
			    		<a href="javascript:jQuery('#uploadify10').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify10').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="chesafedesignfj10">
							  <c:forEach var="item" items="${picList10}">
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
