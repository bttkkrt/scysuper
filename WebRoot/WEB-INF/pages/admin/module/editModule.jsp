<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Edit Module</title>
		<%@include file="/common/jsLib.jsp"%>
		
		<script>
		function get_moduleTree(){
			createSimpleWindow("ModuleTree", "选择父层模块", "${ctx}/jsp/admin/module/moduleTree.action?func=setModule", "200", "400");			
	    }
		function save(){
			$("#myform").attr("action","saveModule.action");
			$("#myform").submit();
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
					<form id="myform" name="myform" method="post" action="">
						<s:token />
						<c:if test="${not empty module.id}">
							<input type="hidden" name="module.id" id="moduleId" value="${module.id}">
							<input type="hidden" name="module.hasChild" id="hasChild" value="${module.hasChild}">
						</c:if>
						<div class="cell">
							<table>
								<tr>
									<th width="15%">模块名称</th>
									<td width="35%"><input name="module.moduleName" value="${module.moduleName}" id="moduleName" datatype="s1-30" errormsg="不能为空，长度不能超过30个字符！"></td>
									<th width="15%">模块编号</th>
									<td width="35%"><input name="module.moduleCode" value="${module.moduleCode}" readOnly id="moduleCode"></td>
								</tr>
								<tr>
									<th>上级模块</th>
									<td valign="middle">
										<input id="parentModuleCode" name="parentModuleName" value="${parentModuleName}" >
										<input id="parentModuleName" name="moduleCode" value="${parentModuleCode}" type="hidden">
										<img src="${ctx}/webResources/style/images/default/fm.gif" border="0" onclick="get_moduleTree()">
									</td>
									<th>同级排序</th>
									<td><input name="module.sortSq" value="${module.sortSq}" datatype="n1-3" maxlength="3" errormsg="排序号必须是小于1000的数字" ignore="ignore"></td>
								</tr>
								<tr>
									<th>对应地址</th>
									<td colspan="3"><input name="module.moduleAddr" value="${module.moduleAddr}" style="width:400px;"></td>
								</tr>
								<tr>
									<th>可见</th>
									<td>
										<select name="module.isVisible">
											<option value="1" <c:if test="${module.isVisible==1}">selected</c:if>>显示</option>
											<option value="0" <c:if test="${module.isVisible==0}">selected</c:if>>隐藏</option>
										</select>
									</td>
									<th>访问权限</th>
									<td>
										公开
										<input type="radio" name="module.isPublic" value="1" <c:if test="${module.isPublic =='1'}"> checked="checked"</c:if>>
										不公开
										<input type="radio" name="module.isPublic" value="0" <c:if test="${empty module.isPublic or module.isPublic =='0'}"> checked="checked"</c:if>>
									</td>
								</tr>
								<tr>
									<th>目标框架</th>
									<td colspan="3">
										<select name="module.target">
											<option value="框架" <c:if test="${module.target=='框架'}">selected</c:if>>框架</option>
											<option value="新窗口" <c:if test="${module.target=='新窗口'}">selected</c:if>>新窗口</option>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="btn_area_setc">
											<a href="###" class="btn_01"  onclick="save();">保存<b></b></a>
											<!-- <a href="###" class="btn_01" onclick="clear_form(document.moduleEditFrm);">取消<b></b></a> -->
											<a href="###" class="btn_01" onclick="closeLayer();">关闭<b></b></a>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
		</div>
	</body>
</html>