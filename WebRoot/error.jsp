<%@ page language="java" import="java.util.*,com.jshx.core.utils.SysPropertiesUtil" pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ϵͳ����</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

</head>
<%
Boolean isToJsp = new Boolean(SysPropertiesUtil.getProperty("isExceptionLogToJsp"));
int height = 100;
if(isToJsp)
	height = 600;
%>
<body>
<div style="width:100%;height:<%=height%>px" >
    <div style="width:100%;height:50px;">&nbsp;</div>
		<c:if test="${exceptionObject.exceptionType ne null}">
			<div style="width:100%;height:50px;">&nbsp;&nbsp;&nbsp;&nbsp;����ԭ��<cus:hxlabel codeName="�쳣����" itemValue="${exceptionObject.exceptionType}" /></div>
		</c:if>
		<c:if test="${exceptionObject.exceptionType eq null}">
			<div style="width:100%;height:50px;">&nbsp;&nbsp;&nbsp;&nbsp;����ԭ��
			<%
			Exception exception = (Exception)session.getAttribute("exceptionObject");
			if(exception!=null)
				out.println(exception.getLocalizedMessage());
			%>
			</div>
		</c:if>
    <%if(isToJsp){ %>
	<div style="width:100%; height: 400px; overflow-y:auto">&nbsp;&nbsp;&nbsp;&nbsp;��ջ��Ϣ��<c:out value="${exceptionStackTrace}"></c:out></div>		
	<%
    }
	%>
</div>
</body>
</html>