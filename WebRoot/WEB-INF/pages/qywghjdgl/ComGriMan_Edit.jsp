<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript">
		function deptChange(){
			var deptCode=$("#gridManageDept").val();
			if(""==deptCode){
				document.getElementById('personTd').innerHTML="<select style='width:50%;' ><option>请选择</option></select>";
				return ;
			}
			 $.ajax({
		                	url : "deptChange.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	deptCode : deptCode
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','操作时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	document.getElementById('personTd').innerHTML=data.s;
		                        	
		                        }else{
		                        	$.messager.alert('错误','操作时出错！');
		                        }
		                    }
		                });
		
		}
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="comGriManSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="comGriMan.id" value="${comGriMan.id}">
		<input type="hidden" name="comGriMan.createTime" value="<fmt:formatDate type="both" value="${comGriMan.createTime}" />">
		<input type="hidden" name="comGriMan.updateTime" value="${comGriMan.updateTime}">
		<input type="hidden" name="comGriMan.createUserID" value="${comGriMan.createUserID}">
		<input type="hidden" name="comGriMan.updateUserID" value="${comGriMan.updateUserID}">
		<input type="hidden" name="comGriMan.deptId" value="${comGriMan.deptId}">
		<input type="hidden" name="comGriMan.delFlag" value="${comGriMan.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">网格名称</th>
					<td width="35%"><input name="comGriMan.gridName" style="width: 60%" id="gName" value="${comGriMan.gridName}" type="text" datatype="*1-127" errormsg='网格名称必须是1到127位字符！' nullmsg='网格名称不能为空！' sucmsg='网格名称填写正确！' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">MAP_KEY</th>
					<td width="35%"><input name="comGriMan.mapKey"  style="width: 60%" value="${comGriMan.mapKey}" type="text"   maxlength="127"> </td>
					
				</tr>
				<tr>
					<th width="15%">网格管理中队</th>
					<td width="35%"><cus:SelectOneTag style="width: 60%" property="comGriMan.gridManageDept" defaultText='请选择' codeSql="select DEPT_CODE,DEPT_NAME from DEPARTMENT where DEL_FLAG=0 and ((substring(DEPT_CODE,0,10)='002001005' and DEPT_CODE !='002001005') or (substring(DEPT_CODE,0,10)='002001004' and DEPT_CODE !='002001004'))" value="${comGriMan.gridManageDept}" datatype="*1-127" errormsg='网格管理中队必须是1到127位字符！' nullmsg='网格管理中队不能为空！' sucmsg='网格管理中队选择正确！'/><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">管辖范围</th>
					<td width="85%" colspan="3"><textarea name="comGriMan.manageScope" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:96%;height:50px">${comGriMan.manageScope}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit"  >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_comGriMan');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
