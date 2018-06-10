<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${honRec.linkId}","rybzxx","ryfj","fileQueue");
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
			<font style='color:red'>*只需填写安全生产方面获取的表彰</font>
			<font style='color:red'>*若信息还未填写完整，可以点击保存暂存（安监查看不到），若填写完整，则点击提交上报安监审核，提交后信息不可修改删除</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="honRecSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="honRec.id" value="${honRec.id}">
		<input type="hidden" name="honRec.createTime" value="<fmt:formatDate type="both" value="${honRec.createTime}" />">
		<input type="hidden" name="honRec.updateTime" value="${honRec.updateTime}">
		<input type="hidden" name="honRec.createUserID" value="${honRec.createUserID}">
		<input type="hidden" name="honRec.updateUserID" value="${honRec.updateUserID}">
		<input type="hidden" name="honRec.deptId" value="${honRec.deptId}">
		<input type="hidden" name="honRec.delFlag" value="${honRec.delFlag}">
		<input type="hidden" name="honRec.linkId" value="${honRec.linkId}">
		<input type="hidden" id="auditState" name="honRec.auditState" value="待提交">
			<table width="100%" border="0">
				<tr>
					<th width="15%">批准文号</th>
					<td width="35%"><input name="honRec.approvalNumber" value="${honRec.approvalNumber}" type="text" datatype="*1-100" errormsg='批准文号必须是1到100位字符！' nullmsg='批准文号不能为空！' sucmsg='批准文号填写正确！'  maxlength="100" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">表彰部门</th>
					<td width="35%"><input name="honRec.recognitionDept" value="${honRec.recognitionDept}" type="text" datatype="*1-100" errormsg='表彰部门必须是1到100位字符！' nullmsg='表彰部门不能为空！' sucmsg='表彰部门填写正确！'  maxlength="100" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">荣誉称号</th>
					<td width="35%"><input name="honRec.honor" value="${honRec.honor}" type="text" datatype="*1-100" errormsg='荣誉称号必须是1到100位字符！' nullmsg='荣誉称号不能为空！' sucmsg='荣誉称号填写正确！'  maxlength="100" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">地区</th>
					<td width="35%"><input name="honRec.area" value="${honRec.area}" type="text" datatype="*1-100" errormsg='地区必须是1到100位字符！' nullmsg='地区不能为空！' sucmsg='地区填写正确！'  maxlength="100" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">年度</th>
					<td width="35%"><input name="honRec.bzyear" value="<fmt:formatDate type='date' value='${honRec.bzyear}'  pattern="yyyy"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy'})" datatype="*1-100"   nullmsg='年度不能为空！' style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">表彰日期</th>
					<td width="35%"><input name="honRec.recognitionDate"  value="${honRec.recognitionDate}" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   nullmsg='表彰日期不能为空！' datatype="*1-100" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				<th width="15%">表彰内容</th>
					<td width="35%" colspan="3"><textarea name="honRec.recognitionContent" style="width:96%;height:90px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${honRec.recognitionContent}</textarea></td>
				</tr>	
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea name="honRec.intelligenceRemark" style="width:96%;height:90px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${honRec.intelligenceRemark}</textarea></td>
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
						<table id="ryfj">
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
