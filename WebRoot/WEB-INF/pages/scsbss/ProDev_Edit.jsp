<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify1","${proDev.linkId}","scsbss","cchgzfj","fileQueue1");
		  uploadPic("uploadify2","${proDev.linkId}","scsbss","sywhsmfj","fileQueue2");
		  uploadPic("uploadify3","${proDev.linkId}","scsbss","azjswjfj","fileQueue3");
		});
		
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
			<font style='color:red'>*只需上报工艺类、辅助设施类的设备信息，同一设备型号的设备只需上传一条记录，在设备编号中填写所有编号，合格证如果能找到，最好上传</font>
			<font style='color:red'>*需先在企业作业安全-生产车间管理内录入车间信息，此处车间才能选择</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="proDevSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="proDev.id" value="${proDev.id}">
		<input type="hidden" name="proDev.createTime" value="<fmt:formatDate type="both" value="${proDev.createTime}" />">
		<input type="hidden" name="proDev.updateTime" value="${proDev.updateTime}">
		<input type="hidden" name="proDev.createUserID" value="${proDev.createUserID}">
		<input type="hidden" name="proDev.updateUserID" value="${proDev.updateUserID}">
		<input type="hidden" name="proDev.deptId" value="${proDev.deptId}">
		<input type="hidden" name="proDev.delFlag" value="${proDev.delFlag}">
		<input type="hidden" name="proDev.areaId" value="${proDev.areaId}">
		<input type="hidden" name="proDev.areaName" value="${proDev.areaName}">
		<input type="hidden" name="proDev.companyId" value="${proDev.companyId}">
		<input type="hidden" name="proDev.companyName" value="${proDev.companyName}">
		<input type="hidden" name="proDev.linkId" value="${proDev.linkId}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">车间名称</th>
					<td width="35%">
						<cus:SelectOneTag style="width:60%;" property="proDev.deviceWorkshopId" defaultText='请选择' codeSql="select t.ROW_ID,t.WORKSHOP_NAME from WORKSHOP t where t.delflag = 0 and t.COMPANY_ID='${proDev.companyId}'" value="${proDev.deviceWorkshopId}" />
					</td>
					<th width="15%">设备名称</th>
					<td width="35%"><input name="proDev.deviceName" style="width:60%;" value="${proDev.deviceName}"  errormsg='设备名称必须是1到127位字符！' nullmsg='设备名称不能为空！' sucmsg='设备名称填写正确！'   datatype="*1-127" type="text"  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">设备用途</th>
					<td width="35%"><input name="proDev.deviceUse" style="width:60%;" value="${proDev.deviceUse}" type="text" maxlength="127"></td>
					<th width="15%">设备编号</th>
					<td width="35%"><input name="proDev.deviceCode" style="width:60%;" value="${proDev.deviceCode}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">设备型号</th>
					<td width="35%"><input name="proDev.deviceType" style="width:60%;" value="${proDev.deviceType}" type="text" maxlength="127"></td>
					<th width="15%">设备出厂日期</th>
					<td width="35%"><input name="proDev.deviceManufactureDate" style="width:60%;" value="<fmt:formatDate type='both' pattern="yyyy-MM-dd"   value='${proDev.deviceManufactureDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">设备生产商</th>
					<td width="96%" colspan="3"><input style="width:96%" name="proDev.deviceProductCommpnay" value="${proDev.deviceProductCommpnay}" type="text" maxlength="127"></td>
				</tr>
				
				<tr>
					<th width="10%">出厂合格证</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue1"/>
				    	<input type="file" name="uploadify1" id="uploadify1"/>
			    		<a href="javascript:jQuery('#uploadify1').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify1').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="cchgzfj">
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
								   <a href="javascript:del('${item.id}');">删除</a>
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				
				
				<tr>
					<th width="10%">使用维护说明</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue2"/>
				    	<input type="file" name="uploadify2" id="uploadify2"/>
			    		<a href="javascript:jQuery('#uploadify2').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify2').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="sywhsmfj">
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
								   <a href="javascript:del('${item.id}');">删除</a>
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				
				
				<tr>
					<th width="10%">安装技术文件</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue3"/>
				    	<input type="file" name="uploadify3" id="uploadify3"/>
			    		<a href="javascript:jQuery('#uploadify3').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify3').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="azjswjfj">
							  <c:forEach var="item" items="${picList3}">
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
								   <a href="javascript:del('${item.id}');">删除</a>
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>	
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea name="proDev.deviceRemark" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:96%;height:60px">${proDev.deviceRemark}</textarea></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_proDev');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
