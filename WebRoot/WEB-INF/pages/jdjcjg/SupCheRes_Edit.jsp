<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify1","${supCheRes.linkId}","jdjcjg1","jdjcjgfj1","fileQueue1");
		  uploadPicOnly ("uploadify2","${supCheRes.linkId}","jdjcjg2","jdjcjgfj2","fileQueue2");
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
	<form name="myform1" method="post" enctype="multipart/form-data" action="supCheResSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="supCheRes.id" value="${supCheRes.id}">
		<input type="hidden" name="supCheRes.createTime" value="<fmt:formatDate type="both" value="${supCheRes.createTime}" />">
		<input type="hidden" name="supCheRes.updateTime" value="${supCheRes.updateTime}">
		<input type="hidden" name="supCheRes.createUserID" value="${supCheRes.createUserID}">
		<input type="hidden" name="supCheRes.updateUserID" value="${supCheRes.updateUserID}">
		<input type="hidden" name="supCheRes.deptId" value="${supCheRes.deptId}">
		<input type="hidden" name="supCheRes.delFlag" value="${supCheRes.delFlag}">
		<input type="hidden" name="supCheRes.linkId" value="${supCheRes.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag property="supCheRes.areaId" style="width: 60%" defaultText='请选择' codeName="企业属地" value="${supCheRes.areaId}" onchange="clearCompany()"/></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input id="companyName" name="supCheRes.companyName" style="width: 60%" value="${supCheRes.companyName}" type="text" readonly="readonly" onclick="queryQy()"/>
						<input type="hidden" id="companyId" name="supCheRes.companyId" value="${supCheRes.companyId}"/>
					</td>
				</tr>
				<tr>
					<th width="15%">检查人员姓名</th>
					<td width="35%"><input name="supCheRes.checkUsername" style="width: 60%" value="${supCheRes.checkUsername}" type="text" datatype="*1-127" errormsg='检查人员姓名必须是1到127位字符！' nullmsg='检查人员姓名不能为空！' sucmsg='检查人员姓名填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">检查文书号</th>
					<td width="35%"><input name="supCheRes.checkInstrumentNum" style="width: 60%" value="${supCheRes.checkInstrumentNum}" type="text" maxlength="127"></td>
					<th width="15%">检查内容</th>
					<td width="35%"><input name="supCheRes.checkContent" style="width: 60%" value="${supCheRes.checkContent}" type="text" maxlength="127"></td>
				</tr>
				
				<tr>
					<th width="15%">检查文书</th>
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
						<table id="jdjcjgfj1">
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
					<th width="15%">检查图片</th>
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
						<table id="jdjcjgfj2">
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_supCheRes');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
