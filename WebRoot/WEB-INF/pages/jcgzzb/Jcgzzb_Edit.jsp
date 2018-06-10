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
				
				document.myform1.action="jcgzzbSave.action";
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
		<input type="hidden" name="jcgzzb.id" value="${jcgzzb.id}">
		<input type="hidden" name="jcgzzb.createTime" value="<fmt:formatDate type="both" value="${jcgzzb.createTime}" />">
		<input type="hidden" name="jcgzzb.updateTime" value="${jcgzzb.updateTime}">
		<input type="hidden" name="jcgzzb.createUserID" value="${jcgzzb.createUserID}">
		<input type="hidden" name="jcgzzb.updateUserID" value="${jcgzzb.updateUserID}">
		<input type="hidden" name="jcgzzb.deptId" value="${jcgzzb.deptId}">
		<input type="hidden" name="jcgzzb.delFlag" value="${jcgzzb.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="20%">填报单位</th>
					<td width="20%"><input name="jcgzzb.szzname" value="${jcgzzb.szzname}" type="text" maxlength="255"></td>
					<th width="20%">统计日期</th>
					<td width="40%" colspan="2">
						<input name="jcgzzb.tjkssj" value="<fmt:formatDate type='date' value='${jcgzzb.tjkssj}' />" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
						至
						<input name="jcgzzb.tjjssj" value="<fmt:formatDate type='date' value='${jcgzzb.tjjssj}' />" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font color="red">*</font>
					</td>
				</tr>
				<tr>
					<th width="20%">计划执法检查</th>
					<td width="40%" colspan="2"><input name="jcgzzb.jhzfjccs" value="${jcgzzb.jhzfjccs}" type="text" maxlength="255" onKeyUp="validate(event,this)">次</td>
					<td width="40%" colspan="2"><input name="jcgzzb.jhzfjcjs" value="${jcgzzb.jhzfjcjs}" type="text" maxlength="255" onKeyUp="validate(event,this)">家</td>
				</tr>
				<tr>
					<th width="20%" rowspan="5">执法检查</th>
					<th width="20%">组织执法检查（次）</th>
					<td width="20%"><input name="jcgzzb.zzzfjc" value="${jcgzzb.zzzfjc}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
					<th width="20%">检查单位（家）</th>
					<td width="20%"><input name="jcgzzb.jcdw" value="${jcgzzb.jcdw}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%">发现安全隐患（处）</th>
					<td width="20%"><input name="jcgzzb.fxaqyh" value="${jcgzzb.fxaqyh}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
					<th width="20%">督促整改隐患（处）</th>
					<td width="20%"><input name="jcgzzb.dczgxx" value="${jcgzzb.dczgxx}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%">其中：重大安全隐患（处）</th>
					<td width="20%"><input name="jcgzzb.zdaqyh" value="${jcgzzb.zdaqyh}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
					<th width="20%">其中：整改重大安全隐患（处）</th>
					<td width="20%"><input name="jcgzzb.zgzdaqyh" value="${jcgzzb.zgzdaqyh}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%">现场检查记录（份）</th>
					<td width="20%"><input name="jcgzzb.xcjcjl" value="${jcgzzb.xcjcjl}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
					<th width="20%">整改复查意见书（份）</th>
					<td width="20%"><input name="jcgzzb.zgfcyjs" value="${jcgzzb.zgfcyjs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%">责令限期整改指令书（份）</th>
					<td width="20%"><input name="jcgzzb.zlxqzgzls" value="${jcgzzb.zlxqzgzls}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
					<th width="20%">强制措施决定书（份）</th>
					<td width="20%"><input name="jcgzzb.qzcsjds" value="${jcgzzb.qzcsjds}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%">建议实施行政处罚</th>
					<td width="80%" colspan="4"><textarea name="jcgzzb.jyssxzcf" style="width:100%;height:120px">${jcgzzb.jyssxzcf}</textarea></td>
				</tr>
				<tr>
					<th width="20%">主要安全隐患</th>
					<td width="80%" colspan="4"><textarea name="jcgzzb.zyaqyh" style="width:100%;height:120px">${jcgzzb.zyaqyh}</textarea></td>
				</tr>
				<tr>
					<th width="20%">其它</th>
					<td width="80%" colspan="4"><textarea name="jcgzzb.qt" style="width:100%;height:120px">${jcgzzb.qt}</textarea></td>
				</tr>
				<tr>
					<th width="20%">填报人</th>
					<td width="20%"><input name="jcgzzb.tbr" value="${jcgzzb.tbr}" type="text" maxlength="255"></td>
					<th width="20%">填报日期</th>
					<td width="20%"><input name="jcgzzb.tbrq" value="<fmt:formatDate type='date' value='${jcgzzb.tbrq}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="5" height="100px" style="text-align:center">
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
