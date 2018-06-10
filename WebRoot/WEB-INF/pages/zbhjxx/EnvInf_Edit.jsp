<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	$(document).ready(function() {
		 uploadPic("uploadify","${envInf.linkId}","zbhjxx","xgzp","fileQueue");
	});
		function getMark(){
			return '-1';
		}
		function getChlidCode(){
			return "${envInf.mapkey}";
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
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="envInfSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="envInf.id" value="${envInf.id}">
		<input type="hidden" name="envInf.createTime" value="<fmt:formatDate type="both" value="${envInf.createTime}" />">
		<input type="hidden" name="envInf.updateTime" value="${envInf.updateTime}">
		<input type="hidden" name="envInf.createUserID" value="${envInf.createUserID}">
		<input type="hidden" name="envInf.updateUserID" value="${envInf.updateUserID}">
		<input type="hidden" name="envInf.deptId" value="${envInf.deptId}">
		<input type="hidden" name="envInf.delFlag" value="${envInf.delFlag}">
		<input type="hidden" name="envInf.linkId" value="${envInf.linkId}">
		<input type="hidden" name="envInf.mapkey" value="${envInf.mapkey}">
		<input type="hidden" id="longitude" name="envInf.longitude" value="${envInf.longitude}">
		<input type="hidden" id="latitude" name="envInf.latitude" value="${envInf.latitude}">
		
			<table width="100%" border="0">
				<tr>
					<th width="17%">周边环境类型</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="envInf.surroundingEnvironmentType" defaultText='请选择' codeName="周边环境类型"  datatype="*"  value="${envInf.surroundingEnvironmentType}" /><font style='color:red'>*</font></td>
					<th width="17%">周边环境名称</th>
					<td width="35%"><input id="surroundingEnvironmentName" style="width:60%;" name="envInf.surroundingEnvironmentName" errormsg='周边环境名称必须是1到127位字符！' nullmsg='周边环境名称不能为空！' sucmsg='周边环境名称填写正确！'   datatype="*1-127"  value="${envInf.surroundingEnvironmentName}" type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="17%">周边环境方位</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="envInf.surroundingEnvironment" defaultText='请选择' codeName="周边环境方位"  datatype="*" value="${envInf.surroundingEnvironment}" /><font style='color:red'>*</font></td>
					<th width="17%">与危险源最小距离</th>
					<td width="35%"><input name="envInf.minimumDistance" style="width:60%;" errormsg='与危险源最小距离必须是1到127位字符！' nullmsg='与危险源最小距离不能为空！' sucmsg='与危险源最小距离填写正确！'   datatype="*1-127"  value="${envInf.minimumDistance}" type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="17%">建筑结构</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="envInf.buildingStructure" defaultText='请选择' codeName="建筑结构"   datatype="*"  value="${envInf.buildingStructure}" /><font style='color:red'>*</font></td>
					
					<th width="17%">建筑高度</th>
					<td width="35%"><input name="envInf.buildingHeight" style="width:60%;" value="${envInf.buildingHeight}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="17%">人员类型</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" property="envInf.dangerousChemicalName" defaultText='请选择' codeName="周围环境人员类型" value="${envInf.dangerousChemicalName}" /></td>
					<th width="17%">人员数量</th>
					<td width="35%"><input name="envInf.personnelType" style="width:60%;" value="${envInf.personnelType}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="17%">联系人</th>
					<td width="35%"><input name="envInf.contactPerson" style="width:60%;" value="${envInf.contactPerson}" errormsg='联系人必须是1到127位字符！' nullmsg='联系人不能为空！' sucmsg='联系人填写正确！'   datatype="*1-127"  type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="17%">联系人固定电话</th>
					<td width="35%"><input name="envInf.telephone" style="width:60%;" value="${envInf.telephone}" type="text" errormsg='联系人固定电话超长！'  nullmsg='联系人固定电话不能为空！' sucmsg='联系人固定电话填写正确！'   datatype="*1-127"  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="17%">联系人移动电话</th>
					<td width="35%"><input name="envInf.mobile" style="width:60%;" datatype="m" value="${envInf.mobile}"  errormsg='联系人移动电话格式错误！'  nullmsg='联系人移动电话不能为空！' sucmsg='联系人移动电话填写正确！'   datatype="*1-127" type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="17%">联系人电子邮箱</th>
					<td width="35%"><input name="envInf.email" style="width:60%;" nullmsg='联系人电子邮箱不能为空！' sucmsg='联系人电子邮箱填写正确！'   datatype="e"   value="${envInf.email}" type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">附件</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue"/>
				    	<input type="file" name="uploadify" id="uploadify"/>
			    		<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已上传附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="xgzp">
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
					<th width="17%">备注</th>
					<td width="96%" colspan="3"><textarea name="envInf.remark" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:96%;height:60px">${envInf.remark}</textarea></td>
				</tr>
				<tr>
					<td width="100%" colspan="4">
						<div style="width:100%;">
				        	<iframe id="map" name="map" src="${ctx}/webResources/Map/index.html"  style="height:300px;width:100%; border:hidden; "scrolling="no" ></iframe>

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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_envInf');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
