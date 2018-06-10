<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script type="text/javascript">
		function getMark(){
			return '-1';
		}
		function getChlidCode(){
			return "${policeInstitutions.mapkey}";
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
	<form name="myform1" method="post" enctype="multipart/form-data" action="policeInstitutionsSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="policeInstitutions.id" value="${policeInstitutions.id}">
		<input type="hidden" name="policeInstitutions.createTime" value="<fmt:formatDate type="both" value="${policeInstitutions.createTime}" />">
		<input type="hidden" name="policeInstitutions.updateTime" value="${policeInstitutions.updateTime}">
		<input type="hidden" name="policeInstitutions.createUserID" value="${policeInstitutions.createUserID}">
		<input type="hidden" name="policeInstitutions.updateUserID" value="${policeInstitutions.updateUserID}">
		<input type="hidden" name="policeInstitutions.deptId" value="${policeInstitutions.deptId}">
		<input type="hidden" name="policeInstitutions.delFlag" value="${policeInstitutions.delFlag}">
		<input type="hidden" name="policeInstitutions.mapkey" value="${policeInstitutions.mapkey}">
		<input type="hidden" id="longitude" name="policeInstitutions.longitude" value="${policeInstitutions.longitude}">
		<input type="hidden" id="latitude" name="policeInstitutions.latitude" value="${policeInstitutions.latitude}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">机构编号</th>
					<td width="35%"><input name="policeInstitutions.agencyNum" style="width:60%"  value="${policeInstitutions.agencyNum}" type="text" datatype="*1-127" errormsg='机构编号必须是1到127位字符！' nullmsg='机构编号不能为空！' sucmsg='机构编号填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">机构名称</th>
					<td width="35%"><input id="agencyName" name="policeInstitutions.agencyName" style="width:60%"  value="${policeInstitutions.agencyName}" type="text" datatype="*1-127" errormsg='机构名称必须是1到127位字符！' nullmsg='机构名称不能为空！' sucmsg='机构名称填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">机构地址</th>
					<td width="35%"><input name="policeInstitutions.agencyAddress" style="width:60%"  value="${policeInstitutions.agencyAddress}" type="text" datatype="*1-127" errormsg='机构地址必须是1到127位字符！' nullmsg='机构地址不能为空！' sucmsg='机构地址填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">联系方式</th>
					<td width="35%"><input name="policeInstitutions.mobile" style="width:60%"  value="${policeInstitutions.mobile}" type="text"   maxlength="127"></td>
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
