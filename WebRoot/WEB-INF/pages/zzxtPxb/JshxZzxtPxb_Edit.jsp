<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				var pxnr = document.getElementById('pxnr').value;
				if(pxnr.length < 30)
				{
					alert("培训内容不少于30个字!");
					return false;
				}
				else
				{
					document.myform1.action="jshxZzxtPxbSave.action";
					document.myform1.submit();
				}
			}
		}
		
			function queryPerson(){
var OpenWindow = window.showModalDialog("queryPersonList.action", '', 'dialogHeight=600px;dialogWidth=800px;scroll=no,resizable=no, status=no');
		if(typeof(OpenWindow) != 'undefined')
		{
			var sonValue = OpenWindow.split(";");
			document.getElementById('personId').value = sonValue[0];
			document.getElementById('personName').value = sonValue[1];
		}
		}
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="jshxZzxtPxb.id" value="${jshxZzxtPxb.id}">
		<input type="hidden" name="jshxZzxtPxb.createTime" value="<fmt:formatDate type="both" value="${jshxZzxtPxb.createTime}" />">
		<input type="hidden" name="jshxZzxtPxb.updateTime" value="${jshxZzxtPxb.updateTime}">
		<input type="hidden" name="jshxZzxtPxb.createUserID" value="${jshxZzxtPxb.createUserID}">
		<input type="hidden" name="jshxZzxtPxb.updateUserID" value="${jshxZzxtPxb.updateUserID}">
		<input type="hidden" name="jshxZzxtPxb.deptId" value="${jshxZzxtPxb.deptId}">
		<input type="hidden" name="jshxZzxtPxb.delFlag" value="${jshxZzxtPxb.delFlag}">
		<input type="hidden" name="jshxZzxtPxb.szzname" value="${jshxZzxtPxb.szzname}">
		<input type="hidden" name="jshxZzxtPxb.qymc" value="${jshxZzxtPxb.qymc}">
		<input type="hidden" name="jshxZzxtPxb.szzid" value="${jshxZzxtPxb.szzid}">
		<input type="hidden" name="jshxZzxtPxb.qyid" value="${jshxZzxtPxb.qyid}">
		<input type="hidden" name="jshxZzxtPxb.qylx" value="${jshxZzxtPxb.qylx}">
		<input type="hidden" name="jshxZzxtPxb.hyfl" value="${jshxZzxtPxb.hyfl}">
		<input type="hidden" name="jshxZzxtPxb.qygm" value="${jshxZzxtPxb.qygm}">
		<input type="hidden" name="jshxZzxtPxb.qyzclx" value="${jshxZzxtPxb.qyzclx}">
		<input name="jshxZzxtPxb.personId" value="${jshxZzxtPxb.personId}"  type="hidden" id="personId" type="text" maxlength="255">
		<input type="hidden" name="jshxZzxtPxb.ifwhpqylx" value="${jshxZzxtPxb.ifwhpqylx}">
		<input type="hidden" name="jshxZzxtPxb.ifzywhqylx" value="${jshxZzxtPxb.ifzywhqylx}">
		<input type="hidden" name="jshxZzxtPxb.ifyhbzjyqy" value="${jshxZzxtPxb.ifyhbzjyqy}">
		<input type="hidden" name="jshxZzxtPxb.szc" value="${jshxZzxtPxb.szc}">
		<input type="hidden" name="jshxZzxtPxb.szcname" value="${jshxZzxtPxb.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">培训人员</th>
					<td width="85%" colspan="3">
					<input  id="personName" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${jshxZzxtPxb.personName}" readonly="readonly"><font style="color:red">*</font>
					<input type=button value="点击查询" onclick="javascript:queryPerson();">
					</td>
					<td>
					<td>
				</tr>
				<tr>
					<th width="15%">培训时间</th>
					<td width="35%"><input name="jshxZzxtPxb.pxsj" value="<fmt:formatDate type='date' value='${jshxZzxtPxb.pxsj}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="15%">培训学时</th>
					<td width="35%"><input name="jshxZzxtPxb.pxxs" value="${jshxZzxtPxb.pxxs}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">授课人</th>
					<td width="35%"><input name="jshxZzxtPxb.skr" value="${jshxZzxtPxb.skr}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="15%">考核成绩</th>
					<td width="35%"><input name="jshxZzxtPxb.khcj" value="${jshxZzxtPxb.khcj}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">培训内容</th>
					<td width="85%" colspan="3">
						<textarea id="pxnr" name="jshxZzxtPxb.pxnr" style="width:100%;height:150px">${jshxZzxtPxb.pxnr}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
