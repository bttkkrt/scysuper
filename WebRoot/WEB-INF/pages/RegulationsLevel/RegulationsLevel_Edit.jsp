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
		
		function checkExist() {
			var flag = false;
			$.ajax({
				url : '${ctx}/jsp/RegulationsLevel/checkExist.action',
				type : 'post',
				async : false,
				data : {
					'regulationsLevel.levelName' : $("#levelName").val()
				},
				success : function(data) {
					if ('0' == data) {
						flag = true;
					}
				}
			});
			return flag;
		}
		//检查父节点是否变成子节点
		function checkToBeChild() {
			var flag = false;
			$.ajax({
				url : '${ctx}/jsp/RegulationsLevel/checkToBeChild.action',
				type : 'post',
				async : false,
				data : {
					'regulationsLevel.uplevelId' : $("#mfSuppliesTypeParentId").val(),
					'regulationsLevel.id' : $("#mfSuppliesTypeId").val()
				},
				success : function(data) {
					if ('0' == data) {
						flag = true;
					}
				}
			});
			return flag;
		}
		
		function save(){
			if(Validator.Validate(document.myform1,3)){
				//判断父节点不能为自己
				var mfSuppliesTypeParentId=$("#mfSuppliesTypeParentId").val();
				var mfSuppliesTypeId=$("#mfSuppliesTypeId").val();
				if( null!=mfSuppliesTypeId && $.trim(mfSuppliesTypeId)!='' && mfSuppliesTypeParentId == mfSuppliesTypeId  )
				{
					$.messager.alert("提示","上级级别不能为自己!");
					return;
				}
				
				//检测是否已存在此法规级别
				if ('add'=='${flag}'&&!checkExist()) {
					$.messager.alert('提示', '法规级别已存在，请重新输入！');
					$("#levelName").focus();
					return false;
				}
				
				//检测修改时是否已存在此法规级别
				if ('add' != '${flag}') {
					if ($("#levelName").val() != $("#tempTypename").val()) {
						if (!checkExist()) {
							$.messager.alert('提示', '该法规级别已存在，请重新输入！');
							return false;
						}
					}
				}
				//检测修改时是否父节点变为子节点
				if (!checkToBeChild()) {
					$.messager.alert('提示', '不能互为上下级关系!');
					return false;
				}
				
				document.myform1.action="regulationsLevelSave.action";
				document.myform1.submit();
			}
		}
		
		
		//页面展示办公用品类别树
		$(function(){
			getSuppliesTypeTree('mfSuppliesTypeParentId','citySel');
		});
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="regulationsLevel.id" value="${regulationsLevel.id}" id="mfSuppliesTypeId">
		<input type="hidden" name="regulationsLevel.createTime" value="<fmt:formatDate type="both" value="${regulationsLevel.createTime}" />">
		<input type="hidden" name="regulationsLevel.updateTime" value="${regulationsLevel.updateTime}">
		<input type="hidden" name="regulationsLevel.createUserID" value="${regulationsLevel.createUserID}">
		<input type="hidden" name="regulationsLevel.updateUserID" value="${regulationsLevel.updateUserID}">
		<input type="hidden" name="regulationsLevel.deptId" value="${regulationsLevel.deptId}">
		<input type="hidden" name="regulationsLevel.delFlag" value="${regulationsLevel.delFlag}">
		<input type="hidden" name="regulationsLevel.fullpath" value="${regulationsLevel.fullpath}">
		<input type="hidden" name="regulationsLevel.fullpath" id="tempTypename" value="${regulationsLevel.levelName}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">
						上级类别：
					</th>
					<td width="85%" style="text-align: left;" colspan="3">
						<input id="citySel" type="text" value="${regulationsLevel.uplevelId }"></input>
						<input type="hidden" name="regulationsLevel.uplevelId"
							id="mfSuppliesTypeParentId"
							value="${regulationsLevel.uplevelId }">
					</td>
				</tr>
				<tr>
				<th width="15%">级别名称：</th>
					<td width="85%"  colspan="3">
					<input id="levelName"  style="width: 54%" name="regulationsLevel.levelName" value="${regulationsLevel.levelName}" type="text" dataType="Require" msg="此项为必填" maxlength="100"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">排序号：</th>
					<td width="85%" style="text-align: left;" colspan="3">
						<input style="width: 54%" id="numbercard"
							name="regulationsLevel.numbercard"
							value="${regulationsLevel.numbercard}" type="text"
							datatype="n1-10" errormsg='此项请填入整数' ignore="ignore"><span style="color: red">*</span>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center">
						<center>
							<s:if test="flag=='add'">
								<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
								<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
							</s:if>
							<s:else>
								<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
							</s:else>						
							<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
						</center>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
