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
			     var imsi = $("#imsi").val();
			     var imei = $("#imei").val();
			     var telephone = $("#telephone").val();
			     var terminalName = $("#terminalName").val();
			     var reg = /^[1]\d{10}$/;
			     if(imei ==""){
                            alert("imei不能为空");
                             imei.focus();
                            return false;
                        }
                 if(imsi =="" || isNaN(imsi) || imsi.substring(0,5) != "46003"){
                            alert("imsi必须是以46003开头的整数");
                            imsi.focus();
                            return false;
                        }
              
                if(telephone =="" || isNaN(telephone)||telephone.length!=11||!reg.test(telephone)){
                            alert("终端号码必须是11位整数");
                            telephone.focus();
                            return false;
                        }
               if(terminalName ==""){
                            alert("终端名称不能为空！");
                            terminalName.focus();
                            return false;
                        }
                        
				var deptId = $("#dept");
				document.myform1.action="userTerminalSave.action?code="+deptId.val();
				document.myform1.submit();
			
		}
		
		function search_parent(){
            popupCenter("${ctx}/jsp/admin/dept/deptTree.action?func=setDept", "DeptTree", "200", "200", 
                "no", "no", "no", "yes", "yes","no");            
        }
        function queryPeople(obj){
		$.ajax({
			type:"POST",
			url:"userTerminalQueryUser.action?mode=ajaxJson&deptCode="+obj,
			success:function(json){
				json = eval(json)
				var selectContainer = $('#userNum'); 
				 selectContainer.empty();
				 var option = $('<option></option>').text("").val(""); 
					selectContainer.append(option); 
  				for(var i=0; i<json.length; i++){
					var option = $('<option></option>').text(json[i].name).val(json[i].id); 
					selectContainer.append(option); 
			 }
			},
			dateType:"json"
		});
	}
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="userTerminal.id" value="${userTerminal.id}">
		<input type="hidden" name="userTerminal.createTime" value="<fmt:formatDate type="both" value="${userTerminal.createTime}" />">
		<input type="hidden" name="userTerminal.updateTime" value="${userTerminal.updateTime}">
		<input type="hidden" name="userTerminal.createUserID" value="${userTerminal.createUserID}">
		<input type="hidden" name="userTerminal.updateUserID" value="${userTerminal.updateUserID}">
		<input type="hidden" name="userTerminal.deptId" value="${userTerminal.deptId}">
		<input type="hidden" name="userTerminal.delFlag" value="${userTerminal.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
						<th width="15%">所属部门</th>
						<td width="35%">
							<s:select  theme="simple" cssStyle="width:100px;" id="dept" list="%{depts}" listKey="deptCode"
								name="userTerminal.deptId" listValue="deptName"
								emptyOption="true" onchange="queryPeople(this.value)">
							</s:select>
					    </td>
				</tr>
				<tr>
					<th width="15%">用户名称</th>
					<td width="35%">
						<s:select theme="simple"  cssStyle="width:100px;" id="userNum" list="%{users}" listKey="id"
								name="userTerminal.userNum" listValue="displayName"
								emptyOption="true">
						</s:select>
					</td>
				</tr>
				<tr>
					<th width="15%">终端名称<font color="red">*</font></th>
					<td colspan="3"><input id="terminalName" name="userTerminal.terminalName" value="${userTerminal.terminalName}" type="text" maxlength="20"></td>
				</tr>
				<tr>
					<th width="15%">终端号码<font color="red">*</font></th>
					<td colspan="3"><input id="telephone" name="userTerminal.telephone" value="${userTerminal.telephone}" type="text" maxlength="11"></td>
				</tr>
				<tr>
					<th width="15%">IMSI<font color="red">*</font></th>
					<td colspan="3"><input id="imsi" name="userTerminal.imsi" value="${userTerminal.imsi}" type="text"></td>
				</tr>
				<tr>
					<th width="15%">IMEI<font color="red">*</font></th>
					<td colspan="3"><input id="imei" name="userTerminal.imei" value="${userTerminal.imei}" type="text"></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align: center;">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
							<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
