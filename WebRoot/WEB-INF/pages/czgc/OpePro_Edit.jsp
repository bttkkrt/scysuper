<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
		<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${opePro.linkId}","czgc","czgcfj","fileQueue");
		});
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
			<font style='color:red'>*涉及保密工艺流程的可不上传</font>
			<font style='color:red'>*需先在企业作业安全-生产车间管理内录入车间信息，此处车间才能选择</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="opeProSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="opePro.id" value="${opePro.id}">
		<input type="hidden" name="opePro.createTime" value="<fmt:formatDate type="both" value="${opePro.createTime}" />">
		<input type="hidden" name="opePro.updateTime" value="${opePro.updateTime}">
		<input type="hidden" name="opePro.createUserID" value="${opePro.createUserID}">
		<input type="hidden" name="opePro.updateUserID" value="${opePro.updateUserID}">
		<input type="hidden" name="opePro.deptId" value="${opePro.deptId}">
		<input type="hidden" name="opePro.delFlag" value="${opePro.delFlag}">
		<input type="hidden" name="opePro.linkId" value="${opePro.linkId}">
			<table width="100%" border="0">
				
				<tr>
					<th width="15%">车间名称</th>
					<td width="35%">
						<cus:SelectOneTag style="width:60%" property="opePro.operationWorkshopId" defaultText='请选择' codeSql="select t.ROW_ID,t.WORKSHOP_NAME from WORKSHOP t where t.delflag = 0 and t.COMPANY_ID='${companyId}'" value="${opePro.operationWorkshopId}" datatype="*"  nullmsg='车间名称不能为空！'/><font style='color:red'>*</font>
					</td>
					<th width="15%">岗位名称</th>
					<td width="35%"><input name="opePro.operationPostname" style="width:60%" value="${opePro.operationPostname}" type="text" datatype="*1-127" errormsg='岗位名称必须是1到127位字符！' nullmsg='岗位名称不能为空！' sucmsg='岗位名称填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">最大工作时间</th>
					<td width="35%"><input name="opePro.operationMostWorktime" style="width:60%" value="${opePro.operationMostWorktime}" type="text" maxlength="127"></td>
					<th width="15%">岗位员工数</th>
					<td width="35%"><input name="opePro.operationPostCount" style="width:60%" value="${opePro.operationPostCount}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">是否倒班</th>
					<td width="35%"><cus:SelectOneTag property="opePro.operationShiftsOrnot" style="width:60%" defaultText='请选择' codeName="是或否" value="${opePro.operationShiftsOrnot}" datatype="*"  nullmsg='是否倒班不能为空！'/><font style='color:red'>*</font></td>
					<th width="15%">倒班总人数</th>
					<td width="35%"><input name="opePro.operationShiftsPersons" style="width:60%" value="${opePro.operationShiftsPersons}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">起草人</th>
					<td width="35%"><input name="opePro.operationDraftPerson" style="width:60%" value="${opePro.operationDraftPerson}" type="text" maxlength="127"></td>
					<th width="15%">批准人</th>
					<td width="35%"><input name="opePro.operationAuthorization" style="width:60%" value="${opePro.operationAuthorization}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">操作规程编号</th>
					<td width="35%"><input name="opePro.operationCode" style="width:60%" value="${opePro.operationCode}" type="text" maxlength="127"></td>
					<th width="15%">有效时间</th>
					<td width="35%"><input name="opePro.effectiveDate" style="width:60%" value="<fmt:formatDate type='date' value='${opePro.effectiveDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
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
						<table id="czgcfj">
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_opePro');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
