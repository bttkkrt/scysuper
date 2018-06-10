<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
	$(function(){
	 /* var a="${attachList}";
	 var len=a.length;
	 if(len=="2"){
	 $("#fj").hide();
	 } */
	 });
	
	function downloadFile(attachId){
		window.location.href="${ctx}/jsp/information/download.action?attachId="+attachId;
		/* $.ajax({
			type:"POST",
			url:"${ctx}/jsp/information/download.action?attachId="+attachId,
			dateType:"json",
			success:function(data){
			}
		}); */
	}
	</script>		
	</head>
	<body>
		<div class="page_dialog">
			<form name="myform" method="post" enctype="multipart/form-data">
				<div class="inner12px">
					<div class="bigtitle">
						${contentInformations.infoTitle}
					</div>
					<div class="info">
						<span>发布人：${contentInformations.user.displayName}</span>
						<span>发表时间：<fmt:formatDate type="date" value="${contentInformations.publicDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
						<span>信息类型：<cus:hxlabel codeName="信息类型" itemValue="${contentInformations.infoType}"/></span>
						<span>阅读期限：<fmt:formatDate type="date" value="${contentInformations.readPeriod}" pattern="yyyy-MM-dd HH"/></span>
					</div>
					<div class="pagecontent">
						${infoContent}
					</div>
					<div id="fj" class="cell">
						
							<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${attachList}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">
								   		<c:choose>
											<c:when test="${fn:endsWith(fn:toLowerCase(item.attachName),'.jpg')
												||fn:endsWith(fn:toLowerCase(item.attachName),'.bmp')
												||fn:endsWith(fn:toLowerCase(item.attachName),'.png')
												||fn:endsWith(fn:toLowerCase(item.attachName),'.jpeg')
												||fn:endsWith(fn:toLowerCase(item.attachName),'.gif')}"> 
												<a href="${item.docUrl}/${item.attachName}" rel="example_group">	
													<img src="${item.docUrl}/${item.attachName}"
													border='0' width='220' height='150'/>
												</a>
											</c:when> 
											<c:otherwise> 
												<span style="color: brown;">${item.docName}</span>
											</c:otherwise>
										</c:choose>
								   </td>
								   <%-- <td width="30%"><a href="${ctx}/${item.docUrl}/${item.attachName}">&nbsp;下载&nbsp;</a></td> --%>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">&nbsp;下载&nbsp;</a></td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
						
					</div>
					<s:if test="readable==1">
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
					</s:if>
					<s:if test="flag!=0">
					<div class="inner6px">
						<div class="btn_area_setc">
							<a href="###" class="btn_01"
								onclick="closeLayer();">关闭<b></b> </a>
						</div>
					</div>
					</s:if>
				</div>
			</form>
		</div>
	</body>
</html>
