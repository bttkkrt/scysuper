<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
		<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${maiPerRep.linkId}","qyzyfzrlzqk","qyzyfzrlzqkfj","fileQueue");
		});
		
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="maiPerRepSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="maiPerRep.id" value="${maiPerRep.id}">
		<input type="hidden" name="maiPerRep.createTime" value="<fmt:formatDate type="both" value="${maiPerRep.createTime}" />">
		<input type="hidden" name="maiPerRep.updateTime" value="${maiPerRep.updateTime}">
		<input type="hidden" name="maiPerRep.createUserID" value="${maiPerRep.createUserID}">
		<input type="hidden" name="maiPerRep.updateUserID" value="${maiPerRep.updateUserID}">
		<input type="hidden" name="maiPerRep.deptId" value="${maiPerRep.deptId}">
		<input type="hidden" name="maiPerRep.delFlag" value="${maiPerRep.delFlag}">
		<input type="hidden" name="maiPerRep.linkId" value="${maiPerRep.linkId}">
			<table width="100%" border="0">
				
				<tr>
					<th width="15%">主要负责人</th>
					<td width="35%"><input name="maiPerRep.principalResponsiblePerson" style="width:60%" value="${maiPerRep.principalResponsiblePerson}" type="text" datatype="*1-127" errormsg='主要负责人必须是1到127位字符！' nullmsg='主要负责人不能为空！' sucmsg='主要负责人填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">履职报告</th>
					<td width="35%"><input name="maiPerRep.performanceReport" style="width:60%" value="${maiPerRep.performanceReport}" type="text" datatype="*1-127" errormsg='履职报告必须是1到127位字符！' nullmsg='履职报告不能为空！' sucmsg='履职报告填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">是否危险工艺</th>
					<td width="35%"><cus:SelectOneTag property="maiPerRep.ifHazardousProcess" style="width:60%" defaultText='请选择' codeName="是或否" value="${maiPerRep.ifHazardousProcess}" datatype="*"  nullmsg='是否危险工艺不能为空！' /><font style='color:red'>*</font></td>
				
					
					<th width="15%">是否构成重大危险源</th>
					<td width="35%"><cus:SelectOneTag property="maiPerRep.ifHazardSources" style="width:60%" defaultText='请选择' codeName="是或否" value="${maiPerRep.ifHazardSources}" datatype="*"  nullmsg='是否构成重大危险源不能为空！'/><font style='color:red'>*</font></td>
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
						<table id="qyzyfzrlzqkfj">
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_maiPerRep');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
