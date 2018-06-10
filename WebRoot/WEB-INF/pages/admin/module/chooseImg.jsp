<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<c:set var="curr_path" value="选择图片"></c:set>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<title>选择图片</title>
<%@include file="/common/jsLib.jsp"%>
<script>
function set_img(name,target){
	var el = parent.document.frames["moduleWindow_frm"].document.getElementById(target);
	el.value = name;
	parent.close_win("ModuleTree");
}
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <c:if test="${imageType=='Big'}">
    <c:set var="target" value="bigIconAddr"></c:set>
    </c:if>
    <c:if test="${imageType=='Small'}">
    <c:set var="target" value="smallIconAddr"></c:set>
    </c:if>
    <c:set var="i" value="0"></c:set>
    <c:forEach var="fileName" items="${fileNames}">
    <c:if test="${i%8==0}">
    <tr>
    </c:if>
    <td><a href='#' onclick="set_img('${fileName}','${target }')"><img border='0' src='${ctx}/webResources/images/${imageType}Icon/${fileName}'></a></td>
    <c:set var="i" value="${i+1}"></c:set>
    </c:forEach>
</table>
</body>
</html>