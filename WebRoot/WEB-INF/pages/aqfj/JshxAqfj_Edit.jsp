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
				
				document.myform1.action="jshxAqfjSave.action";
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
		<input type="hidden" name="jshxAqfj.id" value="${jshxAqfj.id}">
		<input type="hidden" name="jshxAqfj.createTime" value="<fmt:formatDate type="both" value="${jshxAqfj.createTime}" />">
		<input type="hidden" name="jshxAqfj.updateTime" value="${jshxAqfj.updateTime}">
		<input type="hidden" name="jshxAqfj.createUserID" value="${jshxAqfj.createUserID}">
		<input type="hidden" name="jshxAqfj.updateUserID" value="${jshxAqfj.updateUserID}">
		<input type="hidden" name="jshxAqfj.deptId" value="${jshxAqfj.deptId}">
		<input type="hidden" name="jshxAqfj.delFlag" value="${jshxAqfj.delFlag}">
		<input type="hidden" name="jshxAqfj.szzname" value="${jshxAqfj.szzname}">
		<input type="hidden" name="jshxAqfj.qymc" value="${jshxAqfj.qymc}">
		<input type="hidden" name="jshxAqfj.szzid" value="${jshxAqfj.szzid}">
		<input type="hidden" name="jshxAqfj.qyid" value="${jshxAqfj.qyid}">
		<input type="hidden" name="jshxAqfj.qylx" value="${jshxAqfj.qylx}">
		<input type="hidden" name="jshxAqfj.hyfl" value="${jshxAqfj.hyfl}">
		<input type="hidden" name="jshxAqfj.qygm" value="${jshxAqfj.qygm}">
		<input type="hidden" name="jshxAqfj.qyzclx" value="${jshxAqfj.qyzclx}">
		<input type="hidden" name="jshxAqfj.ifwhpqylx" value="${jshxAqfj.ifwhpqylx}">
		<input type="hidden" name="jshxAqfj.ifzywhqylx" value="${jshxAqfj.ifzywhqylx}">
		<input type="hidden" name="jshxAqfj.ifyhbzjyqy" value="${jshxAqfj.ifyhbzjyqy}">
		<input type="hidden" name="jshxAqfj.szc" value="${jshxAqfj.szc}">
		<input type="hidden" name="jshxAqfj.szcname" value="${jshxAqfj.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">安全附件名称</th>
					<td width="35%"><input name="jshxAqfj.aqfjmc" value="${jshxAqfj.aqfjmc}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="15%">型号</th>
					<td width="35%"><input name="jshxAqfj.xh" value="${jshxAqfj.xh}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">所属设备名称</th>
					<td width="35%"><input name="jshxAqfj.sssbmc" value="${jshxAqfj.sssbmc}" type="text" maxlength="255"></td>
					<th width="15%">所属特种设备出厂编号</th>
					<td width="35%"><input name="jshxAqfj.sstzsbccbh" value="${jshxAqfj.sstzsbccbh}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">投用日期</th>
					<td width="35%"><input name="jshxAqfj.tyrq" value="<fmt:formatDate type='date' value='${jshxAqfj.tyrq}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">制造单位名称</th>
					<td width="35%"><input name="jshxAqfj.zzdwmc" value="${jshxAqfj.zzdwmc}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">使用部门</th>
					<td width="35%"><input name="jshxAqfj.sybm" value="${jshxAqfj.sybm}" type="text" maxlength="255"></td>
					<th width="15%">使用状态</th>
					<td width="35%"><input name="jshxAqfj.syzt" value="${jshxAqfj.syzt}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">设备安装地点</th>
					<td width="85%" colspan="3"><input name="jshxAqfj.sbazdd" value="${jshxAqfj.sbazdd}" type="text" maxlength="255" style="width:85%"></td>
				</tr>
				<tr>
					<th width="15%">上次检测日期</th>
					<td width="35%"><input name="jshxAqfj.scjcsj" value="<fmt:formatDate type='date' value='${jshxAqfj.scjcsj}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">下次检测日期</th>
					<td width="35%"><input name="jshxAqfj.xcjcsj" value="<fmt:formatDate type='date' value='${jshxAqfj.xcjcsj}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">安全责任人</th>
					<td width="35%"><input name="jshxAqfj.aqzrr" value="${jshxAqfj.aqzrr}" type="text" maxlength="255"></td>
					<th width="15%">检验报告编号</th>
					<td width="35%"><input name="jshxAqfj.jybgbh" value="${jshxAqfj.jybgbh}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">检验情况记录</th>
					<td width="85%" colspan="3">
						<textarea name="jshxAqfj.jyqkjl" style="width:100%;height:100px">${jshxAqfj.jyqkjl}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">检验结论</th>
					<td width="35%"><input name="jshxAqfj.jyjl" value="${jshxAqfj.jyjl}" type="text" maxlength="255"></td>
					<th width="15%">检测单位</th>
					<td width="35%"><input name="jshxAqfj.jcdw" value="${jshxAqfj.jcdw}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">整改情况</th>
					<td width="85%" colspan="3">
						<textarea name="jshxAqfj.zgqk" style="width:100%;height:100px">${jshxAqfj.zgqk}</textarea>
					</td>
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
