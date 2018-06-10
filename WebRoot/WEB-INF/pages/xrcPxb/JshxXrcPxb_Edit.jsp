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
				var facpxnr = document.getElementById('facpxnr').value;
				var cjpxnr = document.getElementById('cjpxnr').value;
				var bzpxnr = document.getElementById('bzpxnr').value;
				if(facpxnr.length < 30)
				{
					alert("厂级培训内容不少于30个字!");
					return false;
				}
				else if(cjpxnr.length < 30)
				{
					alert("车间培训内容不少于30个字!");
					return false;
				}
				else if(bzpxnr.length < 30)
				{
					alert("班组培训内容不少于30个字!");
					return false;
				}
				else
				{
					document.myform1.action="jshxXrcPxbSave.action";
					document.myform1.submit();
				}
			}
		}
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="jshxXrcPxb.id" value="${jshxXrcPxb.id}">
		<input type="hidden" name="jshxXrcPxb.createTime" value="<fmt:formatDate type="both" value="${jshxXrcPxb.createTime}" />">
		<input type="hidden" name="jshxXrcPxb.updateTime" value="${jshxXrcPxb.updateTime}">
		<input type="hidden" name="jshxXrcPxb.createUserID" value="${jshxXrcPxb.createUserID}">
		<input type="hidden" name="jshxXrcPxb.updateUserID" value="${jshxXrcPxb.updateUserID}">
		<input type="hidden" name="jshxXrcPxb.deptId" value="${jshxXrcPxb.deptId}">
		<input type="hidden" name="jshxXrcPxb.delFlag" value="${jshxXrcPxb.delFlag}">
		<input type="hidden" name="jshxXrcPxb.szzname" value="${jshxXrcPxb.szzname}">
		<input type="hidden" name="jshxXrcPxb.qymc" value="${jshxXrcPxb.qymc}">
		<input type="hidden" name="jshxXrcPxb.szzid" value="${jshxXrcPxb.szzid}">
		<input type="hidden" name="jshxXrcPxb.qyid" value="${jshxXrcPxb.qyid}">
		<input type="hidden" name="jshxXrcPxb.qylx" value="${jshxXrcPxb.qylx}">
		<input type="hidden" name="jshxXrcPxb.hyfl" value="${jshxXrcPxb.hyfl}">
		<input type="hidden" name="jshxXrcPxb.qygm" value="${jshxXrcPxb.qygm}">
		<input type="hidden" name="jshxXrcPxb.qyzclx" value="${jshxXrcPxb.qyzclx}">
		<input type="hidden" name="jshxXrcPxb.ifwhpqylx" value="${jshxXrcPxb.ifwhpqylx}">
		<input type="hidden" name="jshxXrcPxb.ifzywhqylx" value="${jshxXrcPxb.ifzywhqylx}">
		<input type="hidden" name="jshxXrcPxb.ifyhbzjyqy" value="${jshxXrcPxb.ifyhbzjyqy}">
		<input type="hidden" name="jshxXrcPxb.szc" value="${jshxXrcPxb.szc}">
		<input type="hidden" name="jshxXrcPxb.szcname" value="${jshxXrcPxb.szcname}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="10%">姓名</th>
					<td width="23%"><input name="jshxXrcPxb.personName" value="${jshxXrcPxb.personName}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="10%">性别</th>
					<td width="23%"><cus:SelectOneTag property="jshxXrcPxb.sex" codeName="性别" value="${jshxXrcPxb.sex}" dataType="Require" msg="此项为必填"/><font style="color:red">*</font></td>
					<th width="10%">文化程度</th>
					<td width="24%"><cus:SelectOneTag property="jshxXrcPxb.whcd" defaultText='请选择' codeName="学历" value="${jshxXrcPxb.whcd}" dataType="Require" msg="此项为必填"/><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="10%">从事岗位</th>
					<td width="23%"><input name="jshxXrcPxb.csgw" value="${jshxXrcPxb.csgw}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="10%">培训学时</th>
					<td width="23%"><input name="jshxXrcPxb.pxxs" value="${jshxXrcPxb.pxxs}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="100%" colspan="6" style="text-align:center">厂级培训内容</th>
				</tr>
				<tr>
					<th width="10%">培训时间</th>
					<td width="23%"><input name="jshxXrcPxb.facpxsj" value="<fmt:formatDate type='date' value='${jshxXrcPxb.facpxsj}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="10%">培训授课人</th>
					<td width="23%"><input name="jshxXrcPxb.facpxskr" value="${jshxXrcPxb.facpxskr}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="10%">培训考核成绩</th>
					<td width="24%"><input name="jshxXrcPxb.facpxkhcj" value="${jshxXrcPxb.facpxkhcj}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="10%">培训内容</th>
					<td width="90%" colspan="5">
						<textarea id="facpxnr" name="jshxXrcPxb.facpxnr" style="width:100%;height:100px">${jshxXrcPxb.facpxnr}</textarea>
					</td>
				</tr>
				
				<tr>
					<th width="100%" colspan="6" style="text-align:center">车间培训内容</th>
				</tr>
				<tr>	
					<th width="10%">培训时间</th>
					<td width="23%"><input name="jshxXrcPxb.cjpxsj" value="<fmt:formatDate type='date' value='${jshxXrcPxb.cjpxsj}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="10%">培训授课人</th>
					<td width="23%"><input name="jshxXrcPxb.cjpxskr" value="${jshxXrcPxb.cjpxskr}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="10%">培训考核成绩</th>
					<td width="24%"><input name="jshxXrcPxb.cjpxkhcj" value="${jshxXrcPxb.cjpxkhcj}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>	
					<th width="10%">培训内容</th>
					<td width="90%" colspan="5">
						<textarea id="cjpxnr" name="jshxXrcPxb.cjpxnr" style="width:100%;height:100px">${jshxXrcPxb.cjpxnr}</textarea>
					</td>
				</tr>
				<tr>
					<th width="100%" colspan="6" style="text-align:center">班组培训内容</th>
				</tr>
					<th width="10%">培训时间</th>
					<td width="23%"><input name="jshxXrcPxb.bzpxsj" value="<fmt:formatDate type='date' value='${jshxXrcPxb.bzpxsj}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="10%">培训授课人</th>
					<td width="23%"><input name="jshxXrcPxb.bzpxskr" value="${jshxXrcPxb.bzpxskr}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="10%">培训考核成绩</th>
					<td width="24%"><input name="jshxXrcPxb.bzpxkhcj" value="${jshxXrcPxb.bzpxkhcj}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="10%">培训内容</th>
					<td width="90%" colspan="5">
						<textarea id="bzpxnr" name="jshxXrcPxb.bzpxnr" style="width:100%;height:100px">${jshxXrcPxb.bzpxnr}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="6" height="100px" style="text-align:center">
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
