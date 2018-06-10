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
				
				document.myform1.action="dangerUsedSave.action";
				document.myform1.submit();
			}
		}
		function validateNum(event,obj)
        {
        	event = window.event||event; 
       	 	if(event.keyCode == 37 | event.keyCode == 39){ 
           		return; 
       	 	} 
	        //先把非数字的都替换掉，除了数字和. 
	        obj.value = obj.value.replace(/[^\d.]/g,""); 
	        //必须保证第一个为数字而不是. 
	        obj.value = obj.value.replace(/^\./g,""); 
	        //保证只有出现一个.而没有多个. 
	        obj.value = obj.value.replace(/\.{2,}/g,"."); 
	        //保证.只出现一次，而不能出现两次以上 
	        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	        if(obj.value.length >= 2 && obj.value.substring(0,1) == "0" && obj.value.substring(1,2) != ".")
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
		<input type="hidden" name="dangerused.id" value="${dangerused.id}">
		<input type="hidden" name="dangerused.createTime" value="<fmt:formatDate type="both" value="${dangerused.createTime}" />">
		<input type="hidden" name="dangerused.updateTime" value="${dangerused.updateTime}">
		<input type="hidden" name="dangerused.createUserID" value="${dangerused.createUserID}">
		<input type="hidden" name="dangerused.updateUserID" value="${dangerused.updateUserID}">
		<input type="hidden" name="dangerused.deptId" value="${dangerused.deptId}">
		<input type="hidden" name="dangerused.delFlag" value="${dangerused.delFlag}">
			<input type="hidden" name="dangerused.szz" value="${dangerused.szz}">
		<input type="hidden" name="dangerused.qymc"  value="${dangerused.qymc}">
		<input type="hidden" name="dangerused.comid"  value="${dangerused.comid}">
		
			<input type="hidden" name="dangerused.qylxc" value="${dangerused.qylxc}">
		<input type="hidden" name="dangerused.hyfl" value="${dangerused.hyfl}">
		<input type="hidden" name="dangerused.qygm" value="${dangerused.qygm}">
		<input type="hidden" name="dangerused.qyzclx" value="${dangerused.qyzclx}">
		
		<input type="hidden" name="dangerused.ifwhpqylx" value="${dangerused.ifwhpqylx}">
		<input type="hidden" name="dangerused.ifzywhqylx" value="${dangerused.ifzywhqylx}">
		<input type="hidden" name="dangerused.ifyhbzjyqy" value="${dangerused.ifyhbzjyqy}">
		
		<input type="hidden" name="dangerused.szc" value="${dangerused.szc}">
		<input type="hidden" name="dangerused.szcname" value="${dangerused.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">危险化学品名称</th>
					<td width="35%"><input name="dangerused.dangername"size=35 value="${dangerused.dangername}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
					<th width="15%">危规号</th>
					<td width="35%"><input name="dangerused.dannum"size=35 value="${dangerused.dannum}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">年使用量(t)</th>
					<td width="35%"><input name="dangerused.yearuser"size=35 value="${dangerused.yearuser}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
					<th width="15%">最大贮存量(t)</th>
					<td width="35%"><input name="dangerused.maxstorge"size=35 value="${dangerused.maxstorge}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">贮存方式</th>
					<td width="35%"><input name="dangerused.storgerway"size=35 value="${dangerused.storgerway}" type="text" maxlength="255"></td>
					<th width="15%">贮存地点</th>
					<td width="35%"><input name="dangerused.storgeaddress"size=35 value="${dangerused.storgeaddress}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">包装方式</th>
					<td width="35%"><input name="dangerused.packingway"size=35 value="${dangerused.packingway}" type="text" maxlength="255"></td>
					<th width="15%">是否是易制毒化学品</th>
					<td width="35%"><cus:hxradio property="dangerused.sfsyzdhxp" codeName="是或否" value="${dangerused.sfsyzdhxp}" /></td>
				</tr>
				<tr>
					<th width="15%">是否是重点监管化学品</th>
					<td width="35%"><cus:hxradio property="dangerused.sfzdjghxp" codeName="是或否" value="${dangerused.sfzdjghxp}" /></td>
					<th width="15%">是否是易制爆化学品</th>
					<td width="35%"><cus:hxradio property="dangerused.sfyzbhxp" codeName="是或否" value="${dangerused.sfyzbhxp}" /></td>
				</tr>
				<tr>
					<th width="15%">是否是剧毒化学品</th>
					<td width="35%"><cus:hxradio property="dangerused.sfjdhxp"  codeName="是或否" value="${dangerused.sfjdhxp}" /></td>
					<th width="15%">企业类型</th>
					<td width="35%"><cus:hxcheckbox property="dangerused.qylx"  codeName="危化品企业类型" value="${dangerused.qylx}" /></td>
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
