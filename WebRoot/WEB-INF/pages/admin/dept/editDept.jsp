<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>新建/修改部门</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
	        function search_parent(){
	            var id = document.getElementById("dept.id").value;
	            //createSimpleWindow("setDept", "设置上层部门", "${ctx}/jsp/admin/dept/deptTree.action?needCheck=true&func=setParentDept&deptId="+id, 250, 400);
	            var indexLayer =layer.open({
					  type: 2,
					  title: "设置上层部门",
					  maxmin: true,
					  shadeClose: true, //点击遮罩关闭层
					  area : [250, 400],
					  content: "${ctx}/jsp/admin/dept/deptTree.action?func=setParentDept&deptId="+id
				});
	        }
	       /*  window.formAjaxCallback=function(data){
	        	if(data.status="y"){
	        		parent.$.messager.alert("成功","保存成功！", "info",function(){
	        			reloadData("edit_dept");
	        		});
	        		//暂缺刷新代码
	        	}else{
	        		parent.$.messager.alert("错误","保存时出错！");
	        	}
	        } */
	        function save(){
				//$("#myform").attr("action","saveDept.action");
				//$("#myform").submit();
				var formData = new FormData($( "#myform" )[0]);  
				layer.load(0, {shade: false});
	     		$.ajax({  
	          		url: 'saveDept.action' ,  
	          		type: 'POST',  
	          		data: formData,  
	          		dataType: 'json',
	          		async: false,  
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
				//判断是否通过校验
				/* if($("#myform").Validform().getStatus()!="posted"){
					return;
				}
				closeLayer(); */
			}
	    </script>
	</head>
	<body validform="true">
		<div class="page_dialog">
				<div class="inner6px">
					<div class="cell">
						<form id="myform" name="myform" method="post" action="" >
							<s:token />
							<c:if test="${not empty dept.id}">
							    <input type="hidden" name="dept.delFlag" id="delFlag" value="${dept.delFlag}"> 
							    <input type="hidden" name="dept.hasChild" id="hasChild" value="${dept.hasChild}">
							    <input type="hidden" name="dept.id" id="dept.id" value="${dept.id}">
							    
							</c:if>
							<input type="hidden" name="dept.isPublic" id="isPublic" value="0"/>
							<table>
								<tr>
									<th width="80">上级部门</th>
									<td>
										<input class="form_text" id="deptNameOne" name="deptName" value="${deptName}" readOnly>
										<input class="form_text" id="deptCodeOne" name="deptCode" value="${deptCode}" type="hidden">
										<c:if test='${not empty dept.id}'>
											<a href="###" class="btn_01_mini" onclick="search_parent()">查找<b></b></a>
										</c:if>
									</td>
								</tr>
								<tr>
									<th>部门编号</th>
									<td>
										<input class="form_text" id="dept.deptCode" name="dept.deptCode" readOnly
											value="<c:if test='${not empty dept.id}'>${dept.deptCode}</c:if><c:if test='${empty dept.id}'>${newDeptCode}</c:if>"> 
									</td>
								</tr>
								<tr>
									<th>部门名称</th>
									<td>
										<input class="form_text" id="editDeptName" name="dept.deptName" value="${dept.deptName}" datatype="s1-20" errormsg="不能为空，部门名称长度不能超过20个字符！"
											 ajaxurl="checkDept.action?dept.id=${dept.id}&parentDeptCode=${deptCode}&deptCode=<c:if test='${not empty dept.id}'>${dept.deptCode}</c:if><c:if test='${empty dept.id}'>${newDeptCode}</c:if>">
									</td>
								</tr>
								<input type="hidden" name="dept.deptTypeCode" id="dept.deptTypeCode" value="04">
								<input type="hidden" name="dept.linkedDeptTypeCode" id="dept.linkedDeptTypeCode" value="04">
								<!--
								<tr>
								    <th>部门类型</th>
								    <td>
								        <cus:SelectOneTag property="dept.deptTypeCode" defaultText='请选择' codeName="部门属性" value="${dept.deptTypeCode}" />
								    </td>
								</tr>
								<tr>
								    <th>关联部门类型</th>
								    <td>
								        <cus:SelectOneTag property="dept.linkedDeptTypeCode" defaultText='请选择' codeName="部门属性" value="${dept.linkedDeptTypeCode}" />
								    </td>
								</tr>-->
								<tr>
									<th>同级排序</th>
									<td><input class="form_text" name="dept.sortSQ" value="${dept.sortSQ}" datatype="n1-3" maxlength="3" errormsg="排序号必须是小于1000的数字"></td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="btn_area_setc">
											<a href="###" class="btn_01_mini" onclick="save();">保存<b></b></a>
											<a href="###" class="btn_01_mini" onclick="closeLayer();">关闭<b></b></a>
										</div>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
		</div>
	</body>
</html>