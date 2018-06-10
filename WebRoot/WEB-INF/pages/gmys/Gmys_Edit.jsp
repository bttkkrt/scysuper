<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="gmysSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="gmys.id" value="${gmys.id}">
		<input type="hidden" name="gmys.createTime" value="<fmt:formatDate type="both" value="${gmys.createTime}" />">
		<input type="hidden" name="gmys.updateTime" value="${gmys.updateTime}">
		<input type="hidden" name="gmys.createUserID" value="${gmys.createUserID}">
		<input type="hidden" name="gmys.updateUserID" value="${gmys.updateUserID}">
		<input type="hidden" name="gmys.deptId" value="${gmys.deptId}">
		<input type="hidden" name="gmys.delFlag" value="${gmys.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">月份</th>
					<td width="35%"><input name="gmys.monthTime" style="width:60%"  value="<fmt:formatDate type='both' value='${gmys.monthTime}' pattern="yyyy-MM"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"><font style='color:red'>*</font></td>
					<th width="15%">区域</th>
				
					<td width="35%"><s:select  id="areaName" cssStyle="width:60%"  name="gmys.areaName" list="#{'':'请选择','一中队':'一中队','二中队':'二中队','三中队':'三中队','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道'}" theme="simple" /></td>
				</tr>
				<tr>
					<th width="15%">达标数量合计</th>
					<td width="35%"><input name="gmys.dbslhj" style="width:60%" value="${gmys.dbslhj}" type="text" datatype="*1-177" errormsg='此项为必填' maxlength="177"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>一级</strong></td>
				</tr>
				<tr>
					<th width="15%">一级计划达标数</th>
					<td width="35%"><input name="gmys.yjjhdbs" style="width:60%" value="${gmys.yjjhdbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				
					<th width="15%">一级申报企业数</th>
					<td width="35%"><input name="gmys.yjsbqys" style="width:60%" value="${gmys.yjsbqys}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">一级达标企业数</th>
					<td width="35%"><input name="gmys.yjdbqys" style="width:60%" value="${gmys.yjdbqys}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				
					<th width="15%">一级累计达标数</th>
					<td width="35%"><input name="gmys.yjljdbs" style="width:60%" value="${gmys.yjljdbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>二级</strong></td>
				</tr>
				<tr>
					<th width="15%">二级计划达标数</th>
					<td width="35%"><input name="gmys.ejjhdbs" style="width:60%" value="${gmys.ejjhdbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				
					<th width="15%">二级申报企业数</th>
					<td width="35%"><input name="gmys.ejsbqys" style="width:60%" value="${gmys.ejsbqys}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">二级达标企业数</th>
					<td width="35%"><input name="gmys.ejdbqys" style="width:60%" value="${gmys.ejdbqys}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				
					<th width="15%">二级累计达标数</th>
					<td width="35%"><input name="gmys.ejljdbs" style="width:60%" value="${gmys.ejljdbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>三级</strong></td>
				</tr>
				<tr>
					<th width="15%">三级计划复审数</th>
					<td width="35%"><input name="gmys.sjjhfss" style="width:60%" value="${gmys.sjjhfss}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				
					<th width="15%">三级复审达标数</th>
					<td width="35%"><input name="gmys.sjfsdbs" style="width:60%" value="${gmys.sjfsdbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				    <th width="15%">三级摘牌企业数</th>
					<td width="35%"><input name="gmys.sjzpqys" style="width:60%" value="${gmys.sjzpqys}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">三级累计达标数</th>
					<td width="35%"><input name="gmys.sjljdbs" style="width:60%" value="${gmys.sjljdbs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_gmys');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
