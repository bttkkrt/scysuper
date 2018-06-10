<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
		<script>
	$(document).ready(function() {
		  uploadPic("uploadify1","${emeDri.linkId}","yjyl","yjylfj","fileQueue1");
		  uploadPicOnly("uploadify2","${emeDri.linkId}","yjyl","yltp","fileQueue2");
		});
		
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="emeDriSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="emeDri.id" value="${emeDri.id}">
		<input type="hidden" name="emeDri.createTime" value="<fmt:formatDate type="both" value="${emeDri.createTime}" />">
		<input type="hidden" name="emeDri.updateTime" value="${emeDri.updateTime}">
		<input type="hidden" name="emeDri.createUserID" value="${emeDri.createUserID}">
		<input type="hidden" name="emeDri.updateUserID" value="${emeDri.updateUserID}">
		<input type="hidden" name="emeDri.deptId" value="${emeDri.deptId}">
		<input type="hidden" name="emeDri.delFlag" value="${emeDri.delFlag}">
		<input type="hidden" name="emeDri.linkId" value="${emeDri.linkId}">
			<table width="100%" border="0">
				
				<tr>
					<th width="15%">演练名称</th>
					<td width="35%"><input name="emeDri.drillName" style="width:60%" value="${emeDri.drillName}" type="text" datatype="*1-127" errormsg='演练名称必须是1到127位字符！' nullmsg='演练名称不能为空！' sucmsg='演练名称填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">演练类型</th>
					<td width="35%"><cus:SelectOneTag property="emeDri.drillType" style="width:60%" defaultText='请选择' codeName="应急演练类型" value="${emeDri.drillType}" /></td>
				</tr>
				<tr>
					<th width="15%">演练地点</th>
					<td width="35%"><input name="emeDri.drillAddress" style="width:60%" value="${emeDri.drillAddress}" type="text" maxlength="127"></td>
					<th width="15%">演练目的</th>
					<td width="35%"><input name="emeDri.drillPurpose" style="width:60%" value="${emeDri.drillPurpose}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">演练形式</th>
					<td width="35%"><cus:SelectOneTag property="emeDri.drillForm" style="width:60%" defaultText='请选择' codeName="应急演练形式" value="${emeDri.drillForm}" /></td>
					<th width="15%">演练内容</th>
					<td width="35%"><input name="emeDri.drillContent" style="width:60%" value="${emeDri.drillContent}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">演练开始时间</th>
					<td width="35%"><input name="emeDri.drillStartTime" id="drillStartTime" style="width:60%" value="<fmt:formatDate type='date' value='${emeDri.drillStartTime}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'drillStopTime\')}'})"></td>
					<th width="15%">演练结束时间</th>
					<td width="35%"><input name="emeDri.drillStopTime" id="drillStopTime" style="width:60%" value="<fmt:formatDate type='date' value='${emeDri.drillStopTime}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'drillStartTime\')}'})"></td>
				</tr>
				<tr>
					<th width="15%">主办单位</th>
					<td width="35%"><input name="emeDri.organizer" style="width:60%" value="${emeDri.organizer}" type="text" maxlength="127"></td>
					<th width="15%">演练单位</th>
					<td width="35%"><input name="emeDri.drillCompany" style="width:60%" value="${emeDri.drillCompany}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">参演人数</th>
					<td width="35%"><input name="emeDri.drillPersonNum" style="width:60%" value="${emeDri.drillPersonNum}" type="text" maxlength="127"></td>
					<th width="15%">评估总结</th>
					<td width="35%"><input name="emeDri.evaluateSummary" style="width:60%" value="${emeDri.evaluateSummary}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">附件上传</th>
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
						<table id="yjylfj">
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
					<th width="15%">演练图片</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue2"/>
				    	<input type="file" name="uploadify2" id="uploadify2"/>
			    		<a href="javascript:jQuery('#uploadify2').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify2').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加图片</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="yltp">
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_emeDri');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
