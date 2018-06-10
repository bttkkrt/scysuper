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
			return "${informCard.mapkey}";
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
	<form name="myform1" method="post" enctype="multipart/form-data" action="informCardSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="informCard.id" value="${informCard.id}">
		<input type="hidden" name="informCard.createTime" value="<fmt:formatDate type="both" value="${informCard.createTime}" />">
		<input type="hidden" name="informCard.updateTime" value="${informCard.updateTime}">
		<input type="hidden" name="informCard.createUserID" value="${informCard.createUserID}">
		<input type="hidden" name="informCard.updateUserID" value="${informCard.updateUserID}">
		<input type="hidden" name="informCard.deptId" value="${informCard.deptId}">
		<input type="hidden" name="informCard.delFlag" value="${informCard.delFlag}">
		<input type="hidden" name="informCard.mapkey" value="${informCard.mapkey}">
		<input type="hidden" id="longitude" name="informCard.informLongitude" value="${informCard.informLongitude}">
		<input type="hidden" id="latitude" name="informCard.informLatitude" value="${informCard.informLatitude}">
		
			<table width="100%" border="0">
			
				<tr>
					<th width="15%">告知卡类别</th>
					<td width="35%"><input name="informCard.informType" style="width:60%;" value="${informCard.informType}" type="text" maxlength="127"></td>
					<th width="15%">告知卡位置</th>
					<td width="35%"><input name="informCard.informAddress" style="width:60%;" value="${informCard.informAddress}" type="text" datatype="*1-127" errormsg='告知卡位置必须是1到127位字符！' nullmsg='告知卡位置不能为空！' sucmsg='告知卡位置填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">告知卡内容</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="informCard.informContent" style="width:96%;height:60px">${informCard.informContent}</textarea></td>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_informCard');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
