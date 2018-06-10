<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	$(function(){
	 var a="${picList}";
	 var len=a.length;
	 if(len=="2"){
	 $("#fj").hide();
	 }
	 })
	</script>		
</head>
<body>
		<div class="page_dialog">
			<form name="myform" method="post" enctype="multipart/form-data">
				<div class="inner12px">
					<div class="bigtitle">
						${sendInformation.sendinfoTitle}
					</div>
					<div class="info">
						<span>拟稿人：${sendInformation.userName}</span>
						<span>发表时间：<fmt:formatDate type="date" value="${sendInformation.sendinfoDraftTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
						<span>发文字号：${sendInformation.sendinfoNum}</span>
						<span>发文类型：<cus:hxlabel codeName="发文类型" itemValue="${sendInformation.sendinfoType}" /></span>
					</div>
					<div class="pagecontent">
						${infoContent}
					</div>
					<div id="fj" class="cell">
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
					</div>
					<div class="cell">
					  <strong>用户阅读记录:</strong>
					<br/>
				<table width="90%" style="border:solid #add9c0; border-width:1px 0px 0px 1px">
					<s:iterator value="noticelist" status="st">
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">
								${userName }
							</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">
								${isRead }:
								${lastReadTime }
							</td>
						</tr>
					</s:iterator>
				</table>
					</div>
					<div class="cell">
					
					<strong>审核记录:</strong>
					<br/>
					<tr>
					<th width="100%">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</th>
				    </tr>
					
					</div>
					<div class="inner6px">
						<div class="btn_area_setc">
							<a href="###" class="btn_01"
								onclick="parent.close_win('win_sendInformation');">关闭<b></b> </a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
