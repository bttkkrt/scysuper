<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body validform="true">
   <div class="box_01 boxBmargin12 submitdata">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="gwhySave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="gwhy.id" value="${gwhy.id}">
		<input type="hidden" name="gwhy.createTime" value="<fmt:formatDate type="both" value="${gwhy.createTime}" />">
		<input type="hidden" name="gwhy.updateTime" value="${gwhy.updateTime}">
		<input type="hidden" name="gwhy.createUserID" value="${gwhy.createUserID}">
		<input type="hidden" name="gwhy.updateUserID" value="${gwhy.updateUserID}">
		<input type="hidden" name="gwhy.deptId" value="${gwhy.deptId}">
		<input type="hidden" name="gwhy.delFlag" value="${gwhy.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">月份</th>
					<td width="35%"><input name="gwhy.monthTime"  style="width:60%" value="<fmt:formatDate type='both' value='${gwhy.monthTime}'/>" datatype="*1-30" nullmsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"><font style='color:red'>*</font></td>
					<th width="15%">区域</th>
					<td width="35%"><s:select  id="areaName"   cssStyle="width:60%" name="gwhy.areaName" list="#{'':'请选择','一中队':'一中队','二中队':'二中队','三中队':'三中队','科教创新区':'科教创新区','国际商务区':'国际商务区','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道'}" theme="simple" /></td>
				</tr>
				<tr>
					<th width="15%">与共保体专门协商</th>
					<td width="35%"><s:select  id="ygbtzmxs"   cssStyle="width:60%" name="gwhy.ygbtzmxs" list="#{'已完成':'已完成','未完成':'未完成'}" theme="simple" /></td>
					<th width="15%">专题动员会</th>
					<td width="35%"><s:select  id="ztdyh"   cssStyle="width:60%" name="gwhy.ztdyh" list="#{'已完成':'已完成','未完成':'未完成'}" theme="simple" /></td>
				</tr>
				<tr>
					<th width="15%">已报试点企业</th>
					<td width="35%"><input name="gwhy.ybsdqy"  style="width:60%" value="${gwhy.ybsdqy}" type="text" datatype="*1-127" nullmsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">试点企业安全现状评估数</th>
					<td width="35%"><input name="gwhy.sdqyaqpgs"  style="width:60%" value="${gwhy.sdqyaqpgs}" type="text" datatype="*1-127" nullmsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">试点企业已投保</th>
					<td width="35%"><input name="gwhy.sdqyytb"  style="width:60%" value="${gwhy.sdqyytb}" type="text" datatype="*1-127" nullmsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_gwhy');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
