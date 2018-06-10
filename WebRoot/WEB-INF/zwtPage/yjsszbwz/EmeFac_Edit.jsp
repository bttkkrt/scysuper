<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
		<script>
	$(document).ready(function() {
		  uploadPic("uploadify","${emeFac.linkId}","yjsszbwz","yjsszbwzfj","fileQueue");
		});
		var type="${type}";
		function getMark(){
			return '-1';
		}
		function getChlidCode(){
			return "${emeFac.mapkey}";
		}
		function setJW(jd,wd)
		{
			document.getElementById('longitude').value = jd;
			document.getElementById('latitude').value = wd;
		}
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="emeFacSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="emeFac.id" value="${emeFac.id}">
		<input type="hidden" name="emeFac.createTime" value="<fmt:formatDate type="both" value="${emeFac.createTime}" />">
		<input type="hidden" name="emeFac.updateTime" value="${emeFac.updateTime}">
		<input type="hidden" name="emeFac.createUserID" value="${emeFac.createUserID}">
		<input type="hidden" name="emeFac.updateUserID" value="${emeFac.updateUserID}">
		<input type="hidden" name="emeFac.deptId" value="${emeFac.deptId}">
		<input type="hidden" name="emeFac.delFlag" value="${emeFac.delFlag}">
		<input type="hidden" name="emeFac.linkId" value="${emeFac.linkId}">
		<input type="hidden" name="emeFac.type" value="${type}">
		<input type="hidden" name="emeFac.mapkey" value="${emeFac.mapkey}">
		<input type="hidden" id="longitude" name="emeFac.longitude" value="${emeFac.longitude}">
		<input type="hidden" id="latitude" name="emeFac.latitude" value="${emeFac.latitude}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">物资名称</th>
					<td width="35%"><input id="facilityName" name="emeFac.facilityName" style="width:60%" value="${emeFac.facilityName}" type="text" datatype="*1-127" errormsg='物资名称必须是1到127位字符！' nullmsg='物资名称不能为空！' sucmsg='物资名称填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">物资级别</th>
					<td width="35%"><cus:SelectOneTag property="emeFac.facilityLevel" style="width:60%" defaultText='请选择' codeName="应急物资级别" value="${emeFac.facilityLevel}" /></td>
				</tr>
				<tr>
					<th width="15%">物资数量</th>
					<td width="35%"><input name="emeFac.facilityNumber" style="width:60%" value="${emeFac.facilityNumber}" type="text" datatype="*1-127" errormsg='物资数量必须是1到127位字符！' nullmsg='物资数量不能为空！' sucmsg='物资数量填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">物资型号</th>
					<td width="35%"><input name="emeFac.facilityModel" style="width:60%" value="${emeFac.facilityModel}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">物资规格</th>
					<td width="35%"><input name="emeFac.facilitySpecific" style="width:60%" value="${emeFac.facilitySpecific}" type="text" maxlength="127"></td>
					<th width="15%">购入日期</th>
					<td width="35%"><input name="emeFac.purchaseDate" style="width:60%" value="<fmt:formatDate type='date' value='${emeFac.purchaseDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">生产厂家</th>
					<td width="35%"><input name="emeFac.vender" style="width:60%" value="${emeFac.vender}" type="text" maxlength="127"></td>
					<th width="15%">出厂日期</th>
					<td width="35%"><input name="emeFac.produceTime" id="produceTime" style="width:60%" value="<fmt:formatDate type='date' value='${emeFac.produceTime}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'expiryDate\')}'})"></td>
				</tr>
				<tr>
					<th width="15%">有效期至</th>
					<td width="35%"><input name="emeFac.expiryDate" id="expiryDate" style="width:60%" value="<fmt:formatDate type='date' value='${emeFac.expiryDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'produceTime\')}'})"></td>
					<th width="15%">用途说明</th>
					<td width="35%"><input name="emeFac.purposeDescrip" style="width:60%" value="${emeFac.purposeDescrip}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">性能说明</th>
					<td width="35%"><input name="emeFac.performanceDescrip" style="width:60%" value="${emeFac.performanceDescrip}" type="text" maxlength="127"></td>
					<th width="15%">存放地点</th>
					<td width="35%"><input name="emeFac.storageLocation" style="width:60%" value="${emeFac.storageLocation}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">负责保管人</th>
					<td width="35%"><input name="emeFac.keeper" value="${emeFac.keeper}" style="width:60%" type="text" maxlength="127"></td>
					<th width="15%">保管人联系方式</th>
					<td width="35%"><input name="emeFac.keeperPhone" style="width:60%" value="${emeFac.keeperPhone}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="emeFac.remark" style="width:96%;height:60px">${emeFac.remark}</textarea></td>
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
						<table id="yjsszbwzfj">
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
					<td width="100%" colspan="4">
						<div style="width:100%;">
				        	<iframe id="map" name="map" src="${ctx}/webResources/Map/index.html"  style="height:500px;width:100%; border:hidden; "scrolling="no" ></iframe>


				    	</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit">添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit">更新<b></b></a>&nbsp;
						</s:else>						
 					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
