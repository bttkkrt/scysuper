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
						表格名称
					</th>
					<td width="35%">
						${bgxz.infoTitle}
					</td>
					<th width="15%">
						表格种类
					</th>
					<td width="35%">
						<s:if test="bgxz.bgzl == 1">
							城市管理
						</s:if>
						<s:elseif test="bgxz.bgzl == 2">
							安全生产
						</s:elseif>
						<s:elseif test="bgxz.bgzl == 3">
							行政执法
						</s:elseif>
						<s:else>
							其他
						</s:else>
					</td>
				</tr>
				<tr>
					<th width="15%">
						内容概述
					</th>
					<td width="85%" colspan="3">
						<textarea name="bgxz.nrgs" style="height:150px" readonly="readonly">${bgxz.nrgs}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">表格下载</th>
					<td width="85%" colspan="3">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							<c:forEach var="item" items="${picList}">
								<tr>
								   <td><a href="javascript:downloadFile('${item.id}');">${item.fileName}</a></td>
								</tr>
							</c:forEach>
						</table>
						</div>
				    </td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_bgxz');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</body>
</html>
