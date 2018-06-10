<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${proSafDes.linkId}","prosafedesign","prosafedesignfj","fileQueue");
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
	<form name="myform1" method="post" enctype="multipart/form-data" action="proSafDesSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="proSafDes.id" value="${proSafDes.id}">
		<input type="hidden" name="proSafDes.createTime" value="<fmt:formatDate type="both" value="${proSafDes.createTime}" />">
		<input type="hidden" name="proSafDes.updateTime" value="${proSafDes.updateTime}">
		<input type="hidden" name="proSafDes.createUserID" value="${proSafDes.createUserID}">
		<input type="hidden" name="proSafDes.updateUserID" value="${proSafDes.updateUserID}">
		<input type="hidden" name="proSafDes.deptId" value="${proSafDes.deptId}">
		<input type="hidden" name="proSafDes.delFlag" value="${proSafDes.delFlag}">
		<input type="hidden" name="proSafDes.linkId" value="${proSafDes.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag property="proSafDes.areaId" style="width: 60%" defaultText='请选择' codeName="企业属地" value="${proSafDes.areaId}" onchange="clearCompany()" dataType="*1-127" nullmsg='所在区域不能为空！' sucmsg='所在区域填写正确！'/><font style='color:red'>*</font></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input id="companyName" name="proSafDes.companyName" style="width: 60%" value="${proSafDes.companyName}" type="text" readonly="readonly" onclick="queryQy()" dataType="*1-127"  nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'/><font style='color:red'>*</font>
						<input type="hidden" id="companyId" name="proSafDes.companyId" value="${proSafDes.companyId}"/>
					</td>
				</tr> 
				<tr>
					<th width="15%">项目性质</th>
					<td width="35%"><cus:SelectOneTag property="proSafDes.projectNature" style="width: 60%" defaultText='请选择' codeName="项目性质" value="${proSafDes.projectNature}" /></td>
					<th width="15%">项目类型</th>
					<td width="35%"><cus:SelectOneTag property="proSafDes.projectType" style="width: 60%" defaultText='请选择' codeName="项目类型" value="${proSafDes.projectType}" /></td>
				</tr>
				<tr>
				     <th width="15%">项目内容</th>
					<td width="35%"><input name="proSafDes.projectContent" style="width: 60%" value="${proSafDes.projectContent}" type="text" datatype="*1-127" errormsg='项目内容必须是1到127位字符！' nullmsg='项目内容不能为空！' sucmsg='项目内容填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">备案日期</th>
					<td width="35%"><input name="proSafDes.recordNum" style="width: 60%" value="<fmt:formatDate type='date' value='${proSafDes.recordNum}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">备案编号</th>
					<td width="35%"><input name="proSafDes.recordDate" style="width: 60%" value="${proSafDes.recordDate}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="proSafDes.remark" style="width:96%;height:60px">${proSafDes.remark}</textarea></td>
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
						<table id="prosafedesignfj">
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
