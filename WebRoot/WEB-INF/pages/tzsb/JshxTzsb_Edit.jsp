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
				
				document.myform1.action="jshxTzsbSave.action";
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
		<input type="hidden" name="jshxTzsb.id" value="${jshxTzsb.id}">
		<input type="hidden" name="jshxTzsb.createTime" value="<fmt:formatDate type="both" value="${jshxTzsb.createTime}" />">
		<input type="hidden" name="jshxTzsb.updateTime" value="${jshxTzsb.updateTime}">
		<input type="hidden" name="jshxTzsb.createUserID" value="${jshxTzsb.createUserID}">
		<input type="hidden" name="jshxTzsb.updateUserID" value="${jshxTzsb.updateUserID}">
		<input type="hidden" name="jshxTzsb.deptId" value="${jshxTzsb.deptId}">
		<input type="hidden" name="jshxTzsb.delFlag" value="${jshxTzsb.delFlag}">
		<input type="hidden" name="jshxTzsb.szzname" value="${jshxTzsb.szzname}">
		<input type="hidden" name="jshxTzsb.qymc" value="${jshxTzsb.qymc}">
		<input type="hidden" name="jshxTzsb.szzid" value="${jshxTzsb.szzid}">
		<input type="hidden" name="jshxTzsb.qyid" value="${jshxTzsb.qyid}">
		<input type="hidden" name="jshxTzsb.qylx" value="${jshxTzsb.qylx}">
		<input type="hidden" name="jshxTzsb.hyfl" value="${jshxTzsb.hyfl}">
		<input type="hidden" name="jshxTzsb.qygm" value="${jshxTzsb.qygm}">
		<input type="hidden" name="jshxTzsb.qyzclx" value="${jshxTzsb.qyzclx}">
		<input type="hidden" name="jshxTzsb.ifwhpqylx" value="${jshxTzsb.ifwhpqylx}">
		<input type="hidden" name="jshxTzsb.ifzywhqylx" value="${jshxTzsb.ifzywhqylx}">
		<input type="hidden" name="jshxTzsb.ifyhbzjyqy" value="${jshxTzsb.ifyhbzjyqy}">
		<input type="hidden" name="jshxTzsb.szc" value="${jshxTzsb.szc}">
		<input type="hidden" name="jshxTzsb.szcname" value="${jshxTzsb.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">设备名称</th>
					<td width="85%" colspan="3"><input name="jshxTzsb.sbmc" value="${jshxTzsb.sbmc}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">操作人员</th>
					<td width="35%"><input name="jshxTzsb.operStaff" value="${jshxTzsb.operStaff}" type="text" maxlength="255"></td>
					<th width="15%">操作人员持证情况</th>
					<td width="35%"><input name="jshxTzsb.cerOfOperStaff" value="${jshxTzsb.cerOfOperStaff}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">设备种类</th>
					<td width="35%"><cus:SelectOneTag property="jshxTzsb.sblx" defaultText='请选择' codeName="特种设备类型" value="${jshxTzsb.sblx}"/></td>
					<th width="15%">设备位号</th>
					<td width="35%"><input name="jshxTzsb.sbwh" value="${jshxTzsb.sbwh}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">使用证编号</th>
					<td width="35%"><input name="jshxTzsb.syzbh" value="${jshxTzsb.syzbh}" type="text" maxlength="255"></td>
					<th width="15%">规格型号</th>
					<td width="35%"><input name="jshxTzsb.ggxh" value="${jshxTzsb.ggxh}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">制造单位名称</th>
					<td width="35%"><input name="jshxTzsb.zzdwmc" value="${jshxTzsb.zzdwmc}" type="text" maxlength="255"></td>
					<th width="15%">使用状态</th>
					<td width="35%"><cus:SelectOneTag property="jshxTzsb.syzt" defaultText='请选择' codeName="特种设备使用状态" value="${jshxTzsb.syzt}"/></td>
				</tr>
				<tr>
					<th width="15%">检测单位</th>
					<td width="35%"><input name="jshxTzsb.jcdw" value="${jshxTzsb.jcdw}" type="text" maxlength="255"></td>
					<th width="15%">设备使用安装地点</th>
					<td width="35%"><input name="jshxTzsb.sbazdd" value="${jshxTzsb.sbazdd}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">上次检测日期</th>
					<td width="35%"><input name="jshxTzsb.scjcrq" value="<fmt:formatDate type='date' value='${jshxTzsb.scjcrq}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">下次检验日期</th>
					<td width="35%"><input name="jshxTzsb.xcjcrq" value="<fmt:formatDate type='date' value='${jshxTzsb.xcjcrq}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">安全责任人</th>
					<td width="35%"><input name="jshxTzsb.aqzrr" value="${jshxTzsb.aqzrr}" type="text" maxlength="255"></td>
					<th width="15%">安全附件</th>
					<td width="35%"><input name="jshxTzsb.aqfj" value="${jshxTzsb.aqfj}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3">
						<textarea name="jshxTzsb.bz" style="width:100%;height:100px">${jshxTzsb.bz}</textarea>
					</td>
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
