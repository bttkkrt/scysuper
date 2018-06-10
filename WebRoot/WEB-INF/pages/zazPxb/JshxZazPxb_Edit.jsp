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
				
				document.myform1.action="jshxZazPxbSave.action";
				document.myform1.submit();
			}
		}
		
		function saves(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="jshxZazPxbSaves.action?flag=add";
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
		<input type="hidden" name="jshxZazPxb.id" value="${jshxZazPxb.id}">
		<input type="hidden" name="jshxZazPxb.createTime" value="<fmt:formatDate type="both" value="${jshxZazPxb.createTime}" />">
		<input type="hidden" name="jshxZazPxb.updateTime" value="${jshxZazPxb.updateTime}">
		<input type="hidden" name="jshxZazPxb.createUserID" value="${jshxZazPxb.createUserID}">
		<input type="hidden" name="jshxZazPxb.updateUserID" value="${jshxZazPxb.updateUserID}">
		<input type="hidden" name="jshxZazPxb.deptId" value="${jshxZazPxb.deptId}">
		<input type="hidden" name="jshxZazPxb.delFlag" value="${jshxZazPxb.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">
						<s:select id="szzname" listKey="key" listValue="value"  theme="simple" list="#{'高铁新城':'高铁新城','度假区':'度假区','开发区':'开发区','北桥街道':'北桥街道'
						,'黄桥街道':'黄桥街道','太平街道':'太平街道','元和街道':'元和街道','阳澄湖镇':'阳澄湖镇','渭塘镇':'渭塘镇','黄埭镇':'黄埭镇','望亭镇':'望亭镇'}" name="jshxZazPxb.szzname" value="{jshxZazPxb.szzname}"/>
					</td>
					<th width="15%">企业名称</th>
					<td width="35%">
						<input id="qymc" name="jshxZazPxb.qymc"　type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${jshxZazPxb.qymc}"><font style="color:red">*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="jshxZazPxb.personName" value="${jshxZazPxb.personName}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="15%">性别</th>
					<td width="35%">
						<s:select listKey="key" listValue="value"  theme="simple" list="#{'男':'男','女':'女'}" name="jshxZazPxb.sex" value="{jshxZazPxb.sex}"/>
					</td>
				</tr>
				<tr>
					<th width="15%">职务</th>
					<td width="35%">
						<s:select listKey="key" listValue="value"  theme="simple" list="#{'法人':'法人','主要负责人':'主要负责人','安全管理员':'安全管理员','职业卫生管理员':'职业卫生管理员'}" name="jshxZazPxb.zw" value="{jshxZazPxb.zw}"/>
					</td>
					<th width="15%">文化</th>
					<td width="35%">
						<s:select listKey="key" listValue="value"  theme="simple" list="#{'博士':'博士','硕士':'硕士','本科':'本科','大专':'大专','中专':'中专','高中':'高中','其它':'其它'}" name="jshxZazPxb.xl" value="{jshxZazPxb.xl}"/>
					</td>
				</tr>
				<tr>
					<th width="15%">电话</th>
					<td width="35%"><input name="jshxZazPxb.lxfs" value="${jshxZazPxb.lxfs}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="15%">身份证</th>
					<td width="35%"><input name="jshxZazPxb.sfz" value="${jshxZazPxb.sfz}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">地址</th>
					<td width="85%" colspan="3"><input name="jshxZazPxb.address" value="${jshxZazPxb.address}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					
				</tr>
				<tr>
					<th width="15%">初培时间</th>
					<td width="35%"><input name="jshxZazPxb.cpsj" value="${jshxZazPxb.cpsj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="15%">资格证号</th>
					<td width="35%"><input name="jshxZazPxb.pxzh" value="${jshxZazPxb.pxzh}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">成绩</th>
					<td width="35%"><input name="jshxZazPxb.kscj" value="${jshxZazPxb.kscj}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="15%">危化品企业类型</th>
					<td width="35%">
						<s:select listKey="key" listValue="value"  theme="simple" list="#{'化工生产企业':'化工生产企业','危险化学品经营企业':'危险化学品经营企业','烟花爆竹经营企业':'烟花爆竹经营企业','其它工贸企业':'其它工贸企业'}" name="jshxZazPxb.whpqylx" value="{jshxZazPxb.whpqylx}"/>
					</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3">
						<textarea style="width:600px;" name="jshxZazPxb.bz" value="${jshxZazPxb.bz}">${jshxZazPxb.bz}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="saves()" iconCls="icon-save">继续添加</a>&nbsp;
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
