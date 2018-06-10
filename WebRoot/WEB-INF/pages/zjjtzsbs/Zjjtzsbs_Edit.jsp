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
				document.myform1.action="zjjtzsbsSave.action";
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
		<input type="hidden" name="zjjtzsbs.id" value="${zjjtzsbs.id}">
		<input type="hidden" name="zjjtzsbs.createTime" value="<fmt:formatDate type="both" value="${zjjtzsbs.createTime}" />">
		<input type="hidden" name="zjjtzsbs.updateTime" value="${zjjtzsbs.updateTime}">
		<input type="hidden" name="zjjtzsbs.createUserID" value="${zjjtzsbs.createUserID}">
		<input type="hidden" name="zjjtzsbs.updateUserID" value="${zjjtzsbs.updateUserID}">
		<input type="hidden" name="zjjtzsbs.deptId" value="${zjjtzsbs.deptId}">
		<input type="hidden" name="zjjtzsbs.delFlag" value="${zjjtzsbs.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">
						<s:select id="szzname" listKey="key" listValue="value"  theme="simple" list="#{'高铁新城':'高铁新城','度假区':'度假区','开发区':'开发区','北桥街道':'北桥街道'
						,'黄桥街道':'黄桥街道','太平街道':'太平街道','元和街道':'元和街道','阳澄湖镇':'阳澄湖镇','渭塘镇':'渭塘镇','黄埭镇':'黄埭镇','望亭镇':'望亭镇'}" name="zjjtzsbs.szzname" value="{zjjtzsbs.szzname}"/>
					</td>
					<th width="15%">使用单位</th>
					<td width="35%">
						<input id="qymc" name="zjjtzsbs.qymc" type="text" dataType="Require" msg="此项为必填"  maxlength="255" value="${zjjtzsbs.qymc}"><font style="color:red">*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">注册代码</th>
					<td width="35%"><input name="zjjtzsbs.zcdm" value="${zjjtzsbs.zcdm}" dataType="Require" msg="此项为必填" type="text" maxlength="255"><font style="color:red">*</font></td>
					<th width="15%">注册登记日期</th>
					<td width="35%">
						<input id="zcdjrq" name="zjjtzsbs.zcdjrq"  value='${zjjtzsbs.zcdjrq}' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
				</tr>
				<tr>
					<th width="15%">设备档案号</th>
					<td width="35%"><input name="zjjtzsbs.sbdah" value="${zjjtzsbs.sbdah}" dataType="Require" msg="此项为必填" type="text" maxlength="255"><font style="color:red">*</font></td>
					<th width="15%">设备类别</th>
					<td width="35%"><input name="zjjtzsbs.sblb" value="${zjjtzsbs.sblb}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">新设备类别</th>
					<td width="35%"><input name="zjjtzsbs.xsblb" value="${zjjtzsbs.xsblb}" type="text" maxlength="255"></td>
					<th width="15%">设备所在地点</th>
					<td width="35%"><input name="zjjtzsbs.szdd" value="${zjjtzsbs.szdd}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">设备状态</th>
					<td width="35%"><input name="zjjtzsbs.sbzt" value="${zjjtzsbs.sbzt}" type="text" maxlength="255"></td>
					<th width="15%">联系人</th>
					<td width="35%"><input name="zjjtzsbs.dwlxr" value="${zjjtzsbs.dwlxr}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">联系电话</th>
					<td width="35%"><input name="zjjtzsbs.dh" value="${zjjtzsbs.dh}" type="text" maxlength="255"></td>
					<th width="15%">使用单位地址</th>
					<td width="35%"><input name="zjjtzsbs.dwdz" value="${zjjtzsbs.dwdz}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">出厂日期</th>
					<td width="35%"><input id="ccrq" name="zjjtzsbs.ccrq"  value='${zjjtzsbs.ccrq}' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">出厂编号</th>
					<td width="35%"><input name="zjjtzsbs.ccbh" value="${zjjtzsbs.ccbh}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">检验日期</th>
					<td width="35%"><input id="jyrq" name="zjjtzsbs.jyrq"  value='${zjjtzsbs.jyrq}' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">下次检验日期</th>
					<td width="35%"><input id="xcjyrq" name="zjjtzsbs.xcjyrq"  value='${zjjtzsbs.xcjyrq}' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
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
