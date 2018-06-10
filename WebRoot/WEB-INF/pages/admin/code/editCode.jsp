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
						<form name="myform" method="post" action="saveCode.action">
							<s:token />
							<c:if test="${not empty code.id}">
								<input type="hidden" name="code.id" value="${code.id}">
							</c:if>
							<table>
								<tr>
									<th>代码名称<font color="red">*</font></th>
									<td><input name="code.codeName" value="${code.codeName}" class="form_text"></td>
								</tr>
								<tr>
									<th>同级排序</th>
									<td><input name="code.sortSQ" value="${code.sortSQ}" class="form_text" datatype="n1-3" ignore="ignore"></td>
								</tr>
								<tr>
									<th>备注</th>
									<td><input name="code.comments" value="${code.comments}" class="form_text" datatype="*1-50" ignore="ignore"></td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="btn_area_setc">
											<a href="###" class="btn_01_mini" type="submit">保存<b></b></a>
											<a href="###" class="btn_01_mini" onclick="clear_form(document.myform);">取消<b></b></a>
											<a href="###" class="btn_01_mini" onclick="parent.close_win('codeWindow');">关闭<b></b></a>
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