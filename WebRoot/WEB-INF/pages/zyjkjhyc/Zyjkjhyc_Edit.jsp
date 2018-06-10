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
				
				document.myform1.action="zyjkjhycSave.action";
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
		<input type="hidden" name="zyjkjhyc.id" value="${zyjkjhyc.id}">
		<input type="hidden" name="zyjkjhyc.createTime" value="<fmt:formatDate type="both" value="${zyjkjhyc.createTime}" />">
		<input type="hidden" name="zyjkjhyc.updateTime" value="${zyjkjhyc.updateTime}">
		<input type="hidden" name="zyjkjhyc.createUserID" value="${zyjkjhyc.createUserID}">
		<input type="hidden" name="zyjkjhyc.updateUserID" value="${zyjkjhyc.updateUserID}">
		<input type="hidden" name="zyjkjhyc.deptId" value="${zyjkjhyc.deptId}">
		<input type="hidden" name="zyjkjhyc.delFlag" value="${zyjkjhyc.delFlag}">
		
		<input type="hidden" name="zyjkjhyc.sfz" value="${zyjkjhyc.sfz}">
		<input type="hidden" name="zyjkjhyc.xm" value="${zyjkjhyc.xm}">
		
		<input type="hidden" name="zyjkjhyc.proid" value="${zyjkjhyc.proid}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">体检机构</th>
					<td width="35%"><input name="zyjkjhyc.tjjg" value="${zyjkjhyc.tjjg}" type="text" maxlength="255"></td>
					<th width="15%">体检日期</th>
					<td width="35%"><input name="zyjkjhyc.tjrq" value="${zyjkjhyc.tjrq}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require" msg="此项为必填"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">复查情况</th>
					<td width="35%"><input name="zyjkjhyc.fcqk" value="${zyjkjhyc.fcqk}" type="text" maxlength="255"></td>
					<th width="15%">处理情况</th>
					<td width="35%"><input name="zyjkjhyc.clqk" value="${zyjkjhyc.clqk}" type="text" maxlength="255"></td>
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
