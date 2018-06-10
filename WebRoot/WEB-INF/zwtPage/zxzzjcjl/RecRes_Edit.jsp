<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	$(document).ready(function() {
		  uploadPicOnly("uploadify1","${recRes.linkId}","zxzzjcjl","xctpfj","fileQueue1");
		  uploadPicOnly("uploadify2","${recRes.linkId}","zxzzjcjl","zghtpfj","fileQueue2");
		});
		
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="recResSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="recRes.id" value="${recRes.id}">
		<input type="hidden" name="recRes.createTime" value="<fmt:formatDate type="both" value="${recRes.createTime}" />">
		<input type="hidden" name="recRes.updateTime" value="${recRes.updateTime}">
		<input type="hidden" name="recRes.createUserID" value="${recRes.createUserID}">
		<input type="hidden" name="recRes.updateUserID" value="${recRes.updateUserID}">
		<input type="hidden" name="recRes.deptId" value="${recRes.deptId}">
		<input type="hidden" name="recRes.delFlag" value="${recRes.delFlag}">
		<input type="hidden" name="recRes.linkId" value="${recRes.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">计划名称</th>
					<c:if test="${flag=='add' }">
					<td  width="35%">
						<s:select list="recPlaList" cssStyle="width: 60%" listKey="id" listValue="planName" name="recRes.planId" headerKey="" headerValue="---请选择---"  theme="simple" />
					</td>
					</c:if>	
					<c:if test="${flag!='add' }">
					<td>&nbsp;<input type="hidden" name="recRes.planId" value="${recRes.planId}">
						<cus:hxlabel   codeSql="select ROW_ID,PLAN_NAME  from  REC_PLA where ROW_ID='${recRes.planId}'"  itemValue="${recRes.planId}" />
					</td>
					</c:if>
					<th width="15%">检查时间</th>
					<td width="35%"><input name="recRes.checkTime"  style="width: 60%"   value="<fmt:formatDate pattern="yyyy-MM-dd" type='both' value='${recRes.checkTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="*1-127" nullmsg='检查时间不能为空！' sucmsg='检查时间填写正确！'><font style='color:red'>*</font></td>
				</tr>
				<tr>	
					<th width="15%">检查部门</th>
					<td width="35%"><input name="recRes.checkDept" style="width: 60%" value="${recRes.checkDept}" type="text" maxlength="127"></td>
					<th width="15%">整改期限</th>
					<td width="35%"><input name="recRes.rectificationDate" style="width: 60%"  value="<fmt:formatDate type='both' pattern="yyyy-MM-dd" value='${recRes.rectificationDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>	
					<th width="15%">检查人员名单</th>
					<td width="85%" colspan="3" ><input name="recRes.checkUser" style="width: 80%" value="${recRes.checkUser}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">检查内容</th>
					<td width="85%" colspan="3" ><textarea name="recRes.checkContent" dataType="*1-2000" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:80%;height:60px">${recRes.checkContent}</textarea><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">整改措施</th>
					<td width="85%" colspan="3" ><textarea name="recRes.rectificationMeasure" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:80%;height:60px">${recRes.rectificationMeasure}</textarea></td>
					
				</tr>
				<tr>
					<th width="10%">现场图片</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue1"/>
				    	<input type="file" name="uploadify1" id="uploadify1"/>
			    		<a href="javascript:jQuery('#uploadify1').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify1').uploadifyClearQueue()">取消所有上传</a>
						</div>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="xctpfj">
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
					<th width="10%">整改后图片</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue2"/>
				    	<input type="file" name="uploadify2" id="uploadify2"/>
			    		<a href="javascript:jQuery('#uploadify2').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify2').uploadifyClearQueue()">取消所有上传</a>
						</div>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="zghtpfj">
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
