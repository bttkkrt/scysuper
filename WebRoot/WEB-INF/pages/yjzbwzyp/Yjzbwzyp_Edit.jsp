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
				
				document.myform1.action="yjzbwzypSave.action";
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
		<input type="hidden" name="yjzbwzyp.id" value="${yjzbwzyp.id}">
		<input type="hidden" name="yjzbwzyp.createTime" value="<fmt:formatDate type="both" value="${yjzbwzyp.createTime}" />">
		<input type="hidden" name="yjzbwzyp.updateTime" value="${yjzbwzyp.updateTime}">
		<input type="hidden" name="yjzbwzyp.createUserID" value="${yjzbwzyp.createUserID}">
		<input type="hidden" name="yjzbwzyp.updateUserID" value="${yjzbwzyp.updateUserID}">
		<input type="hidden" name="yjzbwzyp.deptId" value="${yjzbwzyp.deptId}">
		<input type="hidden" name="yjzbwzyp.delFlag" value="${yjzbwzyp.delFlag}">
		
		<input type="hidden" name="yjzbwzyp.szz" value="${yjzbwzyp.szz}">
		<input type="hidden" name="yjzbwzyp.qymc"  value="${yjzbwzyp.qymc}">
		<input type="hidden" name="yjzbwzyp.comid"  value="${yjzbwzyp.comid}">
			<input type="hidden" name="yjzbwzyp.qylx" value="${yjzbwzyp.qylx}">
		<input type="hidden" name="yjzbwzyp.hyfl" value="${yjzbwzyp.hyfl}">
		<input type="hidden" name="yjzbwzyp.qygm" value="${yjzbwzyp.qygm}">
		<input type="hidden" name="yjzbwzyp.qyzclx" value="${yjzbwzyp.qyzclx}">
		<input type="hidden" name="yjzbwzyp.ifwhpqylx" value="${yjzbwzyp.ifwhpqylx}">
		<input type="hidden" name="yjzbwzyp.ifzywhqylx" value="${yjzbwzyp.ifzywhqylx}">
		<input type="hidden" name="yjzbwzyp.ifyhbzjyqy" value="${yjzbwzyp.ifyhbzjyqy}">
		
		<input type="hidden" name="yjzbwzyp.szc" value="${yjzbwzyp.szc}">
		<input type="hidden" name="yjzbwzyp.szcname" value="${yjzbwzyp.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">应急类型</th>
					<td width="35%"><cus:SelectOneTag property="yjzbwzyp.yjlx" defaultText='请选择' codeName="应急类型" value="${yjzbwzyp.yjlx}" dataType="Require" msg="此项为必选" /><font style="color:red">*</font></td>
					<th width="15%">装备名称</th>
					<td width="35%"><input name="yjzbwzyp.zbmc" value="${yjzbwzyp.zbmc}" size=35 type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">储存地点</th>
					<td width="35%" colspan=3><input name="yjzbwzyp.ccdd" value="${yjzbwzyp.ccdd}"  size=55 type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">储备数量</th>
					<td width="35%"><input name="yjzbwzyp.cbsl" value="${yjzbwzyp.cbsl}"size=35 type="text" maxlength="255"></td>
					<th width="15%">主要用途</th>
					<td width="35%"><input name="yjzbwzyp.zyyt" value="${yjzbwzyp.zyyt}"size=35 type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">保管人</th>
					<td width="35%"><input name="yjzbwzyp.bgr" value="${yjzbwzyp.bgr}"size=35 type="text" maxlength="255"></td>
					<th width="15%">联系电话</th>
					<td width="35%"><input name="yjzbwzyp.lxdh" value="${yjzbwzyp.lxdh}" size=35 type="text" maxlength="255"></td>
				</tr>
					<tr>
					<th width="15%">填报人</th>
					<td width="35%"><input name="yjzbwzyp.tbr" value="${yjzbwzyp.tbr}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font style="color:red">*</font></td>
				
					<th width="15%">填报时间</th>
				<td width="35%"><input name="yjzbwzyp.tbsj" value="<s:property value="yjzbwzyp.tbsj"/>" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea name="yjzbwzyp.remark" style="width:100%;height:120px">${yjzbwzyp.remark}</textarea></td>
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
