<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">材料类型</th>
					<td width="35%">
						<s:if test="caseCl.zjType == 1">
							现场照片
						</s:if>
						<s:elseif test="caseCl.zjType == 2">
							罚没款收据回执
						</s:elseif>
				    </td>
				</tr>
				<s:if test="caseCl.zjType == 1">
				<tr>
					<th width="15%">拍摄时间</th>
					<td width="35%">
				   		<fmt:formatDate type="date" value="${caseCl.picTime}"/>
				   	</td>
				   	<th width="15%">拍摄地点</th>
					<td width="35%">
				   		${caseCl.picAdd}
				   	</td>
				</tr>
				<tr>
					<th width="15%">照片内容</th>
					<td width="85%" colspan="3">
						<textarea name="caseCl.picContent" style="width:78%;height:120px" readonly="readonly">${caseCl.picContent}</textarea>
				   	</td>
				</tr>
				</s:if>
				<tr>
					<th width="15%">案件材料</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="ajclfj">
							  <c:forEach var="item" items="${picList}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">
								   		<c:choose>
											<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
											||fn:endsWith(fn:toLowerCase(item.picName),'.png')
											||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
												<img src="${item.httpUrl}/upload/photo/${item.picName}"
												border='0' width='220' height='150'/><br>&nbsp;&nbsp;&nbsp;${item.fileName}
											</c:when> 
											<c:otherwise> 
												&nbsp;&nbsp;&nbsp;${item.fileName}
											</c:otherwise>
										</c:choose>
								   </td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a></td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01"  onclick="parent.close_win('win_caseCl');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
