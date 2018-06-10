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
						<form name="myform" method="post" action="saveCodeValue.action">
							<s:token />
							<c:if test="${not empty codeValue.id}">
								<input type="hidden" name="codeValue.id" value="${codeValue.id}">
							</c:if>
							<c:if test="${not empty codeValue.parentItem.id}">
								<input type="hidden" name="codeValue.parentItem.id" value="${codeValue.parentItem.id}">
							</c:if>
							<input type="hidden" name="codeValue.codeId" value="${codeValue.codeId}">
							<table>
								<tr>
									<th>代码项值<font color="red">*</font></th>
									<td><input name="codeValue.itemValue" class="form_text" value="${codeValue.itemValue}" ></td>
								</tr>
								<tr>
									<th>代码项显示值<font color="red">*</font></th>
									<td><input name="codeValue.itemText" class="form_text" value="${codeValue.itemText}" ></td>
								</tr>
								<tr>
									<th>同级排序</th>
									<td><input name="codeValue.sortSQ" class="form_text" value="${codeValue.sortSQ}" datatype="n1-3" ignore="ignore"></td>
								</tr>
								<tr>
									<th>备注</th>
									<td><input name="codeValue.comment" class="form_text" value="${codeValue.comment}" datatype="*1-50" ignore="ignore"></td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="btn_area_setc">
											<a href="###" class="btn_01_mini" type="submit">保存<b></b></a>
											<a href="###" class="btn_01_mini" onclick="clear_form(document.myform);">取消<b></b></a>
											<a href="###" class="btn_01_mini" onclick="parent.close_win('codeValueWindow');">关闭<b></b></a>
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