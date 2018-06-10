<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body validform="true">
   <div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="checkTableSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="checkTable.id" value="${checkTable.id}">
		<input type="hidden" name="checkTable.createTime" value="<fmt:formatDate type="both" value="${checkTable.createTime}" />">
		<input type="hidden" name="checkTable.updateTime" value="${checkTable.updateTime}">
		<input type="hidden" name="checkTable.createUserID" value="${checkTable.createUserID}">
		<input type="hidden" name="checkTable.updateUserID" value="${checkTable.updateUserID}">
		<input type="hidden" name="checkTable.deptId" value="${checkTable.deptId}">
		<input type="hidden" name="checkTable.delFlag" value="${checkTable.delFlag}">
		<input type="hidden" name="checkTable.companyName" value="${checkTable.companyName}"  >
		<input type="hidden" name="checkTable.companyId" value="${checkTable.companyId}"  >
		<input type="hidden" name="checkTable.companyType" value="${checkTable.companyType}"  >
		<input type="hidden" name="checkTable.areaId" value="${checkTable.areaId}"  >
		<input type="hidden" name="checkTable.areaName" value="${checkTable.areaName}"  >
			<table width="100%" border="0" >
				<tr>
					<th style="text-align:center">检查内容</th>
					<th style="text-align:center">检查情况</th>
					<th style="text-align:center">是否合格</th>
					<th style="text-align:center">备注</th>
				</tr>
				<c:forEach items="${checkContentList }" var="content" varStatus="i">
				
				<tr>
					<td>${content }</td>
					<td><input type="text" name="checkResultList[${i.index }].checkResult" value="${checkResultList[i.index ].checkResult }"  maxlength="2000"/></td>
					<td style="text-align:center">
						<select name="checkResultList[${i.index }].ifOk" style="width:100px;">
							<c:if test="${checkResultList[i.index ].ifOk!='不合格' }">
								<option value="合格" selected>合格</option>
								<option value="不合格">不合格</option>
							</c:if>
							<c:if test="${checkResultList[i.index ].ifOk=='不合格' }">
								<option value="合格" >合格</option>
								<option value="不合格" selected>不合格</option>
							</c:if>
						</select>
					</td>
					<td><input type="text" name="checkResultList[${i.index }].remark" value="${checkResultList[i.index ].remark }" maxlength="2000"/></td>
				</tr>
				</c:forEach>
				<tr>
					<th width="15%">检查时间</th>
					<td colspan="3"><input name="checkTable.checkTime" value="<fmt:formatDate type='both' value='${checkTable.checkTime}' />" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="document.myform1.reset()">重置<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="closeLayer();">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
