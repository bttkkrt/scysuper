<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<%@ taglib uri="/WEB-INF/tld/edp.tld" prefix="edp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>添加/修改布局</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="description" content="添加/修改布局">
		<link rel="stylesheet" type="text/css"
	href="${ctx}/webResources/themes/${curr_user.cssId}/css/style.css">
		<script src="${ctx}/webResources/js/common.js"></script>
		<script src="${ctx}/webResources/js/easyui/jquery-1.4.4.min.js"></script>
		<script src="${ctx}/webResources/js/validator.js"></script>
	<script>
	function checkRight(val, ff){
		var roleIds = ff.roleIds;
		if(val==0){
			if(roleIds!=null){
				if(roleIds.length==1){
					roleIds.disabled = false;
				}else{
					for(i=0;i<roleIds.length;i++)
						roleIds[i].disabled = false;
				}
			}
		}else{
			if(roleIds!=null){
				if(roleIds.length==1){
					roleIds.disabled = true;
				}else{
					for(i=0;i<roleIds.length;i++)
						roleIds[i].disabled = true;
				}
			}
		}
	}	

	function save_portal(ff){
		if($("#title").val()==""){
			alert("请输入布局名称");
			$("#title").focus();
		}else if($("#url").val()==""){
			alert("请输入布局url");
			$("#url").focus();
		}else
			ff.submit();
	}
    </script>
	</head>
	<c:set var="curr_path" value="新建/修改布局"></c:set>
	<body>
	    <div class="submitdata">
		<form name="portalFrm" id="portalFrm" method="post" enctype="multipart/form-data" action="savePortal.action">
			<c:if test="${not empty portal.id}">
			<input type="hidden" name="portal.id" id="id" value="${portal.id}">
			</c:if>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th width="20%">
						布局名称<font color="red">*</font>
					</th>
					<td width="80%">
						<input type='text' class="input_text" maxlength="30"  name="portal.title" id="title" value="${portal.title }" >
					</td>
				</tr>
				<tr height="22px">
					<th>
						布局URL<font color="red">*</font>
					</th>
					<td >
						<input type='text' name="portal.url" size="40" maxlength="60" id="url" value="${portal.url}" class="input_text">
					</td>
				</tr>
				<tr >
					<th>
						是否公开<font color="red">*</font>
					</th>
					<td>
						<input type='radio' name="portal.isPublic" id="isPublic" onclick="checkRight(this.value, this.form)" <c:if test="${portal.isPublic==1}">checked</c:if> value="1" class="inputtxt">是
						<input type='radio' name="portal.isPublic" id="isPublic" onclick="checkRight(this.value, this.form)" <c:if test="${empty portal.isPublic or portal.isPublic==0}">checked</c:if> value="0" class="inputtxt">否
					</td>
				</tr>	
				<tr>
				    <th>用户角色</th>
				    <td>
				    <c:set var="i" value="1"/>
				    <c:forEach var="role" items="${roleList}">
				    <c:set var="flag" value="false"></c:set>
				    <c:forEach var="userRight" items="${portal.rightList}">
				    <c:if test="${role.id==userRight.role.id}">
				    <c:set var="flag" value="true"></c:set>
				    </c:if>
				    </c:forEach>
				    <input type="checkbox" name="roleIds" value="${role.id }" <c:if test="${portal.isPublic==1}">disabled</c:if> <c:if test="${flag}">checked</c:if>  >${role.roleName }
				    <c:if test="${i%4==0}"><br></c:if>
				    <c:set var="i" value="${i+1}"/>
				    </c:forEach>
				    </td>
				</tr>
				<tr >
					<td colspan='2' class="set_c">
						<input type='button' value='保存' class="input_button"
							onclick="save_portal(this.form)" />						
						<input type='button' value='关闭' onclick="window.close('_self')"
							class="input_button" />
					</td>
				</tr>
			</table>
			</form>
			</div>
	</body>
</html>