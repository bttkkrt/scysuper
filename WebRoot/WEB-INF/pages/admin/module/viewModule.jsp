<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<c:set var="curr_path" value="查看模块设置"></c:set>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Module Detail</title>
<%@include file="/common/jsLib.jsp"%>
<script src="${ctx}/dwr/engine.js"></script>
<script src="${ctx}/dwr/util.js"></script>
<script type='text/javascript' src='${ctx}/dwr/interface/moduleService.js'> </script>
	<script>
         var moduleCode = '${model.moduleCode}';
         moduleCode = moduleCode.substr(0,moduleCode.length-2);
         function load_parent(){
		     moduleService.getDeptByModuleCode(moduleCode,function(module){
			     if(module!=null)
			         document.getElementById("parent").innerHTML = module.moduleName;
		     });
		}
        function edit_module(moduleId){
            
            //window.open('getModel.action?moduleCode='+moduleCode,'_self');
            var ff = document.moduleFrm;
            ff.action = "get.action";
            ff.moduleId.value = moduleId;
            ff.mode.value = "edit";
            ff.submit();
        }
        
        function delete_module(moduleId){
            var con = confirm("确定要删除该部门？");
            if(con){
                var ff = document.moduleFrm;
                ff.action = "delete.action";
                
                ff.moduleId.value = moduleId;
                ff.submit();
            }
        }
    </script>
</head>

<body onload="load_parent()">
<form name="moduleFrm" method="post">
	<input type="hidden" name="moduleId"> 
	<input type="hidden" name="mode" /> 
	<input type="hidden" value="${moduleCode }" name="moduleCode">
</form>
<table width="100%" height="100%">
	<tr height="22px">
		<td class="TableSpecial1">模块名称</td>
		<td class="TableSpecial">${model.moduleName }</td>
		<td class="TableSpecial1">上层模块</td>
		<td class="TableSpecial" valign="middle" id="parent"></td>
	</tr>
	<tr height="22px">
		<td class="TableSpecial1">模块编号</td>
		<td class="TableSpecial">${model.moduleCode}</td>
		<td class="TableSpecial1">对应地址</td>
		<td class="TableSpecial">${model.moduleAddr }</td>
	</tr>
	<tr height="22px">
		<td class="TableSpecial1">同级排序</td>
		<td class="TableSpecial">${model.sortSq }</td>
		<td class="TableSpecial1">目标框架</td>
		<td class="TableSpecial">${model.target}</td>
	</tr>
	<tr height="22px">
		<td class="TableSpecial1">大图标</td>
		<td class="TableSpecial" valign="middle">
			${model.bigIconAddr} 
			<img src="${ctx}/BigIcon/${model.bigIconAddr}" border="0">
		</td>
		<td class="TableSpecial1">小图标</td>
		<td class="TableSpecial" valign="middle">
			${model.smallIconAddr }
			<img src="${ctx}/SmallIcon/${model.smallIconAddr }" border="0">
		</td>
	</tr>
	<tr height="22px">
		<td class="TableSpecial1">可见</td>
		<td class="TableSpecial" valign="middle">
			<c:if test="${model.isVisible==1}">显示</c:if> 
			<c:if test="${model.isVisible==0}">隐藏</c:if>
		</td>
		<td class="TableSpecial1">其他属性</td>
		<td class="TableSpecial" valign="middle">
			<c:if test="${empty model.otherProperty}">无</c:if> 
			<c:if test="${not empty model.otherProperty}">${model.otherProperty}</c:if>
		</td>
	</tr>
	<tr height="22px">
		<td class="tb_01">
			用户角色
		</td>
		<td class="tb_02" colspan="3">
			<c:forEach var="right" items="${module.rightList}">
				${right.role.roleName}, 
			</c:forEach>
		</td>
	</tr>
	<tr height="22px">
		<td align='center' colspan='4'>
			<input type='button' value='编辑' class="btnbg" onclick="edit_module('${model.moduleId}')" /> 
			<input type='button' value='删除' onclick="delete_module('${model.moduleId}')" class="btnbg" /> 
			<input type='button' value='关闭' onclick="window.close('_self')" class="btnbg" />
		</td>
	</tr>
</table>
</body>
</html>