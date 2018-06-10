<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:if test="flag=='add'">新增</s:if> <s:else>修改</s:else>记录</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="checkContentSave.action";
				document.myform1.submit();
			}
		}
		
		
		 function get_checkCategoryTree(){
			var str = showModalDialog('${ctx}/jsp/checkCategory/categoryTree.action?func=returnStr&d=' + Date(), window, 'dialogWidth:650px;dialogHeight:500px;scroll:no;center:yes');
			if(str!=null&&str.length>0)
			{
				$("#categoryId").val(str[0]);
				$("#categoryName").val(str[1]);
			}		
	    }
	</script>

	</head>
	<body>
		<%@include file="/WEB-INF/template/content_title.jsp"%>
		<form name="myform1" method="post" enctype="multipart/form-data">
			<s:token />
			<input type="hidden" name="flag" value="${flag}">
			<input type="hidden" name="checkContent.id"
				value="${checkContent.id}">
			<input type="hidden" name="checkContent.createTime"
				value="<fmt:formatDate type="both" value="${checkContent.createTime}" />">
			<input type="hidden" name="checkContent.updateTime"
				value="${checkContent.updateTime}">
			<input type="hidden" name="checkContent.createUserID"
				value="${checkContent.createUserID}">
			<input type="hidden" name="checkContent.updateUserID"
				value="${checkContent.updateUserID}">
			<input type="hidden" name="checkContent.deptId"
				value="${checkContent.deptId}">
			<input type="hidden" name="checkContent.delFlag"
				value="${checkContent.delFlag}">

			<div class="submitdata">
				<table width="100%" border="0">
					<tr>
						<th width="15%">
							<font style="color: red;">*</font>检查类别
						</th>
						<td colspan="3">
							<input type='text' id="categoryName" 
								dataType="Require" msg="此项为必填" name="categoryName"
								value="${checkContent.category.content }" style="width: 75%">
							<input type="hidden" id="categoryId"
								name="checkContent.category.id"
								value="${checkContent.category.id }">
							<img src="${ctx}/webResources/images/SmallIcon/forum.gif"
								border="0" onclick="get_checkCategoryTree()">
						</td>
					</tr>
					<tr>
						<th width="15%">
							<font style="color: red;">*</font>检查内容
						</th>
						<td colspan="3">
							<input name="checkContent.content"
								value="${checkContent.content}" type="text" dataType="Require"
								msg="此项为必填" maxlength="255" style="width: 75%">
						</td>
					</tr>
					<tr>
						<th width="15%">
							排序序号
						</th>
						<td width="35%">
							<input name="checkContent.indexNum"
								value="${checkContent.indexNum}" type="text" dataType="Integer"
								msg="此项必须填写整数" require="false" maxlength="10">
						</td>
						<th width="15%">
							是否启用
						</th>
						<td width="35%">
							启用
							<input type="radio" value="0"  <s:if test='checkContent.isusing=="0" ||checkContent.isusing==null||checkContent.isusing==""'>checked</s:if> name="checkContent.isusing">&nbsp;&nbsp;
							作废
							<input type="radio" value="1" <s:if test='checkContent.isusing=="1"'>checked</s:if>  name="checkContent.isusing">
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
