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
			return "${comDanIde.mapkey}";
		}
		
		function setJW(jd,wd)
		{
			document.getElementById('longitude').value = jd;
			document.getElementById('latitude').value = wd;
		}
		
		function saveDot(state){
			document.getElementById('auditState').value = state; 
		}
		function setName(name){
			$("#checkPeopleName").val(name);
		}
		
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
			<font style='color:red'>*若信息还未填写完整，可以点击保存暂存（安监查看不到），若填写完整，则点击提交上报安监审核，提交后信息不可修改删除</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="comDanIdeSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="comDanIde.id" value="${comDanIde.id}">
		<input type="hidden" name="comDanIde.createTime" value="<fmt:formatDate type="both" value="${comDanIde.createTime}" />">
		<input type="hidden" name="comDanIde.updateTime" value="${comDanIde.updateTime}">
		<input type="hidden" name="comDanIde.createUserID" value="${comDanIde.createUserID}">
		<input type="hidden" name="comDanIde.updateUserID" value="${comDanIde.updateUserID}">
		<input type="hidden" name="comDanIde.deptId" value="${comDanIde.deptId}">
		<input type="hidden" name="comDanIde.delFlag" value="${comDanIde.delFlag}">
		<input type="hidden" name="comDanIde.mapkey" value="${comDanIde.mapkey}">
		<input type="hidden"  id="checkPeopleName" name="comDanIde.checkPeopleName" value="${comDanIde.checkPeopleName}">
		<input type="hidden" id="auditState" name="comDanIde.auditState" value="待提交">
		<input type="hidden" id="longitude" name="comDanIde.longitude" value="${comDanIde.longitude}">
		<input type="hidden" id="latitude" name="comDanIde.latitude" value="${comDanIde.latitude}">
		
			<table width="100%" border="0">
				
				<tr>
					<th width="15%">重点危险源名称</th>
					<td width="35%"><input id="dangerName" name="comDanIde.dangerName" style="width:60%" value="${comDanIde.dangerName}" type="text" datatype="*1-127" errormsg='重点危险源必须是1到127位字符！' nullmsg='重点危险源不能为空！' sucmsg='重点危险源填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">重点危险源类别</th>
					<td width="35%"><input name="comDanIde.dangerType" style="width:60%" value="${comDanIde.dangerType}" type="text"  maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">重点危险源级别</th>
					<td width="35%"><input name="comDanIde.dangerLevel" style="width:60%" value="${comDanIde.dangerLevel}" type="text"  maxlength="127"></td>
					<th width="15%">重点危险源地址</th>
					<td width="35%"><input name="comDanIde.dangerAddress" style="width:60%" value="${comDanIde.dangerAddress}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">安全负责人</th>
					<td width="35%"><input name="comDanIde.safePerson" style="width:60%" value="${comDanIde.safePerson}" type="text" maxlength="127"></td>
					<th width="15%">联系方式</th>
					<td width="35%"><input name="comDanIde.tele" style="width:60%" value="${comDanIde.tele}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">巡查频率</th>
					<td width="35%">
					<cus:SelectOneTag property="comDanIde.checkFrequency"  style="width:60%"  codeName="巡查频率" value="${comDanIde.checkFrequency}" /></td>
				</tr>
				<tr>
					<th width="15%">巡查人员</th>
				    <td width="35%" colspan="3">
						<cus:hxcheckbox property="comDanIde.checkPeopleId" codeSql="select t.ROW_ID as id,t.USER_NAME as checkPeopleName from PATROL_USER t where t.delflag=0 and t.createUserID= '${comDanIde.createUserID}'" value="${comDanIde.checkPeopleId}" datatype="*" nullmsg='巡查人员不能为空！' sucmsg='巡查人员填写正确！' /><font style='color:red'>*</font>
					</td>
				</tr>
				
				<tr>
				  <th width="15%">要求</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="comDanIde.remark" style="width:96%;height:60px">${comDanIde.remark}</textarea></td>
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
						<a href="#" class="btn_01" type="submit" onclick="saveDot('待提交');" type="submit">保存<b></b></a>&nbsp;
						<a href="#" class="btn_01" type="submit" onclick="saveDot('待审核');" type="submit">提交<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_comDanIde');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
