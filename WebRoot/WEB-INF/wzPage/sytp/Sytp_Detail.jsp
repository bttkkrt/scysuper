<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看</title>
		<%@include file="/common/jsLib.jsp"%>
	</head>
	<body>
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">
					标题
					</th>
					<td colspan="3">
						${sytp.infoTitle}
					</td>
				</tr>
				<tr>
					<th width="15%">图片</th>
					<td width="85%" colspan="3">
						<a href="${sytp.filePath}" rel="example_group">	
							<img src="${sytp.filePath}"
								border='0' width='220' height='150'/>
						</a>
				    </td>
				</tr>
			</table>
		</div></div></div>
	</body>
</html>
