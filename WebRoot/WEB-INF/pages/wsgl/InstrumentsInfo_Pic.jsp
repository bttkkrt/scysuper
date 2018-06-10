<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${instrumentsInfo.linkId}","wsgl","wsglfj","fileQueue");
		});
		
</script>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="instrumentsInfoPicSave.action">
		<s:token />
		<input type="hidden" name="instrumentsInfo.id" value="${instrumentsInfo.id}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">案件名称</th>
					<td width="35%" >${instrumentsInfo.caseName}</td>
					<th width="15%">文书类型</th>
					<td width="35%" ><cus:hxlabel codeName="文书类型" itemValue="${instrumentsInfo.instrumentType}" /></td>
				</tr>
				<tr>
					<th width="15%">送达地点</th>
					<td width="35%"><input name="instrumentsInfo.address" value="${instrumentsInfo.address}" type="text" maxlength="127"></td>
					<th width="15%">送达方式</th>
					<td width="35%" ><input name="instrumentsInfo.returnWay" value="${instrumentsInfo.returnWay}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">送达日期</th>
					<td width="35%"><input name="instrumentsInfo.returnTime" value="<fmt:formatDate type='date' value='${instrumentsInfo.returnTime}' pattern='yyyy-MM-dd'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">送达人</th>
					<td width="35%" ><input name="instrumentsInfo.returnPerson" value="${instrumentsInfo.returnPerson}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">文书回执</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue"/>
				    	<input type="file" name="uploadify" id="uploadify"/>
			    		<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为10M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加回执</th>
					<td width="85%" colspan="3">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="wsglfj">
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
						<a href="#" class="btn_01" type="submit" >确认<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_instrumentsInfo');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
