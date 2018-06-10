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
						${law.firTitle}
					</div>
					<div class="bigtitle">
						${law.infoTitle}
					</div>
					<div class="info">
					    <span>索引号：${law.numbers}</span>
					    <span>发布机构：${law.publicAgency}</span>
						<span>生成日期：<fmt:formatDate type="date" value="${law.generateDate}" pattern="yyyy-MM-dd"/></span>
						<span>发布日期：<fmt:formatDate type="date" value="${law.publicDate}" pattern="yyyy-MM-dd"/></span>
					     <span>类型：<cus:hxlabel codeName="法律法规类型" itemValue="${law.type}" /></span>
						<span>发表时间：<fmt:formatDate type="both" value="${law.publicTime}" pattern="yyyy-MM-dd"/></span>
					</div>
					<div class="info">
						<span>内容概述：${law.remark}</span>
				
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
								onclick="parent.close_win('win_law');">关闭<b></b> </a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
