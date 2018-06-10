<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript">
	var a=0;
	function beforeSubmitCallback(){
		checkLoginId();
		if(a == 1)
		{
			return false;
		}
	}
	
	function checkLoginId(){
		var loginId=$("#loginId").val();
					$.ajax({
		                	url : "checkLoginId.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : loginId
		                    },
		                    error: function(){
		                    	a=1;
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	document.getElementById('errMsg').style.display="none";
		                        	a=0;
		                        }else{
		                        	document.getElementById('errMsg').style.display="";
		                        	a=1;
		                        }
		                    }
		                });
	}
	
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: ">
			<font style='color:red'>*用户名和密码用于提供给巡查人员登录手机端</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="patrolUserSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="patrolUser.id" value="${patrolUser.id}">
		<input type="hidden" name="patrolUser.createTime" value="<fmt:formatDate type="both" value="${patrolUser.createTime}" />">
		<input type="hidden" name="patrolUser.updateTime" value="${patrolUser.updateTime}">
		<input type="hidden" name="patrolUser.createUserID" value="${patrolUser.createUserID}">
		<input type="hidden" name="patrolUser.updateUserID" value="${patrolUser.updateUserID}">
		<input type="hidden" name="patrolUser.deptId" value="${patrolUser.deptId}">
		<input type="hidden" name="patrolUser.delFlag" value="${patrolUser.delFlag}">
		<input type="hidden" name="patrolUser.companyId" value="${patrolUser.companyId}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="patrolUser.userName" style="width:60%" value="${patrolUser.userName}" type="text" datatype="*1-50" errormsg='姓名必须是1到127位字符！' nullmsg='姓名不能为空！' sucmsg='姓名填写正确！'  maxlength="50"><font style='color:red'>*</font></td>
					<th width="15%">手机</th>
					<td width="35%"><input name="patrolUser.mobile" style="width:60%" value="${patrolUser.mobile}" type="text" maxlength="50"></td>
				</tr>
				<tr>
					<th width="15%">用户名</th>
					<td width="35%"><input id="loginId" name="patrolUser.loginId" style="width:60%" value="${patrolUser.loginId}" type="text" datatype="*1-127" errormsg='用户名必须是1到127位字符' nullmsg='用户名不能为空！' sucmsg='用户名填写正确！'  onblur="checkLoginId();"><font style='color:red'>*</font><font style='color:red;display:none;' id='errMsg'>用户名重复</font></td>
					<th width="15%">密码</th>
					<td width="35%"><input name="patrolUser.passWord" style="width:60%" value="${patrolUser.passWord}" type="text" datatype="*1-127" errormsg='密码必须是1到127位字符' nullmsg='密码不能为空！' sucmsg='密码填写正确！' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">职务</th>
					<td width="35%"><input name="patrolUser.job" style="width:60%" value="${patrolUser.job}" type="text" maxlength="127"></td>
				</tr> 
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit">添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="document.myform1.reset()">重置<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_patrolUser');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
