<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify1","${cheSafCon.linkId}","chesafecondition1","chesafeconditionfj1","fileQueue1");
		  uploadPic("uploadify2","${cheSafCon.linkId}","chesafecondition2","chesafeconditionfj2","fileQueue2");
		  uploadPic("uploadify3","${cheSafCon.linkId}","chesafecondition3","chesafeconditionfj3","fileQueue3");
		  uploadPic("uploadify4","${cheSafCon.linkId}","chesafecondition4","chesafeconditionfj4","fileQueue4");
		  uploadPic("uploadify5","${cheSafCon.linkId}","chesafecondition5","chesafeconditionfj5","fileQueue5");
		  uploadPic("uploadify6","${cheSafCon.linkId}","chesafecondition6","chesafeconditionfj6","fileQueue6");
		  uploadPic("uploadify7","${cheSafCon.linkId}","chesafecondition7","chesafeconditionfj7","fileQueue7");
		  uploadPic("uploadify8","${cheSafCon.linkId}","chesafecondition8","chesafeconditionfj8","fileQueue8");
		  uploadPic("uploadify9","${cheSafCon.linkId}","chesafecondition9","chesafeconditionfj9","fileQueue9");
		  uploadPic("uploadify10","${cheSafCon.linkId}","chesafecondition10","chesafeconditionfj10","fileQueue10");
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
	<form name="myform1" method="post" enctype="multipart/form-data" action="cheSafConSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="cheSafCon.id" value="${cheSafCon.id}">
		<input type="hidden" name="cheSafCon.createTime" value="<fmt:formatDate type="both" value="${cheSafCon.createTime}" />">
		<input type="hidden" name="cheSafCon.updateTime" value="${cheSafCon.updateTime}">
		<input type="hidden" name="cheSafCon.createUserID" value="${cheSafCon.createUserID}">
		<input type="hidden" name="cheSafCon.updateUserID" value="${cheSafCon.updateUserID}">
		<input type="hidden" name="cheSafCon.deptId" value="${cheSafCon.deptId}">
		<input type="hidden" name="cheSafCon.delFlag" value="${cheSafCon.delFlag}">
		<input type="hidden" name="cheSafCon.linkId" value="${cheSafCon.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag property="cheSafCon.areaId" style="width: 60%" defaultText='请选择' codeName="企业属地" value="${cheSafCon.areaId}" onchange="clearCompany()" dataType="*1-127" nullmsg='所在区域不能为空！' sucmsg='所在区域填写正确！'/><font style='color:red'>*</font></td>
					<th width="15%">企业名称</th> 
					<td width="35%"><input id="companyName" name="cheSafCon.companyName" style="width: 60%" value="${cheSafCon.companyName}" type="text" readonly="readonly" onclick="queryQy()" dataType="*1-127"  nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'/><font style='color:red'>*</font>
						<input type="hidden" id="companyId" name="cheSafCon.companyId" value="${cheSafCon.companyId}"/>
					</td>
				</tr>
				<tr>
					<th width="15%">评价机构名称</th>
					<td width="35%"><input name="cheSafCon.ratingAgenciesName" style="width: 60%" value="${cheSafCon.ratingAgenciesName}" type="text" datatype="*1-127" errormsg='评价机构名称必须是1到127位字符！' nullmsg='评价机构名称不能为空！' sucmsg='评价机构名称填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">项目内容</th>
					<td width="35%"><input name="cheSafCon.projectContent" style="width: 60%" value="${cheSafCon.projectContent}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">项目性质</th>
					<td width="35%"><cus:SelectOneTag property="cheSafCon.projectNature" style="width: 60%" defaultText='请选择' codeName="项目性质" value="${cheSafCon.projectNature}" /></td>
					<th width="15%">项目类型</th>
					<td width="35%"><cus:SelectOneTag property="cheSafCon.projectType" style="width: 60%" defaultText='请选择' codeName="项目类型" value="${cheSafCon.projectType}" /></td>
				</tr>
				<tr>
					<th width="15%">审查专家</th>
					<td width="35%"><input name="cheSafCon.expertReview" style="width: 60%" value="${cheSafCon.expertReview}" type="text" maxlength="127"></td>
					<th width="15%">审查日期</th>
					<td width="35%"><input name="cheSafCon.reviewDate" style="width: 60%" value="<fmt:formatDate type='date' value='${cheSafCon.reviewDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">审查结果</th>
					<td width="35%"><input name="cheSafCon.review" style="width: 60%" value="${cheSafCon.review}" type="text" maxlength="127"></td>
					<th width="15%">审批编号</th>
					<td width="35%"><input name="cheSafCon.approvalNum" style="width: 60%" value="${cheSafCon.approvalNum}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">审批日期</th>
					<td width="35%"><input name="cheSafCon.approvalDate" style="width: 60%" value="<fmt:formatDate type='date' value='${cheSafCon.approvalDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">材料接收人员</th>
					<td width="35%"><input name="cheSafCon.receptName" style="width: 60%" value="${cheSafCon.receptName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">材料审查人员</th>
					<td width="35%"><input name="cheSafCon.reviewName" style="width: 60%" value="${cheSafCon.reviewName}" type="text" maxlength="127"></td>
					<th width="15%">申请材料是否齐全</th>
					<td width="35%"><cus:SelectOneTag property="cheSafCon.isComplete" style="width: 60%" defaultText='请选择' codeName="是或否" value="${cheSafCon.isComplete}" /></td>
				</tr>
				<tr>
					<th width="15%">档案编号</th>
					<td width="35%"><input name="cheSafCon.fileNo" value="${cheSafCon.fileNo}" style="width: 60%" type="text" maxlength="127"></td>
					<th width="15%">材料审查情况</th>
					<td width="35%"><input name="cheSafCon.materialReview" style="width: 60%" value="${cheSafCon.materialReview}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">签字领导</th>
					<td width="35%"><input name="cheSafCon.signLeader" style="width: 60%" value="${cheSafCon.signLeader}" type="text" maxlength="127"></td>
					<th width="15%">行政许可建议</th>
					<td width="35%"><input name="cheSafCon.proposal" style="width: 60%" value="${cheSafCon.proposal}" type="text" maxlength="127"></td>
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
						<table id="chesafeconditionfj1">
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
					<th width="15%">危险化学品生产、储存企业设立和新建改建扩建项目安全审查批准书申请书</th>
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
						<table id="chesafeconditionfj2">
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
					<th width="15%">项目批准、核准或备案证明</th>
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
						<table id="chesafeconditionfj3">
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
					<th width="15%">具有法定资质的工程咨询机构出具的项目可行性研究报告（含项目安全条件论证专篇）或具有法定资质的中介机构出具的项目安全条件论证报告</th>
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
						<table id="chesafeconditionfj4">
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
					<th width="15%">具有法定资质的安全评价机构出具的项目安全预评价报告</th>
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
						<table id="chesafeconditionfj5">
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
					<th width="15%">项目最终产品、中间产品或储存的危险化学品的产品安全技术说明书或具有法定资质单位出具的产品理化性能指标资料</th>
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
						<table id="chesafeconditionfj6">
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
					<th width="15%">采用的生产工艺、设备或储存方式、设施的情况说明</th>
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
						<table id="chesafeconditionfj7">
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
					<th width="15%">拟建工厂、仓库与周边建筑物、设施的安全防护距离的情况说明</th>
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
						<table id="chesafeconditionfj8">
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
					<th width="15%">事故应急救援措施</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue9"/>
				    	<input type="file" name="uploadify9" id="uploadify9"/>
			    		<a href="javascript:jQuery('#uploadify9').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify9').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="chesafeconditionfj9">
							  <c:forEach var="item" items="${picList9}">
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
					<th width="15%">法律法规规定的其它材料</th>
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
						<table id="chesafeconditionfj10">
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_cheSafCon');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
