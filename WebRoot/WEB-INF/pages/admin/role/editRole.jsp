<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/jsLib.jsp"%>
	</head>

	<body validform="true">
		<div class="page_dialog">
				<div class="inner6px">
					<div class="cell">
						<form name="myform" id="myform" method="post">
						    <input type="hidden" name="userRole.roleType" value="001"/>
						    <input type="hidden" name="userRole.delFlag" value="0"/>
							<s:token />
							<c:if test="${not empty userRole.id}">								
								<input type="hidden" name="userRole.id" 	 value="${userRole.id}">
							</c:if>
							<table>
							<!--
								<tr>
									<th>上级角色</th>
									<td>
										<input type="text"   name="parentRoleName" 		  value="${userRole.parentRole.roleName}" readOnly>
										<input type="hidden" name="parentRoleCode" 		  value="${userRole.parentRole.roleCode}">
										<input type="hidden" name="userRole.parentRoleId" value="${userRole.parentRoleId}">
									</td>
								</tr>
							-->	
								<tr>
									<th>角色编号</th>
									<td>
										<input type="text" name="userRole.roleCode" value="${userRole.roleCode}" readOnly>
									</td>
								</tr>
								<tr>
									<th>角色名称<font color="red">*</font></th>
									<td>
										<input id='roleName' type="text" name="userRole.roleName" value="${userRole.roleName}" datatype="s1-20" ajaxurl="roleIsReg.action?userRole.id=${userRole.id}&userRole.roleCode=${userRole.roleCode}">
									</td>
								</tr>
								<c:if test="${curr_user.isSuperAdmin}">
								<tr>
									<th>超级管理员</th>
									<td>
										<select name="userRole.isSupAdmin">
											<option value="0" <c:if test="${userRole.isSupAdmin==0}">selected</c:if> >
												否
											</option>
											<option value="1" <c:if test="${userRole.isSupAdmin==1}">selected</c:if> >
												是
											</option>
										</select>
									</td>
								</tr>
								</c:if>
								<!--
								<tr>
									<th>角色状态<font color="red">*</font></th>
									<td> 
										<select name="userRole.delFlag">
										    <option value="0" <c:if test="${userRole.delFlag==0}">selected</c:if>>正常</option>
										    <option value="2" <c:if test="${userRole.delFlag==2}">selected</c:if>>注销</option>
										</select>
									</td>
								</tr>
								-->
								<tr>
									<th>同级排序</th>
									<td><input type="text" name="userRole.sortSq" value="${userRole.sortSq}" datatype="n1-3"></td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="btn_area_setc">
											<a href="###" class="btn_01_mini"  onclick="save()">保存<b></b></a>
											
											<a href="###" class="btn_01_mini" onclick="closeLayer();">关闭<b></b></a>
										</div>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
		</div>
	<script type="text/javascript">
		function save(){
			var formData = new FormData($( "#myform" )[0]);  
			layer.load(0, {shade: false});
     		$.ajax({  
          		url: 'saveRole.action' ,  
          		type: 'POST',  
          		data: formData,  
          		dataType: 'json',
          		async: true,  
          		cache: false,  
          		contentType: false,  
          		processData: false,  
          		success: function (data) {
          			parent.getResponse(data);
          			closeLayer();
             	 	/* if(data.status=="y"){
    					reloadData('edit_user');
	             	 	closeLayer();
        				/* parent.$.messager.alert("成功","保存成功！", "info",function(){
        				}); 
        			}else{
        				parent.$.messager.alert("错误",data.info);
        			} */
          		},  
          		error: function () {  
              		alert("保存时出错");  
              		closeLayer();
          		}  
     		});
		}
	</script>
	</body>
</html>