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
				
				document.myform1.action="zfjhSave.action";
				document.myform1.submit();
			}
		}
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="zfjh.id" value="${zfjh.id}">
		<input type="hidden" name="zfjh.createTime" value="<fmt:formatDate type="both" value="${zfjh.createTime}" />">
		<input type="hidden" name="zfjh.updateTime" value="${zfjh.updateTime}">
		<input type="hidden" name="zfjh.createUserID" value="${zfjh.createUserID}">
		<input type="hidden" name="zfjh.updateUserID" value="${zfjh.updateUserID}">
		<input type="hidden" name="zfjh.deptId" value="${zfjh.deptId}">
		<input type="hidden" name="zfjh.delFlag" value="${zfjh.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">关联编号</th>
					<td width="35%"><input name="zfjh.proId" value="${zfjh.proId}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
					<th width="15%">年</th>
					<td width="35%"><input name="zfjh.zfjhYear" value="${zfjh.zfjhYear}" type="text" dataType="Require" msg="此项为必填" maxlength="10"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">周次</th>
					<td width="35%"><input name="zfjh.zfjhWeek" value="${zfjh.zfjhWeek}" type="text" dataType="Require" msg="此项为必填" maxlength="10"><font color="red">*</font></td>
				</tr>
				<tr>
					<th colspan="4"  style="text-align:center" >直管企业</th>
				</tr>
				<tr>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="zfjh.zgqyName" value="${zfjh.zgqyName}" type="text" maxlength="100"></td>
					<th width="15%">工作日</th>
					<td width="35%"><input name="zfjh.zgqyDay" value="${zfjh.zgqyDay}" type="text" maxlength="10"></td>
				</tr>
				<tr>
					<th width="15%">检查内容</th>
					<td width="35%" colspan="3" >
						<textarea name="zfjh.zgqyContent" rows="5" style="width:90%;height:100px">${zfjh.zgqyContent}</textarea>
					</td>
				</tr>
				<tr>
					<th colspan="4"  style="text-align:center" >行业领域</th>
				</tr>
				<tr>
					<th width="15%">行业名称</th>
					<td width="35%"><input name="zfjh.hylyName" value="${zfjh.hylyName}" type="text" maxlength="100"></td>
					<th width="15%">工作日</th>
					<td width="35%"><input name="zfjh.hylyDay" value="${zfjh.hylyDay}" type="text" maxlength="10"></td>
				</tr>
				<tr>
					<th width="15%">检查内容</th>
					<td width="35%" colspan="3" >
						<textarea name="zfjh.hylyContent" rows="5" style="width:90%;height:100px">${zfjh.hylyContent}</textarea>
					</td>
				</tr>
				<tr>
					<th colspan="4"  style="text-align:center" >综合监管</th>
				</tr>
				<tr>
					<th width="15%">部门名称</th>
					<td width="35%"><input name="zfjh.zhjgName" value="${zfjh.zhjgName}" type="text" maxlength="100"></td>
					<th width="15%">工作日</th>
					<td width="35%"><input name="zfjh.zhjgDay" value="${zfjh.zhjgDay}" type="text" maxlength="10"></td>
				</tr>
				<tr>
					<th width="15%">检查内容</th>
					<td width="35%" colspan="3" >
						<textarea name="zfjh.zhjgContent" rows="5" style="width:90%;height:100px">${zfjh.zhjgContent}</textarea>
					</td>
				</tr>
				<tr>
					<th colspan="4"  style="text-align:center" >县区政府</th>
				</tr>
				<tr>
					<th width="15%">县区名称</th>
					<td width="35%"><input name="zfjh.xqzfName" value="${zfjh.xqzfName}" type="text" maxlength="100"></td>
					<th width="15%">工作日</th>
					<td width="35%"><input name="zfjh.xqzfDay" value="${zfjh.xqzfDay}" type="text" maxlength="10"></td>
				</tr>
				<tr>
					<th width="15%">检查内容</th>
					<td width="35%" colspan="3" >
						<textarea name="zfjh.xqzfContent" rows="5" style="width:90%;height:100px">${zfjh.xqzfContent}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px"  style="text-align:center" >
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeLayer();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
