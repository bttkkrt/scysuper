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
				
				document.myform1.action="zybfzjfSave.action";
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
		<input type="hidden" name="zybfzjf.id" value="${zybfzjf.id}">
		<input type="hidden" name="zybfzjf.createTime" value="<fmt:formatDate type="both" value="${zybfzjf.createTime}" />">
		<input type="hidden" name="zybfzjf.updateTime" value="${zybfzjf.updateTime}">
		<input type="hidden" name="zybfzjf.createUserID" value="${zybfzjf.createUserID}">
		<input type="hidden" name="zybfzjf.updateUserID" value="${zybfzjf.updateUserID}">
		<input type="hidden" name="zybfzjf.deptId" value="${zybfzjf.deptId}">
		<input type="hidden" name="zybfzjf.delFlag" value="${zybfzjf.delFlag}">
		
		<input type="hidden" name="zybfzjf.szzname" value="${zybfzjf.szzname}">
		<input type="hidden" name="zybfzjf.qymc" value="${zybfzjf.qymc}">
		<input type="hidden" name="zybfzjf.szzid" value="${zybfzjf.szzid}">
		<input type="hidden" name="zybfzjf.qyid" value="${zybfzjf.qyid}">
		<input type="hidden" name="zybfzjf.qylx" value="${zybfzjf.qylx}">
		<input type="hidden" name="zybfzjf.hyfl" value="${zybfzjf.hyfl}">
		<input type="hidden" name="zybfzjf.qygm" value="${zybfzjf.qygm}">
		<input type="hidden" name="zybfzjf.qyzclx" value="${zybfzjf.qyzclx}">
		<input type="hidden" name="zybfzjf.szc" value="${zybfzjf.szc}">
		<input type="hidden" name="zybfzjf.szcname" value="${zybfzjf.szcname}">
		
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">年度</th>
					<td width="35%">
						<input class="inputText4" name="zybfzjf.jshxYear" value="${zybfzjf.jshxYear}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy'})" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font color="red">*</font>
					</td>
					<th width="15%">用途</th>
					<td width="35%">
						<s:select cssStyle="width:100px" id="yt" listKey="key" listValue="value"  theme="simple" list="#{'职业卫生管理机构的组织工作经费':'职业卫生管理机构的组织工作经费','生产车间改造':'生产车间改造','生产工艺改进':'生产工艺改进',
						'防护设施建设与维护':'防护设施建设与维护','个人劳动防护用品':'个人劳动防护用品','工作场所卫生检测评价':'工作场所卫生检测评价','职业卫生宣传培训':'职业卫生宣传培训','职业健康监护':'职业健康监护','职业病人诊疗':'职业病人诊疗','警示标识':'警示标识','工伤保险':'工伤保险'}" 
						name="zybfzjf.yt" value="{zybfzjf.yt}"/><font color="red">*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">工作内容</th>
					<td width="35%"><input name="zybfzjf.gznr" value="${zybfzjf.gznr}" type="text" maxlength="255"></td>
					<th width="15%">经费(万元)</th>
					<td width="35%"><input name="zybfzjf.fee" value="${zybfzjf.fee}" type="text" maxlength="255" onKeyUp="validateNum(event,this)" dataType="Require" msg="此项为必填"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3"><input name="zybfzjf.remark" value="${zybfzjf.remark}" type="text" maxlength="255"></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center" style="text-align:center;">
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
