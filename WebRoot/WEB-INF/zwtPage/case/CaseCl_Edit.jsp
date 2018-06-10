<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${caseCl.linkId}","ajxx","ajclfj","fileQueue");
		});
		
	function showDiv(obj)
	{
		if(obj == '1')
		{
			document.getElementById('pic1').style.display = "";
			document.getElementById('pic2').style.display = "";
		}
		else
		{
			document.getElementById('pic1').style.display = "none";
			document.getElementById('pic2').style.display = "none";
		}
	}
		
</script>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="saveUploadFile.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="caseCl.id" value="${caseCl.id}">
		<input type="hidden" name="caseCl.caseId" value="${caseCl.caseId}">
		<input type="hidden" name="caseCl.linkId" value="${caseCl.linkId}">
		<input type="hidden" name="caseCl.createTime" value="<fmt:formatDate type="both" value="${caseCl.createTime}" />">
		<input type="hidden" name="caseCl.updateTime" value="${caseCl.updateTime}">
		<input type="hidden" name="caseCl.createUserID" value="${caseCl.createUserID}">
		<input type="hidden" name="caseCl.updateUserID" value="${caseCl.updateUserID}">
		<input type="hidden" name="caseCl.deptId" value="${caseCl.deptId}">
		<input type="hidden" name="caseCl.delFlag" value="${caseCl.delFlag}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">材料类型</th>
					<td width="35%">
						<s:select name="caseCl.zjType" list="#{'':'请选择','1':'现场照片','2':'罚没款收据回执'}"  theme="simple" onchange="showDiv(this.value)" datatype="*1-127"  nullmsg='材料类型不能为空！' sucmsg='材料类型选择正确！' cssStyle="width:60%"/><font style='color:red'>*</font>
				    </td>
				</tr>
				<tr id="pic1" <s:if test="caseCl.zjType != 1">style="display:none"</s:if>>
					<th width="15%">拍摄时间</th>
					<td width="35%">
				   		<input name="caseCl.picTime" value="<fmt:formatDate type='date' value='${caseCl.picTime}' pattern='yyyy-MM-dd'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  maxlength="127" style="width:60%">
				   	</td>
				   	<th width="15%">拍摄地点</th>
					<td width="35%">
				   		<input name="caseCl.picAdd" value="${caseCl.picAdd}" type="text"  maxlength="127" style="width:60%">
				   	</td>
				</tr>
				<tr id="pic2" <s:if test="caseCl.zjType != 1">style="display:none"</s:if>>
					<th width="15%">照片内容</th>
					<td width="85%" colspan="3">
						<textarea name="caseCl.picContent" style="width:78%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${caseCl.picContent}</textarea>
				   	</td>
				</tr>
				<tr>
					<th width="15%">案件材料</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue"/>
				    	<input type="file" name="uploadify" id="uploadify"/>
			    		<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为10M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已上传材料</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="ajclfj">
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
						<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
