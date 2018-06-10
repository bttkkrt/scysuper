<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if> <s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script type="text/javascript">
		function getMark(){
			return '-1';
		}
		function getChlidCode(){
			return "${publicBoard.mapkey}";
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
	<form name="myform1" method="post" enctype="multipart/form-data" action="publicBoardSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="publicBoard.id" value="${publicBoard.id}">
		<input type="hidden" name="publicBoard.createTime" value="<fmt:formatDate type="both" value="${publicBoard.createTime}" />">
		<input type="hidden" name="publicBoard.updateTime" value="${publicBoard.updateTime}">
		<input type="hidden" name="publicBoard.createUserID" value="${publicBoard.createUserID}">
		<input type="hidden" name="publicBoard.updateUserID" value="${publicBoard.updateUserID}">
		<input type="hidden" name="publicBoard.deptId" value="${publicBoard.deptId}">
		<input type="hidden" name="publicBoard.delFlag" value="${publicBoard.delFlag}">
		<input type="hidden" name="publicBoard.mapkey" value="${publicBoard.mapkey}">
		<input type="hidden" id="longitude" name="publicBoard.publicLongitude" value="${publicBoard.publicLongitude}">
		<input type="hidden" id="latitude" name="publicBoard.publicLatitude" value="${publicBoard.publicLatitude}">
		
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">公告栏类别</th>
					<td width="35%"><input name="publicBoard.publicType" style="width:60%;" value="${publicBoard.publicType}" type="text" maxlength="127"></td>
					<th width="15%">公告栏位置</th>
					<td width="35%"><input name="publicBoard.publicAddress" style="width:60%;" value="${publicBoard.publicAddress}" type="text" datatype="*1-127" errormsg='公告栏位置必须是1到127位字符！' nullmsg='公告栏位置不能为空！' sucmsg='公告栏位置填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					
				</tr>
				<tr>
					<th width="15%">公告栏内容</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="publicBoard.publicContent" style="width:96%;height:60px">${publicBoard.publicContent}</textarea></td>
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
