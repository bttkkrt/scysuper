<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify1","${occConDes.linkId}","occcondesign1","occcondesignfj1","fileQueue1");
		   uploadPic("uploadify2","${occConDes.linkId}","occcondesign2","occcondesignfj2","fileQueue2");
		    uploadPic("uploadify3","${occConDes.linkId}","occcondesign3","occcondesignfj3","fileQueue3");
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
	<form name="myform1" method="post" enctype="multipart/form-data" action="occConDesSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="occConDes.id" value="${occConDes.id}">
		<input type="hidden" name="occConDes.createTime" value="<fmt:formatDate type="both" value="${occConDes.createTime}" />">
		<input type="hidden" name="occConDes.updateTime" value="${occConDes.updateTime}">
		<input type="hidden" name="occConDes.createUserID" value="${occConDes.createUserID}">
		<input type="hidden" name="occConDes.updateUserID" value="${occConDes.updateUserID}">
		<input type="hidden" name="occConDes.deptId" value="${occConDes.deptId}">
		<input type="hidden" name="occConDes.delFlag" value="${occConDes.delFlag}">
		<input type="hidden" name="occConDes.linkId" value="${occConDes.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
				    <td width="35%"><cus:SelectOneTag property="occConDes.areaId" style="width: 60%" defaultText='请选择' codeName="企业属地" value="${occConDes.areaId}" onchange="clearCompany()" dataType="*1-127" nullmsg='所在区域不能为空！' sucmsg='所在区域填写正确！'/><font style='color:red'>*</font></td>
				    <th width="15%">企业名称</th>
					<td width="35%"><input id="companyName" name="occConDes.companyName" style="width: 60%" value="${occConDes.companyName}" type="text" readonly="readonly" onclick="queryQy()" dataType="*1-127"  nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'/><font style='color:red'>*</font>
						<input type="hidden" id="companyId" name="occConDes.companyId" value="${occConDes.companyId}"/>
					</td>
				</tr> 
				<tr>
					<th width="15%">行业类别</th>
					<td width="35%"><cus:SelectOneTag property="occConDes.industryCategory" style="width: 60%" defaultText='请选择' codeName="行业类别" value="${occConDes.industryCategory}" /></td>
					<th width="15%">职业病危害风险分类</th>
					<td width="35%"><cus:SelectOneTag style="width: 60%" property="occConDes.occupationalClassification" defaultText='请选择' codeName="职业病危害风险分类" value="${occConDes.occupationalClassification}" /></td>
				</tr>
				<tr>
					<th width="15%">设计单位</th>
					<td width="35%"><input name="occConDes.designUnit" style="width: 60%" value="${occConDes.designUnit}" type="text" datatype="*1-127" errormsg='设计单位必须是1到127位字符！' nullmsg='设计单位不能为空！' sucmsg='设计单位填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">项目内容</th>
					<td width="35%"><input name="occConDes.projectContent" style="width: 60%" value="${occConDes.projectContent}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">项目性质</th>
					<td width="35%"><cus:SelectOneTag property="occConDes.projectNature" style="width: 60%" defaultText='请选择' codeName="项目性质" value="${occConDes.projectNature}" /></td>
					<th width="15%">材料接收人员</th>
					<td width="35%"><input name="occConDes.receptName" style="width: 60%" value="${occConDes.receptName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">材料审查人员</th>
					<td width="35%"><input name="occConDes.reviewName" style="width: 60%" value="${occConDes.reviewName}" type="text" maxlength="127"></td>
					<th width="15%">审查专家</th>
					<td width="35%"><input name="occConDes.expertReview" style="width: 60%" value="${occConDes.expertReview}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">审查结果</th>
					<td width="35%"><input name="occConDes.review" style="width: 60%" value="${occConDes.review}" type="text" maxlength="127"></td>
					<th width="15%">审查日期</th>
					<td width="35%"><input name="occConDes.reviewDate" style="width: 60%" value="<fmt:formatDate type='date' value='${occConDes.reviewDate}' pattern="yyyy-MM-dd" />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">审批日期</th>
					<td width="35%"><input name="occConDes.approvalDate" style="width: 60%" value="<fmt:formatDate type='date' value='${occConDes.approvalDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">审批编号</th>
					<td width="35%"><input name="occConDes.approvalNum" style="width: 60%" value="${occConDes.approvalNum}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">档案编号</th>
					<td width="35%"><input name="occConDes.fileNo" style="width: 60%" value="${occConDes.fileNo}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">职业病防护设施设计专篇</th>
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
						<table id="occcondesignfj1">
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
					<th width="15%">职业病防护设施设计专家审查意见及审查会签到表</th>
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
						<table id="occcondesignfj2">
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
					<th width="15%">建设项目职业病防护设施安监部门的审批文件</th>
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
						<table id="occcondesignfj3">
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
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_occConDes');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
