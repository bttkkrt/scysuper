<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" >${safLed.areaName}</td>
			
					<th width="15%">企业名称</th>
					<td width="35%" >${safLed.companyName}</td>
				</tr>
				<tr>
					<th width="15%">台账类型</th>
					<td width="35%" ><cus:hxlabel codeName="安全设施登记台账类别" itemValue="${safLed.accountType}" /></td>
					<th width="15%">类别</th>
					<s:if test="safLed.accountType==1">
					<td width="35%"><cus:hxlabel codeName="预防事故设施台帐" itemValue="${safLed.category}" /></td>
					</s:if>
					<s:elseif test="safLed.accountType==2">
					<td width="35%"><cus:hxlabel codeName="控制事故设施台帐" itemValue="${safLed.category}" /></td>
					</s:elseif>
					<s:elseif test="safLed.accountType==3">
					<td width="35%"><cus:hxlabel codeName="减少与消除事故影响设施台帐" itemValue="${safLed.category}" /></td>
					</s:elseif>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${picList}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">
								   		<c:choose>
											<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
											||fn:endsWith(fn:toLowerCase(item.picName),'.png')
											||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
											<a href="${item.httpUrl}/upload/photo/${item.picName}" rel="example_group">	
												<img src="${item.httpUrl}/upload/photo/${item.picName}"
												border='0' width='220' height='150'/>
											</a>
											</c:when> 
											<c:otherwise> 
												&nbsp;&nbsp;&nbsp;${item.fileName}
											</c:otherwise>
										</c:choose>
								   </td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;</td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_safLed');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>
