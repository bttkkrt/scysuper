<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:if test="flag=='add'">新增</s:if>
			<s:else>修改</s:else>记录</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="checkCategorySave.action";
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
			<input type="hidden" name="checkCategory.id"
				value="${checkCategory.id}">
			<input type="hidden" name="checkCategory.createTime"
				value="<fmt:formatDate type="both" value="${checkCategory.createTime}" />">
			<input type="hidden" name="checkCategory.updateTime"
				value="${checkCategory.updateTime}">
			<input type="hidden" name="checkCategory.createUserID"
				value="${checkCategory.createUserID}">
			<input type="hidden" name="checkCategory.updateUserID"
				value="${checkCategory.updateUserID}">
			<input type="hidden" name="checkCategory.deptId"
				value="${checkCategory.deptId}">
			<input type="hidden" name="checkCategory.delFlag"
				value="${checkCategory.delFlag}">

			<div class="submitdata">
				<table width="100%" border="0">
					<tr>
						<th width="15%">
							<font style="color: red;">*</font>栏目内容
						</th>
						<td colspan="3">
							<input name="checkCategory.content" type="text" value="${checkCategory.content}"
							 	dataType="Require" msg="此项为必填" maxlength="255" style="width: 90%">
						</td>
					</tr>
					<tr>
						<th width="15%">
							栏目排序号
						</th>
						<td width="35%">
							<input name="checkCategory.indexNum"
								value="${checkCategory.indexNum}" type="text" dataType="Integer"
								msg="此项必须填写整数" require="false" maxlength="10">
						</td>
						<th width="15%">
							是否启用
						</th>
						<td width="35%">
							启用
							<input type="radio" value="0"  <s:if test='checkCategory.isusing=="0" ||checkCategory.isusing==null||checkCategory.isusing==""'>checked</s:if> name="checkCategory.isusing">&nbsp;&nbsp;
							作废
							<input type="radio" value="1" <s:if test='checkCategory.isusing=="1"'>checked</s:if>  name="checkCategory.isusing">
						</td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td colspan="4" height="100px" style="text-align: center;">
							<s:if test="flag=='add'">
								<a href="#" class="easyui-linkbutton" onclick="save()"
									iconCls="icon-save">添加</a>&nbsp;
						</s:if>
							<s:else>
								<a href="#" class="easyui-linkbutton" onclick="save()"
									iconCls="icon-save">更新</a>&nbsp;
						</s:else>
							<a href="#" class="easyui-linkbutton" iconCls="icon-undo"
								onclick="document.myform1.reset()">重置</a>&nbsp;
							<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
								onclick="parent.close_win();">关闭</a>
						</td>
					</tr>
				</table>
				<div class="submitdata">
		</form>
		<%@include file="/WEB-INF/template/pagefoot.jsp"%>
	</body>
</html>
