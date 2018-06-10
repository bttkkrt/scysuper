<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看</title>
		<%@include file="/common/jsLib.jsp"%>
	</head>
	<body>
		<div class="page_dialog">
			<form name="myform" method="post" enctype="multipart/form-data">
				<div class="inner12px">
					<div class="smalltitle">
						${tzgg.firTitle}
					</div>
					<div class="bigtitle">
						${tzgg.infoTitle}
					</div>
					<div class="info">
						<span>发表时间：<fmt:formatDate type="date" value="${tzgg.publicDate}" pattern="yyyy-MM-dd"/></span>
						<span>发布者：${tzgg.user.displayName}</span>
						<span>点击量：${tzgg.readNum}次</span>
						
					</div>
					<div class="pagecontent">
						${infoContent}
					</div>
					<div class="cell">
						<table>
							<tr>
								<td>
									<strong>附件：</strong>
								</td>
							</tr>
							<c:forEach var="item" items="${picList}">
								<tr>
								   <td><a href="javascript:downloadFile('${item.id}');">${item.fileName}</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
					
					<div class="inner6px">
						<div class="btn_area_setc">
							<a href="###" class="btn_01"
								onclick="parent.close_win('win_tzgg');">关闭<b></b> </a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
