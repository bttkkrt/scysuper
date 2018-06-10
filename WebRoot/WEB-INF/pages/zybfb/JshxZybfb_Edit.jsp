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
				
				document.myform1.action="jshxZybfbSave.action";
				document.myform1.submit();
			}
		}
		
		 function validate(event,obj)
        {
        	event = window.event||event; 
        	if(event.keyCode == 37 | event.keyCode == 39){ 
           	 	return; 
        	} 
        	obj.value = obj.value.replace(/[^\d]/g,""); 
        	if(obj.value.length >= 2 && obj.value.substring(0,1) == "0")
        	{
        		obj.value = obj.value.substring(1,obj.value.length);
        	}
        }
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="jshxZybfb.id" value="${jshxZybfb.id}">
		<input type="hidden" name="jshxZybfb.createTime" value="<fmt:formatDate type="both" value="${jshxZybfb.createTime}" />">
		<input type="hidden" name="jshxZybfb.updateTime" value="${jshxZybfb.updateTime}">
		<input type="hidden" name="jshxZybfb.createUserID" value="${jshxZybfb.createUserID}">
		<input type="hidden" name="jshxZybfb.updateUserID" value="${jshxZybfb.updateUserID}">
		<input type="hidden" name="jshxZybfb.deptId" value="${jshxZybfb.deptId}">
		<input type="hidden" name="jshxZybfb.delFlag" value="${jshxZybfb.delFlag}">
		
		<input type="hidden" name="jshxZybfb.szzname" value="${jshxZybfb.szzname}">
		<input type="hidden" name="jshxZybfb.qymc" value="${jshxZybfb.qymc}">
		<input type="hidden" name="jshxZybfb.szzid" value="${jshxZybfb.szzid}">
		<input type="hidden" name="jshxZybfb.qyid" value="${jshxZybfb.qyid}">
		<input type="hidden" name="jshxZybfb.qylx" value="${jshxZybfb.qylx}">
		<input type="hidden" name="jshxZybfb.hyfl" value="${jshxZybfb.hyfl}">
		<input type="hidden" name="jshxZybfb.qygm" value="${jshxZybfb.qygm}">
		<input type="hidden" name="jshxZybfb.qyzclx" value="${jshxZybfb.qyzclx}">
		<input type="hidden" name="jshxZybfb.ifwhpqylx" value="${jshxZybfb.ifwhpqylx}">
		<input type="hidden" name="jshxZybfb.ifzywhqylx" value="${jshxZybfb.ifzywhqylx}">
		<input type="hidden" name="jshxZybfb.ifyhbzjyqy" value="${jshxZybfb.ifyhbzjyqy}">
		
		<input type="hidden" name="jshxZybfb.szc" value="${jshxZybfb.szc}">
		<input type="hidden" name="jshxZybfb.szcname" value="${jshxZybfb.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">作业场所名称</th>
					<td width="35%"><input name="jshxZybfb.workName" value="${jshxZybfb.workName}" type="text" maxlength="255"></td>
					<th width="15%">职业病危害因素名称</th>
					<td width="35%"><input name="jshxZybfb.zybName" value="${jshxZybfb.zybName}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">接触人数(可重复)</th>
					<td width="35%"><input name="jshxZybfb.repeatCount" value="${jshxZybfb.repeatCount}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
					<th width="15%">接触人数(不可重复)</th>
					<td width="35%"><input name="jshxZybfb.noRepeatCount" value="${jshxZybfb.noRepeatCount}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">女工数</th>
					<td width="35%"><input name="jshxZybfb.womanCount" value="${jshxZybfb.womanCount}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
					<th width="15%">填报人</th>
					<td width="35%"><input name="jshxZybfb.tbr" value="${jshxZybfb.tbr}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">联系电话</th>
					<td width="35%"><input name="jshxZybfb.telephone" value="${jshxZybfb.telephone}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px"   style="text-align:center;">
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
