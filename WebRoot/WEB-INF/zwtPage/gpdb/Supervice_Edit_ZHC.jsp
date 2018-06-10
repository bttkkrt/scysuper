<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script type="text/javascript">
		$(document).ready(function() {
			  uploadPic("uploadify1","${supervice.linkId}","gpdb","jcwsfj","fileQueue1");
			  uploadPicOnly("uploadify2","${supervice.linkId}","gpdb","zgqtpfj","fileQueue2");
			  uploadPic("uploadify3","${supervice.linkId}","gpdb","zgfafj","fileQueue3");
			  uploadPic("uploadify4","${supervice.linkId}","gpdb","ffcsfj","fileQueue4");
			  
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
        
        function changDiv(obj)
        {
        	if(obj == 1)
        	{
        		document.getElementById('div1').style.display = "";
        	}
        	else
        	{
        		document.getElementById('div1').style.display = "none";
        	}
        }
        
        function showSgInfo(obj)
        {
        	if(obj == 1)
        	{
        		document.getElementById('sgdiv1').style.display = "";
        		document.getElementById('sgdiv2').style.display = "";
        		document.getElementById('sgdiv3').style.display = "";
        	}
        	else
        	{
        		document.getElementById('sgdiv1').style.display = "none";
        		document.getElementById('sgdiv2').style.display = "none";
        		document.getElementById('sgdiv3').style.display = "none";
        	}
        }
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="superviceSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="supervice.id" value="${supervice.id}">
		<input type="hidden" name="supervice.createTime" value="<fmt:formatDate type="both" value="${supervice.createTime}" />">
		<input type="hidden" name="supervice.updateTime" value="${supervice.updateTime}">
		<input type="hidden" name="supervice.createUserID" value="${supervice.createUserID}">
		<input type="hidden" name="supervice.updateUserID" value="${supervice.updateUserID}">
		<input type="hidden" name="supervice.deptId" value="${supervice.deptId}">
		<input type="hidden" name="supervice.delFlag" value="${supervice.delFlag}">
		<input type="hidden" name="supervice.linkId" value="${supervice.linkId}">
		<input type="hidden" name="supervice.rectificationMoney" value="${supervice.rectificationMoney}">
		<input type="hidden" name="supervice.recfinishTime" value="<fmt:formatDate type='both' value='${supervice.recfinishTime}' />" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		<input type="hidden" name="supervice.acceptTime" value="<fmt:formatDate type='both' value='${supervice.acceptTime}' />" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		<input type="hidden" name="supervice.danrecNum" value="${supervice.danrecNum}">
		<input type="hidden" name="supervice.rectificationState" value="${supervice.rectificationState}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag  style="width:60%;"  property="supervice.areaId" defaultText='请选择' codeName="企业属地" value="${supervice.areaId}" onchange="clearCompany()" dataType="*1-127" nullmsg='所在区域不能为空！' sucmsg='所在区域填写正确！'/><font style='color:red'>*</font></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input id="companyName" style="width:60%;" name="supervice.companyName" value="${supervice.companyName}" type="text" onclick="queryQy()" maxlength="127" dataType="*1-127" nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'/><font style='color:red'>*</font>
						<input type="hidden" id="companyId" name="supervice.companyId" value="${supervice.companyId}"/>
					</td>
				</tr>
				<tr>
					<th width="15%">隐患名称</th>
					<td width="35%"><input name="supervice.dangerName" style="width:60%;" value="${supervice.dangerName}" type="text" dataType="*1-127" errormsg='隐患名称必须是1到127位字符！' nullmsg='隐患名称不能为空！' sucmsg='隐患名称填写正确！' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">挂牌时间</th>
					<td width="35%"><input name="supervice.listingTime" style="width:60%;"  dataType="*" nullmsg='挂牌时间不能为空！' sucmsg='挂牌时间填写正确！' value="<fmt:formatDate  pattern="yyyy-MM-dd"  type='both' value='${supervice.listingTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">隐患类别</th>
					<td width="35%"><cus:SelectOneTag datatype="*" style="width:60%;" nullmsg='隐患类别不能为空！' sucmsg='隐患类别填写正确！' property="supervice.dangerSort" defaultText='请选择' codeName="隐患类别" value="${supervice.dangerSort}" /><font style='color:red'>*</font></td>
					<th width="15%">隐患级别</th>
					<td width="35%"><cus:SelectOneTag datatype="*" style="width:60%;" nullmsg='隐患级别不能为空！' sucmsg='隐患级别填写正确！'  property="supervice.dangerLevel" defaultText='请选择' codeName="隐患级别" value="${supervice.dangerLevel}" /><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">整改级别</th>
					<td width="35%"><cus:SelectOneTag datatype="*" style="width:60%;" nullmsg='整改级别不能为空！' sucmsg='整改级别填写正确！' property="supervice.rectificationLevel" defaultText='请选择' codeName="挂牌督办整改级别" value="${supervice.rectificationLevel}" /><font style='color:red'>*</font></td>
					<th width="15%">责任单位</th>
					<td width="35%"><input name="supervice.responsibleUnit" style="width:60%;"  value="${supervice.responsibleUnit}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">责任人</th>
					<td width="35%"><input name="supervice.responsible" style="width:60%;"  value="${supervice.responsible}" type="text" maxlength="127" ></td>
					<th width="15%">责任人联系电话</th>
					<td width="35%"><input name="supervice.responsibleMobile" style="width:60%;" value="${supervice.responsibleMobile}" type="text" maxlength="127" datatype="lxfs" ignore="ignore" errormsg='请输入手机或者固话！'></td>
				</tr>
				<tr>
					<th width="15%">地址</th>
					<td width="35%"><input name="supervice.address" style="width:60%;" value="${supervice.address}" nullmsg='地址不能为空！' sucmsg='地址填写正确！' type="text" maxlength="127" datatype="*1-127"><font style='color:red'>*</font></td>
					<th width="15%">整改期限</th>
					<td width="35%"><input name="supervice.rectificationTerm" style="width:60%;" nullmsg='整改期限不能为空！' sucmsg='整改期限填写正确！' datatype="*" value="<fmt:formatDate pattern="yyyy-MM-dd" type='both' value='${supervice.rectificationTerm}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">隐患内容</th>
					<td width="85%" colspan="3"><textarea style="width:80%;height:60px;" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" nullmsg='隐患内容不能为空！' sucmsg='隐患内容填写正确！' datatype="*1-2000" name="supervice.dangerContent" >${supervice.dangerContent}</textarea><font style='color:red'>*</font></td>
				
				<tr>
					<th width="10%">检查文书</th>
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
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="jcwsfj">
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
					<th width="10%">整改前图片</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue2"/>
				    	<input type="file" name="uploadify2" id="uploadify2" />
			    		<a href="javascript:jQuery('#uploadify2').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify2').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="zgqtpfj">
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
					<th width="10%">整改方案</th>
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
						<table id="zgfafj">
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
					<th width="10%">防范措施</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue4"/>
				    	<input type="file" name="uploadify4" id="uploadify4"/>
			    		<a href="javascript:jQuery('#uploadify4').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify4').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="ffcsfj">
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
								   <a href="javascript:del('${item.id}');">删除</a>
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				
				<c:if test="${fn:length(checkRecords) >0}">
				<tr>
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
				</c:if> 
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
						</s:else>						
 					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
