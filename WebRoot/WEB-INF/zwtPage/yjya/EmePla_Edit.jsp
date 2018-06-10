<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
		<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${emePla.linkId}","yjya","yjyafj","fileQueue");
		});
		var type="${type}";
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="emePlaSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="emePla.id" value="${emePla.id}">
		<input type="hidden" name="emePla.createTime" value="<fmt:formatDate type="both" value="${emePla.createTime}" />">
		<input type="hidden" name="emePla.updateTime" value="${emePla.updateTime}">
		<input type="hidden" name="emePla.createUserID" value="${emePla.createUserID}">
		<input type="hidden" name="emePla.updateUserID" value="${emePla.updateUserID}">
		<input type="hidden" name="emePla.deptId" value="${emePla.deptId}">
		<input type="hidden" name="emePla.delFlag" value="${emePla.delFlag}">
		<input type="hidden" name="emePla.linkId" value="${emePla.linkId}">
		<input type="hidden" name="emePla.type" value="${type}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">预案名称</th>
					<td width="35%"><input name="emePla.planName" value="${emePla.planName}" style="width:60%" type="text" datatype="*1-127" errormsg='预案名称必须是1到127位字符！' nullmsg='预案名称不能为空！' sucmsg='预案名称填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">预案类别</th>
					<td width="35%"><cus:SelectOneTag property="emePla.planType" style="width:60%" defaultText='请选择' codeName="应急预案类别" value="${emePla.planType}" /></td>
				</tr>
				<tr>
					<th width="15%">预案级别</th>
					<td width="35%"><cus:SelectOneTag property="emePla.planLevel" style="width:60%" defaultText='请选择' codeName="应急预案级别" value="${emePla.planLevel}" /></td>
					<th width="15%">预案摘要</th>
					<td width="35%"><input name="emePla.planSummary" style="width:60%" value="${emePla.planSummary}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">编制单位</th>
					<td width="35%"><input name="emePla.drawUpInstitution" style="width:60%" value="${emePla.drawUpInstitution}" type="text" maxlength="127"></td>
					<th width="15%">编制人</th>
					<td width="35%"><input name="emePla.drawUpPerson" style="width:60%" value="${emePla.drawUpPerson}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">发布日期</th>
					<td width="35%"><input name="emePla.publishDate" style="width:60%" value="<fmt:formatDate type='date' value='${emePla.publishDate}' pattern="yyyy-MM-dd" />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">发布文号</th>
					<td width="35%"><input name="emePla.publishNumber" style="width:60%" value="${emePla.publishNumber}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">发布单位</th>
					<td width="35%"><input name="emePla.publishInstitution" style="width:60%" value="${emePla.publishInstitution}" type="text" maxlength="127"></td>
					<th width="15%">签发人</th>
					<td width="35%"><input name="emePla.publisher" style="width:60%" value="${emePla.publisher}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">预案备案时间</th>
					<td width="35%"><input name="emePla.planFilingTime" style="width:60%" value="<fmt:formatDate type='date' value='${emePla.planFilingTime}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">预案备案部门</th>
					<td width="35%"><input name="emePla.planFilingDepart" style="width:60%" value="${emePla.planFilingDepart}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">预案备案编号</th>
					<td width="35%"><input name="emePla.planFilingNumber" style="width:60%" value="${emePla.planFilingNumber}" type="text" maxlength="127"></td>
					<th width="15%">试用领域</th>
					<td width="35%"><input name="emePla.filed" style="width:60%" value="${emePla.filed}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="emePla.remark" style="width:96%;height:60px">${emePla.remark}</textarea></td>
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
					<td width="85%" colspan="3"  style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="yjyafj">
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
