<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${secProGoa.linkId}","qyaqscmb","mbfj","fileQueue");
		});
		
</script>
<script>
	$(document).ready(function() {
		  uploadPic("uploadify2","${secProGoa.linkId}","qyaqscmb","mbfj2","fileQueue2");
		});
		
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="secProGoaSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="secProGoa.id" value="${secProGoa.id}">
		<input type="hidden" name="secProGoa.createTime" value="<fmt:formatDate type="both" value="${secProGoa.createTime}" />">
		<input type="hidden" name="secProGoa.updateTime" value="${secProGoa.updateTime}">
		<input type="hidden" name="secProGoa.createUserID" value="${secProGoa.createUserID}">
		<input type="hidden" name="secProGoa.updateUserID" value="${secProGoa.updateUserID}">
		<input type="hidden" name="secProGoa.deptId" value="${secProGoa.deptId}">
		<input type="hidden" name="secProGoa.delFlag" value="${secProGoa.delFlag}">
		<input type="hidden" name="secProGoa.linkId" value="${secProGoa.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">年度</th>
					<td width="35%"><input name="secProGoa.productGoalYear" value="<fmt:formatDate type='date' value='${secProGoa.productGoalYear}' pattern="yyyy"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy'})" datatype="*1-127" errormsg='年度必须是1到127位字符！' nullmsg='年度不能为空！' sucmsg='年度填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				<th width="15%">总体生产目标</th> 
					<td width="35%" colspan="3"><textarea name="secProGoa.productGoalTotalGoal" style="width:96%;height:90px" datatype="*1-2000" errormsg='总体生产目标必须是1到2000位字符！' nullmsg='总体生产目标不能为空！' sucmsg='总体生产目标填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${secProGoa.productGoalTotalGoal}</textarea><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">年度生产目标</th>
					<td width="35%" colspan="3"><textarea name="secProGoa.productGoalYearGoal" style="width:96%;height:90px" datatype="*1-2000" errormsg='年度生产目标必须是1到2000位字符！' nullmsg='年度生产目标不能为空！' sucmsg='年度生产目标填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${secProGoa.productGoalYearGoal}</textarea><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">总体生产目标（带总经理签名的附件）</th>
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
						<table id="mbfj">
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
					<th width="15%">年度生产目标（带总经理签名的附件）</th>
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
						<table id="mbfj2">
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
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea name="secProGoa.productGoalRemak" style="width:96%;height:90px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${secProGoa.productGoalRemak}</textarea></td>
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
