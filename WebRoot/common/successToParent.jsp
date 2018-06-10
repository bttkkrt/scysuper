<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%
		String successUrl = request.getParameter("successUrl");
	%>
	<head>
		<title>Success</title>
		<%@include file="/common/jsLib.jsp"%>
		<script language="JavaScript">
       function returnPage(successUrl) {
       		if(opener==null){
	            currTab = top.getCurrTab();
	        }else
	        	currTab = opener.top.getCurrTab();
       		target = currTab.find("iframe");
			 if(successUrl.indexOf("?")!=-1)
			 	target[0].src="${ctx}/"+successUrl+"&refresh=1";
             else
             	target[0].src="${ctx}/"+successUrl+"?refresh=1";
            window.close("_self");
		}
	</script>
	</head>
	<body>
		<div class="page_content">
			<div class="box_01" style="width:400px;">
				<div class="inner6px">
					<div class="o_tips_ok"></div>
					<div class="o_tips_say">
						<c:if test="${not empty message}">${message}</c:if>
						<c:if test="${empty message}">操作成功！</c:if>
					</div>
					<div class="btn_area_setc">
						<a href="#" class="btn_01" onclick="returnPage('<%=successUrl%>')"><b></b>确认</a>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
