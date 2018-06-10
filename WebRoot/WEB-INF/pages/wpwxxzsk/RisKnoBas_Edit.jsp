<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${risKnoBas.linkId}","wpwxxzsk","wpwxxzskfj","fileQueue");
		});
		
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="risKnoBasSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="risKnoBas.id" value="${risKnoBas.id}">
		<input type="hidden" name="risKnoBas.createTime" value="<fmt:formatDate type="both" value="${risKnoBas.createTime}" />">
		<input type="hidden" name="risKnoBas.updateTime" value="${risKnoBas.updateTime}">
		<input type="hidden" name="risKnoBas.createUserID" value="${risKnoBas.createUserID}">
		<input type="hidden" name="risKnoBas.updateUserID" value="${risKnoBas.updateUserID}">
		<input type="hidden" name="risKnoBas.deptId" value="${risKnoBas.deptId}">
		<input type="hidden" name="risKnoBas.delFlag" value="${risKnoBas.delFlag}">
		<input type="hidden" name="risKnoBas.linkId" value="${risKnoBas.linkId}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">物品名称</th>
					<td width="35%"><input name="risKnoBas.itemName" style="width:60%;" value="${risKnoBas.itemName}" errormsg='物品名称必须是1到127位字符！' nullmsg='物品名称不能为空！' sucmsg='物品名称填写正确！'  datatype="*1-127"  type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">危险性内容</th>
					<td width="35%"><input name="risKnoBas.dangerousContent" style="width:60%;" value="${risKnoBas.dangerousContent}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">应对措施</th>
					<td width="35%"><input name="risKnoBas.responseMeasures" style="width:60%;" value="${risKnoBas.responseMeasures}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3"><textarea name="risKnoBas.remark" style="width:96%;height:60px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${risKnoBas.remark}</textarea></td>
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
						<table id="wpwxxzskfj">
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_risKnoBas');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
