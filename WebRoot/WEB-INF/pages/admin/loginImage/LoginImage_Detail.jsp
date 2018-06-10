<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
	<div class="page_dialog">
		<form name="myform" method="post">
			<div class="cell">
				<table>
					<tr>
						<th width="15%">图片名</th>
						<td width="35%">${loginImage.imageName}</td>
					</tr>
					<tr>
						<td width="100%" colspan="2"><img src="${ctx}${loginImage.imageUrl}" style="width:100%"/></td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="btn_area_setc">
								<a href="###" class="btn_01" onclick="parent.close_win('win_loginImage');">关闭<b></b></a>
							</div>
						</td>
					</tr>						
				</table>
			</div>
		</form>
	</div>
</body>
</html>
