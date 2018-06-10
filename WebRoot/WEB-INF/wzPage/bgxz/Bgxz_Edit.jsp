<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><s:if test="flag=='add'">新增</s:if> <s:else>修改</s:else>记录</title>
		<%@include file="/common/jsLib.jsp"%>
		

		<script>
		$(document).ready(function() {
		  uploadPic("uploadify","${bgxz.linkId}","bgxz","bgxzfj","fileQueue");
		});
	</script>

	</head>
<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<form name="myform1" method="post" enctype="multipart/form-data" action="bgxzSave.action">
				<s:token />
				<input type="hidden" name="flag" value="${flag}">
				<input type="hidden" name="bgxz.id"
					value="${bgxz.id}">
				<input type="hidden" name="bgxz.createTime"
					value="<fmt:formatDate type="both" value="${bgxz.createTime}" />">
				<input type="hidden" name="bgxz.updateTime"
					value="${bgxz.updateTime}">
				<input type="hidden" name="bgxz.createUserID"
					value="${bgxz.createUserID}">
				<input type="hidden" name="bgxz.updateUserID"
					value="${bgxz.updateUserID}">
				<input type="hidden" name="bgxz.deptId"
					value="${bgxz.deptId}">
				<input type="hidden" name="bgxz.delFlag"
					value="${bgxz.delFlag}">
				<input type="hidden" name="bgxz.userId"
					value="${bgxz.userId}">
					<input type="hidden" name="bgxz.linkId" value="${bgxz.linkId}">
				<div class="inner6px">
					<div class="cell">
						<table>
							<tr>
								<th width="15%">
									表格名称
								</th>
								<td width="35%">
									<input name="bgxz.infoTitle"  id="infoTitle"
										value="${bgxz.infoTitle}" type="text" datatype="*1-127"
										nullmsg='请输入表格名称  ' sucmsg='表格名称填写正确！'
										class="form_text" maxlength="127" style="width:60%">
									<span class="red">*</span>
								</td>
								<th width="15%">
									表格种类
								</th>
								<td width="35%">
									<s:select cssStyle="width:60%" name="bgxz.bgzl" list="#{'1':'城市管理','2':'安全生产','3':'行政执法','4':'其他'}" theme="simple"/>
								</td>
							</tr>
							<tr>
								<th width="15%">
									内容概述
								</th>
								<td width="85%" colspan="3">
									<textarea name="bgxz.nrgs" style="height:150px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${bgxz.nrgs}</textarea>
								</td>
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
					<td width="85%" colspan="3">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="bgxzfj">
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
						</table>
					</div>

				</div>
				<div class="inner6px">
					<div class="btn_area_setc">
						<s:if test="flag=='add'">
							<a href="###" class="btn_01" type="submit">保存<b></b> </a>
						</s:if>
						<s:else>
							<a href="###" class="btn_01" type="submit">更新<b></b> </a>
						</s:else>
						<a href="###" class="btn_01"
							onclick="parent.close_win('win_bgxz');">关闭<b></b> </a>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
