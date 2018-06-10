<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
			  uploadPic("uploadify","${shuHol.linkId}","jjrktg","jjrktgfj","fileQueue");
			});
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="shuHolSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="shuHol.id" value="${shuHol.id}">
		<input type="hidden" name="shuHol.createTime" value="<fmt:formatDate type="both" value="${shuHol.createTime}" />">
		<input type="hidden" name="shuHol.updateTime" value="${shuHol.updateTime}">
		<input type="hidden" name="shuHol.createUserID" value="${shuHol.createUserID}">
		<input type="hidden" name="shuHol.updateUserID" value="${shuHol.updateUserID}">
		<input type="hidden" name="shuHol.deptId" value="${shuHol.deptId}">
		<input type="hidden" name="shuHol.delFlag" value="${shuHol.delFlag}">
		<input type="hidden" name="shuHol.linkId" value="${shuHol.linkId}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">放假开始时间</th>
					<td width="35%"><input name="shuHol.holidayTimeStart" style="width:60%;" nullmsg='放假开始时间不能为空！' id="queryStrattimeStart"  datatype="*1-127"  value="<fmt:formatDate pattern='yyyy-MM-dd' type='both' value='${shuHol.holidayTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryStrattimeEnd\')}'})"><font style='color:red'>*</font> </td>
					<th width="15%">放假结束时间</th>
					<td width="35%"><input name="shuHol.holidayTimeEnd" style="width:60%;"  nullmsg='放假结束时间不能为空！' id="queryStrattimeEnd"  datatype="*1-127"  value="<fmt:formatDate pattern='yyyy-MM-dd'  type='both' value='${shuHol.holidayTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryStrattimeStart\')}'})" > <font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">是否开工</th>
					<td width="35%"><cus:SelectOneTag datatype="*" style="width:60%;"  property="shuHol.ifStart" defaultText='请选择' codeName="是或否" value="${shuHol.ifStart}" /><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea name="shuHol.remark" style="width:96%;height:60px"  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${shuHol.remark}</textarea></td>
				</tr>
				<tr>
					<th width="10%">附件</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue"/>
				    	<input type="file" name="uploadify" id="uploadify"/>
			    		<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="jjrktgfj">
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
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_shuHol');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
