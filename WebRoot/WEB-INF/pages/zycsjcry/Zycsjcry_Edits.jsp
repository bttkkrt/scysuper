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
				
				document.myform1.action="zycsjcrySave.action";
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
		<input type="hidden" name="zycsjcry.id" value="${zycsjcry.id}">
		<input type="hidden" name="zycsjcry.createTime" value="<fmt:formatDate type="both" value="${zycsjcry.createTime}" />">
		<input type="hidden" name="zycsjcry.updateTime" value="${zycsjcry.updateTime}">
		<input type="hidden" name="zycsjcry.createUserID" value="${zycsjcry.createUserID}">
		<input type="hidden" name="zycsjcry.updateUserID" value="${zycsjcry.updateUserID}">
		<input type="hidden" name="zycsjcry.deptId" value="${zycsjcry.deptId}">
		<input type="hidden" name="zycsjcry.delFlag" value="${zycsjcry.delFlag}">
		<input type="hidden" name="zycsjcry.delFlags" value="${zycsjcry.delFlags}">
		<input type="hidden" name="zycsjcry.zycsid" value="${zycsjcry.zycsid}">
		
		<input type="hidden" name="zycsjcry.szzname" value="${zycsjcry.szzname}">
		<input type="hidden" name="zycsjcry.qymc" value="${zycsjcry.qymc}">
		<input type="hidden" name="zycsjcry.szzid" value="${zycsjcry.szzid}">
		<input type="hidden" name="zycsjcry.qyid" value="${zycsjcry.qyid}">
		<input type="hidden" name="zycsjcry.qylx" value="${zycsjcry.qylx}">
		<input type="hidden" name="zycsjcry.hyfl" value="${zycsjcry.hyfl}">
		<input type="hidden" name="zycsjcry.qyzclx" value="${zycsjcry.qyzclx}">
		
		<input name="zycsjcry.sfz" value="${zycsjcry.sfz}" type="hidden">
		<input name="zycsjcry.xb" value="${zycsjcry.xb}" type="hidden">
		<input name="zycsjcry.xm" value="${zycsjcry.xm}" type="hidden">
		<input name="zycsjcry.sgsj" value="${zycsjcry.sgsj}" type="hidden">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">身份证</th>
					<td width="35%">${zycsjcry.sfz}</td>
					<th width="15%">姓名</th>
					<td width="35%">${zycsjcry.xm}</td>
				</tr>	
				<tr>
					<th width="15%">体检类型</th>
					<td width="35%"><s:select cssStyle="width:100px" id="tjlx" listKey="key" listValue="value"  theme="simple" list="#{'岗前':'岗前','在岗':'在岗','离岗':'离岗','应急':'应急'}" name="zycsjcry.tjlx" value="{zycsjcry.tjlx}" dataType="Require" msg="此项为必填"/><font color="red">*</font></td>
					<th width="15%">体检时间</th>
					<td width="35%"><input name="zycsjcry.tjrq" value="${zycsjcry.tjrq}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require" msg="此项为必填"><font color="red">*</font></td>
				</tr>
				<tr>	
					<th width="15%">体检机构</th>
					<td width="35%"><input name="zycsjcry.tjjg" value="${zycsjcry.tjjg}" type="text" maxlength="255"></td>
					<th width="15%">体检结果</th>
					<td width="35%"><s:select cssStyle="width:100px" id="tjjguo" listKey="key" listValue="value"  theme="simple" list="#{'正常':'正常','职业相关异常':'职业相关异常','职业禁忌':'职业禁忌','疑似职业病人':'疑似职业病人'}" name="zycsjcry.tjjguo" value="{zycsjcry.tjjguo}" dataType="Require" msg="此项为必填"/><font color="red">*</font></td>
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