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
				
				document.myform1.action="dfzwSave.action";
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
		<style>
	th
	{
		border:1px solid #d5dbdc;
		height:24px;
		text-align:center;
		padding:0 2px 0 0;
	}
	td
	{
		border:1px solid #d5dbdc;
		height:24px;
		text-align:left;
		padding:0 0 0 2px;
		background:#fff
	}
	tr
	{
		border:1px solid #d5dbdc;
		height:24px
	}
	</style>
</head>
<body>
	<div theme="simple" style="clear:both;background:#f5f8f9;border:1px solid #e8eff2;margin:6px 0 0 0;overflow: hidden;height: 100%">
		
		<div style="width:99%;height:3%">
			<table width="100%" border="0" style="border-collapse:collapse;border:1px solid #d5dbdc;width:100%">
				<tr>
					<th width="20%">本地区打击各类行业企业情况（单位：人、家、个、处、万元）</th>
					<th width="8%">非煤矿山</th>
					<th width="8%">道路交通</th>
					<th width="8%">水上交通</th>
					<th width="8%">建筑施工</th>
					<th width="8%">消防</th>
					<th width="8%">危化品</th>
					<th width="8%">烟花爆竹</th>
					<th width="8%">民爆物品</th>
					<th width="8%">冶金</th>
					<th width="8%">其他</th>
				</tr>
			</table>
		</div>
		<div style="width:100%;height:80%;overflow-y:auto;">
			<form name="myform1" method="post" enctype="multipart/form-data">
			<s:token />
			<input type="hidden" name="flag" value="${flag}">
			<input type="hidden" name="dfzw.id" value="${dfzw.id}">
			<input type="hidden" name="dfzw.createTime" value="<fmt:formatDate type="both" value="${dfzw.createTime}" />">
			<input type="hidden" name="dfzw.updateTime" value="${dfzw.updateTime}">
			<input type="hidden" name="dfzw.createUserID" value="${dfzw.createUserID}">
			<input type="hidden" name="dfzw.updateUserID" value="${dfzw.updateUserID}">
			<input type="hidden" name="dfzw.deptId" value="${dfzw.deptId}">
			<input type="hidden" name="dfzw.delFlag" value="${dfzw.delFlag}">
			<table width="100%" border="0" style="border-collapse:collapse;border:1px solid #d5dbdc;width:100%">
				<c:forEach var="dfzwglb" items="${dfzwlist}" varStatus="status">
					<tr>
						<td width="20%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${dfzwglb.linktype}<input type="hidden" name="dfzwglb.linktype" value="${dfzwglb.linktype}"></td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="dfzwglb.num1" value="${dfzwglb.num1}" type="text" style="width:100%;<c:if test="${status.count%2==0}">background-color:#EEE</c:if>" onKeyUp="validateNum(event,this)"/></td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="dfzwglb.num2" value="${dfzwglb.num2}" type="text" style="width:100%;<c:if test="${status.count%2==0}">background-color:#EEE</c:if>" onKeyUp="validateNum(event,this)"/></td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="dfzwglb.num3" value="${dfzwglb.num3}" type="text" style="width:100%;<c:if test="${status.count%2==0}">background-color:#EEE</c:if>" onKeyUp="validateNum(event,this)"/></td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="dfzwglb.num4" value="${dfzwglb.num4}" type="text" style="width:100%;<c:if test="${status.count%2==0}">background-color:#EEE</c:if>" onKeyUp="validateNum(event,this)"/></td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="dfzwglb.num5" value="${dfzwglb.num5}" type="text" style="width:100%;<c:if test="${status.count%2==0}">background-color:#EEE</c:if>" onKeyUp="validateNum(event,this)"/></td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="dfzwglb.num6" value="${dfzwglb.num6}" type="text" style="width:100%;<c:if test="${status.count%2==0}">background-color:#EEE</c:if>" onKeyUp="validateNum(event,this)"/></td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="dfzwglb.num7" value="${dfzwglb.num7}" type="text" style="width:100%;<c:if test="${status.count%2==0}">background-color:#EEE</c:if>" onKeyUp="validateNum(event,this)"/></td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="dfzwglb.num8" value="${dfzwglb.num8}" type="text" style="width:100%;<c:if test="${status.count%2==0}">background-color:#EEE</c:if>" onKeyUp="validateNum(event,this)"/></td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="dfzwglb.num9" value="${dfzwglb.num9}" type="text" style="width:100%;<c:if test="${status.count%2==0}">background-color:#EEE</c:if>" onKeyUp="validateNum(event,this)"/></td>
						<td width="8%" <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="dfzwglb.num10" value="${dfzwglb.num10}" type="text" style="width:100%;<c:if test="${status.count%2==0}">background-color:#EEE</c:if>" onKeyUp="validateNum(event,this)"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div style="width:100%;height:17%">
			<table width="100%" border="0" style="border-collapse:collapse;border:1px solid #d5dbdc;width:100%">
				<tr>
					<th>填报单位</th>
					<td><input name="dfzw.szzname" value="${dfzw.szzname}" type="text" maxlength="255"></td>
					<th>填报人</th>
					<td><input name="dfzw.tbr" value="${dfzw.tbr}" type="text" maxlength="255"></td>
					<th>填报时间</th>
					<td><input name="dfzw.tbsj" value="<fmt:formatDate type='date' value='${dfzw.tbsj}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<td colspan="11" height="100px" style="text-align:center">
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
			</form>
		</div>
	</div>
</body>
</html>
