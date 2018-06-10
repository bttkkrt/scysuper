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
				
				document.myform1.action="vedioNameSave.action";
				document.myform1.submit();
			}
		}
		
		
		function queryQy()
		{
			var OpenWindow = window.showModalDialog("${ctx}/jsp/company/queryCompanyList.action", '', 'dialogHeight=600px;dialogWidth=800px;scroll=no,resizable=no, status=no');
			if(typeof(OpenWindow) != 'undefined')
			{
				var sonValue = OpenWindow.split(";");
				document.getElementById('qyid').value = sonValue[0];
				document.getElementById('companyName').value = sonValue[1];
				document.getElementById('dwdz1').value = sonValue[2];
			}
		}
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="vedioName.id" value="${vedioName.id}">
		<input type="hidden" name="vedioName.createTime" value="<fmt:formatDate type="both" value="${vedioName.createTime}" />">
		<input type="hidden" name="vedioName.updateTime" value="${vedioName.updateTime}">
		<input type="hidden" name="vedioName.createUserID" value="${vedioName.createUserID}">
		<input type="hidden" name="vedioName.updateUserID" value="${vedioName.updateUserID}">
		<input type="hidden" name="vedioName.deptId" value="${vedioName.deptId}">
		<input type="hidden" name="vedioName.delFlag" value="${vedioName.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">监控平台视频名称</th>
					<td width="35%"><input name="vedioName.vedioname" value="${vedioName.vedioname}" type="text" dataType="Require" msg="此项为必填" maxlength="200"><font color="red">*</font></td>
					<th width="15%">显示名称</th>
					<td width="35%"><input name="vedioName.showname" value="${vedioName.showname}" type="text" maxlength="200"></td>
				</tr>
				<tr>
					<th width="15%">企业名称</th>
					<td width="35%">
						<input id="companyName" name="vedioName.companyname" value="${vedioName.companyname}" type="text" maxlength="200">
						<input id="qyid" name="vedioName.companyid" value="${vedioName.companyid}" type="hidden">
						<input id="dwdz1" name="vedioName.dwdz1" value="${vedioName.dwdz1}" type="hidden">
						<a href="#" class="easyui-linkbutton" onclick="javascript:queryQy()" iconCls="icon-save">选择</a>
					</td>
					<th width="15%">企业类型</th>
					<td width="35%">
						<select id="qylx" name="vedioName.qylx" value="${vedioName.qylx}" style="width:90%">
							<option value="0" <s:if test="vedioName.qylx == 0">selected</s:if>>危化企业</option>
							<option value="1" <s:if test="vedioName.qylx == 1">selected</s:if>>涉粉企业</option>
						</select>
					</td>
				</tr>
				<tr>
					<th width="15%">排列顺序</th>
					<td width="35%"><input name="vedioName.sort" value="${vedioName.sort}" type="text" maxlength="200"></td>
					<th width="15%">备注</th>
					<td width="35%"><input name="vedioName.remark" value="${vedioName.remark}" type="text" maxlength="2000"></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center" style="text-align:center">
						<p text-align="center">
							<s:if test="flag=='add'">
								<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
							</s:if>
							<s:else>
								<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
							</s:else>						
							<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
							<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
						</p>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
