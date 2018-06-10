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
	<form name="myform1" method="post" enctype="multipart/form-data" action="qyaqscytSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="qyaqscyt.id" value="${qyaqscyt.id}">
		<input type="hidden" name="qyaqscyt.createTime" value="<fmt:formatDate type="both" value="${qyaqscyt.createTime}" />">
		<input type="hidden" name="qyaqscyt.updateTime" value="${qyaqscyt.updateTime}">
		<input type="hidden" name="qyaqscyt.createUserID" value="${qyaqscyt.createUserID}">
		<input type="hidden" name="qyaqscyt.updateUserID" value="${qyaqscyt.updateUserID}">
		<input type="hidden" name="qyaqscyt.deptId" value="${qyaqscyt.deptId}">
		<input type="hidden" name="qyaqscyt.delFlag" value="${qyaqscyt.delFlag}">
		
			<table width="100%" border="0">
				<tr>
				    <th width="15%">月份</th>
					<td width="35%"><input name="qyaqscyt.monthTime" value="<fmt:formatDate type='both' value='${qyaqscyt.monthTime}' pattern="yyyy-MM"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"><font style='color:red'>*</font></td>
					<th width="15%">约谈分类</th>
					
					<td width="35%"><s:select  id="areaName"   cssStyle="width:60%" name="qyaqscyt.areaName" list="#{'':'请选择','安监局':'安监局','综合二处':'综合二处','监管三处':'监管三处','安监大队':'安监大队','第一中队':'第一中队','第二中队':'第二中队','第三中队':'第三中队','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道','胜浦街道':'胜浦街道'}" theme="simple" datatype="*" errormsg='此项为必填'/><font style='color:red'>*</font></td>
					
				</tr>
				<tr>
					<th width="15%">本年度应约谈数</th>
					<td width="35%"><input name="qyaqscyt.bndyyt" value="${qyaqscyt.bndyyt}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">本月约谈企业数</th>
					<td width="35%"><input name="qyaqscyt.byytqys" value="${qyaqscyt.byytqys}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">本年度累计约谈企业数</th>
					<td width="35%"><input name="qyaqscyt.bnyljytqys" value="${qyaqscyt.bnyljytqys}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">备注</th>
					<td width="35%"><input name="qyaqscyt.remark" value="${qyaqscyt.remark}" type="text" maxlength="127"></td>
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
						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_qyaqscyt');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
