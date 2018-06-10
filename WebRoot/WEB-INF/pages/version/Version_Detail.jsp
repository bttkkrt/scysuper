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
				<form name="myform" method="post">
					<div class="cell">
						<table>
							<tr>
								<th width="15%">
									版本号
								</th>
								<td width="35%">
									${version.versionNumber}
								</td>
							</tr>
							<tr>
								<th width="15%">
									版本平台
								</th>
								<td width="35%">
									<cus:hxlabel codeName="终端类型"
										itemValue="${version.versionPlatform}" />
								</td>
							</tr>
							<tr>
								<th width="15%">
									版本更新内容
								</th>
								<td width="35%">
									${version.content}
								</td>
							</tr>
							<tr>
								<th width="15%">
									版本下载
								</th>
								<td width="35%">
									${version.versionDownload}
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="btn_area_setc">
										<a class="btn_01" onclick="parent.close_win('versionWindow');">关闭<b></b>
										</a>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
