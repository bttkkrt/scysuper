<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>查看</title>
		<%@include file="/common/jsLib.jsp"%>
	</head>
	<body>
		<%@include file="/WEB-INF/template/content_title.jsp"%>
		<form name="myform" method="post">
			<div class="submitdata">
				<table width="100%">
					<tr>
						<th width="15%">
							检查类别
						</th>
						<td colspan="3">
							${checkContent.category.content }
						</td>
					</tr>
					<tr>
						<th width="15%">
							检查内容
						</th>
						<td colspan="3">
							${checkContent.content}
						</td>
					</tr>
					<tr>
						<th width="15%">
							排序序号
						</th>
						<td width="35%">
							${checkContent.indexNum}
						</td>
						<th width="15%">
							是否启用
						</th>
						<td width="35%">
							<s:if test='checkContent.isusing=="0" ||checkContent.isusing==null||checkContent.isusing==""'>启用</s:if>
							<s:else>作废</s:else>
						</td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td colspan="4" height="100px" align="center">
							<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
								onclick="parent.close_win();">关闭</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<%@include file="/WEB-INF/template/pagefoot.jsp"%>
	</body>
</html>
