<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript">
		function getMark(){
			return '-1';
		}
		function getChlidCode(){
			return "${equAndFac.mapkey}";
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
	<form name="myform1" method="post" enctype="multipart/form-data" action="equAndFacSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="equAndFac.id" value="${equAndFac.id}">
		<input type="hidden" name="equAndFac.createTime" value="<fmt:formatDate type="both" value="${equAndFac.createTime}" />">
		<input type="hidden" name="equAndFac.updateTime" value="${equAndFac.updateTime}">
		<input type="hidden" name="equAndFac.createUserID" value="${equAndFac.createUserID}">
		<input type="hidden" name="equAndFac.updateUserID" value="${equAndFac.updateUserID}">
		<input type="hidden" name="equAndFac.deptId" value="${equAndFac.deptId}">
		<input type="hidden" name="equAndFac.delFlag" value="${equAndFac.delFlag}">
		<input type="hidden" name="equAndFac.mapkey" value="${equAndFac.mapkey}">
		<input type="hidden" id="longitude" name="equAndFac.equipmentLongitude" value="${equAndFac.equipmentLongitude}">
		<input type="hidden" id="latitude" name="equAndFac.equipmentLatitude" value="${equAndFac.equipmentLatitude}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">设备名称</th>
					<td width="35%"><input id="equipmentName" style="width:60%" name="equAndFac.equipmentName" value="${equAndFac.equipmentName}" errormsg='设备名称必须是1到127位字符！' nullmsg='设备名称不能为空！' sucmsg='设备名称填写正确！'   datatype="*1-127" type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">设备地点</th>
					<td width="35%"><input name="equAndFac.equipmentPlace" style="width:60%" value="${equAndFac.equipmentPlace}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">设备编号</th>
					<td width="35%"><input name="equAndFac.equipmentNumber" style="width:60%" value="${equAndFac.equipmentNumber}" type="text" maxlength="127"></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_equAndFac');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
