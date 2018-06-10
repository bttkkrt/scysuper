<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${speJobTra.linkId}","qytzrypx","pxfj","fileQueue");
		});
		
		function queryQy()
		{
			popupCenter("${ctx}/jsp/tzzyry/speJobPerLists.action", "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
		}
	
</script>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="speJobTraSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="speJobTra.id" value="${speJobTra.id}">
		<input type="hidden" name="speJobTra.createTime" value="<fmt:formatDate type="both" value="${speJobTra.createTime}" />">
		<input type="hidden" name="speJobTra.updateTime" value="${speJobTra.updateTime}">
		<input type="hidden" name="speJobTra.createUserID" value="${speJobTra.createUserID}">
		<input type="hidden" name="speJobTra.updateUserID" value="${speJobTra.updateUserID}">
		<input type="hidden" name="speJobTra.deptId" value="${speJobTra.deptId}">
		<input type="hidden" name="speJobTra.delFlag" value="${speJobTra.delFlag}">
		<input type="hidden" name="speJobTra.linkId" value="${speJobTra.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">特种作业人员姓名</th>
					<td width="35%">
						<input id="ryname" name="speJobTra.trainingPersonName" value="${speJobTra.trainingPersonName}" type="text" datatype="*1-127" errormsg='特种作业人员姓名必须是1到127位字符！' nullmsg='特种作业人员姓名不能为空！' sucmsg='特种作业人员姓名填写正确！'  maxlength="127" style="width:60%" onclick="queryQy()"/><font style='color:red'>*</font>
						<input type="hidden" id="ryid" name="speJobTra.trainingPersonId" value="${speJobTra.trainingPersonId}"/>
					</td>
					<th width="15%">培训单位</th>
					<td width="35%"><input name="speJobTra.trainingAddress" style="width:60%" value="${speJobTra.trainingAddress}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">培训学时</th>
					<td width="35%"><input name="speJobTra.trainingTeacheTime" style="width:60%" value="${speJobTra.trainingTeacheTime}" type="text" datatype="*1-127" errormsg='培训学时必须是1到127位字符！' nullmsg='培训学时不能为空！' sucmsg='培训学时填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">授课人</th>
					<td width="35%"><input name="speJobTra.trainingTeacher" style="width:60%" value="${speJobTra.trainingTeacher}" type="text"  maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">培训开始时间</th>
					<td width="35%"><input name="speJobTra.trainingTime" id="trainingTime" style="width:60%" value="<fmt:formatDate type='date' value='${speJobTra.trainingTime}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'trainingTimeEnd\')}'})" datatype="*1-127" errormsg='培训开始时间必须是1到127位字符！' nullmsg='培训开始时间不能为空！' sucmsg='培训开始时间填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">培训结束时间</th>
					<td width="35%"><input name="speJobTra.trainingTimeEnd" id="trainingTimeEnd" style="width:60%" value="<fmt:formatDate type='date' value='${speJobTra.trainingTimeEnd}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'trainingTime\')}'})" datatype="*1-127" errormsg='培训结束时间必须是1到127位字符！' nullmsg='培训结束时间不能为空！' sucmsg='培训结束时间填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">证书号码</th>
					<td width="35%"><input id="zjhm" name="speJobTra.trainingCardnum" style="width:60%" value="${speJobTra.trainingCardnum}" type="text" datatype="*1-127" errormsg='证书号码必须是1到127位字符！' nullmsg='证书号码不能为空！' sucmsg='证书号码填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">证书发证日期</th>
					<td width="35%"><input name="speJobTra.trainingCardPickDate" style="width:60%" value="<fmt:formatDate type='date' value='${speJobTra.trainingCardPickDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">有效期</th>
					<td width="35%"><input name="speJobTra.trainingCardValidity" style="width:60%" value="<fmt:formatDate type='date' value='${speJobTra.trainingCardValidity}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  ></td>
					<th width="15%">发证单位</th>
					<td width="35%"><input name="speJobTra.fzdw" value="${speJobTra.fzdw}" style="width:60%" type="text"  maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">培训内容</th>
					<td width="35%" colspan="3"><textarea name="speJobTra.trainingContent" style="width:96%;height:100px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${speJobTra.trainingContent}</textarea></td>
				</tr>
				<tr>	
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea name="speJobTra.trainingRemark" style="width:96%;height:100px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${speJobTra.trainingRemark}</textarea></td>
				</tr>
				<tr>
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
				
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_speJobTra');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
