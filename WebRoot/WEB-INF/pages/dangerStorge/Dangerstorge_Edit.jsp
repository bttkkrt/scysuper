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
				
				document.myform1.action="dangerstorgeSave.action";
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
        
        function querywhp()
        {
        	var OpenWindow = window.showModalDialog("${ctx}/jsp/wxhxp/queryWxhxpList.action", '', 'dialogHeight=600px;dialogWidth=800px;scroll=no,resizable=no, status=no');
			if(typeof(OpenWindow) != 'undefined')
			{
				var sonValue = OpenWindow.split(";");
				document.getElementById('dangername').value = sonValue[0];
				document.getElementById('unnum').value = sonValue[1];
			}
        }
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="dangerstorge.id" value="${dangerstorge.id}">
		<input type="hidden" name="dangerstorge.createTime" value="<fmt:formatDate type="both" value="${dangerstorge.createTime}" />">
		<input type="hidden" name="dangerstorge.updateTime" value="${dangerstorge.updateTime}">
		<input type="hidden" name="dangerstorge.createUserID" value="${dangerstorge.createUserID}">
		<input type="hidden" name="dangerstorge.updateUserID" value="${dangerstorge.updateUserID}">
		<input type="hidden" name="dangerstorge.deptId" value="${dangerstorge.deptId}">
		<input type="hidden" name="dangerstorge.delFlag" value="${dangerstorge.delFlag}">
			<input type="hidden" name="dangerstorge.szz" value="${dangerstorge.szz}">
		<input type="hidden" name="dangerstorge.qymc"  value="${dangerstorge.qymc}">
		<input type="hidden" name="dangerstorge.comid"  value="${dangerstorge.comid}">
		
			<input type="hidden" name="dangerstorge.qylxc" value="${dangerstorge.qylxc}">
		<input type="hidden" name="dangerstorge.hyfl" value="${dangerstorge.hyfl}">
		<input type="hidden" name="dangerstorge.qygm" value="${dangerstorge.qygm}">
		<input type="hidden" name="dangerstorge.qyzclx" value="${dangerstorge.qyzclx}">
		
			<input type="hidden" name="dangerstorge.ifwhpqylx" value="${dangerstorge.ifwhpqylx}">
		<input type="hidden" name="dangerstorge.ifzywhqylx" value="${dangerstorge.ifzywhqylx}">
		<input type="hidden" name="dangerstorge.ifyhbzjyqy" value="${dangerstorge.ifyhbzjyqy}">
		<input type="hidden" name="dangerstorge.szc" value="${dangerstorge.szc}">
		<input type="hidden" name="dangerstorge.szcname" value="${dangerstorge.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">危险化学品名称</th>
					<td width="35%"><input id="dangername" name="dangerstorge.dangername" size=35 value="${dangerstorge.dangername}" type="text" dataType="Require" msg="此项为必填" maxlength="255" readonly="readonly"><a href="#" class="easyui-linkbutton" onclick="javascript:querywhp()" iconCls="icon-save">选择</a><font color="red">*</font></td>
					<th width="15%">危规号</th>
					<td width="35%"><input name="dangerstorge.dannum" size=35 value="${dangerstorge.dannum}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">UN号</th>
					<td width="35%"><input id="unnum" name="dangerstorge.unnum" size=35 value="${dangerstorge.unnum}" type="text" maxlength="255"></td>
					<th width="15%">年使用量(t)</th>
					<td width="35%"><input name="dangerstorge.yearuser" size=35 value="${dangerstorge.yearuser}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">最大贮存量(t)</th>
					<td width="35%"><input name="dangerstorge.maxstorge" size=35 value="${dangerstorge.maxstorge}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
					<th width="15%">贮存方式</th>
					<td width="35%"><input name="dangerstorge.storgerway" size=35 value="${dangerstorge.storgerway}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">贮存地点</th>
					<td width="35%"><input name="dangerstorge.storgeaddress" size=35 value="${dangerstorge.storgeaddress}" type="text" maxlength="255"></td>
					<th width="15%">包装方式</th>
					<td width="35%"><input name="dangerstorge.packingway" size=35 value="${dangerstorge.packingway}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">是否是易制毒化学品</th>
					<td width="35%"><cus:hxradio property="dangerstorge.sfsyzdhxp" codeName="是或否" value="${dangerstorge.sfsyzdhxp}" /></td>
					<th width="15%">是否是重点监管化学品</th>
					<td width="35%"><cus:hxradio property="dangerstorge.sfzdjghxp" codeName="是或否" value="${dangerstorge.sfzdjghxp}" /></td>
				</tr>
				<tr>
					<th width="15%">是否是易制爆化学品</th>
					<td width="35%"><cus:hxradio property="dangerstorge.sfyzbhxp" codeName="是或否" value="${dangerstorge.sfyzbhxp}" /></td>
					<th width="15%">是否是剧毒化学品</th>
					<td width="35%"><cus:hxradio property="dangerstorge.sfjdhxp"  codeName="是或否" value="${dangerstorge.sfjdhxp}" /></td>
				</tr>
				<tr>
					<th width="15%">企业类型</th>
					<td width="85%" colspan="3"><cus:hxcheckbox property="dangerstorge.qylx"  codeName="危化品企业类型" value="${dangerstorge.qylx}" /></td>
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
