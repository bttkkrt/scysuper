<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="supPlaXccsSave.action" >
		<s:token />
		<input type="hidden" name="supPlaXccs.id" value="${supPlaXccs.id}">
		<input type="hidden" name="supPlaXccs.createTime" value="<fmt:formatDate type="both" value="${supPlaXccs.createTime}" />">
		<input type="hidden" name="supPlaXccs.updateTime" value="${supPlaXccs.updateTime}">
		<input type="hidden" name="supPlaXccs.createUserID" value="${supPlaXccs.createUserID}">
		<input type="hidden" name="supPlaXccs.updateUserID" value="${supPlaXccs.updateUserID}">
		
		<table width="100%" border="0">
				<tr>
					<th width="25%">危化企业</th>
					<td><input type="text" style="text-align:center" name="supPlaXccs.xccs" value="${fn:split(supPlaXccs.xccs,',')[0]}" dataType="n1-2" /></td>
				</tr>
				<tr>
					<th width="50%">职业卫生企业C类</th>
					<td><input type="text" style="text-align:center" name="supPlaXccs.xccs" value="${fn:split(supPlaXccs.xccs,',')[1] }" dataType="n1-2" /></td>
				</tr>
				<tr>
					<th width="50%">职业卫生企业B类</th>
					<td><input type="text" style="text-align:center" name="supPlaXccs.xccs" value="${fn:split(supPlaXccs.xccs,',')[2] }" dataType="n1-2" /></td>
				</tr>
				<tr>
					<th width="50%">职业卫生企业A类</th>
					<td><input type="text" style="text-align:center" name="supPlaXccs.xccs" value="${fn:split(supPlaXccs.xccs,',')[3] }" dataType="n1-2" /></td>
				</tr>
				<tr>
					<th width="50%">涉爆粉尘企业</th>
					<td><input type="text" style="text-align:center" name="supPlaXccs.xccs" value="${fn:split(supPlaXccs.xccs,',')[4] }" dataType="n1-2" /></td>
				</tr>
				<tr>
					<th width="50%">涉氨制冷企业</th>
					<td><input type="text" style="text-align:center" name="supPlaXccs.xccs" value="${fn:split(supPlaXccs.xccs,',')[5] }" dataType="n1-2" /></td>
				</tr>
				<tr>
					<th width="50%">小微标准化企业</th>
					<td><input type="text" style="text-align:center" name="supPlaXccs.xccs" value="${fn:split(supPlaXccs.xccs,',')[6] }" dataType="n1-2" /></td>
				</tr>
				<tr>
					<th width="50%">推总监制度企业</th>
					<td><input type="text" style="text-align:center" name="supPlaXccs.xccs" value="${fn:split(supPlaXccs.xccs,',')[7] }" dataType="n1-2" /></td>
				</tr>
				<tr>
					<th width="50%">涉有限空间企业</th>
					<td><input type="text" style="text-align:center" name="supPlaXccs.xccs" value="${fn:split(supPlaXccs.xccs,',')[8] }" dataType="n1-2" /></td>
				</tr>
				<tr>
					<th width="50%">冶金企业</th>
					<td><input type="text" style="text-align:center" name="supPlaXccs.xccs" value="${fn:split(supPlaXccs.xccs,',')[9] }" dataType="n1-2" /></td>
				</tr>
				<tr>
					<th width="50%">工业用燃气企业</th>
					<td><input type="text" style="text-align:center" name="supPlaXccs.xccs" value="${fn:split(supPlaXccs.xccs,',')[10] }" dataType="n1-2" /></td>
				</tr>
				<tr>
					<th width="50%">工伤事故多发企业</th>
					<td><input type="text" style="text-align:center" name="supPlaXccs.xccs" value="${fn:split(supPlaXccs.xccs,',')[11] }" dataType="n1-2" /></td>
				</tr>
				<tr>
					<td colspan="2" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit"  >保存<b></b></a>&nbsp;
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
