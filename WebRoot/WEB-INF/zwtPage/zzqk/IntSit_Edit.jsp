<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${intSit.linkId}","zzqk","zzfj","fileQueue");
		});
	function save(state)
	{
		document.getElementById('auditState').value = state;
	}
	
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
			<font style='color:red'>*若信息还未填写完整，可以点击保存暂存（安监查看不到），若填写完整，则点击提交上报安监审核，提交后信息不可修改删除</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="intSitSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="intSit.id" value="${intSit.id}">
		<input type="hidden" name="intSit.createTime" value="<fmt:formatDate type="both" value="${intSit.createTime}" />">
		<input type="hidden" name="intSit.updateTime" value="${intSit.updateTime}">
		<input type="hidden" name="intSit.createUserID" value="${intSit.createUserID}">
		<input type="hidden" name="intSit.updateUserID" value="${intSit.updateUserID}">
		<input type="hidden" name="intSit.deptId" value="${intSit.deptId}">
		<input type="hidden" name="intSit.delFlag" value="${intSit.delFlag}">
		<input type="hidden" name="intSit.linkId" value="${intSit.linkId}">
		<input type="hidden" id="auditState" name="intSit.auditState" value="待提交">
			<table width="100%" border="0">
				<tr>
					<th width="15%">证书编号</th>
					<td width="35%"><input name="intSit.intelligenceCardnum" value="${intSit.intelligenceCardnum}" type="text" datatype="*1-127" errormsg='证书编号必须是1到127位字符！' nullmsg='证书编号不能为空！' sucmsg='证书编号填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">证书名称</th>
					<td width="35%"><input name="intSit.intelligenceCardname" value="${intSit.intelligenceCardname}" type="text" datatype="*1-127" errormsg='证书名称必须是1到127位字符！' nullmsg='证书名称不能为空！' sucmsg='证书名称填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">发证机关</th>
					<td width="35%"><input name="intSit.intelligenceInstitution" value="${intSit.intelligenceInstitution}" type="text"  datatype="*1-127" errormsg='发证机关必须是1到127位字符！' nullmsg='发证机关不能为空！' sucmsg='发证机关填写正确！' maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">发证日期</th>
					<td width="35%"><input name="intSit.intelligenceCardDate" value="<fmt:formatDate type='date' value='${intSit.intelligenceCardDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  datatype="*1-127"  nullmsg='发证日期不能为空！'  style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">有效期起始日期</th>
					<td width="35%"><input name="intSit.intelligenceValidityStart" id="trainingTime" value="<fmt:formatDate type='date' value='${intSit.intelligenceValidityStart}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'trainingTimeEnd\')}'})"   datatype="*1-127"  nullmsg='有效期起始日期不能为空！' style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">有效期截止日期</th>
					<td width="35%"><input name="intSit.intelligenceValidityEnd" id="trainingTimeEnd" value="<fmt:formatDate type='date' value='${intSit.intelligenceValidityEnd}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'trainingTime\')}'})" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">资质类型</th>
					<td width="35%"><cus:SelectOneTag property="intSit.intelligenceType" defaultText='请选择' codeName="资质类型" value="${intSit.intelligenceType}" datatype="*1-127" errormsg='资质类型必须是1到127位字符！' nullmsg='资质类型不能为空！' sucmsg='资质类型填写正确！'  maxlength="100" style="width:60%"/><font style='color:red'>*</font></td>
					<th width="15%">资质级别</th>
					<td width="35%"><input name="intSit.zzjb" value="${intSit.zzjb}" type="text"  maxlength="100" style="width:60%"></td>
				</tr>
				<tr>
					<th width="15%">变更日期</th>
					<td width="35%"><input name="intSit.changeDate" value="<fmt:formatDate type='date' value='${intSit.changeDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:60%"></td>
				</tr>
				<tr>
				<th width="15%">业务范围</th>
					<td width="35%" colspan="3"><textarea name="intSit.bussinessScope" style="width:96%;height:90px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${intSit.bussinessScope}</textarea></td>
				</tr>
				<tr>
				<th width="15%">资质内容</th>
					<td width="35%" colspan="3"><textarea name="intSit.intelligenceContent" style="width:96%;height:90px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${intSit.intelligenceContent}</textarea></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea name="intSit.intelligenceRemark" style="width:96%;height:90px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${intSit.intelligenceRemark}</textarea></td>
				</tr>
				<tr>
					<th width="15%">证书扫描件</th>
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
						<div style="color:green;overflow:auto;height:120px;">
						<table id="zzfj">
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
						<a href="#" class="btn_01" onclick="save('待提交')" type="submit">保存<b></b></a>&nbsp;
						<a href="#" class="btn_01" onclick="save('待审核')" type="submit">提交<b></b></a>&nbsp;					
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
