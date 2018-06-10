<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${majordomoTraining.linkId}","aqsczjpx","pxfj","fileQueue");
		});
	function queryQy()
		{
			popupCenter("${ctx}/jsp/aqsczj/secProMajLists.action", "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
		}
		
</script>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="majordomoTrainingSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="majordomoTraining.id" value="${majordomoTraining.id}">
		<input type="hidden" name="majordomoTraining.createTime" value="<fmt:formatDate type="both" value="${majordomoTraining.createTime}" />">
		<input type="hidden" name="majordomoTraining.updateTime" value="${majordomoTraining.updateTime}">
		<input type="hidden" name="majordomoTraining.createUserID" value="${majordomoTraining.createUserID}">
		<input type="hidden" name="majordomoTraining.updateUserID" value="${majordomoTraining.updateUserID}">
		<input type="hidden" name="majordomoTraining.deptId" value="${majordomoTraining.deptId}">
		<input type="hidden" name="majordomoTraining.delFlag" value="${majordomoTraining.delFlag}">
		<input type="hidden" name="majordomoTraining.linkId" value="${majordomoTraining.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">安全生产总监姓名</th>
					<td width="35%">
						<input id="ryname" name="majordomoTraining.trainingPersonName" value="${majordomoTraining.trainingPersonName}" type="text" datatype="*1-127" errormsg='安全生产总监姓名必须是1到127位字符！' nullmsg='安全生产总监姓名不能为空！' sucmsg='安全生产总监姓名填写正确！'  maxlength="127" style="width:60%" onclick="queryQy()"/><font style='color:red'>*</font>
						<input type="hidden" id="ryid" name="majordomoTraining.trainingPersonId" value="${majordomoTraining.trainingPersonId}"/>
					</td>
					<th width="15%">培训开始时间</th>
					<td width="35%"><input name="majordomoTraining.trainingTime" style="width:60%" id="trainingTime" value="<fmt:formatDate type='date' value='${majordomoTraining.trainingTime}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'trainingTimeEnd\')}'})"  datatype="*1-127" errormsg='培训开始时间必须是1到127位字符！' nullmsg='培训开始时间不能为空！' sucmsg='培训开始时间填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">培训结束时间</th>
					<td width="35%"><input name="majordomoTraining.trainingTimeEnd" style="width:60%" id="trainingTimeEnd" value="<fmt:formatDate type='date' value='${majordomoTraining.trainingTimeEnd}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'trainingTime\')}'})"  datatype="*1-127" errormsg='培训结束时间必须是1到127位字符！' nullmsg='培训结束时间不能为空！' sucmsg='培训结束时间填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">授课人</th>
					<td width="35%"><input name="majordomoTraining.trainingTeacher" style="width:60%" value="${majordomoTraining.trainingTeacher}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">培训单位</th>
					<td width="35%"><input name="majordomoTraining.trainingAddress" style="width:60%" value="${majordomoTraining.trainingAddress}" type="text" datatype="*1-127" errormsg='培训地点必须是1到127位字符！' nullmsg='培训地点不能为空！' sucmsg='培训地点填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">培训学时</th>
					<td width="35%"><input name="majordomoTraining.trainingTeacheTime" style="width:60%" value="${majordomoTraining.trainingTeacheTime}" type="text" datatype="*1-127" errormsg='培训学时必须是1到127位字符！' nullmsg='培训学时不能为空！' sucmsg='培训学时填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">证书号码</th>
					<td width="35%"><input id="zjhm" name="majordomoTraining.trainingCardnum" style="width:60%" value="${majordomoTraining.trainingCardnum}" type="text" datatype="*1-127" errormsg='证书号码必须是1到127位字符！' nullmsg='证书号码不能为空！' sucmsg='证书号码填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">证书发证日期</th>
					<td width="35%"><input name="majordomoTraining.trainingCardPickDate" style="width:60%" value="<fmt:formatDate type='date' value='${majordomoTraining.trainingCardPickDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   ></td>
				</tr>
				<tr>
					<th width="15%">有效期</th>
					<td width="35%"><input name="majordomoTraining.trainingCardValidity" style="width:60%" value="<fmt:formatDate type='date' value='${majordomoTraining.trainingCardValidity}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  ></td>
					<th width="15%">发证单位</th>
					<td width="35%"><input name="majordomoTraining.fzdw" style="width:60%" value="${majordomoTraining.fzdw}" type="text"  maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">培训内容</th>
					<td width="85%" colspan="3"><textarea name="majordomoTraining.trainingContent" style="width:96%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${majordomoTraining.trainingContent}</textarea></td>
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
						<div style="color:green;overflow:auto;height:175px;">
						<table id="pxfj">
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_majordomoTraining');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
